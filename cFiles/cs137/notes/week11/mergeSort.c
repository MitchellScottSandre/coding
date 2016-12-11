#include <stdio.h>
#include <stdlib.h>
#include <assert.h>
//basic idea, divide array in half
//sort each half
//merge the results

void mergesort(int *a, int *temp, int n ){
	if (n <= 1) return;
	//sort halfs
	mergesort(a, temp, n/2);
	mergesort(a + n/2, temp, n - n/2); // n might not be even

	//merge halfs --> iterate through each index (two halfs are sorted!) put lower one in, incriment THAT index, then repeat (two different indexes for each half)

	int i = 0, j = n/2, k = 0;
	while (i < n/2 && j < n){
		if (a[i] <= a[j]){
			temp[k++] = a[i++];
		} else {
			temp[k++] = a[j++];
		}
	}
	//one half is done, since i is either greater than n/2 or j is greataer than n (i went past 1 half, j went past full length
	//i is on left, j is on right
	while (i < n/2){//only one of these while loops will be entered
		temp[k++] = a[i++];
	}
	while (j < n){
		temp[k++] = a[j++];
	}

	//now copy from temp[] to a[]
	for (i = 0; i < n; i++){
		a[i] = temp[i];
	}

}

void sort(int a[], int n){
	int *temp = malloc(n * sizeof(int));
	assert(temp);
	mergesort(a, temp, n);
	free(temp);
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
	

	
	
	printf("\n");
	return 0;
}