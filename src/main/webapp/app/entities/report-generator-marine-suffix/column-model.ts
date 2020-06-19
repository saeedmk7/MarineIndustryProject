export interface IColumnModel {
    columnName?: string;
    displayName?: string;
    displayOrder?: number;
}

export class ColumnModel implements IColumnModel {
    public columnName?: string;
    public displayName?: string;
    public displayOrder?: number;
}
