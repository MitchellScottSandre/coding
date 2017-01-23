#include <cassert>
#include <iostream>
#include <string>
using namespace std;

struct Node{
    string val;
    Node* next;
};


 Node* append (Node* p1, Node* p2) {
	 //add p2 on to the end of p1
	Node * temp = p1;
	while (temp->next != nullptr){
		temp = temp->next;
	}
    temp->next = p2;
    return p1;

}
