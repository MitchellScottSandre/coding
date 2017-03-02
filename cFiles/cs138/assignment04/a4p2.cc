#include <iostream>
#include <string>
#include <cassert>
using namespace std;

const int LEFT = 0;
const int RIGHT = 1;


// Definitions from class
struct BST_Node {
    string key;
    string stuff;
    BST_Node* left;
    BST_Node* right;
};

typedef BST_Node* BST;

void BST_remove (BST& root, string key);
void BST_remove2(BST & parent, BST & child, int leftOrRight);

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

string maxKeyInSubtree(BST & root){
	if (root->right == nullptr){
		return root->key;
	} else {
		return maxKeyInSubtree(root->right);
	}
}

// Now you do this one -->recursive
void BST_remove2(BST & parent, BST & child, int leftOrRight){//0 left, 1 right

	if (child->left == nullptr && child->right == nullptr){//child has no children
		//cerr << "target node has no children" << endl;
		delete child;
		if (leftOrRight == LEFT){
			parent->left = nullptr;
		} else {
			parent->right = nullptr;
		}
	} else if (child->right != nullptr && child->left != nullptr){//child has TWO CHILDREN
		//cerr << "target node has TWO children" << endl;
		string replacementKey = maxKeyInSubtree(child->left);
		string replacementStuff = BST_lookup(child->left, replacementKey);
		child->key = replacementKey;
		child->stuff = replacementStuff;
		cout << "replacement key is " << replacementKey << endl;
		BST_remove(child->left, replacementKey);
	} else if (child->left == nullptr){//1 child, only has RIGHT
		//cerr << "target node has RIGHT child ONLY" << endl;
		//cerr << "Making parent point to " << child->right->key << endl;
		if (leftOrRight == LEFT){//child is parent's LEFT
			parent->left = child->right;
		} else {
			parent->right = child->right;
		}
		delete child;
	} else if (child->right == nullptr){//child has 1 child, only has LEFT
		// cerr << "Child->left is " << child->left->key << endl;
		// cerr << "target node has LEFT child ONLY" << endl;
		// cerr << "Making parent point to " << child->left->key << endl;
		if (leftOrRight == LEFT){//child is parent's LEFT
			parent->left = child->left;
		} else {
			parent->right = child->left;
		}

		delete child;
	}
}

void BST_remove (BST& root, string key) {
    assert(BST_has(root, key));
	if (root->key == key){//first, middle root!
		if (root->left == nullptr && root->right == nullptr){
			//cerr << "Tree or subtree is now empty" << endl;
			root = nullptr;
			return;
		} else if (root->left == nullptr){
			root = root->right;
		} else if (root->right == nullptr){
			root = root->left;
		} else {
			//cerr << "target is FIRT node and has TWO children" << endl;
			string replacementKey = maxKeyInSubtree(root->left);
			string replacementStuff = BST_lookup(root->left, replacementKey);
			root->key = replacementKey;
			root->stuff = replacementStuff;
			BST_remove(root->left, replacementKey);
		}
	} else if (root->left != nullptr && root->left->key == key){//delete the left key
	//	cerr << "parent is: " << root->key << ", child key is " << root->left->key << " which is to LEFT" << endl;
		BST_remove2(root, root->left, LEFT);
	} else if (root->right != nullptr && root->right->key == key){//delete the right key
	//	cerr << "parent is: " << root->key << ", child key is " << root->right->key << " which is to RIGHT" << endl;
		BST_remove2(root, root->right, RIGHT);
		//cerr << "RIGHT IS " << RIGHT << endl;
	} else if (root->key < key){//search in RIGHT subtree to delete it
		//cerr << "current node is: " << root->key << " searching right" << endl;
		BST_remove(root->right, key);
	} else {
		//cerr << "current node is: " << root->key << " searching left" << endl;
		BST_remove(root->left, key);
	}
    //cerr << "Error, couldn't find \"" << key << "\" in the BST\n";
}
