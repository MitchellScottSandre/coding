#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <math.h>
#include "poly.h"



struct poly *polyCreate() { // - Creates a polynomial. Initially, all coefficients in the polynomial should be 0.
    struct poly *p = malloc(sizeof(struct poly));
    p -> size = 1;
    p -> data = malloc(sizeof(double) * p->size);
    p -> length = 1;
    p -> data[0] = 0;
    return p;
}

struct poly *polyDelete(struct poly *p) { //- Frees storage associated with a polynomial. Returns a null pointer for convenience.;


    if (p) {
        //printf("xx");
        free (p->data);
        free (p);
        p = NULL;
    }
    return NULL;
}

struct poly *polySetCoefficient (struct poly *p, int i, double value) { //- Sets the ith coefficient of the polynomial to the specified value. Returns p for convenience. Assume that i ≥ 0.;
    if (p == NULL)return NULL;
    if (i >= 0 && p) {
        if (i >= p->size) { // outside of p's size...we need to make p larger now
            do {
                p -> size *= 2; // determine new size
            } while (i >= p-> size);
            p -> data = realloc(p->data, p->size * sizeof(double)); // realloc size
        }
        //else,index is less than p->size
        while (i >= p-> length) {
            p->data[p->length] = 0;
            p->length++;
        }
        p->data[i] = value;
    }
    return p;
}

double polyGetCoefficient (struct poly *p, int i) { //- Returns the ith coefficient of the polynomial. Assume that i ≥ 0.
    if (p == NULL)return 0.0;
    if (p && i >= 0 && i < p->length) {
        return p->data[i];
    }
    return 0.0;
}
int polyDegree (struct poly *p) { //- Returns the degree of the polynomial. For this assignment, assume that the 0 polynomial has degree 0.
    if (p == NULL)return 0;
    if (p) { //isn't null
        //degree is the largest value that isn't 0...should be length...what if we set a larger number to 0
        for (int i = p->length; i >= 0; i--) {
            if (p->data[i] != 0) {
                   // printf("degree....data at i is %g\n", p->data[i]);
                return i;
            }
        }
        return 0;//otherwise, check this case
    }
    return 0;
}

int nextNumberInfo(double *a, int n, int loc) {
    // -1 0 0 0 8
    int solution = 0;
    int i;
    for (i = loc - 1; i >=0; i--) {
        if (a[i] != 0) {
            if (a[i] < 0) return 1;//less than 0
            return 2;//more than 0
        }
    }
    return 3; // no non zero next number

}

void polyPrint (struct poly *a) {//- Prints the polynomial in the format illustrated below. You may reuse your A4P2 submission.
    if (a == NULL){
        //printf("--> NULL");
        //printf("\n");
        //return ;
    } else {//case: what if a is 0 or null or has size of 0o
    int i, locFirstNum;
    int n = a->length;
   // double a[] = p->data;
    double ebsalon = 0.000000000000000000000000000000000000000001;
    bool havePrintedFirstNumber = false;
    if (n == 0) {
        printf("0\n");
    } else {
        for (i = n - 1; i >= 0; i--) {
            if (a->data[i] != 0) {

                if (havePrintedFirstNumber == false && a->data[i] < 0) {                      //first number to be printed is negative:      -
                    printf("-");
                    locFirstNum = i;
                    havePrintedFirstNumber = true;
                }
                havePrintedFirstNumber = true;
                if (fabs (fabs (a->data[i]) - 1.000000000000000000000000000 ) > ebsalon) { //number is not 1
                    printf("%g", fabs(a->data[i]));
                } else if (i == 0 && fabs (fabs (a->data[i]) - 1.000000000000000000000000000 ) < ebsalon) { //number is 1, i is 0
                    printf("1");
                }
                if (i > 0) { //has an x value
                    printf("x");
                }
                if (i > 1) {
                    printf("^%d", i);//x^2 or x^8
                }
                if (i > 0) {
                    switch (nextNumberInfo(a->data, n, i)) {
                    case 1:
                        printf(" - ");
                        break;
                    case 2:
                        printf(" + ");
                        break;
                    }
                }
            } else if (havePrintedFirstNumber == false && i == 0) {
                printf("0");
            }
        }
        printf("\n");
    }
}
}

struct poly *polyCopy (struct poly *p){ //- Makes a copy of a polynomial.;
    if (p == NULL)return NULL;
    struct poly *newP = malloc(sizeof (p));
    newP -> size = p->size;
    newP -> data = malloc(sizeof(double) * p->size);
    newP -> length = p->length;
    for (int i = 0; i < p->length; i++){
        newP->data[i] = p->data[i];
    }
    return newP;
}

struct poly *polyAdd (struct poly *p0, struct poly *p1){//- Returns a new polynomial with the value p0+p1.
    if (p0 == NULL || p1 == NULL)return NULL;
    struct poly *newP;
    if (sizeof(p0) > sizeof(p1)){//malloc size of new structure, and if one is larger than also set size of data, then malloc
            newP = malloc(sizeof(p0));
            newP->size = p0->size;
    } else {
        newP = malloc(sizeof(p1));
        newP->size = p1->size;
    }
    newP->data = malloc(sizeof(double) * newP->size);
    if (p0->length > p1->length){//they can have same size but have one length larger than the other!
        newP->length = p0->length;
    } else {
        newP->length = p1->length;
    }
    for (int i = 0; i < newP->length; i++){
        newP->data[i] = p0->data[i] + p1->data[i];
    }
    return newP;
}

struct poly *polyMultiply (struct poly *p0, struct poly *p1){// - Returns a new polynomial with the value p0*p1.
    if (p0 == NULL || p1 == NULL)return NULL;
    struct poly *newP;
    //might as well give it extra space, just easier than calculating lol
    newP = malloc(sizeof(struct poly));
    newP->size = p0->size + p1->size;
    newP->length = polyDegree(p0) + polyDegree(p1);
    newP->data = calloc(newP->size, sizeof(double));

    //now multiply
    for (int bottomIndex = 0; bottomIndex < p1->length; bottomIndex++){ //p1, BOTTOM
        for (int topIndex = 0; topIndex < p0->length; topIndex++){ //p0, TOP
           polySetCoefficient(newP, topIndex + bottomIndex, newP->data[topIndex + bottomIndex] + p1->data[bottomIndex] * p0->data[topIndex]);
        }
    }

    return newP;
}


struct poly *polyPrime (struct poly *p){ //- Returns the derivative of the polynomial.;
    if (p == NULL)return NULL;
    struct poly *newP = malloc(sizeof(struct poly));
    newP->size = p->size;
    newP->length = p->length - 1;
    newP->data = calloc ( newP->size, sizeof(double));
    for (int i = p->length - 1; i > 0; i--){
        newP->data[i - 1] = i * p->data[i];
    }
    return newP;
}


double polyEval (struct poly *p, double x){ //- Evaluates the polynomial by substituting x.
    if (p == NULL)return 0.0;
    double sum = 0;
    for (int i =0; i < p->length; i++){
        sum += p->data[i] * pow(x, (double)(i));
    }
    return sum;
}
