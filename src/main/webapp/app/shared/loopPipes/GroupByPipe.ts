import { Pipe, PipeTransform } from '@angular/core';
import { DomSanitizer} from '@angular/platform-browser';

@Pipe({ name: 'GroupBy' })
export class GroupByPipe implements PipeTransform {
    constructor() {}
    transform(array: Array<any>, field) {

        if (array) {
            const groupedObj = array.reduce((prev, cur) => {

                if (!prev[cur[field]]) {
                    prev[cur[field]] = [cur];
                } else {
                    prev[cur[field]].push(cur);
                }
                return prev;
            }, {});

            var result = Object.keys(groupedObj).map(key => ({ key, value: groupedObj[key] }));
            return result;
        }
        return [];
    }
}
