//OLD STACK
#include <iostream>
#include <string>
#include <vector>
#include <cassert>
using namespace std;

//Generic  stacks later:
class Stack {
	public :
		Stack();//constructor
		virtual ~Stack();
		bool isEmpty() const;//promising not to change the stack
		void push(string s);
		void pop();
		string top () const;

	private:
		vector<string> v; // this holds all of the information
		//the only way to access it is from methods WITHIN the class
};

Stack::Stack(){}
Stack::~Stack(){}

bool Stack::isEmpty() const {
	return 0 == v.size();
}

void Stack::push(string s){
	v.push_back(s);
}

void Stack::pop(){
	assert(!isEmpty());
	v.pop_back();
}

string Stack::top() const {
	assert(!isEmpty());
	return v.back();
}

//Slick new OO Stack!!

int main(int argc, char * argv[]){
	Stack s1;
	s1.push("aaa");
	s1.push("bbb");
	s1.push("ccc");
	Stack * s2 = new Stack;
	s2->push("one");
	s2->push("two");
	cout << s1.top() << endl;
	s1.pop();
	return 0;
}
