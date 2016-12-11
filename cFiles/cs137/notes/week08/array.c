#include <stdio.h>
#include <assert.h>
#include <stdlib.h>

int *numbers (int n){
	//fill with numbers 0 to n
	int *p = malloc(n * sizeof(int) );
	assert(p != NULL);
for (int i = 0; i < n; i++){
p[i] = i;
}
return p;
}

int main(void){
int *q = numbers(100);
printf("%d\n", q[50]);
free(q);
return 0;
}
