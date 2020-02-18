import {RequestStatus} from "app/shared/model/enums/RequestStatus";

export const REQUEST_STATUS_FILTERS =[{
    id: undefined,
    title: 'همه'
},{
    id: RequestStatus.NEW,
    title: 'جاری'
},{
    id: RequestStatus.ACCEPT,
    title: 'تصویب شده شوراء'
},{
    id: RequestStatus.IGNORE,
    title: 'رد شده'
},{
    id: RequestStatus.RETURNED,
    title: 'پیام های مهم دار'
}];
