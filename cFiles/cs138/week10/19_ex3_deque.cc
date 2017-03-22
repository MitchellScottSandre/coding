#include <iostream>
#include <string>
#include <deque>
using namespace std;

void print (deque<string> d){
	cout << "Printing deque" << endl;
	for (int i = 0; i < d.size(); i++){
		cout << "d[" << i << "] = \"" << d[i] << "\"" << endl;
	}
	cout << endl;
}

int main(int argc, char * argv[]){
	deque<string> d;
	d.push_back("aaaa");
	d.push_back("bbbb");
	d.push_back("cccc");
	d.push_back("dddd");
	d.push_front("eeee");
	d.push_front("zeta");
	print(d);
	cout << d.front() << "   " << d.at(0) << endl;
}
