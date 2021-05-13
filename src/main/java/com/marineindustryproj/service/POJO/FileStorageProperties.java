package com.marineindustryproj.service.POJO;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String uploadDir;

    public String getUploadDir() {
        return uploadDir;
    }

    public void setUploadDir(String uploadDir) {
        this.uploadDir = uploadDir;
    }

    private String imageUploadDir;

    public String getImageUploadDir() {
        return imageUploadDir;
    }

    public void setImageUploadDir(String imageUploadDir) {
        this.imageUploadDir = imageUploadDir;
    }

    private String historyUploadDir;

    public String getHistoryUploadDir() {
        return historyUploadDir;
    }

    public void setHistoryUploadDir(String historyUploadDir) {
        this.historyUploadDir = historyUploadDir;
    }

    private String soldierTrainingReportUploadDir;

    public String getSoldierTrainingReportUploadDir() {
        return soldierTrainingReportUploadDir;
    }

    public void setSoldierTrainingReportUploadDir(String soldierTrainingReportUploadDir) {
        this.soldierTrainingReportUploadDir = soldierTrainingReportUploadDir;
    }

    private String soldierMediaAwarenessReportUploadDir;

    public String getSoldierMediaAwarenessReportUploadDir() {
        return soldierMediaAwarenessReportUploadDir;
    }

    public void setSoldierMediaAwarenessReportUploadDir(String soldierMediaAwarenessReportUploadDir) {
        this.soldierMediaAwarenessReportUploadDir = soldierMediaAwarenessReportUploadDir;
    }

    private String evaluateCriteriaDataUploadDir;

    public String getEvaluateCriteriaDataUploadDir() {
        return evaluateCriteriaDataUploadDir;
    }

    public void setEvaluateCriteriaDataUploadDir(String evaluateCriteriaDataUploadDir) {
        this.evaluateCriteriaDataUploadDir = evaluateCriteriaDataUploadDir;
    }

    private String educationUploadDir;

    public String getEducationUploadDir() {
        return educationUploadDir;
    }

    public void setEducationUploadDir(String educationUploadDir)
    {
        this.educationUploadDir = educationUploadDir;
    }

    private String jobUploadDir;

    public String getJobUploadDir() {
        return jobUploadDir;
    }

    public void setJobUploadDir(String jobUploadDir)
    {
        this.jobUploadDir = jobUploadDir;
    }

    private String runPhaseUploadDir;

    public String getRunPhaseUploadDir() {
        return runPhaseUploadDir;
    }

    public void setRunPhaseUploadDir(String runPhaseUploadDir) {
        this.runPhaseUploadDir = runPhaseUploadDir;
    }

    private String matchingEducationalRecordUploadDir;

    public String getMatchingEducationalRecordUploadDir() {
        return matchingEducationalRecordUploadDir;
    }

    public void setMatchingEducationalRecordUploadDir(String matchingEducationalRecordUploadDir) {
        this.matchingEducationalRecordUploadDir = matchingEducationalRecordUploadDir;
    }

    private String applicationProcessUploadDir;

    public String getApplicationProcessUploadDir() {
        return applicationProcessUploadDir;
    }

    public void setApplicationProcessUploadDir(String applicationProcessUploadDir) {
        this.applicationProcessUploadDir = applicationProcessUploadDir;
    }

    private String jamHelpUploadDir;

    public String getJamHelpUploadDir() {
        return jamHelpUploadDir;
    }

    public void setJamHelpUploadDir(String jamHelpUploadDir) {
        this.jamHelpUploadDir = jamHelpUploadDir;
    }
}
