#include <string>
#include <iostream>
using namespace std;

class B {
public:
	B();
	virtual ~ B();
	virtual void m1() = 0;//purely virtual
	virtual void m2();
	void m3();
};

B::B(){
	cout << "B::B()" << endl;
}

B::~B(){
	cout << "B::~B" << endl;
}

void B::m2(){
	cout << "B::m2" << endl;
}

void B::m3(){
	cout << "B::m3" << endl;
}

class C : public B {
public:
	C(){
		cout << "C::C()" << endl;
	};
    virtual ~C(){
      cout << "C::~C" <<endl;
    };
    virtual void m1(){
      cout << "C::m1" <<endl;
    };
    virtual void m2(){
      cout<< "C::m2" << endl;
    }
    void m3(){
      cout << "C::m3" << endl;
    }
};

int main(int argc, char * argv[]){
	cout << "B pointer x = new C .... static type is B, dynamic type is C" << endl;
	B * xx = new C();//B::B then C::C
	xx->m1();//C::m1
	xx->m2();//C::m2
	xx->m3();//B::m3
	cout << "C pointer y = new C.... static type and dynamic type is C" << endl;
	C * yy = new C();//B::B then C::C
	yy->m1();//C::m1
	yy->m2();//C::m2
	yy->m3();//C::m3
	return 0;
}
