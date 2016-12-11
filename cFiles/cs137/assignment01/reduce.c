#include <stdio.h>
#include <stdlib.h>
int gcdFunction(int a, int b) {
    int r = 0;
    while (b) {
        r = a % b;
        a = b;
        b = r;
    }
    return a;
}

int main(void) {
    int num, denom, gcd;
    scanf("%d", &num);
    scanf("%d", &denom);
    if (denom == 0) { // 5 / 0
        printf("Divide by zero!\n");
    } else if (num == 0) { // 0 / 22
        printf("0\n");
    } else if (num % denom == 0) { // 10 / 2;   -5/5
        printf("%d\n", num / denom);
    } else {
        if(num * denom < 0) { //one of them is < 0
            printf("-");
        }
        num = abs(num);
        denom = abs(denom);
        gcd = gcdFunction (num, denom);
        if (num > denom) { // - 10 / 3
            printf("%d %d/%d\n", num / denom, (num - (num / denom)*denom) / gcd, denom / gcd);
        } else {//num < denom
            printf("%d/%d\n", num / gcd, denom / gcd);
        }
    }
    return 0;
}
