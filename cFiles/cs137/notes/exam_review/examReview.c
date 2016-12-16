#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include <assert.h>
#include <string.h>
#include <time.h>
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
	double fm = function(m);
	if (fabs(fm) < epsilon) return m;
	if (function(m) > 0) return bisect(a, m, epsilon);// m is to the right of 0, so try again with a on left of 0, m on right of 0 instead of b
	else return bisect (m, b, epsilon);

}

//fib 1
int fibOne(int n){
	if (n < 0) return -1;
	if (n == 0) return 0;
	if (n == 1) return 1;
	return fibOne(n - 1) + fibOne(n - 2);
}

int fibTwo(int n){
	if (n < 0) return -1;//error
	if (n == 0) return 0;
	int prev = 0,  curr = 1;
	for (int i = 1; i < n; i++){
		int next = prev + curr;
		prev = curr;
		curr = next;
	}
	return curr;
}
//COUNTING CHANGE

//===============================SORTING=======================
//SELECTION SORT
//best: O(n squared) worst: same
//space: 1
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

//best: O(n), worst: O(n^2)
//space: 1
void insertionSort(int a[], int n){
	for (int i = 1; i < n; i++){//we are saying that a[0] is our sorted part
		int x = a[i];
		int j = i - 1;
		while (j >= 0 && a[j] > x){//putting that element into place in the sorted part of the array
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

//best nlogn, worst: nlogn
//space: n
void mergeSort(int *a, int *temp, int n){
	if (n <= 1) return;
	mergeSort(a, temp, n/2);
	mergeSort(a + n/2, temp, n - n/2);

	//merge halfs --> iterate through each index(so two halfs are sorted!)
	// put lower one in, incriment THAT index, then repeat
	//i is left index, j is middle index, k is index in temp array
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

//best nlogn, nsquared
//space: n
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

struct dog{
	int weight, age;
	char *name;
	bool tailWagging;
};

typedef struct {
	bool evil;
	int weight;
	double maxSpeed;
} cat;

void displayDogStruct(struct dog doggy){
	printf("Name: %s\n",  doggy.name);
	printf("Age: %d\nWeight: %d\nTail Wagging? %d\n", doggy.age, doggy.weight, doggy.tailWagging);
}


//big 0
// unicode (binary representation)

void displayArray(int a[], int n){
	for (int i = 0; i < n; i++){
		printf("%d, ", a[i]);
	}
	printf("\n");
}

//moving on to function pointers==================
int multiplyTwoNums(int a, int b){
	return a * b;
}

int addTwoNums(int a, int b){
	return a + b;
}

//so remember that syntax for including a funtion pointer is
// (*functionPointer)(parameter data type, param data type...etc etc);
int doStuffWithInts(int x, int y, int (*functionPointer)(int, int)){
	return functionPointer(x, y);
}


typedef struct {
	int length;
	int a[];// this NEEDS to be the LAST member in the STRUCT
} flex_array;


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
	//qsort( array, length of array, size of each index, compareFunction)
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
	printf("Why the hell does this not work??\n");

	//const int * const p;//pointer can't change and neither can the value


	//sub topic : dynamic memory allocation
	/*
	void *malloc (size_t size);//returns a pointer to a block of memory of size bytes long
	void free (void *);//frees memory block that that pointer pointer to (NOT the pointer itself)
	void *realloc( void *p, size_t size);
	//resizes previously allocated block of memory

	BIG O Notation: -> everything below is O(x) x is what is there
	most efficient
	1 constant
	logn logarithmic
	n linear time
	nlogn linearithmic time
	n^2 quadratic
	n^c polynomial time ( c greater than 2)
	c^n exponential time
	n! -- factorial
	least effiicent
	*/





	//======================STRUCTURES==================================
	struct dog toby;
	toby.weight = 45;
	toby.age = 5;
	toby.tailWagging = true;
	toby.name = "Toby";
	printf("Displaing Dog Struct 1: Toby\n");
	displayDogStruct(toby);
	struct dog dante = {14, 12, malloc(sizeof(char) * 5 + 1), true};
	dante.name = "Dante";
	printf("Displaing Dog Struct 2: Dante\n");
	displayDogStruct(dante);

	cat evilCat;
	evilCat.evil = true;
	evilCat.maxSpeed = 100;
	evilCat.weight = 9;
	cat goodCat = {.evil = false, .weight = 8, .maxSpeed = 33.222};
	printf("GoodCat: Evil?%d, Weight %d, max speed %g\n", goodCat.evil, goodCat.weight, goodCat.maxSpeed);

	//=======================STRINGS!!!======================
	//need to include --> <string.h>
	const char *string1 = "hello world";
	const char *string2 = "hello world";
	if (string1 == string2){
		printf("String1 == String2\n");
	} else {
		printf("String1 != String2\n");//it will print out this...the pointers in memory do NOT point to SAME SPOT
	}
	printf("String Length:   strlen\n");
	printf("Remember to use %%zu to print out variables of type size_t\n");
	printf("Also, remember to use two percent signs %% %% to print out one percent isgn %%\n");
	printf("strlen ----> Length of String1 is %zu\n", strlen(string1));

	printf("String Copy:    strcpy\n");
	printf("To use strcpy you must assign enough space into s2 to copy into it s1\n");
	printf("Remember that strcpy takes (copyIntoMe, const char array TO_BE_COPPIED)\n");
	char *string3 = malloc(sizeof(string2));
	strcpy(string3, string1);
	printf("Just copied string1 into string3. string3 is now %s\n", string3);

	printf("String N Copy:    strncpy\n");
	printf("Copies chars up to index n from one string to another\n");
	char *string4 = malloc(sizeof(string2));
	strncpy(string4, string2, 5);
	printf("Just copied up to 5 chars from string 2 into string 4. string4 is %s\n", string4);

	printf("String Concatenation: strcat\n");
	printf("concatentates (joins) two string together\n");
    char *string5 = malloc (sizeof(char)*50 + 1);
	string5 = "Hold the";
	char *string6 = malloc(sizeof(char) * 50 + 1);
	string6 = "door";
	//strcat(string5, string6);
	//printf("string 6 concatted to string5 is %s\n", string6);



	//printing stuff out
	double ggg = 12341234.444333332;
	printf("use %%f -->ggg is %f\n",  ggg);
	printf("use %%g -->ggg is %g\n",  ggg);
	printf("use %%e -->ggg is %e\n",  ggg);

	//review math stuff here:https://www.tutorialspoint.com/c_standard_library/math_h.htm

	printf("On to Function Pointers!!!\n");
	printf("Doing stuff with ints (10 and 20) -> add\n");
	int xval = 20;
	int yval = 10;
	printf("added them == %d\n", doStuffWithInts(xval, yval, addTwoNums ) );
	printf("multiplied them == %d\n", doStuffWithInts(xval, yval, multiplyTwoNums ) );


	//fib stuff
	printf("fib 1....\n");
	clock_t start = clock();
	//printf("printing out the 44 fib number: %d\n", fibOne(44));
	clock_t end = clock();
	printf("Time for fib1 to get 44th number is: %ld\n", (long int)(end - start)/CLOCKS_PER_SEC);
	printf("fib 2....\n");
	start = clock();
	printf("printing out the 44 fib number: %d\n", fibTwo(44));
    end = clock();
	printf("Time for fib2 to get 44th number is: %ld\n", (long int)(end - start)/CLOCKS_PER_SEC);

	flex_array *arrayFlex1 = malloc(sizeof(flex_array) + 6 * sizeof(int));//int, array of length 6
	arrayFlex1->length = 6;
	printf("Before flex array, length 6, is: \n");
	displayArray(arrayFlex1->a, arrayFlex1->length);
	printf("Now reallocating memory to the flexible array...\n");
	arrayFlex1 = realloc(arrayFlex1, sizeof(flex_array) + 12 * sizeof(int));
	printf("after flex array, length 12, is: \n");
		arrayFlex1->length = 12;
	displayArray(arrayFlex1->a, arrayFlex1->length);

	printf("More on constant pointers and such\n");

	//pointer to const int
	const int tt;
	//error ---> tt = 5;
	const int ss = 6;
	//error ---> ss = 7;
	const int *ppp;//p is a pointer to a constant int
	ppp = &ss;
	printf("The value that ppp points to is %d\n", *ppp);
	printf("Ppp points to a constant so trying to change that constant is not allowed\n");
	//error ---> *p = 4;
	const int kk = 22;
	ppp = &kk;
	printf("ppp is not constant so we can change where it points to\n, p now points to %d\n", *ppp);

	//constant pointer to int
	int hhh = 88832;
	int *const qqq = &hhh;
	printf("qqq is a constant pointer to an int\nvalue of qqq is %d\n", *qqq);
	printf("I can change value qqq points to\n");
	hhh = 4444444;
	int fff = -987;
	printf("qqq now points to %d\n", *qqq);
	//error ---> qqq = &fff; //qqq is a CONST pointer

	//constant pointer to constant int
	const int *const ptr_1 = &kk;
	printf("ptr_1 is  a constant pointer to a constant int, val is %d\n", *ptr_1);

	const int *const *ptr_2 = &ptr_1;
	printf("IMPORTANT. TO PRINT THE VALUE THAT A POINTER TO A POINTER POINTS TO, USER **\n");
	printf("ptr_2 is a pointer to a constant pointer to a constant int, val ptr_2 = %d\n", **ptr_2);

	printf("lots and lots of pointer fun\n");
	printf("YO YO YO SO APPARENTLY YOU CAN USE %%p that is perent p to PRINT POINTER MEMORY LOCATIONS\n");
	int aaaa = 5;
	int *ptr_3 = &aaaa;
	int **ptr_4 = &ptr_3;
	int ***ptr_5 = &ptr_4;
	printf("aaaa is %d\n", aaaa);
	printf("Memory location of ptr_3 is %p\n", &ptr_3);
	printf("value ptr_3 points to is %d\n", *ptr_3);

	printf("Memory location of ptr_4 which points to ptr_3 is %p\n", &ptr_4);
	printf("value ptr_4 points to is %d\n", **ptr_4);

	printf("Memory location of ptr_5 which points to ptr_4 is %p\n", &ptr_5);
	printf("value ptr_5 points to is %d\n", ***ptr_5);
	return 0;
}
