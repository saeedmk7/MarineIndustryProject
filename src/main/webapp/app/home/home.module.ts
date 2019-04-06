import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { MarineindustryprojSharedModule } from 'app/shared';
import { HOME_ROUTE, HomeComponent } from './';
import {ChartModule} from "angular-highcharts";
import {BrowserAnimationsModule} from "@angular/platform-browser/animations";


@NgModule({
    imports: [MarineindustryprojSharedModule,ChartModule,BrowserAnimationsModule, RouterModule.forChild([HOME_ROUTE])],
    declarations: [HomeComponent],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojHomeModule {}
