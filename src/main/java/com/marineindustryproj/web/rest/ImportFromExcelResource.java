package com.marineindustryproj.web.rest;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.service.StorageService;
import com.marineindustryproj.service.dto.ReturnImageUrlDTO;
import com.marineindustryproj.web.rest.errors.BadRequestAlertException;
import com.marineindustryproj.web.rest.util.HeaderUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class ImportFromExcelResource {

    private static final Logger logger = LoggerFactory.getLogger(ImportFromExcelResource.class);

    @Autowired
    private StorageService storageService;

    @PostMapping("/import-excel")
    @Timed
    public ResponseEntity<String> handleFileUpload(@Valid @RequestBody @RequestParam("file") MultipartFile file,@RequestParam("entityName") String entityName) {

        StringBuilder sb = new StringBuilder();
        try {
             sb = storageService.store(file,entityName);

             if(sb.toString().isEmpty()) {
                 sb.append("اطلاعات فایل شما با موفقیت ثبت و درج شد.");
                 return ResponseEntity.status(HttpStatus.OK).body(sb.toString());
             }
             else {
                 sb.append("بقیه اطلاعات فایل شما با موفقیت ثبت و درج شد.\r\n");
                 return ResponseEntity.status(HttpStatus.OK).body(sb.toString());
             }
        } catch (Exception e) {
            sb.append("آپلود و درج اطلاعات شما با مشکل روبرو شده است.");
            sb.append(e.getMessage());
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(sb.toString());
        }
    }
    @PostMapping("/saveUserImage")
    @Timed
    public ResponseEntity<ReturnImageUrlDTO> saveUserImage(@Valid @RequestBody @RequestParam("file") MultipartFile file,@RequestParam("login") String login) {

        /*StringBuilder sb = new StringBuilder();*/
        try {
            String fileName = storageService.storeImageFile(file, login);

            String fileDownloadUri = "api/downloadImageFile/" + fileName;

            //sb = storageService.storeUserImageToResource(file);
            /*sb.append(fileDownloadUri);*/

            String result = fileDownloadUri; //sb.toString();
            ReturnImageUrlDTO  returnImageUrlDTO = new ReturnImageUrlDTO();
            returnImageUrlDTO.setUrl(result);
            returnImageUrlDTO.setOk(true);
            //return ResponseEntity.status(HttpStatus.OK).body(sb.toString());
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Image","User"))
                .body(returnImageUrlDTO);
        } catch (Exception e) {
            /*sb.append("آپلود و درج اطلاعات شما با مشکل روبرو شده است.");
            sb.append(e.getMessage());*/
            //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(sb.toString());
            //throw new BadRequestAlertException("Failure","Image","Bad");
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Image","key1","saeed")).body(null);
        }
    }
    /*@PostMapping("/saveFile")
    @Timed
    public ResponseEntity<ReturnImageUrlDTO> saveFile(@Valid @RequestBody @RequestParam("file") MultipartFile file) {

        *//*StringBuilder sb = new StringBuilder();*//*
        try {
            String fileName = storageService.storeFile(file);

            String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("api/downloadFile/")
                .path(fileName)
                .toUriString();

            //sb = storageService.storeUserImageToResource(file);
            *//*sb.append(fileDownloadUri);*//*

            String result = fileDownloadUri; //sb.toString();
            ReturnImageUrlDTO  returnImageUrlDTO = new ReturnImageUrlDTO();
            returnImageUrlDTO.setUrl(result);
            returnImageUrlDTO.setOk(true);
            //return ResponseEntity.status(HttpStatus.OK).body(sb.toString());
            return ResponseEntity.ok()
                .headers(HeaderUtil.createEntityCreationAlert("Image","User"))
                .body(returnImageUrlDTO);
        } catch (Exception e) {
            *//*sb.append("آپلود و درج اطلاعات شما با مشکل روبرو شده است.");
            sb.append(e.getMessage());*//*
            //return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(sb.toString());
            //throw new BadRequestAlertException("Failure","Image","Bad");
            return ResponseEntity.badRequest().headers(HeaderUtil.createFailureAlert("Image","key1","saeed")).body(null);
        }
    }*/
    @GetMapping("/downloadImageFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadImageFile(@PathVariable String fileName) {
        // Load file as Resource
        Resource resource = storageService.loadImageAsResource(fileName);

        // Try to determine file's content type
        /*String contentType = null;
        try {

            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }*/
        String contentType = null;
        String[] ss = fileName.split("\\.");
        if(ss[ss.length - 1] == "jpg" || ss[ss.length - 1] == "jpeg"){
            contentType = "image/jpeg";
        }
        else{
            contentType = "image/png";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    @GetMapping("/downloadFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = storageService.loadFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }

    @GetMapping("/downloadHistoryFile/{fileName:.+}")
    public ResponseEntity<Resource> downloadHistoryFile(@PathVariable String fileName, HttpServletRequest request) {
        // Load file as Resource
        Resource resource = storageService.loadHistoryFileAsResource(fileName);

        // Try to determine file's content type
        String contentType = null;
        try {
            contentType = request.getServletContext().getMimeType(resource.getFile().getAbsolutePath());
        } catch (IOException ex) {
            logger.info("Could not determine file type.");
        }

        // Fallback to the default content type if type could not be determined
        if(contentType == null) {
            contentType = "application/octet-stream";
        }

        return ResponseEntity.ok()
            .contentType(MediaType.parseMediaType(contentType))
            .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"")
            .body(resource);
    }
}
