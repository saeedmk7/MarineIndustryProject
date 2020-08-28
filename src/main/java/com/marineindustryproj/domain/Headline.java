package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.marineindustryproj.domain.enumeration.HeadlineLevel;

import com.marineindustryproj.domain.enumeration.HeadlineScope;

/**
 * A Headline.
 */
@Entity
@Table(name = "headline")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Headline implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 4096)
    @Column(name = "title", length = 4096, nullable = false)
    private String title;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "headline_level", nullable = false)
    private HeadlineLevel headlineLevel;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "headline_scope", nullable = false)
    private HeadlineScope headlineScope;

    @NotNull
    @Column(name = "total_hour", nullable = false)
    private Float totalHour;

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
    @JsonIgnoreProperties("headlines")
    private RequestEducationalModule requestEducationalModule;

    @ManyToOne
    @JsonIgnoreProperties("headlines")
    private EducationalModule educationalModule;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public Headline title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public HeadlineLevel getHeadlineLevel() {
        return headlineLevel;
    }

    public Headline headlineLevel(HeadlineLevel headlineLevel) {
        this.headlineLevel = headlineLevel;
        return this;
    }

    public void setHeadlineLevel(HeadlineLevel headlineLevel) {
        this.headlineLevel = headlineLevel;
    }

    public HeadlineScope getHeadlineScope() {
        return headlineScope;
    }

    public Headline headlineScope(HeadlineScope headlineScope) {
        this.headlineScope = headlineScope;
        return this;
    }

    public void setHeadlineScope(HeadlineScope headlineScope) {
        this.headlineScope = headlineScope;
    }

    public Float getTotalHour() {
        return totalHour;
    }

    public Headline totalHour(Float totalHour) {
        this.totalHour = totalHour;
        return this;
    }

    public void setTotalHour(Float totalHour) {
        this.totalHour = totalHour;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Headline createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Headline createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public Headline modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public Headline modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public RequestEducationalModule getRequestEducationalModule() {
        return requestEducationalModule;
    }

    public Headline requestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModule = requestEducationalModule;
        return this;
    }

    public void setRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModule = requestEducationalModule;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public Headline educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Headline headline = (Headline) o;
        if (headline.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), headline.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Headline{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", headlineLevel='" + getHeadlineLevel() + "'" +
            ", headlineScope='" + getHeadlineScope() + "'" +
            ", totalHour=" + getTotalHour() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
