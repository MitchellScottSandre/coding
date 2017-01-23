#include <iostream>
#include <string>
using namespace std;

void printReverseRecursive () {
	string word;
	if (cin >> word){
		printReverseRecursive();
		cout << word << endl;
	}
}
