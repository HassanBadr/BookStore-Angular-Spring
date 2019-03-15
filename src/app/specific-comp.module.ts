import {NgModule} from '@angular/core';
import {MatButtonModule, MatProgressSpinnerModule, MatSpinner, MatTabsModule} from '@angular/material';

@NgModule({
  imports: [
    MatButtonModule,
    MatTabsModule,
    MatProgressSpinnerModule
  ],
  exports: [
    MatButtonModule,
    MatTabsModule,
    MatProgressSpinnerModule
  ]
})
export class SpecificCompModule { }
