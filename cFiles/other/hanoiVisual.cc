#include <iostream>
#include <cassert>
#include <stack>
using namespace std;



void displayTower(Stack tower, int n){

}


void hanoi(int N, int src, int dest, int temp){
	if (N > 0){
		hanoi(N - 1, src, temp, dest);
		cout << "Move from " << src << " to " << dest << endl;
		hanoi(N - 1, temp, dest, src);//switched src with temp, temp with dest, dest with src
	}
}

void initializeFirstStack(stack <int> & s, int n){
	int width = n;
	for (int i = 0; i < n; i++){
		s.push(width);
		width--;
	}
}

int main(int argc, char * argv[]){
	int size;
	cout << "Enter size of hanoi stack: ";
	cin >> size;
	assert(size > 0);
	stack <int> left_tower;
	stack <int> middle_tower;
	stack <int> right_tower;

	return 0;
}
