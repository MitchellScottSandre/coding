#include <stdio.h>

int main (void) {
printf("Enter number n, then enter n more numbers, sum will be outputted\n");
int n;
scanf("%d", &n);
int i; 
int sum = 0;
int val;
for (i = 0; i < n; i++){
	scanf("%d", &val);
	sum += val;
}

printf("Total sum of all values entered is %d\n", sum);
return 0;
}
