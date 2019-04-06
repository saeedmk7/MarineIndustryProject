package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Job entity.
 */
public class JobMinDTO implements Serializable {

    public JobMinDTO(Long id,
                     String title,
                     String jobKey,
                     String jobCode,
                     String first3JobCode) {
        this.id = id;
        this.title = title;
        this.jobKey = jobKey;
        this.jobCode = jobCode;
        this.first3JobCode = first3JobCode;
    }

    private Long id;

    private String title;

    private String jobKey;

    private String jobCode;

    private String first3JobCode;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getJobKey() {
        return jobKey;
    }

    public void setJobKey(String jobKey) {
        this.jobKey = jobKey;
    }

    public String getJobCode() {
        return jobCode;
    }

    public void setJobCode(String jobCode) {
        this.jobCode = jobCode;
    }

    public String getFirst3JobCode() {
        return first3JobCode;
    }

    public void setFirst3JobCode(String first3JobCode) {
        this.first3JobCode = first3JobCode;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        JobMinDTO jobDTO = (JobMinDTO) o;
        if (jobDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), jobDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "JobDTO{" +
            "id=" + getId() +
            ", title='" + getTitle() + "'" +
            ", jobKey='" + getJobKey() + "'" +
            ", jobCode='" + getJobCode() + "'" +
            ", first3JobCode='" + getFirst3JobCode() + "'" +
            "}";
    }
}
