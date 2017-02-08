#include <iostream>
#include <string>
#include <cassert>
using namespace std;

struct NodeChunk{
    string* val;
    NodeChunk* next;
};

struct Stack{
    int chunkSize;
    int topElt;
    NodeChunk* firstChunk;
};


NodeChunk* createNewNodeChunk (int chunkSize) {
	if (chunkSize <= 0){
		cerr << "Error, chunk size must be positive." << endl;
	    assert (false);
	}
	NodeChunk * new_chunk = new NodeChunk;
	string * data_array = new string[chunkSize];
	new_chunk->val = data_array;
	new_chunk->next = nullptr;
	return new_chunk;
}

void initStack (int chunkSize, Stack& s) {
	if (chunkSize <= 0){
		cerr << "Error, chunk size must be positive." << endl;
	    assert (false);
	}
	s.chunkSize = chunkSize;
	s.topElt = -1;
	s.firstChunk = nullptr;
}

bool isEmpty (const Stack& s) {
	return nullptr == s.firstChunk;
}

//However,push needs to check if the current first NodeChunk is full; if so,
//another NodeChunk needs to be allocated and linked in place.
bool isArrayFull(string * data, int size){
	for (int i = 0; i < size; i++){
		if (data[i].empty() == true){
			return false;
		}
	}
	return true;
}

bool isArrayEmpty(string * data, int size){
	for (int i = 0; i < size; i++){
		if (data[i].empty() == false){
			return false;
		}
	}
	return true;
}

int addWordToArray(string * data, string word, int size){//data CANNOT be full
	for (int i = 0; i < size; i++){
		if (data[i].empty() == true){
			data[i] = word;
			return i;
		}
	}
	//cerr << "Could not place the word, data WAS FULL" << endl;
	return -1;
}

void push (string val, Stack& s) {
	NodeChunk * chunk;
	//if s is empty
	if (nullptr == s.firstChunk){
		chunk = createNewNodeChunk(s.chunkSize);
		s.firstChunk = chunk;
	} else if (isArrayFull(s.firstChunk->val, s.chunkSize) == true){//first array is full
		chunk = createNewNodeChunk(s.chunkSize);
		chunk->next = s.firstChunk;
		s.firstChunk = chunk;
	}
	//guaranteed that first chunk is not full
	int index = addWordToArray(s.firstChunk->val, val, s.chunkSize);
	s.topElt = index;
}

void pop (Stack& s) {
	assert( !(isEmpty(s)) ); // what if the firstChunk is now empty? delete it //string.erase
	s.firstChunk->val[s.topElt].erase();//delete the first one

	if (isArrayEmpty(s.firstChunk->val, s.chunkSize) == true){ //first array is now empty
		NodeChunk * chunk = s.firstChunk;
		s.firstChunk = s.firstChunk->next;
		//what if the entire stack is now empty?
		delete chunk;
		if (s.firstChunk == nullptr){
			s.topElt = -1;
		} else {
		    s.topElt = s.chunkSize - 1;//top of next array
		}
	} else {//the first array is NOT empty
		s.topElt--;
	}

}

string top (const Stack& s) {
	assert( !(isEmpty(s)) );
	return s.firstChunk->val[s.topElt];
}

int size (const Stack& s) {
	//if stack is empty
	if (nullptr == s.firstChunk){
		return 0;
	}
	int size = s.topElt + 1;
	NodeChunk * chunk = s.firstChunk->next;
	while (nullptr != chunk){
		size += s.chunkSize;
		chunk = chunk->next;
	}
	return size;
}

void swap (Stack& s) {
	assert(size(s) >= 2);
	//switches top 2 elements
	string word_one = top(s);
	string word_two;
	//two cases: next word is in the same stack, or it is not
	int numInFirstStack = s.topElt + 1;
	if (numInFirstStack >= 2){
		word_two = s.firstChunk->val[s.topElt - 1];//more than 2 in the first chunk, get the second one, below the top one
	} else {
		word_two = s.firstChunk->next->val[s.chunkSize - 1];//more than 2 total, so next chunk is full. so get word from next chunk
	}

	//now just pop twice and then add
	//cout << "Word one is: " << word_one << " and word two is: " << word_two << endl;
	pop(s);
	pop(s);
	push(word_one, s);
	push(word_two, s);
}



void print(const Stack & s){//assume it is not NULL
	NodeChunk * tempChunk = s.firstChunk;
	while (tempChunk != nullptr){
		//display the data
		for (int i = 0; i < s.chunkSize; i++){
			cout << tempChunk->val[i] << ", ";
		}
		cout << endl;
		tempChunk = tempChunk->next;
	}
	cout << "The Top element is " << top(s) << endl;;

}

int main(int argc, char * argv[]){
	Stack s1;
	initStack(1, s1);
	push("aaa", s1);
	push("bbb", s1);
	push("ccc", s1);
	push("ddd", s1);
	push("eee", s1);
	push("fff", s1);
	push("ggg", s1);
	push("hhh", s1);
	push("iii", s1);
	push("jjj", s1);
	push("kkk", s1);
	cout << "Size should be 11. size is " << size(s1) << endl;
	cout << "Top should be kkk. top is " << top(s1) << endl;
	cout << "Swapping. Before top 2 should be kkk , jjj" << endl;
	print(s1);
	cout << "After swapping, top 2 should be jjj, kkk" << endl;
	swap(s1);
	print(s1);
	cout << "popping 5 times, new top should be fff" << endl;
	pop(s1);
	pop(s1);
	pop(s1);
	pop(s1);
	pop(s1);
	print(s1);

	cout << endl;
	cout << "==============================================" << endl;
	cout << endl;
	cout << "Making new stack. chunk size is 100" << endl;
	Stack s2;
	initStack(100, s2);
	for (int i = 0; i < 100; i++){
		push(to_string(i), s2);
	}
	cout << "Size should be 100. size is " << size(s2) << endl;
	cout << "Top should be 99. top is " << top(s2) << endl;
	cout << "Swapping. Before top 2 should be 99 , 98" << endl;
	print(s2);
	cout << "After swapping, top 2 should be 98, 99" << endl;
	swap(s2);
	print(s2);


	return 0;
}

// int main(int argc, char * argv[]){
// 	Stack s1;
//     initStack(3,s1);
// 	cout << "Test case: it is empty... is empty = " << isEmpty(s1) << endl;
// 	push("aaa", s1);
// 	push("bbb", s1);
// 	push("ccc", s1);
// 	push("ddd", s1);
// 	cout << "testing swapping" << endl;
// 	cout << "Before: " << endl;
// 	print(s1);
// 	cout << "After: " << endl;
// 	swap(s1);
// 	print(s1);
//
// 	cout << "Size of s1 is " << size(s1) << endl;
// 	cout << "Test case: it is NOT empty... is empty = " << isEmpty(s1) << endl;
// 	cout << "Top Element loc is " << s1.topElt << endl;
// 	cout << "Before popping" << endl;
// 	cout << "before Top Element loc is " << s1.topElt << endl;
// 	print(s1);
// 	cout << "popping..." << endl;
// 	pop(s1);
// 	print(s1);
// 	cout << "after Top Element loc is " << s1.topElt << endl;
//
// 	cout << "Popping stack of size 1" << endl;
// 	Stack s2;
// 	initStack(10, s2);
// 	//push("aaa", s2);
// 	print(s2);
// 	//pop(s2);
// 	print(s2);
// 	cout << "Size of s2 is " << size(s2) << endl;
// 	return 0;
// }