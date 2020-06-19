package com.marineindustryproj.web.rest;

import com.codahale.metrics.annotation.Timed;
import com.marineindustryproj.domain.User;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.dto.customs.PersonEducationalRecordDTO;
import io.github.jhipster.service.filter.LongFilter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.InetAddress;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ReportResource {
    private final Logger log = LoggerFactory.getLogger(RasteResource.class);

    private PersonService personService;

    private FinalNiazsanjiReportService finalNiazsanjiReportService;

    private JobRecordQueryService jobRecordQueryService;

    private EducationalRecordQueryService educationalRecordQueryService;

    private ResearchRecordQueryService researchRecordQueryService;

    private TeachingRecordQueryService teachingRecordQueryService;

    private UserService userService;

    private Environment environment;

    ReportResource(PersonService personService, FinalNiazsanjiReportService finalNiazsanjiReportService, JobRecordQueryService jobRecordQueryService, EducationalRecordQueryService educationalRecordQueryService, ResearchRecordQueryService researchRecordQueryService, TeachingRecordQueryService teachingRecordQueryService, UserService userService, Environment environment){
        this.personService = personService;
        this.finalNiazsanjiReportService = finalNiazsanjiReportService;
        this.jobRecordQueryService = jobRecordQueryService;
        this.educationalRecordQueryService = educationalRecordQueryService;
        this.researchRecordQueryService = researchRecordQueryService;
        this.teachingRecordQueryService = teachingRecordQueryService;
        this.userService = userService;
        this.environment = environment;
    }
    @GetMapping("report/personEducationalRecordReport/{personId}")
    @Timed
    public PersonEducationalRecordDTO personEducationalRecordReport(@PathVariable Long personId) throws Exception {
        LongFilter personFilter = new LongFilter();
        personFilter.setEquals(personId);

        PersonEducationalRecordDTO personEducationalRecordDTO = new PersonEducationalRecordDTO();

        Optional<PersonDTO> personDTO = personService.findOne(personId);
        if(!personDTO.isPresent())
            throw new Exception("personId is not valid");

        personEducationalRecordDTO.setPerson(personDTO.get());

        personEducationalRecordDTO.setHomePagePersonEducationalModules(finalNiazsanjiReportService.getHomePagePersonEducationalModules(personId));

        JobRecordCriteria jobRecordCriteria = new JobRecordCriteria();
        jobRecordCriteria.setPersonId(personFilter);
        personEducationalRecordDTO.setJobRecords(jobRecordQueryService.findByCriteria(jobRecordCriteria));


        EducationalRecordCriteria educationalRecordCriteria = new EducationalRecordCriteria();
        educationalRecordCriteria.setPersonId(personFilter);
        personEducationalRecordDTO.setEducationalRecords(educationalRecordQueryService.findByCriteria(educationalRecordCriteria));

        ResearchRecordCriteria researchRecordCriteria = new ResearchRecordCriteria();
        researchRecordCriteria.setPersonId(personFilter);
        personEducationalRecordDTO.setResearchRecords(researchRecordQueryService.findByCriteria(researchRecordCriteria));

        TeachingRecordCriteria teachingRecordCriteria = new TeachingRecordCriteria();
        teachingRecordCriteria.setPersonId(personFilter);
        personEducationalRecordDTO.setTeachingRecords(teachingRecordQueryService.findByCriteria(teachingRecordCriteria));

        Optional<User> userOptional = userService.getUserWithAuthoritiesByPersonId(personId);
        if(userOptional.isPresent() && userOptional.get().getImageUrl() != null){
            String ipAddress = InetAddress.getLocalHost().getHostAddress();
            String port = environment.getProperty("server.port");
            String imageUrl = userOptional.get().getImageUrl();
            String finalUrl = "http://" + ipAddress + ":" + port + "/" + imageUrl;
            personEducationalRecordDTO.setImage(finalUrl);
        }

        return personEducationalRecordDTO;

    }
}
