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
 * A PreJobNiazsanjiCompetency.
 */
@Entity
@Table(name = "pre_job_niazsanji_competency")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class PreJobNiazsanjiCompetency implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Size(max = 100)
    @Column(name = "title", length = 100, nullable = false)
    private String title;

    @NotNull
    @Column(name = "need_to_improve", nullable = false)
    private Integer needToImprove;

    @Size(max = 4096)
    @Column(name = "need_to_improve_description", length = 4096)
    private String needToImproveDescription;

    @Size(max = 4096)
    @Column(name = "fix_competency_deficiency_description", length = 4096)
    private String fixCompetencyDeficiencyDescription;

    @Size(max = 100)
    @Column(name = "educational_module_text", length = 100)
    private String educationalModuleText;

    @Column(name = "sum_score")
    private Integer sumScore;

    @Column(name = "priority")
    private Integer priority;

    @NotNull
    @Column(name = "selected", nullable = false)
    private Boolean selected;

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

    @OneToMany(mappedBy = "preJobNiazsanjiCompetency")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<PriorityCriteriaValue> priorityCriteriaValues = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "pre_job_niazsanji_competency_teaching_approach",
               joinColumns = @JoinColumn(name = "pre_job_niazsanji_competencies_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "teaching_approaches_id", referencedColumnName = "id"))
    private Set<TeachingApproach> teachingApproaches = new HashSet<>();

    @ManyToOne
    @JsonIgnoreProperties("preJobNiazsanjiCompetencies")
    private FixCompetencyDeficiency fixCompetencyDeficiency;

    @ManyToOne
    @JsonIgnoreProperties("preJobNiazsanjiCompetencies")
    private EducationalModule educationalModule;

    @ManyToOne
    @JsonIgnoreProperties("preJobNiazsanjiCompetencies")
    private PreJobNiazsanji preJobNiazsanji;

    @ManyToOne
    @JsonIgnoreProperties("preJobNiazsanjiCompetencies")
    private Competency competency;

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

    public PreJobNiazsanjiCompetency title(String title) {
        this.title = title;
        return this;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getNeedToImprove() {
        return needToImprove;
    }

    public PreJobNiazsanjiCompetency needToImprove(Integer needToImprove) {
        this.needToImprove = needToImprove;
        return this;
    }

    public void setNeedToImprove(Integer needToImprove) {
        this.needToImprove = needToImprove;
    }

    public String getNeedToImproveDescription() {
        return needToImproveDescription;
    }

    public PreJobNiazsanjiCompetency needToImproveDescription(String needToImproveDescription) {
        this.needToImproveDescription = needToImproveDescription;
        return this;
    }

    public void setNeedToImproveDescription(String needToImproveDescription) {
        this.needToImproveDescription = needToImproveDescription;
    }

    public String getFixCompetencyDeficiencyDescription() {
        return fixCompetencyDeficiencyDescription;
    }

    public PreJobNiazsanjiCompetency fixCompetencyDeficiencyDescription(String fixCompetencyDeficiencyDescription) {
        this.fixCompetencyDeficiencyDescription = fixCompetencyDeficiencyDescription;
        return this;
    }

    public void setFixCompetencyDeficiencyDescription(String fixCompetencyDeficiencyDescription) {
        this.fixCompetencyDeficiencyDescription = fixCompetencyDeficiencyDescription;
    }

    public String getEducationalModuleText() {
        return educationalModuleText;
    }

    public PreJobNiazsanjiCompetency educationalModuleText(String educationalModuleText) {
        this.educationalModuleText = educationalModuleText;
        return this;
    }

    public void setEducationalModuleText(String educationalModuleText) {
        this.educationalModuleText = educationalModuleText;
    }

    public Integer getSumScore() {
        return sumScore;
    }

    public PreJobNiazsanjiCompetency sumScore(Integer sumScore) {
        this.sumScore = sumScore;
        return this;
    }

    public void setSumScore(Integer sumScore) {
        this.sumScore = sumScore;
    }

    public Integer getPriority() {
        return priority;
    }

    public PreJobNiazsanjiCompetency priority(Integer priority) {
        this.priority = priority;
        return this;
    }

    public void setPriority(Integer priority) {
        this.priority = priority;
    }

    public Boolean isSelected() {
        return selected;
    }

    public PreJobNiazsanjiCompetency selected(Boolean selected) {
        this.selected = selected;
        return this;
    }

    public void setSelected(Boolean selected) {
        this.selected = selected;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public PreJobNiazsanjiCompetency createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public PreJobNiazsanjiCompetency createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public PreJobNiazsanjiCompetency modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public PreJobNiazsanjiCompetency modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Set<PriorityCriteriaValue> getPriorityCriteriaValues() {
        return priorityCriteriaValues;
    }

    public PreJobNiazsanjiCompetency priorityCriteriaValues(Set<PriorityCriteriaValue> priorityCriteriaValues) {
        this.priorityCriteriaValues = priorityCriteriaValues;
        return this;
    }

    public PreJobNiazsanjiCompetency addPriorityCriteriaValue(PriorityCriteriaValue priorityCriteriaValue) {
        this.priorityCriteriaValues.add(priorityCriteriaValue);
        priorityCriteriaValue.setPreJobNiazsanjiCompetency(this);
        return this;
    }

    public PreJobNiazsanjiCompetency removePriorityCriteriaValue(PriorityCriteriaValue priorityCriteriaValue) {
        this.priorityCriteriaValues.remove(priorityCriteriaValue);
        priorityCriteriaValue.setPreJobNiazsanjiCompetency(null);
        return this;
    }

    public void setPriorityCriteriaValues(Set<PriorityCriteriaValue> priorityCriteriaValues) {
        this.priorityCriteriaValues = priorityCriteriaValues;
    }

    public Set<TeachingApproach> getTeachingApproaches() {
        return teachingApproaches;
    }

    public PreJobNiazsanjiCompetency teachingApproaches(Set<TeachingApproach> teachingApproaches) {
        this.teachingApproaches = teachingApproaches;
        return this;
    }

    public PreJobNiazsanjiCompetency addTeachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproaches.add(teachingApproach);
        teachingApproach.getPreJobNiazsanjiCompetencies().add(this);
        return this;
    }

    public PreJobNiazsanjiCompetency removeTeachingApproach(TeachingApproach teachingApproach) {
        this.teachingApproaches.remove(teachingApproach);
        teachingApproach.getPreJobNiazsanjiCompetencies().remove(this);
        return this;
    }

    public void setTeachingApproaches(Set<TeachingApproach> teachingApproaches) {
        this.teachingApproaches = teachingApproaches;
    }

    public FixCompetencyDeficiency getFixCompetencyDeficiency() {
        return fixCompetencyDeficiency;
    }

    public PreJobNiazsanjiCompetency fixCompetencyDeficiency(FixCompetencyDeficiency fixCompetencyDeficiency) {
        this.fixCompetencyDeficiency = fixCompetencyDeficiency;
        return this;
    }

    public void setFixCompetencyDeficiency(FixCompetencyDeficiency fixCompetencyDeficiency) {
        this.fixCompetencyDeficiency = fixCompetencyDeficiency;
    }

    public EducationalModule getEducationalModule() {
        return educationalModule;
    }

    public PreJobNiazsanjiCompetency educationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
        return this;
    }

    public void setEducationalModule(EducationalModule educationalModule) {
        this.educationalModule = educationalModule;
    }

    public PreJobNiazsanji getPreJobNiazsanji() {
        return preJobNiazsanji;
    }

    public PreJobNiazsanjiCompetency preJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanji = preJobNiazsanji;
        return this;
    }

    public void setPreJobNiazsanji(PreJobNiazsanji preJobNiazsanji) {
        this.preJobNiazsanji = preJobNiazsanji;
    }

    public Competency getCompetency() {
        return competency;
    }

    public PreJobNiazsanjiCompetency competency(Competency competency) {
        this.competency = competency;
        return this;
    }

    public void setCompetency(Competency competency) {
        this.competency = competency;
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
        PreJobNiazsanjiCompetency preJobNiazsanjiCompetency = (PreJobNiazsanjiCompetency) o;
        if (preJobNiazsanjiCompetency.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), preJobNiazsanjiCompetency.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PreJobNiazsanjiCompetency{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", needToImprove=" + getNeedToImprove() +
            ", needToImproveDescription='" + getNeedToImproveDescription() + "'" +
            ", fixCompetencyDeficiencyDescription='" + getFixCompetencyDeficiencyDescription() + "'" +
            ", educationalModuleText='" + getEducationalModuleText() + "'" +
            ", sumScore=" + getSumScore() +
            ", priority=" + getPriority() +
            ", selected='" + isSelected() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            "}";
    }
}
