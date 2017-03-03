// EMPTY == never used; ACTIVE == in use; ZOMBIE == deleted
enum Status {EMPTY, ACTIVE, ZOMBIE};
const int NOT_FOUND = -1;

struct Node {
	string name;
	int snum;
	Status status;
};

typedef vector<Node> HashTable;

//numActiveElements should really be part of the table variable
int numActiveElements;

// All table entries allocated at once
void initHT (HashTable& table, int K) {
	table.resize(K);
    numActiveElements = 0;
    for (int i=0; i<K; i++) {
    	table[i].status = EMPTY;
    }
}

// While this is a legal hash function, its "spread"
// is likely to be pretty awful
// We assume key >= 0 and numBuckets > 0
int myhash (int key, int numBuckets) {
    return key % numBuckets;
}

void insertHT (HashTable& table, string name, int snum){
    cerr << "Insert: " << name << " " << snum << endl;
    const int numBuckets = table.size();
    // Real world: If full (or approaching full), we'd create
    // a new, larger vector and re-insert the existing
    // non-zombie elements one-by-one into the new vector
    assert (numActiveElements < numBuckets);
    int slot = myhash (snum, numBuckets);
    // Stop if you find a zombie slot or a never-used slot
    while (ZOMBIE != table[slot].status && EMPTY != table[slot].status) {
    	slot = (slot + 1) % numBuckets;
    }
    table[slot].snum = snum;
    table[slot].name = name;
    table[slot].status = ACTIVE;
    numActiveElements++;
}

// Return NOT_FOUND (i.e., -1) if not found
int lookupIndexHT (const HashTable & table, int snum) {
    cerr << "Looking up index: " << snum << endl;
    const int numBuckets = table.size();
    int slot = myhash (snum, numBuckets);
    for (int i = 0; i<numBuckets; i++) {
    	if (EMPTY == table[slot].status) {
       		// If we find an EMPTY slot, the key's not there
 			return NOT_FOUND;
 		} else if (ACTIVE == table[slot].status && snum == table[slot].snum) {
 			return slot; // Found it!
 		}
 		// else it's a ZOMBIE or (ACTIVE and not a match),
 		// so keep going
 		slot = (slot + 1) % numBuckets;
 	}
 	// We made it around the horn but didn't find the element
 	return NOT_FOUND; // == -1
}

bool hasHT (const HashTable& table, int snum) {
	return NOT_FOUND != lookupIndexHT(table, snum);
}

string lookupHT (const HashTable& table, int snum){
    cerr << "Lookup: " << snum << endl;
 	const int index = lookupIndexHT (table, snum);
 	if (index == NOT_FOUND) {
 		return ""; // lame
 	} else {
 		return table[index].name;
 	}
}

// Simple! lookupIndexHT does the real work here!
void removeHT (HashTable & table, int snum) {
    cerr << "Remove: " << snum << endl;
 	const int index = lookupIndexHT (table, snum);
 	assert (NOT_FOUND != index);
 	table[index].status = ZOMBIE;
 	numActiveElements--;
}

// Print to stdout, not stderr; OUTPUT IS NOT SORTED!!
void printHT (const HashTable & table) {
    cout << "Printing table" << endl;
 	for (int i=0; i<(int)table.size(); i++) {
 		if (ACTIVE == table[i].status) {
 			cout << " " << i << " " << table[i].snum << " " << table[i].name << endl;
 		}
 	}
}
