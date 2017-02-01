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
