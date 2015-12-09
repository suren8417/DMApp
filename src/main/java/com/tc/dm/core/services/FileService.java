package com.tc.dm.core.services;

import com.tc.dm.rest.dto.ItemContent;
import org.springframework.web.multipart.MultipartFile;

public interface FileService {

    public String storeFile(MultipartFile file);

    public boolean deleteFile(String contentPath);

    public MultipartFile getFile(String contentPath);
}
