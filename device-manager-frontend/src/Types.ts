export interface Status {
    id: string;
    status: string;
    preFilter: number;
    postFilter: number;
}

export interface Device {
    id: string;
    status: Status | undefined;
}