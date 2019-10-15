package com.marineindustryproj.domain;

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
 * A ForceRunningPercent.
 */
@Entity
@Table(name = "force_running_percent")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ForceRunningPercent implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "title", length = 4096)
    private String title;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @Column(name = "percent_amount")
    private Integer percentAmount;

    @Column(name = "run_month")
    private Integer runMonth;

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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "force_running_percent_organization_chart",
               joinColumns = @JoinColumn(name = "force_running_percents_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "organization_charts_id", referencedColumnName = "id"))
    private Set<OrganizationChart> organizationCharts = new HashSet<>();

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

    public ForceRunningPercent title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ForceRunningPercent description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPercentAmount() {
        return percentAmount;
    }

    public ForceRunningPercent percentAmount(Integer percentAmount) {
        this.percentAmount = percentAmount;
        return this;
    }

    public void setPercentAmount(Integer percentAmount) {
        this.percentAmount = percentAmount;
    }

    public Integer getRunMonth() {
        return runMonth;
    }

    public ForceRunningPercent runMonth(Integer runMonth) {
        this.runMonth = runMonth;
        return this;
    }

    public void setRunMonth(Integer runMonth) {
        this.runMonth = runMonth;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public ForceRunningPercent createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ForceRunningPercent createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public ForceRunningPercent modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public ForceRunningPercent modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public ForceRunningPercent guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<OrganizationChart> getOrganizationCharts() {
        return organizationCharts;
    }

    public ForceRunningPercent organizationCharts(Set<OrganizationChart> organizationCharts) {
        this.organizationCharts = organizationCharts;
        return this;
    }

    public ForceRunningPercent addOrganizationChart(OrganizationChart organizationChart) {
        this.organizationCharts.add(organizationChart);
        organizationChart.getForceRunningPercents().add(this);
        return this;
    }

    public ForceRunningPercent removeOrganizationChart(OrganizationChart organizationChart) {
        this.organizationCharts.remove(organizationChart);
        organizationChart.getForceRunningPercents().remove(this);
        return this;
    }

    public void setOrganizationCharts(Set<OrganizationChart> organizationCharts) {
        this.organizationCharts = organizationCharts;
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
        ForceRunningPercent forceRunningPercent = (ForceRunningPercent) o;
        if (forceRunningPercent.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), forceRunningPercent.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ForceRunningPercent{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", percentAmount=" + getPercentAmount() +
            ", runMonth=" + getRunMonth() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
