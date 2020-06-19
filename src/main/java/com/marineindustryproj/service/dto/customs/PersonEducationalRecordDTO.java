package com.marineindustryproj.service.dto.customs;

import com.marineindustryproj.domain.TeachApproach;
import com.marineindustryproj.service.dto.*;

import java.io.Serializable;
import java.util.List;

/**
 * A DTO for the FinalNiazsanjiReport entity.
 */
public class PersonEducationalRecordDTO implements Serializable {

    private PersonDTO person;

    private List<JobRecordDTO> jobRecords;

    private List<EducationalRecordDTO> educationalRecords;

    private List<ResearchRecordDTO> researchRecords;

    private List<TeachingRecordDTO> teachingRecords;

    private List<HomePagePersonEducationalModule> homePagePersonEducationalModules;

    private String image;

    public PersonDTO getPerson() {
        return person;
    }

    public void setPerson(PersonDTO person) {
        this.person = person;
    }

    public List<JobRecordDTO> getJobRecords() {
        return jobRecords;
    }

    public void setJobRecords(List<JobRecordDTO> jobRecords) {
        this.jobRecords = jobRecords;
    }

    public List<EducationalRecordDTO> getEducationalRecords() {
        return educationalRecords;
    }

    public void setEducationalRecords(List<EducationalRecordDTO> educationalRecords) {
        this.educationalRecords = educationalRecords;
    }

    public List<ResearchRecordDTO> getResearchRecords() {
        return researchRecords;
    }

    public void setResearchRecords(List<ResearchRecordDTO> researchRecords) {
        this.researchRecords = researchRecords;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public List<HomePagePersonEducationalModule> getHomePagePersonEducationalModules() {
        return homePagePersonEducationalModules;
    }

    public void setHomePagePersonEducationalModules(List<HomePagePersonEducationalModule> homePagePersonEducationalModules) {
        this.homePagePersonEducationalModules = homePagePersonEducationalModules;
    }

    public List<TeachingRecordDTO> getTeachingRecords() {
        return teachingRecords;
    }

    public void setTeachingRecords(List<TeachingRecordDTO> teachingRecords) {
        this.teachingRecords = teachingRecords;
    }
}
