package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemDto;
import com.tc.dm.rest.dto.ItemResponseDto;
import com.tc.dm.rest.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
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
    public ResponseEntity addItem(@RequestParam("file") MultipartFile file, @RequestParam("itemDto") String itemDtoString) {

        if (!file.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ItemDto itemDto = mapper.readValue(itemDtoString, ItemDto.class);
                itemDto.setUploadItem(file);
                itemService.createItem(itemDto.toItem());

                List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findAllItems());
                ItemResponseDto itemResponseDto = new ItemResponseDto();
                itemResponseDto.setItemDtos(itemDtos);
                itemResponseDto.setMessage("Creat item");
                itemResponseDto.setStatus("OK");
                return new ResponseEntity(itemResponseDto, HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItems() {

        try {
            ItemDto userDto = new ItemDto();
            List<ItemDto> itemDtos = userDto.toItemDtos(itemService.findAllItems());
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setMessage("Sent items");
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }

    }

    @RequestMapping(value = "/item", method = RequestMethod.DELETE)
    public ResponseEntity deleteAccount(@RequestParam("deleteItem") Long deleteItemId) {
        try {
            ItemDto itemDto = new ItemDto();
            itemDto.setId(deleteItemId);
            itemService.deleteItem(itemDto.toItem());

            List<ItemDto> itemDtos = itemDto.toItemDtos(itemService.findAllItems());
            ItemResponseDto itemResponseDto = new ItemResponseDto();
            itemResponseDto.setItemDtos(itemDtos);
            itemResponseDto.setMessage("Delete item");
            itemResponseDto.setStatus("OK");
            return new ResponseEntity(itemResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            throw exception;
        }
    }


}
