//find smallest element in the entire array
//swap with the first element in the array
//first element in the array is now sorted
//repeat with the rest of the array

#include <stdio.h>
#include <assert.h>
void selectionSort(int a[], int n){
	assert(n > 0);
	for (int i = 0; i < n - 1; i++){
		int pos = i;
		for (int j = i + 1; j < n; j ++){
			if (a[j] < a[pos]){
				pos = j; //re iterates and repeatas, so last one will make sure that it is the smallest value
			}
		}
	}
	//need to swap

}

int main(void){

int a[] = {38, -7, 14, 2, 11, -10};
sort(a, sizeof(a) / sizeof(a[0]) );
for (int i = 0; i < sizeof (a) / sizeof(a[0]); i++){
printf("%d\n", a[i]);
}

return 0;
}

// int i,j;
// int n;

// /* advance the position through the entire array */
// /*   (could do j < n-1 because single element is also min element) */
// for (j = 0; j < n-1; j++) 
//   {
//     /* find the min element in the unsorted a[j .. n-1] */

//     /* assume the min is the first element */
//     int iMin = j;
//      test against elements after j to find the smallest 
//     for ( i = j+1; i < n; i++) {
//         /* if this element is less, then it is the new minimum */
//         if (a[i] < a[iMin]) {
//             /* found new minimum; remember its index */
//             iMin = i;
//         }
//     }

//     if(iMin != j) 
//     {
//         swap(a[j], a[iMin]);
//     }
// }
