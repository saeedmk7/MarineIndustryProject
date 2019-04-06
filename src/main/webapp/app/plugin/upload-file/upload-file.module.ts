import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { MarineindustryprojSharedModule } from '../../shared';
import {DpDatePickerModule} from "ng2-jalali-date-picker";
import {FormUploadComponent} from "./upload-file.component";
import {UploadFileService} from "./upload-file.service";
import { HttpClientModule} from "@angular/common/http";
import {BrowserModule} from "@angular/platform-browser";


@NgModule({
    imports: [
        MarineindustryprojSharedModule,
        DpDatePickerModule,
        BrowserModule,
        HttpClientModule
    ],
    declarations: [
        FormUploadComponent,

    ],
    providers: [
        UploadFileService
    ],
    exports:[
        FormUploadComponent
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UploadFileModule {}
