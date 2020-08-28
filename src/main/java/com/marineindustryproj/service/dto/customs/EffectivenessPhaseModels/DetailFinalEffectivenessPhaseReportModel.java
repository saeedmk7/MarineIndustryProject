package com.marineindustryproj.service.dto.customs.EffectivenessPhaseModels;

public class DetailFinalEffectivenessPhaseReportModel {

    private Integer effectivenessLevel;

    private Long finalNiazsanjiReportId;

    private String educationalModuleTitle;

    private Float effectivenessPhaseFinalResultPercent;

    private Long peopleCount;

    private Float finalAverage;

    public DetailFinalEffectivenessPhaseReportModel() {
    }

    public DetailFinalEffectivenessPhaseReportModel(Long finalNiazsanjiReportId, String educationalModuleTitle, Float effectivenessPhaseFinalResultPercent, Long peopleCount) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
        this.educationalModuleTitle = educationalModuleTitle;
        this.effectivenessPhaseFinalResultPercent = effectivenessPhaseFinalResultPercent;
        this.peopleCount = peopleCount;
    }

    public DetailFinalEffectivenessPhaseReportModel(Integer effectivenessLevel, Long finalNiazsanjiReportId, String educationalModuleTitle, Float effectivenessPhaseFinalResultPercent, Long peopleCount) {
        this.effectivenessLevel = effectivenessLevel;
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
        this.educationalModuleTitle = educationalModuleTitle;
        this.effectivenessPhaseFinalResultPercent = effectivenessPhaseFinalResultPercent;
        this.peopleCount = peopleCount;
    }

    public Long getFinalNiazsanjiReportId() {
        return finalNiazsanjiReportId;
    }

    public void setFinalNiazsanjiReportId(Long finalNiazsanjiReportId) {
        this.finalNiazsanjiReportId = finalNiazsanjiReportId;
    }

    public String getEducationalModuleTitle() {
        return educationalModuleTitle;
    }

    public void setEducationalModuleTitle(String educationalModuleTitle) {
        this.educationalModuleTitle = educationalModuleTitle;
    }

    public Long getPeopleCount() {
        return peopleCount;
    }

    public void setPeopleCount(Long peopleCount) {
        this.peopleCount = peopleCount;
    }

    public Integer getEffectivenessLevel() {
        return effectivenessLevel;
    }

    public void setEffectivenessLevel(Integer effectivenessLevel) {
        this.effectivenessLevel = effectivenessLevel;
    }

    public Float getEffectivenessPhaseFinalResultPercent() {
        return effectivenessPhaseFinalResultPercent;
    }

    public void setEffectivenessPhaseFinalResultPercent(Float effectivenessPhaseFinalResultPercent) {
        this.effectivenessPhaseFinalResultPercent = effectivenessPhaseFinalResultPercent;
    }

    public Float getFinalAverage() {
        return finalAverage;
    }

    public void setFinalAverage(Float finalAverage) {
        this.finalAverage = finalAverage;
    }
}
