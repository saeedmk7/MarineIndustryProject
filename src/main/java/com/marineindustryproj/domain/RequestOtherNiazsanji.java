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
 * A RequestOtherNiazsanji.
 */
@Entity
@Table(name = "request_other_niazsanji")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestOtherNiazsanji implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Column(name = "cost_educational_module")
    private Long costEducationalModule;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 4096)
    @Column(name = "restriction_description", length = 4096)
    private String restrictionDescription;

    @Size(max = 4096)
    @Column(name = "goals_text", length = 4096)
    private String goalsText;

    @Size(max = 4096)
    @Column(name = "prerequisite", length = 4096)
    private String prerequisite;

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

    @OneToMany(mappedBy = "requestOtherNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiOther> niazsanjiOthers = new HashSet<>();
    @OneToMany(mappedBy = "requestOtherNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_other_niazsanji_document",
               joinColumns = @JoinColumn(name = "request_other_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_other_niazsanji_restriction",
               joinColumns = @JoinColumn(name = "request_other_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "restrictions_id", referencedColumnName = "id"))
    private Set<Restriction> restrictions = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("requestOtherNiazsanjis")
    private NiazsanjiInput niazsanjiInput;

    @ManyToOne
    @JsonIgnoreProperties("requestOtherNiazsanjis")
    private CourseType courseType;

    @ManyToOne
    @JsonIgnoreProperties("requestOtherNiazsanjis")
    private EducationalModule educationalModule;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("requestOtherNiazsanjis")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("requestOtherNiazsanjis")
    private OrganizationChart organizationChart;

    @ManyToOne
    @JsonIgnoreProperties("requestOtherNiazsanjis")
    private TeachingApproach teachingApproach;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public RequestOtherNiazsanji code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCostEducationalModule() {
        return costEducationalModule;
    }

    public RequestOtherNiazsanji costEducationalModule(Long costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
        return this;
    }

    public void setCostEducationalModule(Long costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
    }

    public String getDescription() {
        return description;
    }

    public RequestOtherNiazsanji description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestrictionDescription() {
        return restrictionDescription;
    }

    public RequestOtherNiazsanji restrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
        return this;
    }

    public void setRestrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public String getGoalsText() {
        return goalsText;
    }

    public RequestOtherNiazsanji goalsText(String goalsText) {
        this.goalsText = goalsText;
        return this;
    }

    public void setGoalsText(String goalsText) {
        this.goalsText = goalsText;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public RequestOtherNiazsanji prerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
        return this;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public RequestOtherNiazsanji createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public RequestOtherNiazsanji createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public RequestOtherNiazsanji modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public RequestOtherNiazsanji modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public RequestOtherNiazsanji archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public RequestOtherNiazsanji archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public RequestOtherNiazsanji archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public RequestOtherNiazsanji status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConversation() {
        return conversation;
    }

    public RequestOtherNiazsanji conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public RequestOtherNiazsanji requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public RequestOtherNiazsanji changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGuid() {
        return guid;
    }

    public RequestOtherNiazsanji guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public RequestOtherNiazsanji hasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
        return this;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public Set<NiazsanjiOther> getNiazsanjiOthers() {
        return niazsanjiOthers;
    }

    public RequestOtherNiazsanji niazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
        return this;
    }

    public RequestOtherNiazsanji addNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.add(niazsanjiOther);
        niazsanjiOther.setRequestOtherNiazsanji(this);
        return this;
    }

    public RequestOtherNiazsanji removeNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.remove(niazsanjiOther);
        niazsanjiOther.setRequestOtherNiazsanji(null);
        return this;
    }

    public void setNiazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
    }

    public Set<PrioritizeRequestNiazsanji> getPrioritizeRequestNiazsanjis() {
        return prioritizeRequestNiazsanjis;
    }

    public RequestOtherNiazsanji prioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
        return this;
    }

    public RequestOtherNiazsanji addPrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.add(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setRequestOtherNiazsanji(this);
        return this;
    }

    public RequestOtherNiazsanji removePrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.remove(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setRequestOtherNiazsanji(null);
        return this;
    }

    public void setPrioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public RequestOtherNiazsanji documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public RequestOtherNiazsanji addDocument(Document document) {
        this.documents.add(document);
        document.getRequestOtherNiazsanjis().add(this);
        return this;
    }

    public RequestOtherNiazsanji removeDocument(Document document) {
        this.documents.remove(document);
        document.getRequestOtherNiazsanjis().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Restriction> getRestrictions() {
        return restrictions;
    }

    public RequestOtherNiazsanji restrictions(Set<Restriction> restrictions) {
        this.restrictions = restrictions;
        return this;
    }

    public RequestOtherNiazsanji addRestriction(Restriction restriction) {
        this.restrictions.add(restriction);
        restriction.getRequestOtherNiazsanjis().add(this);
        return this;
    }

    public RequestOtherNiazsanji removeRestriction(Restriction restriction) {
        this.restrictions.remove(restriction);
        restriction.getRequestOtherNiazsanjis().remove(this);
        return this;
    }

    public void setRestrictions(Set<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public NiazsanjiInput getNiazsanjiInput() {
        return niazsanjiInput;
    }

    public RequestOtherNiazsanji niazsanjiInput(NiazsanjiInput niazsanjiInput) {
        this.niazsanjiInput = niazsanjiInput;
        return this;
    }

    public void setNiazsanjiInput(NiazsanjiInput niazsanjiInput) {
        this.niazsanjiInput = niazsanjiInput;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public RequestOtherNiazsanji courseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public RequestOtherNiazsanji educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }

    public Person getPerson() {
        return person;
    }

    public RequestOtherNiazsanji person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public RequestOtherNiazsanji organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public TeachingApproach getTeachingApproach() {
        return teachingApproach;
    }

    public RequestOtherNiazsanji teachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproach = teachingApproach;
        return this;
    }

    public void setTeachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproach = teachingApproach;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RequestOtherNiazsanji requestOtherNiazsanji = (RequestOtherNiazsanji) o;
        if (requestOtherNiazsanji.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestOtherNiazsanji.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestOtherNiazsanji{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", costEducationalModule=" + getCostEducationalModule() +
            ", description='" + getDescription() + "'" +
            ", restrictionDescription='" + getRestrictionDescription() + "'" +
            ", goalsText='" + getGoalsText() + "'" +
            ", prerequisite='" + getPrerequisite() + "'" +
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
            "}";
    }
}
