package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.core.util.CommonUtil;
import com.tc.dm.rest.dto.ItemDto;
import com.tc.dm.rest.dto.ItemResponseDto;
import com.tc.dm.rest.dto.ItemStatus;
import com.tc.dm.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
@RequestMapping("/items")
public class ItemController {

    private ItemService itemService;

    @Autowired
    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(value = "/item", method = RequestMethod.POST)
    public ResponseEntity addItem(HttpServletRequest request,
                                  @RequestParam("file") MultipartFile file,
                                  @RequestParam("itemDto") String itemDtoString) {

        ItemResponseDto itemResponseDto = new ItemResponseDto();
        if (!file.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ItemDto itemDto = mapper.readValue(itemDtoString, ItemDto.class);
                itemDto.setUploadItem(file);
                if (itemDto.getId() != null) {
                    itemDto.setStatus(ItemStatus.EDITED.toString());
                    itemService.updateItem(itemDto.toItem());
                } else {
                    Object currentUser = request.getSession().getAttribute("currentUser");
                    itemDto.setStatus(ItemStatus.NEW.toString());
                    itemDto.setAddedBy(CommonUtil.isNullOrEmpty(currentUser)?"ANONYMOUS":String.valueOf(currentUser));
                    itemDto.setAddedDate(new Date());
                    itemService.createItem(itemDto.toItem());
                }

                List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW, ItemStatus.EDITED, ItemStatus.PENDING));
                itemResponseDto.setItemDtos(itemDtos);
                itemResponseDto.setStatus("OK");
                return new ResponseEntity(itemResponseDto, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        }
        itemResponseDto.setStatus("No attachment");
        return new ResponseEntity(itemResponseDto, HttpStatus.OK);

    }


    @RequestMapping(value = "/itemDetail", method = RequestMethod.POST)
    public ResponseEntity addItemWhithOutFile(@RequestParam("itemDto") String itemDtoString) {

        ItemResponseDto itemResponseDto = new ItemResponseDto();
        try {
            ObjectMapper mapper = new ObjectMapper();
            ItemDto itemDto = mapper.readValue(itemDtoString, ItemDto.class);
            itemDto.setStatus(ItemStatus.EDITED.toString());
            if (itemDto.getId() != null) {
                itemService.updateItem(itemDto.toItem());
            }
            List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW, ItemStatus.EDITED, ItemStatus.PENDING));
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "/validateItem", method = RequestMethod.POST)
    public ResponseEntity validate(@RequestParam("itemArray") String itemArray) {

        ItemResponseDto itemResponseDto = new ItemResponseDto();
        ItemDto itemDto = new ItemDto();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> list = new ObjectMapper().readValue(itemArray, List.class);
            for (Integer itemId : list) {
                Item item = itemService.findItemById(Long.valueOf(itemId));
                item.setStatus(ItemStatus.APPROVED.toString());
                itemService.updateItem(item);
            }
            List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW, ItemStatus.EDITED, ItemStatus.PENDING));
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(value = "/rejectItem", method = RequestMethod.POST)
    public ResponseEntity reject(@RequestParam("itemArray") String itemArray) {

        ItemResponseDto itemResponseDto = new ItemResponseDto();
        ItemDto itemDto = new ItemDto();
        try {
            ObjectMapper mapper = new ObjectMapper();
            List<Integer> list = new ObjectMapper().readValue(itemArray, List.class);
            for (Integer itemId : list) {
                Item item = itemService.findItemById(Long.valueOf(itemId));
                item.setStatus(ItemStatus.PENDING.toString());
                itemService.updateItem(item);
            }
            List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW, ItemStatus.EDITED, ItemStatus.PENDING));
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItems() {

        try {
            ItemDto userDto = new ItemDto();
            List<ItemDto> itemDtos = userDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW, ItemStatus.EDITED, ItemStatus.PENDING));
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/item", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@RequestParam("deleteItem") Long deleteItemId) {

        try {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(deleteItemId);
            itemService.deleteItem(itemDto.toItem());

            List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW, ItemStatus.EDITED, ItemStatus.PENDING));
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
