//OLD STACK
#include <iostream>
#include <string>
#include <vector>
#include <cassert>
using namespace std;

typedef vector<string> Stack;

bool isEmpty(const Stack & s){
	return 0 == s.size();
}

void push(Stack & s, string e){
	s.push_back(e);
}

void pop(Stack & s){
	assert(!isEmpty(s));
	s.pop_back();
}

string top (const Stack & s){
	assert(!isEmpty(s));
	return s.back();
}

int main(int argc, char * argv[]){
	Stack s1;
	Stack * s2 = new Stack;
	push(s1, "aaa");
	push(s1, "bbb");
	push(s1, "ccc");
	push(s1, "ddd");
	push(s1, "eee");
	push(*s2, "one");
	push(*s2, "two");
	cout << "top of s1: " << top(s1) << endl;
	pop(s1);
	return 0;
}
