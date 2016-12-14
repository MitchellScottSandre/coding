#include <stdio.h>


int partition (int a[], int n){
    int m = 0;
    for (int i = 1; i < n; i++){
        if (a[i] < a[0]){
            m++;
            swap(a,m,i);
        }
    }
    swap (a,m,0);
    return m;
}

void quicksort(int a[], int n){
    if (n <= 0)returnl
    int m = partition(a, n);
    quicksort(a, m);//start at a, length is m
    quicksort(a + m + 1, n - m - 1);//start at a + m + 1, length is n - m  -1
}
