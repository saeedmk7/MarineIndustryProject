package com.marineindustryproj.service.dto;

import java.io.Serializable;
import java.time.ZonedDateTime;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * A DTO for the AcademicRank entity.
 */
public class ReturnImageUrlDTO implements Serializable {

    @NotNull
    @Size(max = 10000)
    private String url;

    private boolean isOk;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isOk() {
        return isOk;
    }

    public void setOk(boolean ok) {
        isOk = ok;
    }
}
