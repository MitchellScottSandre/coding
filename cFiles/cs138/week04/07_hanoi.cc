#include <iostream>
#include <cassert>
using namespace std;

//N + 1 takes twice as long as N, plus 1 more move
//so H of (N) = 2^N - 1  //number of moves it will take

void hanoi(int N, int src, int dest, int temp){
	if (N > 0){
		hanoi(N - 1, src, temp, dest);
		cout << "Move from " << src << " to " << dest << endl;
		hanoi(N - 1, temp, dest, src);//switched src with temp, temp with dest, dest with src
	}
}

int main(int argc, char * argv[]){
	int N;
	cout << "How many rings? ";
	cin >> N;
	assert(N > 0);
	hanoi(N, 1, 3, 2);

	return 0;
}
