package com.marineindustryproj.service.dto.customs.EffectivenessPhaseModels;

import java.util.List;

public class FinalEffectivenessPhaseReportModel {

    private Integer levelId;

    private String levelTitle;

    private Long finishedCount;

    private Float averageEffectiveness;

    private List<DetailFinalEffectivenessPhaseReportModel> detailFinalEffectivenessPhaseReportModels;

    public FinalEffectivenessPhaseReportModel(Integer levelId, String levelTitle, Long finishedCount, Float averageEffectiveness, List<DetailFinalEffectivenessPhaseReportModel> detailFinalEffectivenessPhaseReportModels) {
        this.levelId = levelId;
        this.levelTitle = levelTitle;
        this.finishedCount = finishedCount;
        this.averageEffectiveness = averageEffectiveness;
        this.detailFinalEffectivenessPhaseReportModels = detailFinalEffectivenessPhaseReportModels;
    }

    public String getLevelTitle() {
        return levelTitle;
    }

    public void setLevelTitle(String levelTitle) {
        this.levelTitle = levelTitle;
    }

    public Long getFinishedCount() {
        return finishedCount;
    }

    public void setFinishedCount(Long finishedCount) {
        this.finishedCount = finishedCount;
    }

    public Float getAverageEffectiveness() {
        return averageEffectiveness;
    }

    public void setAverageEffectiveness(Float averageEffectiveness) {
        this.averageEffectiveness = averageEffectiveness;
    }

    public List<DetailFinalEffectivenessPhaseReportModel> getDetailFinalEffectivenessPhaseReportModels() {
        return detailFinalEffectivenessPhaseReportModels;
    }

    public void setDetailFinalEffectivenessPhaseReportModels(List<DetailFinalEffectivenessPhaseReportModel> detailFinalEffectivenessPhaseReportModels) {
        this.detailFinalEffectivenessPhaseReportModels = detailFinalEffectivenessPhaseReportModels;
    }

    public Integer getLevelId() {
        return levelId;
    }

    public void setLevelId(Integer levelId) {
        this.levelId = levelId;
    }
}
