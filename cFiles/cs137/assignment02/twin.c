#include <stdbool.h>

bool isPrime(int num){
	int divisor = 2;
	if (num <= 1) return false;
	while (num / divisor >= divisor){
		if (num % divisor == 0) return false;
		divisor++;
	}
	return true;
}

int isTwin(int number){
    if (number <= 0) return 0;
    if (isPrime (number) == false) return 0;
    if (isPrime (number + 2) == true) return 1;
    if (isPrime (number - 2) == true) return 1;
    return 0;
}
