#include <string>
#include <fstream>
#include <iostream>
using namespace std;


int main(int argc, char * argv[]){
	ifstream inputFile ("inputTest.txt");
	while (!inputFile.eof()){
		string word;
		inputFile >> word;
		cout << word << endl;
	}

	ofstream outputFile("outputTest.txt");
	for (int i = 0; i < 20; i++){
		outputFile << i << endl;
	}
	return 0;
}
