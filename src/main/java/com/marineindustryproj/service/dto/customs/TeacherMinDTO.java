package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.Objects;

/**
 * A DTO for the Teacher entity.
 */
public class TeacherMinDTO implements Serializable {

    public TeacherMinDTO(Long id,
                         String name,
                         String family,
                         String phoneNumber) {
        this.id = id;
        this.name = name;
        this.family = family;
        this.phoneNumber = phoneNumber;
    }

    private Long id;

    private String name;

    private String family;

    private String phoneNumber;

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

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        TeacherMinDTO teacherDTO = (TeacherMinDTO) o;
        if (teacherDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), teacherDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "TeacherDTO{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            ", family='" + getFamily() + "'" +
            ", phoneNumber='" + getPhoneNumber() + "'" +
        "}";
    }
}
