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
 * Criteria class for the InvestToGroupTransaction entity. This class is used in InvestToGroupTransactionResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /invest-to-group-transactions?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class InvestToGroupTransactionCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter description;

    private StringFilter investDate;

    private IntegerFilter investYear;

    private IntegerFilter investAmount;

    private StringFilter checkNumber;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private StringFilter guid;

    private LongFilter documentId;

    private LongFilter organizationChartId;

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

    public StringFilter getInvestDate() {
        return investDate;
    }

    public void setInvestDate(StringFilter investDate) {
        this.investDate = investDate;
    }

    public IntegerFilter getInvestYear() {
        return investYear;
    }

    public void setInvestYear(IntegerFilter investYear) {
        this.investYear = investYear;
    }

    public IntegerFilter getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(IntegerFilter investAmount) {
        this.investAmount = investAmount;
    }

    public StringFilter getCheckNumber() {
        return checkNumber;
    }

    public void setCheckNumber(StringFilter checkNumber) {
        this.checkNumber = checkNumber;
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

    public LongFilter getDocumentId() {
        return documentId;
    }

    public void setDocumentId(LongFilter documentId) {
        this.documentId = documentId;
    }

    public LongFilter getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(LongFilter organizationChartId) {
        this.organizationChartId = organizationChartId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final InvestToGroupTransactionCriteria that = (InvestToGroupTransactionCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(description, that.description) &&
            Objects.equals(investDate, that.investDate) &&
            Objects.equals(investYear, that.investYear) &&
            Objects.equals(investAmount, that.investAmount) &&
            Objects.equals(checkNumber, that.checkNumber) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(guid, that.guid) &&
            Objects.equals(documentId, that.documentId) &&
            Objects.equals(organizationChartId, that.organizationChartId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        description,
        investDate,
        investYear,
        investAmount,
        checkNumber,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        guid,
        documentId,
        organizationChartId
        );
    }

    @Override
    public String toString() {
        return "InvestToGroupTransactionCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (investDate != null ? "investDate=" + investDate + ", " : "") +
                (investYear != null ? "investYear=" + investYear + ", " : "") +
                (investAmount != null ? "investAmount=" + investAmount + ", " : "") +
                (checkNumber != null ? "checkNumber=" + checkNumber + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (guid != null ? "guid=" + guid + ", " : "") +
                (documentId != null ? "documentId=" + documentId + ", " : "") +
                (organizationChartId != null ? "organizationChartId=" + organizationChartId + ", " : "") +
            "}";
    }

}
