#include <stdio.h>


void selectionsort(int a[], int n){
    if (n <= 0)return;
    for (int i = 0; i < n; i++){
        int minLoc = i;
        for (int j = i + 1; j < n; j++){
            if (a[i] < a[minLoc]){
                minLoc = i;
            }
        }
        //swap them
        int temp = a[minloc];
        a[minloc] = a[i];
        a[i] = temp;
    }
}

void insertionSort(int a[], int n){
    if (n <= 0) return;
    for (int i = 1; i < n; i++){
        int x = a[i];
        int j =i -1;
        while (j >= 0 && a[j] > x){
            a[j + 1] = a[j];
            --j;
        }
        a[j + 1] = x;
    }
}

void mergeSort(int *a, int *temp, int n){
    if (n <= 0) return;
    mergesort(a, temp, n/2);//left side
    mergesort(a + n/2, temp, n - n/2);

    int i = 0, j = n/2, k = 0;//left index, j starts at middle, k is index if temo
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
        temp[k++] = a[i++];
    }

    //now copy from temp to a
    for (int i = 0; i < n; i++){
        a[i] = temp[i];
    }
}

void swap(int a[], int i, int j){
    temp = a[i];
    a[i] = a[j];
    a[j] = a[i];
}

int partition(int a[], int n){
    int m = 0;
    for (int i = 1; i < n; i++){
        if (a[i] < a[m]){
            m++;//FORGOT THIS
            swap(a, m, i);
        }
    }
    swap (a,m,0);
    return m;
}

void quicksort(int *a, int n){
    if ( n <= 0) return;
    int m = partition(a, n);
    quicksort(a, m);
    quicksort(a + m + 1, n - m - 1);
    //done/??
}
