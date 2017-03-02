#include<iostream>
#include<string>
#include<cassert>
using namespace std;

// Implement these using a linked list, using your class notes
struct Qnode {
	string val;
	Qnode * next;
};

struct Queue {
	Qnode * first;
	Qnode * last;
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
	Qnode * temp = q.first;
	while (nullptr != temp){
		size++;
		temp = temp->next;
	}
	return size;
}

void enterQ (Queue& q, string val) {
	Qnode * p = new Qnode;
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
	assert(!isEmptyQ(q));
	return q.first->val;
}
void leaveQ (Queue& q) {
    //cerr << "Error, queue is empty." << endl;
	assert(!isEmptyQ(q));
	Qnode * p = q.first;
	q.first = q.first->next;
	if (nullptr == q.first){
		q.last = nullptr;
	}
	delete p;
}

void printQ (const Queue& q) {
	Qnode * temp = q.first;
	while (nullptr != temp){
		cout << temp->val << endl;
		temp = temp->next;
	}
}


struct PQnode {
    int priority;
	Queue queue;
	PQnode * next;
};


typedef PQnode* PQ;

void initPQ (PQ& pq) {
    pq = new PQnode;
	Queue newQueue;
	initQ(newQueue);//sets q.first and q.last to nullptr;
	pq -> priority = -1;
	pq -> queue = newQueue;
	pq -> next = nullptr;

}

bool isEmptyPQ (const PQ & pq) {
	//return sizePQ(pq) == 0;
	//return nullptr == pq;
    return nullptr == pq || nullptr == pq->queue.first;
}

void enterPQ (PQ & pq, string val, int priority) {

	if (isEmptyPQ(pq) == true){//==========================================EMPTY (nullptr) BEFORE
		cerr << "Queue was empty" << endl;
		initPQ(pq);//makes pq, gives it a queue (empty), sets next to nullptr, sets priority to -1
	    pq -> priority = priority;
		enterQ(pq->queue, val);
		return;
	}
	//cerr << "Queue was not empty" << endl;

	//priority to insert is lower than first priority
	if (priority < pq->priority){//========================================insert BEFORE FIRST
		cerr << "Priority to insert lower than first priority" << endl;
		PQ newPQ;
		initPQ(newPQ);
		newPQ ->priority = priority;
		newPQ -> next = pq;
		enterQ(newPQ->queue, val);
		pq = newPQ;
		return;
	}

	//check if existing PQnode for that priority
		//yes, just add it
		//no, create a PQnode of that priority, then add it
	PQnode * temp = pq;
	if (temp->priority == priority){//====================================insert AT FIRST
		enterQ(temp->queue, val);
	} else {//============================================================insert AFTER FIRST
		while (temp->next != nullptr){
			if (temp->next->priority == priority){
				enterQ(temp->next->queue, val);
				return;
			} else if (temp->next->priority > priority){//================MIDDLE
				//insert new PQnode
				PQ newPQ;
				initPQ(newPQ);
				newPQ ->priority = priority;
				enterQ(newPQ->queue, val);
				newPQ->next = temp->next;
				temp->next = newPQ;
				return;
			}
			temp = temp->next;
		}
		//add it TO end
		//temp->next is NULLPTR
		//insert new PQnode
		PQ newPQ;
		initPQ(newPQ);
		newPQ ->priority = priority;
		enterQ(newPQ->queue, val);
		temp->next = newPQ;
		newPQ->next = nullptr;
		return;
	}

	//delete temp?
}

string firstPQ (const PQ& pq) {
	assert(!isEmptyPQ(pq));
    //cerr << "Error FIRTSPQ, priority queue is empty." << endl;

	return firstQ(pq->queue);
}

void leavePQ (PQ& pq) {
	assert(!isEmptyPQ(pq));
    //cerr << "Error LEAVEPQ, priority queue is empty." << endl;
	//sizeQ,leaveQ
	leaveQ(pq->queue);
	if (sizeQ(pq->queue) == 0){
		PQ temp = pq;
		pq = pq->next;
		delete temp;
	}
}

int sizePQ (const PQ& pq) {
	int size = 0;
	PQ temp = pq;
	while (temp != nullptr){
		size += sizeQ(temp->queue);
		temp = temp->next;
	}
	return size;
}

int sizeByPriority (const PQ& pq, int priority) {
	PQ temp = pq;
	while (temp != nullptr){
		if (temp->priority == priority){
			return sizeQ(temp->queue);
		} else if (temp->priority > priority){
			return 0;
		}
		temp = temp->next;
	}
	return 0;
}

int numPriorities (const PQ& pq) {
	int num = 0;
	PQ temp = pq;
	while (temp != nullptr){
		num++;
		temp = temp->next;
	}
	return num;
}

//TESTING
void printPQ(const PQ & pq){
	cout << "PRINTING" << endl;
	PQ temp = pq;
	while (temp != nullptr){
		cout << "FOR PRIORITY # " << temp->priority << endl;
		printQ(temp->queue);
		temp = temp->next;
	}
	delete temp; //??
}

int main (int argc, char * argv[]){
	PQ pq;
	initPQ(pq);
	cout << "PQ is empty : " << isEmptyPQ(pq) << endl;

	PQ pq2;
	pq2 = nullptr;
	cout << "PQ2 is empty : " << isEmptyPQ(pq2) << endl;
	// PQ pq = nullptr;//PQnode *
	// enterPQ(pq, "apples", 10);
	// enterPQ(pq, "butter", 4);
	// enterPQ(pq, "carrots", 4);
	// enterPQ(pq, "dinner", 4);
	// enterPQ(pq, "elf", 3);
	// enterPQ(pq, "flips", 7);
	// enterPQ(pq, "ghost", 7);
	// enterPQ(pq, "hello!", 12);
	// enterPQ(pq, "igloo!", 2);
	// enterPQ(pq, "joker!", 15);
	// enterPQ(pq, "king", 2);
	// printPQ(pq);
	// cout << "Sizeo of PQ is " << sizePQ(pq) << endl;
	//
	// cout << endl;
	//
	// cout << "First element is " << firstPQ(pq) << endl;
	// cout << "Calling leave function..." << endl;
	// leavePQ(pq);
	// cout << "First element is " << firstPQ(pq) << endl;
	// cout << "Sizeo of PQ is " << sizePQ(pq) << endl;
	//
	// cout << endl;
	//
	// cout << "Size by priority of 4 is " << sizeByPriority(pq, 4) << endl;
	// cout << "Size by priority of 3 is " << sizeByPriority(pq, 3) << endl;
	// cout << "Size by priority of 0 is " << sizeByPriority(pq, 0) << endl;
	// cout << "Size by priority of 55 is " << sizeByPriority(pq, 55) << endl;
	// cout << "Size by priority of -3 is " << sizeByPriority(pq, -3) << endl;
	//
	// cout << "Number of priorities is " << numPriorities(pq) << endl;
	// leavePQ(pq);
	// cout << "Number of priorities is now (just deleted first one)" << numPriorities(pq) << endl;


	return 0;
}
