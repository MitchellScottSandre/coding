#include <iostream>
#include <string>
#include <cassert>
using namespace std;

// Definitions from class
struct BST_Node {
    string key;
    string stuff;
    BST_Node* left; 
    BST_Node* right;
};

typedef BST_Node* BST;

void BST_init (BST& root) {
    root = nullptr;
}

bool BST_isMT (const BST& root) {
    return nullptr == root;
}

bool BST_has (const BST& root, string key) {
    if (nullptr == root) {
	return false;
    } else if (root->key == key) {
	return true;
    } else if (root->key > key) {
	return BST_has (root->left, key);
    } else {
	return BST_has (root->right, key);
    }
}

string BST_lookup (const BST& root, string key) {
    if (nullptr == root) {
	return "";
    } else if (root->key == key) {
	return root->stuff;
    } else if (root->key > key) {
	return BST_lookup (root->left, key);
    } else {
	return BST_lookup (root->right, key);
    }
}

void BST_insert (BST& root, string key, string stuff) {
    if (nullptr == root) {
	root = new BST_Node; 
	root->key = key; 
	root->stuff = stuff; 
	root->left = nullptr; 
	root->right = nullptr;
    } else if (key < root->key) {
	BST_insert(root->left, key, stuff);
    } else {
	BST_insert(root->right, key, stuff);
    } 
}

void BST_print (const BST& root) {
    if (nullptr != root) {
	BST_print (root->left);
	//cout << root->key << " " << root->stuff << endl;
    cout << root->stuff << endl; // you need this code to match the output for a4p3
	BST_print (root->right);
    }
}

// Now you do this one
void BST_remove (BST& root, string key) { 
    cerr << "Error, couldn't find \"" << key << "\" in the BST\n"; 
}

int main (int argc, char* argv[]) { 
}
