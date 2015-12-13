package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemDto;
import com.tc.dm.rest.dto.ItemResponseDto;
import com.tc.dm.rest.dto.ItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
@RequestMapping("/validates")
public class ValidateController {

    private ItemService itemService;

    @Autowired
    public ValidateController(ItemService itemService) {
        this.itemService = itemService;
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
            List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW,ItemStatus.APPROVED));
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
            List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW,ItemStatus.APPROVED));
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
            List<ItemDto> itemDtos = userDto.toItemDtos(itemService.findItemsByStatus(ItemStatus.NEW,ItemStatus.APPROVED));
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }



}
