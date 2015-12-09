package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.CollectionService;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.CollectionDto;
import com.tc.dm.rest.dto.CollectionResponseDto;
import com.tc.dm.rest.dto.ItemDto;
import com.tc.dm.rest.dto.ItemResponseDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
@RequestMapping("/collections")
public class CollectionController {

    private CollectionService collectionService;
    private ItemService itemService;

    @Autowired
    public CollectionController(CollectionService collectionService, ItemService itemService) {
        this.collectionService = collectionService;
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItems() {

        try {
            List<CollectionDto> collectionDtos = CollectionDto.toDtos(collectionService.findAllCollections());
            List<ItemDto> itemDtos = ItemDto.toItemDtos(itemService.findAllItems());
            CollectionResponseDto collectionResponseDto = new CollectionResponseDto();
            collectionResponseDto.setItemDtos(itemDtos);
            collectionResponseDto.setCollectionDto(collectionDtos);
            collectionResponseDto.setStatus("OK");
            return new ResponseEntity(collectionResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @RequestMapping(value = "/collection", method = RequestMethod.POST)
    public ResponseEntity addItem(@RequestParam("itemArray") String itemArray, @RequestParam("collection") String collection) {
        ObjectMapper mapper = new ObjectMapper();
        try {
            List<Integer> list = new ObjectMapper().readValue(itemArray, List.class);
            CollectionDto collectionDto = mapper.readValue(collection, CollectionDto.class);

            for(Integer itemId: list){
                ItemDto itemDto = new ItemDto();
                itemDto.setId(Long.valueOf(itemId));
                collectionDto.getItemDtos().add(itemDto);
            }
            collectionService.createCollection(collectionDto.toCollection());

            List<CollectionDto> collectionDtos = CollectionDto.toDtos(collectionService.findAllCollections());
            List<ItemDto> itemDtos = ItemDto.toItemDtos(itemService.findAllItems());
            CollectionResponseDto collectionResponseDto = new CollectionResponseDto();
            collectionResponseDto.setItemDtos(itemDtos);
            collectionResponseDto.setCollectionDto(collectionDtos);
            collectionResponseDto.setStatus("OK");
            return new ResponseEntity(collectionResponseDto, HttpStatus.OK);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return new ResponseEntity(HttpStatus.OK);

    }


}
