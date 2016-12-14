#include <stdio.h>

void selectionSort (int a[], int n){
    if (n <= 0) return;
    for (int i = 0; i < n; i++){
        int minLoc = i;
        for (int j = i + 1; j < n; j++){
            if (a[j] < a[minLoc]){
                minLoc = j;
            }
        }
        //now swap
        int temp = a[minLoc];
        a[minLoc] = a[i];
        a[i] = temp;
    }
}

void insertionSort(int a[], int n){
    if (n <= 0)return;
    for (int i = 1; i < n; i++){
        int x = a[i];
        int j = i - 1;
        while (j >=0 && a[j] > x){
            a[j + 1] = a[j];
            --j;
        }
        a[j + 1] = x;
    }
}

void mergeSort(int *a, int *temp, int n){
    if (n <= 0) return;
    mergeSort(a, temp, n/2);//left side
    mergerSort(a + n/2, temp, n - n/2);

    int i = 0; j = n/2, k = 0;
    while (i < n/2 && j < n){
        if (a[i] >= a[j]){
            temp[k++] = a[i++];
        } else {
            temp[k++] = a[j++];
        }
    }

    //only going to enter one of these loops
    while (i < n/2){
        temp[k++] = a[i++];
    }
    while (j < n){
        temp[k++] = a[j++];
    }

    //put into temp
    for (int i = 0; i < n; i++){
        a[i] = temp[i];
    }
}

void swap(int a[], int i, int j){
    int temp = a[i];
    a[i] = a[j];
    a[j] = temp;
}

void partition(int a[], int n){//pivot is a[0]
    int m = 0;
    for (int i = 1; i < n; i++){
        if (a[i] < a[0]){
            m++;
            swap(a, m, i);
        }
    }
    swap(a, m, 0);
    return m;
}

void quickSort(int a[], int n){
    if (n <= 1) return;
    int m = partition(a, n);
    quicksort(a, m);
    quicksort(a + m + 1, n - m - 1);
}

int main(void){
    return 0;
}
