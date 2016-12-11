
#include <stdio.h>
#include <stdlib.h>

int *histogram(const int *a, int n, int *m){//return new size m //return null if only negative numbers
    // find largest value in the array
    // count number of negative values
    //make new array (pointer) of correct size
    int *k = NULL;
    if (n <= 0) return k; // case: size is 0
    int newSize, numNegatives = 0, largestNum = a[0];
    for (int i = 1; i < n; i++){
        if (a[i] > largestNum) largestNum = a[i];
        if (a[i] < 0) numNegatives++;
    }
    if (numNegatives == n) return k; //case: only negative numbers
    newSize = largestNum + 1;
    int *hist = calloc(newSize, sizeof(int)); //hist is now all 0s
    int index = 0;
    for (int i = 0; i < n; i++){
        if (a[i] >= 0){
            hist[a[i]]++;
        }
    }
    *m = newSize;
    return hist;

}
