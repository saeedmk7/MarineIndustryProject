import {Component, Input, OnInit} from '@angular/core';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Component({
    selector: 'mi-run-phase-marine-suffix-detail-people',
    templateUrl: './run-phase-marine-suffix-detail-people.component.html'
})
export class RunPhaseMarineSuffixDetailPeopleComponent implements OnInit {
    @Input() public people: IPersonMarineSuffix[];
    public view: any[];

    ngOnInit(): void {
        let count = 0;
        this.people.forEach(a => {
            count++;
            a.id = count;
        });
        this.view = this.people;
    }

}
