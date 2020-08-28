package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A JamHelpAuthority.
 */
@Entity
@Table(name = "jam_help_authority")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JamHelpAuthority implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "authority_name", nullable = false)
    private String authorityName;

    @NotNull
    @Column(name = "has_edit_permission", nullable = false)
    private Boolean hasEditPermission;

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

    @ManyToOne
    @JsonIgnoreProperties("jamHelpAuthorities")
    private JamHelp jamHelp;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAuthorityName() {
        return authorityName;
    }

    public JamHelpAuthority authorityName(String authorityName) {
        this.authorityName = authorityName;
        return this;
    }

    public void setAuthorityName(String authorityName) {
        this.authorityName = authorityName;
    }

    public Boolean isHasEditPermission() {
        return hasEditPermission;
    }

    public JamHelpAuthority hasEditPermission(Boolean hasEditPermission) {
        this.hasEditPermission = hasEditPermission;
        return this;
    }

    public void setHasEditPermission(Boolean hasEditPermission) {
        this.hasEditPermission = hasEditPermission;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public JamHelpAuthority createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public JamHelpAuthority createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public JamHelpAuthority modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public JamHelpAuthority modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public JamHelp getJamHelp() {
        return jamHelp;
    }

    public JamHelpAuthority jamHelp(JamHelp jamHelp) {
        this.jamHelp = jamHelp;
        return this;
    }

    public void setJamHelp(JamHelp jamHelp) {
        this.jamHelp = jamHelp;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JamHelpAuthority jamHelpAuthority = (JamHelpAuthority) o;
        if (jamHelpAuthority.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jamHelpAuthority.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JamHelpAuthority{" +
            "id=" + getId() +
            ", authorityName='" + getAuthorityName() + "'" +
            ", hasEditPermission='" + isHasEditPermission() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
