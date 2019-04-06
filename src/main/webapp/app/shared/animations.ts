import { trigger, state, style, transition,
    animate, group, query, stagger, keyframes
} from '@angular/animations';

export const SlideInOutAnimation = [
    trigger('slideInOut', [
        state('in', style({
            'max-height': '500px', 'opacity': '1', 'visibility': 'visible'
        })),
        state('out', style({
            'max-height': '0px', 'opacity': '0', 'visibility': 'hidden'
        })),
        transition('in => out', [group([
                animate('400ms ease-in-out', style({
                    'opacity': '0'
                })),
                animate('600ms ease-in-out', style({
                    'max-height': '0px'
                })),
                animate('700ms ease-in-out', style({
                    'visibility': 'hidden'
                }))
            ]
        )]),
        transition('out => in', [group([
                animate('500ms ease-in-out', style({
                    'visibility': 'visible'
                })),
                animate('800ms ease-in-out', style({
                    'max-height': '500px'
                })),
                animate('1200ms ease-in-out', style({
                    'opacity': '1'
                }))
            ]
        )])
    ]),
    trigger(
        'enterAnimation', [
            transition(':enter', [
                style({transform: 'translateX(100%)', opacity: 0}),
                animate('1000ms', style({transform: 'translateX(0)', opacity: 1}))
            ]),
            transition(':leave', [
                style({transform: 'translateX(0)', opacity: 1}),
                animate('500ms', style({transform: 'translateX(100%)', opacity: 0}))
            ])
        ]
    ),
    trigger(
        'updownAnimation', [
            transition(':enter', [
                style({transform: 'translateY(100%)', opacity: 0}),
                animate('1000ms', style({transform: 'translateY(0)', opacity: 1}))
            ]),
            transition(':leave', [
                style({transform: 'translateY(0)', opacity: 1}),
                animate('500ms', style({transform: 'translateY(100%)', opacity: 0}))
            ])
        ]
    ),
    trigger(
        'hover', [
            transition(':enter', [
                style({'box-shadow': '0 2px 8px #334', opacity: 1}),
                animate('500ms', style({transform: 'scale(1.1)', opacity: 1}))
            ]),
            transition(':leave', [
                style({opacity: 0}),
                animate('500ms', style({opacity: 0}))
            ])
        ]
    )
]
