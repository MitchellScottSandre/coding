#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main(int argc, char * argv[]){
	vector <string> v;
	v.push_back("aaa");
	v.push_back("bbb");
	v.push_back("ccc");

	//Walking through a vector the old fashioned way
	cout << "Printing through it the old fashioned way" << endl;
	for (int i = 0; i < v.size(); i++){
		cout << "v[" << i << "] = \"" << v.at(i) << "\"" << endl;
	}

	//via an iterator
	cout << "Printing through it via an iterator" << endl;
	for (vector<string>::const_iterator ci = v.cbegin(); ci != v.cend(); ci++){
		cout << (*ci) << endl;
	}

	return 0;
}
