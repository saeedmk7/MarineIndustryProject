export interface INavbarModel {
    title:string;
    url:string;
    faicon:string;
    isActive:boolean;
    acceptedRoles:string;
    children?:INavbarModel[]
}

export class NavbarModel implements INavbarModel {
    public acceptedRoles: string;
    public children?: INavbarModel[];
    public faicon: string;
    public isActive: boolean;
    public title: string;
    public url: string;
}
