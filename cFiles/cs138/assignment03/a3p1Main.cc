#include <iostream>
#include <string>
#include <cassert>
using namespace std;

struct Node {
    string val;
    Node* next;
    Node* prev;
};

struct Stew {
    Node* first;
    Node* last;
};

void initStew (Stew& q) {
    q.first = nullptr;
    q.last = nullptr;
}

bool isEmpty (const Stew& q) {
    return nullptr == q.first;
}

void addFront (Stew & q, string val) {
	Node * newFront = new Node;
	newFront->val = val;
	newFront->prev = nullptr;
	newFront->next = q.first;
	q.first = newFront;
}

void addBack (Stew & q, string val) {
    Node * newBack = new Node;
	newBack->val = val;
	newBack->prev = q.last;
	newBack->next = nullptr;
    if (nullptr != q.last){//list is NOT empty
        q.last->next = newBack;
    } else {//list IS currently empty
        q.last = newBack;
    }
    q.last = newBack;

}

void leaveFront (Stew & q) {
}

string peekFront (const Stew & q) {
	return "asdf";
}

void leaveBack (Stew & q) {
}

string peekBack (const Stew & q) {
	return "asdf";
}

void print (const Stew & q, char direction) {
    // Use the below line in your solution
    cerr << "Error, illegal direction: '" << direction << "'" << endl;
}

void nuke (Stew & q) {

}

int main (int argc, char* argv[]) {
    Stew s1;
    initStew (s1);
    addFront (s1, "alpha");
    addFront (s1, "beta");
    addFront (s1, "gamma");
    addBack (s1, "delta");
    cout << "This prints \"gamma beta alpha delta\" across four lines\n";
    print (s1, 'f');
    cout << "This prints \"delta alpha beta gamma\" across four lines\n";
    print (s1, 'r');
    leaveFront (s1);
    leaveBack (s1);
    cout << "This prints \"beta alpha\" in one line\n";
    cout << peekFront (s1) << " " << peekBack (s1) << endl;
    cout << "This nuke has no output, but is good form to call when done\n";
    nuke (s1);
    cout << "This assertion should succeed\n";
    assert (isEmpty (s1));
    cout << "Illegal direction should cause error mesg\n";
    print (s1, 'k');
//    addFront()
	return 0;
}
