import {NgModule} from '@angular/core';

import {
    MatButtonModule,
    MatCardModule,
    MatCheckboxModule,
    MatFormFieldModule,
    MatGridListModule,
    MatIconModule,
    MatInputModule,
    MatListModule,
    MatNativeDateModule,
    MatRadioModule,
    MatToolbarModule,
    MatTooltipModule
} from '@angular/material';
import {MatDatepickerModule} from '@angular/material/datepicker';
import {FormsModule} from '@angular/forms';


const materials = [
    MatNativeDateModule,
    MatDatepickerModule,
    MatIconModule,
    MatButtonModule,
    MatCheckboxModule,
    MatToolbarModule,
    FormsModule,
    MatCardModule,
    MatFormFieldModule,
    MatInputModule,
    MatListModule,
    MatRadioModule,
    MatTooltipModule,
    MatGridListModule
];


@NgModule({
    declarations: [],
    imports: [
        materials
    ],
    exports: [
        materials
    ]
})
export class MaterialModule {
}
