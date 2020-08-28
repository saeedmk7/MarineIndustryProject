package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

import com.marineindustryproj.domain.enumeration.EducationalModuleType;

import com.marineindustryproj.domain.enumeration.RequestStatus;

import com.marineindustryproj.domain.enumeration.RequestNiazsanjiType;
import org.hibernate.annotations.Cache;

/**
 * A PrioritizeRequestNiazsanji.
 */
@Entity
@Table(name = "prioritize_request_niazsanji")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PrioritizeRequestNiazsanji implements Serializable {

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

    @Enumerated(EnumType.STRING)
    @Column(name = "educational_module_type")
    private EducationalModuleType educationalModuleType;

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

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "request_niazsanji_type", nullable = false)
    private RequestNiazsanjiType requestNiazsanjiType;

    @Column(name = "priority")
    private Integer priority;

    @OneToMany(mappedBy = "prioritizeRequestNiazsanji", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiIntegration> niazsanjiIntegrations = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "prioritize_request_niazsanji_document",
               joinColumns = @JoinColumn(name = "prioritize_request_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "prioritize_request_niazsanji_restriction",
               joinColumns = @JoinColumn(name = "prioritize_request_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "restrictions_id", referencedColumnName = "id"))
    private Set<Restriction> restrictions = new HashSet<>();

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private RequestNiazsanjiFardi requestNiazsanjiFardi;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private PreJobNiazsanji preJobNiazsanji;

    @ManyToOne(cascade = CascadeType.REMOVE)
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private RequestOtherNiazsanji requestOtherNiazsanji;

    @ManyToOne
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private NiazsanjiInput niazsanjiInput;

    @ManyToOne
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private CourseType courseType;

    @ManyToOne
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private EducationalModule educationalModule;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
    private OrganizationChart organizationChart;

    @ManyToOne
    @JsonIgnoreProperties("prioritizeRequestNiazsanjis")
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

    public PrioritizeRequestNiazsanji code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Long getCostEducationalModule() {
        return costEducationalModule;
    }

    public PrioritizeRequestNiazsanji costEducationalModule(Long costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
        return this;
    }

    public void setCostEducationalModule(Long costEducationalModule) {
        this.costEducationalModule = costEducationalModule;
    }

    public EducationalModuleType getEducationalModuleType() {
        return educationalModuleType;
    }

    public PrioritizeRequestNiazsanji educationalModuleType(EducationalModuleType educationalModuleType) {
        this.educationalModuleType = educationalModuleType;
        return this;
    }

    public void setEducationalModuleType(EducationalModuleType educationalModuleType) {
        this.educationalModuleType = educationalModuleType;
    }

    public String getDescription() {
        return description;
    }

    public PrioritizeRequestNiazsanji description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRestrictionDescription() {
        return restrictionDescription;
    }

    public PrioritizeRequestNiazsanji restrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
        return this;
    }

    public void setRestrictionDescription(String restrictionDescription) {
        this.restrictionDescription = restrictionDescription;
    }

    public String getGoalsText() {
        return goalsText;
    }

    public PrioritizeRequestNiazsanji goalsText(String goalsText) {
        this.goalsText = goalsText;
        return this;
    }

    public void setGoalsText(String goalsText) {
        this.goalsText = goalsText;
    }

    public String getPrerequisite() {
        return prerequisite;
    }

    public PrioritizeRequestNiazsanji prerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
        return this;
    }

    public void setPrerequisite(String prerequisite) {
        this.prerequisite = prerequisite;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public PrioritizeRequestNiazsanji createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public PrioritizeRequestNiazsanji createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public PrioritizeRequestNiazsanji modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public PrioritizeRequestNiazsanji modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public PrioritizeRequestNiazsanji archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public PrioritizeRequestNiazsanji archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public PrioritizeRequestNiazsanji archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public PrioritizeRequestNiazsanji status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConversation() {
        return conversation;
    }

    public PrioritizeRequestNiazsanji conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public PrioritizeRequestNiazsanji requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public PrioritizeRequestNiazsanji changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGuid() {
        return guid;
    }

    public PrioritizeRequestNiazsanji guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public PrioritizeRequestNiazsanji hasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
        return this;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public RequestNiazsanjiType getRequestNiazsanjiType() {
        return requestNiazsanjiType;
    }

    public PrioritizeRequestNiazsanji requestNiazsanjiType(RequestNiazsanjiType requestNiazsanjiType) {
        this.requestNiazsanjiType = requestNiazsanjiType;
        return this;
    }

    public void setRequestNiazsanjiType(RequestNiazsanjiType requestNiazsanjiType) {
        this.requestNiazsanjiType = requestNiazsanjiType;
    }

    public Integer getPriority() {
        return priority;
    }

    public PrioritizeRequestNiazsanji priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Set<NiazsanjiIntegration> getNiazsanjiIntegrations() {
        return niazsanjiIntegrations;
    }

    public PrioritizeRequestNiazsanji niazsanjiIntegrations(Set<NiazsanjiIntegration> niazsanjiIntegrations) {
        this.niazsanjiIntegrations = niazsanjiIntegrations;
        return this;
    }

    public PrioritizeRequestNiazsanji addNiazsanjiIntegration(NiazsanjiIntegration niazsanjiIntegration) {
        this.niazsanjiIntegrations.add(niazsanjiIntegration);
        niazsanjiIntegration.setPrioritizeRequestNiazsanji(this);
        return this;
    }

    public PrioritizeRequestNiazsanji removeNiazsanjiIntegration(NiazsanjiIntegration niazsanjiIntegration) {
        this.niazsanjiIntegrations.remove(niazsanjiIntegration);
        niazsanjiIntegration.setPrioritizeRequestNiazsanji(null);
        return this;
    }

    public void setNiazsanjiIntegrations(Set<NiazsanjiIntegration> niazsanjiIntegrations) {
        this.niazsanjiIntegrations = niazsanjiIntegrations;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public PrioritizeRequestNiazsanji documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public PrioritizeRequestNiazsanji addDocument(Document document) {
        this.documents.add(document);
        document.getPrioritizeRequestNiazsanjis().add(this);
        return this;
    }

    public PrioritizeRequestNiazsanji removeDocument(Document document) {
        this.documents.remove(document);
        document.getPrioritizeRequestNiazsanjis().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<Restriction> getRestrictions() {
        return restrictions;
    }

    public PrioritizeRequestNiazsanji restrictions(Set<Restriction> restrictions) {
        this.restrictions = restrictions;
        return this;
    }

    public PrioritizeRequestNiazsanji addRestriction(Restriction restriction) {
        this.restrictions.add(restriction);
        restriction.getPrioritizeRequestNiazsanjis().add(this);
        return this;
    }

    public PrioritizeRequestNiazsanji removeRestriction(Restriction restriction) {
        this.restrictions.remove(restriction);
        restriction.getPrioritizeRequestNiazsanjis().remove(this);
        return this;
    }

    public void setRestrictions(Set<Restriction> restrictions) {
        this.restrictions = restrictions;
    }

    public RequestNiazsanjiFardi getRequestNiazsanjiFardi() {
        return requestNiazsanjiFardi;
    }

    public PrioritizeRequestNiazsanji requestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardi = requestNiazsanjiFardi;
        return this;
    }

    public void setRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardi = requestNiazsanjiFardi;
    }

    public PreJobNiazsanji getPreJobNiazsanji() {
        return preJobNiazsanji;
    }

    public PrioritizeRequestNiazsanji preJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanji = preJobNiazsanji;
        return this;
    }

    public void setPreJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanji = preJobNiazsanji;
    }

    public RequestOtherNiazsanji getRequestOtherNiazsanji() {
        return requestOtherNiazsanji;
    }

    public PrioritizeRequestNiazsanji requestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanji = requestOtherNiazsanji;
        return this;
    }

    public void setRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanji = requestOtherNiazsanji;
    }

    public NiazsanjiInput getNiazsanjiInput() {
        return niazsanjiInput;
    }

    public PrioritizeRequestNiazsanji niazsanjiInput(NiazsanjiInput niazsanjiInput) {
        this.niazsanjiInput = niazsanjiInput;
        return this;
    }

    public void setNiazsanjiInput(NiazsanjiInput niazsanjiInput) {
        this.niazsanjiInput = niazsanjiInput;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public PrioritizeRequestNiazsanji courseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public PrioritizeRequestNiazsanji educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }

    public Person getPerson() {
        return person;
    }

    public PrioritizeRequestNiazsanji person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public PrioritizeRequestNiazsanji organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public TeachingApproach getTeachingApproach() {
        return teachingApproach;
    }

    public PrioritizeRequestNiazsanji teachingApproach(TeachingApproach teachingApproach) {
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
        PrioritizeRequestNiazsanji prioritizeRequestNiazsanji = (PrioritizeRequestNiazsanji) o;
        if (prioritizeRequestNiazsanji.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), prioritizeRequestNiazsanji.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PrioritizeRequestNiazsanji{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", costEducationalModule=" + getCostEducationalModule() +
            ", educationalModuleType='" + getEducationalModuleType() + "'" +
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
            ", requestNiazsanjiType='" + getRequestNiazsanjiType() + "'" +
            ", priority=" + getPriority() +
            "}";
    }
}
