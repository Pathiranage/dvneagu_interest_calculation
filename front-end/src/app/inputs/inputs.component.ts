import {Component, OnInit, ViewChild} from '@angular/core';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';
import {AppConfigService} from '../services/app-config.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {EsResponse} from '../util/es-response';

@Component({
    selector: 'app-inputs',
    templateUrl: './inputs.component.html',
    styleUrls: ['./inputs.component.css']
})
export class InputsComponent implements OnInit {
    @ViewChild(FormGroupDirective, {static: true}) formGroupDirective: FormGroupDirective;
    calculatorForm: FormGroup;
    rateForm: FormGroup;
    interest = '0.0';
    penalties = '0.0';

    interestCurrency = '0.0';
    penaltiesCurrency = '0.0';

    constructor(
        private appConfig: AppConfigService,
        private http: HttpClient
    ) {
    }

    ngOnInit() {
        this.calculatorForm = new FormGroup({
            actualDate: new FormControl('', [Validators.required]),
            receivedDate: new FormControl('', [Validators.required]),
            amount: new FormControl('', [Validators.required]),
        });
        this.rateForm = new FormGroup({
            rate: new FormControl('', [Validators.required]),
        });
    }

    calculateInterest() {
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + 'rate/calculate';
        const httpOptions = {
            headers: new HttpHeaders(),
        };
        const actualDate: Date = this.calculatorForm.value.actualDate;
        const receivedDate: Date = this.calculatorForm.value.receivedDate;
        const data = {
            actualDate: (actualDate.getDate() < 10 ? '0' : '') + actualDate.getDate() + '/' + ((actualDate.getMonth() + 1) < 10 ? '0' : '') + (actualDate.getMonth() + 1) + '/' + actualDate.getFullYear(),
            receivedDate: (receivedDate.getDate() < 10 ? '0' : '') + receivedDate.getDate() + '/' + ((receivedDate.getMonth() + 1) < 10 ? '0' : '') + (receivedDate.getMonth() + 1) + '/' + receivedDate.getFullYear(),
            amount: this.calculatorForm.value.amount
        };
        console.log(this.calculatorForm.value);
        this.http.post(url, data, httpOptions)
            .subscribe((res: EsResponse) => {
                console.log(res);
                if (res.status === 1) {
                    this.interest = res.data.interest;
                    this.penalties = res.data.penalties;
                } else {
                    alert(res.message);
                }
            });
    }

    calculateCurrencyRate() {
        if (+this.interest <= 0) {
            alert('calculate the interest first');
            return;
        }
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + 'rate/change-currency';
        const httpOptions = {
            headers: new HttpHeaders({}),
            params: new HttpParams({
                fromObject: {
                    interest: this.interest,
                    penalties: this.penalties,
                    rate: this.rateForm.value.rate,
                }
            })
        };
        this.http.get(url, httpOptions)
            .subscribe((res: EsResponse) => {
                console.log(res);
                if (res.status === 1) {
                    this.interestCurrency = res.data.interest;
                    this.penaltiesCurrency = res.data.penalties;
                } else {
                    alert(res.message);
                }
            });
    }

    clearForm() {
        this.formGroupDirective.resetForm();
        this.rateForm.reset();
        this.rateForm.updateValueAndValidity();
        this.interest = '0.0';
        this.penalties = '0.0';
        this.interestCurrency = '0.0';
        this.penaltiesCurrency = '0.0';
    }
}
