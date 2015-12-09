package com.tc.dm.rest.dto;

import java.util.List;

/**
 * Created by sg40304 on 12/9/15.
 */
public class SearchResponseDto {


    private List<SearchResultDto> searchResultDtos;
    private String status;
    private String message;


    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<SearchResultDto> getSearchResultDtos() {
        return searchResultDtos;
    }

    public void setSearchResultDtos(List<SearchResultDto> searchResultDtos) {
        this.searchResultDtos = searchResultDtos;
    }
}
