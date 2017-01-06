#include <iostream>
#include <string>
#include <vector>
using namespace std;
int main(int argc, char* argv[]){
	int n;
	cin >> n;
	if (n > 0){
		string s, shorterS;
		vector<string> allLines;
		cin.ignore();
		while (true){
			getline(cin, s);
			if (cin.fail()){
				break;
			}
			allLines.push_back(s);
		}

		//now display
		for (int i = 0; i < allLines.size(); i++){
			if (allLines[i].length() <= n){
				cout << allLines[i] << endl;
			} else {
				shorterS = allLines[i].substr(0, n);
				cout << shorterS << endl;
			}
		}
	} else {
		cerr << "Error, line length must be positive." << endl;
	}
	return 0;
}
