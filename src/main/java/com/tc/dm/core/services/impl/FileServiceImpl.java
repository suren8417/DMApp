package com.tc.dm.core.services.impl;

import com.tc.dm.core.services.FileService;
import jcifs.smb.NtlmPasswordAuthentication;
import jcifs.smb.SmbFile;
import jcifs.smb.SmbFileOutputStream;
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
    @Value("${filestore.is.nas}")
    private String filestoreIsNas;
    @Value("${nas.domain}")
    private String nasDomain;
    @Value("${nas.user}")
    private String nasUserName;
    @Value("${nas.password}")
    private String nasPassword;

    public String getFilestorePath() {
        return filestorePath;
    }

    public String getFilestoreIsNas() {
        return filestoreIsNas;
    }

    public String getNasDomain() {
        return nasDomain;
    }

    public String getNasUserName() {
        return nasUserName;
    }

    public String getNasPassword() {
        return nasPassword;
    }

    @Override
    public String storeFile(MultipartFile file) throws Exception {
        if (null != file && !file.isEmpty()) {
            try {
                String filePath = null;
                final String fileUniqueId = UUID.randomUUID().toString() + "_" + String.valueOf(System.currentTimeMillis()) + "_";
                if("true".equalsIgnoreCase(getFilestoreIsNas())) {
                    final NtlmPasswordAuthentication ntlmAuth = getAuthenticationToekn();
                    filePath = "smb:" + getFilestorePath().replace("\\", "/") + "/" + fileUniqueId + file.getOriginalFilename();
                    final SmbFileOutputStream sfos = new SmbFileOutputStream(new SmbFile(filePath, ntlmAuth));
                    sfos.write(file.getBytes());
                    sfos.flush();
                    sfos.close();
                } else {
                    filePath = getFilestorePath() + File.separator + fileUniqueId + file.getOriginalFilename();
                    file.transferTo(new File(filePath));
                }
                return filePath;
            } catch (Exception e) {
                throw new Exception("File creation failed", e);
            }
        } else throw new Exception("File is NullOrEmpty");

    }

    @Override
    public boolean deleteFile(String contentPath) throws Exception {
        try {
            if("true".equalsIgnoreCase(getFilestoreIsNas())) {
                final NtlmPasswordAuthentication ntlmAuth = getAuthenticationToekn();
                final SmbFile file = new SmbFile(contentPath, ntlmAuth);
                file.delete();
                return true;
            } else {
                return Files.deleteIfExists(Paths.get(contentPath));
            }
        } catch (Exception e) {
            throw new Exception("File deletion failed for:"+contentPath, e);
        }
    }

    @Override
    public MultipartFile getFile(String contentPath) {
        return null;
    }

    private NtlmPasswordAuthentication getAuthenticationToekn() {
        return new NtlmPasswordAuthentication(getNasDomain() == null ? "" : getNasDomain(), getNasUserName(), getNasPassword());
    }
}
