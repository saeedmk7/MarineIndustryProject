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
 * A RequestOrganizationNiazsanji.
 */
@Entity
@Table(name = "request_organization_niazsanji")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class RequestOrganizationNiazsanji implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

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

    @Column(name = "teacher_not_found")
    private Boolean teacherNotFound;

    @Size(max = 100)
    @Column(name = "teacher_name", length = 100)
    private String teacherName;

    @Size(max = 100)
    @Column(name = "teacher_mobile", length = 100)
    private String teacherMobile;

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

    @Lob
    @Column(name = "conversation")
    private String conversation;

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @OneToMany(mappedBy = "requestOrganizationNiazsanji")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_organization_niazsanji_person",
               joinColumns = @JoinColumn(name = "request_organization_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"))
    private Set<Person> people = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "request_organization_niazsanji_document",
               joinColumns = @JoinColumn(name = "request_organization_niazsanjis_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("requestOrganizationNiazsanjis")
    private CourseType courseType;

    @ManyToOne
    @JsonIgnoreProperties("requestOrganizationNiazsanjis")
    private OrganizationChart organizationChart;

    @ManyToOne
    @JsonIgnoreProperties("requestOrganizationNiazsanjis")
    private Teacher teacher;

    @ManyToOne
    @JsonIgnoreProperties("requestOrganizationNiazsanjis")
    private EducationalModule educationalModule;

    @ManyToOne
    @JsonIgnoreProperties("requestOrganizationNiazsanjis")
    private TeachApproach teachApproach;

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

    public RequestOrganizationNiazsanji code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getRecommendedByOrgchart() {
        return recommendedByOrgchart;
    }

    public RequestOrganizationNiazsanji recommendedByOrgchart(String recommendedByOrgchart) {
        this.recommendedByOrgchart = recommendedByOrgchart;
        return this;
    }

    public void setRecommendedByOrgchart(String recommendedByOrgchart) {
        this.recommendedByOrgchart = recommendedByOrgchart;
    }

    public String getNeededSoftwares() {
        return neededSoftwares;
    }

    public RequestOrganizationNiazsanji neededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
        return this;
    }

    public void setNeededSoftwares(String neededSoftwares) {
        this.neededSoftwares = neededSoftwares;
    }

    public String getNeededHardware() {
        return neededHardware;
    }

    public RequestOrganizationNiazsanji neededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
        return this;
    }

    public void setNeededHardware(String neededHardware) {
        this.neededHardware = neededHardware;
    }

    public String getStudentsType() {
        return studentsType;
    }

    public RequestOrganizationNiazsanji studentsType(String studentsType) {
        this.studentsType = studentsType;
        return this;
    }

    public void setStudentsType(String studentsType) {
        this.studentsType = studentsType;
    }

    public Boolean isTeacherNotFound() {
        return teacherNotFound;
    }

    public RequestOrganizationNiazsanji teacherNotFound(Boolean teacherNotFound) {
        this.teacherNotFound = teacherNotFound;
        return this;
    }

    public void setTeacherNotFound(Boolean teacherNotFound) {
        this.teacherNotFound = teacherNotFound;
    }

    public String getTeacherName() {
        return teacherName;
    }

    public RequestOrganizationNiazsanji teacherName(String teacherName) {
        this.teacherName = teacherName;
        return this;
    }

    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }

    public String getTeacherMobile() {
        return teacherMobile;
    }

    public RequestOrganizationNiazsanji teacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
        return this;
    }

    public void setTeacherMobile(String teacherMobile) {
        this.teacherMobile = teacherMobile;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public RequestOrganizationNiazsanji requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public RequestOrganizationNiazsanji changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getTrainingGoals() {
        return trainingGoals;
    }

    public RequestOrganizationNiazsanji trainingGoals(String trainingGoals) {
        this.trainingGoals = trainingGoals;
        return this;
    }

    public void setTrainingGoals(String trainingGoals) {
        this.trainingGoals = trainingGoals;
    }

    public String getDescription() {
        return description;
    }

    public RequestOrganizationNiazsanji description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public RequestOrganizationNiazsanji priceCost(Integer priceCost) {
        this.priceCost = priceCost;
        return this;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public RequestOrganizationNiazsanji createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public RequestOrganizationNiazsanji createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public RequestOrganizationNiazsanji modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public RequestOrganizationNiazsanji modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public RequestOrganizationNiazsanji archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public RequestOrganizationNiazsanji archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public RequestOrganizationNiazsanji archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public RequestOrganizationNiazsanji status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConversation() {
        return conversation;
    }

    public RequestOrganizationNiazsanji conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public String getGuid() {
        return guid;
    }

    public RequestOrganizationNiazsanji guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public RequestOrganizationNiazsanji finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public RequestOrganizationNiazsanji addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setRequestOrganizationNiazsanji(this);
        return this;
    }

    public RequestOrganizationNiazsanji removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setRequestOrganizationNiazsanji(null);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public RequestOrganizationNiazsanji people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public RequestOrganizationNiazsanji addPerson(Person person) {
        this.people.add(person);
        person.getRequestOrganizationNiazsanjis().add(this);
        return this;
    }

    public RequestOrganizationNiazsanji removePerson(Person person) {
        this.people.remove(person);
        person.getRequestOrganizationNiazsanjis().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public RequestOrganizationNiazsanji documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public RequestOrganizationNiazsanji addDocument(Document document) {
        this.documents.add(document);
        document.getRequestOrganizationNiazsanjis().add(this);
        return this;
    }

    public RequestOrganizationNiazsanji removeDocument(Document document) {
        this.documents.remove(document);
        document.getRequestOrganizationNiazsanjis().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public RequestOrganizationNiazsanji courseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public RequestOrganizationNiazsanji organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public RequestOrganizationNiazsanji teacher(Teacher teacher) {
        this.teacher = teacher;
        return this;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public RequestOrganizationNiazsanji educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }

    public TeachApproach getTeachApproach() {
        return teachApproach;
    }

    public RequestOrganizationNiazsanji teachApproach(TeachApproach teachApproach) {
        this.teachApproach = teachApproach;
        return this;
    }

    public void setTeachApproach(TeachApproach teachApproach) {
        this.teachApproach = teachApproach;
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
        RequestOrganizationNiazsanji requestOrganizationNiazsanji = (RequestOrganizationNiazsanji) o;
        if (requestOrganizationNiazsanji.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), requestOrganizationNiazsanji.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "RequestOrganizationNiazsanji{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", recommendedByOrgchart='" + getRecommendedByOrgchart() + "'" +
            ", neededSoftwares='" + getNeededSoftwares() + "'" +
            ", neededHardware='" + getNeededHardware() + "'" +
            ", studentsType='" + getStudentsType() + "'" +
            ", teacherNotFound='" + isTeacherNotFound() + "'" +
            ", teacherName='" + getTeacherName() + "'" +
            ", teacherMobile='" + getTeacherMobile() + "'" +
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
            ", conversation='" + getConversation() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
