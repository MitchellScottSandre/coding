#include <stdio.h>
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


int main(void){
printf("Keep entering numbers to see if they are prime or not.\nEnter 0 to exit the loop\n");
int num;
while (true){
	scanf("%d", &num);
	if (num == 0) return;
	printf("%d is %sprime\n", num, !isPrime(num) ? "not ":"");

}
return 0;
}
