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
 * Criteria class for the JamHelpAuthority entity. This class is used in JamHelpAuthorityResource to
 * receive all the possible filtering options from the Http GET request parameters.
 * For example the following could be a valid requests:
 * <code> /jam-help-authorities?id.greaterThan=5&amp;attr1.contains=something&amp;attr2.specified=false</code>
 * As Spring is unable to properly convert the types, unless specific {@link Filter} class are used, we need to use
 * fix type specific filters.
 */
public class JamHelpAuthorityCriteria implements Serializable {

    private static final long serialVersionUID = 1L;

    private LongFilter id;

    private StringFilter authorityName;

    private BooleanFilter hasEditPermission;

    private StringFilter createUserLogin;

    private ZonedDateTimeFilter createDate;

    private StringFilter modifyUserLogin;

    private ZonedDateTimeFilter modifyDate;

    private LongFilter jamHelpId;

    public LongFilter getId() {
        return id;
    }

    public void setId(LongFilter id) {
        this.id = id;
    }

    public StringFilter getAuthorityName() {
        return authorityName;
    }

    public void setAuthorityName(StringFilter authorityName) {
        this.authorityName = authorityName;
    }

    public BooleanFilter getHasEditPermission() {
        return hasEditPermission;
    }

    public void setHasEditPermission(BooleanFilter hasEditPermission) {
        this.hasEditPermission = hasEditPermission;
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

    public LongFilter getJamHelpId() {
        return jamHelpId;
    }

    public void setJamHelpId(LongFilter jamHelpId) {
        this.jamHelpId = jamHelpId;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        final JamHelpAuthorityCriteria that = (JamHelpAuthorityCriteria) o;
        return
            Objects.equals(id, that.id) &&
            Objects.equals(authorityName, that.authorityName) &&
            Objects.equals(hasEditPermission, that.hasEditPermission) &&
            Objects.equals(createUserLogin, that.createUserLogin) &&
            Objects.equals(createDate, that.createDate) &&
            Objects.equals(modifyUserLogin, that.modifyUserLogin) &&
            Objects.equals(modifyDate, that.modifyDate) &&
            Objects.equals(jamHelpId, that.jamHelpId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(
        id,
        authorityName,
        hasEditPermission,
        createUserLogin,
        createDate,
        modifyUserLogin,
        modifyDate,
        jamHelpId
        );
    }

    @Override
    public String toString() {
        return "JamHelpAuthorityCriteria{" +
                (id != null ? "id=" + id + ", " : "") +
                (authorityName != null ? "authorityName=" + authorityName + ", " : "") +
                (hasEditPermission != null ? "hasEditPermission=" + hasEditPermission + ", " : "") +
                (createUserLogin != null ? "createUserLogin=" + createUserLogin + ", " : "") +
                (createDate != null ? "createDate=" + createDate + ", " : "") +
                (modifyUserLogin != null ? "modifyUserLogin=" + modifyUserLogin + ", " : "") +
                (modifyDate != null ? "modifyDate=" + modifyDate + ", " : "") +
                (jamHelpId != null ? "jamHelpId=" + jamHelpId + ", " : "") +
            "}";
    }

}
