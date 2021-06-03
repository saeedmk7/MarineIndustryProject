package com.marineindustryproj.service.dto.customs.CapitationReportModels;

import java.util.List;

public class IndustryChildModel {

    private long groupId;
    private String groupName;

    private String industryCode;
    private String industryTitle;
    private List<Long> industryOrganizationChartIds;

    public IndustryChildModel(long groupId, String groupName, String industryCode, String industryTitle, List<Long> industryOrganizationChartIds) {
        this.groupId = groupId;
        this.groupName = groupName;
        this.industryCode = industryCode;
        this.industryTitle = industryTitle;
        this.industryOrganizationChartIds = industryOrganizationChartIds;
    }

    public IndustryChildModel() {
    }

    public long getGroupId() {
        return groupId;
    }

    public void setGroupId(long groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public String getIndustryCode() {
        return industryCode;
    }

    public void setIndustryCode(String industryCode) {
        this.industryCode = industryCode;
    }

    public String getIndustryTitle() {
        return industryTitle;
    }

    public void setIndustryTitle(String industryTitle) {
        this.industryTitle = industryTitle;
    }

    public List<Long> getIndustryOrganizationChartIds() {
        return industryOrganizationChartIds;
    }

    public void setIndustryOrganizationChartIds(List<Long> industryOrganizationChartIds) {
        this.industryOrganizationChartIds = industryOrganizationChartIds;
    }
}
