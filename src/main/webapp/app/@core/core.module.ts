import { CommonModule } from '@angular/common';
import { ModuleWithProviders, NgModule, Optional, SkipSelf } from '@angular/core';
import { NbAuthModule, NbDummyAuthStrategy } from '@nebular/auth';
import { NbRoleProvider, NbSecurityModule } from '@nebular/security';
import { of as observableOf } from 'rxjs';

import { throwIfAlreadyLoaded } from './module-import-guard';
import { LayoutService } from './utils';

const socialLinks = [
    {
        url: 'https://github.com/akveo/nebular',
        target: '_blank',
        icon: 'github',
    },
    {
        url: 'https://www.facebook.com/akveo/',
        target: '_blank',
        icon: 'facebook',
    },
    {
        url: 'https://twitter.com/akveo_inc',
        target: '_blank',
        icon: 'twitter',
    },
];

export class NbSimpleRoleProvider extends NbRoleProvider {
    getRole() {
        // here you could provide any role based on any auth flow
        return observableOf('guest');
    }
}

export const NB_CORE_PROVIDERS = [
    ...NbAuthModule.forRoot({
        strategies: [
            NbDummyAuthStrategy.setup({
                name: 'email',
                delay: 3000,
            }),
        ],
        forms: {
            login: {
                socialLinks: socialLinks,
            },
            register: {
                socialLinks: socialLinks,
            },
        },
    }).providers,
    NbSecurityModule.forRoot({
        accessControl: {
            guest: {
                view: '*',
            },
            user: {
                parent: 'guest',
                create: '*',
                edit: '*',
                remove: '*',
            },
        },
    }).providers,
    {
        provide: NbRoleProvider, useClass: NbSimpleRoleProvider,
    },
    LayoutService
];

@NgModule({
    imports: [
        CommonModule,
    ],
    exports: [
        NbAuthModule,
    ],
    declarations: [],
})
export class ThemeCoreModule {
    constructor(@Optional() @SkipSelf() parentModule: ThemeCoreModule) {
        throwIfAlreadyLoaded(parentModule, 'CoreModule');
    }

    static forRoot(): ModuleWithProviders {
        return {
            ngModule: ThemeCoreModule,
            providers: [
                ...NB_CORE_PROVIDERS,
            ],
        };
    }
}