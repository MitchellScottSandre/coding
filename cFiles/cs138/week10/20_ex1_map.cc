#include <iostream> // This is new, tho #include <string>
#include <cassert>
#include <map>
using namespace std;

// Examples adapted from Josuttis
int main (int argc, char* argv[]) {
    map<string,string> dict;
    dict["car"] = "voiture";
    dict["hello"] = "bonjour";
    dict["apple"] = "pomme";
    cout << "Printing simple dictionary" << endl;
    for (map<string,string>::iterator i=dict.begin();
            i!=dict.end();i++){
        cout << (*i).first << ":    " << (*i).second << endl;
	}

	// Examples adapted from Josuttis
	multimap<string,string> mdict;
	mdict.insert(make_pair ("car", "voiture"));
	mdict.insert(make_pair ("car", "auto"));
	mdict.insert(make_pair ("car", "wagon"));
	mdict.insert(make_pair ("hello", "bonjour"));
	mdict.insert(make_pair ("apple", "pomme"));
	cout << "\nPrinting all defs of \"car\"" << endl;
	for (multimap<string,string>::const_iterator i=mdict.lower_bound("car"); i!=mdict.upper_bound("car"); i++) {
    	cout << (*i).first << ":    " << (*i).second << endl;
	}
}
