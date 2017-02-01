#include <iostream>
#include <string>
#include <cassert>
using namespace std;

struct Node {
    string val;
    Node* next;
    Node* prev;
};

struct Stew {
    Node* first;
    Node* last;
};

void initStew (Stew& q) {
    q.first = nullptr;
    q.last = nullptr;
}

bool isEmpty (const Stew& q) {
    return nullptr == q.first;
}

void addFront (Stew & q, string val) {
	Node * newFront = new Node;
	newFront->val = val;
	newFront->prev = nullptr;
	newFront->next = q.first;
	if (nullptr != q.first){//case: list was not empty
		q.first->prev = newFront;
	} else {//case: list WAS previously empty
		q.last = newFront;
	}
	q.first = newFront;
}


void leaveFront (Stew & q) {
	assert (!isEmpty(q));
	Node * p = q.first->next;
	delete q.first;
	if (nullptr != p){
		p->prev = nullptr;
	} else {
		q.last = nullptr;
	}
	q.first = p;
}

string peekFront (const Stew & q) {
	assert (!isEmpty(q));
	return q.first->val;
}

void print (const Stew & q, char direction) {//f forward, r reverse
    //what if the Stew is empty??
    if (direction == 'f'){
		Node * temp = q.first;
		while(temp != nullptr){
			cout << temp->val << endl;
			temp = temp->next;
		}
	} else if (direction == 'r'){
		Node * temp = q.last;
		while(temp != nullptr){
			cout << temp->val << endl;
			temp = temp->prev;
		}
	} else {
    	cerr << "Error, illegal direction: '" << direction << "'" << endl;
	}
}

int main(int argc, char * argv[]){
	Stew s;
	initStew(s);
	addFront(s, "aaa");
	addFront(s, "bbb");
	addFront(s, "ccc");
	addFront(s, "ddd");
	print(s, 'g');

	return 0;
}
