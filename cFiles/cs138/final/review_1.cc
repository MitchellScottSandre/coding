#include <string>
using namespace std;

struct Qnode{
	string val;
	Qnode * next;
};

struct PQnode {
	int priority;
	Qnode * first;
	Qnode * last;
	PQnode * next;
};

typedef PQnode * PQ;
