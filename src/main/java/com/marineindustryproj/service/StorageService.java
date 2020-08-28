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
    private final Path soldierTrainingReportRootLocation = Paths.get("soldier-training-report-upload-dir");
    private final Path soldierMediaAwarenessReportRootLocation = Paths.get("soldier-media-awareness-report-upload-dir");
    private final Path evaluateCriteriaDataRootLocation = Paths.get("evaluate-criteria-data-upload-dir");
    private final Path educationRootLocation = Paths.get("education-upload-dir");
    private final Path jobRootLocation = Paths.get("job-upload-dir");
    private final Path imagesRootLocation = Paths.get("images-dir");
    private final Path runPhaseRootLocation = Paths.get("run-phase-dir");
    private final Path jamHelpRootLocation = Paths.get("jam-help-dir");
    private final Path fileStorageLocation;
    private final Path historyFileStorageLocation;
    private final Path soldierTrainingReportStorageLocation;
    private final Path soldierMediaAwarenessReportStorageLocation;
    private final Path evaluateCriteriaDataStorageLocation;
    private final Path educationFileStorageLocation;
    private final Path jobFileStorageLocation;
    private final Path imageFileStorageLocation;
    private final Path runPhaseStorageLocation;
    private final Path jamHelpStorageLocation;

    @Autowired
    public StorageService(FileStorageProperties fileStorageProperties){
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
            .toAbsolutePath().normalize();
        this.imageFileStorageLocation = Paths.get(fileStorageProperties.getImageUploadDir())
            .toAbsolutePath().normalize();
        this.historyFileStorageLocation = Paths.get(fileStorageProperties.getHistoryUploadDir())
            .toAbsolutePath().normalize();
        this.soldierTrainingReportStorageLocation = Paths.get(fileStorageProperties.getSoldierTrainingReportUploadDir())
            .toAbsolutePath().normalize();
        this.soldierMediaAwarenessReportStorageLocation = Paths.get(fileStorageProperties.getSoldierMediaAwarenessReportUploadDir())
            .toAbsolutePath().normalize();
        this.evaluateCriteriaDataStorageLocation = Paths.get(fileStorageProperties.getEvaluateCriteriaDataUploadDir())
            .toAbsolutePath().normalize();
        this.educationFileStorageLocation = Paths.get(fileStorageProperties.getEducationUploadDir())
            .toAbsolutePath().normalize();
        this.jobFileStorageLocation = Paths.get(fileStorageProperties.getJobUploadDir())
            .toAbsolutePath().normalize();
        this.runPhaseStorageLocation = Paths.get(fileStorageProperties.getRunPhaseUploadDir())
            .toAbsolutePath().normalize();
        this.jamHelpStorageLocation = Paths.get(fileStorageProperties.getJamHelpUploadDir())
            .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
            Files.createDirectories(this.imageFileStorageLocation);
            Files.createDirectories(this.historyFileStorageLocation);
            Files.createDirectories(this.soldierTrainingReportStorageLocation);
            Files.createDirectories(this.soldierMediaAwarenessReportStorageLocation);
            Files.createDirectories(this.evaluateCriteriaDataStorageLocation);
            Files.createDirectories(this.educationFileStorageLocation);
            Files.createDirectories(this.jobFileStorageLocation);
            Files.createDirectories(this.runPhaseStorageLocation);
            Files.createDirectories(this.jamHelpStorageLocation);
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
            Files.createDirectory(runPhaseRootLocation);
            Files.createDirectory(jamHelpRootLocation);
            Files.createDirectory(historyRootLocation);
            Files.createDirectory(soldierTrainingReportRootLocation);
            Files.createDirectory(soldierMediaAwarenessReportRootLocation);
            Files.createDirectory(evaluateCriteriaDataRootLocation);
            Files.createDirectory(educationRootLocation);
            Files.createDirectory(jobRootLocation);
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


    public String storeEducationFile(MultipartFile file) {
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
            Path targetLocation = this.educationFileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadEducationFileAsResource(String fileName) {
        try {
            Path filePath = this.educationFileStorageLocation.resolve(fileName).normalize();
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

    public void deleteEducationFile(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.educationFileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

        }
    }
    public String storeJobFile(MultipartFile file) {
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
            Path targetLocation = this.jobFileStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadJobFileAsResource(String fileName) {
        try {
            Path filePath = this.jobFileStorageLocation.resolve(fileName).normalize();
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
    public void deleteJobFile(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.jobFileStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

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
    public String storeSoldierTrainingReport(MultipartFile file) {
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
            Path targetLocation = this.soldierTrainingReportStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadSoldierTrainingReportAsResource(String fileName) {
        try {
            Path filePath = this.soldierTrainingReportStorageLocation.resolve(fileName).normalize();
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
    public void deleteSoldierTrainingReport(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.soldierTrainingReportStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

        }
    }
    public String storeSoldierMediaAwarenessReport(MultipartFile file) {
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
            Path targetLocation = this.soldierMediaAwarenessReportStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadSoldierMediaAwarenessReportAsResource(String fileName) {
        try {
            Path filePath = this.soldierMediaAwarenessReportStorageLocation.resolve(fileName).normalize();
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
    public void deleteSoldierMediaAwarenessReport(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.soldierMediaAwarenessReportStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

        }
    }
    public String storeEvaluateCriteriaData(MultipartFile file) {
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
            Path targetLocation = this.evaluateCriteriaDataStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadEvaluateCriteriaDataAsResource(String fileName) {
        try {
            Path filePath = this.evaluateCriteriaDataStorageLocation.resolve(fileName).normalize();
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
    public void deleteEvaluateCriteriaData(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.evaluateCriteriaDataStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

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
    

    public String storeRunPhaseFile(MultipartFile file) {
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
            Path targetLocation = this.runPhaseStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadRunPhaseFileAsResource(String fileName) {
        try {
            Path filePath = this.runPhaseStorageLocation.resolve(fileName).normalize();
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
    public void deleteRunPhaseFile(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.runPhaseStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

        }
    }

    public String storeJamHelpFile(MultipartFile file) {
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
            Path targetLocation = this.jamHelpStorageLocation.resolve(fileName);
            Files.copy(file.getInputStream(), targetLocation, StandardCopyOption.REPLACE_EXISTING);

            return fileName;
        } catch (IOException ex) {
            throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
        }
    }
    public Resource loadJamHelpFileAsResource(String fileName) {
        try {
            Path filePath = this.jamHelpStorageLocation.resolve(fileName).normalize();
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
    public void deleteJamHelpFile(String fileName) {
        try {
            String[] fileNameSplit = fileName.split("/");
            fileName = fileNameSplit[fileNameSplit.length - 1];
            Path filePath = this.jamHelpStorageLocation.resolve(fileName).normalize();
            Resource resource = new UrlResource(filePath.toUri());
            if(resource.exists()) {
                Files.delete(filePath);
            } else {

            }
        } catch (IOException ex) {

        }
    }


}
