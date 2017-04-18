#include <string>
#include <vector>
#include <iostream>
#include <cassert>
#include <time.h>
using namespace std;

struct Node{
	string val;
	Node * next;
};


typedef vector <Node *> hashTable;

void initHT(hashTable & table, int k){
	table.resize(k);
}

int getNumBuckets(const hashTable & table){
	return table.capacity();
}

int myHash(string key, int numBuckets){
	//sum up values, multiply by 137, mod size of table
	int sum = 0;
	for (int i = 0; i < key.length(); i++){
		sum += key.at(i);
	}
	sum *= 137;
	return sum % numBuckets;
}

void insertHT(hashTable & table, string key){
	const int bucket = myHash(key, getNumBuckets(table));
	//just need to make new  current node in that bucket point to new node
	Node * newNode = new Node;
	newNode->val = key;
	newNode->next = table.at(bucket);
	table.at(bucket) = newNode;
}

bool lookupHT(const hashTable & table, string key){
	const int bucket = myHash(key, getNumBuckets(table));
	Node * temp = new Node;
	temp = table.at(bucket);
	while (temp != nullptr){
		if (temp->val == key){
			return true;
		}
		temp = temp->next;
	}
	return false;

}

void printHT(const hashTable & table){
	cout << "PRINTING" << endl << endl;
	int longestChain = 0;
	int numEmptyBuckets = 0;
	for (int i = 0; i < getNumBuckets(table); i++){
		//print out each Linked List
		Node * temp = table.at(i);
		int thisChainLength = 0;
		if (temp == nullptr){
			numEmptyBuckets++;
		}
		while (temp != nullptr){
			thisChainLength++;
			cout << "Bucket #" << i << ": " << temp->val << endl;
			temp = temp->next;
		}
		if (thisChainLength > longestChain){
			longestChain = thisChainLength;
		}
	}

	cout << "REPORT" << endl << endl;
	cout << "Number Empty Buckets: " << numEmptyBuckets << endl;
	cout << "Percent empty: " << numEmptyBuckets * 100 / getNumBuckets(table) << "%" << endl;
	cout << "Longest Chain Length: " << longestChain << endl;


}


//nuke print lookup
string makeRandomWord(){
	int randomLength = rand() % 12 + 1;
	string word = "";
	for (int i = 0; i < randomLength; i++){
		char c = (rand() % 26) + 65;
		word += c;
	}
	return word;
}

int main(int argc, char * argv[]){
	srand (time(NULL));


	hashTable t;
	int length = 200;
	initHT(t, length);
	//make random words
	for (int i = 0; i < length * 2; i++){
		string word = makeRandomWord();
		insertHT(t, word);
	}
	printHT(t);

	return 0;
}
