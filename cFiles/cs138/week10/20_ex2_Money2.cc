#include <iostream>


// Similar to (but not same as) example in Savitch
class Money {
public :
Money ();
Money (int dollars, int cents);
bool operator==(const Money &m) const;
const Money operator+(const Money &m) const;
Money& operator=(const Money &m);
friend ostream& operator<<(ostream &out, const Money &m); // What do I need to include here?
  private :
    int dollars, cents;
};

// operator+ is non-trivial!  [Savitch]
const Money Money::operator+(const Money&m) const{
    const int totalCents = cents + m.cents +
        100*(dollars + m.dollars);
    int sumCents = abs (totalCents % 100);
    int sumDollars = abs(totalCents / 100);
    if (totalCents < 0) {
        sumCents *= -1;
        sumDollars *= -1;
    }
    return Money (sumDollars, sumCents);
}
