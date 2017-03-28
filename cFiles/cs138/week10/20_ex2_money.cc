// Similar to (but not same as) example in Savitch
class Money {
  public :
    Money ();
    Money (int dollars, int cents);
  private :
    int dollars, cents;
};

Money::Money() : dollars(0), cents(0) {}
Money::Money(int dollars, int cents) : dollars(dollars), cents(cents) {}

int main (int argc, char* argv[]) {
	Money buckFifty (1,50), totalCost;
	Money twelvebits = buckFifty; // Use implicit copy cxr
	if (buckFifty == twelvebits){ // Oops, no "==" defined
		totalCost = twelveBits + buckFifty; // or "=" or "+" cout << totalCost << endl; // or "<<"
	}
}
