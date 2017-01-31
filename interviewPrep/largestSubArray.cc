#include <iostream>
#include <climits>
using namespace std;

int sumOfLargestSubArray(int a[], int n){
	int max_so_far = INT_MIN;
	int max_ending_here = 0;
	for (int i = 0; i < n; i++){
		max_ending_here = max_ending_here + a[i]; // add value to max ending at that spot
		if (max_so_far < max_ending_here){
			max_so_far = max_ending_here;
		}
		if (max_ending_here < 0){
			max_ending_here = 0;
		}
	}
	return max_so_far;
}

int main (int argc, char * argv[]){
	int a[]= {1,-3,4,5,6,-100,2,3,90,-20,83,4,1};
	const int n = sizeof(a)/sizeof(a[0]);
	cout << sumOfLargestSubArray(a, n) << endl;
	return 0;
}
