package com.tc.dm.core.services.impl;

import com.tc.dm.core.services.FileService;
import com.tc.dm.rest.dto.ItemContent;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service
public class FileServiceImpl implements FileService {
    @Override
    public String storeFile(ItemContent content) {
        return "temPath";
    }

    @Override
    public boolean deleteFile(String contentPath) {
        return false;
    }

    @Override
    public ItemContent getFile(String contentPath) {
        return null;
    }
}
