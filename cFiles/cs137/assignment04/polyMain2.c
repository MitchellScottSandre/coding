#include <stdio.h>

int main(void){
printf("Num of digits in polynomial is 8\n");
double array[8] = {0};
double x;
int  i;
for (i = 0; i < 8; i++){
        printf("Enter digit %d:", (i + 1));
        scanf("%lf", &x);
        array[i] = x;
}

polyPrint (array, sizeof(array)/sizeof(array[0]));
}
