#include <cassert>
#include <string>
#include <iostream>
using namespace std;
//- loopup, insert, print, delete, init, isEmpty

struct BST_Node {
	string key;
	BST_Node * leftChild;
	BST_Node * rightChild;
};

typedef BST_Node * BST;

void BST_init(BST & root){
	root = nullptr;
}

bool BST_isEmpty(const BST & root){
	return root == nullptr;
}

void BST_insert(BST & root, string key){//no children, 1 children, 2 children
	if (root == nullptr){
		root = new BST_Node;
		root -> leftChild = nullptr;
		root -> rightChild = nullptr;
		root -> key = key;
	} else if (key < root->key ){
		BST_insert(root->leftChild, key);
	} else if (key > root->key){
		BST_insert(root->rightChild, key);
	}
}

void BST_print(const BST & root){
	if(root != nullptr){
		BST_print(root->leftChild);
		cout << root->key << endl;
		BST_print(root->rightChild);
	}
}

bool BST_lookup(const BST & root, string key){
	if (root == nullptr){
		return false;
	} else if ( root->key == key){
		return true;
	} else if (root->key < key){
		return BST_lookup(root->rightChild, key);
	} else {
		return BST_lookup(root->leftChild, key);
	}
}



void BST_remove(BST & root, string key){
	if (BST_lookup(root, key) == false){
		return;
	}
	BST_Node * parent = nullptr;
	BST_Node * target = nullptr;
	bool left = false;

	if (root->key == key){
		if (root->leftChild == nullptr && root->rightChild == nullptr){//no children, deleting root
			cerr << "deleting root: it had no children" << endl;
			root = nullptr;
		} else if (root->leftChild != nullptr && root->rightChild != nullptr){
			cerr << "deleting root: it had TWO children" << endl;
					target = root;
					BST_Node * tempPar = target;
					BST_Node * tempRep = target -> leftChild;

					while (true){
						if (tempRep->rightChild == nullptr){
							cerr << "largest value in left subtree is " << tempRep -> key << endl;
							break;
						} else {
							tempPar = tempRep;
							tempRep = tempRep->rightChild;
						}
					}
					//now we have largest in LEFT subtree
					if (tempRep->leftChild != nullptr){ // connect replacement's parent node to replacement's child node, if it has one
						cerr << "replacement node had a left child" << endl;
						tempPar->rightChild = tempRep->leftChild;
					}

					//now, swap replacement Node with target Node
					tempRep -> leftChild = target->leftChild;
					tempRep -> rightChild = target-> rightChild;
					root = tempRep;
					delete target;
					return ;
		} else if (root->leftChild == nullptr){
			cerr << "deleting root: it had only right child" << endl;
			target = root;
			root = root -> rightChild;
			delete target;
			return ;
		} else if (root->rightChild == nullptr){
			cerr << "deleting root: it had only left child" << endl;
			target = root;
			root = root -> leftChild;
			delete target;
			return ;
		}
	} else if (root->leftChild != nullptr  && root->leftChild->key == key){
		cerr << "target is root's left child" << endl;
		parent = root;
		target = root->leftChild;
		left = true;
	} else if (root->rightChild != nullptr && root->rightChild->key == key){
		cerr << "target is root's right child" << endl;
		parent = root;
		target = root->rightChild;
		left = false;
	} else if (root->key < key){
		cerr << "searching right child" << endl;
		BST_remove(root->rightChild, key);
	} else {
		cerr << "searching left child" << endl;
		BST_remove(root->leftChild, key);
	}

	if (target!= nullptr && target->leftChild == nullptr && target->rightChild == nullptr){//NO CHILDREN
		cerr << "no children" << endl;
		if (left == true){
			parent->leftChild = nullptr;
		} else {
			parent->rightChild = nullptr;
		}
		delete target;
		return;
	} else if (target!= nullptr && (target->leftChild == nullptr || target->rightChild == nullptr)){//ONE CHILD
		cerr << "1 child" << endl;
		if (target->leftChild == nullptr){
			if (left == true){
				parent->leftChild  = target->rightChild;
			} else {
				parent->rightChild  = target->rightChild;
			}
		} else {
			if (left == true){
				parent->leftChild  = target->leftChild;
			} else {
				parent->rightChild  = target->leftChild;
			}
		}
		delete target;
		return;
	} else {//TWO CHILDREN; pick largest in LEFT subtree as replacement
		//find largest in left subtree
		cerr << "two children" << endl;
		BST_Node * tempPar = target;
		BST_Node * tempRep = target -> leftChild;

		while (true){
			if (tempRep->rightChild == nullptr){
				cerr << "largest value in left subtree is " << tempRep -> key << endl;
				break;
			} else {
				tempPar = tempRep;
				tempRep = tempRep->rightChild;
			}
		}
		//now we have largest in LEFT subtree
		if (tempRep->leftChild != nullptr){ // connect replacement's parent node to replacement's child node, if it has one
			cerr << "replacement node had a left child, so replacement parent points to that" << endl;
			tempPar->rightChild = tempRep->leftChild;
		} else {
			cerr << "replacement node didn't have any childre, parent of replacement points to nullptr" << endl;
			tempPar->rightChild = nullptr;
		}

		//now, swap replacement Node with target Node
		tempRep -> leftChild = target->leftChild;
		tempRep -> rightChild = target-> rightChild;
		cerr << "temp rep now points to: " << tempRep->leftChild->key << " <--.--> " << tempRep->rightChild->key << endl;
		cerr << "parent (" << parent->key << ") now points to new replacement of target (" << tempRep->key << ") on ";
		if (left == true){
			cerr << "left" << endl;
			parent->leftChild = tempRep;//make PARENT OF TARGET piont to the new REPLACEMENT node!
		} else {
			cerr << "right" << endl;
			parent->rightChild = tempRep;
		}
		delete target;
		return;
	}
}

int main(int argc, char * argv[]){
	BST tree;
	BST_init(tree);
	BST_insert(tree, "G");
	BST_insert(tree, "D");
	BST_insert(tree, "B");
	BST_insert(tree, "F");
	BST_insert(tree, "E");
	BST_insert(tree, "A");
	BST_insert(tree, "C");
	BST_insert(tree, "Z");

	cout << "BEFORE:" << endl;
	BST_print(tree);
	BST_remove(tree, "D");
//	cout << "AFTER" << endl;
	// for (int i = 0; i < 26; i++){
	// 	string word = "";
	// 	char c = i + 65;
	// 	for (int j = 0; j < 3; j++){
	// 		word += c;
	// 	}
	// 	BST_insert(tree, word);
	// }
	BST_print(tree);

	//cout << "does JJJ exist? " << BST_lookup(tree, "JJJ") << endl;
	return 0;
}
