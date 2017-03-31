#include <vector>
#include <iostream>
#include <string>
#include <algorithm>
using namespace std;

void displayVectorIterator(vector<string> data){

	for (vector<string>::const_iterator ci = data.begin(); ci != data.end(); ci++){
		cout << (*ci) << endl;
	}
}

template <typename T>
void displayVectorNormal(vector<T> data){
	cout << "Display Vector Normally" << endl;
	for (int i = 0; i < data.size(); i++){
		cout << data[i] << endl;
	}
}

template<typename T>
void sortVector(vector<T> & data){
	sort(data.begin(), data.end());
}

int main (int argc, char * argv[]){
	vector <string> v;
	for (int i = 25; i >= 0; i--){
		string s;
		for (int j = 0; j < 3; j++){
			s.insert(j,1,(char)(i + 65));
		}
		v.push_back(s);
	}
	displayVectorNormal(v);

	sortVector(v);
	displayVectorIterator(v);

	return 0;
}
