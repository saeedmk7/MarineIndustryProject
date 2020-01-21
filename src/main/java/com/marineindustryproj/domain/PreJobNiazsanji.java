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
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

/**
 * A PreJobNiazsanji.
 */
@Entity
@Table(name = "pre_job_niazsanji")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PreJobNiazsanji implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Size(max = 50)
    @Column(name = "review_date", length = 50)
    private String reviewDate;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

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

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @Column(name = "has_important_message")
    private Boolean hasImportantMessage;

    @NotNull
    @Column(name = "step", nullable = false)
    private Integer step;

    @OneToMany(mappedBy = "preJobNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "preJobNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignNiazsanji> designNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "preJobNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PreJobNiazsanjiCompetency> preJobNiazsanjiCompetencies = new HashSet<>();
    @OneToMany(mappedBy = "preJobNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobNiazsanji> jobNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "preJobNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "pre_job_niazsanji_document",
               joinColumns = @JoinColumn(name = "pre_job_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "pre_job_niazsanji_person",
               joinColumns = @JoinColumn(name = "pre_job_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"))
    private Set<Person> people = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("preJobNiazsanjis")
    private OrganizationChart organizationChart;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("preJobNiazsanjis")
    private Person person;

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

    public PreJobNiazsanji title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getReviewDate() {
        return reviewDate;
    }

    public PreJobNiazsanji reviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
        return this;
    }

    public void setReviewDate(String reviewDate) {
        this.reviewDate = reviewDate;
    }

    public String getCode() {
        return code;
    }

    public PreJobNiazsanji code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public PreJobNiazsanji description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public PreJobNiazsanji createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public PreJobNiazsanji createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public PreJobNiazsanji modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public PreJobNiazsanji modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public PreJobNiazsanji archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public PreJobNiazsanji archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public PreJobNiazsanji archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public PreJobNiazsanji status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConversation() {
        return conversation;
    }

    public PreJobNiazsanji conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public PreJobNiazsanji requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public PreJobNiazsanji changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGuid() {
        return guid;
    }

    public PreJobNiazsanji guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public PreJobNiazsanji hasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
        return this;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public Integer getStep() {
        return step;
    }

    public PreJobNiazsanji step(Integer step) {
        this.step = step;
        return this;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public PreJobNiazsanji niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public PreJobNiazsanji addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.setPreJobNiazsanji(this);
        return this;
    }

    public PreJobNiazsanji removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.setPreJobNiazsanji(null);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<DesignNiazsanji> getDesignNiazsanjis() {
        return designNiazsanjis;
    }

    public PreJobNiazsanji designNiazsanjis(Set<DesignNiazsanji> designNiazsanjis) {
        this.designNiazsanjis = designNiazsanjis;
        return this;
    }

    public PreJobNiazsanji addDesignNiazsanji(DesignNiazsanji designNiazsanji) {
        this.designNiazsanjis.add(designNiazsanji);
        designNiazsanji.setPreJobNiazsanji(this);
        return this;
    }

    public PreJobNiazsanji removeDesignNiazsanji(DesignNiazsanji designNiazsanji) {
        this.designNiazsanjis.remove(designNiazsanji);
        designNiazsanji.setPreJobNiazsanji(null);
        return this;
    }

    public void setDesignNiazsanjis(Set<DesignNiazsanji> designNiazsanjis) {
        this.designNiazsanjis = designNiazsanjis;
    }

    public Set<PreJobNiazsanjiCompetency> getPreJobNiazsanjiCompetencies() {
        return preJobNiazsanjiCompetencies;
    }

    public PreJobNiazsanji preJobNiazsanjiCompetencies(Set<PreJobNiazsanjiCompetency> preJobNiazsanjiCompetencies) {
        this.preJobNiazsanjiCompetencies = preJobNiazsanjiCompetencies;
        return this;
    }

    public PreJobNiazsanji addPreJobNiazsanjiCompetency(PreJobNiazsanjiCompetency preJobNiazsanjiCompetency) {
        this.preJobNiazsanjiCompetencies.add(preJobNiazsanjiCompetency);
        preJobNiazsanjiCompetency.setPreJobNiazsanji(this);
        return this;
    }

    public PreJobNiazsanji removePreJobNiazsanjiCompetency(PreJobNiazsanjiCompetency preJobNiazsanjiCompetency) {
        this.preJobNiazsanjiCompetencies.remove(preJobNiazsanjiCompetency);
        preJobNiazsanjiCompetency.setPreJobNiazsanji(null);
        return this;
    }

    public void setPreJobNiazsanjiCompetencies(Set<PreJobNiazsanjiCompetency> preJobNiazsanjiCompetencies) {
        this.preJobNiazsanjiCompetencies = preJobNiazsanjiCompetencies;
    }

    public Set<JobNiazsanji> getJobNiazsanjis() {
        return jobNiazsanjis;
    }

    public PreJobNiazsanji jobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
        return this;
    }

    public PreJobNiazsanji addJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.add(jobNiazsanji);
        jobNiazsanji.setPreJobNiazsanji(this);
        return this;
    }

    public PreJobNiazsanji removeJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.remove(jobNiazsanji);
        jobNiazsanji.setPreJobNiazsanji(null);
        return this;
    }

    public void setJobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
    }

    public Set<PrioritizeRequestNiazsanji> getPrioritizeRequestNiazsanjis() {
        return prioritizeRequestNiazsanjis;
    }

    public PreJobNiazsanji prioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
        return this;
    }

    public PreJobNiazsanji addPrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.add(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setPreJobNiazsanji(this);
        return this;
    }

    public PreJobNiazsanji removePrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.remove(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setPreJobNiazsanji(null);
        return this;
    }

    public void setPrioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public PreJobNiazsanji documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public PreJobNiazsanji addDocument(Document document) {
        this.documents.add(document);
        document.getPreJobNiazsanjis().add(this);
        return this;
    }

    public PreJobNiazsanji removeDocument(Document document) {
        this.documents.remove(document);
        document.getPreJobNiazsanjis().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public PreJobNiazsanji people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public PreJobNiazsanji addPerson(Person person) {
        this.people.add(person);
        person.getPreJobNiazsanjis().add(this);
        return this;
    }

    public PreJobNiazsanji removePerson(Person person) {
        this.people.remove(person);
        person.getPreJobNiazsanjis().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public PreJobNiazsanji organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public Person getPerson() {
        return person;
    }

    public PreJobNiazsanji person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        PreJobNiazsanji preJobNiazsanji = (PreJobNiazsanji) o;
        if (preJobNiazsanji.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), preJobNiazsanji.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PreJobNiazsanji{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", reviewDate='" + getReviewDate() + "'" +
            ", code='" + getCode() + "'" +
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
            ", guid='" + getGuid() + "'" +
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", step=" + getStep() +
            "}";
    }
}
