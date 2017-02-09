#include <iostream>
using namespace std;

void swap (int & x, int & y){
	x = x + y;
	y = x - y;
	x = x - y;
}

int main (int argc, char * argv[]){
	int x = 10;
	int y = 5;
	cout << "Before: x is " << x << " and y is : " << y << endl;
	swap(x, y);
	cout << "After: x is " << x << " and y is : " << y << endl;
	return 0;
}
