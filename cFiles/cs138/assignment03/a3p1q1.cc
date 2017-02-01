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
