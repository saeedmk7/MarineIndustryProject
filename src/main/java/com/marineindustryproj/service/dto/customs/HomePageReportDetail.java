package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

public class HomePageReportDetail implements Serializable {

    public HomePageReportDetail(){}
    public HomePageReportDetail(Long organizationChartId,
                                String organizationChartTitle,
                                Float total,
                                Float totalPercent,
                                Float totalManagers,
                                Float totalManagersPercent,
                                Float totalStuffs,
                                Float totalStuffsPercent,
                                Float totalPassed,
                                Float totalPassedPercent,
                                Float totalPassedManagers,
                                Float totalPassedManagersPercent,
                                Float totalPassedStuffs,
                                Float totalPassedStuffsPercent,
                                Float remaining,
                                Float remainingPercent,
                                Float remainingManagers,
                                Float remainingManagersPercent,
                                Float remainingStuffs,
                                Float remainingStuffsPercent,
                                List<HomePageReportSecondLevelDetail> homePageReportSecondLevelDetails) {
        this.organizationChartId = organizationChartId;
        this.organizationChartTitle = organizationChartTitle;
        this.total = total;
        this.totalPercent = totalPercent;
        this.totalManagers = totalManagers;
        this.totalManagersPercent = totalManagersPercent;
        this.totalStuffs = totalStuffs;
        this.totalStuffsPercent = totalStuffsPercent;
        this.totalPassed = totalPassed;
        this.totalPassedPercent = totalPassedPercent;
        this.totalPassedManagers = totalPassedManagers;
        this.totalPassedManagersPercent = totalPassedManagersPercent;
        this.totalPassedStuffs = totalPassedStuffs;
        this.totalPassedStuffsPercent = totalPassedStuffsPercent;
        this.remaining = remaining;
        this.remainingPercent = remainingPercent;
        this.remainingManagers = remainingManagers;
        this.remainingManagersPercent = remainingManagersPercent;
        this.remainingStuffs = remainingStuffs;
        this.remainingStuffsPercent = remainingStuffsPercent;
        this.homePageReportSecondLevelDetails = homePageReportSecondLevelDetails;
    }

    private Long organizationChartId;

    private String organizationChartTitle;

    private Float total;

    private Float totalPercent;

    private Float totalManagers;

    private Float totalManagersPercent;

    private Float totalStuffs;

    private Float totalStuffsPercent;

    private Float totalPassed;

    private Float totalPassedPercent;

    private Float totalPassedManagers;

    private Float totalPassedManagersPercent;

    private Float totalPassedStuffs;

    private Float totalPassedStuffsPercent;

    private Float remaining;

    private Float remainingPercent;

    private Float remainingManagers;

    private Float remainingManagersPercent;

    private Float remainingStuffs;

    private Float remainingStuffsPercent;

    private List<HomePageReportSecondLevelDetail> homePageReportSecondLevelDetails;

    public Long getOrganizationChartId() {
        return organizationChartId;
    }

    public void setOrganizationChartId(Long organizationChartId) {
        this.organizationChartId = organizationChartId;
    }

    public String getOrganizationChartTitle() {
        return organizationChartTitle;
    }

    public void setOrganizationChartTitle(String organizationChartTitle) {
        this.organizationChartTitle = organizationChartTitle;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
    }

    public Float getTotalPercent() {
        return totalPercent;
    }

    public void setTotalPercent(Float totalPercent) {
        this.totalPercent = totalPercent;
    }

    public Float getTotalManagers() {
        return totalManagers;
    }

    public void setTotalManagers(Float totalManagers) {
        this.totalManagers = totalManagers;
    }

    public Float getTotalManagersPercent() {
        return totalManagersPercent;
    }

    public void setTotalManagersPercent(Float totalManagersPercent) {
        this.totalManagersPercent = totalManagersPercent;
    }

    public Float getTotalStuffs() {
        return totalStuffs;
    }

    public void setTotalStuffs(Float totalStuffs) {
        this.totalStuffs = totalStuffs;
    }

    public Float getTotalStuffsPercent() {
        return totalStuffsPercent;
    }

    public void setTotalStuffsPercent(Float totalStuffsPercent) {
        this.totalStuffsPercent = totalStuffsPercent;
    }

    public Float getTotalPassed() {
        return totalPassed;
    }

    public void setTotalPassed(Float totalPassed) {
        this.totalPassed = totalPassed;
    }

    public Float getTotalPassedPercent() {
        return totalPassedPercent;
    }

    public void setTotalPassedPercent(Float totalPassedPercent) {
        this.totalPassedPercent = totalPassedPercent;
    }

    public Float getTotalPassedManagers() {
        return totalPassedManagers;
    }

    public void setTotalPassedManagers(Float totalPassedManagers) {
        this.totalPassedManagers = totalPassedManagers;
    }

    public Float getTotalPassedManagersPercent() {
        return totalPassedManagersPercent;
    }

    public void setTotalPassedManagersPercent(Float totalPassedManagersPercent) {
        this.totalPassedManagersPercent = totalPassedManagersPercent;
    }

    public Float getTotalPassedStuffs() {
        return totalPassedStuffs;
    }

    public void setTotalPassedStuffs(Float totalPassedStuffs) {
        this.totalPassedStuffs = totalPassedStuffs;
    }

    public Float getTotalPassedStuffsPercent() {
        return totalPassedStuffsPercent;
    }

    public void setTotalPassedStuffsPercent(Float totalPassedStuffsPercent) {
        this.totalPassedStuffsPercent = totalPassedStuffsPercent;
    }

    public Float getRemaining() {
        return remaining;
    }

    public void setRemaining(Float remaining) {
        this.remaining = remaining;
    }

    public Float getRemainingPercent() {
        return remainingPercent;
    }

    public void setRemainingPercent(Float remainingPercent) {
        this.remainingPercent = remainingPercent;
    }

    public Float getRemainingManagers() {
        return remainingManagers;
    }

    public void setRemainingManagers(Float remainingManagers) {
        this.remainingManagers = remainingManagers;
    }

    public Float getRemainingManagersPercent() {
        return remainingManagersPercent;
    }

    public void setRemainingManagersPercent(Float remainingManagersPercent) {
        this.remainingManagersPercent = remainingManagersPercent;
    }

    public Float getRemainingStuffs() {
        return remainingStuffs;
    }

    public void setRemainingStuffs(Float remainingStuffs) {
        this.remainingStuffs = remainingStuffs;
    }

    public Float getRemainingStuffsPercent() {
        return remainingStuffsPercent;
    }

    public void setRemainingStuffsPercent(Float remainingStuffsPercent) {
        this.remainingStuffsPercent = remainingStuffsPercent;
    }


    public List<HomePageReportSecondLevelDetail> getHomePageReportSecondLevelDetails() {
        return homePageReportSecondLevelDetails;
    }

    public void setHomePageReportSecondLevelDetails(List<HomePageReportSecondLevelDetail> homePageReportSecondLevelDetails) {
        this.homePageReportSecondLevelDetails = homePageReportSecondLevelDetails;
    }
}
