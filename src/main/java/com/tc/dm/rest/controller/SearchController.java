package com.tc.dm.rest.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tc.dm.core.services.SearchService;
import com.tc.dm.rest.dto.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.Arrays;
import java.util.List;
import static com.tc.dm.core.util.CommonUtil.*;

@Controller
@RequestMapping("/searches")
public class SearchController {

    private SearchService searchService;

    @Autowired
    public SearchController(SearchService searchService) {
        this.searchService = searchService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getItems(@RequestParam("searchQuery") String searchQueryString) {

        try {
            ObjectMapper mapper = new ObjectMapper();
            SearchQueryDto searchQueryDto = mapper.readValue(searchQueryString, SearchQueryDto.class);


            SearchParam searchParam = new SearchParam();
            searchParam.setTextToSearch(searchQueryDto.getSearchText());

            String TYPE_BOX = "YES";
            if(TYPE_BOX.equals(searchQueryDto.getImage())) { searchParam.getTypes().add(ItemType.IMAGE);}
            if(TYPE_BOX.equals(searchQueryDto.getDocument())) { searchParam.getTypes().add(ItemType.DOCUMENT);}
            if(TYPE_BOX.equals(searchQueryDto.getAudio())) { searchParam.getTypes().add(ItemType.AUDIO);}
            if(TYPE_BOX.equals(searchQueryDto.getVideo())) { searchParam.getTypes().add(ItemType.VIDEO);}
            if(TYPE_BOX.equals(searchQueryDto.getCollection())) { searchParam.getTypes().add(ItemType.COLLECTION);}

            searchParam.setDateOfOriginFrom(searchQueryDto.getStartDate());
            searchParam.setDateOfOriginTo(searchQueryDto.getEndDate());
            searchParam.setDateAddedFrom(searchQueryDto.getAddedStartDate());
            searchParam.setDateAddedTo(searchQueryDto.getAddedEndDate());
            searchParam.setStatus(Arrays.asList(ItemStatus.APPROVED));

            List<SearchResultDto> resultDtoList = searchService.search(searchParam);

            SearchResponseDto searchResponseDto = new SearchResponseDto();
            searchResponseDto.setSearchResultDtos(resultDtoList);
            searchResponseDto.setStatus("OK");
            return new ResponseEntity(searchResponseDto, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
