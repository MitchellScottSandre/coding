#include <iostream>
using namespace std;

struct Node{
    string val;
    Node* next;
};

void printList (Node* p) {
	while (nullptr != p){
		cout << p->val << endl;
		p = p->next;
	}
}
