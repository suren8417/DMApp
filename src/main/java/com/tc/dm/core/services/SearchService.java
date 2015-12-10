package com.tc.dm.core.services;

import com.tc.dm.core.entities.Item;
import com.tc.dm.rest.dto.ItemSearchParam;
import com.tc.dm.rest.dto.SearchResultDto;

import java.util.List;

public interface SearchService {

    public List<SearchResultDto> searchItems(ItemSearchParam searchParam);
}
