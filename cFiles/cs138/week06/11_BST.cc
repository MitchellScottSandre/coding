#include <iostream>
#include <string>
#include <cassert>
using namespace std;

struct BST_Node {
    string key;
    // we'll ignore "otherStuff"
    string otherStuff;
    BST_Node* left;
    BST_Node* right;
;}

typedef BST_Node* BST;

void BST_init (BST& root) {
    root = nullptr;
}
bool BST_isEmpty(const BST& root){
    return nullptr == root;
}
