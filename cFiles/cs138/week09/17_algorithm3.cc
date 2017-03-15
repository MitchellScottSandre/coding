#include <iostream>
#include <string>
#include <vector>
#include <algorithm>
using namespace std;

bool isSorted (vector<string> v){
    for (int i=0; i<v.size()-1; i++) {
        if (v[i] > v[i+1]) {
            return false;
        }
	}
    return true;
}
void print (vector<string> v) {
  	cout << "\nPrinting a vector:" << endl;
	for (vector<string>::iterator i=v.begin();i!=v.end();i++){
        cout << " " << (*i) << endl;
	}
}


int main (int argc, char* argv[]) {
    vector<string> v;
    v.push_back("hello");
	v.push_back("there");
    v.push_back("world");
	v.push_back("how");
    v.push_back("are");
	v.push_back("we");
    v.push_back("doing");
	v.push_back("today");
    print(v);
    int nfactorial = 1;
    for (int i=1; i<=v.size(); i++) {
        nfactorial *= i;
    }
    for (int i=2; i<=nfactorial; i++) {
        next_permutation (v.begin(), v.end());
        if (isSorted(v)) {
            cout << "Found a sorted version at " << "permutation number " << i <<endl;
			print (v);
		}
	}

	return 0;
}
