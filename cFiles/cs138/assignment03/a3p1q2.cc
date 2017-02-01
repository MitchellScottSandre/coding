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

void addBack (Stew & q, string val) {
    Node * newBack = new Node;
	newBack->val = val;
	newBack->prev = q.last;
	newBack->next = nullptr;
    if (nullptr != q.last){//list is NOT empty
        q.last->next = newBack;
    } else {//list IS currently empty
        q.first = newBack;
    }
    q.last = newBack;
}

void leaveBack (Stew & q) {
	assert (!isEmpty(q));
	Node * p = q.last->prev;
	delete q.last;
	if (nullptr != p){
		p->next = nullptr;
	} else {
		q.first = nullptr;
	}
	q.last = p;
}

string peekBack (const Stew & q) {
	assert (!isEmpty(q));
	return q.last->val;
}
