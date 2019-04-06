import { Route } from '@angular/router';
import {ImportDataComponent} from "app/admin/import-data/import-data.component";


export const importDataRoute: Route = {
    path: 'import-data',
    component: ImportDataComponent,
    data: {
        pageTitle: 'import-data.title'
    }
};
