import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { NgbDateAdapter } from '@ng-bootstrap/ng-bootstrap';

import { NgbDateMomentAdapter } from './util/datepicker-adapter';
import {
    MarineindustryprojSharedCommonModule,
    JhiLoginModalComponent,
    HasAnyAuthorityDirective
} from './';
import {SearchPanelComponent} from "app/shared/search-panel/search-panel.component";
import {NgSelectModule} from "@ng-select/ng-select";
import {FormsModule} from "@angular/forms";
import {SteppersPanelComponent} from "app/shared/steppers-panel/steppers-panel.component";
import {CheckboxComponent} from "app/shared/checkbox-group/checkbox.component";
import {CheckboxGroupComponent} from "app/shared/checkbox-group/checkbox-group.component";
import {EducationHistoryPanelComponent} from "app/shared/education-history-panel/education-history-panel.component";

@NgModule({
    imports: [ MarineindustryprojSharedCommonModule, NgSelectModule, FormsModule],
    declarations: [JhiLoginModalComponent, SearchPanelComponent, CheckboxComponent, CheckboxGroupComponent, SteppersPanelComponent, EducationHistoryPanelComponent, HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent, SearchPanelComponent, SteppersPanelComponent, EducationHistoryPanelComponent, CheckboxComponent, CheckboxGroupComponent],
    exports: [MarineindustryprojSharedCommonModule, JhiLoginModalComponent, SearchPanelComponent, SteppersPanelComponent, EducationHistoryPanelComponent, CheckboxComponent, CheckboxGroupComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSharedModule {}
