import {Component, Input, OnInit} from '@angular/core';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Component({
    selector: 'mi-planning-marine-suffix-detail-people',
    templateUrl: './planning-marine-suffix-detail-people.component.html'
})
export class PlanningMarineSuffixDetailPeopleComponent implements OnInit {
    @Input() public people: IPersonMarineSuffix[];
    public view: any[];

    ngOnInit(): void {

        this.view = this.people;
    }

}
