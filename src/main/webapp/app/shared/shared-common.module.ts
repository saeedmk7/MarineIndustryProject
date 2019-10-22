import { NgModule } from '@angular/core';

import { MarineindustryprojSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent } from './';
import {SafePipe} from "app/shared/SafePipe/SafePipe";
import {SumPipe} from "app/shared/loopPipes/SumPipe";
import {GroupByPipe} from "app/shared/loopPipes/GroupByPipe";

@NgModule({
    imports: [MarineindustryprojSharedLibsModule],
    declarations: [FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, SafePipe, SumPipe, GroupByPipe],
    exports: [MarineindustryprojSharedLibsModule, FindLanguageFromKeyPipe, JhiAlertComponent, JhiAlertErrorComponent, SafePipe, SumPipe, GroupByPipe]
})
export class MarineindustryprojSharedCommonModule {}
