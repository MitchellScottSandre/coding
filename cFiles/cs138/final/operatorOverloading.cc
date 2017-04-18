#include <string>
#include <iostream>
using namespace std;

class Money1 {
public :
	Money1 ();
	Money1 (int dollars, int cents);
	bool operator== (const Money1& m) const;
private :
	int dollars, cents;
};

// Note const ref param and const method promise
bool Money1::operator== (const Money1& m) const {
	return m.dollars == this->dollars && m.cents == this->cents;
}

//prefferred way

// Preferred way: Overload the global def of operator==
class Money {
public :
	Money ();
	Money (int dollars, int cents);
	// Must be a friend, as there are no accessors for data
	friend bool operator==(const Money& m1, const Money& m2);
private :
	int dollars, cents;
};

Money::Money() : dollars(0), cents(0) {}
Money::Money(int dollars, int cents) : dollars(dollars), cents(cents) {}
// Note both params are const refs

bool operator== (const Money& m1, const Money& m2) {
	return m1.dollars == m2.dollars && m1.cents == m2.cents;
}
