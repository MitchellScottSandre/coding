#include <stdio.h>
//try using lu instead of d when using %d when doing the iseof double
int main(void){

double x = -2.3450e30;
printf("Size of Double: %d\n", sizeof(double));
printf("Prining out using percent f instead of percent d: %f\n", x);//should print out -2.3450 000 000 000 00 123 242 488 967 128.00000 //rounding error
printf("%.2e\n", x);//-2.35e + 30
printf("%g\n", x);//chooses between e and f, chooses whichever one uses fewer characters in output

return 0;
}
