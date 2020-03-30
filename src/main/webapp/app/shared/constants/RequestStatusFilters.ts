import {RequestStatus} from "app/shared/model/enums/RequestStatus";

export const REQUEST_STATUS_FILTERS =[{
    id: undefined,
    title: 'همه'
},{
    id: 'NEW',
    title: 'منتظر تایید'
},{
    id: 'ACCEPT',
    title: 'تصویب شده شوراء'
},{
    id: 'IGNORE',
    title: 'رد شده'
},{
    id: 'RETURNED',
    title: 'پیام های مهم دار'
}];
