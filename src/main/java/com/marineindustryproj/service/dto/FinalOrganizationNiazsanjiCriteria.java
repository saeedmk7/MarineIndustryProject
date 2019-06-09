package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.RequestStatus;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the FinalOrganizationNiazsanji entity. This class is used in FinalOrganizationNiazsanjiResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /final-organization-niazsanjis?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class FinalOrganizationNiazsanjiCriteria implements Serializable {

    /**
     * Class for filtering RequestStatus
     */
    public static class RequestStatusFilter extends Filter<RequestStatus> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private IntegerFilter niazsanjiYear;

    private StringFilter code;

    private StringFilter recommendedByOrgchart;

    private StringFilter neededSoftwares;

    private StringFilter neededHardware;

    private StringFilter studentsType;

    private StringFilter teacherName;

    private StringFilter teacherMobile;

    private RequestStatusFilter requestStatus;

    private StringFilter changeStatusUserLogin;

    private StringFilter trainingGoals;

    private StringFilter description;

    private IntegerFilter priceCost;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private StringFilter guid;

    private LongFilter personId;

    private LongFilter documentId;

    private LongFilter courseTypeId;

    private LongFilter organizationChartId;

    private LongFilter teacherId;

    private LongFilter educationalModuleId;

    private StringFilter educationalModuleTitle;

    private LongFilter teachApproachId;

    private LongFilter requestOrganizationNiazsanjiId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public IntegerFilter getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public void setNiazsanjiYear(IntegerFilter niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public StringFilter getCode() {
        return code;
    }

    public void setCode(StringFilter code) {
        this.code = code;
    }

    public StringFilter getRecommendedByOrgchart() {
        return recommendedByOrgchart;
    }

    public void setRecommendedByOrgchart(StringFilter recommendedByOrgchart) {
        this.recommendedByOrgchart = recommendedByOrgchart;
    }

    public StringFilter getNeededSoftwares() {
        return neededSoftwares;
    }

    public void setNeededSoftwares(StringFilter neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
    }

    public StringFilter getNeededHardware() {
        return neededHardware;
    }

    public void setNeededHardware(StringFilter neededHardware) {
        this.neededHardware = neededHardware;
    }

    public StringFilter getStudentsType() {
        return studentsType;
    }

    public void setStudentsType(StringFilter studentsType) {
        this.studentsType = studentsType;
    }

    public StringFilter getTeacherName() {
        return teacherName;
    }

    public void setTeacherName(StringFilter teacherName) {
        this.teacherName = teacherName;
    }

    public StringFilter getTeacherMobile() {
        return teacherMobile;
    }

    public void setTeacherMobile(StringFilter teacherMobile) {
        this.teacherMobile = teacherMobile;
    }

    public RequestStatusFilter getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatusFilter requestStatus) {
        this.requestStatus = requestStatus;
    }

    public StringFilter getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public void setChangeStatusUserLogin(StringFilter changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public StringFilter getTrainingGoals() {
        return trainingGoals;
    }

    public void setTrainingGoals(StringFilter trainingGoals) {
        this.trainingGoals = trainingGoals;
    }

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public IntegerFilter getPriceCost() {
        return priceCost;
    }

    public void setPriceCost(IntegerFilter priceCost) {
        this.priceCost = priceCost;
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

    public StringFilter getGuid() {
        return guid;
    }

    public void setGuid(StringFilter guid) {
        this.guid = guid;
    }

    public LongFilter getPersonId() {
        return personId;
    }

    public void setPersonId(LongFilter personId) {
        this.personId = personId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(LongFilter courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public LongFilter getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(LongFilter teacherId) {
        this.teacherId = teacherId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }
    public StringFilter getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(StringFilter educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public LongFilter getTeachApproachId() {
        return teachApproachId;
    }

    public void setTeachApproachId(LongFilter teachApproachId) {
        this.teachApproachId = teachApproachId;
    }

    public LongFilter getRequestOrganizationNiazsanjiId() {
        return requestOrganizationNiazsanjiId;
    }

    public void setRequestOrganizationNiazsanjiId(LongFilter requestOrganizationNiazsanjiId) {
        this.requestOrganizationNiazsanjiId = requestOrganizationNiazsanjiId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final FinalOrganizationNiazsanjiCriteria that = (FinalOrganizationNiazsanjiCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(niazsanjiYear, that.niazsanjiYear) &&
            Objects.equals(code, that.code) &&
            Objects.equals(recommendedByOrgchart, that.recommendedByOrgchart) &&
            Objects.equals(neededSoftwares, that.neededSoftwares) &&
            Objects.equals(neededHardware, that.neededHardware) &&
            Objects.equals(studentsType, that.studentsType) &&
            Objects.equals(teacherName, that.teacherName) &&
            Objects.equals(teacherMobile, that.teacherMobile) &&
            Objects.equals(requestStatus, that.requestStatus) &&
            Objects.equals(changeStatusUserLogin, that.changeStatusUserLogin) &&
            Objects.equals(trainingGoals, that.trainingGoals) &&
            Objects.equals(description, that.description) &&
            Objects.equals(priceCost, that.priceCost) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(personId, that.personId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(courseTypeId, that.courseTypeId) &&
            Objects.equals(organizationChartId, that.organizationChartId) &&
            Objects.equals(teacherId, that.teacherId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(educationalModuleTitle, that.educationalModuleTitle) &&
            Objects.equals(teachApproachId, that.teachApproachId) &&
            Objects.equals(requestOrganizationNiazsanjiId, that.requestOrganizationNiazsanjiId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        niazsanjiYear,
        code,
        recommendedByOrgchart,
        neededSoftwares,
        neededHardware,
        studentsType,
        teacherName,
        teacherMobile,
        requestStatus,
        changeStatusUserLogin,
        trainingGoals,
        description,
        priceCost,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        guid,
        personId,
        documentId,
        courseTypeId,
        organizationChartId,
        teacherId,
        educationalModuleId,
        educationalModuleTitle,
        teachApproachId,
        requestOrganizationNiazsanjiId
        );
    }

    @Override
    public String toString() {
        return "FinalOrganizationNiazsanjiCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (niazsanjiYear != null ? "niazsanjiYear=" + niazsanjiYear + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (recommendedByOrgchart != null ? "recommendedByOrgchart=" + recommendedByOrgchart + ", " : "") +
                (neededSoftwares != null ? "neededSoftwares=" + neededSoftwares + ", " : "") +
                (neededHardware != null ? "neededHardware=" + neededHardware + ", " : "") +
                (studentsType != null ? "studentsType=" + studentsType + ", " : "") +
                (teacherName != null ? "teacherName=" + teacherName + ", " : "") +
                (teacherMobile != null ? "teacherMobile=" + teacherMobile + ", " : "") +
                (requestStatus != null ? "requestStatus=" + requestStatus + ", " : "") +
                (changeStatusUserLogin != null ? "changeStatusUserLogin=" + changeStatusUserLogin + ", " : "") +
                (trainingGoals != null ? "trainingGoals=" + trainingGoals + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (priceCost != null ? "priceCost=" + priceCost + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (personId != null ? "personId=" + personId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (courseTypeId != null ? "courseTypeId=" + courseTypeId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
                (teacherId != null ? "teacherId=" + teacherId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (educationalModuleTitle != null ? "educationalModuleTitle=" + educationalModuleTitle + ", " : "") +
                (teachApproachId != null ? "teachApproachId=" + teachApproachId + ", " : "") +
                (requestOrganizationNiazsanjiId != null ? "requestOrganizationNiazsanjiId=" + requestOrganizationNiazsanjiId + ", " : "") +
            "}";
    }

}
