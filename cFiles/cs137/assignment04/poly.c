#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>

int nextNumberInfo(double a[], int n, int loc) {
    // -1 0 0 0 8
    int solution = 0;
    int i;
    for (i = loc - 1; i >=0; i--) {
        if (a[i] != 0) {
            if (a[i] < 0) return 1;//less than 0
            return 2;//more than 0
        }
    }
    return 3; // no non zero next number

}
void polyPrint(double a[], int n) { //use fmod instead of %
    int i, locFirstNum;
    double ebsalon = 0.000000000000000000000000000000000000000001;
    bool havePrintedFirstNumber = false;
    if (n == 0) {
        printf("0\n");
    } else {
        for (i = n - 1; i >= 0; i--) {
            if (a[i] != 0) {

                if (havePrintedFirstNumber == false && a[i] < 0) {                      //first number to be printed is negative:      -
                    printf("-");
                    locFirstNum = i;
                    havePrintedFirstNumber = true;
                }
                havePrintedFirstNumber = true;
                if (fabs (fabs (a[i]) - 1.000000000000000000000000000 ) > ebsalon) { //number is not 1
                    printf("%g", fabs(a[i]));
                } else if (i == 0 && fabs (fabs (a[i]) - 1.000000000000000000000000000 ) < ebsalon) { //number is 1, i is 0
                    printf("1");
                }
                if (i > 0) { //has an x value
                    printf("x");
                }
                if (i > 1) {
                    printf("^%d", i);//x^2 or x^8
                }
                if (i > 0) {
                    switch (nextNumberInfo(a, n, i)) {
                    case 1:
                        printf(" - ");
                        break;
                    case 2:
                        printf(" + ");
                        break;
                    }
                }
            } else if (havePrintedFirstNumber == false && i == 0) {
                printf("0");
            }
        }
        printf("\n");
    }
}


