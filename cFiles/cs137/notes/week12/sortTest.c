#include <stdio.h>
#include <stdlib.h>

int compare (const void *a, const void *b);

int main(void){
	int a [] = {1, 2, 3, 4, 5, 2, 3, -10, 14, 41, -11, -7, -38);
	const int n = sizeof(a)/sizeof(a[0]);
	qsort(a, n, sizeof(a[0]), compare);

	for (int i = 0; i < n; i++){
		printf("%d, ", a[i]);
	}
	printf("\n");

	return 0;
}

int compare(const void *a, const void *b){
	int val_a = *(int *) a; // can't do *a...because it is a void pointer
	int val_b = *(int *) b;
	return (val_a < val_b) ? -1 : (val_a > val_b) ? : 1 : 0;
}