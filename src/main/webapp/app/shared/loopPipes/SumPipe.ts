import { Pipe, PipeTransform } from '@angular/core';

@Pipe({
    name: 'sum'
})
export class SumPipe implements PipeTransform {
    transform(items: any[], attr: string): any {
        return items.reduce(function(a, b) {

            if(b[attr] != "NaN")
                return a + b[attr];
            return a;
        }, 0);
    }
}
