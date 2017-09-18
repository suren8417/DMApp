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
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.UUID;
import static com.tc.dm.core.util.CommonUtil.*;

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

    @Value("${file.cache.path}")
    private String fileCachePath;

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

    public String getFileCachePath() {
        return fileCachePath;
    }

    @Override
    public String storeFile(MultipartFile file) throws Exception {
        if (null != file && !file.isEmpty()) {
            try {
                String filePath = null;
                final String fileUniqueName = UUID.randomUUID().toString() + "." + extractFileExt(file.getOriginalFilename());
                if("true".equalsIgnoreCase(getFilestoreIsNas())) {
                    final NtlmPasswordAuthentication ntlmAuth = getAuthenticationToekn();
                    filePath = "smb:" + getFilestorePath().replace("\\", "/") + "/" + fileUniqueName;
                    final SmbFileOutputStream sfos = new SmbFileOutputStream(new SmbFile(filePath, ntlmAuth));
                    sfos.write(file.getBytes());
                    sfos.flush();
                    sfos.close();
                } else {
                    File dir = new File(getFilestorePath());
                    if (!dir.isDirectory()) dir.mkdirs();
                    filePath = getFilestorePath() + File.separator + fileUniqueName;
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
    public MultipartFile getFile(String contentPath) throws Exception {
        throw new Exception("Unimplemented method");
    }

    @Override
    public boolean copyToCache(String contentPath) throws Exception {
        try {
            String targetFilePath = getFileCachePath()+File.separator+extractFileName(contentPath);
            if("true".equalsIgnoreCase(getFilestoreIsNas())) {
                final NtlmPasswordAuthentication ntlmAuth = getAuthenticationToekn();
                final SmbFile source = new SmbFile(contentPath, ntlmAuth);
                SmbFile target = new SmbFile(targetFilePath);
                target.createNewFile();
                source.copyTo(target);
                return true;
            } else {
                Path source = Paths.get(contentPath);
                Path target = Paths.get(targetFilePath);
                CopyOption[] options = new CopyOption[]{
                        StandardCopyOption.REPLACE_EXISTING,
                        StandardCopyOption.COPY_ATTRIBUTES
                };
                Path copiedPath = java.nio.file.Files.copy(source, target, options);
                return true;
            }
        } catch (Exception e) {
            throw new Exception("File copying to cache failed for:"+contentPath, e);
        }
    }

    @Override
    public boolean clearCache() throws Exception {
        try {
            Path rootPath = Paths.get(getFileCachePath());
            Files.walkFileTree(rootPath, new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                    Files.delete(file);
                    return FileVisitResult.CONTINUE;
                }

                @Override
                public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
//                    Files.delete(dir);
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch(Exception e){
            throw new Exception("File cache cleaning failed", e);
        }
        return true;
    }

    private NtlmPasswordAuthentication getAuthenticationToekn() {
        return new NtlmPasswordAuthentication(getNasDomain() == null ? "" : getNasDomain(), getNasUserName(), getNasPassword());
    }
}
