#include <iostream>
#include <string>
#include <cassert>
using namespace std;


//==================================================START OF QUEUE CODE =========================================
struct Qnode {
	string val;
	Qnode * next;
};

struct Queue {
	Qnode * first;
	Qnode * last;
};

void initQ (Queue& q) {
	q.first = nullptr;
	q.last = nullptr;
}

bool isEmptyQ (const Queue& q) {
	return nullptr == q.first;
}

int sizeQ (const Queue& q) {
	int size = 0;
	Qnode * temp = q.first;
	while (nullptr != temp){
		size++;
		temp = temp->next;
	}
	return size;
}

void enterQ (Queue& q, string val) {
	Qnode * p = new Qnode;
	p->val = val;
	if (nullptr == q.first){
		q.first = p;
	} else{
		q.last->next = p;
	}
	q.last = p;
	p->next = nullptr;
}

string firstQ (const Queue& q) {
	if (isEmptyQ(q) == true){
		cerr << "Error, queue is empty." << endl;
		assert(false);
	}
	return q.first->val;
}
void leaveQ (Queue& q) {
	if (isEmptyQ(q) == true){
		cerr << "Error, queue is empty." << endl;
		assert(false);
	}
	Qnode * p = q.first;
	q.first = q.first->next;
	if (nullptr == q.first){
		q.last = nullptr;
	}
	delete p;
}

void printQ (const Queue& q) {
	Qnode * temp = q.first;
	while (nullptr != temp){
		cout << temp->val << endl;
		temp = temp->next;
	}
}

//==================================================END OF QUEUE CODE =========================================

//==================================================START OF BST CODE =========================================
const int LEFT = 0;
const int RIGHT = 1;

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
		cout << root->key << endl;
	    //cerr << root->stuff << endl; // you need this code to match the output for a4p3
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
    BST toBeDeleted = child;

	if (child->left == nullptr && child->right == nullptr){//child has no children
		delete toBeDeleted;
		if (leftOrRight == LEFT){
			parent->left = nullptr;
		} else {
			parent->right = nullptr;
		}
	} else if (child->right != nullptr && child->left != nullptr){//child has TWO CHILDREN
		string replacementKey = maxKeyInSubtree(child->left);
		string replacementStuff = BST_lookup(child->left, replacementKey);
		child->key = replacementKey;
		child->stuff = replacementStuff;
		BST_remove(child->left, replacementKey);
	} else if (child->left == nullptr){//1 child, only has RIGHT
		if (leftOrRight == LEFT){//child is parent's LEFT
			parent->left = child->right;
		} else {
			parent->right = child->right;
		}
        delete toBeDeleted;
	} else if (child->right == nullptr){//child has 1 child, only has LEFT
		if (leftOrRight == LEFT){//child is parent's LEFT
			parent->left = child->left;
		} else {
			parent->right = child->left;
		}
        delete toBeDeleted;
	}
}

void BST_remove (BST& root, string key) {
    if (BST_has(root, key) == false){
        cerr << "Error, couldn't find \"" << key << "\" in the BST\n";
        assert(false);
    }
    assert(BST_has(root, key));
	if (root->key == key){//first, middle root!
		if (root->left == nullptr && root->right == nullptr){
			delete root;
			return;
		} else if (root->left == nullptr){
			BST temp = root;
			root = root->right;
			delete temp;
		} else if (root->right == nullptr){
			BST temp = root;
			root = root->left;
			delete temp;
		} else {
			string replacementKey = maxKeyInSubtree(root->left);
			string replacementStuff = BST_lookup(root->left, replacementKey);
			root->key = replacementKey;
			root->stuff = replacementStuff;
			BST_remove(root->left, replacementKey);
		}
	} else if (root->left != nullptr && root->left->key == key){//delete the left key
		BST_remove2(root, root->left, LEFT);
	} else if (root->right != nullptr && root->right->key == key){//delete the right key
		BST_remove2(root, root->right, RIGHT);
	} else if (root->key < key){//search in RIGHT subtree to delete it
		BST_remove(root->right, key);
	} else {
		BST_remove(root->left, key);
	}
}

//==================================================END OF BST CODE =========================================

struct SBL {
	BST names;
	Queue order;
	int numPeople;
};


void SBL_init (SBL& sbl) {
	BST newNames;
	BST_init(newNames);
	Queue newOrder;
	initQ(newOrder);
	sbl.names = newNames;
	sbl.order = newOrder;
	sbl.numPeople = 0;

}

int SBL_size (const SBL& sbl){
	return sbl.numPeople;
}

void SBL_arrive (SBL& sbl, string name) {
	sbl.numPeople++;
	BST_insert(sbl.names, name, "");
	enterQ(sbl.order, name);
}

string SBL_first (const SBL& sbl) {
	if (isEmptyQ(sbl.order) == true){
		cerr << "Error, SBL is empty." << endl;
		assert(false);
	}

	return firstQ(sbl.order);
}

void SBL_leave (SBL& sbl){
	if (isEmptyQ(sbl.order) == true){
		cerr << "Error, SBL is empty." << endl;
		assert(false);
	}
	
	BST_remove(sbl.names, firstQ(sbl.order));
	leaveQ(sbl.order);
	sbl.numPeople--;
}

bool SBL_lookup (const SBL& sbl, string name){
	return BST_has(sbl.names, name);
}

void SBL_printInArrivalOrder (const SBL& sbl) {
	if (SBL_size(sbl) > 0){
		printQ(sbl.order);
	}l
}

void SBL_printInLexicographicalOrder (const SBL& sbl) {
	if (SBL_size(sbl) > 0){
		BST_print(sbl.names);
	}
}
