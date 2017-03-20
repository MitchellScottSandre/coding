#include <iostream>
using namespace std;

bool solver(int n){
	if ( ((n * n)- 2) % 119 == 0) return true;
	return false;
}

int main(int argc, char * argv[]){
	for (int i = 0; i < 119; i++){
		if (solver(i) == true){
			cout << i << endl;
		}
	}
	return 0;
}
