#include <iostream>
using namespace std;

void mySwap(int &x, int &y){
	const int temp = x;
	x = y;
	y = temp;
}

template<typename T>
void anySwap(T &x, T & y){ // T is a place holder for some other type
	//type T must support operator =
	const T temp = x;
	x = y;
	y = temp;
}

template <typename T>
void printPair(const T &x, const T & y){
	//type T must support <<
	cout << "x = " << x << ", y = " << y << endl;
}

int main (int argc, char * argv[]){
	int j = 20;
	int k = 33;
	printPair(j,k);
	anySwap(j, k);
	printPair(j, k); // OK

	string s = "asdf";
	string t = "zxcv";
	printPair(s, t);
	anySwap(s, t);
	printPair(s, t);
}
