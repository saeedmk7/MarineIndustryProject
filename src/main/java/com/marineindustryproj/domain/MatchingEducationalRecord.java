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

import com.marineindustryproj.domain.enumeration.RequestStatus;

/**
 * A MatchingEducationalRecord.
 */
@Entity
@Table(name = "matching_educational_record")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class MatchingEducationalRecord implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Size(max = 100)
    @Column(name = "code", length = 100)
    private String code;

    @Lob
    @Column(name = "description")
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
    @Column(name = "chart_status", nullable = false)
    private Integer chartStatus;

    @NotNull
    @Column(name = "boss_status", nullable = false)
    private Integer bossStatus;

    @Lob
    @Column(name = "conversation")
    private String conversation;

    @Enumerated(EnumType.STRING)
    @Column(name = "request_status")
    private RequestStatus requestStatus;

    @Size(max = 50)
    @Column(name = "change_status_user_login", length = 50)
    private String changeStatusUserLogin;

    @Size(max = 50)
    @Column(name = "guid", length = 50)
    private String guid;

    @Column(name = "has_important_message")
    private Boolean hasImportantMessage;

    @Size(max = 4096)
    @Column(name = "selected_module_ids", length = 4096)
    private String selectedModuleIds;

    @OneToMany(mappedBy = "matchingEducationalRecord")
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    private Set<MatchingRunRunningStep> matchingRunRunningSteps = new HashSet<>();
    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "matching_educational_record_skillable_level_of_skill",
               joinColumns = @JoinColumn(name = "matching_educational_records_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "skillable_level_of_skills_id", referencedColumnName = "id"))
    private Set<SkillableLevelOfSkill> skillableLevelOfSkills = new HashSet<>();

    @ManyToMany
    @Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
    @JoinTable(name = "matching_educational_record_document",
               joinColumns = @JoinColumn(name = "matching_educational_records_id", referencedColumnName = "id"),
               inverseJoinColumns = @JoinColumn(name = "documents_id", referencedColumnName = "id"))
    private Set<Document> documents = new HashSet<>();

    @ManyToOne(optional = false)
    @NotNull
    @JsonIgnoreProperties("matchingEducationalRecords")
    private Person person;

    @ManyToOne
    @JsonIgnoreProperties("matchingEducationalRecords")
    private OrganizationChart organizationChart;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public MatchingEducationalRecord code(String code) {
        this.code = code;
        return this;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDescription() {
        return description;
    }

    public MatchingEducationalRecord description(String description) {
        this.description = description;
        return this;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreateUserLogin() {
        return createUserLogin;
    }

    public MatchingEducationalRecord createUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
        return this;
    }

    public void setCreateUserLogin(String createUserLogin) {
        this.createUserLogin = createUserLogin;
    }

    public ZonedDateTime getCreateDate() {
        return createDate;
    }

    public MatchingEducationalRecord createDate(ZonedDateTime createDate) {
        this.createDate = createDate;
        return this;
    }

    public void setCreateDate(ZonedDateTime createDate) {
        this.createDate = createDate;
    }

    public String getModifyUserLogin() {
        return modifyUserLogin;
    }

    public MatchingEducationalRecord modifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
        return this;
    }

    public void setModifyUserLogin(String modifyUserLogin) {
        this.modifyUserLogin = modifyUserLogin;
    }

    public ZonedDateTime getModifyDate() {
        return modifyDate;
    }

    public MatchingEducationalRecord modifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
        return this;
    }

    public void setModifyDate(ZonedDateTime modifyDate) {
        this.modifyDate = modifyDate;
    }

    public Boolean isArchived() {
        return archived;
    }

    public MatchingEducationalRecord archived(Boolean archived) {
        this.archived = archived;
        return this;
    }

    public void setArchived(Boolean archived) {
        this.archived = archived;
    }

    public String getArchivedUserLogin() {
        return archivedUserLogin;
    }

    public MatchingEducationalRecord archivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
        return this;
    }

    public void setArchivedUserLogin(String archivedUserLogin) {
        this.archivedUserLogin = archivedUserLogin;
    }

    public ZonedDateTime getArchivedDate() {
        return archivedDate;
    }

    public MatchingEducationalRecord archivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
        return this;
    }

    public void setArchivedDate(ZonedDateTime archivedDate) {
        this.archivedDate = archivedDate;
    }

    public Integer getChartStatus() {
        return chartStatus;
    }

    public MatchingEducationalRecord chartStatus(Integer chartStatus) {
        this.chartStatus = chartStatus;
        return this;
    }

    public void setChartStatus(Integer chartStatus) {
        this.chartStatus = chartStatus;
    }

    public Integer getBossStatus() {
        return bossStatus;
    }

    public MatchingEducationalRecord bossStatus(Integer bossStatus) {
        this.bossStatus = bossStatus;
        return this;
    }

    public void setBossStatus(Integer bossStatus) {
        this.bossStatus = bossStatus;
    }

    public String getConversation() {
        return conversation;
    }

    public MatchingEducationalRecord conversation(String conversation) {
        this.conversation = conversation;
        return this;
    }

    public void setConversation(String conversation) {
        this.conversation = conversation;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public MatchingEducationalRecord requestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
        return this;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getChangeStatusUserLogin() {
        return changeStatusUserLogin;
    }

    public MatchingEducationalRecord changeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
        return this;
    }

    public void setChangeStatusUserLogin(String changeStatusUserLogin) {
        this.changeStatusUserLogin = changeStatusUserLogin;
    }

    public String getGuid() {
        return guid;
    }

    public MatchingEducationalRecord guid(String guid) {
        this.guid = guid;
        return this;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public Boolean isHasImportantMessage() {
        return hasImportantMessage;
    }

    public MatchingEducationalRecord hasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
        return this;
    }

    public void setHasImportantMessage(Boolean hasImportantMessage) {
        this.hasImportantMessage = hasImportantMessage;
    }

    public String getSelectedModuleIds() {
        return selectedModuleIds;
    }

    public MatchingEducationalRecord selectedModuleIds(String selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
        return this;
    }

    public void setSelectedModuleIds(String selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
    }

    public Set<MatchingRunRunningStep> getMatchingRunRunningSteps() {
        return matchingRunRunningSteps;
    }

    public MatchingEducationalRecord matchingRunRunningSteps(Set<MatchingRunRunningStep> matchingRunRunningSteps) {
        this.matchingRunRunningSteps = matchingRunRunningSteps;
        return this;
    }

    public MatchingEducationalRecord addMatchingRunRunningStep(MatchingRunRunningStep matchingRunRunningStep) {
        this.matchingRunRunningSteps.add(matchingRunRunningStep);
        matchingRunRunningStep.setMatchingEducationalRecord(this);
        return this;
    }

    public MatchingEducationalRecord removeMatchingRunRunningStep(MatchingRunRunningStep matchingRunRunningStep) {
        this.matchingRunRunningSteps.remove(matchingRunRunningStep);
        matchingRunRunningStep.setMatchingEducationalRecord(null);
        return this;
    }

    public void setMatchingRunRunningSteps(Set<MatchingRunRunningStep> matchingRunRunningSteps) {
        this.matchingRunRunningSteps = matchingRunRunningSteps;
    }

    public Set<SkillableLevelOfSkill> getSkillableLevelOfSkills() {
        return skillableLevelOfSkills;
    }

    public MatchingEducationalRecord skillableLevelOfSkills(Set<SkillableLevelOfSkill> skillableLevelOfSkills) {
        this.skillableLevelOfSkills = skillableLevelOfSkills;
        return this;
    }

    public MatchingEducationalRecord addSkillableLevelOfSkill(SkillableLevelOfSkill skillableLevelOfSkill) {
        this.skillableLevelOfSkills.add(skillableLevelOfSkill);
        skillableLevelOfSkill.getMatchingEducationalRecords().add(this);
        return this;
    }

    public MatchingEducationalRecord removeSkillableLevelOfSkill(SkillableLevelOfSkill skillableLevelOfSkill) {
        this.skillableLevelOfSkills.remove(skillableLevelOfSkill);
        skillableLevelOfSkill.getMatchingEducationalRecords().remove(this);
        return this;
    }

    public void setSkillableLevelOfSkills(Set<SkillableLevelOfSkill> skillableLevelOfSkills) {
        this.skillableLevelOfSkills = skillableLevelOfSkills;
    }

    public Set<Document> getDocuments() {
        return documents;
    }

    public MatchingEducationalRecord documents(Set<Document> documents) {
        this.documents = documents;
        return this;
    }

    public MatchingEducationalRecord addDocument(Document document) {
        this.documents.add(document);
        document.getMatchingEducationalRecords().add(this);
        return this;
    }

    public MatchingEducationalRecord removeDocument(Document document) {
        this.documents.remove(document);
        document.getMatchingEducationalRecords().remove(this);
        return this;
    }

    public void setDocuments(Set<Document> documents) {
        this.documents = documents;
    }

    public Person getPerson() {
        return person;
    }

    public MatchingEducationalRecord person(Person person) {
        this.person = person;
        return this;
    }

    public void setPerson(Person person) {
        this.person = person;
    }

    public OrganizationChart getOrganizationChart() {
        return organizationChart;
    }

    public MatchingEducationalRecord organizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
        return this;
    }

    public void setOrganizationChart(OrganizationChart organizationChart) {
        this.organizationChart = organizationChart;
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
        MatchingEducationalRecord matchingEducationalRecord = (MatchingEducationalRecord) o;
        if (matchingEducationalRecord.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), matchingEducationalRecord.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "MatchingEducationalRecord{" +
            "id=" + getId() +
            ", code='" + getCode() + "'" +
            ", description='" + getDescription() + "'" +
            ", createUserLogin='" + getCreateUserLogin() + "'" +
            ", createDate='" + getCreateDate() + "'" +
            ", modifyUserLogin='" + getModifyUserLogin() + "'" +
            ", modifyDate='" + getModifyDate() + "'" +
            ", archived='" + isArchived() + "'" +
            ", archivedUserLogin='" + getArchivedUserLogin() + "'" +
            ", archivedDate='" + getArchivedDate() + "'" +
            ", chartStatus=" + getChartStatus() +
            ", bossStatus=" + getBossStatus() +
            ", conversation='" + getConversation() + "'" +
            ", requestStatus='" + getRequestStatus() + "'" +
            ", changeStatusUserLogin='" + getChangeStatusUserLogin() + "'" +
            ", guid='" + getGuid() + "'" +
            ", hasImportantMessage='" + isHasImportantMessage() + "'" +
            ", selectedModuleIds='" + getSelectedModuleIds() + "'" +
            "}";
    }
}
