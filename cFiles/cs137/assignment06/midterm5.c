#include <stdio.h>
#include <math.h>
#include <stdbool.h>

int lengthOfInt(int x){
    int counter = 0;
    while (x >= 1){
        counter++;
        x = x / 10;
    }
    return counter;
}

int main(void) {
    int x, a, b, c;
    int digitsUsed[10] = {};//sets them all to 0
    bool printMe = false;
    scanf("%d", &x);

    while (x > 0) { //if x contains a digit that hasn't been used yet, print x
        b = x;//copy
        a = (int) lengthOfInt(x);
        printMe = false;
        for (int i = 0; i < a; i++) { //a is length of that number, so 1003 log 10 is 3, length is 4
            c = b % 10;//c is last digit of that number, so for 1003 c = 3
            if (digitsUsed[c] == 0) {
                printMe = true;
                digitsUsed[c] = 1;
            }
            b = b / 10;

        }
        if (printMe == true) printf("%d\n", x);

        scanf("%d", &x);
    }

    return 0;
}
