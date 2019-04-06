
export interface IReportMarineSuffix {
    fileDocContentType?: string;
    fileDoc?: any;
}

export class ReportMarineSuffix implements IReportMarineSuffix {
    constructor(
        public fileDocContentType?: string,
        public fileDoc?: any
    ) {}
}
