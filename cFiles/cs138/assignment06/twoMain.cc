#include <algorithm>
#include<iomanip>
#include <string>
#include <iostream>
#include <vector>
#include <cctype> // I added this
#include "a6p2.cc"
using namespace std;

int main(int argc, char * argv[]){
	SimpleHashTable s = SimpleHashTable(20);
	s.insert("hello");
	s.insert("world");
	s.insert("what is going on everyone");
	s.insert("aaaa");
	s.insert("zzzz");
	s.insert("g");
	s.insert("trump");
	s.insert("bible");
	s.insert("speed");
	s.insert("speed");
	s.insert("speed");
	s.insert("very long long long long long sentence");
	s.insert("apple");
	s.insert("cash me outside how bout dat");
	s.print();
	s.report();

	cout << endl;
	cout << endl;

	cout << "removing 'speed' " << endl;
	s.remove("speed");
	s.print();

	cout << "Case: LAST in list at index: removing 'hello' " << endl;
	s.remove("hello");
	s.print();

	cout << "Case: FIRSST in list at index: removing 'apple' " << endl;
	s.remove("apple");
	s.print();

	cout << "Case: MIDDLE in list at index: removing 'zzzz' " << endl;
	s.remove("zzzz");
	s.print();





	return 0;
}
