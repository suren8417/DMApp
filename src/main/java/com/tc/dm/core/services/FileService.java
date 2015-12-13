package com.tc.dm.core.services;

import com.tc.dm.rest.dto.ItemContent;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String storeFile(MultipartFile file) throws Exception;

    public boolean deleteFile(String contentPath) throws Exception;

    public MultipartFile getFile(String contentPath) throws Exception;

    boolean copyToCache(String contentPath) throws Exception;

    boolean clearCache() throws Exception;
}
