package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.id.GUIDGenerator;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.Objects;

/**
 * A Person.
 */
@Entity
@Table(name = "person")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    /*@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")*/
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @NotNull
    @Size(max = 100)
    @Column(name = "family", length = 100, nullable = false)
    private String family;

    @NotNull
    @Size(max = 100)
    @Column(name = "father_name", length = 100, nullable = false)
    private String fatherName;

    @NotNull
    @Size(max = 12)
    @Column(name = "certificate_number", length = 12, nullable = false)
    private String certificateNumber;

    @NotNull
    @Size(max = 20)
    /*@Pattern(regexp = "[0-9]{11}")*/
    @Column(name = "national_id", length = 20, nullable = false, unique = true)
    private String nationalId;

    @NotNull
    @Column(name = "birth_date", nullable = false)
    private ZonedDateTime birthDate;

    @Size(max = 50)
    @Column(name = "personel_code", length = 50)
    private String personelCode;

    @Column(name = "employment_date")
    private ZonedDateTime employmentDate;

    @Column(name = "year_of_service")
    private Integer yearOfService;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

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
    @Column(name = "guid", length = 50, unique = true)
    private String guid;

    @Size(max = 50)
    @Column(name = "phone_number", length = 50)
    private String phoneNumber;

    @Size(max = 50)
    @Column(name = "mobile", length = 50)
    private String mobile;

    @Size(max = 4096)
    @Column(name = "address", length = 4096)
    private String address;

    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople = new HashSet<>();
    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PollScore> pollScores = new HashSet<>();
    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestNiazsanjiFardi> requestNiazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalHistory> educationalHistories = new HashSet<>();
    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalRecord> educationalRecords = new HashSet<>();
    @OneToMany(mappedBy = "person")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobRecord> jobRecords = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "person_document",
               joinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "person_scientific_work_group",
               joinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "scientific_work_groups_id", referencedColumnName = "id"))
    private Set<ScientificWorkGroup> scientificWorkGroups = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("people")
    private Qualification lastQualification;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private FieldOfStudy lastFieldOfStudy;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private EmploymentType employmentType;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private WorkGroup workGroup;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private WorkIndustry workIndustry;

    @ManyToOne
    @JsonIgnoreProperties("jobPeople")
    private Job job;

    @ManyToOne
    @JsonIgnoreProperties("practicaljobPeople")
    private Job practicaljob;

    @ManyToOne
    @JsonIgnoreProperties("people")
    private OrganizationChart organizationChart;

    @ManyToMany(mappedBy = "people")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<MainTask> mainTasks = new HashSet<>();

    @ManyToMany(mappedBy = "people")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "people")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();

    @ManyToMany(mappedBy = "people")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();

    @ManyToMany(mappedBy = "people")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RunPhase> runPhases = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Person name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public Person family(String family) {
        this.family = family;
        return this;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getFatherName() {
        return fatherName;
    }

    public Person fatherName(String fatherName) {
        this.fatherName = fatherName;
        return this;
    }

    public void setFatherName(String fatherName) {
        this.fatherName = fatherName;
    }

    public String getCertificateNumber() {
        return certificateNumber;
    }

    public Person certificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
        return this;
    }

    public void setCertificateNumber(String certificateNumber) {
        this.certificateNumber = certificateNumber;
    }

    public String getNationalId() {
        return nationalId;
    }

    public Person nationalId(String nationalId) {
        this.nationalId = nationalId;
        return this;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public ZonedDateTime getBirthDate() {
        return birthDate;
    }

    public Person birthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
        return this;
    }

    public void setBirthDate(ZonedDateTime birthDate) {
        this.birthDate = birthDate;
    }

    public String getPersonelCode() {
        return personelCode;
    }

    public Person personelCode(String personelCode) {
        this.personelCode = personelCode;
        return this;
    }

    public void setPersonelCode(String personelCode) {
        this.personelCode = personelCode;
    }

    public ZonedDateTime getEmploymentDate() {
        return employmentDate;
    }

    public Person employmentDate(ZonedDateTime employmentDate) {
        this.employmentDate = employmentDate;
        return this;
    }

    public void setEmploymentDate(ZonedDateTime employmentDate) {
        this.employmentDate = employmentDate;
    }

    public Integer getYearOfService() {
        return yearOfService;
    }

    public Person yearOfService(Integer yearOfService) {
        this.yearOfService = yearOfService;
        return this;
    }

    public void setYearOfService(Integer yearOfService) {
        this.yearOfService = yearOfService;
    }

    public String getCode() {
        return code;
    }

    public Person code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Person createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Person createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public Person modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public Person modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public Person archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public Person archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public Person archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public Person status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getGuid() {
        return guid;
    }

    public Person guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public Person phoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getMobile() {
        return mobile;
    }

    public Person mobile(String mobile) {
        this.mobile = mobile;
        return this;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAddress() {
        return address;
    }

    public Person address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Set<FinalNiazsanjiReportPerson> getFinalNiazsanjiReportPeople() {
        return finalNiazsanjiReportPeople;
    }

    public Person finalNiazsanjiReportPeople(Set<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople) {
        this.finalNiazsanjiReportPeople = finalNiazsanjiReportPeople;
        return this;
    }

    public Person addFinalNiazsanjiReportPerson(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson) {
        this.finalNiazsanjiReportPeople.add(finalNiazsanjiReportPerson);
        finalNiazsanjiReportPerson.setPerson(this);
        return this;
    }

    public Person removeFinalNiazsanjiReportPerson(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson) {
        this.finalNiazsanjiReportPeople.remove(finalNiazsanjiReportPerson);
        finalNiazsanjiReportPerson.setPerson(null);
        return this;
    }

    public void setFinalNiazsanjiReportPeople(Set<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople) {
        this.finalNiazsanjiReportPeople = finalNiazsanjiReportPeople;
    }

    public Set<PollScore> getPollScores() {
        return pollScores;
    }

    public Person pollScores(Set<PollScore> pollScores) {
        this.pollScores = pollScores;
        return this;
    }

    public Person addPollScore(PollScore pollScore) {
        this.pollScores.add(pollScore);
        pollScore.setPerson(this);
        return this;
    }

    public Person removePollScore(PollScore pollScore) {
        this.pollScores.remove(pollScore);
        pollScore.setPerson(null);
        return this;
    }

    public void setPollScores(Set<PollScore> pollScores) {
        this.pollScores = pollScores;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public Person niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public Person addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.setPerson(this);
        return this;
    }

    public Person removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.setPerson(null);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<RequestNiazsanjiFardi> getRequestNiazsanjiFardis() {
        return requestNiazsanjiFardis;
    }

    public Person requestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
        return this;
    }

    public Person addRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.add(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setPerson(this);
        return this;
    }

    public Person removeRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.remove(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setPerson(null);
        return this;
    }

    public void setRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
    }

    public Set<EducationalHistory> getEducationalHistories() {
        return educationalHistories;
    }

    public Person educationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
        return this;
    }

    public Person addEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.add(educationalHistory);
        educationalHistory.setPerson(this);
        return this;
    }

    public Person removeEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.remove(educationalHistory);
        educationalHistory.setPerson(null);
        return this;
    }

    public void setEducationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
    }

    public Set<EducationalRecord> getEducationalRecords() {
        return educationalRecords;
    }

    public Person educationalRecords(Set<EducationalRecord> educationalRecords) {
        this.educationalRecords = educationalRecords;
        return this;
    }

    public Person addEducationalRecord(EducationalRecord educationalRecord) {
        this.educationalRecords.add(educationalRecord);
        educationalRecord.setPerson(this);
        return this;
    }

    public Person removeEducationalRecord(EducationalRecord educationalRecord) {
        this.educationalRecords.remove(educationalRecord);
        educationalRecord.setPerson(null);
        return this;
    }

    public void setEducationalRecords(Set<EducationalRecord> educationalRecords) {
        this.educationalRecords = educationalRecords;
    }

    public Set<JobRecord> getJobRecords() {
        return jobRecords;
    }

    public Person jobRecords(Set<JobRecord> jobRecords) {
        this.jobRecords = jobRecords;
        return this;
    }

    public Person addJobRecord(JobRecord jobRecord) {
        this.jobRecords.add(jobRecord);
        jobRecord.setPerson(this);
        return this;
    }

    public Person removeJobRecord(JobRecord jobRecord) {
        this.jobRecords.remove(jobRecord);
        jobRecord.setPerson(null);
        return this;
    }

    public void setJobRecords(Set<JobRecord> jobRecords) {
        this.jobRecords = jobRecords;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public Person documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public Person addDocument(Document document) {
        this.documents.add(document);
        document.getPeople().add(this);
        return this;
    }

    public Person removeDocument(Document document) {
        this.documents.remove(document);
        document.getPeople().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<ScientificWorkGroup> getScientificWorkGroups() {
        return scientificWorkGroups;
    }

    public Person scientificWorkGroups(Set<ScientificWorkGroup> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
        return this;
    }

    public Person addScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroups.add(scientificWorkGroup);
        scientificWorkGroup.getPeople().add(this);
        return this;
    }

    public Person removeScientificWorkGroup(ScientificWorkGroup scientificWorkGroup) {
        this.scientificWorkGroups.remove(scientificWorkGroup);
        scientificWorkGroup.getPeople().remove(this);
        return this;
    }

    public void setScientificWorkGroups(Set<ScientificWorkGroup> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
    }

    public Qualification getLastQualification() {
        return lastQualification;
    }

    public Person lastQualification(Qualification qualification) {
        this.lastQualification = qualification;
        return this;
    }

    public void setLastQualification(Qualification qualification) {
        this.lastQualification = qualification;
    }

    public FieldOfStudy getLastFieldOfStudy() {
        return lastFieldOfStudy;
    }

    public Person lastFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.lastFieldOfStudy = fieldOfStudy;
        return this;
    }

    public void setLastFieldOfStudy(FieldOfStudy fieldOfStudy) {
        this.lastFieldOfStudy = fieldOfStudy;
    }

    public EmploymentType getEmploymentType() {
        return employmentType;
    }

    public Person employmentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
        return this;
    }

    public void setEmploymentType(EmploymentType employmentType) {
        this.employmentType = employmentType;
    }

    public WorkGroup getWorkGroup() {
        return workGroup;
    }

    public Person workGroup(WorkGroup workGroup) {
        this.workGroup = workGroup;
        return this;
    }

    public void setWorkGroup(WorkGroup workGroup) {
        this.workGroup = workGroup;
    }

    public WorkIndustry getWorkIndustry() {
        return workIndustry;
    }

    public Person workIndustry(WorkIndustry workIndustry) {
        this.workIndustry = workIndustry;
        return this;
    }

    public void setWorkIndustry(WorkIndustry workIndustry) {
        this.workIndustry = workIndustry;
    }

    public Job getJob() {
        return job;
    }

    public Person job(Job job) {
        this.job = job;
        return this;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public Job getPracticaljob() {
        return practicaljob;
    }

    public Person practicaljob(Job job) {
        this.practicaljob = job;
        return this;
    }

    public void setPracticaljob(Job job) {
        this.practicaljob = job;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public Person organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public Set<MainTask> getMainTasks() {
        return mainTasks;
    }

    public Person mainTasks(Set<MainTask> mainTasks) {
        this.mainTasks = mainTasks;
        return this;
    }

    public Person addMainTask(MainTask mainTask) {
        this.mainTasks.add(mainTask);
        mainTask.getPeople().add(this);
        return this;
    }

    public Person removeMainTask(MainTask mainTask) {
        this.mainTasks.remove(mainTask);
        mainTask.getPeople().remove(this);
        return this;
    }

    public void setMainTasks(Set<MainTask> mainTasks) {
        this.mainTasks = mainTasks;
    }

    public Set<RequestOrganizationNiazsanji> getRequestOrganizationNiazsanjis() {
        return requestOrganizationNiazsanjis;
    }

    public Person requestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
        return this;
    }

    public Person addRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.add(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.getPeople().add(this);
        return this;
    }

    public Person removeRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.remove(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.getPeople().remove(this);
        return this;
    }

    public void setRequestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public Person finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public Person addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.getPeople().add(this);
        return this;
    }

    public Person removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.getPeople().remove(this);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public Person designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public Person addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.getPeople().add(this);
        return this;
    }

    public Person removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.getPeople().remove(this);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }

    public Set<RunPhase> getRunPhases() {
        return runPhases;
    }

    public Person runPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
        return this;
    }

    public Person addRunPhase(RunPhase runPhase) {
        this.runPhases.add(runPhase);
        runPhase.getPeople().add(this);
        return this;
    }

    public Person removeRunPhase(RunPhase runPhase) {
        this.runPhases.remove(runPhase);
        runPhase.getPeople().remove(this);
        return this;
    }

    public void setRunPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
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
        Person person = (Person) o;
        if (person.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), person.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Person{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", family='" + getFamily() + "'" +
            ", fatherName='" + getFatherName() + "'" +
            ", certificateNumber='" + getCertificateNumber() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", birthDate='" + getBirthDate() + "'" +
            ", personelCode='" + getPersonelCode() + "'" +
            ", employmentDate='" + getEmploymentDate() + "'" +
            ", yearOfService=" + getYearOfService() +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            ", guid='" + getGuid() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
            ", mobile='" + getMobile() + "'" +
            ", address='" + getAddress() + "'" +
            "}";
    }
}
