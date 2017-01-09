#include <iostream>
#include <string>
#include <vector>
using namespace std;

int main (int argc, char* argv[]){
	vector <string> allLines;
	vector <string> allWords;
	while (true){
		string s;
		getline(cin, s);
		if (cin.fail()){
			break;
		}
		allLines.push_back(s);
	}
	//now we have all lines
	//low get all words

	for (int i = 0; i < allLines.size(); i++){
		while ( (string) allLines[i]){
			string tmp;
			cin >> tmp;
			allWords.push_back(tmp);
		}
	}
	return 0;
}
