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
printf("Enter number to which all the prime numbers lower than it will be printed: ");
int topRange;
scanf("%d", &topRange);

int i;
for (i = 0; i < topRange; i++){
if (isPrime (i) == true){
printf("%d\n", i);

}


}


return 0;
}
