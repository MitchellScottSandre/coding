#include <stdio.h>

void swap(int *p, int *q){
int temp = *p;
*p = *q;
*q = temp;
}

void swap2(int *x, int *y){
	if (x != y){
		*x ^= *y;
		*y ^= *x;
		*x ^= *y;
	}
//x    y
//step 1: x  XOR y | y
//step 2: x XOR y | y xOR (x xOR y)
//ste
}

int main(void){

int i = 5, j = 10;
swap(&i, &j);
printf("%d %d\n", i, j);
return 0;
}
