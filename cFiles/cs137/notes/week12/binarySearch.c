#include <stdio.h>

int search(int a[], int n, int value);//returns the index of the value in the sorted array, or -1 if the value is not found
//basic idea
//proble middle element a[m],
//if it is equal to the value that we want, return m
//if it is larger than the value we want, search lower half
//if it is smaller than the value we want, search top half
/*
worst case: found, not found when lo == hi
amount of work for each level is order (1)...
amount of levels is order (n)
k + 1 levels..
*/

int main(void){
	int a[] = {-10, -7, 0, 2, 11, 13, 38, 42};//a sorted array
	const int n = sizeof(a)/sizeof(a[0]);
	printf("%d\n", search(a, n, 0));
	printf("%d\n", search(a, n, 3));
}

int search(int a[], int n, int value){
	int lo = 0, hi = n - 1;

	while (lo <= hi){
		int m = lo + (hi - lo) / 2; //don't want (lo + hi) to overflow and then try dividing that sum by 2
		if (a[m] == value) return m;
		if (a[m] > value) hi = m - 1;
		else lo = m + 1;
	}
	return -1;
}