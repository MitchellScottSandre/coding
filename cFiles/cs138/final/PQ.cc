#include <string>
#include <cassert>
#include <iostream>
using namespace std;

struct Node{
	string val;
	Node * next;
};

struct PQnode{
	int priority;
	Node * first;
	Node * last;
	PQnode * next;
};

typedef PQnode * PQ;

bool isPQEmpty(const PQ & list){
	return list == nullptr;
}

void initPQ(PQ & list, int x){
	PQnode * pp = new PQnode;
	Node * ff = nullptr;
	Node * ll = nullptr;
	pp -> first = ff;
	pp -> last = ll;
	pp -> next = nullptr;
	pp -> priority = x;
	list = pp;

}

void insertIntoCorrectPQNode(PQnode * a, string data){//regular insertion onto the end of the node
	if (a->first == nullptr){
		cerr << "a -> first is nullptr" << endl;
		Node * newNode = new Node;
		newNode -> val = data;
		newNode ->next = nullptr;
		a->first = newNode;
		a->last = newNode;
	} else {
		Node * temp;
		temp = a-> first;
		while (temp->next != nullptr){
			temp = temp->next;
		}
		Node * newNode = new Node;
		newNode -> val = data;
		newNode -> next = nullptr;
		temp->next = newNode;
		a->last = newNode;
	}

}

void enterPQ(PQ & list, string data, int p){

	//it is lower than first? it doesn't exist..it exists in middle or in end
	if (list -> priority > p){
		cerr << "p is new lowest, inserting at front" << endl;
		PQ newNode;
		initPQ(newNode, p);
		insertIntoCorrectPQNode(newNode, data);
		newNode -> next = list;
		list = newNode;
	} else if (list ->priority == p){
		cerr << "p is at the front, inserting there" << endl;
		insertIntoCorrectPQNode(list, data);
	} else {
		cerr << "searching for p" << endl;
		PQnode * temp;
		temp = list;
		while (temp -> next != nullptr && temp->next->priority < p){
			temp = temp -> next;
		}
		if (temp->next == nullptr){
			cerr << "temp next is nullptr, therefore priority is largest value (lowest), must go at end" << endl;
			PQ newNode;
			initPQ(newNode, p);
			insertIntoCorrectPQNode(newNode, data);
			temp->next = newNode;
		} else if (temp ->next->priority == p){
			cerr << "that priority node of p " << p << " already existed, inserting there" << endl;
			insertIntoCorrectPQNode(temp -> next, data);
		} else {
			cerr << "priority node didn't exist, need to create it BETWEEN two nodes" << endl;
			PQ newNode;
			initPQ(newNode, p);
			insertIntoCorrectPQNode(newNode, data);
			newNode -> next  = temp->next;
			temp ->next = newNode;
		}
	}
}

void leavePQ(PQ & list){
	//remove the FIRST one
	//if that PQ is now empty, delete it
	Node * temp = list->first;
	list->first = list->first->next;
	delete temp;
	if (list->first == nullptr){
		//need to delete it
		PQ tempPQ = list;
		list = list->next;
		delete tempPQ;
	}
}

string firstPQ(const PQ & list){
	return list->first->val;
}

void nukePQ(PQ & list){
	PQ tempPQ;
	while (list != nullptr){
		Node * tempN;
		while(list->first != nullptr){
			tempN = list->first;
			list->first = list->first->next;
			delete tempN;
		}
		tempPQ = list;
		list = list -> next;
		delete tempPQ;
	}
	list = nullptr;
}

void displayPQ(const PQ & list){
	if (isPQEmpty(list)){
		cerr << "ERROR: can't print: it is empty" << endl;
		return;
	}
	cout << "Displaying: " << endl;

	cout << "First is: " << firstPQ(list) << endl;

	PQnode * tempPQ = list;
	while (tempPQ != nullptr){
		cout << "Priority #" << tempPQ->priority << endl;
		Node * tempNode = tempPQ->first;
		while (tempNode != nullptr){
			cout << "    " << tempNode->val << endl;
			tempNode = tempNode -> next;
		}
		tempPQ = tempPQ -> next;
	}
}

int main(int argc, char * argv[]){
	PQ list;
	initPQ(list, 5);
	enterPQ(list, "aaa", 3);
	enterPQ(list, "bbb", 3);
	enterPQ(list, "ccc", 5);
	enterPQ(list, "ddd", 3);
	enterPQ(list, "eee", 5);
	enterPQ(list, "fff", 7);
	enterPQ(list, "ggg", 6);
	enterPQ(list, "hhh", 6);
	enterPQ(list, "iii", 6);
	enterPQ(list, "jjj", 1);
	enterPQ(list, "kkk", 2);

	displayPQ(list);
	leavePQ(list);
	displayPQ(list);
	nukePQ(list);
	displayPQ(list);
	return 0;
}
