#include <algorithm>
#include <iomanip>
#include <string>
#include <iostream>
#include <vector>
#include <cctype> // I added this
#include <fstream>
#include <cstdlib>
#include <time.h>       /* time */
#include "a6p3.cc"
using namespace std;

//srand (time(NULL));

string randomWord(int length){
	string word = "";
	for (int i = 0; i < length; i++){
		int x = rand() % 26;
		word += x + 97;
	}
	return word;
}

int main(int argc, char * argv[]){
	SmartHashTable s = SmartHashTable(1000);

	// int numWords = 2000;
	// for (int i = 0; i < numWords; i++){
	// 	int wordLen = 2 + rand() %  10;
	// 	string word = randomWord(wordLen);
	// 	s.insert(word);
	// }

	

	s.print();
	s.report();

	return 0;
}
