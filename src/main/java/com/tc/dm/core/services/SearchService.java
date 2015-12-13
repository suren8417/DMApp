package com.tc.dm.core.services;

import com.tc.dm.rest.dto.SearchParam;
import com.tc.dm.rest.dto.SearchResultDto;

import java.util.List;

public interface SearchService {

    public List<SearchResultDto> search(SearchParam searchParam) throws Exception;
}
