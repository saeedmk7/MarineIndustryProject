package com.marineindustryproj.service.dto.customs;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;

/**
 * A DTO for the RunningStep entity.
 */
public class RunPhaseSaveDataItemModel implements Serializable {

    private Long runningStepId;

    private Boolean done;

    private String description;

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
}
