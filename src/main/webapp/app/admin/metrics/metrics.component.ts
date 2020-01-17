import { Component, OnInit } from '@angular/core';
import { MatDialog } from '@angular/material';

import { MetricsMonitoringModalComponent } from './metrics-modal.component';
import { JhiMetricsService } from './metrics.service';

@Component({
    selector: 'lamis-metrics',
    templateUrl: './metrics.component.html'
})
export class MetricsMonitoringComponent implements OnInit {
    metrics: any = {};
    cachesStats: any = {};
    servicesStats: any = {};
    updatingMetrics = true;
    JCACHE_KEY: string;

    constructor(private dialogRef: MatDialog, private metricsService: JhiMetricsService) {
        this.JCACHE_KEY = 'jcache.statistics';
    }

    ngOnInit() {
        this.refresh();
    }

    refresh() {
        this.updatingMetrics = true;
        this.metricsService.getMetrics().subscribe(metrics => {
            this.metrics = metrics;
            this.updatingMetrics = false;
            this.servicesStats = {};
            this.cachesStats = {};
            Object.keys(metrics.timers).forEach(key => {
                const value = metrics.timers[key];
                if (key.includes('web.rest') || key.includes('service')) {
                    this.servicesStats[key] = value;
                }
            });
            Object.keys(metrics.gauges).forEach(key => {
                if (key.includes('jcache.statistics')) {
                    const value = metrics.gauges[key].value;
                    // remove gets or puts
                    const index = key.lastIndexOf('.');
                    const newKey = key.substr(0, index);

                    // Keep the name of the domain
                    this.cachesStats[newKey] = {
                        name: this.JCACHE_KEY.length,
                        value
                    };
                }
            });
        });
    }

    refreshThreadDumpData() {
        this.metricsService.threadDump().subscribe((data) => {
            this.dialogRef.open(MetricsMonitoringModalComponent, {
                data: {threadDump: data}
            });
        });
    }

    filterNaN(input) {
        if (isNaN(input)) {
            return 0;
        }
        return input;
    }
}
