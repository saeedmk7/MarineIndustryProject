package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A JobRecord.
 */
@Entity
@Table(name = "job_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class JobRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 1000)
    @Column(name = "work_organization", length = 1000)
    private String workOrganization;

    @Size(max = 1000)
    @Column(name = "work_section", length = 1000)
    private String workSection;

    @Size(max = 1000)
    @Column(name = "job_responsibility", length = 1000)
    private String jobResponsibility;

    @Size(max = 1000)
    @Column(name = "job_position", length = 1000)
    private String jobPosition;

    @Column(name = "total_hour")
    private Integer totalHour;

    @Size(max = 50)
    @Column(name = "start_date", length = 50)
    private String startDate;

    @Size(max = 50)
    @Column(name = "end_date", length = 50)
    private String endDate;

    @Size(max = 4096)
    @Column(name = "leave_reason", length = 4096)
    private String leaveReason;

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

    @ManyToOne
    @JsonIgnoreProperties("jobRecords")
    private Person person;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getWorkOrganization() {
        return workOrganization;
    }

    public JobRecord workOrganization(String workOrganization) {
        this.workOrganization = workOrganization;
        return this;
    }

    public void setWorkOrganization(String workOrganization) {
        this.workOrganization = workOrganization;
    }

    public String getWorkSection() {
        return workSection;
    }

    public JobRecord workSection(String workSection) {
        this.workSection = workSection;
        return this;
    }

    public void setWorkSection(String workSection) {
        this.workSection = workSection;
    }

    public String getJobResponsibility() {
        return jobResponsibility;
    }

    public JobRecord jobResponsibility(String jobResponsibility) {
        this.jobResponsibility = jobResponsibility;
        return this;
    }

    public void setJobResponsibility(String jobResponsibility) {
        this.jobResponsibility = jobResponsibility;
    }

    public String getJobPosition() {
        return jobPosition;
    }

    public JobRecord jobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
        return this;
    }

    public void setJobPosition(String jobPosition) {
        this.jobPosition = jobPosition;
    }

    public Integer getTotalHour() {
        return totalHour;
    }

    public JobRecord totalHour(Integer totalHour) {
        this.totalHour = totalHour;
        return this;
    }

    public void setTotalHour(Integer totalHour) {
        this.totalHour = totalHour;
    }

    public String getStartDate() {
        return startDate;
    }

    public JobRecord startDate(String startDate) {
        this.startDate = startDate;
        return this;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public JobRecord endDate(String endDate) {
        this.endDate = endDate;
        return this;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getLeaveReason() {
        return leaveReason;
    }

    public JobRecord leaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
        return this;
    }

    public void setLeaveReason(String leaveReason) {
        this.leaveReason = leaveReason;
    }

    public String getDescription() {
        return description;
    }

    public JobRecord description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public JobRecord createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public JobRecord createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public JobRecord modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public JobRecord modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public JobRecord guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Person getPerson() {
        return person;
    }

    public JobRecord person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
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
        JobRecord jobRecord = (JobRecord) o;
        if (jobRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobRecord{" +
            "id=" + getId() +
            ", workOrganization='" + getWorkOrganization() + "'" +
            ", workSection='" + getWorkSection() + "'" +
            ", jobResponsibility='" + getJobResponsibility() + "'" +
            ", jobPosition='" + getJobPosition() + "'" +
            ", totalHour=" + getTotalHour() +
            ", startDate='" + getStartDate() + "'" +
            ", endDate='" + getEndDate() + "'" +
            ", leaveReason='" + getLeaveReason() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
