#include <vector>
#include <iostream>
#include <string>
#include <cassert>
using namespace std;

typedef vector <string> Stack;//so, STACK IS A VECTOR

bool isEmpty(Stack s){
	return s.empty();
}

Stack push(Stack s, string val){
	s.push_back(val);
	return s;
}

Stack pop(Stack s){
	assert(!isEmpty(s));
	s.pop_back();
	return s;
}

Stack peek(Stack s){
	assert(!isEmpty(s));
	return s.back();
}
