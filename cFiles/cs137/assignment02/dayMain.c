#include <stdio.h>

int dayOfYear(int day, int month, int year);
void testDayOfYear(int day, int month, int year) {
    printf ("%d/%d/%d => %d\n", day, month, year,
            dayOfYear(day, month, year));
}
int main (void) {
    printf("my tests\n");
    testDayOfYear (-1, 1, 2008);
    testDayOfYear (33, 1, 2008);
    testDayOfYear (1, -1, 2008);
    testDayOfYear (1, 0, 2008);
    testDayOfYear (1, 12, 2008);
    testDayOfYear (1, 13, 2008);
    testDayOfYear (1, 1, 1700);
    printf("....\n");
    testDayOfYear (1, 1, 2008);
    testDayOfYear(30, 1, 2008);
    testDayOfYear(28, 2, 2008);
    testDayOfYear (30, 2, 2008);
    testDayOfYear (30, 2, 2009);
    testDayOfYear (29, 2, 2008);
    testDayOfYear (29, 2, 2009);
    testDayOfYear (26, 9, 2008);
    testDayOfYear (31, 12, 2008);
    testDayOfYear (31, 12, 2009);
    testDayOfYear (100, 1, 2008);
    testDayOfYear (1, 100, 2008);
    testDayOfYear (1, 1, 100);


//    printf("\n\nNow trying all combinations...\n\n");
//    int year, day, month, z;
//    for (year = 2007; year <= 2008; year++) {
//        for (month = 1; month <= 12; month ++) {
//            for (day = 0; day <= 31; day++) {
//                z = dayOfYear (day, month, year);
//                if (z != -1) {
//                    printf("%d, ", z);
//                }
//                if (z % 10 == 0) printf ("\n");
//            }
//        }
//        printf("\n\nDifferent year now\n\n");
//    }


    return 0;
}
