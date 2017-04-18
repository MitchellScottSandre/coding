#include <string>
#include <iostream>
using namespace std;

int main(int argc, char * argv[]){
	//Arrays
	cout << "Enter length of array: " << endl;
	int n;
	cin >> n;
	int * A = new int[n];
	for (int i = 0; i < n; i++){
		A[i] = i;
	}
	for (int i = 0; i < n; i++){
		cout << A[i] << ",";
	}
	cout << endl;

	delete []A;
	return 0;
}
