#include <iostream>
#include <string>
#include <sstream>
#include <vector>
#include <math.h>
using namespace std;
const string SPACE = ".";

void printLinesWithFNORD(vector<vector<string> > allLines){
	for (int i = 0 ; i < allLines.size(); i++){//iterate through each line in allLines
		//now search that line for "fnord", if it has it, print that line
		bool lineHasFnord = false;
		for (int j = 0; j < allLines[i].size(); j++){//iterate through all words in that line
			//search for fnord
			size_t found = allLines[i][j].find("fnord");
			if (found != -1){
				lineHasFnord = true;
				break;
			}
		}
		if (lineHasFnord == true){//print that entire line
			for (int j = 0; j < allLines[i].size(); j++){
				cout << allLines[i][j];
			}
			cout << endl;
		}

	}
}

void printReverseOrder(vector<vector<string> > allLines){//iterate through each line in allLines
	for (int i = allLines.size() - 1; i >= 0; i--){
		for (int j = 0; j < allLines[i].size(); j++){
			cout << allLines[i][j];
		}
		cout << endl;
	}
}

void printNormalOrder(vector<vector<string> > allLines){//iterate through each line in allLines
	for (int i = 0; i < allLines.size(); i++){
		for (int j = 0; j < allLines[i].size(); j++){
			cout << allLines[i][j];
		}
		cout << endl;
	}
}




//this is called by displayLineBothJustified and just adds words to the allLines vector!!!
//then this calls the correct print order function
vector<string> formatLineBothJustified(vector<string> wordsOnLine, int n){
	vector<string> thisLine;
	int lengthOfAllWords = 0;
	int numWords= wordsOnLine.size();
	int numGaps = numWords - 1;
	for (int i = 0; i < numWords; i++){
		lengthOfAllWords += wordsOnLine[i].length();
	}
	int extraSpaces = n - lengthOfAllWords;
	for (int i = 0; i < numWords; i++){
		// if (wordsOnLine[0].length() > n){
		// 	string shorterS;
		// 	shorterS = wordsOnLine[0].substr(0, n);
		// 	thisLine.push_back(shorterS);
		// 	break;
		// } else
		{
			thisLine.push_back(wordsOnLine[i]);
			int x = ceil( (double)extraSpaces / (double)numGaps);
			for (int p = 0; p < x; p++){
				thisLine.push_back(SPACE);
	 		}
			extraSpaces -= x;
			numGaps--;
		}

	}
	if (numWords == 1 && wordsOnLine[0].length() < n){//only 1 word on the line!!!, fill rest with spaces
			for (int i = 0; i < n - wordsOnLine[0].length(); i++){
				thisLine.push_back(SPACE);
			}
	}
	return thisLine;
}

vector<string> formatLineRightJustified(vector<string> wordsOnLine, int n){
	vector<string> thisLine;
	int lengthOfAllWords = 0;
	int numWords= wordsOnLine.size();
	int numGaps = numWords - 1;
	for (int i = 0; i < numWords; i++){
		lengthOfAllWords += wordsOnLine[i].length();
	}
	int x = lengthOfAllWords + numGaps;//total number of chars, including spaces
	int y = n - x; // number spaces to add to front
	for (int i = 0; i < y; i++){
		thisLine.push_back(SPACE);//add spaces to front
	}
	for (int i = 0; i < numWords - 1; i++){
		thisLine.push_back(wordsOnLine[i]);
		thisLine.push_back(SPACE);
	}
	thisLine.push_back(wordsOnLine[numWords - 1]);//last word, NO SPACE!

	return thisLine;
}

vector<string> formatCenter(vector<string> wordsOnLine, int n){
	vector<string> thisLine;
	int lengthOfAllWords = 0;
	int numWords= wordsOnLine.size();
	int numGaps = numWords - 1;
	for (int i = 0; i < numWords; i++){
		lengthOfAllWords += wordsOnLine[i].length();
	}
	int x = lengthOfAllWords + numGaps;//total number of chars, including spaces
	int y = n - x; // number of extra spaces
	int z = ceil( (double) y / 2.0);
	int w = y - z;
	for (int i = 0; i < z; i++){
		thisLine.push_back(SPACE);//add spaces to front
	}
	for (int i = 0; i < numWords - 1; i++){
		thisLine.push_back(wordsOnLine[i]);
		thisLine.push_back(SPACE);
	}
	thisLine.push_back(wordsOnLine[numWords - 1]);//last word, no spaces

	//print out correct number of spaces now to make it center
	for (int i = 0; i < w; i++){
		thisLine.push_back(SPACE);//add spaces to back
	}

	return thisLine;
}

vector<string> formatLeftJustified(vector<string> wordsOnLine, int n){
	vector<string> thisLine;
	for (int i = 0; i < wordsOnLine.size() - 1; i++){
		thisLine.push_back(wordsOnLine[i]);
		thisLine.push_back(SPACE);
	}
	thisLine.push_back(wordsOnLine[wordsOnLine.size() - 1]);//last one
	return thisLine;
}

void findMostWordsPerLine_PassToFormatter_CreateAllLinesArray_thenCallDisplayFunction(int n, vector<string> allWords, string c2, string format){
	vector<vector <string> > allLines;
	vector<string> thisLine;
	int lineLength = 0, startIndex = 3;
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
				if (allWords[i].length() > n){//word is too long!!!
					string shorterS;
					shorterS = allWords[i].substr(0, n);
					thisLine.push_back(shorterS);
					allLines.push_back(thisLine);
					thisLine.clear();
					indexSave = i;
					break;
				}
				else if (lineLength + allWords[i].length() < n){
					lineLength += allWords[i].length() + 1;
					wordsOnLine.push_back(allWords[i]);
				} else if (lineLength + allWords[i].length() == n){
					lineLength = n;
					wordsOnLine.push_back(allWords[i]);

					if (format == "j"){
						thisLine = formatLineBothJustified(wordsOnLine, n);
					} else if (format == "rl"){
						thisLine = formatLineRightJustified(wordsOnLine, n);
					} else if (format == "c"){
						thisLine = formatCenter(wordsOnLine, n);
					} else {
						thisLine = formatLeftJustified(wordsOnLine, n);
					}

					allLines.push_back(thisLine);
					thisLine.clear();
					printedThisLine = true;
					indexSave = i;
					break;
				} else {
						//what if the word length is > n, we haven't added it yet
						if (allWords[i].length() > n){
						wordsOnLine.push_back(allWords[i]);
						i++;
						}
						if (format == "j"){
							thisLine = formatLineBothJustified(wordsOnLine, n);
						} else if (format == "rl"){
							thisLine = formatLineRightJustified(wordsOnLine, n);
						} else if (format == "c"){
							thisLine = formatCenter(wordsOnLine, n);
						} else {
							thisLine = formatLeftJustified(wordsOnLine, n);
						}

					//thisLine = displayLineBothJustified(wordsOnLine, n);
					allLines.push_back(thisLine);
					thisLine.clear();
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

						if (format == "j"){
							thisLine = formatLineBothJustified(wordsOnLine, n);//BOTH
						} else if (format == "rl"){
							thisLine = formatLineRightJustified(wordsOnLine, n);//RIGHT
						} else if (format == "c"){
							thisLine = formatCenter(wordsOnLine, n);
						} else {
							thisLine = formatLeftJustified(wordsOnLine, n);
						}

						allLines.push_back(thisLine);
						thisLine.clear();
						printedThisLine = true;
					}
					break;
				}
		}
		startIndex = indexSave + 1;
	}

	if (c2 == "f"){
		printNormalOrder(allLines);
	} else if (c2 == "r"){
		printReverseOrder(allLines);
	} else {
		printLinesWithFNORD(allLines);
	}

}




int main(int argc, char* argv[]){

	string line, word;
	int n;
	string c1, c2;
	vector<string> allWords;
	getline(cin, line);
	while ( cin.fail() == false ){//continue reading in all of the lines lines
		istringstream ss(line);
		while (ss >> word){
			allWords.push_back(word);
		}
		getline(cin, line);
	}
	cout << "There were " << allWords.size() << " words entered" << endl;
	//now do stuff with that information
	n = stoi(allWords[0]);
	c1 = allWords[1];
	c2 = allWords[2];
	int goodInput = 0;
	//============================ERROR HANDLING ===========================
	if (n <= 0){//bad input of n
		cerr << "Error, command is illegal." << endl;
		return 0;
	}
	if (c1 == "rr" || c1 == "j" || c1=="rl" || c1 =="c"){ //check c1 and c2
		goodInput++;
	}
	if (c2 == "f" || c2 =="r" || c2 =="g"){
		goodInput++;
	}
	if (goodInput != 2){
		cerr << "Error, command is illegal." << endl;
		return 0;
	}
	//=========================CALL THE CORECT FUNCTIONS ==================
	if (c1 == "rr"){
		//leftAligned(n, allWords, c2);
		findMostWordsPerLine_PassToFormatter_CreateAllLinesArray_thenCallDisplayFunction(n, allWords, c2, "rr");
	} else if (c1 == "j"){
		findMostWordsPerLine_PassToFormatter_CreateAllLinesArray_thenCallDisplayFunction(n, allWords, c2, "j");
	} else if (c1 == "rl"){
		findMostWordsPerLine_PassToFormatter_CreateAllLinesArray_thenCallDisplayFunction(n, allWords, c2, "rl");
	} else {
		findMostWordsPerLine_PassToFormatter_CreateAllLinesArray_thenCallDisplayFunction(n, allWords, c2, "c");
	}

	return 0;
}
