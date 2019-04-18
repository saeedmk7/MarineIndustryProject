package com.marineindustryproj.service.parseExcel;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DecimalFormat;
import java.text.MessageFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.marineindustryproj.security.SecurityUtils;
import com.marineindustryproj.service.EmploymentTypeService;
import com.marineindustryproj.service.FieldOfStudyService;
import com.marineindustryproj.service.JobService;
import com.marineindustryproj.service.OrganizationChartService;
import com.marineindustryproj.service.PersonService;
import com.marineindustryproj.service.QualificationService;
import com.marineindustryproj.service.ScientificWorkGroupService;
import com.marineindustryproj.service.WorkGroupService;
import com.marineindustryproj.service.WorkIndustryService;
import com.marineindustryproj.service.dto.EmploymentTypeDTO;
import com.marineindustryproj.service.dto.FieldOfStudyDTO;
import com.marineindustryproj.service.dto.JobDTO;
import com.marineindustryproj.service.dto.OrganizationChartDTO;
import com.marineindustryproj.service.dto.PersonDTO;
import com.marineindustryproj.service.dto.QualificationDTO;
import com.marineindustryproj.service.dto.ScientificWorkGroupDTO;
import com.marineindustryproj.service.dto.WorkGroupDTO;
import com.marineindustryproj.service.dto.WorkIndustryDTO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.NumberUtils;
import org.springframework.util.StringUtils;

@Service
@Transactional
public class JobExcel {

    private static JobService jobService = null;
    private static ScientificWorkGroupService scientificWorkGroupService = null;
    private static ImportUtilities importUtilities = null;
    private final Path rootLocation = Paths.get("upload-dir");

    @Autowired
    public JobExcel(JobService jobService, ImportUtilities importUtilities, ScientificWorkGroupService scientificWorkGroupService) {

        this.jobService = jobService;
        this.importUtilities = importUtilities;
        this.scientificWorkGroupService = scientificWorkGroupService;
    }
    public JobExcel(){}


    public StringBuilder parseJob(String fileName) throws Exception {
        StringBuilder sb = new StringBuilder();
        try {

            FileInputStream excelFile = new FileInputStream(new File(this.rootLocation.resolve(fileName).toAbsolutePath().toString()));
            Workbook workbook = new XSSFWorkbook(excelFile);
            Sheet datatypeSheet = workbook.getSheet("Sheet1");
            List<Long> ids = jobService.findAllFromCache().stream().map(a -> a.getId()).collect(Collectors.toList());
            Iterator<Row> iterator = datatypeSheet.iterator();
            while (iterator.hasNext()) {
                Row currentRow = iterator.next();
                int rowNum = currentRow.getRowNum();
                if (rowNum == 0)
                    continue;
                try {
                    Iterator<Cell> cellIterator = currentRow.iterator();
                    JobDTO jobDTO = new JobDTO();
                    boolean hasError = false;
                    while (cellIterator.hasNext()) {
                        if (hasError)
                            break;
                        Cell currentCell = cellIterator.next();
                        int columnNum = currentCell.getColumnIndex();

                        switch (columnNum) {
                            case 0: //JobKey
                                Double jobKey = Double.valueOf(0);
                                //noinspection deprecation
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    jobKey = currentCell.getNumericCellValue();
                                else
                                    jobKey = Double.valueOf(currentCell.getStringCellValue());
                                if (jobKey == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "JobKey",
                                             1,
                                             "",
                                             sb);
                                    hasError = true;
                                    continue;
                                }
                                if(ids.contains(jobKey.longValue())){
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
                                jobDTO.setJobKey(String.valueOf(jobKey.longValue()));
                                jobDTO.setId(jobKey.longValue());
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
                                             "",
                                             sb);
                                    hasError = true;
                                    continue;
                                }
                                jobDTO.setTitle(importUtilities.correctString(title));
                                break;
                            case 2: //JobCode
                                Double jobCode = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    jobCode = currentCell.getNumericCellValue();
                                else
                                    jobCode = Double.valueOf(currentCell.getStringCellValue());
                                if (jobCode == 0) {
                                    importUtilities.addError(rowNum,
                                             columnNum,
                                             "jobCode",
                                             1,
                                             "",
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
                                jobDTO.setJobCode(String.valueOf(jobCode.longValue()));
                                jobDTO.setFirst3JobCode(jobDTO.getJobCode().substring(0,
                                                                                      3));
                                break;
                            case 3: //ScientificWorkGroupId
                                Double scientificWorkGroupId = Double.valueOf(0);
                                if(currentCell.getCellTypeEnum() == CellType.NUMERIC)
                                    scientificWorkGroupId = currentCell.getNumericCellValue();
                                else
                                    scientificWorkGroupId = Double.valueOf(currentCell.getStringCellValue());
                                if (scientificWorkGroupId != 0) {
                                    Optional<ScientificWorkGroupDTO> scientificWorkGroupDTO =
                                        scientificWorkGroupService.findOne(scientificWorkGroupId.longValue());
                                    if(scientificWorkGroupDTO.isPresent())
                                        jobDTO.setScientificWorkGroupId(scientificWorkGroupId.longValue());
                                    else
                                        importUtilities.addError(rowNum,columnNum,"scientificWorkGroupId",4, "",sb);
                                }
                        }
                    }
                    if(hasError)
                        continue;
                    jobDTO.setCreateDate(ZonedDateTime.now());
                    jobDTO.setCreateUserLogin(SecurityUtils.getCurrentUserLogin().get());
                    jobDTO.setArchived(false);
                    jobDTO.setStatus(0);
                    jobService.save(jobDTO);
                }
                catch (Exception ex){
                    importUtilities.addError(rowNum, 0, "", 3, ex.getMessage(), sb);
                    break;
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

