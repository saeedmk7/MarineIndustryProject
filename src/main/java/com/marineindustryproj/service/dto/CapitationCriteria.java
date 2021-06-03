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
 * Criteria class for the Capitation entity. This class is used in CapitationResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /capitations?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class CapitationCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter code;

    private StringFilter description;

    private IntegerFilter employeeMaximumAllowablePersonHours;

    private IntegerFilter bossMaximumAllowablePersonHours;

    private IntegerFilter employeeMaximumAllowablePersonCosts;

    private IntegerFilter bossMaximumAllowablePersonCosts;

    private IntegerFilter capitationYear;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

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

    public StringFilter getDescription() {
        return description;
    }

    public void setDescription(StringFilter description) {
        this.description = description;
    }

    public IntegerFilter getEmployeeMaximumAllowablePersonHours() {
        return employeeMaximumAllowablePersonHours;
    }

    public void setEmployeeMaximumAllowablePersonHours(IntegerFilter employeeMaximumAllowablePersonHours) {
        this.employeeMaximumAllowablePersonHours = employeeMaximumAllowablePersonHours;
    }

    public IntegerFilter getBossMaximumAllowablePersonHours() {
        return bossMaximumAllowablePersonHours;
    }

    public void setBossMaximumAllowablePersonHours(IntegerFilter bossMaximumAllowablePersonHours) {
        this.bossMaximumAllowablePersonHours = bossMaximumAllowablePersonHours;
    }

    public IntegerFilter getEmployeeMaximumAllowablePersonCosts() {
        return employeeMaximumAllowablePersonCosts;
    }

    public void setEmployeeMaximumAllowablePersonCosts(IntegerFilter employeeMaximumAllowablePersonCosts) {
        this.employeeMaximumAllowablePersonCosts = employeeMaximumAllowablePersonCosts;
    }

    public IntegerFilter getBossMaximumAllowablePersonCosts() {
        return bossMaximumAllowablePersonCosts;
    }

    public void setBossMaximumAllowablePersonCosts(IntegerFilter bossMaximumAllowablePersonCosts) {
        this.bossMaximumAllowablePersonCosts = bossMaximumAllowablePersonCosts;
    }

    public IntegerFilter getCapitationYear() {
        return capitationYear;
    }

    public void setCapitationYear(IntegerFilter capitationYear) {
        this.capitationYear = capitationYear;
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


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final CapitationCriteria that = (CapitationCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(code, that.code) &&
            Objects.equals(description, that.description) &&
            Objects.equals(employeeMaximumAllowablePersonHours, that.employeeMaximumAllowablePersonHours) &&
            Objects.equals(bossMaximumAllowablePersonHours, that.bossMaximumAllowablePersonHours) &&
            Objects.equals(employeeMaximumAllowablePersonCosts, that.employeeMaximumAllowablePersonCosts) &&
            Objects.equals(bossMaximumAllowablePersonCosts, that.bossMaximumAllowablePersonCosts) &&
            Objects.equals(capitationYear, that.capitationYear) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        code,
        description,
        employeeMaximumAllowablePersonHours,
        bossMaximumAllowablePersonHours,
        employeeMaximumAllowablePersonCosts,
        bossMaximumAllowablePersonCosts,
        capitationYear,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate
        );
    }

    @Override
    public String toString() {
        return "CapitationCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (employeeMaximumAllowablePersonHours != null ? "employeeMaximumAllowablePersonHours=" + employeeMaximumAllowablePersonHours + ", " : "") +
                (bossMaximumAllowablePersonHours != null ? "bossMaximumAllowablePersonHours=" + bossMaximumAllowablePersonHours + ", " : "") +
                (employeeMaximumAllowablePersonCosts != null ? "employeeMaximumAllowablePersonCosts=" + employeeMaximumAllowablePersonCosts + ", " : "") +
                (bossMaximumAllowablePersonCosts != null ? "bossMaximumAllowablePersonCosts=" + bossMaximumAllowablePersonCosts + ", " : "") +
                (capitationYear != null ? "capitationYear=" + capitationYear + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
            "}";
    }

}
