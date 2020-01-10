import {Component, EventEmitter, Input, OnDestroy, OnInit, Output} from '@angular/core';
import {SteppersPanelModel} from "app/shared/model/custom/steppers.model";
@Component({
    selector: 'steppers-panel',
    templateUrl: './steppers-panel.component.html',
    styleUrls: ['./steppers-panel.component.scss']
})
export class SteppersPanelComponent implements OnInit, OnDestroy {
    @Input('stepsList') stepsList: SteppersPanelModel[];
    @Output() activeTabChanged = new EventEmitter();

    constructor() {

    }

    returnActiveTab(activeIndex){
        this.activeTabChanged.emit(activeIndex);
    }

    ngOnInit() {

    }

    ngOnDestroy(): void {
    }
}
