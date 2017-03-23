// Preserving external reference integrity
#include <vector>
#include <deque>
#include <iostream>
using namespace std;
int main (int argc, char* argv[]) {
	cout << "\nWith a vector:" << endl;
	vector<int> v;
	v.push_back(4);
	v.push_back(3);
	v.push_back(37);
	v.push_back(15);
	int* p = &v.back();
	cout <<*p<<""<<v.at(3)<<" "<< p << " " << &v.at(3) << endl; // Must be same v.push_back(99); // Likely causes a reallocation
	cout <<*p<<""<<v.at(3)<<" "<< p << " " << &v.at(3) << endl; // Probably different

	cout << "\nWith a deque:" << endl;
	deque<int> d;
	d.push_back(4); d.push_back(3);
	d.push_back(37); d.push_back(15);
	p = &d.back();
	cout<<*p<<""<<d.at(3)<<" " << p << " " << &d.at(3) << endl; // Must be same d.resize(32767); // Probably causes realloc
	cout<<*p<<""<<d.at(3)<<" " << p << " " << &d.at(3) << endl;   // Must be same

return 0;
}
