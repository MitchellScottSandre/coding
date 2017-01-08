#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <math.h>
using namespace std;

void displayLineBothJustified(vector<string> wordsOnLine, int n){
	int lengthOfAllWords = 0;
	int numWords= wordsOnLine.size();
	int numGaps = numWords - 1;
	for (int i = 0; i < numWords; i++){
		lengthOfAllWords += wordsOnLine[i].length();
	}
	int extraSpaces = n - lengthOfAllWords;
	for (int i = 0; i < numWords; i++){
		if (wordsOnLine[0].length() > n){
			string shorterS;
			shorterS = wordsOnLine[0].substr(0, n);
			cout << shorterS;
			break;
		} else {
			cout << wordsOnLine[i] ;
			int x = ceil( (double)extraSpaces / (double)numGaps);
			for (int p = 0; p < x; p++){
			 	cout << " ";
	 		}
			extraSpaces -= x;
			numGaps--;
		}

	}
	if (numWords == 1 && wordsOnLine[0].length() < n){//only 1 word on the line!!!, fill rest with spaces
			for (int i = 0; i < n - wordsOnLine[0].length(); i++){
				cout << " ";
			}
	}
	cout << endl;
	//what if only one word on the line? fill rest with spaces
}

int main (int argc, char* argv[]){
	//what if n is negative
	string line, word;
	int n;
	vector<string> allWords;

	//cin.ignore();
	getline(cin, line);
	while ( cin.fail() == false ){//continue reading in lines
		istringstream ss(line);
		while (ss >> word){
			allWords.push_back(word);
		}
		getline(cin, line);
	}
	n = stoi(allWords[0]);

	if (n > 0){
		int lineLength = 0;
		vector <string> thisLine;

		for (int i = 1; i < allWords.size(); i++){
			if (lineLength + allWords[i].length() < n){
				thisLine.push_back(allWords[i]);
				lineLength += allWords[i].length() + 1;//for the space!!!
			} else if (lineLength + allWords[i].length() == n){
				thisLine.push_back(allWords[i]);
				lineLength += allWords[i].length() ;//NO space!!!
			} else if (allWords[i].length() > n){
				displayLineBothJustified(thisLine, n);
				thisLine.clear();
				string shorterS = allWords[i].substr(0, n);
				thisLine.push_back(shorterS);
				displayLineBothJustified(thisLine, n);
				thisLine.clear();
				lineLength = 0;
			} else {//greater than n, display this line, then must start a new line!
				displayLineBothJustified(thisLine, n);
				thisLine.clear();
				lineLength = 0;
				i--;
			}
			if (i == allWords.size() - 1){//last line since it has last word, has not been printed
				displayLineBothJustified(thisLine, n);
			}

		}

	} else {
		cerr << "Error, command is illegal." << endl;
	}

	return 0;
}
