import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { XComponent } from './x.component';
import { YComponent } from './y.component';
import { LamisSharedModule } from '@lamis/web-core';
import { HOME_ROUTE } from './home.route';
import { HomeComponent } from './home.component';
import { MaterialModule } from 'app/material.module';
import { GridsterModule } from 'angular-gridster2';
import { CoreModule } from '@alfresco/adf-core';
import { CommonModule } from '@angular/common';
import { FlexLayoutModule } from '@angular/flex-layout';
import { CellComponent } from './cell.component';
import { MatFormioModule } from 'angular-material-formio';

@NgModule({
    imports: [
        CommonModule,
        LamisSharedModule,
        RouterModule.forChild([HOME_ROUTE]),
        GridsterModule,
        MaterialModule,
        CoreModule,
        FlexLayoutModule,
        MatFormioModule
    ],
    declarations: [HomeComponent, XComponent, YComponent, CellComponent],
    entryComponents: [XComponent, YComponent],
    providers: []
})
export class LamisHomeModule {
}
