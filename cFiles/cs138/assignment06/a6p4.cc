#include <algorithm>
#include <iomanip>
#include <string>
#include <iostream>
#include <fstream>
#include <vector>
#include <cassert>
using namespace std;

// Passive data node for hash tables.
struct Node {
    string word;
    Node* next;
};

class HashTable {
    public :
    	HashTable ();
    	HashTable (int K);
    	virtual ~HashTable();
    	void insert (string word);
    	void remove (string word);	    // You implement this!
    	bool lookup (string word) const;
    	void print () const;
    	void report () const;
    	static const int DefaultSize;
    protected :
	   int getTableSize() const;
    private :
	   vector<Node*> table;
	   // The "hash" function will be defined by each child class,
	   // each one using a different strategy.  But it needs to be
	   // abstract (aka "pure virtual") in the abstract parent class.
	   virtual int hash (string key) const = 0;

};

// Note that insert/lookup are completely defined here (and call hash) even
// tho hash is not defined here.  This is called the Template Method design
// pattern.  The hash function is private, meaning the children can't call
// it.  However, they can *define* it.  In fact, that's the only difference
// between the children classes: they each provide a difference
// implementation of hash!

const int HashTable::DefaultSize = 1000;
HashTable::HashTable() : table(DefaultSize) {}
HashTable::HashTable(int K) : table(K) {}

// The destructor has lots of garbage to clean up!  For each bucket with an
// overflow list, we delete the nodes.
HashTable::~HashTable(){
    for (int i=0; i<getTableSize(); i++) {
	Node* p = table[i];
	while (p!=NULL) {
	    Node* temp = p;
	    p = p->next;
	    delete temp;
	}
    }
}

// Simple accessor function that will be used by children classes to
// implement the hash function.  It's protected as children need to use it
// but not public as external clients do not need to know about it.
// This is the only information about the table that the children classes
// need to know, so we can make the table itself private!
int HashTable::getTableSize() const {
    return (int) table.size();
}

// Use open hashing with unsorted linked list for overflow.
void HashTable::insert(string key) {
    const int slot = hash(key);
    Node* newNode = new Node;
    newNode->word = key;
    newNode->next = table[slot];
    table[slot] = newNode;
}

bool HashTable::lookup (string key) const {
    const int slot = hash(key);
    Node* curNode = table[slot];
    while (curNode != NULL) {
		if (curNode->word == key) {
		    return true;
		}
		curNode = curNode -> next;
    }
    return false;
}

// You implement this! //TODO
void HashTable::remove(string key) {
    if (lookup(key) == false){
        return;
    }
    //word exists
    int slot = hash(key);
    Node * temp = table[slot];
    //Case 1: node is FIRST NODE in SLOT
    if (temp->word == key){
        table[slot] = temp->next;
        delete temp;
        return;
    } else {//Case 2: node is somewhere else in list
        while(temp->next->word != key){
            temp = temp->next;
        }
        //now, temp->next IS THE NODE TO DELETE
        Node * deleteMe = temp->next;
        temp->next = temp->next->next;
        delete deleteMe;
        return;
    }
}

// To help you debug, if you find it useful.
void HashTable::print() const {
    for (int i=0; i<getTableSize(); i++) {
		if (table[i] != NULL) {
		    Node* p = table[i];
		    while (p != NULL) {
			cout << i << "    " << p->word << endl;
			p = p->next;
		    }
		}
    }
}


// So we can tell how good your hash function is ...
// Do NOT override this function in the version you submit.  We will check!
void HashTable::report () const {
    // K is number of buckets
    const int K = getTableSize();
    // How many overflow elements in each bucket?
    vector<int> stats (K);
    int totalNumEntries = 0;
    int numNonZeros = 0;
    int maxOverflowSize = 0;
    for (int i=0; i<K ; i++) {
	if (table[i] != NULL) {
	    numNonZeros++;
	    int numEntriesInThisBucket = 0;
	    Node* p = table[i];
	    while (p != NULL) {
		p = p->next;
		numEntriesInThisBucket++;
	    }
	    totalNumEntries += numEntriesInThisBucket;
	    if (numEntriesInThisBucket > maxOverflowSize) {
		maxOverflowSize = numEntriesInThisBucket;
	    }
	    stats[i]=numEntriesInThisBucket;
	}
    }
    // This part added 28 March 2014:
    // Compute average # of probes required to establish that an element
    // is not present by summing the squares of the bucket list lengths,
    // then dividing by totalNumEntries
    int sumOfSquaresOfBucketListLengths = 0;
    for (int i=0; i<stats.size(); i++) {
	sumOfSquaresOfBucketListLengths += stats.at(i) * stats.at(i);
    }
    const double avgNumberOfProbes = (double) sumOfSquaresOfBucketListLengths
	/ (double) totalNumEntries;
    cout << "Average # of probes to establish an element is not present: "
	<< setprecision(3) << fixed << avgNumberOfProbes << endl;

    // If we sort, it's trivial to find the median.
    sort(stats.begin(), stats.end());
    const int numEmptyBuckets = K - numNonZeros;
    const int firstNonZeroBucketIndex = numEmptyBuckets;
    cout << "Number of entries in table: " << totalNumEntries << endl;
    cout << "Total # buckets: " << K << " of which " << numEmptyBuckets
	<< " (" << 100 * numEmptyBuckets / K << "%)" << " were empty." <<
	endl;
    // We want the avg and median # of elements, but ignoring the empty
    // buckets, on the grounds that if we're looking for a valid word, it
    // doesn't matter how many empty buckets there are, as we'll be hashing
    // to a non-empty bucket and traversing that list.
    // To compute the median, find the index that is half-way between
    // firstNonZeroBucketIndex and K.
    const int median = stats[firstNonZeroBucketIndex + numNonZeros/2];
    const int average = totalNumEntries / numNonZeros;
    cout << "Overflow list length:  Max = " << maxOverflowSize
	<< "  Median = " << median << "  Average = " << average <<  endl;
}


class SimpleHashTable : public HashTable{
    public:
        SimpleHashTable();
        SimpleHashTable(int K);
         ~SimpleHashTable();
    private:
         virtual int hash (string key) const;
};

SimpleHashTable::SimpleHashTable() : HashTable() {}
SimpleHashTable::SimpleHashTable(int K) : HashTable(K) {}
SimpleHashTable::~SimpleHashTable() {}

int SimpleHashTable::hash(string key) const {
    int numBuckets = getTableSize();
    int sum = 0;
    for (int i = 0; i < key.length(); i++){
        sum += key[i];
    }
    return sum % numBuckets;
}

class SmartHashTable : public HashTable{
    public:
        SmartHashTable();
        SmartHashTable(int K);
         ~SmartHashTable();
    private:
         virtual int hash (string key) const;
};

SmartHashTable::SmartHashTable() : HashTable() {}
SmartHashTable::SmartHashTable(int K) : HashTable(K) {}
SmartHashTable::~SmartHashTable() {}

int SmartHashTable::hash(string key) const {
    int index = 1;
    for(int i=0; i<key.size();i ++) {
       index ^= 173 ^ ((key[i]*91) << i);
    }
   return index%getTableSize();
   //http://www.echochamber.me/viewtopic.php?t=52779
}

// Ancillary function for powerset.  It adds a character onto the beginning
// of each string in a vector.
vector<string> addChar (const vector<string>& v, char c) {
    vector<string> ans;
    for (int i=0; i<(int)v.size() ; i++) {
		ans.push_back(c + v.at(i));
    }
    return ans;
}

// Note that I wrote this in scheme first!  Programming "shell game"
// recursion puzzles is easier with weak typing.
// This takes a string and returns the power(multi)set of its characters
// as a vector.  For example, powerset of "aab" would be the vector with
// elements (in no particular order): "aab" "ab" "aa" "a" "ab" "a" "b".
// Assume we don't care about eliminating duplicates for now.
vector<string> powerset (string s) {
    vector<string> ans;
    if (s.size() == 0) {
		ans.push_back("");
    } else {
		char c = s.at(0);
		string rest = s.substr(1);
		vector<string> psetRest = powerset (rest);
		ans = addChar (psetRest, c);
		ans.insert(ans.end(), psetRest.begin(), psetRest.end());
    }
    return ans;
}

int scrabbleValue(char letter){
	char c = letter;
	tolower(c);
	if (c =='q' || c=='z'){
		return 10;
	} else if (c =='j' || c == 'x'){
		return 8;
	} else if (c == 'k'){
		return 5;
	} else if (c == 'f' || c == 'h' || c == 'v' || c == 'w' || c == 'y'){
		return 4;
	} else if (c == 'b' || c == 'c' || c == 'm' || c == 'p'){
		return 3;
	} else if (c == 'd' || c == 'g'){
		return 2;
	} else if (c == 'e' || c == 'a' || c == 'i' || c == 'o' || c == 'n' || c == 'r' || c == 't' || c == 'l' || c == 's' || c == 'u'){
		return 1;
	}
	return 0;
}

int scrabbleValue(string word){
	int sum = 0;
	for (int i = 0; i < word.size(); i++){
		sum += scrabbleValue(word[i]);
	}
	return sum;
}


int main(int argc, char * argv[]){//argc is number of arguments, argv contains them
    if (argc != 2){
        cerr << "Error, no word list file name provided." << endl;
        return 0;
    }
    string wordlistFileName = argv[1];

	ifstream file (wordlistFileName);
    if (!file.is_open()){
		cerr << "Error, couldn’t open word list file." << endl;
		return 0;
	}
	SmartHashTable table = SmartHashTable(1000000);
	string word;
	while (file >> word){
		table.insert(word);
	}

	//cout << "done inserting words" << endl;

	while(true){
			cin >> word;
			if (cin.fail()){
				break;
			}

			vector<string> wordPowerSet = powerset(word);//now, find the maximum valued Scrabble word that can be made from those letters.
			int highestScrabbleScore = -1;
			string bestWord = "";
			for (int i = 0; i < wordPowerSet.size(); i++){			//need to do every possible combination, see if it is a word in the given list, and then output max scrabble value
				string s = wordPowerSet[i];
				sort (s.begin(), s.end());
				//cout << "sorted word # " << (i + 1) << " of powerset is " << s << endl;
				do {
					if (table.lookup(s) == true){
						//cout << "table contains: " << s << endl;
						int x = scrabbleValue(s);
						if (x > highestScrabbleScore){
							highestScrabbleScore = x;
							bestWord = s;
						}
					}
				} while (next_permutation(s.begin(), s.end()));
			}
			cout << word << ": ";
			if (highestScrabbleScore == -1){
				cout << "no matches" << endl;
			} else {
				cout << bestWord << " has score of " << highestScrabbleScore << endl;
			}

	}


	return 0;
}
//http://softwareengineering.stackexchange.com/questions/49550/which-hashing-algorithm-is-best-for-uniqueness-and-speed
