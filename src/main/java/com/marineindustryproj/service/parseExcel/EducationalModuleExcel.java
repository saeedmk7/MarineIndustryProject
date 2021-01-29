package com.marineindustryproj.service.parseExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigInteger;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import com.marineindustryproj.domain.SkillableLevelOfSkill;
import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.*;
import com.marineindustryproj.service.dto.*;
import io.github.jhipster.service.filter.StringFilter;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class EducationalModuleExcel {

    private static EducationalModuleService educationalModuleService = null;
    private static SkillableLevelOfSkillService skillableLevelOfSkillService = null;
    private static ScientificWorkGroupService scientificWorkGroupService = null;
    private static OrganizationService organizationService = null;
    private static SecurityLevelService securityLevelService = null;
    private static ImportUtilities importUtilities = null;

    private final Path rootLocation = Paths.get("upload-dir");

    @Autowired
    public EducationalModuleExcel(EducationalModuleService educationalModuleService,
                                  SkillableLevelOfSkillService skillableLevelOfSkillService,
                                  ScientificWorkGroupService scientificWorkGroupService,
                                  OrganizationService organizationService,
                                  SecurityLevelService securityLevelService,
                                  ImportUtilities importUtilities) {
        this.securityLevelService = securityLevelService;
        this.organizationService = organizationService;
        this.educationalModuleService = educationalModuleService;
        this.importUtilities = importUtilities;
        this.skillableLevelOfSkillService = skillableLevelOfSkillService;
        this.scientificWorkGroupService = scientificWorkGroupService;
    }
    public EducationalModuleExcel(){}


    public StringBuilder parseEducationalModule(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {

            FileInputStream excelFile = new FileInputStream(new File(this.rootLocation.resolve(fileName).toAbsolutePath().toString()));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheet("Sheet1");

            List<Long> ids = educationalModuleService.findAllFromCache().stream().map(a -> Long.parseLong(a.getCode())).collect(Collectors.toList());

            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                int rowNum = currentRow.getRowNum();
                boolean isInsertRow = true;
                if (rowNum == 0)
                    continue;
                try {
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    EducationalModuleDTO educationalModuleDTO = new EducationalModuleDTO();
                    boolean hasError = false;
                    while (cellIterator.hasNext()) {
                        if (hasError)
                            break;

                        Cell currentCell = cellIterator.next();
                        int columnNum = currentCell.getColumnIndex();
                        try {
                            switch (columnNum) {
                                case 0: //code
                                    Double code = Double.valueOf(0);
                                    //noinspection deprecation
                                    if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                        code = currentCell.getNumericCellValue();
                                    else
                                        code = Double.valueOf(currentCell.getStringCellValue());
                                    if (code == 0) {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "code",
                                            1,
                                            "",
                                            sb);
                                        hasError = true;
                                        continue;
                                    }
                                    if (ids.contains(code.longValue())) {
                                        Optional<EducationalModuleDTO> educationalModuleDTOOptional = educationalModuleService.findByCode(String.valueOf(code.longValue()));
                                        if (educationalModuleDTOOptional.isPresent()) {
                                            educationalModuleDTO = educationalModuleDTOOptional.get();
                                            isInsertRow = false;
                                            break;
                                        }
                                    }
                                    educationalModuleDTO.setCode(String.valueOf(code.longValue()));
                                    educationalModuleDTO.setId(code.longValue());
                                    break;
                                case 1: //Title
                                    String title = "";
                                    if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                        title = String.valueOf(currentCell.getNumericCellValue());
                                    else
                                        title = currentCell.getStringCellValue();
                                    if (title.isEmpty()) {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "title",
                                            1,
                                            "",
                                            sb);
                                        hasError = true;
                                        continue;
                                    }
                                    educationalModuleDTO.setTitle(importUtilities.correctString(title));
                                    break;
                                case 2: //learningTimeTheorical
                                    Double learningTimeTheorical = Double.valueOf(0);
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            learningTimeTheorical = currentCell.getNumericCellValue();
                                        else
                                            learningTimeTheorical = Double.valueOf(currentCell.getStringCellValue());
                                    } catch (Exception ex) {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "learningTimeTheorical",
                                            3,
                                            ex.getMessage(),
                                            sb);
                                    }
                                    educationalModuleDTO.setLearningTimeTheorical(learningTimeTheorical.intValue());
                                    break;
                                case 3: //learningTimePractical
                                    Double learningTimePractical = Double.valueOf(0);
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            learningTimePractical = currentCell.getNumericCellValue();
                                        else
                                            learningTimePractical = Double.valueOf(currentCell.getStringCellValue());
                                    }
                                    catch (Exception ex)
                                    {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "learningTimePractical",
                                            3,
                                            ex.getMessage(),
                                            sb);
                                    }
                                    educationalModuleDTO.setLearningTimePractical(learningTimePractical.intValue());
                                    break;
                                case 4: //skillableLevelOfSkillTitle
                                    String skillableLevelOfSkillTitle = "";
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            skillableLevelOfSkillTitle = String.valueOf(currentCell.getNumericCellValue());
                                        else
                                            skillableLevelOfSkillTitle = currentCell.getStringCellValue();
                                        if (skillableLevelOfSkillTitle.isEmpty()) {
                                            importUtilities.addError(rowNum,
                                                columnNum,
                                                "skillableLevelOfSkillTitle",
                                                1,
                                                "",
                                                sb);
                                            hasError = true;
                                            continue;
                                        }
                                        skillableLevelOfSkillTitle = importUtilities.correctAndRemoveExtraCharsString(skillableLevelOfSkillTitle);
                                        Optional<SkillableLevelOfSkillDTO> skillableLevelOfSkillDTO =
                                            skillableLevelOfSkillService.findByTitle(skillableLevelOfSkillTitle);

                                        if (skillableLevelOfSkillDTO.isPresent()) {
                                            educationalModuleDTO.setSkillableLevelOfSkillId(skillableLevelOfSkillDTO.get().getId());
                                        } else {
                                            SkillableLevelOfSkillDTO newSkillDTO = new SkillableLevelOfSkillDTO();
                                            newSkillDTO.setTitle(skillableLevelOfSkillTitle);
                                            newSkillDTO.setCreateDate(ZonedDateTime.now());
                                            newSkillDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                                            newSkillDTO = skillableLevelOfSkillService.save(newSkillDTO);
                                            educationalModuleDTO.setSkillableLevelOfSkillId(newSkillDTO.getId());
                                        }
                                    }
                                    catch (Exception ex)
                                    {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "skillableLevelOfSkillId",
                                            3,
                                            ex.getMessage(),
                                            sb);
                                        hasError = true;
                                        continue;
                                    }
                                    break;
                                case 5: //scientificWorkGroupTitle
                                    String scientificWorkGroupTitle = "";
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            scientificWorkGroupTitle = String.valueOf(currentCell.getNumericCellValue());
                                        else
                                            scientificWorkGroupTitle = currentCell.getStringCellValue();
                                        if (scientificWorkGroupTitle.isEmpty()) {
                                            break;
                                        }
                                        scientificWorkGroupTitle = importUtilities.correctAndRemoveExtraCharsString(scientificWorkGroupTitle);
                                        Optional<ScientificWorkGroupDTO> scientificWorkGroupDTO =
                                            scientificWorkGroupService.findByTitle(scientificWorkGroupTitle);
                                        ScientificWorkGroupDTO scientificWorkGroup;
                                        if (!scientificWorkGroupDTO.isPresent()) {
                                            ScientificWorkGroupDTO newScientific = new ScientificWorkGroupDTO();
                                            newScientific.setTitle(scientificWorkGroupTitle);
                                            newScientific.setCreateDate(ZonedDateTime.now());
                                            newScientific.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                                            scientificWorkGroup = scientificWorkGroupService.save(newScientific);
                                        }
                                        else
                                            scientificWorkGroup = scientificWorkGroupDTO.get();
                                        Set<ScientificWorkGroupDTO> groupDTOSet = educationalModuleDTO.getScientificWorkGroups();
                                        groupDTOSet.add(scientificWorkGroup);
                                        educationalModuleDTO.setScientificWorkGroups(groupDTOSet);
                                    }
                                    catch (Exception ex)
                                    {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "scientificWorkGroupId",
                                            3,
                                            ex.getMessage(),
                                            sb);
                                        continue;
                                    }
                                    break;
                                case 6: //organizationTitle
                                    String organizationTitle = "";
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            organizationTitle = String.valueOf(currentCell.getNumericCellValue());
                                        else
                                            organizationTitle = currentCell.getStringCellValue();
                                        if (organizationTitle.isEmpty()) {
                                            break;
                                        }
                                        organizationTitle = importUtilities.correctAndRemoveExtraCharsString(organizationTitle);
                                        Optional<OrganizationDTO> organizationDTO =
                                            organizationService.findByTitle(organizationTitle);

                                        if (organizationDTO.isPresent()) {
                                            educationalModuleDTO.setOrganizationId(organizationDTO.get().getId());
                                        } else {
                                            OrganizationDTO newScientific = new OrganizationDTO();
                                            newScientific.setTitle(organizationTitle);
                                            newScientific.setCreateDate(ZonedDateTime.now());
                                            newScientific.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                                            newScientific = organizationService.save(newScientific);
                                            educationalModuleDTO.setOrganizationId(newScientific.getId());
                                        }
                                    }
                                    catch (Exception ex)
                                    {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "organizationId",
                                            3,
                                            ex.getMessage(),
                                            sb);
                                        continue;
                                    }
                                    break;
                                case 7: //securityLevelTitle
                                    String securityLevelTitle = "";
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            securityLevelTitle = String.valueOf(currentCell.getNumericCellValue());
                                        else
                                            securityLevelTitle = currentCell.getStringCellValue();
                                        if (securityLevelTitle.isEmpty()) {
                                            break;
                                        }
                                        securityLevelTitle = importUtilities.correctAndRemoveExtraCharsString(securityLevelTitle);
                                        Optional<SecurityLevelDTO> securityLevelDTO =
                                            securityLevelService.findByTitle(securityLevelTitle);

                                        if (securityLevelDTO.isPresent()) {
                                            educationalModuleDTO.setSecurityLevelId(securityLevelDTO.get().getId());
                                        } else {
                                            SecurityLevelDTO newScientific = new SecurityLevelDTO();
                                            newScientific.setTitle(securityLevelTitle);
                                            newScientific.setCreateDate(ZonedDateTime.now());
                                            newScientific.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                                            newScientific = securityLevelService.save(newScientific);
                                            educationalModuleDTO.setSecurityLevelId(newScientific.getId());
                                        }
                                    }
                                    catch (Exception ex)
                                    {
                                        importUtilities.addError(rowNum,
                                            columnNum,
                                            "securityLevelId",
                                            3,
                                            ex.getMessage(),
                                            sb);
                                        continue;
                                    }
                                    break;
                                case 8: //goalsText
                                    String goalsText = "";
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            goalsText = String.valueOf(currentCell.getNumericCellValue());
                                        else
                                            goalsText = currentCell.getStringCellValue();
                                        if (goalsText.isEmpty()) {
                                            importUtilities.addError(rowNum,
                                                columnNum,
                                                "goalsText",
                                                1,
                                                "",
                                                sb);
                                        }
                                        educationalModuleDTO.setGoalsText(importUtilities.correctString(goalsText));
                                    }
                                    catch (Exception ex){
                                        if (goalsText.isEmpty()) {
                                            importUtilities.addError(rowNum,
                                                columnNum,
                                                "goalsText",
                                                3,
                                                "",
                                                sb);
                                        }
                                    }
                                    break;
                                case 9: //headlines
                                    String headlines = "";
                                    try {
                                        if (currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                            headlines = String.valueOf(currentCell.getNumericCellValue());
                                        else
                                            headlines = currentCell.getStringCellValue();
                                        if (headlines.isEmpty()) {
                                            importUtilities.addError(rowNum,
                                                columnNum,
                                                "headlines",
                                                1,
                                                "",
                                                sb);
                                        }
                                        educationalModuleDTO.setEducationalModuleHeadlines(importUtilities.correctString(headlines));
                                    }
                                    catch (Exception ex){
                                        if (headlines.isEmpty()) {
                                            importUtilities.addError(rowNum,
                                                columnNum,
                                                "headlines",
                                                3,
                                                "",
                                                sb);
                                        }
                                    }
                                    break;
                            }
                        }
                        catch (Exception ex){
                            hasError = true;
                            importUtilities.addError(rowNum,
                                columnNum,
                                "code",
                                3,
                                ex.getMessage(),
                                sb);
                        }
                    }
                    if(hasError)
                        continue;

                    if(isInsertRow) {
                        educationalModuleDTO.setCreateDate(ZonedDateTime.now());
                        educationalModuleDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                        educationalModuleDTO.setArchived(false);
                        educationalModuleDTO.setStatus(0);
                    }
                    else{
                        educationalModuleDTO.setModifyDate(ZonedDateTime.now());
                        educationalModuleDTO.setModifyUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    }
                    educationalModuleService.save(educationalModuleDTO);
                }
                catch (Exception ex){
                    importUtilities.addError(rowNum, 0, "", 3, ex.getMessage(),sb);
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

