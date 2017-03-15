#include <iostream>
#include <string>
#include <vector>
using namespace std;
int main (int argc, char* argv[]) {
    vector<string> v;
    v.push_back("alpha");
    v.push_back("beta");
    v.push_back("gamma");
    v.push_back("delta");
	cout << "Forwards" << endl;
	for (vector<string>::const_iterator vi=v.begin(); vi!=v.end(); vi++) {
		cout << (*vi) << endl;
	}
	cout << "\nBackwards using a bidirectional iterator\n";
	for (vector<string>::iterator vi=v.end()-1; vi!=v.begin(); vi--) { // Stops one too early cout << (*vi) << endl;
		cout << (*vi) << endl;
	}
	cout << "\nBackwards using a reverse iterator\n";
	for (vector<string>::reverse_iterator rit=v.rbegin(); rit!=v.rend(); rit++) {
		cout << (*rit) << endl;
    }
    // Print pairs of elements; thus, need to double
    // increment the iterator each time thru;
    cout << "\nTwo per line w. a random access iterator\n";
    for (vector<string>::iterator vi = v.begin(); vi!=v.end(); vi++, vi++) {
		// vi[1] is legal as vi is a random access iterator
		cout << *vi << " " << vi[1] << endl;//vi[1] is the next one beside it!
	}
    return 0;
}
