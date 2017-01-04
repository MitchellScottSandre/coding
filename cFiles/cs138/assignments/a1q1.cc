using namespace std;

#include <iostream>
#include <string>

int main(int argc, char* argv[]){
	int n;
	cin >> n;
	if (n > 0){
		string s, shorterS;
		cin.ignore();
		getline(cin, s);
		while ( s.length() > 0 ){
			if (s.length() < n){
				cout << s << endl;
			} else {
			    shorterS = s.substr(0, n);
				cout << shorterS << endl;
			}
			getline(cin, s);
		}
	} else {
		cerr << "Error, line length must be positive." << endl;
	}
	return 0;
}
