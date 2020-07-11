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

/**
 * A EvaluateCriteriaData.
 */
@Entity
@Table(name = "evaluate_criteria_data")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EvaluateCriteriaData implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "title", length = 4096)
    private String title;

    @Column(name = "first_number")
    private Float firstNumber;

    @Column(name = "second_number")
    private Float secondNumber;

    @Column(name = "third_number")
    private Float thirdNumber;

    @Column(name = "measure_calc")
    private Float measureCalc;

    @Column(name = "measure_calc_percent")
    private Float measureCalcPercent;

    @Column(name = "result")
    private Float result;

    @Column(name = "result_percent")
    private Float resultPercent;

    @NotNull
    @Column(name = "mi_year", nullable = false)
    private Float year;

    @NotNull
    @Column(name = "month", nullable = false)
    private Float month;

    @Lob
    @Column(name = "file_doc")
    private String fileDoc;

    @Size(max = 50)
    @Column(name = "report_time", length = 50)
    private String reportTime;

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

    @Size(max = 4096)
    @Column(name = "quality_goal", length = 50)
    private String qualityGoal;

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "evaluate_criteria_data_document",
               joinColumns = @JoinColumn(name = "evaluate_criteria_data_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("evaluateCriteriaData")
    private EvaluateCriteriaTraining evaluateCriteriaTraining;

    @ManyToOne
    @JsonIgnoreProperties("evaluateCriteriaData")
    private OrganizationChart organizationChart;

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

    public EvaluateCriteriaData title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Float getFirstNumber() {
        return firstNumber;
    }

    public EvaluateCriteriaData firstNumber(Float firstNumber) {
        this.firstNumber = firstNumber;
        return this;
    }

    public void setFirstNumber(Float firstNumber) {
        this.firstNumber = firstNumber;
    }

    public Float getSecondNumber() {
        return secondNumber;
    }

    public EvaluateCriteriaData secondNumber(Float secondNumber) {
        this.secondNumber = secondNumber;
        return this;
    }

    public void setSecondNumber(Float secondNumber) {
        this.secondNumber = secondNumber;
    }

    public Float getThirdNumber() {
        return thirdNumber;
    }

    public EvaluateCriteriaData thirdNumber(Float thirdNumber) {
        this.thirdNumber = thirdNumber;
        return this;
    }

    public void setThirdNumber(Float thirdNumber) {
        this.thirdNumber = thirdNumber;
    }

    public Float getMeasureCalc() {
        return measureCalc;
    }

    public EvaluateCriteriaData measureCalc(Float measureCalc) {
        this.measureCalc = measureCalc;
        return this;
    }

    public void setMeasureCalc(Float measureCalc) {
        this.measureCalc = measureCalc;
    }

    public Float getMeasureCalcPercent() {
        return measureCalcPercent;
    }

    public EvaluateCriteriaData measureCalcPercent(Float measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
        return this;
    }

    public void setMeasureCalcPercent(Float measureCalcPercent) {
        this.measureCalcPercent = measureCalcPercent;
    }

    public Float getResult() {
        return result;
    }

    public EvaluateCriteriaData result(Float result) {
        this.result = result;
        return this;
    }

    public void setResult(Float result) {
        this.result = result;
    }

    public Float getResultPercent() {
        return resultPercent;
    }

    public EvaluateCriteriaData resultPercent(Float resultPercent) {
        this.resultPercent = resultPercent;
        return this;
    }

    public void setResultPercent(Float resultPercent) {
        this.resultPercent = resultPercent;
    }

    public Float getYear() {
        return year;
    }

    public EvaluateCriteriaData year(Float year) {
        this.year = year;
        return this;
    }

    public void setYear(Float year) {
        this.year = year;
    }

    public Float getMonth() {
        return month;
    }

    public EvaluateCriteriaData month(Float month) {
        this.month = month;
        return this;
    }

    public void setMonth(Float month) {
        this.month = month;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public EvaluateCriteriaData fileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
        return this;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }

    public String getReportTime() {
        return reportTime;
    }

    public EvaluateCriteriaData reportTime(String reportTime) {
        this.reportTime = reportTime;
        return this;
    }

    public void setReportTime(String reportTime) {
        this.reportTime = reportTime;
    }

    public String getDescription() {
        return description;
    }

    public EvaluateCriteriaData description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EvaluateCriteriaData createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EvaluateCriteriaData createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EvaluateCriteriaData modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EvaluateCriteriaData modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public EvaluateCriteriaData guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getQualityGoal() {
        return qualityGoal;
    }

    public EvaluateCriteriaData qualityGoal(String qualityGoal) {
        this.qualityGoal = qualityGoal;
        return this;
    }

    public void setQualityGoal(String qualityGoal) {
        this.qualityGoal = qualityGoal;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public EvaluateCriteriaData documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public EvaluateCriteriaData addDocument(Document document) {
        this.documents.add(document);
        document.getEvaluateCriteriaData().add(this);
        return this;
    }

    public EvaluateCriteriaData removeDocument(Document document) {
        this.documents.remove(document);
        document.getEvaluateCriteriaData().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public EvaluateCriteriaTraining getEvaluateCriteriaTraining() {
        return evaluateCriteriaTraining;
    }

    public EvaluateCriteriaData evaluateCriteriaTraining(EvaluateCriteriaTraining evaluateCriteriaTraining) {
        this.evaluateCriteriaTraining = evaluateCriteriaTraining;
        return this;
    }

    public void setEvaluateCriteriaTraining(EvaluateCriteriaTraining evaluateCriteriaTraining) {
        this.evaluateCriteriaTraining = evaluateCriteriaTraining;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public EvaluateCriteriaData organizationChart(OrganizationChart organizationChart) {
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
        EvaluateCriteriaData evaluateCriteriaData = (EvaluateCriteriaData) o;
        if (evaluateCriteriaData.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), evaluateCriteriaData.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EvaluateCriteriaData{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", firstNumber=" + getFirstNumber() +
            ", secondNumber=" + getSecondNumber() +
            ", thirdNumber=" + getThirdNumber() +
            ", measureCalc=" + getMeasureCalc() +
            ", measureCalcPercent=" + getMeasureCalcPercent() +
            ", result=" + getResult() +
            ", resultPercent=" + getResultPercent() +
            ", year=" + getYear() +
            ", month=" + getMonth() +
            ", fileDoc='" + getFileDoc() + "'" +
            ", reportTime='" + getReportTime() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
