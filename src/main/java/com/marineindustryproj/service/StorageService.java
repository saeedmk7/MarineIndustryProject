package com.marineindustryproj.service;

import com.marineindustryproj.security.FileStorageException;
import com.marineindustryproj.security.MyFileNotFoundException;
import com.marineindustryproj.service.POJO.FileStorageProperties;
import com.marineindustryproj.service.parseExcel.EducationalModuleExcel;
import com.marineindustryproj.service.parseExcel.JobExcel;
import com.marineindustryproj.service.parseExcel.PersonExcel;
import com.marineindustryproj.service.parseExcel.TeacherExcel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.ZonedDateTime;
import java.util.UUID;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;

@Service
public class StorageService {

    Logger log = LoggerFactory.getLogger(this.getClass().getName());
    private final Path rootLocation = Paths.get("upload-dir");
    private final Path historyRootLocation = Paths.get("history-upload-dir");
    private final Path imagesRootLocation = Paths.get("images-dir");
    private final Path historyFileStorageLocation;
    private final Path fileStorageLocation;
    private final Path imageFileStorageLocation;

    @Autowired
    public StorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
            .toAbsolutePath().normalize();
        this.imageFileStorageLocation = Paths.get(fileStorageProperties.getImageUploadDir())
            .toAbsolutePath().normalize();
        this.historyFileStorageLocation = Paths.get(fileStorageProperties.getHistoryUploadDir())
            .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
            Files.createDirectories(this.imageFileStorageLocation);
            Files.createDirectories(this.historyFileStorageLocation);
        } catch (Exception ex) {
            throw new FileStorageException("Could not create the directory where the uploaded files will be stored.", ex);
        }
    }

    public StringBuilder store(MultipartFile file,String entityName) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            String fileName = ZonedDateTime.now().toLocalDateTime().toString().replace(".",
                                                                                       "").replace(":",
                                                                                                   "") + "-" + file.getOriginalFilename();
            Files.copy(file.getInputStream(),
                       this.rootLocation.resolve(fileName));

            switch (entityName) {
                case "job":
                    sb = new JobExcel().parseJob(fileName);
                    break;
                case "educationalModule":
                    sb = new EducationalModuleExcel().parseEducationalModule(fileName);
                    break;
                case "person":
                    sb = new PersonExcel().parsePerson(fileName);
                    break;
                case "teacher":
                    sb = new TeacherExcel().parseTeacher(fileName);
                    break;
                default:
                    break;
            }
        } catch (Exception e) {
            throw  e;
        }
        return sb;
    }

    public void init() {
        try {
            Files.createDirectory(rootLocation);
            Files.createDirectory(imagesRootLocation);
            Files.createDirectory(historyFileStorageLocation);
        } catch (IOException e) {
            throw new RuntimeException("Could not initialize storage!");
        }
    }
    public static final String UUIDGenerate(){
        UUID uuid = UUID.randomUUID();
        String stringUUID = uuid.toString().replace("-","");
        return stringUUID;
    }
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
    public static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }
    public String storeFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            String[] fileNameSplit = fileName.split("\\.");
            String extension = fileNameSplit[fileNameSplit.length - 1];
            fileName = UUIDGenerate();
            fileName += "." + extension;
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.fileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }

    public String storeImageFile(MultipartFile file,String login) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            String[] fileNameSplit = fileName.split("\\.");
            String extension = fileNameSplit[fileNameSplit.length - 1];
            fileName = login + "." + extension;
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.imageFileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public void deleteFile(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

        }
    }
    public Resource loadFileAsResource(String fileName) {
        try {
            Path filePath = this.fileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }


    public String storeHistoryFile(MultipartFile file) {
        // Normalize file name
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        try {
            // Check if the file's name contains invalid characters
            if(fileName.contains("..")) {
                throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
            }
            String[] fileNameSplit = fileName.split("\\.");
            String extension = fileNameSplit[fileNameSplit.length - 1];
            fileName = UUIDGenerate();
            fileName += "." + extension;
            // Copy file to the target location (Replacing existing file with the same name)
            Path targetLocation = this.historyFileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadHistoryFileAsResource(String fileName) {
        try {
            Path filePath = this.historyFileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    public Resource loadImageAsResource(String fileName) {
        try {
            Path filePath = this.imageFileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                return resource;
            } else {
                throw new MyFileNotFoundException("File not found " + fileName);
            }
        } catch (MalformedURLException ex) {
            throw new MyFileNotFoundException("File not found " + fileName, ex);
        }
    }
    public void deleteHistoryFile(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.historyFileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

        }
    }

}
