package com.marineindustryproj.service.parseExcel;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.Calendar;
import com.marineindustryproj.domain.Person;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.impl.PersonServiceImpl;
import com.marineindustryproj.service.mapper.PersonMapper;
import com.marineindustryproj.service.util.ConvertDateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@Transactional
public class PersonExcel {

    private static QualificationService qualificationService = null;

    private static FieldOfStudyService fieldOfStudyService = null;

    private static EmploymentTypeService employmentTypeService = null;

    private static WorkGroupService workGroupService = null;

    private static WorkIndustryService workIndustryService = null;

    private static JobService jobService = null;

    private static PersonService personService = null;

    private static PersonMapper personMapper = null;

    private static ImportUtilities importUtilities = null;

    private static UserService userService = null;

    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    public PersonExcel(QualificationService qualificationService,
                       FieldOfStudyService fieldOfStudyService, EmploymentTypeService employmentTypeService, WorkGroupService workGroupService, WorkIndustryService workIndustryService, JobService jobService,
                       PersonService personService,
                       PersonMapper personMapper,
                       UserService userService,
                       ImportUtilities importUtilities) {
        this.qualificationService = qualificationService;
        this.fieldOfStudyService = fieldOfStudyService;
        this.employmentTypeService = employmentTypeService;
        this.workGroupService = workGroupService;
        this.workIndustryService = workIndustryService;
        this.jobService = jobService;
        this.personService = personService;
        this.importUtilities = importUtilities;
        this.userService = userService;
        this.personMapper = personMapper;
    }
    public PersonExcel(){}


    public StringBuilder parsePerson(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            ConvertDateUtil convertDateUtil = new ConvertDateUtil();
            FileInputStream excelFile = new FileInputStream(new File(this.rootLocation.resolve(fileName).toAbsolutePath().toString()));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheet("Sheet1");
            List<String> nationalIds = personService.findAllFromCache().stream().map(a -> a.getNationalId()).collect(Collectors.toList());
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                int rowNum = currentRow.getRowNum();
                if (rowNum == 0)
                    continue;
                try {
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    PersonDTO personDTO = new PersonDTO();
                    boolean hasError = false;
                    while (cellIterator.hasNext()) {
                        if (hasError)
                            break;
                        Cell currentCell = cellIterator.next();
                        int columnNum = currentCell.getColumnIndex();

                        switch (columnNum) {
                            case 0: //personel_code
                                Double personelCode = Double.valueOf(0);
                                //noinspection deprecation
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    personelCode = currentCell.getNumericCellValue();
                                else
                                    personelCode = Double.valueOf(currentCell.getStringCellValue());
                                /*if (personelCode == 0) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "personelCode",
                                        1,
                                        "",
                                        sb);
                                    hasError = true;
                                    continue;
                                }*/
                                /*if(ids.contains(personelCode.longValue())){
                                    hasError = true;
                                    continue;
                                }*/
                                /*if (!isNumeric(jobKey)) {
                                    addError(rowNum,
                                             columnNum,
                                             "JobKey",
                                             2,
                                             sb);
                                    hasError = true;
                                    continue;
                                }*/
                                personDTO.setPersonelCode(String.valueOf(personelCode.longValue()));
                                //personDTO.setId(personelCode.longValue());
                                break;
                            case 1: //name
                                String name = "";
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    name = String.valueOf(currentCell.getNumericCellValue());
                                else
                                    name = currentCell.getStringCellValue();
                                if (name.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "name",
                                        1,
                                        "",
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setName(importUtilities.correctString(name));
                                break;
                            case 2: //family
                                String family = "";
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    family = String.valueOf(currentCell.getNumericCellValue());
                                else
                                    family = currentCell.getStringCellValue();
                                if (family.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "family",
                                        1,
                                        "",
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setFamily(importUtilities.correctString(family));
                                break;
                            case 3: //fatherName
                                String fatherName = "";
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    fatherName = String.valueOf(currentCell.getNumericCellValue());
                                else
                                    fatherName = currentCell.getStringCellValue();
                                if (fatherName.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "fatherName",
                                        1,
                                        "",
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setFatherName(importUtilities.correctString(fatherName));
                                break;
                            case 4: //certificateNumber
                                Double certificateNumber = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    certificateNumber = currentCell.getNumericCellValue();
                                else
                                    certificateNumber = Double.valueOf(currentCell.getStringCellValue());
                                if (certificateNumber == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "certificateNumber",
                                             1,
                                             "",
                                             sb);
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setCertificateNumber(String.valueOf(certificateNumber.longValue()));
                                break;
                            case 5: //nationalId
                                Double nationalId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    nationalId = currentCell.getNumericCellValue();
                                else
                                    nationalId = Double.valueOf(currentCell.getStringCellValue());
                                if (nationalId == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "nationalId",
                                             1,
                                             "",
                                             sb);
                                    hasError = true;
                                    continue;
                                }
                                String nationalIdStr = String.valueOf(nationalId.longValue());
                                personDTO.setNationalId(StringUtils.leftPad(nationalIdStr,10, '0'));
                                if(nationalIds.contains(personDTO.getNationalId())){
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setId(nationalId.longValue());
                                /*if (!isNumeric(jobCode)) {
                                    addError(rowNum,
                                             columnNum,
                                             "jobCode",
                                             2,
                                             sb);
                                    hasError = true;
                                    continue;
                                }*/

                                break;
                            case 6: // birthDate
                                Double birthDate = Double.valueOf(0);
                                try {
                                    if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                        birthDate = currentCell.getNumericCellValue();
                                    else
                                        birthDate = Double.valueOf(currentCell.getStringCellValue());
                                    if (birthDate == 0) {
                                        importUtilities.addError(rowNum,
                                                                 columnNum,
                                                                 "birthDate",
                                                                 1,
                                                                 "",
                                                                 sb);
                                        hasError = true;
                                        continue;
                                    }
                                    String birthDateStr = String.valueOf(birthDate.longValue());
                                    int year = Integer.valueOf(birthDateStr.substring(0,
                                                                                      4));
                                    int month = Integer.valueOf(birthDateStr.substring(4,
                                                                                       6));
                                    int day = Integer.valueOf(birthDateStr.substring(6,
                                                                                     8));
                                    ZonedDateTime a = importUtilities.getZonedDateTime(year,
                                                                       month,
                                                                       day);
                                    personDTO.setBirthDate(a);
                                }
                                catch (Exception ex){
                                    importUtilities.addError(rowNum, columnNum, "birthDate", 5, ex.getMessage(), sb);
                                    personDTO.setBirthDate(ZonedDateTime.now());
                                }
                                break;
                            case 7: // jobId
                                Double jobId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    jobId = currentCell.getNumericCellValue();
                                else
                                    jobId = Double.valueOf(currentCell.getStringCellValue());
                                if (jobId != 0) {
                                    Optional<JobDTO> jobDTO =
                                        jobService.findOne(jobId.longValue());
                                    if(jobDTO.isPresent())
                                        personDTO.setJobId(jobId.longValue());
                                    else {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "jobId",
                                            4,
                                            "",
                                            sb);
                                        hasError = true;
                                        continue;
                                    }
                                }
                                else{
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "jobId",
                                        1,
                                        "",
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                break;
                            case 8: // practicalJobId
                                Double practicalJobId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    practicalJobId = currentCell.getNumericCellValue();
                                else
                                    practicalJobId = Double.valueOf(currentCell.getStringCellValue());
                                if (practicalJobId != 0) {
                                    Optional<JobDTO> jobDTO =
                                        jobService.findOne(practicalJobId.longValue());
                                    if(jobDTO.isPresent())
                                        personDTO.setPracticaljobId(practicalJobId.longValue());
                                    else {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "practicalJobId",
                                            4,
                                            "",
                                            sb);
                                        hasError = true;
                                        continue;
                                    }
                                }
                                else{
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "practicalJobId",
                                        1,
                                        "",
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                break;
                            case 9: // employmentDate
                                Double employmentDate = Double.valueOf(0);
                                try {
                                    if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                        employmentDate = currentCell.getNumericCellValue();
                                    else
                                        employmentDate = Double.valueOf(currentCell.getStringCellValue());
                                    if(employmentDate == 0)
                                        break;
                                    String employmentDateStr = String.valueOf(employmentDate.longValue());
                                    int employmentDateYear = Integer.valueOf(employmentDateStr.substring(0,
                                                                                                         4));
                                    int employmentDateMonth = Integer.valueOf(employmentDateStr.substring(4,
                                                                                                          6));
                                    int employmentDateDay = Integer.valueOf(employmentDateStr.substring(6,
                                                                                                        8));
                                    ZonedDateTime b = importUtilities.getZonedDateTime(employmentDateYear,
                                                                       employmentDateMonth,
                                                                       employmentDateDay);
                                    personDTO.setEmploymentDate(b);
                                }
                                catch (Exception ex){
                                    importUtilities.addError(rowNum, columnNum, "employmentDate", 5, ex.getMessage(), sb);
                                    personDTO.setEmploymentDate(ZonedDateTime.now());
                                }
                                break;
                            case 10: // lastQualificationId
                                Double lastQualificationId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    lastQualificationId = currentCell.getNumericCellValue();
                                else
                                    lastQualificationId = Double.valueOf(currentCell.getStringCellValue());
                                if (lastQualificationId != 0) {
                                    Optional<QualificationDTO> qualificationDTO =
                                        qualificationService.findOne(lastQualificationId.longValue());
                                    if(qualificationDTO.isPresent())
                                        personDTO.setLastQualificationId(lastQualificationId.longValue());
                                }
                                break;
                            case 11: // lastFieldOfStudyId
                                Double lastFieldOfStudyId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    lastFieldOfStudyId = currentCell.getNumericCellValue();
                                else
                                    lastFieldOfStudyId = Double.valueOf(currentCell.getStringCellValue());
                                if (lastFieldOfStudyId != 0) {
                                    Optional<FieldOfStudyDTO> fieldOfStudyDTO =
                                        fieldOfStudyService.findOne(lastFieldOfStudyId.longValue());
                                    if(fieldOfStudyDTO.isPresent())
                                        personDTO.setLastFieldOfStudyId(lastFieldOfStudyId.longValue());
                                }
                                break;
                            case 12: // employmentTypeId
                                Double employmentTypeId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    employmentTypeId = currentCell.getNumericCellValue();
                                else
                                    employmentTypeId = Double.valueOf(currentCell.getStringCellValue());
                                if (employmentTypeId != 0) {
                                    Optional<EmploymentTypeDTO> employmentTypeDTO =
                                        employmentTypeService.findOne(employmentTypeId.longValue());
                                    if(employmentTypeDTO.isPresent())
                                        personDTO.setEmploymentTypeId(employmentTypeId.longValue());
                                }
                                break;
                            case 13: // workGroupId
                                Double workGroupId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    workGroupId = currentCell.getNumericCellValue();
                                else
                                    workGroupId = Double.valueOf(currentCell.getStringCellValue());
                                if (workGroupId != 0) {
                                    Optional<WorkGroupDTO> workGroupDTO =
                                        workGroupService.findOne(workGroupId.longValue());
                                    if(workGroupDTO.isPresent())
                                        personDTO.setWorkGroupId(workGroupId.longValue());
                                }
                                break;
                        }
                    }
                    if(hasError)
                        continue;
                    personDTO.setCreateDate(ZonedDateTime.now());
                    personDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    personDTO.setArchived(false);
                    personDTO.setStatus(0);
                    personDTO = personService.save(personDTO, false);

                    Person person = personMapper.toEntity(personDTO);

                    /*PersonServiceImpl.saveNewUser(person,
                                                  userService);*/
                }
                catch (Exception ex){
                    importUtilities.addError(rowNum, 0, "", 3, ex.getMessage(), sb);
                }
            }
            return sb;
        } catch (FileNotFoundException e) {
            sb.append(e.getMessage());
        } catch (IOException e) {
            sb.append(e.getMessage());
        }
        return sb;
    }


}

