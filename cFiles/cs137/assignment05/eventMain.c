#include <stdio.h>
#include "event.h"

int main (void) {
    struct event schedule[] = {{{9,45},{9,55}},{{13,0},
            {14,20}
        },{{15,0},{16,30}}
    };

    printf ("%d\n", freetime (schedule,3,8,0));
    printf ("%d\n", freetime (schedule,3,9,50));
    printf ("%d\n", freetime (schedule,3,13,0));
    printf ("%d\n", freetime (schedule,3,16,30));
    printf("my tests now...\n");

    struct event schedule2[] = { {{0, 0}, {3, 30}}, {{4, 15}, {5, 22}} };
    printf ("%d\n", freetime (schedule2,2,2,44));//2 44 is between 0 and 3:30, should print 0
    printf ("%d\n", freetime (schedule2,2,3,29)); //should print 0
    printf ("%d\n", freetime (schedule2,2,3,30));//1
    struct event schedule3[] = { {{0, 15}, {0, 15}} };
    printf ("%d\n", freetime (schedule3,1,0,15));// should be free, shoudl print 1
    struct event schedule4[] = { {{0, 0}, {24, 44}} };
    printf ("%d\n", freetime (schedule4,1,0,15));// should be free, shoudl print 0

    return 0;
}
