package com.meikelai.service;

import org.springframework.web.multipart.MultipartFile;

public interface IFileStorageService {

    public String storeFile(MultipartFile file);

}
