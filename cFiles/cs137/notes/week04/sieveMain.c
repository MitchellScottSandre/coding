#include <stdio.h>
#include <assert.h>
void sieve(int a[], int n);

int main(){
int n;
printf("Please enter a number up to which all the prime numbers between 0 and that number will be printed:\n");
scanf("%d", &n);
assert(n > 0);//make sure that the number entered is above 0
int a[n + 1];
sieve (a, n + 1);
int x;
for (x = 0; x <= n; x++){

	if (a[x]) printf("%d\n", x);
}

return 0;
}
