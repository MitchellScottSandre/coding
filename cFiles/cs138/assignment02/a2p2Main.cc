#include <iostream>
#include <string>
#include <cassert>
using namespace std;

struct Node{
    string val;
    Node* next;
};

//question 1
Node *makeList () {
	Node *s = nullptr;
	string word;
	while (cin >> word){
		Node *p = new Node;
		p->val = word;
		p->next = s;
		s = p;
	}
	return s;
}

//question 2
void printList (Node* p) {
	while (nullptr != p){
		cout << p->val << endl;
		p = p->next;
	}
}

//question 3
void printPairInOrder (Node* p1, Node* p2) {
	assert(nullptr != p1 && nullptr != p2);
	string first = p1->val, second = p2->val;
	if (p2->val < p1->val){
		first = p2->val;
		second = p1->val;
	}
	cout << first << endl;
	cout << second << endl;
}


//question 4
Node* sortPair (Node* p1, Node* p2) {
	assert(nullptr != p1 && nullptr != p2);
	Node *first = new Node;
	Node *second = new Node;
	if (p1->val < p2->val){//p1 is less than p2
		first->val = p2->val;
		second->val = p1->val;
	} else {
		first->val = p1->val;
		second->val = p2->val;
	}
	first->next = second;
	second->next = nullptr;
	return first;
}

//question 6
Node* append (Node* p1, Node* p2) {
    //add p2 on to the end of p1
   // Node *temp = p1;
   // while (temp->next != nullptr){
   //     temp = temp->next;
   // }
   // temp->next = p2;
   return p1;
}

int main(int argc, char *argv[]){
	// cout << "Question 1: Making list" << endl;
	// Node *head = makeList();
    //
	// cout <<"Question 2: Printing list" << endl;
	// printList(head);
    //
	// cout << "Question 3: Print pair in order" << endl;
	// cout << "before, first val " << head->val << " and second val " << head->next->val << endl;
	// printPairInOrder(head, head->next);
    //
	// cout << "Question 4: Sorting Pair" << endl;
	// cout << "Sorting: head with val " << head->val << " and next with val " << head->next->val << endl;
	// Node * sorted = sortPair(head, head->next);
	// cout << "first is " << sorted->val << endl;
	// cout << "second is " << sorted->next->val << endl;

    cout << "Question 6: Append" << endl;
    cout << "Making first list" << endl;
    Node *firstList = makeList();
    //Node *secondList = makeList();
    cout << "Making second list" << endl;

    Node *secondList = new Node;
    Node *p3 = new Node;
    secondList->val = "four";
    p3 ->val = "five";
    secondList->next = p3;
    p3->next = nullptr;
    Node *totalList = append(firstList, secondList);
    printList(totalList);

	return 0;
}
