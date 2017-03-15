#include <iostream>
#include <string>
#include <vector>
#include <algorithm>//for sort in main!
#include <cassert>
using namespace std;

template (typename T) // generic function definition
void printV(const vecotr<T> & v){
	for (typename vector<T>::const_iterator vi = v.begin(); vi != v.end(); vi++){
		cout << (*vi) << endl;
	}
}

void printA(string A[], int extent){
	for (int i = 0; i < extent; i++){
		cout << A[i] << endl;
	}
}

int main (int argc, char* argv[]) {
    vector<string> v;
    v.push_back("cat");
	v.push_back("zebra");
    v.push_back("alpaca");
	v.push_back("alligator");
    v.push_back("dog");
	v.push_back("sloth");
    v.push_back("monitor lizard");
    const int N = 7;
    assert (N == v.size());
    string A[N];
    for (int i=0; i<v.size(); i++) {
        A[i] = v.at(i);
    }
	printV(v);
	printA(A, N);
	sort(v.begin(), v.end()); // Use vector<string> iterator printV(v);
	sort (&A[0], A+N); // Or C ptrs; note "one beyond" printA(A, N);
}
