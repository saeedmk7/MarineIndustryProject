import { HttpParams } from '@angular/common/http';

export const createRequestOption = (req?: any): HttpParams => {
    let options: HttpParams = new HttpParams();

    if (req) {
        Object.keys(req).forEach(key => {
            if (key !== 'sort') {
                options = options.set(key, req[key]);
            }
        });
        if (req.sort) {
            req.sort.forEach(val => {
                options = options.append('sort', val);
            });
        }

        if (req.criteria) {
            req.criteria.forEach((criterion) => {
                if(criterion.value)
                    options = options.append(criterion.key, criterion.value);
            });
        }
        /*options.set('entityName', req.entityName);
        options.set('entityId', req.entityId);*/
    }
    else {
        options = options.set('size', '2000');
        options = options.set('sort', 'id,asc');
    }
    return options;
};
