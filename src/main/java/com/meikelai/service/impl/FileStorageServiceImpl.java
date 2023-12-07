package com.meikelai.service.impl;

import com.meikelai.service.IFileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
@Service
public class FileStorageServiceImpl implements IFileStorageService {
    private final Path fileStorageLocation; // 文件存储位置

    public FileStorageServiceImpl(@Value("${file.storage.location}") String fileStorageLocationPath) {
        this.fileStorageLocation = Paths.get(fileStorageLocationPath).toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (Exception ex) {
            throw new RuntimeException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    // 存储文件并返回文件名
    public String storeFile(MultipartFile file) {
        String fileName = null;
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("Cannot store empty file.");
            }

            // 构建目标文件路径
            fileName = file.getOriginalFilename();
            Path targetLocation = this.fileStorageLocation.resolve(fileName);

            // 将文件复制到目标位置
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return fileName; // 或者构建并返回文件的访问URL
    }
}
