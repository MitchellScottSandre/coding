//Closure: a lambda function that captures variables from its containing scrope

#include <stdio.h>

auto return_fib(){
	int prev = 0, curr = 1;
	return [prev, curr]() mutable {//mutable makes captured variables writable
		int next = prev + curr;
		prev = curr;
		curr = next;
		return prev;
	};
}

int main(){
	auto f1 = return_fib();
	auto f2 = return_fib();
	printf("%d\n", f1());//1
	printf("%d\n", f1());//1
	printf("%d\n", f1());//2
	printf("%d\n", f2());//1
	return 0;
}


