#include <iostream>
 #include <fstream> //???
#include <string>
#include <vector>
// #include <cassert>
using namespace std;

string SPACE = ".";

//===================================FORMATTING FUNCTIONS================================
vector< vector<string> > formatRaggedRight(vector< vector<string> > allLinesAndWords_OriginalCopy, int n){
    vector< vector<string> > allLinesAndWords_new;
    for (int i = 0; i < allLinesAndWords_OriginalCopy.size(); i++){
        vector<string> wordsOnLine = allLinesAndWords_OriginalCopy[i];
        vector<string> thisLine;
    	for (int i = 0; i < wordsOnLine.size() - 1; i++){
    		thisLine.push_back(wordsOnLine[i]);
    		thisLine.push_back(SPACE);
    	}
    	thisLine.push_back(wordsOnLine[wordsOnLine.size() - 1]);//last one
        allLinesAndWords_new.push_back(thisLine);//add the line to the return array
    }

    return allLinesAndWords_new;
}

vector< vector<string> > formatRaggedLeft(vector< vector<string> > allLinesAndWords_OriginalCopy, int n){
    vector< vector<string> > allLinesAndWords_new;
    for (int i = 0; i < allLinesAndWords_OriginalCopy.size(); i++){
        vector<string> wordsOnLine = allLinesAndWords_OriginalCopy[i];
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
        allLinesAndWords_new.push_back(thisLine);//add the line to all lines new
    }

    return allLinesAndWords_new;
}


//===================================END OF FORMATTING FUNCTIONS================================

vector< vector<string> > addMostWordsForAllLines(vector <string> allWords, int n){
    vector<vector <string> > allLines;
    vector<string> thisLine;
    int lineLength = 0, startIndex = 3;
    bool keepPrinting = true, printedThisLine, lastWord;
    lastWord = false;

    for (int i = 0; i < allWords.size(); i++){
        if (lineLength + allWords[i].length() < n){
            thisLine.push_back(allWords[i]);
            lineLength += allWords[i].length() + 1;//for the space!!!
        } else if (lineLength + allWords[i].length() == n){
            thisLine.push_back(allWords[i]);
            lineLength += allWords[i].length() ;//NO space!!!
        } else if (allWords[i].length() > n){
            allLines.push_back(thisLine);
            thisLine.clear();
            string shorterS = allWords[i].substr(0, n);
            thisLine.push_back(shorterS);
            allLines.push_back(thisLine);
            thisLine.clear();
            lineLength = 0;
        } else {//greater than n, display this line, then must start a new line!
            allLines.push_back(thisLine);
            thisLine.clear();
            lineLength = 0;
            i--;
        }
        if (i == allWords.size() - 1){//last line since it has last word, has not been printed
            allLines.push_back(thisLine);
        }

    }
    return allLines;
}

void print (vector< vector<string> > allLinesPrintMe_copy, bool forward){
    if (forward){
        for (int i = 0; i < allLinesPrintMe_copy.size(); i++){
            for (int j = 0; j < allLinesPrintMe_copy[i].size(); j++){
                cout << allLinesPrintMe_copy[i][j];
            }
            cout << endl;
        }
    } else {
        for (int i = allLinesPrintMe_copy.size() - 1; i > 0; i--){
            for (int j = 0; j < allLinesPrintMe_copy[i].size(); j++){
                cout << allLinesPrintMe_copy[i][j];
            }
            cout << endl;
        }
    }
}

int main(int argc, char *argv[]){
	//n textFileName seriesOfCommands
	//n is line length
	//textFileName is file in current directory that we are to be working with
	//continue to process commands until either end of file EOF or q for quit is entered
    int n;
    string textFileName;
    cin >> n >> textFileName;
    //now, process the text from the file
    vector<string> allWords;
	ifstream file (textFileName);
    string word;
    while (file >> word){//continue reading in while there are words left in the file
		allWords.push_back(word);
	}

    vector < vector<string> > allLinesOriginal, allLinesPrintMe;
    allLinesOriginal = addMostWordsForAllLines(allWords, n);


    //set defaults
    allLinesPrintMe = formatRaggedRight(allLinesOriginal, n);
    bool printForward = true;

    while (true){
        int kCommandValue;
        string command, sCommandValue;
        cin >> command;
        if (cin.fail() || command == "q") {
            break;
        }
        if (command[0] == 'k'){
            cin >> kCommandValue;
            cout << "k command value is " << kCommandValue << endl;
        } else if (command[0] == 's'){
            cin >> sCommandValue;
            cout << "S command value is " << sCommandValue << endl;
        }

        if (command == "rr"){//         justification: ragged right (default)
            allLinesPrintMe = formatRaggedRight(allLinesOriginal, n);
        } else if (command == "rl"){//  justification: ragged left
            allLinesPrintMe = formatRaggedLeft(allLinesOriginal, n);
        } else if (command == "c"){//   justification: centered

        } else if (command == "j"){//   justification: aligned left and right

        } else if (command == "f"){//   print direction: forward
            printForward = true;
        } else if (command == "r"){//   print direction: reverse
            printForward = false;
        } else if (command == "p"){//    print command: print!
            print(allLinesPrintMe, printForward);
        } else if (command == "k"){//   print command: print up to kth line

        } else if (command == "s"){//   print command: print only lines that contain the sCommandValue string

        }

    }

    return 0;
}
