#include <iostream>
#include <string>
#include <cassert>
using namespace std;

// You'll probably want to insert (and adapt) the code for queues & BSTs.

struct SBLnode {
};

void SBL_init (SBL& sbl) { } 
int SBL_size (const SBL& sbl){ } 
void SBL_arrive (SBL& sbl, string name) { } 
void SBL_leave (SBL& sbl){
	cerr << "Error, SBL is empty." << endl;
}
string SBL_first (const SBL& sbl) {
	cerr << "Error, SBL is empty." << endl;
} 
bool SBL_lookup (const SBL& sbl, string name){ } 
void SBL_printInArrivalOrder (const SBL& sbl) { } 
void SBL_printInLexicographicalOrder (const SBL& sbl) { }

int main (int argc, char* argv[]) {
}

