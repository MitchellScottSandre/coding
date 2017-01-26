
#include <cassert>
#include <string>
#include <iostream>
using namespace std;
struct Node {
	string val;
	Node * next;
};

struct Queue{
	Node * first;
	Node * last;
};

void initQueue(Queue & q){
	q.first = nullptr;
	q.last = nullptr;
}

bool isEmptyQueue(const Queue & q){
	return nullptr == q.first;
}

void enter(Queue & q, string val){
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

void enterQ(Queue q, string val){
	Node * p = new Node;
	p->val = val;
	p->next = nullptr;
	if (isEmptyQueue(q)){
		q.first = p;
	} else {
		q.last->next = p;
	}
	q.last = p;
}

string first(const Queue & q){
	assert(isEmptyQueue(q));
	return q.first->val;
}

void leave (Queue & q){
	assert(isEmptyQueue(q));//it's not empty..but what if it is the last element?
	Node * p = q.first;
	q.first = q.first->next;
	if (nullptr == q.first){
		q.last = nullptr;
	}
	delete p; //need p to exist so you can delete the first one!
}

void nuke(Queue & q){
	while (!isEmptyQueue(q)){
		leave(q);
	}
}

int main(int argc, char * argv[]){
	cout << "test" << endl;
	return 0;
}
