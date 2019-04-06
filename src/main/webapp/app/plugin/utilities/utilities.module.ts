import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import {JhiLanguageService} from "ng-jhipster";


@NgModule({
    providers: [
        JhiLanguageService
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class UtilitiesModule {}
