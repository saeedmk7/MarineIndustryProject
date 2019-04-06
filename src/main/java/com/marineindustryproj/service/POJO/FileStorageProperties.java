package com.marineindustryproj.service.POJO;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    private String imageUploadDir;

    public String getImageUploadDir() {
        return imageUploadDir;
    }

    public void setImageUploadDir(String imageUploadDir) {
        this.imageUploadDir = imageUploadDir;
    }

    private String historyUploadDir;

    public String getHistoryUploadDir() {
        return historyUploadDir;
    }

    public void setHistoryUploadDir(String historyUploadDir) {
        this.historyUploadDir = historyUploadDir;
    }
}
