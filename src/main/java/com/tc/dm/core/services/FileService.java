package com.tc.dm.core.services;

import com.tc.dm.rest.dto.ItemContent;

public interface FileService {

    public String storeFile(ItemContent content);

    public boolean deleteFile(String contentPath);

    public ItemContent getFile(String contentPath);
}
