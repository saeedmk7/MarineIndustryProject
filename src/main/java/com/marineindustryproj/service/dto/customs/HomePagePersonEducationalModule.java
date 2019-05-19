package com.marineindustryproj.service.dto.customs;

import com.marineindustryproj.service.dto.EducationalHistoryDTO;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;

public class HomePagePersonEducationalModule {

    public HomePagePersonEducationalModule(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO, EducationalModuleDTO educationalModuleDTO) {
        this.id = educationalModuleDTO.getId();
        this.title = educationalModuleDTO.getTitle();
        this.code = educationalModuleDTO.getCode();
        this.learningTimePractical = educationalModuleDTO.getLearningTimePractical();
        this.learningTimeTheorical = educationalModuleDTO.getLearningTimeTheorical();
        this.organizationId = educationalModuleDTO.getOrganizationId();
        this.organizationTitle = educationalModuleDTO.getOrganizationTitle();
        this.skillableLevelOfSkillId = educationalModuleDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        this.educationalModuleType = "از کلیه پودمان ها";
        switch (finalNiazsanjiReportDTO.getStatus())
        {
            case 0:
                this.status = 70;
                break;
            case 10:
                this.status = 80;
                break;
            case 20:
                this.status = 90;
                break;
        }
        this.status = status;
    }

    public HomePagePersonEducationalModule(EducationalModuleMinDTO educationalModuleMinDTO, Integer status) {
        this.id = educationalModuleMinDTO.getId();
        this.title = educationalModuleMinDTO.getTitle();
        this.code = educationalModuleMinDTO.getCode();
        this.learningTimePractical = educationalModuleMinDTO.getLearningTimePractical();
        this.learningTimeTheorical = educationalModuleMinDTO.getLearningTimeTheorical();
        this.organizationId = educationalModuleMinDTO.getOrganizationId();
        this.organizationTitle = educationalModuleMinDTO.getOrganizationTitle();
        this.skillableLevelOfSkillId = educationalModuleMinDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleMinDTO.getSkillableLevelOfSkillTitle();
        this.educationalModuleType = "از شناسنامه شغلی";
        this.status = status;

    }
    public HomePagePersonEducationalModule(EducationalModuleDTO educationalModuleDTO, Integer status) {
        this.id = educationalModuleDTO.getId();
        this.title = educationalModuleDTO.getTitle();
        this.code = educationalModuleDTO.getCode();
        this.learningTimePractical = educationalModuleDTO.getLearningTimePractical();
        this.learningTimeTheorical = educationalModuleDTO.getLearningTimeTheorical();
        this.organizationId = educationalModuleDTO.getOrganizationId();
        this.organizationTitle = educationalModuleDTO.getOrganizationTitle();
        this.skillableLevelOfSkillId = educationalModuleDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        this.educationalModuleType = "از شناسنامه شغلی";
        this.status = status;
    }
    public HomePagePersonEducationalModule(EducationalHistoryDTO educationalHistoryDTO, EducationalModuleDTO educationalModuleDTO, Integer status) {
        this.id = educationalHistoryDTO.getId();
        this.title = educationalHistoryDTO.getEducationalModuleName();
        this.learningTimePractical = educationalHistoryDTO.getLearningTimePractical();
        this.learningTimeTheorical = educationalHistoryDTO.getLearningTimeTheorical();

        this.code = educationalHistoryDTO.getEducationalModuleId().toString();
        this.skillableLevelOfSkillId = educationalModuleDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        this.organizationId = educationalModuleDTO.getOrganizationId();
        this.organizationTitle = educationalModuleDTO.getOrganizationTitle();
        this.educationalModuleType = "از کلیه پودمان ها";
        this.status = status;
    }
    private Long id;

    private String code;

    private String title;

    private Integer learningTimeTheorical;

    private Integer learningTimePractical;

    private Long skillableLevelOfSkillId;

    private String skillableLevelOfSkillTitle;

    private Long organizationId;

    private String organizationTitle;

    private String educationalModuleType;

    private Integer status;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLearningTimeTheorical() {
        return learningTimeTheorical;
    }

    public void setLearningTimeTheorical(Integer learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
    }

    public Integer getLearningTimePractical() {
        return learningTimePractical;
    }

    public void setLearningTimePractical(Integer learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
    }

    /*public Set<ScientificWorkGroupDTO> getScientificWorkGroups() {
        return scientificWorkGroups;
    }

    public void setScientificWorkGroups(Set<ScientificWorkGroupDTO> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
    }*/

    public Long getSkillableLevelOfSkillId() {
        return skillableLevelOfSkillId;
    }

    public void setSkillableLevelOfSkillId(Long skillableLevelOfSkillId) {
        this.skillableLevelOfSkillId = skillableLevelOfSkillId;
    }

    public String getSkillableLevelOfSkillTitle() {
        return skillableLevelOfSkillTitle;
    }

    public void setSkillableLevelOfSkillTitle(String skillableLevelOfSkillTitle) {
        this.skillableLevelOfSkillTitle = skillableLevelOfSkillTitle;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationTitle() {
        return organizationTitle;
    }

    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getEducationalModuleType() {
        return educationalModuleType;
    }

    public void setEducationalModuleType(String educationalModuleType) {
        this.educationalModuleType = educationalModuleType;
    }
}
