#include <iostream>
using namespace std;
struct Node {
	struct val;
	Node * next;
};

typedef Node * list;

//first points to first element of remaining, unprocessed elements
//prev: first element of reversed list so far

void reverse(List & first){
	Node * prev = nullptr;
	while (nullptr !- first){
		//remember: first->next before resetting first
		Node * temp = first->next;
		first->next = prev;
		prev = first;
		first = temp;
	}
	first = prev;
	//what is the complexity of this?
	//let's check for border cases
}


int main(int argc, char * argv[]){


	return 0;
}
