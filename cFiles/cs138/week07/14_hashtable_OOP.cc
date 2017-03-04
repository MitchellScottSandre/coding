#include <string>
#include <iostream>
#include <vector>
using namespace std;
//open hashing with chaining
struct Node {
	string name;
	int snum;
	Node * next;
};

class HashTable{
public:
	HashTable();
	HashTable(int K);
	virtual ~HashTable();//destructor
	void insert(string name, int snum);
	bool lookup(int snum) const;
	void remove(int snum);
	void print() const; //for debugging
	static const int DefaultSize;//global constant, tied to the class,
	//statis means one per universe, not one per instance
private:
	vector<Node *> table;
	int hash(int key) const;
};

const int HashTable::DefaultSize = 1000;

//the two constructors (different param lists)
//if we didn't use an explicit default size, then we would get a vector/table of size 0
HashTable::HashTable() : table (DefaultSize) {}
//the table (K / defaultSize) are initializers
HashTable::HashTable(int K) : table(K) {}

HashTable::~HashTable(){
	for (int i = 0; i < (int)table.size(); i++){
		Node * p = table[i];
		while(nullptr != p){
			Node * temp = p;
			p = p->next;
			delete temp;
		}
	}
}

//this method CANNOT be static
int HashTable::hash(int key) const{
	return key % (int) table.size();
}

bool HashTable::lookup(int key) const {
	const int slot = hash(key);
	Node * temp = table[slot];
	while (nullptr != temp){
		if (temp->snum == key){
			return true;
		}
		temp = temp -> next;
	}
	return false;
}

void HashTable::insert(string name, int snum){
	const int slot = hash(snum);
	Node * newNode = new Node;//overflow list is unordered
	newNode -> name = name;
	newNode -> snum = snum;
	newNode ->next = table[slot];
	table[slot] = newNode;
}

void HashTable::remove(int snum){
	//left to student
}

void HashTable::print() const{
	for (int i = 0; i < (int) table.size(); i++){
		Node * p = table[i];
		while(nullptr != p){
			cout << i << " " << p->snum << " " << p->snum << endl;
			p = p->next;
		}
	}
}

int main(int argc, char * argv[]){
	HashTable t (100);
	t.insert("Bob", 12345678);
	t.insert("Carole", 11111111);
	t.insert("Ted", 22222222);
	t.print();
	if (t.lookup(12345678)){
		cout << "Found Bob!" << endl;
	}

}
