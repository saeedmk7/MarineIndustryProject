package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the MatchingRunningStep entity. This class is used in MatchingRunningStepResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /matching-running-steps?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MatchingRunningStepCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter description;

    private IntegerFilter stepNumber;

    private BooleanFilter stepRequired;

    private BooleanFilter fileDocRequired;

    private StringFilter colorText;

    private BooleanFilter isHeader;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter matchingRunRunningStepId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getTitle() {
        return title;
    }

    public void setTitle(StringFilter title) {
        this.title = title;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public IntegerFilter getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(IntegerFilter stepNumber) {
        this.stepNumber = stepNumber;
    }

    public BooleanFilter getStepRequired() {
        return stepRequired;
    }

    public void setStepRequired(BooleanFilter stepRequired) {
        this.stepRequired = stepRequired;
    }

    public BooleanFilter getFileDocRequired() {
        return fileDocRequired;
    }

    public void setFileDocRequired(BooleanFilter fileDocRequired) {
        this.fileDocRequired = fileDocRequired;
    }

    public StringFilter getColorText() {
        return colorText;
    }

    public void setColorText(StringFilter colorText) {
        this.colorText = colorText;
    }

    public BooleanFilter getIsHeader() {
        return isHeader;
    }

    public void setIsHeader(BooleanFilter isHeader) {
        this.isHeader = isHeader;
    }

    public StringFilter getCreateUserLogin() {
        return createUserLogin;
    }

    public void setCreateUserLogin(StringFilter createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTimeFilter getCreateDate() {
        return createDate;
    }

    public void setCreateDate(ZonedDateTimeFilter createDate) {
        this.createDate = createDate;
    }

    public StringFilter getModifyUserLogin() {
        return modifyUserLogin;
    }

    public void setModifyUserLogin(StringFilter modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTimeFilter getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(ZonedDateTimeFilter modifyDate) {
        this.modifyDate = modifyDate;
    }

    public BooleanFilter getArchived() {
        return archived;
    }

    public void setArchived(BooleanFilter archived) {
        this.archived = archived;
    }

    public StringFilter getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public void setArchivedUserLogin(StringFilter archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTimeFilter getArchivedDate() {
        return archivedDate;
    }

    public void setArchivedDate(ZonedDateTimeFilter archivedDate) {
        this.archivedDate = archivedDate;
    }

    public IntegerFilter getStatus() {
        return status;
    }

    public void setStatus(IntegerFilter status) {
        this.status = status;
    }

    public LongFilter getMatchingRunRunningStepId() {
        return matchingRunRunningStepId;
    }

    public void setMatchingRunRunningStepId(LongFilter matchingRunRunningStepId) {
        this.matchingRunRunningStepId = matchingRunRunningStepId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MatchingRunningStepCriteria that = (MatchingRunningStepCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(stepNumber, that.stepNumber) &&
            Objects.equals(stepRequired, that.stepRequired) &&
            Objects.equals(fileDocRequired, that.fileDocRequired) &&
            Objects.equals(colorText, that.colorText) &&
            Objects.equals(isHeader, that.isHeader) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(matchingRunRunningStepId, that.matchingRunRunningStepId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        description,
        stepNumber,
        stepRequired,
        fileDocRequired,
        colorText,
        isHeader,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        matchingRunRunningStepId
        );
    }

    @Override
    public String toString() {
        return "MatchingRunningStepCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (stepNumber != null ? "stepNumber=" + stepNumber + ", " : "") +
                (stepRequired != null ? "stepRequired=" + stepRequired + ", " : "") +
                (fileDocRequired != null ? "fileDocRequired=" + fileDocRequired + ", " : "") +
                (colorText != null ? "colorText=" + colorText + ", " : "") +
                (isHeader != null ? "isHeader=" + isHeader + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (matchingRunRunningStepId != null ? "matchingRunRunningStepId=" + matchingRunRunningStepId + ", " : "") +
            "}";
    }

}
