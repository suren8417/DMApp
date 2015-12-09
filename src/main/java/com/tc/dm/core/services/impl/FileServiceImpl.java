package com.tc.dm.core.services.impl;

import com.tc.dm.core.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

@Transactional
@Service
public class FileServiceImpl implements FileService {

    @Value("${filestore.path}")
    private String filestorePath;

    public String getFilestorePath() {
        return filestorePath;
    }

    @Override
    public String storeFile(MultipartFile file) {

        String fileName = null;
        String saveLocation = null;
        if (!file.isEmpty()) {
            try {

                fileName = file.getOriginalFilename();
                saveLocation= getFilestorePath()+fileName;
                byte[] bytes = file.getBytes();
                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(saveLocation)));
                buffStream.write(bytes);
                buffStream.close();

            } catch (Exception e) {
            }
        }
        return saveLocation;

    }

    @Override
    public boolean deleteFile(String contentPath) {
        return false;
    }

    @Override
    public MultipartFile getFile(String contentPath) {
        return null;
    }
}
