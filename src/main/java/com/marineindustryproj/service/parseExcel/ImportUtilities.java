package com.marineindustryproj.service.parseExcel;

import java.text.MessageFormat;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.List;

import com.ghasemkiani.util.icu.PersianCalendar;
import com.ibm.icu.util.Calendar;
import org.springframework.stereotype.Service;

@Service
public class ImportUtilities {
    public void addError(int rowNum,
                         int columnNum,
                         String fieldName,
                         int errorType,
                         String errorMessage,
                         StringBuilder sb
                         ){
        switch (errorType)
        {
            case 1: //isEmpty
                //sb.append("در ردیف " + rowNum + " در ستون " + columnNum + " مقدار jobKey خالی است.");
                sb.append(MessageFormat.format("در ردیف {0} در ستون {1} مقدار {2} خالی است.", rowNum, columnNum, fieldName));
                break;
            case 2: //must be number
                sb.append(MessageFormat.format("در ردیف {0} در ستون {1} مقدار {2} باید عدد باشد.", rowNum, columnNum, fieldName));
                break;
            case 3: //row has error
                sb.append(MessageFormat.format("اطلاعات ردیف {0} مشکل دارد. لطفا آن را دقیقا بررسی نمائید. ارور: {1}", rowNum, errorMessage));
                break;
            case 4: //entity is not present
                sb.append(MessageFormat.format("در ردیف {0} مقدار ستون {1} در جدول متناظرش وجود ندارد.", rowNum, fieldName));
                break;
            case 5:
                sb.append(MessageFormat.format("در ردیف {0} مقدار ستون {1} با ارور {2} مواجه شد.", rowNum, fieldName, errorMessage));
                break;
        }
    }
    public boolean isNumeric(String str) {
        try {
            Long.parseLong(str);
            return true;
        } catch(NumberFormatException e){
            return false;
        }
    }
    public static String[] SpecialChars = {"&nbsp","(NULL)",".","'","/","//",".","|","\\","`","~","!","@","#","$","%","^","&","*","(",")","-","_","+","=",",","<",">","?","؟"};

    public String correctString(String str){
        if (str.isEmpty())
            return "";

        str = str.trim().replace("ﮎ", "ک").replace("ﮏ", "ک").replace("ﮐ", "ک").replace("ﮑ", "ک").replace("ك", "ک").replace("ي", "ی");
        return str;
    }
    public String correctAndRemoveExtraCharsString(String str){
        if (str.isEmpty())
            return "";

        str = str.trim().replace("ﮎ", "ک").replace("ﮏ", "ک").replace("ﮐ", "ک").replace("ﮑ", "ک").replace("ك", "ک").replace("ي", "ی");
        for (String specialChar : SpecialChars) {
            str = str.replace(specialChar, "");
        }
        return str;
    }
    public ZonedDateTime getZonedDateTime(int year,
                                           int month,
                                           int day) {
        ZonedDateTime b;
        try {
            PersianCalendar persianCalendar = new PersianCalendar(year,
                                                                  month,
                                                                  day);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(persianCalendar.getTime());

            b = ZonedDateTime.of(gregorianCalendar.get(Calendar.YEAR),
                                 gregorianCalendar.get(Calendar.MONTH) == 0 ? 1 : gregorianCalendar.get(Calendar.MONTH),
                                 gregorianCalendar.get(Calendar.DAY_OF_MONTH),
                                 0,
                                 0,
                                 0,
                                 0,
                                 ZoneId.systemDefault());
        }
        catch (Exception ex){
            PersianCalendar persianCalendar = new PersianCalendar(year,
                                                                  1,
                                                                  1);
            GregorianCalendar gregorianCalendar = new GregorianCalendar();
            gregorianCalendar.setTime(persianCalendar.getTime());

            b = ZonedDateTime.of(gregorianCalendar.get(Calendar.YEAR),
                                 gregorianCalendar.get(Calendar.MONTH) == 0 ? 1 : gregorianCalendar.get(Calendar.MONTH),
                                 gregorianCalendar.get(Calendar.DAY_OF_MONTH),
                                 0,
                                 0,
                                 0,
                                 0,
                                 ZoneId.systemDefault());
        }
        return b;
    }
}
