#include <iostream>
#include <string>
#include <cassert>
using namespace std;

void swap3(int& x, int& y){
	int temp = x;
	x = y;
	y = temp;
}

int main(int argc, char *argv[]){
	int x = 10;
	int y = 20;
	swap3(x, y);
	cout << "x is " << x << " and y is " << y << endl;
	return 0;
}
