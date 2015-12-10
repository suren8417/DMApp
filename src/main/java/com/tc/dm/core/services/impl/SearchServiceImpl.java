package com.tc.dm.core.services.impl;

import com.tc.dm.core.dao.impl.CollectionDaoImpl;
import com.tc.dm.core.dao.impl.ItemDaoImpl;
import com.tc.dm.core.entities.Collection;
import com.tc.dm.core.entities.Item;
import com.tc.dm.core.services.SearchService;
import com.tc.dm.rest.dto.ItemDto;
import com.tc.dm.rest.dto.ItemSearchParam;
import com.tc.dm.rest.dto.SearchResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

import static com.tc.dm.core.util.CommonUtil.isNullOrEmpty;
import static com.tc.dm.rest.dto.ItemType.COLLECTION;

@Transactional(rollbackFor = Exception.class)
@Service
public class SearchServiceImpl implements SearchService {

    @Autowired
    ItemDaoImpl itemDao;

    @Autowired
    CollectionDaoImpl collectionDao;

    @Override
    public List<SearchResultDto> searchItems(ItemSearchParam searchParam) {

        if(isNullOrEmpty(searchParam)) return new ArrayList<SearchResultDto>();

        List<SearchResultDto> result = new ArrayList<SearchResultDto>();


        if(searchParam.getTypes().contains(COLLECTION)){

            Map<String, String> collectionParam = new HashMap<>();
            collectionParam.put("name", searchParam.getTextToSearch());
            List<Collection> collectionListOnName = collectionDao.search(collectionParam);

            collectionParam.clear();
            collectionParam.put("description", searchParam.getTextToSearch());
            List<Collection> collectionListOnDesc = collectionDao.search(collectionParam);

            mergeIntoResultDtoList(result, collectionListOnName, collectionListOnDesc);
        }

        for(Item item : itemDao.search(searchParam.toMap())) {
            SearchResultDto dto = new SearchResultDto();
            dto.setTitle(item.getTitle());
            dto.setDescription(item.getDescription());
            dto.setItemDtos(Arrays.asList(ItemDto.toDto(item)));
            result.add(dto);
        }
        return result;
    }

    private void mergeIntoResultDtoList(List<SearchResultDto> result, List<Collection>... collectionLists) {

        if(isNullOrEmpty(result) || isNullOrEmpty(collectionLists)) return;

        List<Long> ids = new ArrayList<>();
        for(List<Collection> collectionList : collectionLists) {
            for(Collection collection : collectionList) {
                if(ids.contains(collection.getId())) continue;
                ids.add(collection.getId());

                SearchResultDto dto = new SearchResultDto();
                dto.setTitle(collection.getName());
                dto.setDescription(collection.getDescription());
                dto.setItemDtos(ItemDto.toItemDtos(collection.getItems()));
                result.add(dto);
            }
        }
    }
}
