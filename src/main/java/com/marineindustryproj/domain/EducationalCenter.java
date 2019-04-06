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
 * A EducationalCenter.
 */
@Entity
@Table(name = "educational_center")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class EducationalCenter implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "name", length = 100, nullable = false)
    private String name;

    @Size(max = 50)
    @Column(name = "ceo", length = 50)
    private String ceo;

    @Size(max = 50)
    @Column(name = "connection_person", length = 50)
    private String connectionPerson;

    @Size(max = 100)
    @Column(name = "telephone", length = 100)
    private String telephone;

    @Size(max = 50)
    @Column(name = "fax", length = 50)
    private String fax;

    @Size(max = 1024)
    @Column(name = "address", length = 1024)
    private String address;

    @Size(max = 50)
    @Column(name = "email", length = 50)
    private String email;

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

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_center_activity_area",
               joinColumns = @JoinColumn(name = "educational_centers_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "activity_areas_id", referencedColumnName = "id"))
    private Set<ActivityArea> activityAreas = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "educational_center_document",
               joinColumns = @JoinColumn(name = "educational_centers_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToMany(mappedBy = "educationalCenters")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<EducationalModule> educationalModules = new HashSet<>();

    @ManyToMany(mappedBy = "educationalCenters")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JsonIgnore
    private Set<RequestEducationalModule> requestEducationalModules = new HashSet<>();

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public EducationalCenter name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCeo() {
        return ceo;
    }

    public EducationalCenter ceo(String ceo) {
        this.ceo = ceo;
        return this;
    }

    public void setCeo(String ceo) {
        this.ceo = ceo;
    }

    public String getConnectionPerson() {
        return connectionPerson;
    }

    public EducationalCenter connectionPerson(String connectionPerson) {
        this.connectionPerson = connectionPerson;
        return this;
    }

    public void setConnectionPerson(String connectionPerson) {
        this.connectionPerson = connectionPerson;
    }

    public String getTelephone() {
        return telephone;
    }

    public EducationalCenter telephone(String telephone) {
        this.telephone = telephone;
        return this;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getFax() {
        return fax;
    }

    public EducationalCenter fax(String fax) {
        this.fax = fax;
        return this;
    }

    public void setFax(String fax) {
        this.fax = fax;
    }

    public String getAddress() {
        return address;
    }

    public EducationalCenter address(String address) {
        this.address = address;
        return this;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public EducationalCenter email(String email) {
        this.email = email;
        return this;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public EducationalCenter createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public EducationalCenter createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public EducationalCenter modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public EducationalCenter modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public EducationalCenter archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public EducationalCenter archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public EducationalCenter archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public EducationalCenter status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<ActivityArea> getActivityAreas() {
        return activityAreas;
    }

    public EducationalCenter activityAreas(Set<ActivityArea> activityAreas) {
        this.activityAreas = activityAreas;
        return this;
    }

    public EducationalCenter addActivityArea(ActivityArea activityArea) {
        this.activityAreas.add(activityArea);
        activityArea.getEducationalCenters().add(this);
        return this;
    }

    public EducationalCenter removeActivityArea(ActivityArea activityArea) {
        this.activityAreas.remove(activityArea);
        activityArea.getEducationalCenters().remove(this);
        return this;
    }

    public void setActivityAreas(Set<ActivityArea> activityAreas) {
        this.activityAreas = activityAreas;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public EducationalCenter documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public EducationalCenter addDocument(Document document) {
        this.documents.add(document);
        document.getEducationalCenters().add(this);
        return this;
    }

    public EducationalCenter removeDocument(Document document) {
        this.documents.remove(document);
        document.getEducationalCenters().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Set<EducationalModule> getEducationalModules() {
        return educationalModules;
    }

    public EducationalCenter educationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
        return this;
    }

    public EducationalCenter addEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.add(educationalModule);
        educationalModule.getEducationalCenters().add(this);
        return this;
    }

    public EducationalCenter removeEducationalModule(EducationalModule educationalModule) {
        this.educationalModules.remove(educationalModule);
        educationalModule.getEducationalCenters().remove(this);
        return this;
    }

    public void setEducationalModules(Set<EducationalModule> educationalModules) {
        this.educationalModules = educationalModules;
    }

    public Set<RequestEducationalModule> getRequestEducationalModules() {
        return requestEducationalModules;
    }

    public EducationalCenter requestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
        return this;
    }

    public EducationalCenter addRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.add(requestEducationalModule);
        requestEducationalModule.getEducationalCenters().add(this);
        return this;
    }

    public EducationalCenter removeRequestEducationalModule(RequestEducationalModule requestEducationalModule) {
        this.requestEducationalModules.remove(requestEducationalModule);
        requestEducationalModule.getEducationalCenters().remove(this);
        return this;
    }

    public void setRequestEducationalModules(Set<RequestEducationalModule> requestEducationalModules) {
        this.requestEducationalModules = requestEducationalModules;
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
        EducationalCenter educationalCenter = (EducationalCenter) o;
        if (educationalCenter.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalCenter.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalCenter{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", ceo='" + getCeo() + "'" +
            ", connectionPerson='" + getConnectionPerson() + "'" +
            ", telephone='" + getTelephone() + "'" +
            ", fax='" + getFax() + "'" +
            ", address='" + getAddress() + "'" +
            ", email='" + getEmail() + "'" +
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
