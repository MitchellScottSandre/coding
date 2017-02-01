#include <iostream>
#include <string>
#include <cassert>
using namespace std;

struct Node {
	string val;
	string otherStuff; //not going to touch this
	Node * next;
};

typedef Node * SortedList;

void initList(SortedList & first){
	first = nullptr;
}

bool isEmpty(const SortedList & first){
	return nullptr == first;
}

bool lookup(const SortedList & first, string val){
	Node * cur = first;
	while(nullptr != cur && cur->val < val){//why not equal to??
		cur = cur->next;
	}
	return (nullptr != cur) && (cur->val == val);
}

void insert(SortedList & first, string val){
	Node * newNode = new Node;
	newNode->val = val;


	if (nullptr == first){//Empty List
		newNode->next = nullptr;
		first = newNode;
	} else if (first->val > val){//insert before list
		newNode-> next = first;
		first = newNode;
	} else {//non empty list, insert in middle or insert at last
		Node * cur = first;
		while(nullptr != cur->next && val > cur->next->val){
			cur = cur->next;
		}
		newNode->next = cur->next;
		cur->next = newNode;
	}
}

void remove (SortedList & first, string val){
	assert(!isEmpty(first));
	//remove first element
	if (first->val == val){
		Node * temp = first;
		first = first->next;
		delete temp;
	} else {//middle pointer or last one?
		Node * cur = first;
		while (nullptr != cur && cur->next->val < val){
			cur = cur->next;
		}
		//if (nullptr == cur->next || val != )
		//TO DO
		Node * temp = cur->next;
		cur->next = cur->next->next;
		delete temp;
	}
}
