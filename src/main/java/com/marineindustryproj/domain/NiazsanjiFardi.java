package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.marineindustryproj.domain.enumeration.EducationalModuleType;

/**
 * A NiazsanjiFardi.
 */
@Entity
@Table(name = "niazsanji_fardi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class NiazsanjiFardi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "niazsanji_year")
    private Integer niazsanjiYear;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "educational_module_type", nullable = false)
    private EducationalModuleType educationalModuleType;

    @Column(name = "price_cost")
    private Long priceCost;

    @Lob
    @Column(name = "description")
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

    @Lob
    @Column(name = "conversation")
    private String conversation;

    @Size(max = 50)
    @Column(name = "change_status_user_login", length = 50)
    private String changeStatusUserLogin;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "niazsanji_fardi_document",
               joinColumns = @JoinColumn(name = "niazsanji_fardis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("niazsanjiFardis")
    private RequestNiazsanjiFardi requestNiazsanjiFardi;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("niazsanjiFardis")
    private EducationalModule educationalModule;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("niazsanjiFardis")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("niazsanjiFardis")
    private OrganizationChart organizationChart;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNiazsanjiYear() {
        return niazsanjiYear;
    }

    public NiazsanjiFardi niazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
        return this;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public String getCode() {
        return code;
    }

    public NiazsanjiFardi code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public EducationalModuleType getEducationalModuleType() {
        return educationalModuleType;
    }

    public NiazsanjiFardi educationalModuleType(EducationalModuleType educationalModuleType) {
        this.educationalModuleType = educationalModuleType;
        return this;
    }

    public void setEducationalModuleType(EducationalModuleType educationalModuleType) {
        this.educationalModuleType = educationalModuleType;
    }

    public Long getPriceCost() {
        return priceCost;
    }

    public NiazsanjiFardi priceCost(Long priceCost) {
        this.priceCost = priceCost;
        return this;
    }

    public void setPriceCost(Long priceCost) {
        this.priceCost = priceCost;
    }

    public String getDescription() {
        return description;
    }

    public NiazsanjiFardi description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public NiazsanjiFardi createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public NiazsanjiFardi createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public NiazsanjiFardi modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public NiazsanjiFardi modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public NiazsanjiFardi archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public NiazsanjiFardi archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public NiazsanjiFardi archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public NiazsanjiFardi status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConversation() {
        return conversation;
    }

    public NiazsanjiFardi conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public NiazsanjiFardi changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public NiazsanjiFardi documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public NiazsanjiFardi addDocument(Document document) {
        this.documents.add(document);
        document.getNiazsanjiFardis().add(this);
        return this;
    }

    public NiazsanjiFardi removeDocument(Document document) {
        this.documents.remove(document);
        document.getNiazsanjiFardis().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public RequestNiazsanjiFardi getRequestNiazsanjiFardi() {
        return requestNiazsanjiFardi;
    }

    public NiazsanjiFardi requestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardi = requestNiazsanjiFardi;
        return this;
    }

    public void setRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardi = requestNiazsanjiFardi;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public NiazsanjiFardi educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }

    public Person getPerson() {
        return person;
    }

    public NiazsanjiFardi person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public NiazsanjiFardi organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
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
        NiazsanjiFardi niazsanjiFardi = (NiazsanjiFardi) o;
        if (niazsanjiFardi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), niazsanjiFardi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "NiazsanjiFardi{" +
            "id=" + getId() +
            ", niazsanjiYear=" + getNiazsanjiYear() +
            ", code='" + getCode() + "'" +
            ", educationalModuleType='" + getEducationalModuleType() + "'" +
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
            ", conversation='" + getConversation() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            "}";
    }
}
