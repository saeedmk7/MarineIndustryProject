package com.marineindustryproj.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
 * A ReportGenerator.
 */
@Entity
@Table(name = "report_generator")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class ReportGenerator implements Serializable {

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
    @Column(name = "description", length = 4096)
    private String description;

    @NotNull
    @Size(max = 9999999)
    @Column(name = "search_params", length = 9999999, nullable = false)
    private String searchParams;

    @NotNull
    @Size(max = 9999999)
    @Column(name = "column_names", length = 9999999, nullable = false)
    private String columnNames;

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

    @OneToMany(mappedBy = "reportGenerator")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<ReportGeneratorAuthority> reportGeneratorAuthorities = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "report_generator_organization_chart",
               joinColumns = @JoinColumn(name = "report_generators_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "organization_charts_id", referencedColumnName = "id"))
    private Set<OrganizationChart> organizationCharts = new HashSet<>();


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public ReportGenerator title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public ReportGenerator description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getSearchParams() {
        return searchParams;
    }

    public ReportGenerator searchParams(String searchParams) {
        this.searchParams = searchParams;
        return this;
    }

    public void setSearchParams(String searchParams) {
        this.searchParams = searchParams;
    }

    public String getColumnNames() {
        return columnNames;
    }

    public ReportGenerator columnNames(String columnNames) {
        this.columnNames = columnNames;
        return this;
    }

    public void setColumnNames(String columnNames) {
        this.columnNames = columnNames;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public ReportGenerator createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public ReportGenerator createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public ReportGenerator modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public ReportGenerator modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public ReportGenerator guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<ReportGeneratorAuthority> getReportGeneratorAuthorities() {
        return reportGeneratorAuthorities;
    }

    public ReportGenerator reportGeneratorAuthorities(Set<ReportGeneratorAuthority> reportGeneratorAuthorities) {
        this.reportGeneratorAuthorities = reportGeneratorAuthorities;
        return this;
    }

    public ReportGenerator addReportGeneratorAuthority(ReportGeneratorAuthority reportGeneratorAuthority) {
        this.reportGeneratorAuthorities.add(reportGeneratorAuthority);
        reportGeneratorAuthority.setReportGenerator(this);
        return this;
    }

    public ReportGenerator removeReportGeneratorAuthority(ReportGeneratorAuthority reportGeneratorAuthority) {
        this.reportGeneratorAuthorities.remove(reportGeneratorAuthority);
        reportGeneratorAuthority.setReportGenerator(null);
        return this;
    }

    public void setReportGeneratorAuthorities(Set<ReportGeneratorAuthority> reportGeneratorAuthorities) {
        this.reportGeneratorAuthorities = reportGeneratorAuthorities;
    }

    public Set<OrganizationChart> getOrganizationCharts() {
        return organizationCharts;
    }

    public ReportGenerator organizationCharts(Set<OrganizationChart> organizationCharts) {
        this.organizationCharts = organizationCharts;
        return this;
    }

    public ReportGenerator addOrganizationChart(OrganizationChart organizationChart) {
        this.organizationCharts.add(organizationChart);
        organizationChart.getReportGenerators().add(this);
        return this;
    }

    public ReportGenerator removeOrganizationChart(OrganizationChart organizationChart) {
        this.organizationCharts.remove(organizationChart);
        organizationChart.getReportGenerators().remove(this);
        return this;
    }

    public void setOrganizationCharts(Set<OrganizationChart> organizationCharts) {
        this.organizationCharts = organizationCharts;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ReportGenerator reportGenerator = (ReportGenerator) o;
        if (reportGenerator.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), reportGenerator.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "ReportGenerator{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", searchParams='" + getSearchParams() + "'" +
            ", columnNames='" + getColumnNames() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
