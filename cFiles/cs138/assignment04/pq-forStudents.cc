#include<iostream>
#include<string>
#include<cassert>
using namespace std;

// Implement these using a linked list, using your class notes
struct Qnode { };
struct Queue { }; 
void initQ (Queue& q) { }
bool isEmptyQ (const Queue& q) { }
int sizeQ (const Queue& q) { }
void enterQ (Queue& q, string val) { }
string firstQ (const Queue& q) { 
    cerr << "Error, queue is empty." << endl;
}
void leaveQ (Queue& q) {
    cerr << "Error, queue is empty." << endl;
}
void printQ (const Queue& q) { } 

// Now implement these yourself.
struct PQnode {
    // fill me in
};

// Free samples :-)
typedef PQnode* PQ; 

void initPQ (PQ& pq) { 
    pq = nullptr;
}

bool isEmptyPQ (const PQ& pq) {}
void enterPQ (PQ& pq, string val, int priority) {}
string firstPQ (const PQ& pq) {
    cerr << "Error, priority queue is empty." << endl;
}
void leavePQ (PQ& pq) {
    cerr << "Error, priority queue is empty." << endl;
}
int sizePQ (const PQ& pq) {}
int sizeByPriority (const PQ& pq, int priority) {}
int numPriorities (const PQ& pq) {}
