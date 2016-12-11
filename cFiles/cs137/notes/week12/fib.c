#include <stdio.h>
int fib(int n){//exponential runtime complexity
	if (n == 0) return 0;
	if (n == 1) return 1;
	return fib(n - 1) + fib(n - 2);
}

int betterFib(int n){//n runtime complexity
	if (n == 0) return 0;
	int prev = 0, current = 1;
	for (int i = 2; i < n; i++){//i is what term it is calculating
		int next = prev + current;
		prev = current;
		current = next;
	}
	return current;
}

int main(void){
	printf("%d\n", fib(38));// < 1 second
	printf("%d\n", fib(42));// ~ 3 seconds
	printf("%d\n", betterFib(22));
	printf("%d\n", betterFib(44));
	//prntf("%d\n", fib(45)); //biggest that fints in a 4 byte int... ~13 seconds
}

//call tree: really innefficient. sometimes we call the same thing twice
//observation, # of fib(1) leaves = fib(n) -> summing this is O(fix(n))