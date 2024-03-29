package com.marineindustryproj.service.dto.customs;

import com.marineindustryproj.domain.enumeration.EducationalModuleType;
import com.marineindustryproj.service.dto.EducationalHistoryDTO;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;

public class HomePagePersonEducationalModule {

    public HomePagePersonEducationalModule(FinalNiazsanjiReportDTO finalNiazsanjiReportDTO,
                                           EducationalModuleType educationalModuleType, EducationalModuleDTO educationalModuleDTO) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportDTO.getId();
        this.id = educationalModuleDTO.getId();
        this.title = educationalModuleDTO.getTitle();
        this.code = educationalModuleDTO.getCode();
        this.learningTimePractical = educationalModuleDTO.getLearningTimePractical();
        this.learningTimeTheorical = educationalModuleDTO.getLearningTimeTheorical();
        this.organizationId = educationalModuleDTO.getOrganizationId();
        this.organizationTitle = educationalModuleDTO.getOrganizationTitle();
        this.skillableLevelOfSkillId = educationalModuleDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        if(educationalModuleType == EducationalModuleType.ALL)
            this.educationalModuleType = "نیازسنجی فردی";
        else
            this.educationalModuleType = "نیازسنجی از شناسنامه شغلی";

        this.courseTypeId = finalNiazsanjiReportDTO.getCourseTypeId();
        this.courseType = finalNiazsanjiReportDTO.getCourseTypeTitle();
        if(finalNiazsanjiReportDTO.getNiazsanjiYear() != null)
            this.runDate = finalNiazsanjiReportDTO.getNiazsanjiYear().toString();
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
        /*this.organizationId = educationalModuleMinDTO.getOrganizationId();
        this.organizationTitle = educationalModuleMinDTO.getOrganizationTitle();*/
        this.skillableLevelOfSkillId = educationalModuleMinDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleMinDTO.getSkillableLevelOfSkillTitle();
        this.educationalModuleType = "نیازسنجی از شناسنامه شغلی";
        this.status = status;

    }
    public HomePagePersonEducationalModule(EducationalModuleDTO educationalModuleDTO, Integer status) {
        this.id = educationalModuleDTO.getId();
        this.title = educationalModuleDTO.getTitle();
        this.code = educationalModuleDTO.getCode();
        this.learningTimePractical = educationalModuleDTO.getLearningTimePractical();
        this.learningTimeTheorical = educationalModuleDTO.getLearningTimeTheorical();
        /*this.organizationId = educationalModuleDTO.getOrganizationId();
        this.organizationTitle = educationalModuleDTO.getOrganizationTitle();*/
        this.skillableLevelOfSkillId = educationalModuleDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        this.educationalModuleType = "نیازسنجی از شناسنامه شغلی";
        this.status = status;
    }
    public HomePagePersonEducationalModule(EducationalHistoryDTO educationalHistoryDTO, EducationalModuleDTO educationalModuleDTO, Integer status) {
        this.finalNiazsanjiReportId = educationalHistoryDTO.getFinalNiazsanjiReportId();
        this.id = educationalHistoryDTO.getId();
        this.title = educationalHistoryDTO.getEducationalModuleName();
        this.learningTimePractical = educationalHistoryDTO.getLearningTimePractical();
        this.learningTimeTheorical = educationalHistoryDTO.getLearningTimeTheorical();
        this.courseTypeId = educationalHistoryDTO.getCourseTypeId();
        this.courseType = educationalHistoryDTO.getCourseTypeTitle();
        this.code = educationalHistoryDTO.getEducationalModuleCode();
        this.skillableLevelOfSkillId = educationalModuleDTO.getSkillableLevelOfSkillId();
        this.skillableLevelOfSkillTitle = educationalModuleDTO.getSkillableLevelOfSkillTitle();
        /*this.organizationId = educationalModuleDTO.getOrganizationId();
        this.organizationTitle = educationalModuleDTO.getOrganizationTitle();*/
        this.runDate = educationalHistoryDTO.getDateOfStart();
        this.endDate = educationalHistoryDTO.getDateOfEnd();
        this.educationalModuleType = "نیازسنجی فردی";
        this.status = status;
        this.educationalHistoryId = educationalHistoryDTO.getId();
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

    private String courseType;

    private Integer status;

    private String runDate;

    private String endDate;

    private Long educationalHistoryId;

    private Long courseTypeId;

    private Long finalNiazsanjiReportId;

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

    public String getRunDate() {
        return runDate;
    }

    public void setRunDate(String runDate) {
        this.runDate = runDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Long getEducationalHistoryId() {
        return educationalHistoryId;
    }

    public void setEducationalHistoryId(Long educationalHistoryId) {
        this.educationalHistoryId = educationalHistoryId;
    }

    public String getCourseType() {
        return courseType;
    }

    public void setCourseType(String courseType) {
        this.courseType = courseType;
    }

    public Long getCourseTypeId() {
        return courseTypeId;
    }

    public void setCourseTypeId(Long courseTypeId) {
        this.courseTypeId = courseTypeId;
    }

    public Long getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }
}
