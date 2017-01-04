using namespace std;
#include <iostream>
#include <string>
#include <sstream>

void printString(int n, string s){
	string shorterS;
	if (s.length() < n){
		cout << s << endl;
	} else {
		shorterS = s.substr(0, n);//substring it from start to index n, as required in the assignment
		cout << shorterS << endl;
	}
}

void functionFromPartOne(int n){
		string s;
		cin.ignore();
		getline(cin, s);
		while ( s.length() > 0 ){
			printString(n,s);
			getline(cin, s);
		}
}

void printRevOrder(int n, string s){
	if (s.length() > 0 ){
		string newS;
		getline(cin, newS);
		printRevOrder(n, newS);
		printString(n, newS);
	}
}

void printIfContainsFNORD(int n){//contains fnord
	string s, word;
	cin.ignore();
	bool continueSearchingLine;
	getline(cin, s);
	while ( s.length() > 0 ){
		istringstream ss(s);
		continueSearchingLine = true;
		while (ss >> word && continueSearchingLine == true){//keeps going through the line string s and putting words into string word
			//search the word for fnord
			if (word.length() == 5 && word.compare("fnord") == 0){ // it equals the word
				continueSearchingLine = false;
				printString(n, s);
			} else if (word.length() > 5){
				size_t found = word.find("fnord");
				if (found != -1){//-1 is npos which is no position for string:find apparently
					continueSearchingLine = false;
					printString(n, s);
				}
			}
		}
		getline(cin, s);
	}

}

int main (int argc, char* argv[]){
	int n;
	char c;
	cin >> n >> c;//read in the variables
	if (n < 0){
		cerr << "Error, line length must be positive." << endl;
	} else {
		switch (c){
			case 'f': //do as you did in question one
				functionFromPartOne(n);
				break;
			case 'r': //print in reverse order
				{//need brackets if I am going to declare a variable in the case statement
					string s;
					cin.ignore();
					getline(cin, s);
					if (s.length() > 0 ){
						printRevOrder(n, s);//call the recursive function
					} else {
						cout << endl;
					}
					break;
				}
			case 'g':
				printIfContainsFNORD(n);
				break;
			default:
				cerr << "Error, command is illegal" << endl;
				break;
		}
	}
	return 0;
}
