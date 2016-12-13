#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <assert.h>
//==============================Random Algorithms
//GCD (Eucler's algorithm)
int gcd(int a, int b){
	int r = 0;
	while (b){
		r = a % b;
		a = b;
		b = r;
	}
	return a;
}

//PRIME
bool isPrime(int num){
	int divisor = 2;
	if (num <=1 ) return false;
	while (num / divisor >= divisor){
		if (num % divisor == 0)return false;
		divisor++;
	}
	return true;
}

//ERATOSTHENES SIEVE //way of finding all prime numbers in range
//0 means not prime, 1 means yes prime
void sieve(int a[], int m){//m is length of a
	//--> need to include <stdbool.h>
	if (m <= 1) return;//a has no length
	a[0] = 0; // 0 is NOT prime
	a[1] = 0;
	for (int i = 2; i < m ; i++){
		a[i] = 1;//may be prime
	}
	for (int i = 2; i < m; i++){//iterate from 3rd index to the end
		if (a[i] == 1){
			for (int j = 2*i; j < m; j += i){//start at 2* the number you are at, increase by that value each iteration until the end index
				a[j] = 0;//all of these values are multiples of i!!!
			}
		}
	}
}

//BASIC FUNCTION EVALUATION
//5 + 3x + 2x^2....
//x^0 is on left
double evaluateFunction(double f[], int n, double x){
	//need to include <math.h>
	if (n == 0) return 0;
	double sum = 0;
	for (int i = 0; i < n; i++){
		sum += pow(x, i) * f[i];
	}
	return sum;
}

//HORNERS METHOD --> evalutes a function f(x)
double horner(double f[], int n, double x){
	if (n <= 0)return 0;
	double y = f[n-1];
	for (int i = n - 2; i >= 0; i--){
		y = x * y + f[i];
	}
	return y;
}
//BISECTION
//a, b are two different x locations, a is on the left, b on the right
double function(double x){
	return x * x * x;//x ^ 3
}

double bisect(double a, double b, double epsilon){
	//--> need to include <assert.h>
	//--> need to include <math.h>
	//--> need to have made double function(x) that returns value
	assert(function(a) < 0 && function(b) > 0 && epsilon > 0);
	double m = (a + b) / 2; // this is the mid point between points a, b
	if (fabs(m) < epsilon) return m;
	if (function(m) > 0) return bisect(a, m, epsilon);// m is to the right of 0, so try again with a on left of 0, m on right of 0 instead of b
	else return bisect (b, m, epsilon);

}
//COUNTING CHANGE

//===============================SORTING=======================
//SELECTION SORT
void selectionSort(int a[], int n){
	if (n <= 0) return;
	for (int i = 0; i < n - 1; i++){
		int min = i;//just set the INDEX of smallest value to I
		for (int j = i + 1; j < n; j++){
			if (a[j] < a[min]){
				min = j;//iterate through and find the smallest value
			}
		}
		int temp = a[min];//once you have iterated all the way through
		a[min]= a[i];
		a[i] = temp;
	}
}

//INSERTION SORT
//seperate the array into sorted and unsorted parts
//as you iterate across, the size of the sorted part grows
//each time when the size of it grows, you are adding a value, and inserting it into
//where it should go in the sorted part (so it remains sorted)
//if need be, shift elements that are > x in the sorted part, then insert n
void insertionSort(int a[], int n){
	for (int i = 1; i < n; i++){//we are saying that a[0] is our sorted part
		int x = a[i];
		int j = i - 1;
		while (j >= 0 && a[j] > x){//shifting elements that are greater than x
			a[j + 1] = a[j];
			--j;
		}
		a[j + 1] = x;//inserting x
	}
}

//MERGE SORT
//divide the array in half
//recursively sort each half (by diving them in half, and continuing to divide them,
//then merging them into sorted order as your come back up
void mergeSort(int *a, int *temp, int n){
	if (n <= 1) return;
	mergeSort(a, temp, n/2);
	mergeSort(a + n/2, temp, n - n/2);

	//merge halfs --> iterate through each index(so two halfs are sorted!)
	// put lower one in, incriment THAT index, then repeat
	int i = 0, j = n/2, k = 0;
	while (i < n/2 && j<n){
		if (a[i] <= a[j]){
			temp[k++] = a[i++];
		} else {
			temp[k++] = a[j++];
		}
	}
	//one half is done, since i is either greater than n/2 or j is greater than n
	//ie, i went past half, j went past the full length
	while (i < n/2){//only one of these two loops will be entered.
		temp[k++] = a[i++];//either this loop
	}
	while (j < n){
		temp[k++] = a[j++];//or this loop
	}

	//now copy from temp[] to a
	for (int i = 0; i < n; i++){
		a[i] = temp[i];
	}
}

void useThisToCallMergeSort(int a[], int n){
	int *temp = malloc(n * sizeof(int));
	assert(temp);
	mergeSort(a, temp, n);
	free(temp);
}

//QUICK SORT
//quicksort needs: quicksort(int a[], int n), and it needs
//partition (int a[], int n), and swap(int a, int b)
/*Better Description from Online:
1. partition array into left and right sub arrays such that
	all elements in left sub array are less than elements in
	right sub array
2. recursively sort left and right sub arrays
3. concatenate left and right sub arrays with pivot in middle

How To Partition the Array:
1 choose an elements fromm the array as a pivot
2. move all elements < pivot to LEFT, all elements > pivot to RIGHT;
*/
void swap(int a[], int i, int j){
	int temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}
int partition(int a[], int n){//pivot is a[0]
	int m = 0;
	for (int i = 1; i < n; i++){
		if (a[i] < a[0]){//comparing to pivot at a[0]
			m++;//moving m up so it becomes middle
			swap(a, m, i);
		}
	}
	swap(a, 0, m);
	return m;
}
void quickSort(int a[], int n){
	if (n <= 1) return;
	int m = partition(a, n);
	quickSort(a, m);
	quickSort(a + m + 1, n - m - 1);
}

//Standard Library qSort function..needs compareFunction

int compareFunction(const void *a, const void *b){
 	const int *ptr1 = a;
	const int *ptr2 = b;
	int val1 = *ptr1;
	int val2 = *ptr2;
	if (val1 > val2)return 1;
	if (val1 == val2)return 0;
	return -1;
}




//===============================Searching=======================
//LINEAR SEARCH
//pretty darn easy. just look through entire array index by index and check values
int linearSearch(int a[], int n, int val){
	for (int i = 0; i < n; i++){
		if (a[i] == val) return i;
	}
	return -1;//number could not be found
}

//BINARY SEARCH
//basic idea: given a SORTED array, check middle elements a[m]
//if a[m] is our value, return m
//otherwise, if it is larger/smaller, search either the top or bottom half

int binarySearch(int a[], int n, int val){
	int lo = 0, hi = n - 1;
	while (lo <= hi){
		int m = lo + (hi - lo)/2; //we could do (lo + hi) / 2, but we dont want to add two numbers
		//that are too big and overflow the stack! so do this instead
		if (a[m] == val)return m;
		if (a[m] > val) hi = m -1;//search bottom half
		else lo = m + 1;//search top half
	}
	return -1;//didn't find it
}
//=============================POINTERS FUN======================
//...no they are not.
void swap_usingPointers(int *a, int *b){
	int temp = *a;
	*a = *b;
	*b = temp;
}

int *indexLargestVal_usingPointers(int a[], int n){
	int m = 0;//index of largest value thus far
	for (int i = 1; i < n; i++){
		if (a[i] > a[m]) m = i;
	}
	return a + m;//&(a[m]);//return location in a of largest val
}

//=============================STRUCTURES MORE FUN===============

//big 0
// unicode (binary representation)

void displayArray(int a[], int n){
	for (int i = 0; i < n; i++){
		printf("%d, ", a[i]);
	}
	printf("\n");
}


int main(void){
	printf("Testing GCD. GCD of 332 608 is %d\n", gcd(332, 608));


	printf("Testing isPrime function.\n");
	printf("5 is prime: %d\n", isPrime(5));
	printf("2660 is prime: %d\n", isPrime(2660));


	printf("Testing sieve function.\n");
	int a[200] = {0}; // this sets all values in a to 0
	const int n = sizeof(a)/sizeof(a[0]);//this is length of a
	sieve(a, n);
	for (int i = 0; i < n; i++){
		if (a[i] == 1){//is prime!!!
			printf("%d, ", i);
		}
	}
	printf("\n");

	double f[] = {1, 2.2, 3, 4, 5.555};
    int len = sizeof(f)/sizeof(f[0]);
	printf("Testing evaluation function 1\n");
	double y1 = evaluateFunction(f, len, -11.44);
	printf("f evaluated at x = -11.44 is %g\n", y1 );
	double y2 = evaluateFunction(f, len, 22.3);
	printf("f evaluated at x = 22.3 is %f\n", y2 );


	printf("Testing horner's method:\n");
	double y3 = horner(f, len, -11.44);
	printf("f evaluated at x = -11.44 is %g\n", y3 );
	double y4 = horner(f, len, 22.3);
	printf("f evaluated at x = 22.3 is %f\n", y4 );


	printf("Testing bisect algorithm:\n");
	printf("Bisecting x ^ 3, starting at -10,+ 10\n");
	printf("Result is: %g\n", bisect(-10, 10, 0.001));

	len = 20;
	int b[20] = {0};
	int c[20] = {0};
	int d[20] = {0};
	int e[20] = {0};
	int z[20] = {0};
	int y[20] = {0};
	for (int i = 0; i < len; i++){
		//--> need to include <stdlib.h> to use rand()
		b[i] = rand() % 100 - 50;//rand() produces a number between 0 and rand() max ( a big number)...so mod it
		c[i] = rand() % 100 - 50;
		d[i] = rand() % 100 - 50;
		e[i] = rand() % 100 - 50;
		z[i] = rand() % 100 - 50;
		y[i] = rand() % 100 - 50;
	}


	printf("Testing SELECTION Sort Algorithm:\n");
	selectionSort(b, len);
	displayArray(b, len);

	printf("Testing INSERTION Sort Algorithm:\n");
	selectionSort(c, len);
	displayArray(c, len);

	printf("Testing MERGE Sort Algorithm:\n");
	useThisToCallMergeSort(d, len);
	displayArray(d, len);

	printf("Testing QUICK Sort Algorithm:\n");
	quickSort(e, len);
	displayArray(e, len);

	printf("Testing standard library q sort:\n");
	//need to make a compareFunction
	//END OF SORTING
	//qsort(base array, length of array, size of each index, compareFunction)
	const int len_z = sizeof(z)/sizeof(int);
	qsort(z, len_z, sizeof(z[0]), compareFunction);
	displayArray(z, len_z);

	printf("Testing LINEAR Search:\n");
	int g[] = {1, 4, 6, 2, 3, 4, 2, -22, -55, -33, -44, 9, 98773, 222, 4444, -2222, -4, 8, 8, 2, 17, 88};
	int len_g = sizeof(g)/sizeof(g[0]);
	printf("The number 17 is located in g at index %d\n", linearSearch(g, len_g, 17));
	printf("The number 133332 is located in g at index %d\n", linearSearch(g, len_g, 133332));

	printf("Testing BINARY search - with a sorted array:\n");
	int h[] = {1,2,3,4,5,6,7,8,9,10,22,44,55,66,88,123,145,177,188,231,234,256,278,300,312,3177,1000, 1000333};
	const int len_h = sizeof(h)/sizeof(h[0]);
	printf("The value 312 is located in h at index %d\n", binarySearch(h, len_h, 312));
	printf("The value 13 is located in h at index %d\n", binarySearch(h, len_h, 13));

	//=====================POINTERS====================
	printf("Moving on to pointers...\n");
	int i = 16;	//make int
	printf("Before i is %d\n", i);
	int *p;		//make pointer
	p = &i;		//p points to the memory location of i
	*p = 22;	//the value p points to is now 22. p points to memory address of i, therefore i is 22
	printf("After i is %d\n", i);
	printf("The value of p is also %d\n", *p);

	int aa = 4, bb = 88;
	printf("Before: a is %d, b is %d\n", aa, bb);
	swap_usingPointers(&aa, &bb);
	printf("Just swapped. After: a is %d, b is %d\n", aa, bb);

	printf("Findinf largest value in array using pointers");
	int *index_maxVal = indexLargestVal_usingPointers(y, sizeof(y)/sizeof(y[0]));
	printf("Y is:\n");
	displayArray(y, sizeof(y)/sizeof(y[0]));
	printf("Largest val in y is %d\n", y[*index_maxVal]);//y at the value of largestValInY
	printf("Why the hell isn't this working???\n");



	return 0;
}
