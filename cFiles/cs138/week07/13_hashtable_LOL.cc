//Open hashing with chaining
//a hash table, where each node is a linked list
// thus this is a vector with Node pointers
#include <iostream>
#include <string>
#include <vector>
using namespace std;
struct Node {
	string name;
	int snum;
	Node * next;
};

typedef vector<Node *> HashTable;

void initHT(HashTable & table, int K){
	table.resize(K);
}

//this is likely a terrible hash function
int myhash(int key, int numBuckets){
	return key % numBuckets;
}

void nukeHT(HashTable & table){
	for (int i = 0; i < (int) (table.size()); i++){
		Node * p = table[i];
		while (nullptr != p){
			Node * temp = p;
			p = p->next;
			delete temp;
		}
	}
	table.resize(0);
}

void insertHT(HashTable & table, string name, int snum){
	const int slot = myhash(snum, table.size());
	Node * newNode = new Node;
	newNode->name = name;
	newNode->snum = snum;
	newNode->next = table[slot];
	table[slot] = newNode;
}

bool lookupHT(const HashTable & table, int key){
	const int slot = myhash(key, table.size());
	Node * temp = table[slot];
	while (nullptr != temp) {
		if (temp->snum == key){
			return true;
		}
		temp = temp->next;
	}
	return false;
}

//this is not in any particular order, but all of the elements will be printed

void printHT(const HashTable & table){
	for (int i = 0; i < (int) (table.size()); i++){
		if (table[i] != nullptr){
			Node * p = table[i];
			while (nullptr != p){
				cout << i << " " << p->snum << " " << p->name << endl;
				p = p->next;
			}
		}
	}
}

//remove: FOR US TO DO // probably for an assignment

int main(int argc, char * argv[]){

	return 0;
}
