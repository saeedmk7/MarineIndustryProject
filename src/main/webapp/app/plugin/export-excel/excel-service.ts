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
        debugger;
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
                    debugger;
                    let exactName;
                    if(key.includes('.')){
                        exactName = this.translate.get(key);
                    }
                    else {
                        exactName = (key!=='id') ? this.translate.get(jsontype + '.' + key) : this.translate.get('global.field.id');
                    }
                    let title = exactName.value;
                    //exactName.subscribe((result) => title = result.toString());
                    if(!title.startsWith('translation-not-found')) {
                        this.assignToObj(obj, title, key);
                    }

                    delete obj[key];
                }
            }
        }
        return newJson;
    }
    private assignToObj(obj, title, key){
        var value = obj[key];

        if(value === false)
            value = 'خیر'
        if(value === true)
            value = 'بلی'
        obj[title] = value;
    }
    private saveAsExcelFile(buffer: any, fileName: string): void {
        const data: Blob = new Blob([buffer], {
            type: EXCEL_TYPE
        });
        FileSaver.saveAs(data, fileName + '_export_' + new Date().getTime() + EXCEL_EXTENSION);
    }

}
