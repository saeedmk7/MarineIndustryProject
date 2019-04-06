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
 * Criteria class for the EducationalCenter entity. This class is used in EducationalCenterResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /educational-centers?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class EducationalCenterCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter name;

    private StringFilter ceo;

    private StringFilter connectionPerson;

    private StringFilter telephone;

    private StringFilter fax;

    private StringFilter address;

    private StringFilter email;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private BooleanFilter archived;

    private StringFilter archivedUserLogin;

    private ZonedDateTimeFilter archivedDate;

    private IntegerFilter status;

    private LongFilter activityAreaId;

    private LongFilter documentId;

    private LongFilter educationalModuleId;

    private LongFilter requestEducationalModuleId;

    public EducationalCenterCriteria() {
    }

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getName() {
        return name;
    }

    public void setName(StringFilter name) {
        this.name = name;
    }

    public StringFilter getCeo() {
        return ceo;
    }

    public void setCeo(StringFilter ceo) {
        this.ceo = ceo;
    }

    public StringFilter getConnectionPerson() {
        return connectionPerson;
    }

    public void setConnectionPerson(StringFilter connectionPerson) {
        this.connectionPerson = connectionPerson;
    }

    public StringFilter getTelephone() {
        return telephone;
    }

    public void setTelephone(StringFilter telephone) {
        this.telephone = telephone;
    }

    public StringFilter getFax() {
        return fax;
    }

    public void setFax(StringFilter fax) {
        this.fax = fax;
    }

    public StringFilter getAddress() {
        return address;
    }

    public void setAddress(StringFilter address) {
        this.address = address;
    }

    public StringFilter getEmail() {
        return email;
    }

    public void setEmail(StringFilter email) {
        this.email = email;
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

    public LongFilter getActivityAreaId() {
        return activityAreaId;
    }

    public void setActivityAreaId(LongFilter activityAreaId) {
        this.activityAreaId = activityAreaId;
    }

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }

    public LongFilter getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(LongFilter requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final EducationalCenterCriteria that = (EducationalCenterCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(name, that.name) &&
            Objects.equals(ceo, that.ceo) &&
            Objects.equals(connectionPerson, that.connectionPerson) &&
            Objects.equals(telephone, that.telephone) &&
            Objects.equals(fax, that.fax) &&
            Objects.equals(address, that.address) &&
            Objects.equals(email, that.email) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(archived, that.archived) &&
            Objects.equals(archivedUserLogin, that.archivedUserLogin) &&
            Objects.equals(archivedDate, that.archivedDate) &&
            Objects.equals(status, that.status) &&
            Objects.equals(activityAreaId, that.activityAreaId) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId) &&
            Objects.equals(requestEducationalModuleId, that.requestEducationalModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        name,
        ceo,
        connectionPerson,
        telephone,
        fax,
        address,
        email,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        archived,
        archivedUserLogin,
        archivedDate,
        status,
        activityAreaId,
        documentId,
        educationalModuleId,
        requestEducationalModuleId
        );
    }

    @Override
    public String toString() {
        return "EducationalCenterCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (name != null ? "name=" + name + ", " : "") +
                (ceo != null ? "ceo=" + ceo + ", " : "") +
                (connectionPerson != null ? "connectionPerson=" + connectionPerson + ", " : "") +
                (telephone != null ? "telephone=" + telephone + ", " : "") +
                (fax != null ? "fax=" + fax + ", " : "") +
                (address != null ? "address=" + address + ", " : "") +
                (email != null ? "email=" + email + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (archived != null ? "archived=" + archived + ", " : "") +
                (archivedUserLogin != null ? "archivedUserLogin=" + archivedUserLogin + ", " : "") +
                (archivedDate != null ? "archivedDate=" + archivedDate + ", " : "") +
                (status != null ? "status=" + status + ", " : "") +
                (activityAreaId != null ? "activityAreaId=" + activityAreaId + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
                (requestEducationalModuleId != null ? "requestEducationalModuleId=" + requestEducationalModuleId + ", " : "") +
            "}";
    }

}
