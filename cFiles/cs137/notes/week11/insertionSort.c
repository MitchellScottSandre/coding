#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
//find out where x should
//go, shift elements greater then x to right, insert x, repeat n - 1

/*
38 -7 14 2 11 -10 // have x be -7, -7 is less than 38, so swap
-7 38 14 2 11 -10 //14 is smaller thn 38, so swap
-7 14 38 2 11 -10 //s is smaller than 38 so swap, but it is saller than 14 too
-7 2 14 38 11 -10

time complexity
outer loop: n - 1 iterations
inner lop: 0 to i iterations
best case: (already sorted), inner loop is always 0 iterations --> time complexity is O(n)
worst case: sorted in reverse order, inner loop: 1 + 2 + ... + (n-1) is O n^2
*/

void insertionSort(int *a, int n){
	for (int i = 1; i < n; i++){
		int x = a[i];
		int j = i - 1;
		while (j >= 0 && a[j ] > x){
			a[j + 1] = a[j];
			--j;
		}
		a[j + 1] = x;
	}
}

void sort(int a[], int n){
	insertionSort(a , n);

}



int main(void){
	int choice;
	printf("Enter 1 for pre selected sort of numbers, 2 for enter your own:");
	scanf("%d", &choice);
	int size;
	if (choice == 1){
	size = 8;
	//int  = malloc(sizeof(int) * size);
	int a[] = {38, -7, 14, 2, 11, -10, 0, 42};
	sort(a, size);
	for (int i = 0; i < size; i++){
		printf("%d ", a[i]);
	}
	} else {
		size;
		printf("Enter length (int): ");
		scanf("%d", &size);
		int *a = malloc(sizeof(int) * size);
		for (int i = 0; i < size; i++){
			int x;
			scanf("%d", &x);
			a[i] = x;
		}
		sort(a, size);
		for (int i = 0; i < size; i++){
				printf("%d ", a[i]);
		}
}
return 0;
}
