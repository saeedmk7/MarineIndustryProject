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

/**
 * A OrganizationChart.
 */
@Entity
@Table(name = "organization_chart")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class OrganizationChart implements Serializable {

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
    @Column(name = "code", length = 50)
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

    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Person> people = new HashSet<>();
    @OneToMany(mappedBy = "parent")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrganizationChart> organizationCharts = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalNiazsanjiReport> finalNiazsanjiReports = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RunPhase> runPhases = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<OrganizationChartAuthority> organizationChartAuthorities = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestNiazsanjiFardi> requestNiazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalHistory> educationalHistories = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<InvestToGroupTransaction> investToGroupTransactions = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MediaAwarenessReport> mediaAwarenessReports = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PreJobNiazsanji> preJobNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobNiazsanji> jobNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiOther> niazsanjiOthers = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestOtherNiazsanji> requestOtherNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Soldier> soldiers = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EvaluateCriteriaTraining> evaluateCriteriaTrainings = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EvaluateCriteriaData> evaluateCriteriaData = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MatchingEducationalRecord> matchingEducationalRecords = new HashSet<>();
    @OneToMany(mappedBy = "organizationChart")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ApplicationProcess> applicationProcesses = new HashSet<>();
    @ManyToOne
    @JsonIgnoreProperties("organizationCharts")
    private OrganizationChart parent;

    @ManyToMany(mappedBy = "organizationCharts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<ForceRunningPercent> forceRunningPercents = new HashSet<>();

    @ManyToMany(mappedBy = "organizationCharts")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<ReportGenerator> reportGenerators = new HashSet<>();

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

    public OrganizationChart title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public OrganizationChart code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public OrganizationChart createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public OrganizationChart createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public OrganizationChart modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public OrganizationChart modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public OrganizationChart archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public OrganizationChart archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public OrganizationChart archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public OrganizationChart status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public OrganizationChart people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public OrganizationChart addPerson(Person person) {
        this.people.add(person);
        person.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removePerson(Person person) {
        this.people.remove(person);
        person.setOrganizationChart(null);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<OrganizationChart> getOrganizationCharts() {
        return organizationCharts;
    }

    public OrganizationChart organizationCharts(Set<OrganizationChart> organizationCharts) {
        this.organizationCharts = organizationCharts;
        return this;
    }

    public OrganizationChart addOrganizationChart(OrganizationChart organizationChart) {
        this.organizationCharts.add(organizationChart);
        organizationChart.setParent(this);
        return this;
    }

    public OrganizationChart removeOrganizationChart(OrganizationChart organizationChart) {
        this.organizationCharts.remove(organizationChart);
        organizationChart.setParent(null);
        return this;
    }

    public void setOrganizationCharts(Set<OrganizationChart> organizationCharts) {
        this.organizationCharts = organizationCharts;
    }

    public Set<RequestOrganizationNiazsanji> getRequestOrganizationNiazsanjis() {
        return requestOrganizationNiazsanjis;
    }

    public OrganizationChart requestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
        return this;
    }

    public OrganizationChart addRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.add(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.remove(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setOrganizationChart(null);
        return this;
    }

    public void setRequestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public OrganizationChart finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public OrganizationChart addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setOrganizationChart(null);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<FinalNiazsanjiReport> getFinalNiazsanjiReports() {
        return finalNiazsanjiReports;
    }

    public OrganizationChart finalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
        return this;
    }

    public OrganizationChart addFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.add(finalNiazsanjiReport);
        finalNiazsanjiReport.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.remove(finalNiazsanjiReport);
        finalNiazsanjiReport.setOrganizationChart(null);
        return this;
    }

    public void setFinalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public OrganizationChart designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public OrganizationChart addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.setOrganizationChart(null);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }

    public Set<RunPhase> getRunPhases() {
        return runPhases;
    }

    public OrganizationChart runPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
        return this;
    }

    public OrganizationChart addRunPhase(RunPhase runPhase) {
        this.runPhases.add(runPhase);
        runPhase.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeRunPhase(RunPhase runPhase) {
        this.runPhases.remove(runPhase);
        runPhase.setOrganizationChart(null);
        return this;
    }

    public void setRunPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
    }

    public Set<OrganizationChartAuthority> getOrganizationChartAuthorities() {
        return organizationChartAuthorities;
    }

    public OrganizationChart organizationChartAuthorities(Set<OrganizationChartAuthority> organizationChartAuthorities) {
        this.organizationChartAuthorities = organizationChartAuthorities;
        return this;
    }

    public OrganizationChart addOrganizationChartAuthority(OrganizationChartAuthority organizationChartAuthority) {
        this.organizationChartAuthorities.add(organizationChartAuthority);
        organizationChartAuthority.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeOrganizationChartAuthority(OrganizationChartAuthority organizationChartAuthority) {
        this.organizationChartAuthorities.remove(organizationChartAuthority);
        organizationChartAuthority.setOrganizationChart(null);
        return this;
    }

    public void setOrganizationChartAuthorities(Set<OrganizationChartAuthority> organizationChartAuthorities) {
        this.organizationChartAuthorities = organizationChartAuthorities;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public OrganizationChart niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public OrganizationChart addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.setOrganizationChart(null);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<RequestNiazsanjiFardi> getRequestNiazsanjiFardis() {
        return requestNiazsanjiFardis;
    }

    public OrganizationChart requestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
        return this;
    }

    public OrganizationChart addRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.add(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.remove(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setOrganizationChart(null);
        return this;
    }

    public void setRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
    }

    public Set<EducationalHistory> getEducationalHistories() {
        return educationalHistories;
    }

    public OrganizationChart educationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
        return this;
    }

    public OrganizationChart addEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.add(educationalHistory);
        educationalHistory.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.remove(educationalHistory);
        educationalHistory.setOrganizationChart(null);
        return this;
    }

    public void setEducationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
    }

    public Set<InvestToGroupTransaction> getInvestToGroupTransactions() {
        return investToGroupTransactions;
    }

    public OrganizationChart investToGroupTransactions(Set<InvestToGroupTransaction> investToGroupTransactions) {
        this.investToGroupTransactions = investToGroupTransactions;
        return this;
    }

    public OrganizationChart addInvestToGroupTransaction(InvestToGroupTransaction investToGroupTransaction) {
        this.investToGroupTransactions.add(investToGroupTransaction);
        investToGroupTransaction.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeInvestToGroupTransaction(InvestToGroupTransaction investToGroupTransaction) {
        this.investToGroupTransactions.remove(investToGroupTransaction);
        investToGroupTransaction.setOrganizationChart(null);
        return this;
    }

    public void setInvestToGroupTransactions(Set<InvestToGroupTransaction> investToGroupTransactions) {
        this.investToGroupTransactions = investToGroupTransactions;
    }

    public Set<MediaAwarenessReport> getMediaAwarenessReports() {
        return mediaAwarenessReports;
    }

    public OrganizationChart mediaAwarenessReports(Set<MediaAwarenessReport> mediaAwarenessReports) {
        this.mediaAwarenessReports = mediaAwarenessReports;
        return this;
    }

    public OrganizationChart addMediaAwarenessReport(MediaAwarenessReport mediaAwarenessReport) {
        this.mediaAwarenessReports.add(mediaAwarenessReport);
        mediaAwarenessReport.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeMediaAwarenessReport(MediaAwarenessReport mediaAwarenessReport) {
        this.mediaAwarenessReports.remove(mediaAwarenessReport);
        mediaAwarenessReport.setOrganizationChart(null);
        return this;
    }

    public void setMediaAwarenessReports(Set<MediaAwarenessReport> mediaAwarenessReports) {
        this.mediaAwarenessReports = mediaAwarenessReports;
    }

    public Set<PreJobNiazsanji> getPreJobNiazsanjis() {
        return preJobNiazsanjis;
    }

    public OrganizationChart preJobNiazsanjis(Set<PreJobNiazsanji> preJobNiazsanjis) {
        this.preJobNiazsanjis = preJobNiazsanjis;
        return this;
    }

    public OrganizationChart addPreJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanjis.add(preJobNiazsanji);
        preJobNiazsanji.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removePreJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanjis.remove(preJobNiazsanji);
        preJobNiazsanji.setOrganizationChart(null);
        return this;
    }

    public void setPreJobNiazsanjis(Set<PreJobNiazsanji> preJobNiazsanjis) {
        this.preJobNiazsanjis = preJobNiazsanjis;
    }

    public Set<JobNiazsanji> getJobNiazsanjis() {
        return jobNiazsanjis;
    }

    public OrganizationChart jobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
        return this;
    }

    public OrganizationChart addJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.add(jobNiazsanji);
        jobNiazsanji.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.remove(jobNiazsanji);
        jobNiazsanji.setOrganizationChart(null);
        return this;
    }

    public void setJobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
    }

    public Set<NiazsanjiOther> getNiazsanjiOthers() {
        return niazsanjiOthers;
    }

    public OrganizationChart niazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
        return this;
    }

    public OrganizationChart addNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.add(niazsanjiOther);
        niazsanjiOther.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.remove(niazsanjiOther);
        niazsanjiOther.setOrganizationChart(null);
        return this;
    }

    public void setNiazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
    }

    public Set<RequestOtherNiazsanji> getRequestOtherNiazsanjis() {
        return requestOtherNiazsanjis;
    }

    public OrganizationChart requestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
        return this;
    }

    public OrganizationChart addRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.add(requestOtherNiazsanji);
        requestOtherNiazsanji.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.remove(requestOtherNiazsanji);
        requestOtherNiazsanji.setOrganizationChart(null);
        return this;
    }

    public void setRequestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
    }

    public Set<PrioritizeRequestNiazsanji> getPrioritizeRequestNiazsanjis() {
        return prioritizeRequestNiazsanjis;
    }

    public OrganizationChart prioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
        return this;
    }

    public OrganizationChart addPrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.add(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removePrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.remove(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setOrganizationChart(null);
        return this;
    }

    public void setPrioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
    }

    public Set<Soldier> getSoldiers() {
        return soldiers;
    }

    public OrganizationChart soldiers(Set<Soldier> soldiers) {
        this.soldiers = soldiers;
        return this;
    }

    public OrganizationChart addSoldier(Soldier soldier) {
        this.soldiers.add(soldier);
        soldier.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeSoldier(Soldier soldier) {
        this.soldiers.remove(soldier);
        soldier.setOrganizationChart(null);
        return this;
    }

    public void setSoldiers(Set<Soldier> soldiers) {
        this.soldiers = soldiers;
    }

    public Set<EvaluateCriteriaTraining> getEvaluateCriteriaTrainings() {
        return evaluateCriteriaTrainings;
    }

    public OrganizationChart evaluateCriteriaTrainings(Set<EvaluateCriteriaTraining> evaluateCriteriaTrainings) {
        this.evaluateCriteriaTrainings = evaluateCriteriaTrainings;
        return this;
    }

    public OrganizationChart addEvaluateCriteriaTraining(EvaluateCriteriaTraining evaluateCriteriaTraining) {
        this.evaluateCriteriaTrainings.add(evaluateCriteriaTraining);
        evaluateCriteriaTraining.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeEvaluateCriteriaTraining(EvaluateCriteriaTraining evaluateCriteriaTraining) {
        this.evaluateCriteriaTrainings.remove(evaluateCriteriaTraining);
        evaluateCriteriaTraining.setOrganizationChart(null);
        return this;
    }

    public void setEvaluateCriteriaTrainings(Set<EvaluateCriteriaTraining> evaluateCriteriaTrainings) {
        this.evaluateCriteriaTrainings = evaluateCriteriaTrainings;
    }

    public Set<EvaluateCriteriaData> getEvaluateCriteriaData() {
        return evaluateCriteriaData;
    }

    public OrganizationChart evaluateCriteriaData(Set<EvaluateCriteriaData> evaluateCriteriaData) {
        this.evaluateCriteriaData = evaluateCriteriaData;
        return this;
    }

    public OrganizationChart addEvaluateCriteriaData(EvaluateCriteriaData evaluateCriteriaData) {
        this.evaluateCriteriaData.add(evaluateCriteriaData);
        evaluateCriteriaData.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeEvaluateCriteriaData(EvaluateCriteriaData evaluateCriteriaData) {
        this.evaluateCriteriaData.remove(evaluateCriteriaData);
        evaluateCriteriaData.setOrganizationChart(null);
        return this;
    }

    public void setEvaluateCriteriaData(Set<EvaluateCriteriaData> evaluateCriteriaData) {
        this.evaluateCriteriaData = evaluateCriteriaData;
    }

    public Set<MatchingEducationalRecord> getMatchingEducationalRecords() {
        return matchingEducationalRecords;
    }

    public OrganizationChart matchingEducationalRecords(Set<MatchingEducationalRecord> matchingEducationalRecords) {
        this.matchingEducationalRecords = matchingEducationalRecords;
        return this;
    }

    public OrganizationChart addMatchingEducationalRecord(MatchingEducationalRecord matchingEducationalRecord) {
        this.matchingEducationalRecords.add(matchingEducationalRecord);
        matchingEducationalRecord.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeMatchingEducationalRecord(MatchingEducationalRecord matchingEducationalRecord) {
        this.matchingEducationalRecords.remove(matchingEducationalRecord);
        matchingEducationalRecord.setOrganizationChart(null);
        return this;
    }

    public void setMatchingEducationalRecords(Set<MatchingEducationalRecord> matchingEducationalRecords) {
        this.matchingEducationalRecords = matchingEducationalRecords;
    }

    public Set<ApplicationProcess> getApplicationProcesses() {
        return applicationProcesses;
    }

    public OrganizationChart applicationProcesses(Set<ApplicationProcess> applicationProcesses) {
        this.applicationProcesses = applicationProcesses;
        return this;
    }

    public OrganizationChart addApplicationProcess(ApplicationProcess applicationProcess) {
        this.applicationProcesses.add(applicationProcess);
        applicationProcess.setOrganizationChart(this);
        return this;
    }

    public OrganizationChart removeApplicationProcess(ApplicationProcess applicationProcess) {
        this.applicationProcesses.remove(applicationProcess);
        applicationProcess.setOrganizationChart(null);
        return this;
    }

    public void setApplicationProcesses(Set<ApplicationProcess> applicationProcesses) {
        this.applicationProcesses = applicationProcesses;
    }

    public OrganizationChart getParent() {
        return parent;
    }

    public OrganizationChart parent(OrganizationChart organizationChart) {
        this.parent = organizationChart;
        return this;
    }

    public void setParent(OrganizationChart organizationChart) {
        this.parent = organizationChart;
    }

    public Set<ForceRunningPercent> getForceRunningPercents() {
        return forceRunningPercents;
    }

    public OrganizationChart forceRunningPercents(Set<ForceRunningPercent> forceRunningPercents) {
        this.forceRunningPercents = forceRunningPercents;
        return this;
    }

    public OrganizationChart addForceRunningPercent(ForceRunningPercent forceRunningPercent) {
        this.forceRunningPercents.add(forceRunningPercent);
        forceRunningPercent.getOrganizationCharts().add(this);
        return this;
    }

    public OrganizationChart removeForceRunningPercent(ForceRunningPercent forceRunningPercent) {
        this.forceRunningPercents.remove(forceRunningPercent);
        forceRunningPercent.getOrganizationCharts().remove(this);
        return this;
    }

    public void setForceRunningPercents(Set<ForceRunningPercent> forceRunningPercents) {
        this.forceRunningPercents = forceRunningPercents;
    }

    public Set<ReportGenerator> getReportGenerators() {
        return reportGenerators;
    }

    public OrganizationChart reportGenerators(Set<ReportGenerator> reportGenerators) {
        this.reportGenerators = reportGenerators;
        return this;
    }

    public OrganizationChart addReportGenerator(ReportGenerator reportGenerator) {
        this.reportGenerators.add(reportGenerator);
        reportGenerator.getOrganizationCharts().add(this);
        return this;
    }

    public OrganizationChart removeReportGenerator(ReportGenerator reportGenerator) {
        this.reportGenerators.remove(reportGenerator);
        reportGenerator.getOrganizationCharts().remove(this);
        return this;
    }

    public void setReportGenerators(Set<ReportGenerator> reportGenerators) {
        this.reportGenerators = reportGenerators;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        OrganizationChart organizationChart = (OrganizationChart) o;
        if (organizationChart.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), organizationChart.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "OrganizationChart{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", status=" + getStatus() +
            "}";
    }
}
