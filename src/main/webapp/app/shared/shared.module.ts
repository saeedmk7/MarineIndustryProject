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

@NgModule({
    imports: [ MarineindustryprojSharedCommonModule, NgSelectModule, FormsModule],
    declarations: [JhiLoginModalComponent, SearchPanelComponent, HasAnyAuthorityDirective],
    providers: [{ provide: NgbDateAdapter, useClass: NgbDateMomentAdapter }],
    entryComponents: [JhiLoginModalComponent, SearchPanelComponent],
    exports: [MarineindustryprojSharedCommonModule, JhiLoginModalComponent, SearchPanelComponent, HasAnyAuthorityDirective],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class MarineindustryprojSharedModule {}
