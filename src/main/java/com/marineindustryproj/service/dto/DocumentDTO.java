package com.marineindustryproj.service.dto;

import java.time.ZonedDateTime;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Lob;

/**
 * A DTO for the Document entity.
 */
public class DocumentDTO implements Serializable {

    private Long id;

    @NotNull
    @Size(max = 100)
    private String title;

    
    @Lob
    private String fileDoc;
    private String fileDocContentType;

    @NotNull
    private String entityName;

    @NotNull
    private Long entityId;

    @Size(max = 50)
    private String createUserLogin;

    private ZonedDateTime createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getFileDocContentType() {
        return fileDocContentType;
    }

    public void setFileDocContentType(String fileDocContentType) {
        this.fileDocContentType = fileDocContentType;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getEntityName() {
        return entityName;
    }

    public void setEntityName(String entityName) {
        this.entityName = entityName;
    }

    public Long getEntityId() {
        return entityId;
    }

    public void setEntityId(Long entityId) {
        this.entityId = entityId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        DocumentDTO documentDTO = (DocumentDTO) o;
        if (documentDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), documentDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DocumentDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", fileDoc='" + getFileDoc() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            "}";
    }
}
