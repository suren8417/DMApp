package com.tc.dm.rest.controller;

import com.tc.dm.core.services.SearchService;
import com.tc.dm.rest.dto.SearchParam;
import com.tc.dm.rest.dto.SearchResponseDto;
import com.tc.dm.rest.dto.SearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("/searches")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItems(@RequestParam("searchText") String searchText, @RequestParam("image") String image,@RequestParam("document") String document,@RequestParam("audio") String audio,@RequestParam("video") String video,@RequestParam("collection") String collection,@RequestParam("startDate") String startDate,@RequestParam("endDate") String endDate) {

        try {

            SearchParam searchParam = new SearchParam();
            searchParam.setTextToSearch(searchText);
            //searchParam.setDateOfOriginFrom(toDate(startDate));
            //searchParam.setDateOfOriginTo(toDate(endDate));

            List<SearchResultDto> resultDtoList = searchService.search(searchParam);

            SearchResponseDto searchResponseDto = new SearchResponseDto();
            searchResponseDto.setStatus("OK");
            return new ResponseEntity(searchResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
