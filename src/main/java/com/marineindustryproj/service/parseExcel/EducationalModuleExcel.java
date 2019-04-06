package com.marineindustryproj.service.parseExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EducationalModuleService;
import com.marineindustryproj.service.ScientificWorkGroupService;
import com.marineindustryproj.service.SkillableLevelOfSkillService;
import com.marineindustryproj.service.dto.EducationalModuleDTO;
import com.marineindustryproj.service.dto.ScientificWorkGroupDTO;
import com.marineindustryproj.service.dto.SkillableLevelOfSkillDTO;
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
    private static ImportUtilities importUtilities = null;

    private final Path rootLocation = Paths.get("upload-dir");

    @Autowired
    public EducationalModuleExcel(EducationalModuleService educationalModuleService,
                                  SkillableLevelOfSkillService skillableLevelOfSkillService,
                                  ScientificWorkGroupService scientificWorkGroupService,
                                  ImportUtilities importUtilities) {
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
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                int rowNum = currentRow.getRowNum();
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

                        switch (columnNum) {
                            case 0: //code
                                Double code = Double.valueOf(0);
                                //noinspection deprecation
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    code = currentCell.getNumericCellValue();
                                else
                                    code = Double.valueOf(currentCell.getStringCellValue());
                                if (code == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "code",
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
                                educationalModuleDTO.setCode(String.valueOf(code.longValue()));
                                educationalModuleDTO.setId(code.longValue());
                                break;
                            case 1: //Title
                                String title = "";
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                   title = String.valueOf(currentCell.getNumericCellValue());
                                else
                                    title = currentCell.getStringCellValue();
                                if (title.isEmpty()) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "title",
                                             1,
                                             sb);
                                    hasError = true;
                                    continue;
                                }
                                educationalModuleDTO.setTitle(title);
                                break;
                            case 2: //learningTimeTheorical
                                Double learningTimeTheorical = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    learningTimeTheorical = currentCell.getNumericCellValue();
                                else
                                    learningTimeTheorical = Double.valueOf(currentCell.getStringCellValue());
                                /*if (learningTimeTheorical == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "learningTimeTheorical",
                                             1,
                                             sb);
                                    hasError = true;
                                    continue;
                                }*/
                                /*if (!isNumeric(jobCode)) {
                                    addError(rowNum,
                                             columnNum,
                                             "jobCode",
                                             2,
                                             sb);
                                    hasError = true;
                                    continue;
                                }*/
                                educationalModuleDTO.setLearningTimeTheorical(learningTimeTheorical.intValue());
                                break;
                            case 3: //learningTimePractical
                                Double learningTimePractical = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    learningTimePractical = currentCell.getNumericCellValue();
                                else
                                    learningTimePractical = Double.valueOf(currentCell.getStringCellValue());
                                /*if (learningTimePractical == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "learningTimePractical",
                                             1,
                                             sb);
                                    hasError = true;
                                    continue;
                                }*/
                                /*if (!isNumeric(jobCode)) {
                                    addError(rowNum,
                                             columnNum,
                                             "jobCode",
                                             2,
                                             sb);
                                    hasError = true;
                                    continue;
                                }*/
                                educationalModuleDTO.setLearningTimePractical(learningTimePractical.intValue());
                                break;
                            case 4: //skillableLevelOfSkillId
                                Double skillableLevelOfSkillId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    skillableLevelOfSkillId = currentCell.getNumericCellValue();
                                else
                                    skillableLevelOfSkillId = Double.valueOf(currentCell.getStringCellValue());
                                if (skillableLevelOfSkillId != 0) {
                                    Optional<SkillableLevelOfSkillDTO> skillableLevelOfSkillDTO =
                                        skillableLevelOfSkillService.findOne(skillableLevelOfSkillId.longValue());
                                    if(skillableLevelOfSkillDTO.isPresent())
                                        educationalModuleDTO.setSkillableLevelOfSkillId(skillableLevelOfSkillId.longValue());
                                    else {
                                        importUtilities.addError(rowNum,
                                                                 columnNum,
                                                                 "skillableLevelOfSkillId",
                                                                 4,
                                                                 sb);
                                        hasError = true;
                                        continue;
                                    }
                                }
                                else{
                                    importUtilities.addError(rowNum,
                                                             columnNum,
                                                             "skillableLevelOfSkillId",
                                                             1,
                                                             sb);
                                    hasError = true;
                                    continue;
                                }
                                break;
                            case 5: //scientificWorkGroupId
                                Double scientificWorkGroupId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    scientificWorkGroupId = currentCell.getNumericCellValue();
                                else
                                    scientificWorkGroupId = Double.valueOf(currentCell.getStringCellValue());
                                if (scientificWorkGroupId != 0) {
                                    Optional<ScientificWorkGroupDTO> scientificWorkGroupDTO =
                                        scientificWorkGroupService.findOne(scientificWorkGroupId.longValue());
                                    if(scientificWorkGroupDTO.isPresent()) {
                                        Set<ScientificWorkGroupDTO> scientificWorkGroupDTOList = new HashSet<>();
                                        scientificWorkGroupDTOList.add(scientificWorkGroupDTO.get());
                                        educationalModuleDTO.setScientificWorkGroups(scientificWorkGroupDTOList);
                                    }
                                    else
                                        importUtilities.addError(rowNum,columnNum,"scientificWorkGroupId",4, sb);
                                }
                                break;
                        }
                    }
                    educationalModuleDTO.setCreateDate(ZonedDateTime.now());
                    educationalModuleDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    educationalModuleDTO.setArchived(false);
                    educationalModuleDTO.setStatus(0);
                    educationalModuleService.save(educationalModuleDTO);
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

