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
		int lineLength = 0, startIndex = 1;
		bool keepPrinting = true, printedThisLine, lastWord;
		lastWord = false;
		while(keepPrinting == true){//print so it is left and right justified
			//find max number words whose length including spaces is less than n
			vector<string> wordsOnLine;
			lineLength = 0;
			int indexSave ;
			printedThisLine = false;

			for (int i = startIndex; i < allWords.size(); i++){//add words to word on line
					if (i == allWords.size() - 1){
						lastWord = true;
					}
					if (lineLength + allWords[i].length() < n){
						lineLength += allWords[i].length() + 1;
						wordsOnLine.push_back(allWords[i]);
					} else if (lineLength + allWords[i].length() == n){
						lineLength = n;
						wordsOnLine.push_back(allWords[i]);
						displayLineBothJustified(wordsOnLine, n);
						printedThisLine = true;
						indexSave = i;
						break;
					} else {
							//what if the word length is > n, we haven't added it yet
							if (allWords[i].length() > n){
							wordsOnLine.push_back(allWords[i]);
							i++;
							}
						displayLineBothJustified(wordsOnLine, n);
						printedThisLine = true;
						//we never added this line !!
						i--;
						indexSave = i;
						break;
					}
					indexSave = i;

					if (lastWord == true){
						keepPrinting = false; //finished all of the words
						if (printedThisLine == false){
							displayLineBothJustified(wordsOnLine, n);
							printedThisLine = true;
						}
						break;
					}
			}
			startIndex = indexSave + 1;
		}

	} else {
		cerr << "Error, command is illegal." << endl;
	}

	return 0;
}
