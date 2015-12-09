package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.services.CollectionService;
import com.tc.dm.core.services.ItemService;
import com.tc.dm.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import static com.tc.dm.core.util.CommonUtil.*;

/**
 * Created by sg40304 on 7/9/15.
 */
@Controller
@RequestMapping("/searches")
public class SearchController {

    private ItemService itemService;

    @Autowired
    public SearchController(ItemService itemService) {
        this.itemService = itemService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItems(@RequestParam("searchText") String searchText, @RequestParam("image") String image,@RequestParam("document") String document,@RequestParam("audio") String audio,@RequestParam("video") String video,@RequestParam("collection") String collection,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {

        try {

            ItemSearchParam itemSearchParam = new ItemSearchParam();
            itemSearchParam.setTextToSearch(searchText);
            //itemSearchParam.setDateOfOriginFrom(toDate(startDate));
            //itemSearchParam.setDateOfOriginTo(toDate(endDate));

            itemService.searchItems(itemSearchParam);

            SearchResponseDto searchResponseDto = new SearchResponseDto();
            searchResponseDto.setStatus("OK");
            return new ResponseEntity(searchResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
