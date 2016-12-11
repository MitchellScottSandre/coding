#include <stdio.h>
//pick a pivot, divide the array into parts that are less than the pivot, and greater than the pivot
//two cases: pivot is element 0
		//if x >= p, just add it to end?
		//if x < p, we have to move it to left of pivot

/*
Run Time Complexity of QuickSort:
Best Case: each partitioning yields two equal-sized partitions . best case is same as margesort log2 of n levels
Average Case is O(nlogn). p falls between 25th and 75th percentile of highest, you get 3:1 partition sizes at worst, and 1:1 partition size at best
results in log (4/3) n --> 0 nlogn for QuickSort
worst case: p is always the smallest or largest element in the array. worst case is O(n ^ 2)
4 1 2 3...pivot is at 4.
time is N + n - 1 + n -2 + n - 3.... O N^2
Back to the average case, what we wanted was to select a pivot such that at worst.. what we have been using as our pivot is a[0].
what we suggest is take 3 values from the array..first, last, middle, take the median of those 3.. this increase your chances of the middle pivot being in the
25th to 75th percitle
this is to get O n log n performance
pick the mediuan of the 3 values instead of a[0] for the pivot
p1, p2, p3
swap a[0] with the median of p1, p2, p3...
another technique is to do the median of 3 medians

space complexity of merge sort
is order N --> additional storage. this is slightly fudging the truth


*/

void swap (int a[], int i, int j){
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

int partition (int a[], int n){//pivot is a[0]
	int m = 0;
	for (int i = 1; i < n; i++){
		if (a[i] < a[0]){
			m++;
			swap(a, m, i);
		}
	}
	swap(a, 0, m);
	return m;
}
void quicksort(int a[], int n){
	if (n <= 1) return;
	int m = partition(a, n);
	quicksort(a, m);
	quicksort(a + m + 1, n - m - 1);
}

int main (void){
	int a[] = {2, -10, 14, 42, 11, -7, 0, 38};
	printf("Before:\n");
	for (int i = 0; i < sizeof(a)/sizeof(a[0]); i++){
		printf("%d ", a[i]);
	}
	printf("\nAfter:\n");
	quicksort(a, sizeof(a)/sizeof(a[0]));
	for (int i = 0; i < sizeof(a)/sizeof(a[0]); i++){
		printf("%d ", a[i]);
	}
	printf("\n");
}
