package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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

import com.marineindustryproj.domain.enumeration.RequestStatus;

/**
 * A RequestNiazsanjiFardi.
 */
@Entity
@Table(name = "request_niazsanji_fardi")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestNiazsanjiFardi implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "cost_approved_educational_module")
    private Long costApprovedEducationalModule;

    @Column(name = "cost_all_educational_module")
    private Long costAllEducationalModule;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Size(max = 50)
    @Column(name = "change_status_user_login", length = 50)
    private String changeStatusUserLogin;

    @OneToMany(mappedBy = "requestNiazsanjiFardi")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_niazsanji_fardi_document",
               joinColumns = @JoinColumn(name = "request_niazsanji_fardis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("approvedRequestNiazsanjiFardis")
    private EducationalModule approvedEducationalModule;

    @ManyToOne
    @JsonIgnoreProperties("allRequestNiazsanjiFardis")
    private EducationalModule allEducationalModule;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("requestNiazsanjiFardis")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("requestNiazsanjiFardis")
    private OrganizationChart organizationChart;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public RequestNiazsanjiFardi code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCostApprovedEducationalModule() {
        return costApprovedEducationalModule;
    }

    public RequestNiazsanjiFardi costApprovedEducationalModule(Long costApprovedEducationalModule) {
        this.costApprovedEducationalModule = costApprovedEducationalModule;
        return this;
    }

    public void setCostApprovedEducationalModule(Long costApprovedEducationalModule) {
        this.costApprovedEducationalModule = costApprovedEducationalModule;
    }

    public Long getCostAllEducationalModule() {
        return costAllEducationalModule;
    }

    public RequestNiazsanjiFardi costAllEducationalModule(Long costAllEducationalModule) {
        this.costAllEducationalModule = costAllEducationalModule;
        return this;
    }

    public void setCostAllEducationalModule(Long costAllEducationalModule) {
        this.costAllEducationalModule = costAllEducationalModule;
    }

    public String getDescription() {
        return description;
    }

    public RequestNiazsanjiFardi description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public RequestNiazsanjiFardi createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public RequestNiazsanjiFardi createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public RequestNiazsanjiFardi modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public RequestNiazsanjiFardi modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public RequestNiazsanjiFardi archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public RequestNiazsanjiFardi archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public RequestNiazsanjiFardi archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public RequestNiazsanjiFardi status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConversation() {
        return conversation;
    }

    public RequestNiazsanjiFardi conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public RequestNiazsanjiFardi requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public RequestNiazsanjiFardi changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public RequestNiazsanjiFardi niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public RequestNiazsanjiFardi addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.setRequestNiazsanjiFardi(this);
        return this;
    }

    public RequestNiazsanjiFardi removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.setRequestNiazsanjiFardi(null);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public RequestNiazsanjiFardi documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public RequestNiazsanjiFardi addDocument(Document document) {
        this.documents.add(document);
        document.getRequestNiazsanjiFardis().add(this);
        return this;
    }

    public RequestNiazsanjiFardi removeDocument(Document document) {
        this.documents.remove(document);
        document.getRequestNiazsanjiFardis().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public EducationalModule getApprovedEducationalModule() {
        return approvedEducationalModule;
    }

    public RequestNiazsanjiFardi approvedEducationalModule(EducationalModule educationalModule) {
        this.approvedEducationalModule = educationalModule;
        return this;
    }

    public void setApprovedEducationalModule(EducationalModule educationalModule) {
        this.approvedEducationalModule = educationalModule;
    }

    public EducationalModule getAllEducationalModule() {
        return allEducationalModule;
    }

    public RequestNiazsanjiFardi allEducationalModule(EducationalModule educationalModule) {
        this.allEducationalModule = educationalModule;
        return this;
    }

    public void setAllEducationalModule(EducationalModule educationalModule) {
        this.allEducationalModule = educationalModule;
    }

    public Person getPerson() {
        return person;
    }

    public RequestNiazsanjiFardi person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public RequestNiazsanjiFardi organizationChart(OrganizationChart organizationChart) {
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
        RequestNiazsanjiFardi requestNiazsanjiFardi = (RequestNiazsanjiFardi) o;
        if (requestNiazsanjiFardi.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestNiazsanjiFardi.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestNiazsanjiFardi{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", costApprovedEducationalModule=" + getCostApprovedEducationalModule() +
            ", costAllEducationalModule=" + getCostAllEducationalModule() +
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
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            "}";
    }
}
