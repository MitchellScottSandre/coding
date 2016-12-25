#include <stdio.h>
#include <stdlib.h>

typedef struct BST {
	int data;
	struct BST *lchild, *rchild;
} node;

void insert(node * root, node *new_node){//this creates a binary search tree
	if (new_node->data < root->data){//LESS THAN
		if (root->lchild == NULL){
			root->lchild = new_node; //it's null, so just set it to new node
		} else {
			insert(root->lchild, new_node);//try again on the left child node
		}
	}

	if (new_node->data > root->data){//GREATER THAN
		if (root->rchild == NULL){
			root->rchild = new_node;
		} else {
			insert(root->rchild, new_node);
		}
	}
}

//displays the tree in INORDER fashion
void inorder(node *temp){
	if (temp != NULL){
		inorder(temp->lchild);
		printf("%d\n ", temp->data);
		inorder(temp->rchild);
	}
}

void preorder(node *temp){
	if (temp != NULL){
		printf("%d\n ", temp->data);
		inorder(temp->lchild);
		inorder(temp->rchild);
	}
}
void postorder (node *temp){
	inorder(temp->lchild);
	inorder(temp->rchild);
	printf("%d\n ", temp->data);
}

//searches for a specific node (with a value) in the Binary Search Tree
node *search(node *root, int key, node **parent){//key is value we are looking for
	node *temp;
	temp = root;
	while (temp != NULL){
		if (temp->data == key){
			printf("The %d Element is Present\n", temp->data);
			return temp;
		}
		//else
		*parent = temp;
		if (temp->data > key){//so search left
			temp = temp->lchild;
		} else {
			temp = temp->rchild;
		}
	}
	return NULL;
}

node *get_node(){
	node *temp;
	temp = (node *) malloc(sizeof(node));
	temp->lchild = NULL;
	temp->rchild = NULL;
	return temp;
}


int main(void){
	int choice, key;
	char ans = 'N';
	node *new_node, *root, *tmp, *parent;
	node *getNode();
	root = NULL;

	printf("Program For Binary Search Tree\n");
	do {
		printf("\n1.Create");
      	printf("\n2.Search");
      	printf("\n3.Recursive Traversals");
      	printf("\n4.Exit");
      	printf("\nEnter your choice :");
		scanf("%d", &choice);

		switch(choice){
			case 1:
				do {
					new_node = get_node();
					printf("Enter the Data of New Node: ");
					scanf("%d", &new_node->data);

					if (root == NULL){
						root = new_node;
					} else {
						insert(root, new_node);
					}
					printf("Enter more elements? y or n:");
					scanf(" %c", &ans);//need space here!!!
				} while (ans == 'y');
				break;
			case 2:
				printf("Enter Element to be Searched: ");
				scanf("%d", &key);
				tmp = search(root, key, &parent);
				//printf("Parent of node %d is %d\n", tmp->data, parent->data);
				break;
			case 3:
				if (root == NULL){
					printf("The tree is not created\n");
				} else {
						printf("The InOrder Display:\n");
						inorder(root);
						printf("The PREOrder Display:\n");
						preorder(root);
						printf("The POSTOrder Display:\n");
						postorder(root);
					}
				break;
		}

	} while (choice != 4);


	return 0;
}
