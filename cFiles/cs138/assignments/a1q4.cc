using namespace std;
#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <math.h>

void displayLineBothJustified(vector<string> wordsOnLine, int n){
	int lengthOfAllWords = 0;
	int numWords= wordsOnLine.size();
	int numGaps = numWords - 1;
	for (int i = 0; i < numWords; i++){
		lengthOfAllWords += wordsOnLine[i].length();
	}
	int extraSpaces = n - lengthOfAllWords;
	for (int i = 0; i < numWords; i++){
		cout << wordsOnLine[i] ;
		int x = ceil( (double)extraSpaces / (double)numGaps);
		for (int p = 0; p < x; p++){
		 	cout << ".";
 		}
		extraSpaces -= x;
		numGaps--;
	}
	if (numWords == 1){//only 1 word on the line!!!, fill rest with spaces
		for (int i = 0; i < n - wordsOnLine[0].length(); i++){
			cout << ".";
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
	while ( line.length() > 0 ){//continue reading in lines
		istringstream ss(line);
		while (ss >> word){
			allWords.push_back(word);
		}
		getline(cin, line);
	}
	n = stoi(allWords[0]);


	if (n > 0){
		int lineLength = 0, startIndex = 1;
		bool keepPrinting = true, printedThisLine, lastWord;

		while(keepPrinting == true){//print so it is left and right justified
			//find max number words whose length including spaces is less than n
			vector<string> wordsOnLine;
			lineLength = 0;
			int indexSave ;
			printedThisLine = false;
			lastWord = false;
			for (int i = startIndex; i < allWords.size(); i++){//add words to word on line
					if (i == allWords.size() - 1){
						lastWord = true;
					}
					indexSave = i;
					if (lineLength + allWords[i].length() < n){
						 lineLength += allWords[i].length() + 1;
						wordsOnLine.push_back(allWords[i]);
					} else if (lineLength + allWords[i].length() == n){
						lineLength = n;
						wordsOnLine.push_back(allWords[i]);
						displayLineBothJustified(wordsOnLine, n);
						printedThisLine = true;
						break;
					} else {
						displayLineBothJustified(wordsOnLine, n);
						printedThisLine = true;
						break;
					}

					if (lastWord == true){
						keepPrinting = false; //finished all of the words
						if (printedThisLine == false){
							displayLineBothJustified(wordsOnLine, n);
							printedThisLine = true;
						}
					}
			}
			startIndex = indexSave ;

		}

	} else {
		cerr << "Error, command is illegal" << endl;
	}

	return 0;
}
