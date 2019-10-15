package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class HomePageReport implements Serializable {

    public HomePageReport(){}
    public HomePageReport(Float total,
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
                          List<HomePageReportDetail> homePageReportDetails,
                          List<HomePageReportCourseTypeDetail> homePageReportCourseTypeDetails,
                          List<HomePageReportOrganizationAndCourseTypeDetail> homePageReportOrganizationAndCourseTypeDetails) {
        this.total = total;
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
        this.homePageReportDetails = homePageReportDetails;
        this.homePageReportCourseTypeDetails = homePageReportCourseTypeDetails;
        this.homePageReportOrganizationAndCourseTypeDetails = homePageReportOrganizationAndCourseTypeDetails;
    }
    public HomePageReport(Float total,
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
                          Float remainingStuffsPercent) {
        this.total = total;
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
    }

    private Float total;

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

    private List<HomePageReportDetail> homePageReportDetails;

    private List<HomePageReportCourseTypeDetail> homePageReportCourseTypeDetails;

    private List<HomePageReportOrganizationAndCourseTypeDetail> homePageReportOrganizationAndCourseTypeDetails;

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
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

    public List<HomePageReportDetail> getHomePageReportDetails() {
        return homePageReportDetails;
    }

    public void setHomePageReportDetails(List<HomePageReportDetail> homePageReportDetails) {
        this.homePageReportDetails = homePageReportDetails;
    }

    public List<HomePageReportCourseTypeDetail> getHomePageReportCourseTypeDetails() {
        return homePageReportCourseTypeDetails;
    }

    public void setHomePageReportCourseTypeDetails(List<HomePageReportCourseTypeDetail> homePageReportCourseTypeDetails) {
        this.homePageReportCourseTypeDetails = homePageReportCourseTypeDetails;
    }

    public List<HomePageReportOrganizationAndCourseTypeDetail> getHomePageReportOrganizationAndCourseTypeDetails() {
        return homePageReportOrganizationAndCourseTypeDetails;
    }

    public void setHomePageReportOrganizationAndCourseTypeDetails(List<HomePageReportOrganizationAndCourseTypeDetail> homePageReportOrganizationAndCourseTypeDetails) {
        this.homePageReportOrganizationAndCourseTypeDetails = homePageReportOrganizationAndCourseTypeDetails;
    }
}
