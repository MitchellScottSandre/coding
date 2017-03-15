#include <vector>
#include <iostream>
#include <string>
#include <list>
#include <algorithm>
using namespace std;

int main (int argc, char* argv[]) {
	list<string> a;
	a.push_back("Keon");
	a.push_back("Sittler");
	a.push_back("Gilmour");
	vector<string> b;
	b.push_back("Ramage");
	b.push_back("Vaive");
	b.push_back("Clark");
    // Insert just before last element
	cout << "Beflore insertion" << endl;
	for (list<string>::const_iterator it=a.begin(); it!=a.end(); it++) {
        cout << (*it) << endl;
	}
	cout << endl;
    a.insert (--a.end(), b.begin(), b.end());
	cout << "Insertiong all of b just before the end of a ... " << endl;

    cout << "After insertion" << endl;
    // Output: Keon Sittler Ramage Vaive Clark Gilmour
    for (list<string>::const_iterator it=a.begin(); it!=a.end(); it++) {
        cout << (*it) << endl;
	}

	//Erase up to but not including 3rd element
	a.erase(a.begin(), ++++a.begin());
	cout << "After erase" << endl;
	// Output: Ramage Vaive Clark Gilmour
    for (list<string>::const_iterator it=a.begin(); it!=a.end(); it++) {
        cout << (*it) << endl;
    }
	return 0;
}
