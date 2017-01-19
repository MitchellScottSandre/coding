#include <iostream>
#include <string>
using namespace std;


struct Node {
  string val;
  Node *next;
};

int main (int argc, char *argc[]){
  Node *p = new Node;
  p->val = "first";
  p->next = nullptr;
  Node *q, r; //q is pointer, r is not
  r.val = "flurble";
  q = new Node;
  q->next = p;
  q->val = "second";
  Node *s;
  s = new Node;
  s->val = "third";
  s->next = q;
  Node *temp = s;
  while (temp != nullptr){
    cout << temp->val < <end;
    temp = temp->next;
  }
  delete p;//clean up!
  delete q;
  delete s;
  //DONT DLETE R THO
}
