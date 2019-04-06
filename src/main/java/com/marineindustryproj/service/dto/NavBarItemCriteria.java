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
 * Criteria class for the NavBarItem entity. This class is used in NavBarItemResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /nav-bar-items?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class NavBarItemCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter title;

    private StringFilter code;

    private StringFilter description;

    private StringFilter url;

    private StringFilter faicon;

    private BooleanFilter isActive;

    private IntegerFilter displayOrder;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter navBarItemId;

    private LongFilter navBarItemAuthorityId;

    private LongFilter parentId;

    public NavBarItemCriteria() {
    }

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

    public StringFilter getUrl() {
        return url;
    }

    public void setUrl(StringFilter url) {
        this.url = url;
    }

    public StringFilter getFaicon() {
        return faicon;
    }

    public void setFaicon(StringFilter faicon) {
        this.faicon = faicon;
    }

    public BooleanFilter getIsActive() {
        return isActive;
    }

    public void setIsActive(BooleanFilter isActive) {
        this.isActive = isActive;
    }

    public IntegerFilter getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(IntegerFilter displayOrder) {
        this.displayOrder = displayOrder;
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

    public LongFilter getNavBarItemId() {
        return navBarItemId;
    }

    public void setNavBarItemId(LongFilter navBarItemId) {
        this.navBarItemId = navBarItemId;
    }

    public LongFilter getNavBarItemAuthorityId() {
        return navBarItemAuthorityId;
    }

    public void setNavBarItemAuthorityId(LongFilter navBarItemAuthorityId) {
        this.navBarItemAuthorityId = navBarItemAuthorityId;
    }

    public LongFilter getParentId() {
        return parentId;
    }

    public void setParentId(LongFilter parentId) {
        this.parentId = parentId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final NavBarItemCriteria that = (NavBarItemCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(title, that.title) &&
            Objects.equals(code, that.code) &&
            Objects.equals(description, that.description) &&
            Objects.equals(url, that.url) &&
            Objects.equals(faicon, that.faicon) &&
            Objects.equals(isActive, that.isActive) &&
            Objects.equals(displayOrder, that.displayOrder) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(navBarItemId, that.navBarItemId) &&
            Objects.equals(navBarItemAuthorityId, that.navBarItemAuthorityId) &&
            Objects.equals(parentId, that.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        title,
        code,
        description,
        url,
        faicon,
        isActive,
        displayOrder,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        navBarItemId,
        navBarItemAuthorityId,
        parentId
        );
    }

    @Override
    public String toString() {
        return "NavBarItemCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (title != null ? "title=" + title + ", " : "") +
                (code != null ? "code=" + code + ", " : "") +
                (description != null ? "description=" + description + ", " : "") +
                (url != null ? "url=" + url + ", " : "") +
                (faicon != null ? "faicon=" + faicon + ", " : "") +
                (isActive != null ? "isActive=" + isActive + ", " : "") +
                (displayOrder != null ? "displayOrder=" + displayOrder + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (navBarItemId != null ? "navBarItemId=" + navBarItemId + ", " : "") +
                (navBarItemAuthorityId != null ? "navBarItemAuthorityId=" + navBarItemAuthorityId + ", " : "") +
                (parentId != null ? "parentId=" + parentId + ", " : "") +
            "}";
    }

}
