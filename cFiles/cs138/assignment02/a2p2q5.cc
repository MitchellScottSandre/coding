#include <cassert>
#include <iostream>
#include <string>
using namespace std;

struct Node{
    string val;
    Node* next;
};

Node* makePairList (string s1, string s2) {
	Node *first = new Node;
	Node *second = new Node;
	if (s1 < s2){
        first->val = s2;
        second->val = s1;
    } else{
        first->val = s1;
        second->val = s2;
    }
    first->next = second;
    second->next = nullptr;
    return first;
}
