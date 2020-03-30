import {RequestStatus} from "app/shared/model/enums/RequestStatus";

export const REQUEST_STATUS_FILTERS_FOR_INTEGRATION =[{
    id: undefined,
    title: 'همه'
},{
    id: RequestStatus.NEW,
    title: 'منتظر تایید'
},{
    id: RequestStatus.READ,
    title: 'منتظر تصویب شورا'
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
