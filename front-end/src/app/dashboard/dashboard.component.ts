import {Component, OnInit, ViewChild} from '@angular/core';
import {MatPaginator} from '@angular/material/paginator';
import {MatTableDataSource} from '@angular/material/table';
import {Rate} from '../util/rate';
import {AppConfigService} from '../services/app-config.service';
import {HttpClient, HttpHeaders, HttpParams} from '@angular/common/http';
import {EsResponse} from '../util/es-response';
import {FormControl, FormGroup, FormGroupDirective, Validators} from '@angular/forms';


@Component({
    selector: 'app-dashboard',
    templateUrl: './dashboard.component.html',
    styleUrls: ['./dashboard.component.css']
})
export class DashboardComponent implements OnInit {
    displayedColumns: string[] = ['fromDate', 'interest', 'penalties', 'delete'];
    dataSource = new MatTableDataSource<Rate>();

    @ViewChild(MatPaginator, {static: true}) paginator: MatPaginator;
    @ViewChild(FormGroupDirective,{static: true}) formGroupDirective: FormGroupDirective;
    rateForm: FormGroup;

    constructor(private appConfig: AppConfigService,
                private http: HttpClient) {
    }

    ngOnInit() {
        this.dataSource.paginator = this.paginator;
        this.findAllRates();
        this.rateForm = new FormGroup({
            fromDate: new FormControl('', [Validators.required]),
            interest: new FormControl('', [Validators.required]),
            penalties: new FormControl('', [Validators.required]),
        });
    }

    private findAllRates() {
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + 'rate';
        const httpOptions = {
            headers: new HttpHeaders({}),
            params: new HttpParams({})
        };
        this.http.get(url, httpOptions)
            .subscribe((res: EsResponse) => {
                if (res.status === 1) {
                    this.dataSource.data = res.data;
                } else {
                    alert(res.message);
                }
            });
    }

    deleteRate(id: any) {
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + 'rate' + this.appConfig.URL_SEPARATOR + id;
        const httpOptions = {
            headers: new HttpHeaders({}),
            params: new HttpParams({})
        };
        this.http.delete(url, httpOptions)
            .subscribe((res: EsResponse) => {
                if (res.status === 1) {
                    alert(res.message);
                    this.findAllRates();
                } else {
                    alert(res.message);
                }
            });
    }

    createNewRate() {
        const rate = new Rate();
        rate.fromDate = this.rateForm.value.fromDate;
        rate.interestRate = this.rateForm.value.interest;
        rate.penalties = this.rateForm.value.penalties;
        const url = this.appConfig.BASE_URL + this.appConfig.URL_SEPARATOR + 'rate';
        const httpOptions = {
            headers: new HttpHeaders({}),
            params: new HttpParams({})
        };
        this.http.post(url, rate, httpOptions)
            .subscribe((res: EsResponse) => {
                if (res.status === 1) {
                    alert(res.message);
                    this.findAllRates();
                    this.formGroupDirective.resetForm();
                } else {
                    alert(res.message);
                }
            });
    }
}
