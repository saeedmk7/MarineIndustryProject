package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.persistence.Lob;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the Document entity.
 */
public class ReportDTO implements Serializable {

    
    @Lob
    private byte[] fileDoc;
    private String fileDocContentType;

    public byte[] getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(byte[] fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getFileDocContentType() {
        return fileDocContentType;
    }

    public void setFileDocContentType(String fileDocContentType) {
        this.fileDocContentType = fileDocContentType;

    }
}
