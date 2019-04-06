import { Injectable } from '@angular/core';
import * as XLSX from 'xlsx';
import {TranslateService} from '@ngx-translate/core';
import * as FileSaver from 'file-saver';

const EXCEL_TYPE = 'application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=UTF-8';
const EXCEL_EXTENSION = '.xlsx';
import * as moment from 'jalali-moment';

@Injectable()
export class ExcelService {
    isfa:boolean;
    constructor(private translate: TranslateService) {
        this.isfa = translate.currentLang == 'fa';
    }
    public exportAsExcelFile(json: any[], excelFileName: string,jsontype:string): void {
        var finalJson = this.makeJson(json,jsontype);
        const worksheet: XLSX.WorkSheet = XLSX.utils.json_to_sheet(finalJson);
        const workbook: XLSX.WorkBook = { Sheets: { 'data': worksheet }, SheetNames: ['data'] };
        const excelBuffer: any = XLSX.write(workbook, { bookType: 'xlsx', type: 'array' });
        this.saveAsExcelFile(excelBuffer, excelFileName);
    }
    makeJson(json: any[],jsontype:string) {
        const mustChangeList: string[] = ['timepassed'];
        var newJson = JSON.parse(JSON.stringify(json));
        for (var i = 0; i < newJson.length; i++) {
            var obj = newJson[i];
            for (var key in obj) {
                if (obj.hasOwnProperty(key)) {
                    var name = (key!=='id') ? this.translate.get(jsontype + '.' + key) : this.translate.get('global.field.id');
                    var e;
                    var ww = name.subscribe((result) => e = result.toString());
                    if(!e.startsWith('translation-not-found')) {
                        var value = obj[key];
                        /*if(key.toLowerCase().includes('date'))
                        {
                            if(this.isfa)
                                if(value != null)
                                    value = moment(value).format('jYYYY/jM/jD');
                                else
                                    value = "";
                        }
                        if (mustChangeList.filter(a => a === key.toLowerCase()).length) {
                            if (value)
                                value = moment(value).format('jYYYY/jM/jD');
                        }*/
                        if(value === false)
                            value = 'خیر'
                        if(value === true)
                            value = 'بلی'
                        obj[e] = value;
                    }

                    delete obj[key];
                }
            }
        }
        return newJson;
    }
    private saveAsExcelFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], {
            type: EXCEL_TYPE
        });
        FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
    }

}
