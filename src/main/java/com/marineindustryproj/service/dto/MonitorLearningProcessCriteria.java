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
 * Criteria class for the MonitorLearningProcess entity. This class is used in MonitorLearningProcessResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /monitor-learning-processes?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class MonitorLearningProcessCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter code;

    private StringFilter processDate;

    private StringFilter classification;

    private StringFilter description;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private LongFilter monitorLearningProcessGradeId;

    private LongFilter documentId;

    private LongFilter monitorProcessDurationId;

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

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getProcessDate() {
        return processDate;
    }

    public void setProcessDate(StringFilter processDate) {
        this.processDate = processDate;
    }

    public StringFilter getClassification() {
        return classification;
    }

    public void setClassification(StringFilter classification) {
        this.classification = classification;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
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

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public LongFilter getMonitorLearningProcessGradeId() {
        return monitorLearningProcessGradeId;
    }

    public void setMonitorLearningProcessGradeId(LongFilter monitorLearningProcessGradeId) {
        this.monitorLearningProcessGradeId = monitorLearningProcessGradeId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getMonitorProcessDurationId() {
        return monitorProcessDurationId;
    }

    public void setMonitorProcessDurationId(LongFilter monitorProcessDurationId) {
        this.monitorProcessDurationId = monitorProcessDurationId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final MonitorLearningProcessCriteria that = (MonitorLearningProcessCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(code, that.code) &&
            Objects.equals(processDate, that.processDate) &&
            Objects.equals(classification, that.classification) &&
            Objects.equals(description, that.description) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(monitorLearningProcessGradeId, that.monitorLearningProcessGradeId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(monitorProcessDurationId, that.monitorProcessDurationId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        code,
        processDate,
        classification,
        description,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        monitorLearningProcessGradeId,
        documentId,
        monitorProcessDurationId
        );
    }

    @Override
    public String toString() {
        return "MonitorLearningProcessCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (processDate != null ? "processDate=" + processDate + ", " : "") +
                (classification != null ? "classification=" + classification + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (monitorLearningProcessGradeId != null ? "monitorLearningProcessGradeId=" + monitorLearningProcessGradeId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (monitorProcessDurationId != null ? "monitorProcessDurationId=" + monitorProcessDurationId + ", " : "") +
            "}";
    }

}
