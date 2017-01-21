void printList (Node* p) {
	while (nullptr != p){
		cout << p->val << endl;
		p = p->next;
	}
}
