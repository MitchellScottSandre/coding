#include <iostream>
#include <string>
#include <cassert>
using namespace std;

struct Node {
	string val;
	Node* next;
};

typedef Node* Stack;

Stack initStack () {
	return nullptr;
}

bool isEmpty (Stack s) {
    return nullptr == s;
}

Stack push (Stack s, string val) {
	Node* newNode = new Node;
 	newNode->val = val;
	newNode->next = s;
	return newNode;
}

string peek (Stack s) {
	assert (!isEmpty(s));
	return s->val;
}

Stack pop (Stack s) {
	assert (!isEmpty(s));
	Node* newNode = s-> next;
	delete s;
	return newNode;
}

Stack nuke (Stack s) {
	while (!isEmpty(s)) {
			s = pop(s);
	}
	return s;
}
int main (int argc, char* argv[]) {
    Stack s = initStack();
    s = push (s, "alice");
    s = push (s, "bob");
    s = push (s, "carol");
    cout << peek(s) << endl;
    s = pop(s);
    cout << peek(s) << endl;
    s = nuke(s);
}
