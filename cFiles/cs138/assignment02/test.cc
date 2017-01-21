#include <iostream>
using namespace std;

int myCeilFnctn(double a){
    if ((int) a == a){
        return a ;
    }
    return (a  + 1) / 1;
}

int main(int argc, char *argv[]){
	cout << myCeilFnctn(1 / 2.0) << endl;
	cout << myCeilFnctn(2 / 2.0) << endl;
	cout << myCeilFnctn(3 / 2.0) << endl;
	cout << myCeilFnctn(4 / 2.0) << endl;
	cout << myCeilFnctn(5 / 2.0) << endl;
	return 0;
}
