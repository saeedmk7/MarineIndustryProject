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
 * A DesignAndPlanning.
 */
@Entity
@Table(name = "design_and_planning")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class DesignAndPlanning implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "direct_cost", nullable = false)
    private Integer directCost;

    @Size(max = 4096)
    @Column(name = "direct_cost_description", length = 4096)
    private String directCostDescription;

    @NotNull
    @Column(name = "undirect_cost", nullable = false)
    private Integer undirectCost;

    @Size(max = 4096)
    @Column(name = "undirect_cost_description", length = 4096)
    private String undirectCostDescription;

    @NotNull
    @Column(name = "step", nullable = false)
    private Integer step;

    @NotNull
    @Column(name = "finished", nullable = false)
    private Boolean finished;

    @Size(max = 50)
    @Column(name = "finished_user_login", length = 50)
    private String finishedUserLogin;

    @Column(name = "finished_date")
    private ZonedDateTime finishedDate;

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
    @JoinTable(name = "design_and_planning_person",
               joinColumns = @JoinColumn(name = "design_and_plannings_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "people_id", referencedColumnName = "id"))
    private Set<Person> people = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "design_and_planning_document",
               joinColumns = @JoinColumn(name = "design_and_plannings_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private FinalNiazsanjiReport finalNiazsanjiReport;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private MahiatCourse mahiatCourse;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private CourseType courseType;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private TeachType teachType;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private CourseLocation courseLocation;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private ConditionsOfParticipant conditionsOfParticipant;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private EffectivenessLevel effectivenessLevel;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private ToolsAndFacility toolsAndFacility;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private TeachingApproach teachingApproach;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private TeachTechnique teachTechnique;

    @ManyToOne
    @JsonIgnoreProperties("designAndPlannings")
    private EffectivenessIndex effectivenessIndex;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getDirectCost() {
        return directCost;
    }

    public DesignAndPlanning directCost(Integer directCost) {
        this.directCost = directCost;
        return this;
    }

    public void setDirectCost(Integer directCost) {
        this.directCost = directCost;
    }

    public String getDirectCostDescription() {
        return directCostDescription;
    }

    public DesignAndPlanning directCostDescription(String directCostDescription) {
        this.directCostDescription = directCostDescription;
        return this;
    }

    public void setDirectCostDescription(String directCostDescription) {
        this.directCostDescription = directCostDescription;
    }

    public Integer getUndirectCost() {
        return undirectCost;
    }

    public DesignAndPlanning undirectCost(Integer undirectCost) {
        this.undirectCost = undirectCost;
        return this;
    }

    public void setUndirectCost(Integer undirectCost) {
        this.undirectCost = undirectCost;
    }

    public String getUndirectCostDescription() {
        return undirectCostDescription;
    }

    public DesignAndPlanning undirectCostDescription(String undirectCostDescription) {
        this.undirectCostDescription = undirectCostDescription;
        return this;
    }

    public void setUndirectCostDescription(String undirectCostDescription) {
        this.undirectCostDescription = undirectCostDescription;
    }

    public Integer getStep() {
        return step;
    }

    public DesignAndPlanning step(Integer step) {
        this.step = step;
        return this;
    }

    public void setStep(Integer step) {
        this.step = step;
    }

    public Boolean isFinished() {
        return finished;
    }

    public DesignAndPlanning finished(Boolean finished) {
        this.finished = finished;
        return this;
    }

    public void setFinished(Boolean finished) {
        this.finished = finished;
    }

    public String getFinishedUserLogin() {
        return finishedUserLogin;
    }

    public DesignAndPlanning finishedUserLogin(String finishedUserLogin) {
        this.finishedUserLogin = finishedUserLogin;
        return this;
    }

    public void setFinishedUserLogin(String finishedUserLogin) {
        this.finishedUserLogin = finishedUserLogin;
    }

    public ZonedDateTime getFinishedDate() {
        return finishedDate;
    }

    public DesignAndPlanning finishedDate(ZonedDateTime finishedDate) {
        this.finishedDate = finishedDate;
        return this;
    }

    public void setFinishedDate(ZonedDateTime finishedDate) {
        this.finishedDate = finishedDate;
    }

    public String getDescription() {
        return description;
    }

    public DesignAndPlanning description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public DesignAndPlanning createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public DesignAndPlanning createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public DesignAndPlanning modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public DesignAndPlanning modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public DesignAndPlanning archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public DesignAndPlanning archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public DesignAndPlanning archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getStatus() {
        return status;
    }

    public DesignAndPlanning status(Integer status) {
        this.status = status;
        return this;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Set<Person> getPeople() {
        return people;
    }

    public DesignAndPlanning people(Set<Person> people) {
        this.people = people;
        return this;
    }

    public DesignAndPlanning addPerson(Person person) {
        this.people.add(person);
        person.getDesignAndPlannings().add(this);
        return this;
    }

    public DesignAndPlanning removePerson(Person person) {
        this.people.remove(person);
        person.getDesignAndPlannings().remove(this);
        return this;
    }

    public void setPeople(Set<Person> people) {
        this.people = people;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public DesignAndPlanning documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public DesignAndPlanning addDocument(Document document) {
        this.documents.add(document);
        document.getDesignAndPlannings().add(this);
        return this;
    }

    public DesignAndPlanning removeDocument(Document document) {
        this.documents.remove(document);
        document.getDesignAndPlannings().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public FinalNiazsanjiReport getFinalNiazsanjiReport() {
        return finalNiazsanjiReport;
    }

    public DesignAndPlanning finalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
        return this;
    }

    public void setFinalNiazsanjiReport(FinalNiazsanjiReport finalNiazsanjiReport) {
        this.finalNiazsanjiReport = finalNiazsanjiReport;
    }

    public MahiatCourse getMahiatCourse() {
        return mahiatCourse;
    }

    public DesignAndPlanning mahiatCourse(MahiatCourse mahiatCourse) {
        this.mahiatCourse = mahiatCourse;
        return this;
    }

    public void setMahiatCourse(MahiatCourse mahiatCourse) {
        this.mahiatCourse = mahiatCourse;
    }

    public CourseType getCourseType() {
        return courseType;
    }

    public DesignAndPlanning courseType(CourseType courseType) {
        this.courseType = courseType;
        return this;
    }

    public void setCourseType(CourseType courseType) {
        this.courseType = courseType;
    }

    public TeachType getTeachType() {
        return teachType;
    }

    public DesignAndPlanning teachType(TeachType teachType) {
        this.teachType = teachType;
        return this;
    }

    public void setTeachType(TeachType teachType) {
        this.teachType = teachType;
    }

    public CourseLocation getCourseLocation() {
        return courseLocation;
    }

    public DesignAndPlanning courseLocation(CourseLocation courseLocation) {
        this.courseLocation = courseLocation;
        return this;
    }

    public void setCourseLocation(CourseLocation courseLocation) {
        this.courseLocation = courseLocation;
    }

    public ConditionsOfParticipant getConditionsOfParticipant() {
        return conditionsOfParticipant;
    }

    public DesignAndPlanning conditionsOfParticipant(ConditionsOfParticipant conditionsOfParticipant) {
        this.conditionsOfParticipant = conditionsOfParticipant;
        return this;
    }

    public void setConditionsOfParticipant(ConditionsOfParticipant conditionsOfParticipant) {
        this.conditionsOfParticipant = conditionsOfParticipant;
    }

    public EffectivenessLevel getEffectivenessLevel() {
        return effectivenessLevel;
    }

    public DesignAndPlanning effectivenessLevel(EffectivenessLevel effectivenessLevel) {
        this.effectivenessLevel = effectivenessLevel;
        return this;
    }

    public void setEffectivenessLevel(EffectivenessLevel effectivenessLevel) {
        this.effectivenessLevel = effectivenessLevel;
    }

    public ToolsAndFacility getToolsAndFacility() {
        return toolsAndFacility;
    }

    public DesignAndPlanning toolsAndFacility(ToolsAndFacility toolsAndFacility) {
        this.toolsAndFacility = toolsAndFacility;
        return this;
    }

    public void setToolsAndFacility(ToolsAndFacility toolsAndFacility) {
        this.toolsAndFacility = toolsAndFacility;
    }

    public TeachingApproach getTeachingApproach() {
        return teachingApproach;
    }

    public DesignAndPlanning teachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproach = teachingApproach;
        return this;
    }

    public void setTeachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproach = teachingApproach;
    }

    public TeachTechnique getTeachTechnique() {
        return teachTechnique;
    }

    public DesignAndPlanning teachTechnique(TeachTechnique teachTechnique) {
        this.teachTechnique = teachTechnique;
        return this;
    }

    public void setTeachTechnique(TeachTechnique teachTechnique) {
        this.teachTechnique = teachTechnique;
    }

    public EffectivenessIndex getEffectivenessIndex() {
        return effectivenessIndex;
    }

    public DesignAndPlanning effectivenessIndex(EffectivenessIndex effectivenessIndex) {
        this.effectivenessIndex = effectivenessIndex;
        return this;
    }

    public void setEffectivenessIndex(EffectivenessIndex effectivenessIndex) {
        this.effectivenessIndex = effectivenessIndex;
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
        DesignAndPlanning designAndPlanning = (DesignAndPlanning) o;
        if (designAndPlanning.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), designAndPlanning.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "DesignAndPlanning{" +
            "id=" + getId() +
            ", directCost=" + getDirectCost() +
            ", directCostDescription='" + getDirectCostDescription() + "'" +
            ", undirectCost=" + getUndirectCost() +
            ", undirectCostDescription='" + getUndirectCostDescription() + "'" +
            ", step=" + getStep() +
            ", finished='" + isFinished() + "'" +
            ", finishedUserLogin='" + getFinishedUserLogin() + "'" +
            ", finishedDate='" + getFinishedDate() + "'" +
            ", description='" + getDescription() + "'" +
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
