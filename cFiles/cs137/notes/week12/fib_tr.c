#include <stdio.h>

int fib_tr(int prev, int curr, int n){
	if(n == 0) return curr;
	return fib_tr(curr, prev + curr, n - 1);
}

int fib(int n){
	if (n == 0) return 0;
	return fib_tr(0, 1, n-1);
}

int main(void){
printf("%d\n", fib(43));
	return 0;
}

//tail call elimination
//re uses the activation records, things dont need old values