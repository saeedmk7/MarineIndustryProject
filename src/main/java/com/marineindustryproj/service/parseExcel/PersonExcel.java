package com.marineindustryproj.service.parseExcel;

import com.marineindustryproj.domain.Qualification;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import com.marineindustryproj.service.util.ConvertDateUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.*;

import java.time.format.DateTimeFormatter;
import java.util.*;
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

    private static ImportUtilities importUtilities = null;

    private static UserService userService = null;

    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    public PersonExcel(QualificationService qualificationService,
                       FieldOfStudyService fieldOfStudyService, EmploymentTypeService employmentTypeService, WorkGroupService workGroupService, WorkIndustryService workIndustryService, JobService jobService,
                       PersonService personService,
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
    }
    public PersonExcel(){}


    public StringBuilder parsePerson(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {
            ConvertDateUtil convertDateUtil = new ConvertDateUtil();
            FileInputStream excelFile = new FileInputStream(new File(this.rootLocation.resolve(fileName).toAbsolutePath().toString()));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheet("Sheet1");
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
                                if (personelCode == 0) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "personelCode",
                                        1,
                                        sb);
                                    hasError = true;
                                    continue;
                                }
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
                                personDTO.setId(personelCode.longValue());
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
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setName(name);
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
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setFamily(family);
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
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                personDTO.setFatherName(fatherName);
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
                                             sb);
                                    hasError = true;
                                    continue;
                                }
                                /*if (!isNumeric(jobCode)) {
                                    addError(rowNum,
                                             columnNum,
                                             "jobCode",
                                             2,
                                             sb);
                                    hasError = true;
                                    continue;
                                }*/
                                String nationalIdStr = String.valueOf(nationalId.longValue());
                                personDTO.setNationalId(StringUtils.leftPad(nationalIdStr,10, '0'));
                                break;
                            case 6: // birthDate
                                String birthDate = "";
                                birthDate = currentCell.getStringCellValue();
                                if (birthDate.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "birthDate",
                                        1,
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                String[] seperated = birthDate.split("-");
                                ConvertDateUtil.YearMonthDate yearMonthDate = new ConvertDateUtil.YearMonthDate(Integer.valueOf(seperated[0]),Integer.valueOf(seperated[1]),Integer.valueOf(seperated[2]));
                                ConvertDateUtil.YearMonthDate gregorianYearMonthDateBirthDate =  ConvertDateUtil.jalaliToGregorian(yearMonthDate);

                                //DateTimeFormatter parserEmploymentDate = DateTimeFormatter.ofPattern("YYYY/MM/DD");
                                ZonedDateTime a = ZonedDateTime.of(gregorianYearMonthDateBirthDate.getYear(),gregorianYearMonthDateBirthDate.getMonth(),gregorianYearMonthDateBirthDate.getDate(),0,0,0,0, ZoneId.systemDefault()); //) ZonedDateTime. (LocalDate.parse(jalaliYearMonthDateEmploymentDate.toString(), parserEmploymentDate));
                                personDTO.setBirthDate(a);
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
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                break;
                            case 9: // employmentDate
                                String employmentDate = "";
                                employmentDate = currentCell.getStringCellValue();
                                if (employmentDate.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "employmentDate",
                                        1,
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                String[] seperatedEmploymentDate = employmentDate.split("-");
                                ConvertDateUtil.YearMonthDate yearMonthDateEmploymentDate = new ConvertDateUtil.YearMonthDate(Integer.valueOf(seperatedEmploymentDate[0]),Integer.valueOf(seperatedEmploymentDate[1]),Integer.valueOf(seperatedEmploymentDate[2]));
                                ConvertDateUtil.YearMonthDate gregorianYearMonthDateEmploymentDate =  ConvertDateUtil.jalaliToGregorian(yearMonthDateEmploymentDate);

                                //DateTimeFormatter parserEmploymentDate = DateTimeFormatter.ofPattern("YYYY/MM/DD");
                                ZonedDateTime b = ZonedDateTime.of(gregorianYearMonthDateEmploymentDate.getYear(),gregorianYearMonthDateEmploymentDate.getMonth(),gregorianYearMonthDateEmploymentDate.getDate(),0,0,0,0, ZoneId.systemDefault()); //) ZonedDateTime. (LocalDate.parse(jalaliYearMonthDateEmploymentDate.toString(), parserEmploymentDate));
                                personDTO.setEmploymentDate(b);
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
                        }
                    }
                    personDTO.setCreateDate(ZonedDateTime.now());
                    personDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    personDTO.setArchived(false);
                    personDTO.setStatus(0);
                    personDTO = personService.save(personDTO);

                    UserDTO userDTO = new UserDTO();
                    userDTO.setActivated(true);
                    userDTO.setCreatedBy(SecurityUtils.getCurrentUserLogin().get());
                    userDTO.setLastModifiedBy(SecurityUtils.getCurrentUserLogin().get());
                    userDTO.setCreatedDate(Instant.now());
                    userDTO.setLastModifiedDate(Instant.now());
                    userDTO.setEmail(personDTO.getNationalId() + "@amoozesh.com");
                    userDTO.setLangKey("fa");
                    userDTO.setLogin(personDTO.getNationalId());
                    userDTO.setPassword("123456");
                    userDTO.setPersonId(personDTO.getId());
                    Set<String> authorities = new HashSet<String>();
                    authorities.add("ROLE_USER");
                    userDTO.setAuthorities(authorities);

                    userService.createUser(userDTO);

                }
                catch (Exception ex){
                    importUtilities.addError(rowNum, 0, "", 3, sb);
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

