package com.marineindustryproj.service.dto.customs.ApplicationProcessModels;

import java.io.Serializable;

/**
 * A DTO for the RunningStep entity.
 */
public class ApplicationProcessSaveDataItemModel implements Serializable {

    private Long runningStepId;

    private Boolean done;

    private String description;

    private String fileDoc;

    public Long getRunningStepId() {
        return runningStepId;
    }

    public void setRunningStepId(Long runningStepId) {
        this.runningStepId = runningStepId;
    }

    public Boolean getDone() {
        return done;
    }

    public void setDone(Boolean done) {
        this.done = done;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFileDoc() {
        return fileDoc;
    }

    public void setFileDoc(String fileDoc) {
        this.fileDoc = fileDoc;
    }
}
