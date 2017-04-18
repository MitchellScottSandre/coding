#include <string>
#include <vector>
#include <iostream>
using namespace std;

enum Status {EMPTY, ACTIVE, ZOMBIE};
//zombie means you have deleted it, but if you search for something at index x, which is zombie, so its not there, but u need you NEED to keep LOOKING
struct Node {
	string val;
	Node * next;
	Status status;
};

class hashTable{
public:
	void initHT(int k);
	bool lookup(int key);
	void printHT();
	void insertHT(string key);
private:
	int getNumBuckets();
	int hash(string key) ;
	int numActiveElements = 0;
	vector <Node *> table;
	const static int defaultSize = 100;

};

void hashTable::initHT(int k){
	table.resize(k);
	numActiveElements = 0;
	for (int i = 0; i < table.capacity(); i++){
		table[i]->status = EMPTY;
	}
}

int hashTable::getNumBuckets(){
	return table.capacity();
}

int hashTable::hash(string key) {
	int sum = 13;
	for (int i = 0; i < key.length(); i++){
		sum += key[i];
	}
	sum *= 101;
	return sum % getNumBuckets();
}

void hashTable::insertHT(string key){
	int slot = hash(key);
	while (table[slot]->status == ACTIVE){
		slot = (slot + 1) % getNumBuckets();
	}
	//now, it is EMPTY or it is ZOMBIE
	table[slot] -> val = key;
	table[slot] -> status = ACTIVE;
	numActiveElements ++;

	// if (numActiveElements * 100 / getNumBuckets() > 80){
	// 	//resize
	// 	vector <Node *> newTable;
	// 	newTable.resize(getNumBuckets() * 2);
	// 	for (int i = 0; i < newTable.capacity(); i++){
	// 		newTable[i]->status = EMPTY;
	// 		for (int j = 0; j < table.capacity(); j++){
	// 			int newSlot = table[j]
	// 		}
	// 	}
	// }
}

int main(int argc, char * argv[]){
	return 0;
}
