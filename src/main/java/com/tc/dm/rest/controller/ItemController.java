package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.ItemDto;
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

    @RequestMapping(value="/item", method=RequestMethod.POST )
    public ResponseEntity addItem(@RequestParam("file") MultipartFile  file,@RequestParam("itemDto") String itemDtoString){

        String fileName = null;
        if (!file.isEmpty()) {
            try {
                ObjectMapper mapper = new ObjectMapper();
                ItemDto itemDto = mapper.readValue(itemDtoString, ItemDto.class);
                fileName = file.getOriginalFilename();
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File("/data/upload/" + fileName)));
                buffStream.write(bytes);
                buffStream.close();
                return new ResponseEntity(HttpStatus.OK);
            } catch (Exception e) {
                return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
            }
        } else {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


}
