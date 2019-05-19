import {Component, Input, OnInit} from '@angular/core';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Component({
    selector: 'run-phase-marine-suffix-detail-people',
    templateUrl: './run-phase-marine-suffix-detail-people.component.html'
})
export class RunPhaseMarineSuffixDetailPeopleComponent implements OnInit {
    @Input() public people: IPersonMarineSuffix[];
    public view: any[];

    ngOnInit(): void {
        this.view = this.people;
    }

}
