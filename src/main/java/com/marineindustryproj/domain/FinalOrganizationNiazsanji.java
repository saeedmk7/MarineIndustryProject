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

import com.marineindustryproj.domain.enumeration.RequestStatus;

/**
 * A FinalOrganizationNiazsanji.
 */
@Entity
@Table(name = "final_organization_niazsanji")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FinalOrganizationNiazsanji implements Serializable {

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

    @Size(max = 4096)
    @Column(name = "recommended_by_orgchart", length = 4096)
    private String recommendedByOrgchart;

    @Size(max = 100)
    @Column(name = "needed_softwares", length = 100)
    private String neededSoftwares;

    @Size(max = 100)
    @Column(name = "needed_hardware", length = 100)
    private String neededHardware;

    @Size(max = 100)
    @Column(name = "students_type", length = 100)
    private String studentsType;

    @Size(max = 100)
    @Column(name = "teacher_name", length = 100)
    private String teacherName;

    @Size(max = 100)
    @Column(name = "teacher_mobile", length = 100)
    private String teacherMobile;

    @Lob
    @Column(name = "conversation")
    private String conversation;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "request_status", nullable = false)
    private RequestStatus requestStatus;

    @Size(max = 50)
    @Column(name = "change_status_user_login", length = 50)
    private String changeStatusUserLogin;

    @Size(max = 1024)
    @Column(name = "training_goals", length = 1024)
    private String trainingGoals;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "price_cost")
    private Integer priceCost;

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

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "final_organization_niazsanji_person",
               joinColumns = @JoinColumn(name = "final_organization_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"))
    private Set<Person> people = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "final_organization_niazsanji_document",
               joinColumns = @JoinColumn(name = "final_organization_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("finalOrganizationNiazsanjis")
    private CourseType courseType;

    @ManyToOne
    @JsonIgnoreProperties("finalOrganizationNiazsanjis")
    private OrganizationChart organizationChart;

    @ManyToOne
    @JsonIgnoreProperties("finalOrganizationNiazsanjis")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnoreProperties("finalOrganizationNiazsanjis")
    private EducationalModule educationalModule;

    @ManyToOne
    @JsonIgnoreProperties("finalOrganizationNiazsanjis")
    private TeachApproach teachApproach;

    @ManyToOne
    @JsonIgnoreProperties("finalOrganizationNiazsanjis")
    private RequestOrganizationNiazsanji requestOrganizationNiazsanji;

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

    public FinalOrganizationNiazsanji niazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
        return this;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public String getCode() {
        return code;
    }

    public FinalOrganizationNiazsanji code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRecommendedByOrgchart() {
        return recommendedByOrgchart;
    }

    public FinalOrganizationNiazsanji recommendedByOrgchart(String recommendedByOrgchart) {
        this.recommendedByOrgchart = recommendedByOrgchart;
        return this;
    }

    public void setRecommendedByOrgchart(String recommendedByOrgchart) {
        this.recommendedByOrgchart = recommendedByOrgchart;
    }

    public String getNeededSoftwares() {
        return neededSoftwares;
    }

    public FinalOrganizationNiazsanji neededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
        return this;
    }

    public void setNeededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
    }

    public String getNeededHardware() {
        return neededHardware;
    }

    public FinalOrganizationNiazsanji neededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
        return this;
    }

    public void setNeededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
    }

    public String getStudentsType() {
        return studentsType;
    }

    public FinalOrganizationNiazsanji studentsType(String studentsType) {
        this.studentsType = studentsType;
        return this;
    }

    public void setStudentsType(String studentsType) {
        this.studentsType = studentsType;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public FinalOrganizationNiazsanji teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public FinalOrganizationNiazsanji teacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
        return this;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
    }

    public String getConversation() {
        return conversation;
    }

    public FinalOrganizationNiazsanji conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public FinalOrganizationNiazsanji requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public FinalOrganizationNiazsanji changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getTrainingGoals() {
        return trainingGoals;
    }

    public FinalOrganizationNiazsanji trainingGoals(String trainingGoals) {
        this.trainingGoals = trainingGoals;
        return this;
    }

    public void setTrainingGoals(String trainingGoals) {
        this.trainingGoals = trainingGoals;
    }

    public String getDescription() {
        return description;
    }

    public FinalOrganizationNiazsanji description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public FinalOrganizationNiazsanji priceCost(Integer priceCost) {
        this.priceCost = priceCost;
        return this;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public FinalOrganizationNiazsanji createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public FinalOrganizationNiazsanji createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public FinalOrganizationNiazsanji modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public FinalOrganizationNiazsanji modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public FinalOrganizationNiazsanji archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public FinalOrganizationNiazsanji archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public FinalOrganizationNiazsanji archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public FinalOrganizationNiazsanji status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGuid() {
        return guid;
    }

    public FinalOrganizationNiazsanji guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public FinalOrganizationNiazsanji people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public FinalOrganizationNiazsanji addPerson(Person person) {
        this.people.add(person);
        person.getFinalOrganizationNiazsanjis().add(this);
        return this;
    }

    public FinalOrganizationNiazsanji removePerson(Person person) {
        this.people.remove(person);
        person.getFinalOrganizationNiazsanjis().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public FinalOrganizationNiazsanji documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public FinalOrganizationNiazsanji addDocument(Document document) {
        this.documents.add(document);
        document.getFinalOrganizationNiazsanjis().add(this);
        return this;
    }

    public FinalOrganizationNiazsanji removeDocument(Document document) {
        this.documents.remove(document);
        document.getFinalOrganizationNiazsanjis().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public FinalOrganizationNiazsanji courseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public FinalOrganizationNiazsanji organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public FinalOrganizationNiazsanji teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public FinalOrganizationNiazsanji educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }

    public TeachApproach getTeachApproach() {
        return teachApproach;
    }

    public FinalOrganizationNiazsanji teachApproach(TeachApproach teachApproach) {
        this.teachApproach = teachApproach;
        return this;
    }

    public void setTeachApproach(TeachApproach teachApproach) {
        this.teachApproach = teachApproach;
    }

    public RequestOrganizationNiazsanji getRequestOrganizationNiazsanji() {
        return requestOrganizationNiazsanji;
    }

    public FinalOrganizationNiazsanji requestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanji = requestOrganizationNiazsanji;
        return this;
    }

    public void setRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanji = requestOrganizationNiazsanji;
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
        FinalOrganizationNiazsanji finalOrganizationNiazsanji = (FinalOrganizationNiazsanji) o;
        if (finalOrganizationNiazsanji.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalOrganizationNiazsanji.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalOrganizationNiazsanji{" +
            "id=" + getId() +
            ", niazsanjiYear=" + getNiazsanjiYear() +
            ", code='" + getCode() + "'" +
            ", recommendedByOrgchart='" + getRecommendedByOrgchart() + "'" +
            ", neededSoftwares='" + getNeededSoftwares() + "'" +
            ", neededHardware='" + getNeededHardware() + "'" +
            ", studentsType='" + getStudentsType() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherMobile='" + getTeacherMobile() + "'" +
            ", conversation='" + getConversation() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", trainingGoals='" + getTrainingGoals() + "'" +
            ", description='" + getDescription() + "'" +
            ", priceCost=" + getPriceCost() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
