#include <string>
#include <iostream>
#include <cassert>
using namespace std;

struct Node {
	string val;
	Node * next;
};

struct stack_struct {
	int length;
	Node * top;
};

typedef stack_struct * stack;


void initStack(stack & s){
	stack_struct * ss = new stack_struct;
	Node * n = nullptr;
	ss->top = n;
	ss->length = 0;
	s = ss;
}

bool isEmpty(const stack s){
	return nullptr != s && s->length == 0;
}

void push(stack & s, string data){
	//assert(!isEmpty(s));
	Node * n = new Node;
	n->val = data;
	n->next = s->top;
	s->top = n;
	s->length ++;
}

void pop(stack & s){
	assert(!isEmpty(s));
	Node * deleteMe = s->top;
	s->top = s->top->next;
	delete deleteMe;
	s->length --;
}

string peep(const stack & s){
	assert(!isEmpty(s));
	return s->top->val;
}

void printStack(const stack & s){
	cout << "Top:" << endl;
	Node * temp = s->top;
	while(nullptr != temp){
		cout << temp->val << endl;
		temp = temp->next;
	}
	cout << "Bottom" << endl;
}

void nuke(stack & s){
	Node * temp = s->top;
	while (s->top != nullptr){
		delete temp;
		s->length--;
		s->top = s->top->next;
		temp = s->top;
	}
	delete s;
}

int main(int argc, char * argv[]){
	stack s;
	initStack(s);
	cout << "Empty stack. isEmpty == " << isEmpty(s) << endl;
	push(s, "aaa");
	push(s, "bbb");
	push(s, "ccc");
	push(s, "ddd");
	push(s, "eee");
	push(s, "fff");
	push(s, "ggg");
	printStack(s);
	cout << "popping 3 times" << endl;
	for (int i = 0; i < 3; i++){
		cout << peep(s) << endl;
		pop(s);
	}
	cout << "printing again" << endl;
	printStack(s);
	cout << "Size before nuke " << s->length << endl;
	nuke(s);
	cout << "Size after nuke " << s->length << endl;
	cout << "Empty stack. isEmpty == " << isEmpty(s) << endl;
	return 0;
}
