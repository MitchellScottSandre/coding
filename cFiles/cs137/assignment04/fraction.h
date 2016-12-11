#ifndef FRACTION_H_INCLUDED
#define FRACTION_H_INCLUDED

struct fraction {
    int num, denom;
};
struct fraction fractionCreate (int numerator, int denominator);
//Creates a reduced fraction with the specified numerator and denominator.
struct fraction fractionAdd (struct fraction a, struct fraction b);
//Returns a + b.
struct fraction fractionSubtract (struct fraction a, struct fraction b);
////Returns a - b.
struct fraction fractionMultiply (struct fraction a, struct fraction b);
//Returns a * b.
struct fraction fractionDivide (struct fraction a, struct fraction b);
//Returns a / b.
void fractionPrint (struct fraction f);
//Prints fraction f in the same format as A1P1. You may reuse your reduce.c submission for this function.

#endif // FRACTION_H_INCLUDED
