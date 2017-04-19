#include <iostream>
#include <vector>
#include <string>
#include <algorithm>
using namespace std;

struct Dog{
	string name;
	int age;
};

template <typename T>
bool isSorted(vector <T> data){
	for (auto c = data.begin(); c!= data.end() - 1; c++){
		if ( (*c) > *(c + 1)) {
			//cerr << "Not Sorted" << endl;
			//cout << (*c) << " > " << *(c + 1) << endl;
			return false;
		}
	}
	//cerr << "yes sorted" << endl;
	return true;
}


int main(int argc, char * argv[]){
	vector <Dog *> dogsList;
	Dog d0 = {"Max", 12};
	Dog * d1 = new Dog;
		d1->name = "Toby";
	Dog * d2 = new Dog;
		d2->name = "Rex";
	Dog * d3 = new Dog;
		d3->name = "Normie";

	dogsList.push_back(d1);
	dogsList.push_back(d2);
	dogsList.push_back(d3);

	for (vector<Dog*>::iterator vi = dogsList.begin(); vi != dogsList.end(); vi++){
		cout << (*vi)->name << endl;
	}

	//one way
	cout << "Method One:" << endl;
	for (auto s = dogsList.begin(); s != dogsList.end(); s++){
		cout << (*s)->name << endl;
	}

	//another way
	cout << "Method Two:" << endl;
	for (auto s : dogsList){
		cout << s->name << endl;
	}

	vector <int> numbers;
	for (int i = 0; i < 15; i++){
		numbers.push_back(i * 10);
	}

	for (auto c : numbers){
		cout << c << endl;
	}

	vector<string> wordsList;
	for (int i = 0; i < 6; i++){
		string word = "";
		word += char(i + 65);
		wordsList.push_back(word);
	}
	int factorial = 1;
	for (int i = 1; i <= 6; i++){
		factorial *= i;
	}
	cout << "Shouffling" << endl;
	random_shuffle(wordsList.begin(), wordsList.end());
	isSorted(wordsList);
	//sort(wordsList.begin(), wordsList.end());
	isSorted(wordsList);

	cout <<"Going to display every permutation of it" << endl;
	for (int i = 0; i < factorial; i++){
		next_permutation(wordsList.begin(), wordsList.end());
		for (int j = 0; j < wordsList.size(); j++){
			cout << wordsList[j] << ", " ;
		}
		cout << endl;
		if (isSorted(wordsList) == true){
			cout << "That permutation was sorted!" << endl;
		}
	}




	return 0;
}
