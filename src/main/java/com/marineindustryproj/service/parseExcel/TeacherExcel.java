package com.marineindustryproj.service.parseExcel;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
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
import java.util.HashSet;
import java.util.Iterator;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class TeacherExcel {

    private static QualificationService qualificationService = null;

    private static FieldOfStudyService fieldOfStudyService = null;

    private static EmploymentTypeService employmentTypeService = null;

    private static WorkGroupService workGroupService = null;

    private static WorkIndustryService workIndustryService = null;

    private static JobService jobService = null;

    private static PersonService personService = null;

    private static ImportUtilities importUtilities = null;

    private static UserService userService = null;

    private static ServiceUnitService serviceUnitService = null;

    private static AcademicRankService academicRankService = null;

    private static TeacherService teacherService = null;

    private final Path rootLocation = Paths.get("upload-dir");
    @Autowired
    public TeacherExcel(QualificationService qualificationService,
                        FieldOfStudyService fieldOfStudyService, EmploymentTypeService employmentTypeService, WorkGroupService workGroupService, WorkIndustryService workIndustryService, JobService jobService,
                        PersonService personService,
                        UserService userService,
                        ServiceUnitService serviceUnitService,
                        AcademicRankService academicRankService,
                        TeacherService teacherService,
                        ImportUtilities importUtilities) {
        this.teacherService = teacherService;
        this.serviceUnitService = serviceUnitService;
        this.academicRankService = academicRankService;
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
    public TeacherExcel(){}


    public StringBuilder parseTeacher(String fileName) throws Exception {
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
                    TeacherDTO teacherDTO = new TeacherDTO();
                    boolean hasError = false;
                    while (cellIterator.hasNext()) {
                        if (hasError)
                            break;
                        Cell currentCell = cellIterator.next();
                        int columnNum = currentCell.getColumnIndex();

                        switch (columnNum) {
                            case 0: //phoneNumber
                                Double phoneNumber = Double.valueOf(0);
                                //noinspection deprecation
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    phoneNumber = currentCell.getNumericCellValue();
                                else
                                    phoneNumber = Double.valueOf(currentCell.getStringCellValue());
                                if (phoneNumber == 0) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "phoneNumber",
                                        1,
                                        sb);
                                    hasError = true;
                                    continue;
                                }
                                teacherDTO.setPhoneNumber(String.valueOf(phoneNumber.longValue()));
                                teacherDTO.setId(phoneNumber.longValue());
                                teacherDTO.setCode(phoneNumber.longValue());
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
                                teacherDTO.setName(name);
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
                                teacherDTO.setFamily(family);
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
                                teacherDTO.setFatherName(fatherName);
                                break;
                            case 4: //scientificBasis
                                Double scientificBasis = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    scientificBasis = currentCell.getNumericCellValue();
                                else
                                    scientificBasis = Double.valueOf(currentCell.getStringCellValue());
                                if (scientificBasis == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "scientificBasis",
                                             1,
                                             sb);
                                    /*hasError = true;
                                    continue;*/
                                }
                                teacherDTO.setScientificBasis(scientificBasis.intValue());
                                break;
                            case 5: //inquiry
                                Double inquiry = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    inquiry = currentCell.getNumericCellValue();
                                else
                                    inquiry = Double.valueOf(currentCell.getStringCellValue());
                                if(inquiry == 0)
                                    teacherDTO.setInquiry(false);
                                else
                                    teacherDTO.setInquiry(true);
                                break;
                            case 6: //schoolConfirmation
                                Double schoolConfirmation = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    schoolConfirmation = currentCell.getNumericCellValue();
                                else
                                    schoolConfirmation = Double.valueOf(currentCell.getStringCellValue());
                                if(schoolConfirmation == 0)
                                    teacherDTO.setSchoolConfirmation(false);
                                else
                                    teacherDTO.setSchoolConfirmation(true);
                                break;
                            case 7: //protectiveApproval
                                Double protectiveApproval = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    protectiveApproval = currentCell.getNumericCellValue();
                                else
                                    protectiveApproval = Double.valueOf(currentCell.getStringCellValue());
                                if(protectiveApproval == 0)
                                    teacherDTO.setProtectiveApproval(false);
                                else
                                    teacherDTO.setProtectiveApproval(true);
                                break;
                            case 8: //teachingSubject
                                String teachingSubject = "";
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    teachingSubject = String.valueOf(currentCell.getNumericCellValue());
                                else
                                    teachingSubject = currentCell.getStringCellValue();
                                if (teachingSubject.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "teachingSubject",
                                        1,
                                        sb);
                                    /*hasError = true;
                                    continue;*/
                                }
                                teacherDTO.setTeachingSubject(teachingSubject);
                                break;
                            case 9: // issueDate
                                String issueDate = "";
                                issueDate = currentCell.getStringCellValue();
                                if (issueDate.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "issueDate",
                                        1,
                                        sb);
                                  /*  hasError = true;
                                    continue;*/
                                }
                                String[] seperated = issueDate.split("-");
                                ConvertDateUtil.YearMonthDate yearMonthDate = new ConvertDateUtil.YearMonthDate(Integer.valueOf(seperated[0]),Integer.valueOf(seperated[1]),Integer.valueOf(seperated[2]));
                                ConvertDateUtil.YearMonthDate gregorianYearMonthDateBirthDate =  ConvertDateUtil.jalaliToGregorian(yearMonthDate);

                                //DateTimeFormatter parserEmploymentDate = DateTimeFormatter.ofPattern("YYYY/MM/DD");
                                ZonedDateTime a = ZonedDateTime.of(gregorianYearMonthDateBirthDate.getYear(),gregorianYearMonthDateBirthDate.getMonth(),gregorianYearMonthDateBirthDate.getDate(),0,0,0,0, ZoneId.systemDefault()); //) ZonedDateTime. (LocalDate.parse(jalaliYearMonthDateEmploymentDate.toString(), parserEmploymentDate));
                                teacherDTO.setIssueDate(a);
                                break;
                            case 10: // expirationDate
                                String expirationDate = "";
                                expirationDate = currentCell.getStringCellValue();
                                if (expirationDate.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "expirationDate",
                                        1,
                                        sb);
                                  /*  hasError = true;
                                    continue;*/
                                }
                                String[] expirationDateSeperated = expirationDate.split("-");
                                ConvertDateUtil.YearMonthDate expirationDateYearMonthDate = new ConvertDateUtil.YearMonthDate(Integer.valueOf(expirationDateSeperated[0]),Integer.valueOf(expirationDateSeperated[1]),Integer.valueOf(expirationDateSeperated[2]));
                                ConvertDateUtil.YearMonthDate gregorianYearMonthDateExpirationDate =  ConvertDateUtil.jalaliToGregorian(expirationDateYearMonthDate);

                                //DateTimeFormatter parserEmploymentDate = DateTimeFormatter.ofPattern("YYYY/MM/DD");
                                ZonedDateTime b = ZonedDateTime.of(gregorianYearMonthDateExpirationDate.getYear(),gregorianYearMonthDateExpirationDate.getMonth(),gregorianYearMonthDateExpirationDate.getDate(),0,0,0,0, ZoneId.systemDefault()); //) ZonedDateTime. (LocalDate.parse(jalaliYearMonthDateEmploymentDate.toString(), parserEmploymentDate));
                                teacherDTO.setExpirationDate(b);
                                break;
                            case 11: //licenseNumber
                                Double licenseNumber = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    licenseNumber = currentCell.getNumericCellValue();
                                else
                                    licenseNumber = Double.valueOf(currentCell.getStringCellValue());
                                if (licenseNumber == 0) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "licenseNumber",
                                        1,
                                        sb);
                                    /*hasError = true;
                                    continue;*/
                                }
                                teacherDTO.setLicenseNumber(licenseNumber.intValue());
                                break;
                            case 12: //sessionNumber
                                Double sessionNumber = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    sessionNumber = currentCell.getNumericCellValue();
                                else
                                    sessionNumber = Double.valueOf(currentCell.getStringCellValue());
                                if (sessionNumber == 0) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "sessionNumber",
                                        1,
                                        sb);
                                    /*hasError = true;
                                    continue;*/
                                }
                                teacherDTO.setSessionNumber(sessionNumber.intValue());
                                break;
                            case 13: //status
                                Double status = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    status = currentCell.getNumericCellValue();
                                else
                                    status = Double.valueOf(currentCell.getStringCellValue());
                                if (status == 0) {
                                    importUtilities.addError(rowNum,
                                        columnNum,
                                        "status",
                                        1,
                                        sb);
                                    /*hasError = true;
                                    continue;*/
                                }
                                teacherDTO.setStatus(status.intValue());
                                break;
                            case 14: // lastQualificationId
                                Double lastQualificationId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    lastQualificationId = currentCell.getNumericCellValue();
                                else
                                    lastQualificationId = Double.valueOf(currentCell.getStringCellValue());
                                if (lastQualificationId != 0) {
                                    Optional<QualificationDTO> qualificationDTO =
                                        qualificationService.findOne(lastQualificationId.longValue());
                                    if(qualificationDTO.isPresent())
                                        teacherDTO.setLastQualificationId(lastQualificationId.longValue());
                                }
                                break;
                            case 15: // serviceUnitId
                                Double serviceUnitId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    serviceUnitId = currentCell.getNumericCellValue();
                                else
                                    serviceUnitId = Double.valueOf(currentCell.getStringCellValue());
                                if (serviceUnitId != 0) {
                                    Optional<ServiceUnitDTO> serviceUnitDTO =
                                        serviceUnitService.findOne(serviceUnitId.longValue());
                                    if(serviceUnitDTO.isPresent())
                                        teacherDTO.setServiceUnitId(serviceUnitId.longValue());
                                }
                                break;
                            case 16: // academicRankId
                                Double academicRankId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    academicRankId = currentCell.getNumericCellValue();
                                else
                                    academicRankId = Double.valueOf(currentCell.getStringCellValue());
                                if (academicRankId != 0) {
                                    Optional<AcademicRankDTO> academicRankDTO =
                                        academicRankService.findOne(academicRankId.longValue());
                                    if(academicRankDTO.isPresent())
                                        teacherDTO.setAcademicRankId(academicRankId.longValue());
                                }
                                break;
                            case 17: // lastFieldOfStudyId
                                Double lastFieldOfStudyId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    lastFieldOfStudyId = currentCell.getNumericCellValue();
                                else
                                    lastFieldOfStudyId = Double.valueOf(currentCell.getStringCellValue());
                                if (lastFieldOfStudyId != 0) {
                                    Optional<FieldOfStudyDTO> fieldOfStudyDTO =
                                        fieldOfStudyService.findOne(lastFieldOfStudyId.longValue());
                                    if(fieldOfStudyDTO.isPresent())
                                        teacherDTO.setLastFieldOfStudyId(lastFieldOfStudyId.longValue());
                                }
                                break;
                        }
                    }
                    teacherDTO.setCreateDate(ZonedDateTime.now());
                    teacherDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    teacherDTO.setArchived(false);
                    //teacherDTO.setStatus(0);
                    teacherService.save(teacherDTO);

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

