#include <stdio.h>
#include <stdlib.h>

int main(){
	int a[] = {2, -10, 14, 42, 11, 7, 6, 5, 88};
	const int n = sizeof(a) / sizeof(a[0]);
	qsort(a, n, sizeof(int), [](const void *a, const void *b) {return *(int *)a - *(int *) b;}

	for (int i = 0; i < n; i++){
		printf("%d\b", a[i]);
	}
}