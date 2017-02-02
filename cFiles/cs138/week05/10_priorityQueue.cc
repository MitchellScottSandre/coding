
struct PQnode{
	int priority; // 0 is most important
	string val;
	PQnode * next;
}

//any new nodes go at the end
//leave takes from FRONT / TOP

typedef * PQnode PQ;

//TO DO copy from online
