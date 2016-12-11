#include <stdio.h>
void printAverageAmount(double money[], int n) {
    double sum = 0.0, centsDouble;
    int i, dollars, centsInt;
    for (i = 0; i < n; i++){
        sum += money[i];
    }
    dollars = sum / n; // truncates down, rounds
    centsDouble = (sum / n ) - dollars;
    centsInt =  centsDouble * 100;
    printf("Everyone gets %d dollar(s) and %d cent(s).\n", dollars, centsInt);
}
