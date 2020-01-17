import { Component, OnDestroy, OnInit } from '@angular/core';
import { DisplayGrid, GridsterConfig, GridsterItem, GridType } from 'angular-gridster2';
import { RxStompService } from '@stomp/ng2-stompjs';
import { Router } from '@angular/router';

export interface Widget extends GridsterItem {
    component: string
}

@Component({
    selector: 'jhi-home',
    templateUrl: './home.component.html',
    styleUrls: ['home.scss']
})
export class HomeComponent implements OnInit, OnDestroy {
    options: GridsterConfig;
    dashboard: Array<Widget>;

    constructor(private rxStompService: RxStompService, private router: Router) {
    }

    ngOnInit() {
        this.options = {
            gridType: GridType.Fit,
            displayGrid: DisplayGrid.OnDragAndResize,
            pushItems: true,
            draggable: {
                enabled: true
            },
            resizable: {
                enabled: true
            },
            api: {},
            itemResizeCallback: (item, itemComponent) => {
                console.log('Item resize', itemComponent.gridster)
            },
            itemRemovedCallback: (item, itemComponent) => console.log('Gridster', itemComponent.gridster)
        };

        this.dashboard = [
            {cols: 2, rows: 1, y: 0, x: 0, component: 'x'},
            {cols: 2, rows: 2, y: 0, x: 2, component: 'x'},
            {cols: 1, rows: 1, y: 0, x: 4, component: 'x'},
            {cols: 3, rows: 2, y: 1, x: 4, component: 'y'},
            {cols: 1, rows: 1, y: 4, x: 5, component: 'x'},
            {cols: 1, rows: 1, y: 2, x: 1, component: 'x'},
            {cols: 2, rows: 2, y: 5, x: 5, component: 'y'},
            {cols: 2, rows: 2, y: 3, x: 2, component: 'x'},
            {cols: 2, rows: 1, y: 2, x: 2, component: 'y'},
            {cols: 1, rows: 1, y: 3, x: 4, component: 'y'},
            {cols: 1, rows: 1, y: 0, x: 6, component: 'x'}
        ];
    }

    changedOptions() {
        if (this.options.api && this.options.api.optionsChanged) {
            this.options.api.optionsChanged();
        }
    }

    removeItem($event, item) {
        $event.preventDefault();
        $event.stopPropagation();
        this.dashboard.splice(this.dashboard.indexOf(item), 1);
    }

    addItem() {
        this.dashboard.push({x: 0, y: 0, cols: 1, rows: 1, component: 'x'});
    }

    ngOnDestroy() {
    }

    form = {
        components: [
            {
                label: 'Children',
                key: 'children',
                type: 'editgrid',
                input: true,
                templates: {
                    header: '' +
                        '<div class="row">' +
                        '  {% util.eachComponent(components, function(component) { %} ' +
                        '    <div class="col-sm-2">' +
                        '      <strong>{{ component.label }}</strong>' +
                        '    </div>' +
                        '  {% }) %}' +
                        '</div>',
                    row: '' +
                        '<div class="row">' +
                        '  {%util.eachComponent(components, function(component) { %}' +
                        '    <div class="col-sm-2">' +
                        '      {{ row[component.key] }}' +
                        '    </div>' +
                        '  {% }) %}' +
                        '  <div class="col-sm-2">' +
                        '    <div class="btn-group pull-right">' +
                        '      <div class="btn btn-default btn-sm editRow"><i class="fa fa-edit"></i></div>' +
                        '      <div class="btn btn-danger btn-sm removeRow"><i class="fa fa-trash"></i></div>' +
                        '    </div>' +
                        '  </div>' +
                        '</div>',
                    footer: ''
                },
                components: [
                    {
                        label: 'First Name',
                        key: 'firstName',
                        type: 'textfield',
                        input: true
                    },
                    {
                        label: 'Last Name',
                        key: 'lastName',
                        type: 'textfield',
                        input: true
                    },
                    {
                        label: 'Gender',
                        key: 'gender',
                        type: 'select',
                        input: true,
                        data: {
                            values: [
                                {
                                    value: 'male',
                                    label: 'Male'
                                },
                                {
                                    value: 'female',
                                    label: 'Female'
                                },
                                {
                                    value: 'other',
                                    label: 'Other'
                                }
                            ]
                        },
                        dataSrc: 'values',
                        template: '<span>{{ item.label }}</span>'
                    },
                    {
                        type: 'checkbox',
                        label: 'Dependant',
                        key: 'dependant',
                        inputType: 'checkbox',
                        input: true
                    },
                    {
                        label: 'Birthdate',
                        key: 'birthdate',
                        type: 'datetime',
                        input: true,
                        format: 'yyyy-MM-dd hh:mm a',
                        enableDate: true,
                        enableTime: true,
                        defaultDate: '',
                        datepickerMode: 'day',
                        datePicker: {
                            showWeeks: true,
                            startingDay: 0,
                            initDate: '',
                            minMode: 'day',
                            maxMode: 'year',
                            yearRows: 4,
                            yearColumns: 5,
                            datepickerMode: 'day'
                        },
                        timePicker: {
                            hourStep: 1,
                            minuteStep: 1,
                            showMeridian: true,
                            readonlyInput: false,
                            mousewheel: true,
                            arrowkeys: true
                        },
                        "conditional": {
                            "eq": "true",
                            "when": "dependant",
                            "show": "true"
                        }
                    }
                ]
            }
        ]
    };
}
