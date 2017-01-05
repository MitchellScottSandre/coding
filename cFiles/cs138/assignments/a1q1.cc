using namespace std;

#include <iostream>
#include <string>
#include <vector>

int main(int argc, char* argv[]){
	int n;
	cin >> n;
	if (n > 0){
		string s, shorterS;
		vector<string> allWords;
		cin.ignore();
		getline(cin, s);
		while ( cin.fail() == false ){
			allWords.push_back(s);
			getline(cin, s);
		}
		//now display
		for (int i = 0; i < allWords.size(); i++){
			cout << allWords[i] << endl;
		}
	} else {
		cerr << "Error, line length must be positive." << endl;
	}
	return 0;
}
