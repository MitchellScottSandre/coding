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
void selectionSort(int a[], int n){
	assert(n >= 0);
	for (int i = 0 ; i < n ; i ++){
		int min = i; //index of CURRENT element
		for (int j = i + 1; j < n ; j++){
			if (a[j] < a[i] ){
				min = j;//go and find the SMALLEST element
			}
		}
		int temp = a[i];//SWAP THEM
		a[i] = a[min];
		a[min] = temp;
	}
}

void generateRandomArray(vector<int> & data){
	srand (time(NULL));
	for (int i = 0; i < data.size(); i++){
		data[i] = rand() % 50;
	}

}

int main(int argc, char * argv[]){



	return 0;
}
