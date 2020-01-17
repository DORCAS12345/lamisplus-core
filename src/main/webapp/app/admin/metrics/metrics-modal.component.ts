import { Component, Inject, OnInit } from '@angular/core';
import { MAT_DIALOG_DATA } from '@angular/material';

@Component({
    selector: 'lamis-metrics-modal',
    templateUrl: './metrics-modal.component.html'
})
export class MetricsMonitoringModalComponent implements OnInit {
    threadDumpFilter: any;
    threadDump: any;
    threadDumpAll = 0;
    threadDumpBlocked = 0;
    threadDumpRunnable = 0;
    threadDumpTimedWaiting = 0;
    threadDumpWaiting = 0;

    constructor(@Inject(MAT_DIALOG_DATA) public data: any) {
        this.threadDump = this.data.threadDump;
    }

    ngOnInit() {
        this.threadDump.forEach(value => {
            if (value.threadState === 'RUNNABLE') {
                this.threadDumpRunnable += 1;
            } else if (value.threadState === 'WAITING') {
                this.threadDumpWaiting += 1;
            } else if (value.threadState === 'TIMED_WAITING') {
                this.threadDumpTimedWaiting += 1;
            } else if (value.threadState === 'BLOCKED') {
                this.threadDumpBlocked += 1;
            }
        });

        this.threadDumpAll = this.threadDumpRunnable + this.threadDumpWaiting + this.threadDumpTimedWaiting + this.threadDumpBlocked;
    }

    getBadgeClass(threadState) {
        if (threadState === 'RUNNABLE') {
            return 'badge-success';
        } else if (threadState === 'WAITING') {
            return 'badge-info';
        } else if (threadState === 'TIMED_WAITING') {
            return 'badge-warning';
        } else if (threadState === 'BLOCKED') {
            return 'badge-danger';
        }
    }
}