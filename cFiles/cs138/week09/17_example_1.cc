#include <iostream>
using namespace std;

class P {
	public:
		P();
		virtual ~P();
		virtual void m1();
		virtual void m2();
		int pv;
};
P::P(){}
P::~P(){}

void P::m1(){
	cout << "P::m1" << endl;
	m2();
}

void P::m2(){
	cout << "P::m2" << endl;
}

class C: public P{
public:
	C();
	virtual ~C();
	virtual void m2();
	virtual void m3();
	int cv;
};

C::C(){}
C::~C(){}

void C::m2(){
	cout << "C::m2" << endl;
}

void C::m3(){
	cout << "C::m3" << endl;
}

int main(int argc, char * argv[]){
	P *f, *g;
	f = new P;
	f->m2();
	g = new C;
	g->m2();
	f->m1();
	g->m1();
}
