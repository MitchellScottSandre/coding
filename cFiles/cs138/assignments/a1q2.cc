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
	getline(cin, s);
	istringstream ss(s);
	while (ss >> word){
		cout << word;
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
				string s;
				cin.ignore();
				getline(cin, s);
				if (s.length() > 0 ){
					//call the recursive function
					printRevOrder(n, s);
				} else {
					//just print it
					//printString(n,s);
					cout << endl;
				}
				break;
			case 'g':
				printIfContainsFNORD(n);
				break;
			default:
				cerr << "Error, command is illegal";
				break;
		}
	}
	return 0;
}
