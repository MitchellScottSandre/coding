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

void nuke (Stew & q) {
	while (nullptr != q.first){
			Node *p = q.first;
			q.first = q.first -> next;
			delete p;
	}
	q.first = nullptr;
	q.last = nullptr;
}
