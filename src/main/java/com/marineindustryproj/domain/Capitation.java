package com.marineindustryproj.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A Capitation.
 */
@Entity
@Table(name = "capitation")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Capitation implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 4096)
    @Column(name = "title", length = 4096)
    private String title;

    @Size(max = 50)
    @Column(name = "code", length = 50)
    private String code;

    @Size(max = 4096)
    @Column(name = "description", length = 4096)
    private String description;

    @NotNull
    @Column(name = "employee_maximum_allowable_person_hours", nullable = false)
    private Integer employeeMaximumAllowablePersonHours;

    @NotNull
    @Column(name = "boss_maximum_allowable_person_hours", nullable = false)
    private Integer bossMaximumAllowablePersonHours;

    @NotNull
    @Column(name = "employee_maximum_allowable_person_costs", nullable = false)
    private Integer employeeMaximumAllowablePersonCosts;

    @NotNull
    @Column(name = "boss_maximum_allowable_person_costs", nullable = false)
    private Integer bossMaximumAllowablePersonCosts;

    @Column(name = "capitation_year")
    private Integer capitationYear;

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

    public Capitation title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public Capitation code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public Capitation description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getEmployeeMaximumAllowablePersonHours() {
        return employeeMaximumAllowablePersonHours;
    }

    public Capitation employeeMaximumAllowablePersonHours(Integer employeeMaximumAllowablePersonHours) {
        this.employeeMaximumAllowablePersonHours = employeeMaximumAllowablePersonHours;
        return this;
    }

    public void setEmployeeMaximumAllowablePersonHours(Integer employeeMaximumAllowablePersonHours) {
        this.employeeMaximumAllowablePersonHours = employeeMaximumAllowablePersonHours;
    }

    public Integer getBossMaximumAllowablePersonHours() {
        return bossMaximumAllowablePersonHours;
    }

    public Capitation bossMaximumAllowablePersonHours(Integer bossMaximumAllowablePersonHours) {
        this.bossMaximumAllowablePersonHours = bossMaximumAllowablePersonHours;
        return this;
    }

    public void setBossMaximumAllowablePersonHours(Integer bossMaximumAllowablePersonHours) {
        this.bossMaximumAllowablePersonHours = bossMaximumAllowablePersonHours;
    }

    public Integer getEmployeeMaximumAllowablePersonCosts() {
        return employeeMaximumAllowablePersonCosts;
    }

    public Capitation employeeMaximumAllowablePersonCosts(Integer employeeMaximumAllowablePersonCosts) {
        this.employeeMaximumAllowablePersonCosts = employeeMaximumAllowablePersonCosts;
        return this;
    }

    public void setEmployeeMaximumAllowablePersonCosts(Integer employeeMaximumAllowablePersonCosts) {
        this.employeeMaximumAllowablePersonCosts = employeeMaximumAllowablePersonCosts;
    }

    public Integer getBossMaximumAllowablePersonCosts() {
        return bossMaximumAllowablePersonCosts;
    }

    public Capitation bossMaximumAllowablePersonCosts(Integer bossMaximumAllowablePersonCosts) {
        this.bossMaximumAllowablePersonCosts = bossMaximumAllowablePersonCosts;
        return this;
    }

    public void setBossMaximumAllowablePersonCosts(Integer bossMaximumAllowablePersonCosts) {
        this.bossMaximumAllowablePersonCosts = bossMaximumAllowablePersonCosts;
    }

    public Integer getCapitationYear() {
        return capitationYear;
    }

    public Capitation capitationYear(Integer capitationYear) {
        this.capitationYear = capitationYear;
        return this;
    }

    public void setCapitationYear(Integer capitationYear) {
        this.capitationYear = capitationYear;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public Capitation createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public Capitation createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public Capitation modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public Capitation modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
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
        Capitation capitation = (Capitation) o;
        if (capitation.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), capitation.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "Capitation{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", employeeMaximumAllowablePersonHours=" + getEmployeeMaximumAllowablePersonHours() +
            ", bossMaximumAllowablePersonHours=" + getBossMaximumAllowablePersonHours() +
            ", employeeMaximumAllowablePersonCosts=" + getEmployeeMaximumAllowablePersonCosts() +
            ", bossMaximumAllowablePersonCosts=" + getBossMaximumAllowablePersonCosts() +
            ", capitationYear=" + getCapitationYear() +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
