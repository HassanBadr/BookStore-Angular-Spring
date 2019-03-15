import {NgModule} from '@angular/core';

import {
  MatButtonModule, MatDialogModule, MatInputModule, MatListModule, MatSelectModule, MatSliderModule,
  MatSlideToggleModule
} from '@angular/material';
import { MatToolbarModule } from '@angular/material/toolbar';
import { MatGridListModule } from '@angular/material';
import { MatFormFieldModule } from '@angular/material';

@NgModule({
  imports: [
    MatGridListModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatListModule,
    MatDialogModule
  ],
  exports: [
    MatGridListModule,
    MatButtonModule,
    MatToolbarModule,
    MatFormFieldModule,
    MatInputModule,
    MatSelectModule,
    MatSlideToggleModule,
    MatListModule,
    MatDialogModule
  ]
})
export class CustomCompModule { }
