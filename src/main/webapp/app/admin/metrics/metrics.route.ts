import { Routes } from '@angular/router';

import { MetricsMonitoringComponent } from './metrics.component';

export const MetricsRoute: Routes = [
    {
        path: '',
        component: MetricsMonitoringComponent,
        data: {
            title: 'Application Metrics',
            breadcrumb: 'APPLICATION METRICS',
        }
    }
];
