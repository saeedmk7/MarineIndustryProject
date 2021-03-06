import {Component, Input, OnInit} from '@angular/core';
import {IPersonMarineSuffix} from "app/shared/model/person-marine-suffix.model";

@Component({
    selector: 'mi-final-niazsanji-report-marine-suffix-detail-people',
    templateUrl: './final-niazsanji-report-marine-suffix-detail-people.component.html'
})
export class FinalNiazsanjiReportMarineSuffixDetailPeopleComponent implements OnInit {
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
