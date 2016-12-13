#include <stdio.h>
#include <math.h>
#include <stdlib.h>
int main(void){
    int x;
    printf("Enter dec val x to see it in binary: ");
    scanf("%d", &x);
    int length = (int) ( log(x * 1.00) / log(2.00)) + 1;
    int data[length];
    for (int i = 0; i < length; i++){
        data[i] = 0;
    }
    //now convert to binary
    //7
    //7 % 2 == 1,
    //7 / 2 == 3... 3 % 2 == 1.
    //3 / 2 == 1... 1 % 2 == 1.
    //1 / 2 == 0... 0 % 2 == 0
    //0111
    int index = 0;
    while (x > 0){
        data[index] = x % 2;
        x /= 2;
        index++;
    }

    for (int i = length - 1; i >=0 ;i--){
        printf("%d ", data[i]);
    }
    printf("\n");
    return 0;
}
