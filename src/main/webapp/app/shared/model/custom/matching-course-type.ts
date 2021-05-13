export interface IMatchingCourseType {
    title?: string;
    sum?: number;
    acceptedSum?: number;
}

export class MatchingCourseType implements IMatchingCourseType {
    public title?: string;
    public sum?: number;
    public acceptedSum?: number;
}
