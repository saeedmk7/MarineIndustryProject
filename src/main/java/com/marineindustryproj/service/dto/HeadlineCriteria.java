package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.util.Objects;
import com.marineindustryproj.domain.enumeration.HeadlineLevel;
import com.marineindustryproj.domain.enumeration.HeadlineScope;
import io.github.jhipster.service.filter.BooleanFilter;
import io.github.jhipster.service.filter.DoubleFilter;
import io.github.jhipster.service.filter.Filter;
import io.github.jhipster.service.filter.FloatFilter;
import io.github.jhipster.service.filter.IntegerFilter;
import io.github.jhipster.service.filter.LongFilter;
import io.github.jhipster.service.filter.StringFilter;
import io.github.jhipster.service.filter.ZonedDateTimeFilter;

/**
 * Criteria class for the Headline entity. This class is used in HeadlineResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /headlines?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class HeadlineCriteria implements Serializable {
    /**
     * Class for filtering HeadlineLevel
     */
    public static class HeadlineLevelFilter extends Filter<HeadlineLevel> {
    }
    /**
     * Class for filtering HeadlineScope
     */
    public static class HeadlineScopeFilter extends Filter<HeadlineScope> {
    }

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private HeadlineLevelFilter headlineLevel;

    private HeadlineScopeFilter headlineScope;

    private FloatFilter totalHour;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter requestEducationalModuleId;

    private LongFilter educationalModuleId;

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

    public HeadlineLevelFilter getHeadlineLevel() {
        return headlineLevel;
    }

    public void setHeadlineLevel(HeadlineLevelFilter headlineLevel) {
        this.headlineLevel = headlineLevel;
    }

    public HeadlineScopeFilter getHeadlineScope() {
        return headlineScope;
    }

    public void setHeadlineScope(HeadlineScopeFilter headlineScope) {
        this.headlineScope = headlineScope;
    }

    public FloatFilter getTotalHour() {
        return totalHour;
    }

    public void setTotalHour(FloatFilter totalHour) {
        this.totalHour = totalHour;
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

    public LongFilter getRequestEducationalModuleId() {
        return requestEducationalModuleId;
    }

    public void setRequestEducationalModuleId(LongFilter requestEducationalModuleId) {
        this.requestEducationalModuleId = requestEducationalModuleId;
    }

    public LongFilter getEducationalModuleId() {
        return educationalModuleId;
    }

    public void setEducationalModuleId(LongFilter educationalModuleId) {
        this.educationalModuleId = educationalModuleId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final HeadlineCriteria that = (HeadlineCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(headlineLevel, that.headlineLevel) &&
            Objects.equals(headlineScope, that.headlineScope) &&
            Objects.equals(totalHour, that.totalHour) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(requestEducationalModuleId, that.requestEducationalModuleId) &&
            Objects.equals(educationalModuleId, that.educationalModuleId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        headlineLevel,
        headlineScope,
        totalHour,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        requestEducationalModuleId,
        educationalModuleId
        );
    }

    @Override
    public String toString() {
        return "HeadlineCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (headlineLevel != null ? "headlineLevel=" + headlineLevel + ", " : "") +
                (headlineScope != null ? "headlineScope=" + headlineScope + ", " : "") +
                (totalHour != null ? "totalHour=" + totalHour + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (requestEducationalModuleId != null ? "requestEducationalModuleId=" + requestEducationalModuleId + ", " : "") +
                (educationalModuleId != null ? "educationalModuleId=" + educationalModuleId + ", " : "") +
            "}";
    }

}
