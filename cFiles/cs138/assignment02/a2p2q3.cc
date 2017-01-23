#include <cassert>
#include <iostream>
#include <string>
using namespace std;

struct Node{
    string val;
    Node* next;
};

void printPairInOrder (Node* p1, Node* p2) {
	assert(nullptr != p1 && nullptr != p2);
	string first = p1->val, second = p2->val;
	if (p2->val < p1->val){
		first = p2->val;
		second = p1->val;
	}
	cout << first << endl;
	cout << second << endl;
}
