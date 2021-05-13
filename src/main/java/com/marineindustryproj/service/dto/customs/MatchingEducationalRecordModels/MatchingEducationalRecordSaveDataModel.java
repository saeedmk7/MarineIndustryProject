package com.marineindustryproj.service.dto.customs.MatchingEducationalRecordModels;

import com.marineindustryproj.domain.enumeration.RequestStatus;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

public class MatchingEducationalRecordSaveDataModel implements Serializable {

    private Long matchingEducationalRecordId;

    private String description;

    private String conversion;

    private Integer stepNumber;

    private Integer chartStatus;

    private Integer bossStatus;

    private RequestStatus requestStatus;

    private Long personId;

    private Long organizationChartId;

    private String selectedModuleIds;

    private Set<SkillableLevelOfSkillDTO> skillableLevelOfSkills = new HashSet<>();

    private Set<MatchingEducationalRecordSaveDataItemModel> matchingEducationalRecordSaveDataItemModels = new HashSet<>();

    public Long getMatchingEducationalRecordId() {
        return matchingEducationalRecordId;
    }

    public void setMatchingEducationalRecordId(Long matchingEducationalRecordId) {
        this.matchingEducationalRecordId = matchingEducationalRecordId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConversion() {
        return conversion;
    }

    public void setConversion(String conversion) {
        this.conversion = conversion;
    }

    public Integer getStepNumber() {
        return stepNumber;
    }

    public void setStepNumber(Integer stepNumber) {
        this.stepNumber = stepNumber;
    }

    public Integer getChartStatus() {
        return chartStatus;
    }

    public void setChartStatus(Integer chartStatus) {
        this.chartStatus = chartStatus;
    }

    public Integer getBossStatus() {
        return bossStatus;
    }

    public void setBossStatus(Integer bossStatus) {
        this.bossStatus = bossStatus;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public Set<SkillableLevelOfSkillDTO> getSkillableLevelOfSkills() {
        return skillableLevelOfSkills;
    }

    public void setSkillableLevelOfSkills(Set<SkillableLevelOfSkillDTO> skillableLevelOfSkills) {
        this.skillableLevelOfSkills = skillableLevelOfSkills;
    }

    public Set<MatchingEducationalRecordSaveDataItemModel> getMatchingEducationalRecordSaveDataItemModels() {
        return matchingEducationalRecordSaveDataItemModels;
    }

    public void setMatchingEducationalRecordSaveDataItemModels(Set<MatchingEducationalRecordSaveDataItemModel> matchingEducationalRecordSaveDataItemModels) {
        this.matchingEducationalRecordSaveDataItemModels = matchingEducationalRecordSaveDataItemModels;
    }

    public RequestStatus getRequestStatus() {
        return requestStatus;
    }

    public void setRequestStatus(RequestStatus requestStatus) {
        this.requestStatus = requestStatus;
    }

    public String getSelectedModuleIds() {
        return selectedModuleIds;
    }

    public void setSelectedModuleIds(String selectedModuleIds) {
        this.selectedModuleIds = selectedModuleIds;
    }
}
