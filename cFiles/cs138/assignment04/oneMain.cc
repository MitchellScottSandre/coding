#include<iostream>
#include<string>
#include<cassert>
using namespace std;

// Implement these using a linked list, using your class notes
struct Qnode {
	string val;
	Node * next;
};

struct Queue {
	Node * first;
	Node * last;
};

void initQ (Queue& q) {
	q.first = nullptr;
	q.last = nullptr;
}

bool isEmptyQ (const Queue& q) {
	return nullptr == q.first;
}

int sizeQ (const Queue& q) {
	int size = 0;
	Node * temp = q.first;
	while (nullptr != temp){
		size++;
		temp = temp->next;
	}
	return temp;
}

void enterQ (Queue& q, string val) {
	Node * p = new Node;
	p->val = val;
	if (nullptr == q.first){
		q.first = p;
	} else{
		q.last->next = p;
	}
	q.last = p;
	p->next = nullptr;
}

string firstQ (const Queue& q) {
    //cerr << "Error, queue is empty." << endl;
	assert(isEmptyQueue(q));
	return q.first->val;
}
void leaveQ (Queue& q) {
    //cerr << "Error, queue is empty." << endl;
	assert(isEmptyQueue(q));
	Node * p = q.first;
	q.first = q.first->next;
	if (nullptr == q.first){
		q.last = nullptr;
	}
	delete p;
}

void printQ (const Queue& q) {
	Node * temp = q.first;
	while (nullptr != temp){
		cout << temp->val << endl;
		temp = temp->next;
	}
}


struct PQnode {
    int priority;
	string val;
	PQnode * next;
};


typedef PQnode* PQ;

void initPQ (PQ& pq) {
    pq = nullptr;
}

bool isEmptyPQ (const PQ& pq) {
    return nullptr == pq;
}

void enterPQ (PQ& pq, string val, int priority) {

}

string firstPQ (const PQ& pq) {
    cerr << "Error, priority queue is empty." << endl;
}

void leavePQ (PQ& pq) {
    cerr << "Error, priority queue is empty." << endl;
}

int sizePQ (const PQ& pq) {

}

int sizeByPriority (const PQ& pq, int priority) {

}

int numPriorities (const PQ& pq) {

}
