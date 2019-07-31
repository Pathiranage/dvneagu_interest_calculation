import {Injectable} from '@angular/core';

@Injectable({
    providedIn: 'root'
})
export class AppConfigService {

    private readonly _BASE_URL: string;

    constructor() {
        this._BASE_URL = 'http://localhost:8180/interest-service/ap1/v1';
        this._URL_SEPARATOR = '/';
    }

    private _URL_SEPARATOR: string;

    get URL_SEPARATOR(): string {
        return this._URL_SEPARATOR;
    }

    get BASE_URL(): string {
        return this._BASE_URL;
    }
}
