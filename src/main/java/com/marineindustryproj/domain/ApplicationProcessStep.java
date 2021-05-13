package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A ApplicationProcessStep.
 */
@Entity
@Table(name = "application_process_step")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationProcessStep implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "title", length = 4096)
    private String title;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @NotNull
    @Column(name = "step_number", nullable = false)
    private Integer stepNumber;

    @NotNull
    @Column(name = "step_required", nullable = false)
    private Boolean stepRequired;

    @NotNull
    @Column(name = "file_doc_required", nullable = false)
    private Boolean fileDocRequired;

    @Column(name = "color_text")
    private String colorText;

    @NotNull
    @Column(name = "is_header", nullable = false)
    private Boolean isHeader;

    @Size(max = 50)
    @Column(name = "create_user_login", length = 50)
    private String createUserLogin;

    @Column(name = "create_date")
    private ZonedDateTime createDate;

    @Size(max = 50)
    @Column(name = "modify_user_login", length = 50)
    private String modifyUserLogin;

    @Column(name = "modify_date")
    private ZonedDateTime modifyDate;

    @NotNull
    @Column(name = "archived", nullable = false)
    private Boolean archived;

    @Size(max = 50)
    @Column(name = "archived_user_login", length = 50)
    private String archivedUserLogin;

    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    @Column(name = "status")
    private Integer status;

    @Lob
    @Column(name = "file_doc")
    private String fileDoc;

    @OneToMany(mappedBy = "applicationProcessStep")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApplicationProcessRunStep> applicationProcessRunSteps = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public ApplicationProcessStep title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ApplicationProcessStep description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public ApplicationProcessStep stepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
        return this;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Boolean isStepRequired() {
        return stepRequired;
    }

    public ApplicationProcessStep stepRequired(Boolean stepRequired) {
        this.stepRequired = stepRequired;
        return this;
    }

    public void setStepRequired(Boolean stepRequired) {
        this.stepRequired = stepRequired;
    }

    public Boolean isFileDocRequired() {
        return fileDocRequired;
    }

    public ApplicationProcessStep fileDocRequired(Boolean fileDocRequired) {
        this.fileDocRequired = fileDocRequired;
        return this;
    }

    public void setFileDocRequired(Boolean fileDocRequired) {
        this.fileDocRequired = fileDocRequired;
    }

    public String getColorText() {
        return colorText;
    }

    public ApplicationProcessStep colorText(String colorText) {
        this.colorText = colorText;
        return this;
    }

    public void setColorText(String colorText) {
        this.colorText = colorText;
    }

    public Boolean isIsHeader() {
        return isHeader;
    }

    public ApplicationProcessStep isHeader(Boolean isHeader) {
        this.isHeader = isHeader;
        return this;
    }

    public void setIsHeader(Boolean isHeader) {
        this.isHeader = isHeader;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public ApplicationProcessStep createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ApplicationProcessStep createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public ApplicationProcessStep modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public ApplicationProcessStep modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public ApplicationProcessStep archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public ApplicationProcessStep archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public ApplicationProcessStep archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public ApplicationProcessStep status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public ApplicationProcessStep fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public Set<ApplicationProcessRunStep> getApplicationProcessRunSteps() {
        return applicationProcessRunSteps;
    }

    public ApplicationProcessStep applicationProcessRunSteps(Set<ApplicationProcessRunStep> applicationProcessRunSteps) {
        this.applicationProcessRunSteps = applicationProcessRunSteps;
        return this;
    }

    public ApplicationProcessStep addApplicationProcessRunStep(ApplicationProcessRunStep applicationProcessRunStep) {
        this.applicationProcessRunSteps.add(applicationProcessRunStep);
        applicationProcessRunStep.setApplicationProcessStep(this);
        return this;
    }

    public ApplicationProcessStep removeApplicationProcessRunStep(ApplicationProcessRunStep applicationProcessRunStep) {
        this.applicationProcessRunSteps.remove(applicationProcessRunStep);
        applicationProcessRunStep.setApplicationProcessStep(null);
        return this;
    }

    public void setApplicationProcessRunSteps(Set<ApplicationProcessRunStep> applicationProcessRunSteps) {
        this.applicationProcessRunSteps = applicationProcessRunSteps;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ApplicationProcessStep applicationProcessStep = (ApplicationProcessStep) o;
        if (applicationProcessStep.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicationProcessStep.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApplicationProcessStep{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", stepNumber=" + getStepNumber() +
            ", stepRequired='" + isStepRequired() + "'" +
            ", fileDocRequired='" + isFileDocRequired() + "'" +
            ", colorText='" + getColorText() + "'" +
            ", isHeader='" + isIsHeader() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", fileDoc='" + getFileDoc() + "'" +
            "}";
    }
}
