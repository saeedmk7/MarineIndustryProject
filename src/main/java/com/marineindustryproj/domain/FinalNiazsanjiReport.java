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

import com.marineindustryproj.domain.enumeration.NiazSanjiSource;

/**
 * A FinalNiazsanjiReport.
 */
@Entity
@Table(name = "final_niazsanji_report")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class FinalNiazsanjiReport implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "niazsanji_year")
    private Integer niazsanjiYear;

    @Enumerated(EnumType.STRING)
    @Column(name = "niaz_sanji_source")
    private NiazSanjiSource niazSanjiSource;

    @Column(name = "price_cost")
    private Integer priceCost;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
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

    @Column(name = "run_month")
    private Integer runMonth;

    @Column(name = "planning_run_month")
    private Integer planningRunMonth;

    @Column(name = "finalize_cost")
    private Integer finalizeCost;

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @OneToMany(mappedBy = "finalNiazsanjiReport", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople = new HashSet<>();
    @OneToMany(mappedBy = "finalNiazsanjiReport", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();
    @OneToMany(mappedBy = "finalNiazsanjiReport", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RunPhase> runPhases = new HashSet<>();
    @OneToMany(mappedBy = "finalNiazsanjiReport", orphanRemoval = true)
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<Poll> polls = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "final_niazsanji_report_document",
               joinColumns = @JoinColumn(name = "final_niazsanji_reports_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("finalNiazsanjiReports")
    private CourseType courseType;

    @ManyToOne
    @JsonIgnoreProperties("finalNiazsanjiReports")
    private OrganizationChart organizationChart;

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("finalNiazsanjiReports")
    private EducationalModule educationalModule;

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

    public FinalNiazsanjiReport niazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
        return this;
    }

    public void setNiazsanjiYear(Integer niazsanjiYear) {
        this.niazsanjiYear = niazsanjiYear;
    }

    public NiazSanjiSource getNiazSanjiSource() {
        return niazSanjiSource;
    }

    public FinalNiazsanjiReport niazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
        return this;
    }

    public void setNiazSanjiSource(NiazSanjiSource niazSanjiSource) {
        this.niazSanjiSource = niazSanjiSource;
    }

    public Integer getPriceCost() {
        return priceCost;
    }

    public FinalNiazsanjiReport priceCost(Integer priceCost) {
        this.priceCost = priceCost;
        return this;
    }

    public void setPriceCost(Integer priceCost) {
        this.priceCost = priceCost;
    }

    public String getDescription() {
        return description;
    }

    public FinalNiazsanjiReport description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public FinalNiazsanjiReport createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public FinalNiazsanjiReport createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public FinalNiazsanjiReport modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public FinalNiazsanjiReport modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public FinalNiazsanjiReport archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public FinalNiazsanjiReport archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public FinalNiazsanjiReport archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public FinalNiazsanjiReport status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getRunMonth() {
        return runMonth;
    }

    public FinalNiazsanjiReport runMonth(Integer runMonth) {
        this.runMonth = runMonth;
        return this;
    }

    public void setRunMonth(Integer runMonth) {
        this.runMonth = runMonth;
    }

    public Integer getPlanningRunMonth() {
        return planningRunMonth;
    }

    public FinalNiazsanjiReport planningRunMonth(Integer planningRunMonth) {
        this.planningRunMonth = planningRunMonth;
        return this;
    }

    public void setPlanningRunMonth(Integer planningRunMonth) {
        this.planningRunMonth = planningRunMonth;
    }

    public Integer getFinalizeCost() {
        return finalizeCost;
    }

    public FinalNiazsanjiReport finalizeCost(Integer finalizeCost) {
        this.finalizeCost = finalizeCost;
        return this;
    }

    public void setFinalizeCost(Integer finalizeCost) {
        this.finalizeCost = finalizeCost;
    }

    public String getGuid() {
        return guid;
    }

    public FinalNiazsanjiReport guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<FinalNiazsanjiReportPerson> getFinalNiazsanjiReportPeople() {
        return finalNiazsanjiReportPeople;
    }

    public FinalNiazsanjiReport finalNiazsanjiReportPeople(Set<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople) {
        this.finalNiazsanjiReportPeople = finalNiazsanjiReportPeople;
        return this;
    }

    public FinalNiazsanjiReport addFinalNiazsanjiReportPerson(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson) {
        this.finalNiazsanjiReportPeople.add(finalNiazsanjiReportPerson);
        finalNiazsanjiReportPerson.setFinalNiazsanjiReport(this);
        return this;
    }

    public FinalNiazsanjiReport removeFinalNiazsanjiReportPerson(FinalNiazsanjiReportPerson finalNiazsanjiReportPerson) {
        this.finalNiazsanjiReportPeople.remove(finalNiazsanjiReportPerson);
        finalNiazsanjiReportPerson.setFinalNiazsanjiReport(null);
        return this;
    }

    public void setFinalNiazsanjiReportPeople(Set<FinalNiazsanjiReportPerson> finalNiazsanjiReportPeople) {
        this.finalNiazsanjiReportPeople = finalNiazsanjiReportPeople;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public FinalNiazsanjiReport designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public FinalNiazsanjiReport addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.setFinalNiazsanjiReport(this);
        return this;
    }

    public FinalNiazsanjiReport removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.setFinalNiazsanjiReport(null);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }

    public Set<RunPhase> getRunPhases() {
        return runPhases;
    }

    public FinalNiazsanjiReport runPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
        return this;
    }

    public FinalNiazsanjiReport addRunPhase(RunPhase runPhase) {
        this.runPhases.add(runPhase);
        runPhase.setFinalNiazsanjiReport(this);
        return this;
    }

    public FinalNiazsanjiReport removeRunPhase(RunPhase runPhase) {
        this.runPhases.remove(runPhase);
        runPhase.setFinalNiazsanjiReport(null);
        return this;
    }

    public void setRunPhases(Set<RunPhase> runPhases) {
        this.runPhases = runPhases;
    }

    public Set<Poll> getPolls() {
        return polls;
    }

    public FinalNiazsanjiReport polls(Set<Poll> polls) {
        this.polls = polls;
        return this;
    }

    public FinalNiazsanjiReport addPoll(Poll poll) {
        this.polls.add(poll);
        poll.setFinalNiazsanjiReport(this);
        return this;
    }

    public FinalNiazsanjiReport removePoll(Poll poll) {
        this.polls.remove(poll);
        poll.setFinalNiazsanjiReport(null);
        return this;
    }

    public void setPolls(Set<Poll> polls) {
        this.polls = polls;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public FinalNiazsanjiReport documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public FinalNiazsanjiReport addDocument(Document document) {
        this.documents.add(document);
        document.getFinalNiazsanjiReports().add(this);
        return this;
    }

    public FinalNiazsanjiReport removeDocument(Document document) {
        this.documents.remove(document);
        document.getFinalNiazsanjiReports().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public FinalNiazsanjiReport courseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public FinalNiazsanjiReport organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public FinalNiazsanjiReport educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
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
        FinalNiazsanjiReport finalNiazsanjiReport = (FinalNiazsanjiReport) o;
        if (finalNiazsanjiReport.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), finalNiazsanjiReport.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "FinalNiazsanjiReport{" +
            "id=" + getId() +
            ", niazsanjiYear=" + getNiazsanjiYear() +
            ", niazSanjiSource='" + getNiazSanjiSource() + "'" +
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
            ", runMonth=" + getRunMonth() +
            ", planningRunMonth=" + getPlanningRunMonth() +
            ", finalizeCost=" + getFinalizeCost() +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
