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
 * A CourseType.
 */
@Entity
@Table(name = "course_type")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class CourseType implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Size(max = 1024)
    @Column(name = "description", length = 1024)
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

    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<FinalNiazsanjiReport> finalNiazsanjiReports = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignAndPlanning> designAndPlannings = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiFardi> niazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestNiazsanjiFardi> requestNiazsanjiFardis = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<EducationalHistory> educationalHistories = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<DesignNiazsanji> designNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<JobNiazsanji> jobNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<NiazsanjiOther> niazsanjiOthers = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<RequestOtherNiazsanji> requestOtherNiazsanjis = new HashSet<>();
    @OneToMany(mappedBy = "courseType")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis = new HashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public CourseType title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCode() {
        return code;
    }

    public CourseType code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public CourseType description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public CourseType createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public CourseType createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public CourseType modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public CourseType modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<RequestOrganizationNiazsanji> getRequestOrganizationNiazsanjis() {
        return requestOrganizationNiazsanjis;
    }

    public CourseType requestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
        return this;
    }

    public CourseType addRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.add(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setCourseType(this);
        return this;
    }

    public CourseType removeRequestOrganizationNiazsanji(RequestOrganizationNiazsanji requestOrganizationNiazsanji) {
        this.requestOrganizationNiazsanjis.remove(requestOrganizationNiazsanji);
        requestOrganizationNiazsanji.setCourseType(null);
        return this;
    }

    public void setRequestOrganizationNiazsanjis(Set<RequestOrganizationNiazsanji> requestOrganizationNiazsanjis) {
        this.requestOrganizationNiazsanjis = requestOrganizationNiazsanjis;
    }

    public Set<FinalOrganizationNiazsanji> getFinalOrganizationNiazsanjis() {
        return finalOrganizationNiazsanjis;
    }

    public CourseType finalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
        return this;
    }

    public CourseType addFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.add(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setCourseType(this);
        return this;
    }

    public CourseType removeFinalOrganizationNiazsanji(FinalOrganizationNiazsanji finalOrganizationNiazsanji) {
        this.finalOrganizationNiazsanjis.remove(finalOrganizationNiazsanji);
        finalOrganizationNiazsanji.setCourseType(null);
        return this;
    }

    public void setFinalOrganizationNiazsanjis(Set<FinalOrganizationNiazsanji> finalOrganizationNiazsanjis) {
        this.finalOrganizationNiazsanjis = finalOrganizationNiazsanjis;
    }

    public Set<FinalNiazsanjiReport> getFinalNiazsanjiReports() {
        return finalNiazsanjiReports;
    }

    public CourseType finalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
        return this;
    }

    public CourseType addFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.add(finalNiazsanjiReport);
        finalNiazsanjiReport.setCourseType(this);
        return this;
    }

    public CourseType removeFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReports.remove(finalNiazsanjiReport);
        finalNiazsanjiReport.setCourseType(null);
        return this;
    }

    public void setFinalNiazsanjiReports(Set<FinalNiazsanjiReport> finalNiazsanjiReports) {
        this.finalNiazsanjiReports = finalNiazsanjiReports;
    }

    public Set<DesignAndPlanning> getDesignAndPlannings() {
        return designAndPlannings;
    }

    public CourseType designAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
        return this;
    }

    public CourseType addDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.add(designAndPlanning);
        designAndPlanning.setCourseType(this);
        return this;
    }

    public CourseType removeDesignAndPlanning(DesignAndPlanning designAndPlanning) {
        this.designAndPlannings.remove(designAndPlanning);
        designAndPlanning.setCourseType(null);
        return this;
    }

    public void setDesignAndPlannings(Set<DesignAndPlanning> designAndPlannings) {
        this.designAndPlannings = designAndPlannings;
    }

    public Set<NiazsanjiFardi> getNiazsanjiFardis() {
        return niazsanjiFardis;
    }

    public CourseType niazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
        return this;
    }

    public CourseType addNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.add(niazsanjiFardi);
        niazsanjiFardi.setCourseType(this);
        return this;
    }

    public CourseType removeNiazsanjiFardi(NiazsanjiFardi niazsanjiFardi) {
        this.niazsanjiFardis.remove(niazsanjiFardi);
        niazsanjiFardi.setCourseType(null);
        return this;
    }

    public void setNiazsanjiFardis(Set<NiazsanjiFardi> niazsanjiFardis) {
        this.niazsanjiFardis = niazsanjiFardis;
    }

    public Set<RequestNiazsanjiFardi> getRequestNiazsanjiFardis() {
        return requestNiazsanjiFardis;
    }

    public CourseType requestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
        return this;
    }

    public CourseType addRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.add(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setCourseType(this);
        return this;
    }

    public CourseType removeRequestNiazsanjiFardi(RequestNiazsanjiFardi requestNiazsanjiFardi) {
        this.requestNiazsanjiFardis.remove(requestNiazsanjiFardi);
        requestNiazsanjiFardi.setCourseType(null);
        return this;
    }

    public void setRequestNiazsanjiFardis(Set<RequestNiazsanjiFardi> requestNiazsanjiFardis) {
        this.requestNiazsanjiFardis = requestNiazsanjiFardis;
    }

    public Set<EducationalHistory> getEducationalHistories() {
        return educationalHistories;
    }

    public CourseType educationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
        return this;
    }

    public CourseType addEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.add(educationalHistory);
        educationalHistory.setCourseType(this);
        return this;
    }

    public CourseType removeEducationalHistory(EducationalHistory educationalHistory) {
        this.educationalHistories.remove(educationalHistory);
        educationalHistory.setCourseType(null);
        return this;
    }

    public void setEducationalHistories(Set<EducationalHistory> educationalHistories) {
        this.educationalHistories = educationalHistories;
    }

    public Set<DesignNiazsanji> getDesignNiazsanjis() {
        return designNiazsanjis;
    }

    public CourseType designNiazsanjis(Set<DesignNiazsanji> designNiazsanjis) {
        this.designNiazsanjis = designNiazsanjis;
        return this;
    }

    public CourseType addDesignNiazsanji(DesignNiazsanji designNiazsanji) {
        this.designNiazsanjis.add(designNiazsanji);
        designNiazsanji.setCourseType(this);
        return this;
    }

    public CourseType removeDesignNiazsanji(DesignNiazsanji designNiazsanji) {
        this.designNiazsanjis.remove(designNiazsanji);
        designNiazsanji.setCourseType(null);
        return this;
    }

    public void setDesignNiazsanjis(Set<DesignNiazsanji> designNiazsanjis) {
        this.designNiazsanjis = designNiazsanjis;
    }

    public Set<JobNiazsanji> getJobNiazsanjis() {
        return jobNiazsanjis;
    }

    public CourseType jobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
        return this;
    }

    public CourseType addJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.add(jobNiazsanji);
        jobNiazsanji.setCourseType(this);
        return this;
    }

    public CourseType removeJobNiazsanji(JobNiazsanji jobNiazsanji) {
        this.jobNiazsanjis.remove(jobNiazsanji);
        jobNiazsanji.setCourseType(null);
        return this;
    }

    public void setJobNiazsanjis(Set<JobNiazsanji> jobNiazsanjis) {
        this.jobNiazsanjis = jobNiazsanjis;
    }

    public Set<NiazsanjiOther> getNiazsanjiOthers() {
        return niazsanjiOthers;
    }

    public CourseType niazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
        return this;
    }

    public CourseType addNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.add(niazsanjiOther);
        niazsanjiOther.setCourseType(this);
        return this;
    }

    public CourseType removeNiazsanjiOther(NiazsanjiOther niazsanjiOther) {
        this.niazsanjiOthers.remove(niazsanjiOther);
        niazsanjiOther.setCourseType(null);
        return this;
    }

    public void setNiazsanjiOthers(Set<NiazsanjiOther> niazsanjiOthers) {
        this.niazsanjiOthers = niazsanjiOthers;
    }

    public Set<RequestOtherNiazsanji> getRequestOtherNiazsanjis() {
        return requestOtherNiazsanjis;
    }

    public CourseType requestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
        return this;
    }

    public CourseType addRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.add(requestOtherNiazsanji);
        requestOtherNiazsanji.setCourseType(this);
        return this;
    }

    public CourseType removeRequestOtherNiazsanji(RequestOtherNiazsanji requestOtherNiazsanji) {
        this.requestOtherNiazsanjis.remove(requestOtherNiazsanji);
        requestOtherNiazsanji.setCourseType(null);
        return this;
    }

    public void setRequestOtherNiazsanjis(Set<RequestOtherNiazsanji> requestOtherNiazsanjis) {
        this.requestOtherNiazsanjis = requestOtherNiazsanjis;
    }

    public Set<PrioritizeRequestNiazsanji> getPrioritizeRequestNiazsanjis() {
        return prioritizeRequestNiazsanjis;
    }

    public CourseType prioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
        return this;
    }

    public CourseType addPrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.add(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setCourseType(this);
        return this;
    }

    public CourseType removePrioritizeRequestNiazsanji(PrioritizeRequestNiazsanji prioritizeRequestNiazsanji) {
        this.prioritizeRequestNiazsanjis.remove(prioritizeRequestNiazsanji);
        prioritizeRequestNiazsanji.setCourseType(null);
        return this;
    }

    public void setPrioritizeRequestNiazsanjis(Set<PrioritizeRequestNiazsanji> prioritizeRequestNiazsanjis) {
        this.prioritizeRequestNiazsanjis = prioritizeRequestNiazsanjis;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        CourseType courseType = (CourseType) o;
        if (courseType.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), courseType.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "CourseType{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
