#include <iostream>
 #include <fstream> //???
#include <string>
#include <vector>
// #include <cassert>
using namespace std;

string SPACE = " ";

int myCeilFnctn(double a){
    if ((int) a == a){
        return a ;
    }
    return (a  + 1) / 1;
}

//===================================FORMATTING FUNCTIONS================================
vector< vector<string> > formatBothJustified(const vector< vector<string> > & allLinesAndWords_OriginalCopy, int n){
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
    	int extraSpaces = n - lengthOfAllWords;
    	for (int i = 0; i < numWords; i++){

    			thisLine.push_back(wordsOnLine[i]);
    			int x = myCeilFnctn( (double)extraSpaces / (double)numGaps);
    			for (int p = 0; p < x; p++){
    				thisLine.push_back(SPACE);
    	 		}
    			extraSpaces -= x;
    			numGaps--;
    	}
    	if (numWords == 1 && wordsOnLine[0].length() < n){//only 1 word on the line!!!, fill rest with spaces
    			for (int i = 0; i < n - wordsOnLine[0].length(); i++){
    				thisLine.push_back(SPACE);
    			}
    	}
        allLinesAndWords_new.push_back(thisLine);//add the line to the return array
    }

    return allLinesAndWords_new;
}

vector< vector<string> > formatRaggedRight(const vector< vector<string> > & allLinesAndWords_OriginalCopy, int n){
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

vector< vector<string> > formatRaggedLeft(const vector< vector<string> > & allLinesAndWords_OriginalCopy, int n){
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

vector< vector<string> > formatCenter(const vector< vector<string> > & allLinesAndWords_OriginalCopy, int n){
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
        int y = n - x; // number of extra spaces
        int z = myCeilFnctn( (double) y / 2.0);; // need to fix this!
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
        allLinesAndWords_new.push_back(thisLine);//add the line to the return array
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
                if (thisLine.size() > 0){
                    allLines.push_back(thisLine);
                }
            thisLine.clear();
            string shorterS = allWords[i].substr(0, n);
            thisLine.push_back(shorterS);
                if (thisLine.size() > 0){
                    allLines.push_back(thisLine);
                }
            thisLine.clear();
            lineLength = 0;
        } else {//greater than n, display this line, then must start a new line!
                if (thisLine.size() > 0){
                    allLines.push_back(thisLine);
                }
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

void print (const vector< vector<string> > & allLinesToPrint, bool forward){
    if (forward){
        for (int i = 0; i < allLinesToPrint.size(); i++){
            for (int j = 0; j < allLinesToPrint[i].size(); j++){
                cout << allLinesToPrint[i][j];
            }
            cout << endl;
        }
    } else {
        for (int i = allLinesToPrint.size() - 1; i > 0; i--){
            for (int j = 0; j < allLinesToPrint[i].size(); j++){
                cout << allLinesToPrint[i][j];
            }
            cout << endl;
        }
    }
}

void printLineK(const vector< vector<string> > & allLinesToPrint, bool forward, int k){
    if (k >= allLinesToPrint.size()){
        return;//NOT A VALID LINE INDEX
    }
    int index = k;
    if (forward == false){
        index = allLinesToPrint.size() - 1 - k;
    }
    //now, print that line
    for (int j = 0; j < allLinesToPrint[index].size(); j++){
        cout << allLinesToPrint[index][j];
    }
    cout << endl;

}

void printLinesWithS(const vector< vector<string> > & allLinesToPrint, bool forward, string word){
    int startIndex = 0;
    int direction = 1;//count up, forward
    if (forward == false){
        direction = -1; //count down
        startIndex = allLinesToPrint.size() - 1;
    }
    int counter = 0;
    int i = startIndex;
    while (counter != allLinesToPrint.size()){
        bool lineHasFnord = false;
        for (int j = 0; j < allLinesToPrint[i].size(); j++){//iterate through all words in that line
            //search for fnord
            size_t found = allLinesToPrint[i][j].find(word);
            if (found != -1){
                lineHasFnord = true;
                break;
            }
        }
        if (lineHasFnord == true){//print that entire line
            for (int j = 0; j < allLinesToPrint[i].size(); j++){
                cout << allLinesToPrint[i][j];
            }
            cout << endl;
        }
        i = i + direction;
        counter++;
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
    if (n < 1){
        cerr << "Error, line length must be positive." << endl; //end line??
        return 1;
    }
    vector<string> allWords;
	ifstream file (textFileName);
    if (!file){
        cerr << "Error, cannot open specified text file." << endl;
        return 1;
    }
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
        if (command == "k"){
            cin >> kCommandValue;
        } else if (command == "s"){
            cin >> sCommandValue;
        }

        if (command == "rr"){//         justification: ragged right (default)
            allLinesPrintMe = formatRaggedRight(allLinesOriginal, n);
        } else if (command == "rl"){//  justification: ragged left
            allLinesPrintMe = formatRaggedLeft(allLinesOriginal, n);
        } else if (command == "c"){//   justification: centered
            allLinesPrintMe = formatCenter(allLinesOriginal, n);
        } else if (command == "j"){//   justification: aligned left and right
            allLinesPrintMe = formatBothJustified(allLinesOriginal, n);
        } else if (command == "f"){//   print direction: forward
            printForward = true;
        } else if (command == "r"){//   print direction: reverse
            printForward = false;
        } else if (command == "p"){//    print command: print!
            print(allLinesPrintMe, printForward);
        } else if (command == "k"){//   print command: print the kth line
            printLineK(allLinesPrintMe, printForward, kCommandValue);
        } else if (command == "s"){//   print command: print only lines that contain the sCommandValue string
            printLinesWithS(allLinesPrintMe, printForward, sCommandValue);
        } else {
            cerr << "Error, command is illegal." << endl;
            return 1;
        }

    }

    return 0;
}
