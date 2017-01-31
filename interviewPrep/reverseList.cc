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
	return p;
}

string peek (Node *first){
	//assert(!isEmpty(first));//ensure there is a first element
	return first->val;
}

Node *pop(Node *first){
	assert(!isEmpty(first));
	Node *p = first->next;
	delete first;
	return p;
}

void displayList_topDown(Node * first){//prints from top to bottom
	Node * temp = first;
	while (temp != nullptr){
		cout << temp->val << endl;
		temp = temp->next;
	}
	delete temp;
}

Node * reverseList(Node * n){//pass it first node, originally
	Node * temp = n;
	Node * newFirst = nullptr;
	if (temp->next->next != nullptr){ //think of edge cases for short lists
		temp = temp->next;
		newFirst = reverseList(temp);
	}
	// else {
	// 	newFirst = temp->next;
	// }
	temp->next->next = temp;
	temp->next = nullptr;
	return newFirst;
}

int main (int argc, char * argv[]){
	Node * stack = initStack();
	stack = push(stack, "a");
	stack = push(stack, "b");
	stack = push(stack, "c");
	stack = push(stack, "d");
	cout << "Before" << endl;
	displayList_topDown(stack);
	Node *revStack = initStack();
	revStack = reverseList(stack);
	cout << "After" << endl;
	displayList_topDown(revStack);
	return 0;
}
