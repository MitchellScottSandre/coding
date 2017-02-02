struct Qnode{//nodes that all have the same priority
	string val;
	Qnode * next;
};

struct PQnode{//each of these have a priority, point to a list of Qnodes
	int priority;
	Qnode * first;
	Qnode * last;
	PQnode * next;
};

typedef PQnode * PQ;
