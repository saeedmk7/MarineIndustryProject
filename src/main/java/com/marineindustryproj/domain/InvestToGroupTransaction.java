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
 * A InvestToGroupTransaction.
 */
@Entity
@Table(name = "invest_to_group_transaction")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class InvestToGroupTransaction implements Serializable {

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

    @Size(max = 50)
    @Column(name = "invest_date", length = 50)
    private String investDate;

    @Column(name = "invest_year")
    private Integer investYear;

    @Column(name = "invest_amount")
    private Integer investAmount;

    @Size(max = 4096)
    @Column(name = "check_number", length = 4096)
    private String checkNumber;

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
    @JoinTable(name = "invest_to_group_transaction_document",
               joinColumns = @JoinColumn(name = "invest_to_group_transactions_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("investToGroupTransactions")
    private OrganizationChart organizationChart;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public InvestToGroupTransaction title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public InvestToGroupTransaction description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getInvestDate() {
        return investDate;
    }

    public InvestToGroupTransaction investDate(String investDate) {
        this.investDate = investDate;
        return this;
    }

    public void setInvestDate(String investDate) {
        this.investDate = investDate;
    }

    public Integer getInvestYear() {
        return investYear;
    }

    public InvestToGroupTransaction investYear(Integer investYear) {
        this.investYear = investYear;
        return this;
    }

    public void setInvestYear(Integer investYear) {
        this.investYear = investYear;
    }

    public Integer getInvestAmount() {
        return investAmount;
    }

    public InvestToGroupTransaction investAmount(Integer investAmount) {
        this.investAmount = investAmount;
        return this;
    }

    public void setInvestAmount(Integer investAmount) {
        this.investAmount = investAmount;
    }

    public String getCheckNumber() {
        return checkNumber;
    }

    public InvestToGroupTransaction checkNumber(String checkNumber) {
        this.checkNumber = checkNumber;
        return this;
    }

    public void setCheckNumber(String checkNumber) {
        this.checkNumber = checkNumber;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public InvestToGroupTransaction createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public InvestToGroupTransaction createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public InvestToGroupTransaction modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public InvestToGroupTransaction modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getGuid() {
        return guid;
    }

    public InvestToGroupTransaction guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public InvestToGroupTransaction documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public InvestToGroupTransaction addDocument(Document document) {
        this.documents.add(document);
        document.getInvestToGroupTransactions().add(this);
        return this;
    }

    public InvestToGroupTransaction removeDocument(Document document) {
        this.documents.remove(document);
        document.getInvestToGroupTransactions().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public InvestToGroupTransaction organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        InvestToGroupTransaction investToGroupTransaction = (InvestToGroupTransaction) o;
        if (investToGroupTransaction.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), investToGroupTransaction.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "InvestToGroupTransaction{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", description='" + getDescription() + "'" +
            ", investDate='" + getInvestDate() + "'" +
            ", investYear=" + getInvestYear() +
            ", investAmount=" + getInvestAmount() +
            ", checkNumber='" + getCheckNumber() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", guid='" + getGuid() + "'" +
            "}";
    }
}
