#include <iostream>
#include <string>
#include <cassert>
using namespace std;
string* myAlloc (int n) {
 assert (n>0);
 return new string[n];
}
int main (int argc, char* argv[]) {
 string* A = myAlloc(10);
 string* B = myAlloc(20);
 string* temp = A;
 A = B; // No problem "swapping"; they're just ptrs!
 B = temp;
 delete []A; // If we stored extent w ptr, this
 delete []B; // would NOT work, but it does
}
