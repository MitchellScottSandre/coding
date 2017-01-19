#include <iostream>
#include <string>
#include <cassert>
using namespace std;

//typedef Node* Stack; you can replace all Node* with Stack

struct Node {
	string val;
	Node *next;
};

Node *initStack(){
	return nullptr;
}

bool isEmpty(Node *first){
	return nullptr == first;
}

Node *push (Node *first, string val){
	Node *p = new Node;
	p->val = val;
	p->next = first;
	return first;
}

string peek (Node *first){
	assert(!isEmpty(first));//ensure there is a first element
	return first->val;
}

Node *pop(Node *first){
	assert(!isEmpty(first));
	Node *p = first->next;
	delete first;
	return p;
}

Node *nuke(Node *first){
	while (nullptr != first){
			Node *p = first;
			first = first -> next;
			delete p;
	}
	return first; // or, return nullptr
}

Node *nuke2(Node *first){
	while (!isEmpty(first)){
		first = pop(first);
	}
	return first;
}

int main (int argc, char *argv[]){
	Node *s = initStack();
	s = push (s, "alice");
	s = push (s, "bob");
	s = push(s, "carol");
	cout << peek(s) << endl;
	s = pop(s);
	cout << peek(s) << endl;
	s = nuke(s);
}
