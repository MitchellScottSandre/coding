#include <stdio.h>
#include "fraction.h"
#include <math.h>
#include <stdlib.h>
int gcdFunction(int a, int b) {
    int r = 0;
    while (b) {
        r = a % b;
        a = b;
        b = r;
    }
    return a;
}

int LCM (int a, int b){
	int gg = gcdFunction(a, b);
    return a / gg * b;
}

int reduce (int a, int b, int returnFirstOrSecond){//a, b, 0 for FIRST, 1 for SECOND
    int gcd = gcdFunction(a, b);
    if (returnFirstOrSecond == 0){
        return a / gcd;
    }
    return b / gcd;
}

struct fraction fractionCreate (int numerator, int denominator){
    if (denominator == 0){
        struct fraction badFraction = {0,0} ;
        return badFraction;
    }
    int newNum = reduce(numerator, denominator, 0);
    int newDenom = reduce(numerator, denominator, 1);
    struct fraction newFraction = {newNum,newDenom};
    return newFraction;
};

struct fraction fractionAdd (struct fraction a, struct fraction b){//3/2 + 1/3 == 9/6 + 2/6 == 11/6
    if (a.denom == 0 || b.denom == 0){
        struct fraction badFraction = {0, 0};
        return badFraction;
    }
    //good input
    int lcm = LCM(a.denom, b.denom);
    int sum = a.num * (lcm / a.denom) + b.num * (lcm / b.denom);
    struct fraction newFraction = {sum, lcm};
    return newFraction;

};

struct fraction fractionSubtract (struct fraction a, struct fraction b){
    int x = b.num;
    b.num = -x;
    return fractionAdd(a, b);

};

struct fraction fractionMultiply (struct fraction a, struct fraction b){//2/3 * 4/5
    if (a.denom == 0 || b.denom == 0){
        struct fraction badFraction = {0, 0};
        return badFraction;
    }
    int n = a.num * b.num;
    int t = n;
    int d = a.denom * b.denom; //now reduce this

    n = reduce (n, d, 0);
    d = reduce (t, d, 1);
    struct fraction newFraction = {n, d};
    return newFraction;

};

struct fraction fractionDivide (struct fraction a, struct fraction b){
    if (a.denom == 0 || b.denom == 0 || b.num == 0){
        struct fraction badFraction = {0, 0};
        return badFraction;
    }
    int t = b.num;
    b.num = b.denom;
    b.denom = t;
    return fractionMultiply(a, b);
};
//Returns a / b.
void fractionPrint (struct fraction f){
    int num, denom, gcd;//copy and pasted code from reduce.c
    num = f.num;
    denom = f.denom;
    if (denom == 0) { // 5 / 0
        printf("Divide by zero!\n");
    } else if (num == 0) { // 0 / 22
        printf("0\n");
    } else if (num % denom == 0) { // 10 / 2;   -5/5
        printf("%d\n", num / denom);
    } else {
        if(num * denom < 0) { //one of them is < 0
            printf("-");
        }
        num = abs(num);
        denom = abs(denom);
        gcd = gcdFunction (num, denom);
        if (num > denom) { // - 10 / 3
            printf("%d %d/%d\n", num / denom, (num - (num / denom)*denom) / gcd, denom / gcd);
        } else {//num < denom
            printf("%d/%d\n", num / gcd, denom / gcd);
        }
    }

};
//Prints fraction f in the same format as A1P1. You may reuse your reduce.c submission for this function.
