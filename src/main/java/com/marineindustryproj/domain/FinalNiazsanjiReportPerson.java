package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

import com.marineindustryproj.domain.enumeration.NiazSanjiSource;

/**
 * A FinalNiazsanjiReportPerson.
 */
@Entity
@Table(name = "final_niazsanji_report_person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FinalNiazsanjiReportPerson implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "niaz_sanji_source", nullable = false)
    private NiazSanjiSource niazSanjiSource;

    @Column(name = "price_cost")
    private Integer priceCost;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

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

    @NotNull
    @Column(name = "archived", nullable = false)
    private Boolean archived;

    @Size(max = 50)
    @Column(name = "archived_user_login", length = 50)
    private String archivedUserLogin;

    @Column(name = "archived_date")
    private ZonedDateTime archivedDate;

    @NotNull
    @Column(name = "status", nullable = false)
    private Integer status;

    @NotNull
    @Column(name = "source_id", nullable = false)
    private Long sourceId;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("finalNiazsanjiReportPeople")
    private Person person;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("finalNiazsanjiReportPeople")
    private FinalNiazsanjiReport finalNiazsanjiReport;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public NiazSanjiSource getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public FinalNiazsanjiReportPerson niazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
        return this;
    }

    public void setNiazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public FinalNiazsanjiReportPerson priceCost(Integer priceCost) {
        this.priceCost = priceCost;
        return this;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
    }

    public String getDescription() {
        return description;
    }

    public FinalNiazsanjiReportPerson description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public FinalNiazsanjiReportPerson createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public FinalNiazsanjiReportPerson createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public FinalNiazsanjiReportPerson modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public FinalNiazsanjiReportPerson modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public FinalNiazsanjiReportPerson archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public FinalNiazsanjiReportPerson archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public FinalNiazsanjiReportPerson archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public FinalNiazsanjiReportPerson status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Long getSourceId() {
        return sourceId;
    }

    public FinalNiazsanjiReportPerson sourceId(Long sourceId) {
        this.sourceId = sourceId;
        return this;
    }

    public void setSourceId(Long sourceId) {
        this.sourceId = sourceId;
    }

    public Person getPerson() {
        return person;
    }

    public FinalNiazsanjiReportPerson person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public FinalNiazsanjiReport getFinalNiazsanjiReport() {
        return finalNiazsanjiReport;
    }

    public FinalNiazsanjiReportPerson finalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
        return this;
    }

    public void setFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
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
        FinalNiazsanjiReportPerson finalNiazsanjiReportPerson = (FinalNiazsanjiReportPerson) o;
        if (finalNiazsanjiReportPerson.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalNiazsanjiReportPerson.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReportPerson{" +
            "id=" + getId() +
            ", niazSanjiSource='" + getNiazSanjiSource() + "'" +
            ", priceCost=" + getPriceCost() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", sourceId=" + getSourceId() +
            "}";
    }
}
