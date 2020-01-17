import { ActivatedRouteSnapshot, Resolve, RouterStateSnapshot, Routes } from '@angular/router';
import { ModuleListComponent } from './components/module.list.component';
import { UserRouteAccessService } from '@lamis/web-core';
import { ModuleDetailsComponent } from './components/module.details.component';
import { Observable } from 'rxjs';
import { Module } from '../../module'
import { ModuleService } from '../../module/module.service';
import { ModuleInstallComponent } from './components/module.install.component';
import { Injectable } from '@angular/core';

@Injectable()
export class ModuleResolve implements Resolve<Module> {
    constructor(private service: ModuleService) {
    }

    resolve(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<Module> {
        const id = route.params['id'] ? route.params['id'] : null;
        return this.service.getModule(id);
    }
}

export const ROUTES: Routes = [
    {
        path: '',
        data: {
            title: 'Modules',
            breadcrumb: 'MODULES',
            authorities: ['ROLE_ADMIN'],
        },
        canActivate: [UserRouteAccessService],
        children: [
            {
                path: '',
                component: ModuleListComponent,
                data: {
                    breadcrumb: 'MODULES',
                    title: 'Modules',
                }
            },
            {
                path: ':id/view',
                component: ModuleDetailsComponent,
                resolve: {
                    entity: ModuleResolve
                },
                data: {
                    authorities: ['ROLE_ADMIN'],
                    title: 'Module Details',
                    breadcrumb: 'MODULE DETAILS'
                },
                canActivate: [UserRouteAccessService]
            },
            {
                path: 'install',
                component: ModuleInstallComponent,
                data: {
                    authorities: ['ROLE_ADMIN'],
                    title: 'Install Module',
                    breadcrumb: 'INSTALL MODULE'
                },
                canActivate: [UserRouteAccessService]
            }
        ]
    }
];
