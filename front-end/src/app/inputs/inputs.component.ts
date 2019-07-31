import {Component, OnInit} from '@angular/core';
import {FormControl, FormGroup, Validators} from '@angular/forms';
import {AppConfigService} from '../services/app-config.service';
import {HttpClient, HttpHeaders} from '@angular/common/http';

@Component({
    selector: 'app-inputs',
    templateUrl: './inputs.component.html',
    styleUrls: ['./inputs.component.css']
})
export class InputsComponent implements OnInit {
    calculatorForm: FormGroup;

    constructor(
        private appConfig: AppConfigService,
        private http: HttpClient
    ) {
    }

    ngOnInit() {
        this.calculatorForm = new FormGroup({
            actualDate: new FormControl('', [Validators.required]),
            receivedData: new FormControl('', [Validators.required]),
            amount: new FormControl('', [Validators.required]),
        });
    }

    calculateInterest() {
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + 'rate/calculate';
        const httpOptions = {
            headers: new HttpHeaders(),
        };
        console.log(this.calculatorForm.value);
        this.http.post(url, this.calculatorForm.value, httpOptions)
            .subscribe(res => {
                console.log(res);
            });
    }
}
