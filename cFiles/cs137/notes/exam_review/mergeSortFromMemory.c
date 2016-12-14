#include <stdio.h>

void mergeSort(int *a, int *temp, int n){
    if (n <= 0) return 0;
    mergeSort(a, temp, n/2);//left half
    mergeSort(a + n/2, temp, n - n/2);

    int i = 0, j = n/2, k = 0;//i is left index, j is middle, k is index of temp
    while (i < n/2 && j < n){
        if (a[i] >= a[j]){
            temp[k++] = a[i++];
        } else {
            temp[k++] = a[j++];
        }
    }

    //only one of these loops will be entered
    while (i < n/2){
        temp[k++] = a[i++];
    }
    while (j < n){
        temp[k++] = a[j++];
    }

    //now copy from temp into a
    for (int i = 0 ; i < n; i++){
        a[i] = temp[i];
    }
}

int main(void){

    return 0;
}
