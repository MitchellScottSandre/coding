#include <iostream>
#include <cassert>
#include <stdlib.h>
#include <time.h>
#include <vector>
using namespace std;

/*
Selection Sort
1. save index of current element
2. go and find smallest element
3. swap them
*/
void selectionSort(vector <int> & a){
	int n = a.size();
	assert(n >= 0);
	for (int i = 0 ; i < n ; i ++){
		int min = i; //index of CURRENT element
		for (int j = i + 1; j < n ; j++){
			if (a[j] < a[min] ){
				min = j;//go and find the SMALLEST element
			}
		}
		int temp = a[i];//SWAP THEM
		a[i] = a[min];
		a[min] = temp;
	}
}

void mergeSort(int * a, int * temp, int n){
	if (n <= 1) return;
	mergeSort(a, temp, n/2);//left side
	mergeSort(a + n/2, temp, n - n/2);

	int i = 0, j = n/2; k = 0;
	while (i < n/2 && j < n){
		if (a[i] <= a[j]){
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

	//copy them over
	for (i = 0; i < n; i++){
		a[i] = temp[i];
	}
}

int swap(int a[], int x, int y){

}

int partition(int a[], int n){
	int m = 0;
	for (int i = 1; i < n; i++){
		if (a[i] < a[0]){
			m++;
			swap(a, m, i);
		}
	}
	swap(a,m,0);
	return m;
}

void quicksort(int a[], int n){
	if (n <= 1) return;
	int m = partition(a, n);
	quicksort(a, m);
	quicksort(a + m + 1, n - m - 1);
}

void generateRandomArray(vector<int> & data, int length){
	srand (time(NULL));
	for (int i = 0; i < length; i++){
		data.push_back(rand() % 50);
	}
}

void displayArray(const vector <int> & data){
	for (int i = 0; i < data.size(); i++){
		cout << data[i] << ", ";
	}
	cout << endl;
}


int main(int argc, char * argv[]){
	vector <int> data;
	int length = 20;
	generateRandomArray(data, length);
	cout << "BEFORE: " << endl;
	displayArray(data);
	selectionSort(data);
	cout << "AFTER: " << endl;
	displayArray(data);
	return 0;
}
