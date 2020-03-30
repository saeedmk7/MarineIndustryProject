export interface IKeyValue {
    key?: string;
    value?: any
}

export class KeyValue implements IKeyValue{
    public key: string;
    public value: number
}
