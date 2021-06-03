package com.marineindustryproj.service.dto.customs.CapitationReportModels;

import java.util.List;

public class CapitationReport {

    //Employees
    private long totalEmployee;
    private long totalBoss;
    private long totalExpert;

    //Capitation
    private double avgLearningTimeExpert;
    private double avgLearningTimeBoss;
    private double maximumAllowablePersonHour;
    private double maximumAllowableCost;

    //HSE
    private long hseNumberOfLearner;
    private long hseCourseTypePersonHour;
    private double hseCourseTypeCost;

    //short times
    private double shortTimeBossShare;
    private double shortTimeExpertShare;

    private long predicateBeforeAcceptPersonHour;
    private long predicateAfterAcceptPersonHour;
    private long predicatePersonHourTotal;

    private double predicateBeforeAcceptCost;
    private double predicateAfterAcceptCost;
    private double predicateCostTotal;


    private double percentNiazsanjiProgramToMaximumPersonHour;
    private double percentNiazsanjiProgramToMaximumCost;

    //Organization Niazsanjis
    private long organizationPredicatePersonHour;
    private double organizationPredicateCost;

    //securityAndInforming
    private long securityAndInformingNumberOfLearner;
    private double securityAndInformingPersonHour;
    private double securityAndInformingCost;

    //Informing Courses
    private long informingPersonHour;
    private double informingCost;

    //Security
    private long securityPersonHour;
    private double securityCost;

    private List<CapitationReportDetail> capitationReportDetails;

    public long getTotalEmployee() {
        return totalEmployee;
    }

    public void setTotalEmployee(long totalEmployee) {
        this.totalEmployee = totalEmployee;
    }

    public long getTotalBoss() {
        return totalBoss;
    }

    public void setTotalBoss(long totalBoss) {
        this.totalBoss = totalBoss;
    }

    public long getTotalExpert() {
        return totalExpert;
    }

    public void setTotalExpert(long totalExpert) {
        this.totalExpert = totalExpert;
    }

    public double getAvgLearningTimeExpert() {
        return avgLearningTimeExpert;
    }

    public void setAvgLearningTimeExpert(double avgLearningTimeExpert) {
        this.avgLearningTimeExpert = avgLearningTimeExpert;
    }

    public double getAvgLearningTimeBoss() {
        return avgLearningTimeBoss;
    }

    public void setAvgLearningTimeBoss(double avgLearningTimeBoss) {
        this.avgLearningTimeBoss = avgLearningTimeBoss;
    }

    public double getMaximumAllowablePersonHour() {
        return maximumAllowablePersonHour;
    }

    public void setMaximumAllowablePersonHour(double maximumAllowablePersonHour) {
        this.maximumAllowablePersonHour = maximumAllowablePersonHour;
    }

    public double getMaximumAllowableCost() {
        return maximumAllowableCost;
    }

    public void setMaximumAllowableCost(double maximumAllowableCost) {
        this.maximumAllowableCost = maximumAllowableCost;
    }

    public long getHseNumberOfLearner() {
        return hseNumberOfLearner;
    }

    public void setHseNumberOfLearner(long hseNumberOfLearner) {
        this.hseNumberOfLearner = hseNumberOfLearner;
    }

    public long getHseCourseTypePersonHour() {
        return hseCourseTypePersonHour;
    }

    public void setHseCourseTypePersonHour(long hseCourseTypePersonHour) {
        this.hseCourseTypePersonHour = hseCourseTypePersonHour;
    }

    public double getHseCourseTypeCost() {
        return hseCourseTypeCost;
    }

    public void setHseCourseTypeCost(double hseCourseTypeCost) {
        this.hseCourseTypeCost = hseCourseTypeCost;
    }

    public double getShortTimeBossShare() {
        return shortTimeBossShare;
    }

    public void setShortTimeBossShare(double shortTimeBossShare) {
        this.shortTimeBossShare = shortTimeBossShare;
    }

    public double getShortTimeExpertShare() {
        return shortTimeExpertShare;
    }

    public void setShortTimeExpertShare(double shortTimeExpertShare) {
        this.shortTimeExpertShare = shortTimeExpertShare;
    }

    public long getPredicateBeforeAcceptPersonHour() {
        return predicateBeforeAcceptPersonHour;
    }

    public void setPredicateBeforeAcceptPersonHour(long predicateBeforeAcceptPersonHour) {
        this.predicateBeforeAcceptPersonHour = predicateBeforeAcceptPersonHour;
    }

    public long getPredicateAfterAcceptPersonHour() {
        return predicateAfterAcceptPersonHour;
    }

    public void setPredicateAfterAcceptPersonHour(long predicateAfterAcceptPersonHour) {
        this.predicateAfterAcceptPersonHour = predicateAfterAcceptPersonHour;
    }

    public long getPredicatePersonHourTotal() {
        return predicatePersonHourTotal;
    }

    public void setPredicatePersonHourTotal(long predicatePersonHourTotal) {
        this.predicatePersonHourTotal = predicatePersonHourTotal;
    }

    public double getPredicateBeforeAcceptCost() {
        return predicateBeforeAcceptCost;
    }

    public void setPredicateBeforeAcceptCost(double predicateBeforeAcceptCost) {
        this.predicateBeforeAcceptCost = predicateBeforeAcceptCost;
    }

    public double getPredicateAfterAcceptCost() {
        return predicateAfterAcceptCost;
    }

    public void setPredicateAfterAcceptCost(double predicateAfterAcceptCost) {
        this.predicateAfterAcceptCost = predicateAfterAcceptCost;
    }

    public double getPredicateCostTotal() {
        return predicateCostTotal;
    }

    public void setPredicateCostTotal(double predicateCostTotal) {
        this.predicateCostTotal = predicateCostTotal;
    }

    public double getPercentNiazsanjiProgramToMaximumPersonHour() {
        return percentNiazsanjiProgramToMaximumPersonHour;
    }

    public void setPercentNiazsanjiProgramToMaximumPersonHour(double percentNiazsanjiProgramToMaximumPersonHour) {
        this.percentNiazsanjiProgramToMaximumPersonHour = percentNiazsanjiProgramToMaximumPersonHour;
    }

    public double getPercentNiazsanjiProgramToMaximumCost() {
        return percentNiazsanjiProgramToMaximumCost;
    }

    public void setPercentNiazsanjiProgramToMaximumCost(double percentNiazsanjiProgramToMaximumCost) {
        this.percentNiazsanjiProgramToMaximumCost = percentNiazsanjiProgramToMaximumCost;
    }

    public long getOrganizationPredicatePersonHour() {
        return organizationPredicatePersonHour;
    }

    public void setOrganizationPredicatePersonHour(long organizationPredicatePersonHour) {
        this.organizationPredicatePersonHour = organizationPredicatePersonHour;
    }

    public double getOrganizationPredicateCost() {
        return organizationPredicateCost;
    }

    public void setOrganizationPredicateCost(double organizationPredicateCost) {
        this.organizationPredicateCost = organizationPredicateCost;
    }

    public long getSecurityAndInformingNumberOfLearner() {
        return securityAndInformingNumberOfLearner;
    }

    public void setSecurityAndInformingNumberOfLearner(long securityAndInformingNumberOfLearner) {
        this.securityAndInformingNumberOfLearner = securityAndInformingNumberOfLearner;
    }

    public double getSecurityAndInformingPersonHour() {
        return securityAndInformingPersonHour;
    }

    public void setSecurityAndInformingPersonHour(double securityAndInformingPersonHour) {
        this.securityAndInformingPersonHour = securityAndInformingPersonHour;
    }

    public double getSecurityAndInformingCost() {
        return securityAndInformingCost;
    }

    public void setSecurityAndInformingCost(double securityAndInformingCost) {
        this.securityAndInformingCost = securityAndInformingCost;
    }

    public long getInformingPersonHour() {
        return informingPersonHour;
    }

    public void setInformingPersonHour(long informingPersonHour) {
        this.informingPersonHour = informingPersonHour;
    }

    public double getInformingCost() {
        return informingCost;
    }

    public void setInformingCost(double informingCost) {
        this.informingCost = informingCost;
    }

    public long getSecurityPersonHour() {
        return securityPersonHour;
    }

    public void setSecurityPersonHour(long securityPersonHour) {
        this.securityPersonHour = securityPersonHour;
    }

    public double getSecurityCost() {
        return securityCost;
    }

    public void setSecurityCost(double securityCost) {
        this.securityCost = securityCost;
    }

    public List<CapitationReportDetail> getCapitationReportDetails() {
        return capitationReportDetails;
    }

    public void setCapitationReportDetails(List<CapitationReportDetail> capitationReportDetails) {
        this.capitationReportDetails = capitationReportDetails;
    }
}
