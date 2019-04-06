package com.marineindustryproj.web.rest.Controllers;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import com.marineindustryproj.domain.Person;
import com.marineindustryproj.repository.PersonRepository;
import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.service.EmploymentTypeService;
import com.marineindustryproj.service.FinalNiazsanjiReportPersonQueryService;
import com.marineindustryproj.service.FinalNiazsanjiReportService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.EmploymentTypeDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportDTO;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonCriteria;
import com.marineindustryproj.service.dto.FinalNiazsanjiReportPersonDTO;
import com.marineindustryproj.service.dto.PersonDTO;
import io.github.jhipster.service.filter.LongFilter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRXlsExporter;
import net.sf.jasperreports.export.ExporterInput;
import net.sf.jasperreports.export.OutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimpleXlsExporterConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ReportController {

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private FinalNiazsanjiReportService finalNiazsanjiReportService;

    @Autowired
    private EducationalModuleService educationalModuleService;

    @Autowired
    private EmploymentTypeService employmentTypeService;

    @Autowired
    private FinalNiazsanjiReportPersonQueryService finalNiazsanjiReportPersonQueryService;




    @RequestMapping(path = "/pdf", method = RequestMethod.GET)
    public String report() throws JRException, IOException {
        InputStream file = new ClassPathResource("reports/report.jrxml").getInputStream();
        //compile to .jasper
        // Compile jrxml file.


        JasperReport jasperReport = JasperCompileManager
            .compileReport(file);

        // Parameters for report
        List<Map<String, Object>> parameters = new ArrayList<>();
        List<Person> personDTOList = personRepository.findAll();
        for (Person item: personDTOList) {
            Map<String, Object> param = new HashMap<>();
            param.put("id",item.getId());
            param.put("name",item.getName());
            param.put("family",item.getFamily());
            param.put("fatherName",item.getFatherName());
            param.put("certificateNumber",item.getCertificateNumber());
            param.put("nationalId",item.getNationalId());
            param.put("birthDate",item.getBirthDate().toString());
            param.put("personelCode",item.getPersonelCode());
            param.put("employmentDate",item.getEmploymentDate().toString());
            param.put("yearOfService",item.getYearOfService());
            parameters.add(param);

        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(parameters);

        Map<String,Object> params = new HashMap<>();
        params.put("datasource",parameters);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,beanCollectionDataSource);


        // Make sure the output directory exists.
        File outDir = new File("C:/jasperoutput");
        outDir.mkdirs();
        JRXlsExporter exporter = new JRXlsExporter();

        ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);

        exporter.setExporterInput(exporterInput);


        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
            "C:/jasperoutput/FirstJasperReport.xls");
        // Output
        exporter.setExporterOutput(exporterOutput);

        //
        SimpleXlsExporterConfiguration configuration = new SimpleXlsExporterConfiguration();
        exporter.setConfiguration(configuration);
        exporter.exportReport();
        // Export to PDF.
        /*JasperExportManager.exportReportToPdfFile(jasperPrint,
                                                  "C:/jasperoutput/myRep.pdf");*/

        System.out.println("Done!");
        return "";
    }
    @RequestMapping(path = "/finalNiazSanjiReport/{finalNiazSanjiReportId}", method = RequestMethod.GET)
    public String finalNiazSanjiReport(@PathVariable Long finalNiazSanjiReportId) throws JRException, IOException {
        InputStream file = new ClassPathResource("reports/finalNiazSanji.jrxml").getInputStream();
        //compile to .jasper
        // Compile jrxml file.


        JasperReport jasperReport = JasperCompileManager
            .compileReport(file);

        FinalNiazsanjiReportDTO finalNiazsanjiReportDTO = finalNiazsanjiReportService.findOne(finalNiazSanjiReportId).get();
        EducationalModuleDTO educationalModuleDTO = educationalModuleService.findOne(finalNiazsanjiReportDTO.getEducationalModuleId()).get();

        // Parameters for report
        List<Map<String, Object>> parameters = new ArrayList<>();
        LongFilter longFilter = new LongFilter();
        longFilter.setEquals(finalNiazSanjiReportId);
        FinalNiazsanjiReportPersonCriteria criteria = new FinalNiazsanjiReportPersonCriteria();
        criteria.setFinalNiazsanjiReportId(longFilter);
        List<FinalNiazsanjiReportPersonDTO> finalNiazsanjiReportPersonDTOS = finalNiazsanjiReportPersonQueryService.findByCriteria(criteria);
        for (FinalNiazsanjiReportPersonDTO item: finalNiazsanjiReportPersonDTOS) {
            Map<String, Object> param = new HashMap<>();
            param.put("educationalModuleTitle",educationalModuleDTO.getTitle());
            param.put("educationalModuleId",finalNiazsanjiReportDTO.getEducationalModuleId());
            param.put("educationalModuleLevel",educationalModuleDTO.getEducationalModuleLevel());
            param.put("educationalModuleHour",educationalModuleDTO.getLearningTimePractical() + educationalModuleDTO.getLearningTimeTheorical());
            PersonDTO personDTO = personService.findOne(item.getPersonId()).get();
            param.put("personFullName",personDTO.getName() + personDTO.getFamily());
            param.put("jobTitle",personDTO.getJobTitle());
            param.put("priceCost",finalNiazsanjiReportDTO.getPriceCost());

            /*Optional<EmploymentTypeDTO> employmentType = employmentTypeService.findOne();
            String employmentTypeTitle =  " ";
            if(employmentType.isPresent())
                employmentTypeTitle = employmentType.get().getTitle();*/
            param.put("employmentTypeTitle"," ");
            parameters.add(param);
        }

        JRBeanCollectionDataSource beanCollectionDataSource = new JRBeanCollectionDataSource(parameters);

        Map<String,Object> params = new HashMap<>();
        params.put("datasource",parameters);


        JasperPrint jasperPrint = JasperFillManager.fillReport(jasperReport, params,beanCollectionDataSource);


        // Make sure the output directory exists.
        File outDir = new File("C:/jasperoutput");
        outDir.mkdirs();
        JRXlsExporter exporter = new JRXlsExporter();

        ExporterInput exporterInput = new SimpleExporterInput(jasperPrint);

        exporter.setExporterInput(exporterInput);


        // ExporterOutput
        OutputStreamExporterOutput exporterOutput = new SimpleOutputStreamExporterOutput(
            "C:/jasperoutput/employmentTypeTitle.xls");
        // Output
        exporter.setExporterOutput(exporterOutput);

        //
        /*SimpleXlsExporterConfiguration configuration = new SimpleXlsExporterConfiguration();
        exporter.setConfiguration(configuration);*/
        exporter.exportReport();
        // Export to PDF.
        /*JasperExportManager.exportReportToPdfFile(jasperPrint,
                                                  "C:/jasperoutput/myRep.pdf");*/

        System.out.println("Done!");
        return "";
    }
}
