#include <iostream>
#include <string>
#include <vector>
 using namespace std;

 void printLine(int n, string line){
 	string shorterLine;
 	if (line.length() < n){
 		cout << line << endl;
 	} else {
 		shorterLine = line.substr(0, n);//substring it from start to index n, as required in the assignment
 		cout << shorterLine << endl;
 	}
 }

 int main (int argc, char* argv[]){
	 int n;
	 char command;
	 cin >> n >> command;
	 if (n < 0){
		 cerr << "Error, line length must be positive." << endl;
		 return 0;
	 }
	 if (command == 'f' || command == 'g' || command=='r'){
		 //all good
	 } else {
		 cerr << "Error, command is illegal." << endl;
		 return 0;
	 }

	 vector<string> allLines;
	 cin.ignore();
	 while (true){
		 string s;
		 getline(cin, s);
		 if (cin.fail()){
			 break;
		 }
		 allLines.push_back(s);
	 }

	 switch(command){
		 case 'f':
		 	for (int i = 0; i < allLines.size(); i++){
				printLine(n, allLines[i]);
			}
		 	break;
		 case 'g':
		 	for (int i = 0; i < allLines.size(); i++){
				size_t found = allLines[i].find("fnord");
				if (found != -1){//found "fnord" on that line
					printLine(n, allLines[i]);
				}
			}
		 	break;
		 case 'r':
		 	for (int i = allLines.size() - 1; i >= 0; i--){
				printLine(n, allLines[i]);
			}
		 	break;
	 }

	 return 0;
 }
