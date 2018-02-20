import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import {CdkTableModule} from '@angular/cdk/table';
import {MatProgressSpinnerModule,MatTableModule,MatDialogModule,MatCheckboxModule,MatGridListModule,MatChipsModule,MatSnackBarModule,MatSliderModule,MatListModule,MatTabsModule,MatSidenavModule,MatInputModule,MatMenuModule,MatSelectModule,MatIconModule,MatButtonModule,MatCardModule} from '@angular/material';
import {MatToolbarModule} from '@angular/material';

@NgModule({
  imports: [
    CommonModule,
    MatToolbarModule,
    MatInputModule,
    MatMenuModule,
    MatSidenavModule,
    MatSelectModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
    MatTabsModule,
    MatListModule,
    MatSliderModule,
    MatSnackBarModule,
    MatGridListModule,
    MatChipsModule,
    MatCheckboxModule,
    MatDialogModule,
    MatTableModule,
    CdkTableModule,
    MatProgressSpinnerModule

],
  exports:[MatToolbarModule,
    MatInputModule,
    MatMenuModule,
    MatSidenavModule,
    MatSelectModule,
    MatIconModule,
    MatButtonModule,
    MatCardModule,
  MatTabsModule, MatListModule,
  MatSliderModule,MatSnackBarModule,
    MatGridListModule,
    MatChipsModule,
    MatCheckboxModule,
    MatDialogModule,
    MatTableModule,
    CdkTableModule,
    MatProgressSpinnerModule
  ],
  declarations: []
})
export class MdwidgetmoduleModule {

}
