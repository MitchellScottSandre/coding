#include <string>
#include <iostream>
#include <vector>
#include <cassert>
using namespace std;

Struct Queue {
	vector <string> store;
	int first;//index of first
};

void initQueue(Queue & q){
	q.first = 0;
}

void enter(Queue & q, string val){
	q.store.push_back(val);
}

bool isEmpty(const Queue & q){
	return q.store.empty();
}

void leave(Queue & q){
	assert(!isEmpty(q));
	q.first++; //the prvious "first" slot is no longer accessible
}

string first (const Queue & q){
	assert(!isEmpty(q));
	return q.store.at(first);
}

int main(int argc, char * argv[]){
	 Queue q1;
	 enter (q1, "early");
	 enter (q1, "timely");
	 enter (q1, "late");
	 cout << first(q1) << endl;
	 leave (q1);
	 cout << first(q1) << endl;
	 nuke (q1);
	return 0;
}
