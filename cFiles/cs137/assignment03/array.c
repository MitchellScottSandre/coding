int max(int array[], int n) { //returns the largest value in the array or 0 if the array has size 0
    if (n == 0) return 0;
    int m = array[0];
    int i;
    for (i = 0; i < n; i++) {
        if (array[i] > m) {
            m = array[i];
        }
    }
    return m;
}

int countValue(int array[], int n, int value) { // returns the number of times the specified value occurs in the array.
    if (n ==0) return 0;
    int i, counter = 0;
    for (i = 0; i < n; i++) {
        if (array[i] == value) {
            counter++;
        }
    }
    return counter;
}

void absolute(int array[], int n) { //- sets each element in the array to its absolute value
    int i;
    for (i = 0; i < n; i++){
        if (array[i] < 0){
            array[i] = -array[i];//does c allow this
        }
    }

}

int isSorted(int array[], int n) { //returns 1 if the array is sorted in ASCENDING order; 0 otherwise. In the case that the array has size zero, return 1.
    if (n == 0) return 1;
    int i;
    for (i = 0; i < n - 1; i++){
            if (array[i + 1] < array[i]){
                return 0;//it is lower than the one before it, therefore not in ascending order
            }
    }
    return 1;

}

//what if n is negative? //assuming positive for now
int isPermutation(int array[], int n) { // returns 1 if the array is a permutation of the integers 1 to n (i.e., it contains each of the integers from 1 to n exactly once) or if the array has size zero; 0 otherwise.
    if (n == 0) return 1;
    if (n < 0) return 0;
    int i;
    int k[n];
    for (i = 0; i < n; i++){ // wasn't allowed to do k[n] = {0};//idky
            k[i] = 0;//k array is all 0s
    }
    for (i = 0; i < n; i++){
        if (array[i] <= 0) return 0;
        if (array[i] > n) return 0;
        k[array[i] - 1] = 1;//if a has number 1, k[0] = 1; if array has number 2, k[1] == 1
    }
    for (i = 0; i < n; i++){
        if (k[i] == 0) return 0;//did not have all of the numbers from 1 to n
    }
    return 1;//every index in the array of length n is used up, therefore array contains values from 1 to n (as each index represents value i + 1)
}


