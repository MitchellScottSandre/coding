#include <iostream>
#include <string>
using namespace std;

struct Node {
	string data;
	Node * next;
};

typedef Node * list;

void revList(Node ** head){
	Node * prev = nullptr;
	Node * curr = *head;
	Node * next;
	while (curr != nullptr){
		next = curr ->next;
		curr -> next = prev;
		prev = curr;
		curr = next;
	}
	*head = prev;
}
