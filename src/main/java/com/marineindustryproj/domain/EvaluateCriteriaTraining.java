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
 * A EvaluateCriteriaTraining.
 */
@Entity
@Table(name = "evaluate_criteria_training")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvaluateCriteriaTraining implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "process_title", length = 4096)
    private String processTitle;

    @NotNull
    @Column(name = "process_weight", nullable = false)
    private Integer processWeight;

    @Size(max = 4096)
    @Column(name = "activity_description", length = 4096)
    private String activityDescription;

    @Column(name = "criteria_weight")
    private Integer criteriaWeight;

    @Size(max = 4096)
    @Column(name = "measure_description", length = 4096)
    private String measureDescription;

    @Column(name = "first_number")
    private Integer firstNumber;

    @Column(name = "second_number")
    private Integer secondNumber;

    @Column(name = "third_number")
    private Integer thirdNumber;

    @Column(name = "measure_calc")
    private Integer measureCalc;

    @Column(name = "measure_calc_percent")
    private Integer measureCalcPercent;

    @Column(name = "result")
    private Integer result;

    @Column(name = "result_percent")
    private Integer resultPercent;

    @NotNull
    @Column(name = "mi_year", nullable = false)
    private Integer year;

    @NotNull
    @Column(name = "month", nullable = false)
    private Integer month;

    @NotNull
    @Column(name = "report_time", nullable = false)
    private Integer reportTime;

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

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @OneToMany(mappedBy = "evaluateCriteriaTraining")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EvaluateCriteriaData> evaluateCriteriaData = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "evaluate_criteria_training_document",
               joinColumns = @JoinColumn(name = "evaluate_criteria_trainings_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("evaluateCriteriaTrainings")
    private OrganizationChart organizationChart;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getProcessTitle() {
        return processTitle;
    }

    public EvaluateCriteriaTraining processTitle(String processTitle) {
        this.processTitle = processTitle;
        return this;
    }

    public void setProcessTitle(String processTitle) {
        this.processTitle = processTitle;
    }

    public Integer getProcessWeight() {
        return processWeight;
    }

    public EvaluateCriteriaTraining processWeight(Integer processWeight) {
        this.processWeight = processWeight;
        return this;
    }

    public void setProcessWeight(Integer processWeight) {
        this.processWeight = processWeight;
    }

    public String getActivityDescription() {
        return activityDescription;
    }

    public EvaluateCriteriaTraining activityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
        return this;
    }

    public void setActivityDescription(String activityDescription) {
        this.activityDescription = activityDescription;
    }

    public Integer getCriteriaWeight() {
        return criteriaWeight;
    }

    public EvaluateCriteriaTraining criteriaWeight(Integer criteriaWeight) {
        this.criteriaWeight = criteriaWeight;
        return this;
    }

    public void setCriteriaWeight(Integer criteriaWeight) {
        this.criteriaWeight = criteriaWeight;
    }

    public String getMeasureDescription() {
        return measureDescription;
    }

    public EvaluateCriteriaTraining measureDescription(String measureDescription) {
        this.measureDescription = measureDescription;
        return this;
    }

    public void setMeasureDescription(String measureDescription) {
        this.measureDescription = measureDescription;
    }

    public Integer getFirstNumber() {
        return firstNumber;
    }

    public EvaluateCriteriaTraining firstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
        return this;
    }

    public void setFirstNumber(Integer firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Integer getSecondNumber() {
        return secondNumber;
    }

    public EvaluateCriteriaTraining secondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
        return this;
    }

    public void setSecondNumber(Integer secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Integer getThirdNumber() {
        return thirdNumber;
    }

    public EvaluateCriteriaTraining thirdNumber(Integer thirdNumber) {
        this.thirdNumber = thirdNumber;
        return this;
    }

    public void setThirdNumber(Integer thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public Integer getMeasureCalc() {
        return measureCalc;
    }

    public EvaluateCriteriaTraining measureCalc(Integer measureCalc) {
        this.measureCalc = measureCalc;
        return this;
    }

    public void setMeasureCalc(Integer measureCalc) {
        this.measureCalc = measureCalc;
    }

    public Integer getMeasureCalcPercent() {
        return measureCalcPercent;
    }

    public EvaluateCriteriaTraining measureCalcPercent(Integer measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
        return this;
    }

    public void setMeasureCalcPercent(Integer measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
    }

    public Integer getResult() {
        return result;
    }

    public EvaluateCriteriaTraining result(Integer result) {
        this.result = result;
        return this;
    }

    public void setResult(Integer result) {
        this.result = result;
    }

    public Integer getResultPercent() {
        return resultPercent;
    }

    public EvaluateCriteriaTraining resultPercent(Integer resultPercent) {
        this.resultPercent = resultPercent;
        return this;
    }

    public void setResultPercent(Integer resultPercent) {
        this.resultPercent = resultPercent;
    }

    public Integer getYear() {
        return year;
    }

    public EvaluateCriteriaTraining year(Integer year) {
        this.year = year;
        return this;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public Integer getMonth() {
        return month;
    }

    public EvaluateCriteriaTraining month(Integer month) {
        this.month = month;
        return this;
    }

    public void setMonth(Integer month) {
        this.month = month;
    }

    public Integer getReportTime() {
        return reportTime;
    }

    public EvaluateCriteriaTraining reportTime(Integer reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(Integer reportTime) {
        this.reportTime = reportTime;
    }

    public String getDescription() {
        return description;
    }

    public EvaluateCriteriaTraining description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EvaluateCriteriaTraining createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EvaluateCriteriaTraining createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EvaluateCriteriaTraining modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EvaluateCriteriaTraining modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public EvaluateCriteriaTraining guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<EvaluateCriteriaData> getEvaluateCriteriaData() {
        return evaluateCriteriaData;
    }

    public EvaluateCriteriaTraining evaluateCriteriaData(Set<EvaluateCriteriaData> evaluateCriteriaData) {
        this.evaluateCriteriaData = evaluateCriteriaData;
        return this;
    }

    public EvaluateCriteriaTraining addEvaluateCriteriaData(EvaluateCriteriaData evaluateCriteriaData) {
        this.evaluateCriteriaData.add(evaluateCriteriaData);
        evaluateCriteriaData.setEvaluateCriteriaTraining(this);
        return this;
    }

    public EvaluateCriteriaTraining removeEvaluateCriteriaData(EvaluateCriteriaData evaluateCriteriaData) {
        this.evaluateCriteriaData.remove(evaluateCriteriaData);
        evaluateCriteriaData.setEvaluateCriteriaTraining(null);
        return this;
    }

    public void setEvaluateCriteriaData(Set<EvaluateCriteriaData> evaluateCriteriaData) {
        this.evaluateCriteriaData = evaluateCriteriaData;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public EvaluateCriteriaTraining documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public EvaluateCriteriaTraining addDocument(Document document) {
        this.documents.add(document);
        document.getEvaluateCriteriaTrainings().add(this);
        return this;
    }

    public EvaluateCriteriaTraining removeDocument(Document document) {
        this.documents.remove(document);
        document.getEvaluateCriteriaTrainings().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public EvaluateCriteriaTraining organizationChart(OrganizationChart organizationChart) {
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
        EvaluateCriteriaTraining evaluateCriteriaTraining = (EvaluateCriteriaTraining) o;
        if (evaluateCriteriaTraining.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateCriteriaTraining.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateCriteriaTraining{" +
            "id=" + getId() +
            ", processTitle='" + getProcessTitle() + "'" +
            ", processWeight=" + getProcessWeight() +
            ", activityDescription='" + getActivityDescription() + "'" +
            ", criteriaWeight=" + getCriteriaWeight() +
            ", measureDescription='" + getMeasureDescription() + "'" +
            ", firstNumber=" + getFirstNumber() +
            ", secondNumber=" + getSecondNumber() +
            ", thirdNumber=" + getThirdNumber() +
            ", measureCalc=" + getMeasureCalc() +
            ", measureCalcPercent=" + getMeasureCalcPercent() +
            ", result=" + getResult() +
            ", resultPercent=" + getResultPercent() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", reportTime=" + getReportTime() +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
