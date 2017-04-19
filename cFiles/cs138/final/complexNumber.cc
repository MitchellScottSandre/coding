#include <iostream>
using namespace std;

class ComplexNumber{
public:
	ComplexNumber();
	ComplexNumber(double real, double img);
	friend bool operator== (const ComplexNumber & c1, const ComplexNumber & c2);
	friend ComplexNumber operator+(const ComplexNumber &c1, const ComplexNumber & c2);
private:
	double a;//real
	double b;//img
};

ComplexNumber::ComplexNumber() : a(0), b(0){}

ComplexNumber::ComplexNumber(double real, double img) : a(real), b(img){}

bool operator== (const ComplexNumber & c1, const ComplexNumber & c2){
	return c1.a == c2.a && c1.b == c2.b;
}

ComplexNumber operator+ (const ComplexNumber & c1, const ComplexNumber & c2){
	ComplexNumber newC (c1.a + c2.a, c1.b + c2.b);
	return newC;
}
