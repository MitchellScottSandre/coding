#include <iostream>
 #include <fstream> //???
#include <string>
#include <vector>
// #include <cassert>
using namespace std;

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
    while (true){
		string word;
		file >> word;
		if (cin.fail()) {
			break;
		}
		allWords.push_back(word);
	}
    return 0;
}
