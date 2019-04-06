package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.Objects;

import com.marineindustryproj.domain.Job;

/**
 * A DTO for the Person entity.
 */
public class PersonMinDTO implements Serializable {

    public PersonMinDTO(Long id,
                        String name,
                        String family,
                        String nationalId,
                        String personelCode,
                        Job job) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.nationalId = nationalId;
        this.personelCode = personelCode;
        this.jobId = job.getId();
        this.jobTitle = job.getTitle();
    }

    private Long id;

    private String name;

    private String family;

    private String nationalId;

    private String personelCode;

    private Long jobId;

    private String jobTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFamily() {
        return family;
    }

    public void setFamily(String family) {
        this.family = family;
    }

    public String getNationalId() {
        return nationalId;
    }

    public void setNationalId(String nationalId) {
        this.nationalId = nationalId;
    }

    public String getPersonelCode() {
        return personelCode;
    }

    public void setPersonelCode(String personelCode) {
        this.personelCode = personelCode;
    }

    public Long getJobId() {
        return jobId;
    }

    public void setJobId(Long jobId) {
        this.jobId = jobId;
    }

    public String getJobTitle() {
        return jobTitle;
    }

    public void setJobTitle(String jobTitle) {
        this.jobTitle = jobTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        PersonMinDTO personDTO = (PersonMinDTO) o;
        if (personDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), personDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "PersonDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", family='" + getFamily() + "'" +
            ", nationalId='" + getNationalId() + "'" +
            ", personelCode='" + getPersonelCode() + "'" +
            ", job=" + getJobId() +
            ", jobTitle='" + getJobTitle() + "'" +
            "}";
    }
}
