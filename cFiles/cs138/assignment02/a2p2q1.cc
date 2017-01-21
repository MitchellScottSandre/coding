#include <iostream>
#include <string>
using namespace std;

struct Node{
    string val;
    Node* next;
};

Node *makeList () {
	Node *s = nullptr;
	string word;
	while (cin >> word){
		Node *p = new Node;
		p->val = word;
		p->next = s;
		s = p;
	}
	return s;
}
