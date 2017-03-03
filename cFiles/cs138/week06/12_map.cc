// This creates a "word bag" for a text file.
#include <iostream>
#include <string>
#include <map>
using namespace std;
int main (int argc, char* argv[]) {
	 // record the number of times each word appears.
	 map<string, int> m;
	 string token;
	 while (cin >> token) { // This is where the magic happens
	 	m[token]++;
	 }
	 // After below line is executed, m["the"] is part of the
	 // map even if it didn't appear in the input stream
	 cout << "\"the\" occurred " << m["the"] << " times\n";

	 // So let's erase it if so ...
	 if (0 == m["the"]) {
	 	m.erase("the");
	 }
	 // map supports bi-directional iterators; so this will
	 // print pairs in "alphabetic" order of key
	 for (map<string,int>::const_iterator i=m.begin(); i!=m.end(); i++) {
	 	// For maps, "first" gets you the key ...
	 	// ... and "second" gets you the value
	 	cout << (*i).first << " " << (*i).second << endl;
	 }
}
