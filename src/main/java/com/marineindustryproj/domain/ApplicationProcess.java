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
 * A ApplicationProcess.
 */
@Entity
@Table(name = "application_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ApplicationProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Lob
    @Column(name = "description")
    private String description;

    @Size(max = 4096)
    @Column(name = "job_after_process", length = 4096)
    private String jobAfterProcess;

    @Size(max = 4096)
    @Column(name = "accepted_university_and_degree", length = 4096)
    private String acceptedUniversityAndDegree;

    @Size(max = 50)
    @Column(name = "start_study_date", length = 50)
    private String startStudyDate;

    @Size(max = 4096)
    @Column(name = "graduate_date_of_new_course", length = 4096)
    private String graduateDateOfNewCourse;

    @Size(max = 50)
    @Column(name = "average_point_of_new_course", length = 50)
    private String averagePointOfNewCourse;

    @Size(max = 4096)
    @Column(name = "accepted_major_and_field", length = 4096)
    private String acceptedMajorAndField;

    @Column(name = "have_used_educational_facilities")
    private Boolean haveUsedEducationalFacilities;

    @Size(max = 4096)
    @Column(name = "have_used_educational_facilities_description", length = 4096)
    private String haveUsedEducationalFacilitiesDescription;

    @Size(max = 50)
    @Column(name = "date_of_acceptance_of_degree", length = 50)
    private String dateOfAcceptanceOfDegree;

    @Size(max = 4096)
    @Column(name = "type_and_amount_of_facilities", length = 4096)
    private String typeAndAmountOfFacilities;

    @Size(max = 4096)
    @Column(name = "academic_commitments_fulfilled", length = 4096)
    private String academicCommitmentsFulfilled;

    @Size(max = 4096)
    @Column(name = "remaining_academic_commitments", length = 4096)
    private String remainingAcademicCommitments;

    @Size(max = 4096)
    @Column(name = "requested_facilities_text", length = 4096)
    private String requestedFacilitiesText;

    @Size(max = 4096)
    @Column(name = "requested_facilities_description", length = 4096)
    private String requestedFacilitiesDescription;

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
    @Column(name = "chart_status", nullable = false)
    private Integer chartStatus;

    @NotNull
    @Column(name = "boss_status", nullable = false)
    private Integer bossStatus;

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

    @Size(max = 4096)
    @Column(name = "selected_module_ids", length = 4096)
    private String selectedModuleIds;

    @OneToMany(mappedBy = "applicationProcess")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApplicationProcessRunStep> applicationProcessRunSteps = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "application_process_document",
               joinColumns = @JoinColumn(name = "application_processes_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("applicationProcesses")
    private EducationalRecord educationalRecord;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("applicationProcesses")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("applicationProcesses")
    private OrganizationChart organizationChart;

    @ManyToOne
    @JsonIgnoreProperties("applicationProcesses")
    private Qualification qualification;

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

    public ApplicationProcess code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public ApplicationProcess description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobAfterProcess() {
        return jobAfterProcess;
    }

    public ApplicationProcess jobAfterProcess(String jobAfterProcess) {
        this.jobAfterProcess = jobAfterProcess;
        return this;
    }

    public void setJobAfterProcess(String jobAfterProcess) {
        this.jobAfterProcess = jobAfterProcess;
    }

    public String getAcceptedUniversityAndDegree() {
        return acceptedUniversityAndDegree;
    }

    public ApplicationProcess acceptedUniversityAndDegree(String acceptedUniversityAndDegree) {
        this.acceptedUniversityAndDegree = acceptedUniversityAndDegree;
        return this;
    }

    public void setAcceptedUniversityAndDegree(String acceptedUniversityAndDegree) {
        this.acceptedUniversityAndDegree = acceptedUniversityAndDegree;
    }

    public String getStartStudyDate() {
        return startStudyDate;
    }

    public ApplicationProcess startStudyDate(String startStudyDate) {
        this.startStudyDate = startStudyDate;
        return this;
    }

    public void setStartStudyDate(String startStudyDate) {
        this.startStudyDate = startStudyDate;
    }

    public String getGraduateDateOfNewCourse() {
        return graduateDateOfNewCourse;
    }

    public ApplicationProcess graduateDateOfNewCourse(String graduateDateOfNewCourse) {
        this.graduateDateOfNewCourse = graduateDateOfNewCourse;
        return this;
    }

    public void setGraduateDateOfNewCourse(String graduateDateOfNewCourse) {
        this.graduateDateOfNewCourse = graduateDateOfNewCourse;
    }

    public String getAveragePointOfNewCourse() {
        return averagePointOfNewCourse;
    }

    public ApplicationProcess averagePointOfNewCourse(String averagePointOfNewCourse) {
        this.averagePointOfNewCourse = averagePointOfNewCourse;
        return this;
    }

    public void setAveragePointOfNewCourse(String averagePointOfNewCourse) {
        this.averagePointOfNewCourse = averagePointOfNewCourse;
    }

    public String getAcceptedMajorAndField() {
        return acceptedMajorAndField;
    }

    public ApplicationProcess acceptedMajorAndField(String acceptedMajorAndField) {
        this.acceptedMajorAndField = acceptedMajorAndField;
        return this;
    }

    public void setAcceptedMajorAndField(String acceptedMajorAndField) {
        this.acceptedMajorAndField = acceptedMajorAndField;
    }

    public Boolean isHaveUsedEducationalFacilities() {
        return haveUsedEducationalFacilities;
    }

    public ApplicationProcess haveUsedEducationalFacilities(Boolean haveUsedEducationalFacilities) {
        this.haveUsedEducationalFacilities = haveUsedEducationalFacilities;
        return this;
    }

    public void setHaveUsedEducationalFacilities(Boolean haveUsedEducationalFacilities) {
        this.haveUsedEducationalFacilities = haveUsedEducationalFacilities;
    }

    public String getHaveUsedEducationalFacilitiesDescription() {
        return haveUsedEducationalFacilitiesDescription;
    }

    public ApplicationProcess haveUsedEducationalFacilitiesDescription(String haveUsedEducationalFacilitiesDescription) {
        this.haveUsedEducationalFacilitiesDescription = haveUsedEducationalFacilitiesDescription;
        return this;
    }

    public void setHaveUsedEducationalFacilitiesDescription(String haveUsedEducationalFacilitiesDescription) {
        this.haveUsedEducationalFacilitiesDescription = haveUsedEducationalFacilitiesDescription;
    }

    public String getDateOfAcceptanceOfDegree() {
        return dateOfAcceptanceOfDegree;
    }

    public ApplicationProcess dateOfAcceptanceOfDegree(String dateOfAcceptanceOfDegree) {
        this.dateOfAcceptanceOfDegree = dateOfAcceptanceOfDegree;
        return this;
    }

    public void setDateOfAcceptanceOfDegree(String dateOfAcceptanceOfDegree) {
        this.dateOfAcceptanceOfDegree = dateOfAcceptanceOfDegree;
    }

    public String getTypeAndAmountOfFacilities() {
        return typeAndAmountOfFacilities;
    }

    public ApplicationProcess typeAndAmountOfFacilities(String typeAndAmountOfFacilities) {
        this.typeAndAmountOfFacilities = typeAndAmountOfFacilities;
        return this;
    }

    public void setTypeAndAmountOfFacilities(String typeAndAmountOfFacilities) {
        this.typeAndAmountOfFacilities = typeAndAmountOfFacilities;
    }

    public String getAcademicCommitmentsFulfilled() {
        return academicCommitmentsFulfilled;
    }

    public ApplicationProcess academicCommitmentsFulfilled(String academicCommitmentsFulfilled) {
        this.academicCommitmentsFulfilled = academicCommitmentsFulfilled;
        return this;
    }

    public void setAcademicCommitmentsFulfilled(String academicCommitmentsFulfilled) {
        this.academicCommitmentsFulfilled = academicCommitmentsFulfilled;
    }

    public String getRemainingAcademicCommitments() {
        return remainingAcademicCommitments;
    }

    public ApplicationProcess remainingAcademicCommitments(String remainingAcademicCommitments) {
        this.remainingAcademicCommitments = remainingAcademicCommitments;
        return this;
    }

    public void setRemainingAcademicCommitments(String remainingAcademicCommitments) {
        this.remainingAcademicCommitments = remainingAcademicCommitments;
    }

    public String getRequestedFacilitiesText() {
        return requestedFacilitiesText;
    }

    public ApplicationProcess requestedFacilitiesText(String requestedFacilitiesText) {
        this.requestedFacilitiesText = requestedFacilitiesText;
        return this;
    }

    public void setRequestedFacilitiesText(String requestedFacilitiesText) {
        this.requestedFacilitiesText = requestedFacilitiesText;
    }

    public String getRequestedFacilitiesDescription() {
        return requestedFacilitiesDescription;
    }

    public ApplicationProcess requestedFacilitiesDescription(String requestedFacilitiesDescription) {
        this.requestedFacilitiesDescription = requestedFacilitiesDescription;
        return this;
    }

    public void setRequestedFacilitiesDescription(String requestedFacilitiesDescription) {
        this.requestedFacilitiesDescription = requestedFacilitiesDescription;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public ApplicationProcess createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ApplicationProcess createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public ApplicationProcess modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public ApplicationProcess modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public ApplicationProcess archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public ApplicationProcess archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public ApplicationProcess archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getChartStatus() {
        return chartStatus;
    }

    public ApplicationProcess chartStatus(Integer chartStatus) {
        this.chartStatus = chartStatus;
        return this;
    }

    public void setChartStatus(Integer chartStatus) {
        this.chartStatus = chartStatus;
    }

    public Integer getBossStatus() {
        return bossStatus;
    }

    public ApplicationProcess bossStatus(Integer bossStatus) {
        this.bossStatus = bossStatus;
        return this;
    }

    public void setBossStatus(Integer bossStatus) {
        this.bossStatus = bossStatus;
    }

    public String getConversation() {
        return conversation;
    }

    public ApplicationProcess conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public ApplicationProcess requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public ApplicationProcess changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGuid() {
        return guid;
    }

    public ApplicationProcess guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public ApplicationProcess hasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
        return this;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public String getSelectedModuleIds() {
        return selectedModuleIds;
    }

    public ApplicationProcess selectedModuleIds(String selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
        return this;
    }

    public void setSelectedModuleIds(String selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
    }

    public Set<ApplicationProcessRunStep> getApplicationProcessRunSteps() {
        return applicationProcessRunSteps;
    }

    public ApplicationProcess applicationProcessRunSteps(Set<ApplicationProcessRunStep> applicationProcessRunSteps) {
        this.applicationProcessRunSteps = applicationProcessRunSteps;
        return this;
    }

    public ApplicationProcess addApplicationProcessRunStep(ApplicationProcessRunStep applicationProcessRunStep) {
        this.applicationProcessRunSteps.add(applicationProcessRunStep);
        applicationProcessRunStep.setApplicationProcess(this);
        return this;
    }

    public ApplicationProcess removeApplicationProcessRunStep(ApplicationProcessRunStep applicationProcessRunStep) {
        this.applicationProcessRunSteps.remove(applicationProcessRunStep);
        applicationProcessRunStep.setApplicationProcess(null);
        return this;
    }

    public void setApplicationProcessRunSteps(Set<ApplicationProcessRunStep> applicationProcessRunSteps) {
        this.applicationProcessRunSteps = applicationProcessRunSteps;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public ApplicationProcess documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public ApplicationProcess addDocument(Document document) {
        this.documents.add(document);
        document.getApplicationProcesses().add(this);
        return this;
    }

    public ApplicationProcess removeDocument(Document document) {
        this.documents.remove(document);
        document.getApplicationProcesses().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public EducationalRecord getEducationalRecord() {
        return educationalRecord;
    }

    public ApplicationProcess educationalRecord(EducationalRecord educationalRecord) {
        this.educationalRecord = educationalRecord;
        return this;
    }

    public void setEducationalRecord(EducationalRecord educationalRecord) {
        this.educationalRecord = educationalRecord;
    }

    public Person getPerson() {
        return person;
    }

    public ApplicationProcess person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public ApplicationProcess organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public Qualification getQualification() {
        return qualification;
    }

    public ApplicationProcess qualification(Qualification qualification) {
        this.qualification = qualification;
        return this;
    }

    public void setQualification(Qualification qualification) {
        this.qualification = qualification;
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
        ApplicationProcess applicationProcess = (ApplicationProcess) o;
        if (applicationProcess.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), applicationProcess.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ApplicationProcess{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", jobAfterProcess='" + getJobAfterProcess() + "'" +
            ", acceptedUniversityAndDegree='" + getAcceptedUniversityAndDegree() + "'" +
            ", startStudyDate='" + getStartStudyDate() + "'" +
            ", graduateDateOfNewCourse='" + getGraduateDateOfNewCourse() + "'" +
            ", averagePointOfNewCourse='" + getAveragePointOfNewCourse() + "'" +
            ", acceptedMajorAndField='" + getAcceptedMajorAndField() + "'" +
            ", haveUsedEducationalFacilities='" + isHaveUsedEducationalFacilities() + "'" +
            ", haveUsedEducationalFacilitiesDescription='" + getHaveUsedEducationalFacilitiesDescription() + "'" +
            ", dateOfAcceptanceOfDegree='" + getDateOfAcceptanceOfDegree() + "'" +
            ", typeAndAmountOfFacilities='" + getTypeAndAmountOfFacilities() + "'" +
            ", academicCommitmentsFulfilled='" + getAcademicCommitmentsFulfilled() + "'" +
            ", remainingAcademicCommitments='" + getRemainingAcademicCommitments() + "'" +
            ", requestedFacilitiesText='" + getRequestedFacilitiesText() + "'" +
            ", requestedFacilitiesDescription='" + getRequestedFacilitiesDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", chartStatus=" + getChartStatus() +
            ", bossStatus=" + getBossStatus() +
            ", conversation='" + getConversation() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", guid='" + getGuid() + "'" +
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", selectedModuleIds='" + getSelectedModuleIds() + "'" +
            "}";
    }
}
