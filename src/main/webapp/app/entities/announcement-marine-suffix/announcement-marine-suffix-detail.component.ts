import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { JhiDataUtils } from 'ng-jhipster';

import { IAnnouncementMarineSuffix } from 'app/shared/model/announcement-marine-suffix.model';
/*import '@ckeditor/ckeditor5-build-classic/build/translations/fa';
import * as ClassicEditor from '@ckeditor/ckeditor5-build-classic';*/
import {ConvertObjectDatesService} from "app/plugin/utilities/convert-object-dates";
import * as moment from 'jalali-moment';

@Component({
    selector: 'mi-announcement-marine-suffix-detail',
    templateUrl: './announcement-marine-suffix-detail.component.html'
})
export class AnnouncementMarineSuffixDetailComponent implements OnInit {
    announcement: IAnnouncementMarineSuffix;
    /*public Editor = ClassicEditor;
    public config = {
        language: 'fa'
    };*/
    constructor(private dataUtils: JhiDataUtils, private activatedRoute: ActivatedRoute) {}

    ngOnInit() {
        this.activatedRoute.data.subscribe(({ announcement }) => {
            announcement.createDate = moment(announcement.createDate).format('jYYYY/jM/jD');
            this.announcement = announcement;
        });
    }

    byteSize(field) {
        return this.dataUtils.byteSize(field);
    }

    openFile(contentType, field) {
        return this.dataUtils.openFile(contentType, field);
    }
    previousState() {
        window.history.back();
    }
}
