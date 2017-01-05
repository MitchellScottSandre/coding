using namespace std;
#include <iostream>
#include <string>
#include <sstream>
#include <vector>

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
		for (int i = 1; i < allWords.size(); i++){//go through entire vector for each word
			if (allWords[i].length() > n){
				cout << endl;
				string shorterS;
				shorterS = allWords[i].substr(0, n);
				cout << shorterS;
				lineLength = 100;
				continue;
			}
			lineLength += allWords[i].length();
		
			if (lineLength > n ){
				cout  << endl;
				lineLength = 0;
				i--;//dont want to miss printing this word!!
			} else {
				cout << allWords[i] << " ";
				lineLength++;
			}

		}
	} else {
		cerr << "Error, command is illegal" << endl;
	}
	cout << endl;
	return 0;
}
