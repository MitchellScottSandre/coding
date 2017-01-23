#include <cassert>
#include <iostream>
#include <string>
using namespace std;

struct Node{
    string val;
    Node* next;
};

Node* sortPair (Node* p1, Node* p2) {
	assert(nullptr != p1 && nullptr != p2);
	if (p1->val < p2->val){//trying opposite logic
		p1->next = p2;
        p2->next = nullptr;
        return p1;
	} else {
		p2->next = p1;
        p1->next = nullptr;
        return p2;
	}

}

// Node* sortPair (Node* p1, Node* p2) {
// 	assert(nullptr != p1 && nullptr != p2);
// 	Node *first = new Node;
// 	Node *second = new Node;
// 	if (p1->val < p2->val){//trying opposite logic
// 		first->val = p2->val;
// 		second->val = p1->val;
// 	} else {
// 		first->val = p1->val;
// 		second->val = p2->val;
// 	}
// 	first->next = second;
// 	second->next = nullptr;
// 	return first;
// }
