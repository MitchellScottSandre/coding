#include <iostream>
#include <string>
using namespace std;

template <typename T>
struct Node{
	T val;
	Node * next;
};

template <typename T>
class Stack {
public:
	Stack();
	virtual ~Stack();
	void push (T val);
	void pop();
	T top();
	bool isEmpty();
	void print();
private:
	Node<T> * first;
};

template <typename T>
Stack<T>::Stack() : first (nullptr) {}

template <typename T>
Stack<T>::~Stack(){
	while(nullptr != first){
		Node<T> * p = first;
		first = first->next;
		delete p;
	}
}

template<typename T>
void Stack<T>::push(T val){
	Node<T> * p = new Node<T>;
	p->val = val;
	p->next = first;
	first = p;
}

template <typename T>
bool Stack<T>::isEmpty(){
	return first==nullptr;
}

template <typename T>
void Stack<T>::pop(){
	assert(!isEmpty());
	Node<T> * p = first;
	first = first -> next;
	delete p;
}


template <typename T>
T Stack<T>::top{
	assert(!isEmpty());
	return first->val;
}

template<typename T>
void Stack<T>::print(){
	Node<T> * p = first;
	while (p != nullptr){
		cout << p -> val;
		p = p -> next;
	}
}

int main(int argc, char* argv[]){
	Stack<string> s1;
	s1.push("alice");
	s1.push("bob");
	s1.push("carol");
	s1.print();
	Stack<int> *ps2 = new Stack<int>;
	ps2->push (15);
	(*ps2).push(37);
	ps2->print();
	delete ps2;
}
