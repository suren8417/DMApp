package com.tc.dm.core.services.impl;

import com.tc.dm.core.services.FileService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

@Transactional
@Service
public class FileServiceImpl implements FileService {

    @Value("${filestore.path}")
    private String filestorePath;

    public String getFilestorePath() {
        return filestorePath;
    }

    @Override
    public String storeFile(MultipartFile file) throws Exception {
        if (null != file && !file.isEmpty()) {
            try {
                final String fileUniqueId = UUID.randomUUID().toString()+"_"+String.valueOf(System.currentTimeMillis())+"_";
                final String filePath = getFilestorePath()+File.separator+fileUniqueId+file.getOriginalFilename();
                file.transferTo(new File(filePath));
                return filePath;

//                final String fileName = file.getOriginalFilename();
//                final String saveLocation= getFilestorePath()+File.separator+fileName;
//                byte[] bytes = file.getBytes();
//                BufferedOutputStream buffStream = new BufferedOutputStream(new FileOutputStream(new File(saveLocation)));
//                buffStream.write(bytes);
//                buffStream.close();
//                return saveLocation;
            } catch (Exception e) {
                throw new Exception("File creation failed", e);
            }
        } else throw new Exception("File is NullOrEmpty");

    }

    @Override
    public boolean deleteFile(String contentPath) throws Exception {
        try {
            return Files.deleteIfExists(Paths.get(contentPath));
        } catch (Exception e) {
            throw new Exception("File deletion failed for:"+contentPath, e);
        }
    }

    @Override
    public MultipartFile getFile(String contentPath) {
        return null;
    }
}
