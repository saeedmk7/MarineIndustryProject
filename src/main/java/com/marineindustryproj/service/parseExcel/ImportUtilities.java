package com.marineindustryproj.service.parseExcel;

import java.text.MessageFormat;

import org.springframework.stereotype.Service;

@Service
public class ImportUtilities {
    public void addError(int rowNum,
                         int columnNum,
                         String fieldName,
                         int errorType,
                         StringBuilder sb){
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
                sb.append(MessageFormat.format("اطلاعات ردیف {0} مشکل دارد. لطفا آن را دقیقا بررسی نمائید.", rowNum, columnNum, fieldName));
                break;
            case 4: //entity is not present
                sb.append(MessageFormat.format("در ردیف {0} مقدار ستون {2} در جدول متناظرش وجود ندارد.",rowNum, fieldName));
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
    public String correctString(String str){
        return str.trim().replace('ي', 'ی').replace('ك','ک').replace('ة','ه');
    }
}
