#include <iostream>
#include <cassert>
using namespace std;

typedef vector <int> Stack;

void displayTower(Stack tower, int n){
	
}


void hanoi(int N, int src, int dest, int temp){
	if (N > 0){
		hanoi(N - 1, src, temp, dest);
		cout << "Move from " << src << " to " << dest << endl;
		hanoi(N - 1, temp, dest, src);//switched src with temp, temp with dest, dest with src
	}
}

int main(int argc, char * argv[]){
	int size;
	cout << "Enter size of hanoi stack: ";
	cin >> size;
	assert(size > 0);
	return 0;
}
