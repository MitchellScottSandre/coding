#include <stdio.h>
#include <stdlib.h>

typedef struct node {
    int val;
    struct node *next;
} node_t;

//this is being defined in a recursive manner. Our node type is node_t
//and node_t has a node *next and an int val

void printList(node_t *head){
    node_t *current = head;

    while (current != NULL){
        printf("%d, ", current->val);
        current = current->next;
    }
    printf("\n");
}

void push_back(node_t *head, int val){//add item to end of list
    node_t *current = head;
    while (current->next != NULL){
        current = current->next;
    }

    //now the next is null, so add our new item here
    current->next = malloc(sizeof(node_t));
    current->next->val = val;
    current->next->next = NULL;

}

void push_front(node_t **head, int val){
    node_t *new_node;
    new_node = malloc(sizeof(node_t));
    new_node->val = val;
    new_node->next = *head;
    *head = new_node;
}

int pop_front(node_t **head){
    int retVal = -1;
    node_t *nextNode = NULL;
    if (*head == NULL) return -1;
    nextNode = (*head)->next;
    retVal = (*head)->val;
    free(*head);
    *head = nextNode;
    return retVal;


}

int main(void){
    node_t *head = NULL;
    head = malloc(sizeof(node_t));
    head->val = 33;
    head->next = malloc(sizeof(node_t));
    head->next->val = 22;
    head->next->next = NULL;
    push_back(head, 11);
    push_back(head, 1033);
    push_front(&head, 5);
    printList(head);
    printf("popping front: %d\n", pop_front(&head));
    printList(head);



    return 0;
}
