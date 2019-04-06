package com.marineindustryproj.service.dto.customs;

import java.io.Serializable;
import java.util.Objects;

import com.marineindustryproj.domain.Organization;
import com.marineindustryproj.domain.SkillableLevelOfSkill;

/**
 * A DTO for the EducationalModule entity.
 */
public class EducationalModuleMinDTO implements Serializable {

    public EducationalModuleMinDTO(Long id,
                                   String code,
                                   String title,
                                   Integer learningTimeTheorical,
                                   Integer learningTimePractical,
                                   SkillableLevelOfSkill skillableLevelOfSkill,
                                   Organization organization) {
        this.id = id;
        this.code = code;
        this.title = title;
        this.learningTimeTheorical = learningTimeTheorical;
        this.learningTimePractical = learningTimePractical;
        this.skillableLevelOfSkillId = skillableLevelOfSkill.getId();
        this.skillableLevelOfSkillTitle = skillableLevelOfSkill.getTitle();
        this.organizationId = organization.getId();
        this.organizationTitle = organization.getTitle();
    }

    private Long id;

    private String code;

    private String title;

    private Integer learningTimeTheorical;

    private Integer learningTimePractical;

    private Long skillableLevelOfSkillId;

    private String skillableLevelOfSkillTitle;

    private Long organizationId;

    private String organizationTitle;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Integer getLearningTimeTheorical() {
        return learningTimeTheorical;
    }

    public void setLearningTimeTheorical(Integer learningTimeTheorical) {
        this.learningTimeTheorical = learningTimeTheorical;
    }

    public Integer getLearningTimePractical() {
        return learningTimePractical;
    }

    public void setLearningTimePractical(Integer learningTimePractical) {
        this.learningTimePractical = learningTimePractical;
    }

    /*public Set<ScientificWorkGroupDTO> getScientificWorkGroups() {
        return scientificWorkGroups;
    }

    public void setScientificWorkGroups(Set<ScientificWorkGroupDTO> scientificWorkGroups) {
        this.scientificWorkGroups = scientificWorkGroups;
    }*/

    public Long getSkillableLevelOfSkillId() {
        return skillableLevelOfSkillId;
    }

    public void setSkillableLevelOfSkillId(Long skillableLevelOfSkillId) {
        this.skillableLevelOfSkillId = skillableLevelOfSkillId;
    }

    public String getSkillableLevelOfSkillTitle() {
        return skillableLevelOfSkillTitle;
    }

    public void setSkillableLevelOfSkillTitle(String skillableLevelOfSkillTitle) {
        this.skillableLevelOfSkillTitle = skillableLevelOfSkillTitle;
    }

    public Long getOrganizationId() {
        return organizationId;
    }

    public void setOrganizationId(Long organizationId) {
        this.organizationId = organizationId;
    }

    public String getOrganizationTitle() {
        return organizationTitle;
    }

    public void setOrganizationTitle(String organizationTitle) {
        this.organizationTitle = organizationTitle;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        EducationalModuleMinDTO educationalModuleDTO = (EducationalModuleMinDTO) o;
        if (educationalModuleDTO.getId() == null || getId() == null) {
            return false;
        }
        return Objects.equals(getId(), educationalModuleDTO.getId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getId());
    }

    @Override
    public String toString() {
        return "EducationalModuleMinDTO{" +
            "id=" + getId() +
            ", code=" + getCode() +
            ", title='" + getTitle() + "'" +
            ", learningTimeTheorical=" + getLearningTimeTheorical() +
            ", learningTimePractical=" + getLearningTimePractical() +
            ", skillableLevelOfSkill=" + getSkillableLevelOfSkillId() +
            ", skillableLevelOfSkill='" + getSkillableLevelOfSkillTitle() + "'" +
            ", organization=" + getOrganizationId() +
            ", organization='" + getOrganizationTitle() + "'" +
            "}";
    }
}
