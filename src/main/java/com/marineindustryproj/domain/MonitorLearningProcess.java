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
 * A MonitorLearningProcess.
 */
@Entity
@Table(name = "monitor_learning_process")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MonitorLearningProcess implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 4096)
    @Column(name = "title", length = 4096, nullable = false)
    private String title;

    @Size(max = 4096)
    @Column(name = "code", length = 4096)
    private String code;

    @Size(max = 50)
    @Column(name = "process_date", length = 50)
    private String processDate;

    @Size(max = 4096)
    @Column(name = "classification", length = 4096)
    private String classification;

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

    @OneToMany(mappedBy = "monitorLearningProcess")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MonitorLearningProcessGrade> monitorLearningProcessGrades = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "monitor_learning_process_document",
               joinColumns = @JoinColumn(name = "monitor_learning_processes_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("monitorLearningProcesses")
    private MonitorProcessDuration monitorProcessDuration;

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

    public MonitorLearningProcess title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public MonitorLearningProcess code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProcessDate() {
        return processDate;
    }

    public MonitorLearningProcess processDate(String processDate) {
        this.processDate = processDate;
        return this;
    }

    public void setProcessDate(String processDate) {
        this.processDate = processDate;
    }

    public String getClassification() {
        return classification;
    }

    public MonitorLearningProcess classification(String classification) {
        this.classification = classification;
        return this;
    }

    public void setClassification(String classification) {
        this.classification = classification;
    }

    public String getDescription() {
        return description;
    }

    public MonitorLearningProcess description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MonitorLearningProcess createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MonitorLearningProcess createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MonitorLearningProcess modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MonitorLearningProcess modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public MonitorLearningProcess guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<MonitorLearningProcessGrade> getMonitorLearningProcessGrades() {
        return monitorLearningProcessGrades;
    }

    public MonitorLearningProcess monitorLearningProcessGrades(Set<MonitorLearningProcessGrade> monitorLearningProcessGrades) {
        this.monitorLearningProcessGrades = monitorLearningProcessGrades;
        return this;
    }

    public MonitorLearningProcess addMonitorLearningProcessGrade(MonitorLearningProcessGrade monitorLearningProcessGrade) {
        this.monitorLearningProcessGrades.add(monitorLearningProcessGrade);
        monitorLearningProcessGrade.setMonitorLearningProcess(this);
        return this;
    }

    public MonitorLearningProcess removeMonitorLearningProcessGrade(MonitorLearningProcessGrade monitorLearningProcessGrade) {
        this.monitorLearningProcessGrades.remove(monitorLearningProcessGrade);
        monitorLearningProcessGrade.setMonitorLearningProcess(null);
        return this;
    }

    public void setMonitorLearningProcessGrades(Set<MonitorLearningProcessGrade> monitorLearningProcessGrades) {
        this.monitorLearningProcessGrades = monitorLearningProcessGrades;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public MonitorLearningProcess documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public MonitorLearningProcess addDocument(Document document) {
        this.documents.add(document);
        document.getMonitorLearningProcesses().add(this);
        return this;
    }

    public MonitorLearningProcess removeDocument(Document document) {
        this.documents.remove(document);
        document.getMonitorLearningProcesses().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public MonitorProcessDuration getMonitorProcessDuration() {
        return monitorProcessDuration;
    }

    public MonitorLearningProcess monitorProcessDuration(MonitorProcessDuration monitorProcessDuration) {
        this.monitorProcessDuration = monitorProcessDuration;
        return this;
    }

    public void setMonitorProcessDuration(MonitorProcessDuration monitorProcessDuration) {
        this.monitorProcessDuration = monitorProcessDuration;
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
        MonitorLearningProcess monitorLearningProcess = (MonitorLearningProcess) o;
        if (monitorLearningProcess.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), monitorLearningProcess.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MonitorLearningProcess{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", processDate='" + getProcessDate() + "'" +
            ", classification='" + getClassification() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
