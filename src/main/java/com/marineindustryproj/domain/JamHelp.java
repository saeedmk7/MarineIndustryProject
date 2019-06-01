package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A JamHelp.
 */
@Entity
@Table(name = "jam_help")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JamHelp implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @NotNull
    @Size(max = 1024)
    @Column(name = "description", length = 1024, nullable = false)
    private String description;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    
    @Lob
    @Column(name = "file_doc", nullable = false)
    private String fileDoc;

    @Size(max = 4096)
    @Column(name = "page_url", length = 4096)
    private String pageUrl;

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

    @OneToMany(mappedBy = "jamHelp")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JamHelpAuthority> jamHelpAuthorities = new HashSet<>();
    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public JamHelp title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public JamHelp description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCode() {
        return code;
    }

    public JamHelp code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public JamHelp fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getPageUrl() {
        return pageUrl;
    }

    public JamHelp pageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
        return this;
    }

    public void setPageUrl(String pageUrl) {
        this.pageUrl = pageUrl;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public JamHelp createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public JamHelp createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public JamHelp modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public JamHelp modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<JamHelpAuthority> getJamHelpAuthorities() {
        return jamHelpAuthorities;
    }

    public JamHelp jamHelpAuthorities(Set<JamHelpAuthority> jamHelpAuthorities) {
        this.jamHelpAuthorities = jamHelpAuthorities;
        return this;
    }

    public JamHelp addJamHelpAuthority(JamHelpAuthority jamHelpAuthority) {
        this.jamHelpAuthorities.add(jamHelpAuthority);
        jamHelpAuthority.setJamHelp(this);
        return this;
    }

    public JamHelp removeJamHelpAuthority(JamHelpAuthority jamHelpAuthority) {
        this.jamHelpAuthorities.remove(jamHelpAuthority);
        jamHelpAuthority.setJamHelp(null);
        return this;
    }

    public void setJamHelpAuthorities(Set<JamHelpAuthority> jamHelpAuthorities) {
        this.jamHelpAuthorities = jamHelpAuthorities;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        JamHelp jamHelp = (JamHelp) o;
        if (jamHelp.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jamHelp.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JamHelp{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", code='" + getCode() + "'" +
            ", fileDoc='" + getFileDoc() + "'" +
            ", pageUrl='" + getPageUrl() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
