#include <stdio.h>
#include "poly.h"
int main (void) {
    printf("tests from marmoset ish...\n");
    struct poly *aaa = polyCreate();
    printf("Length %d, size %d, data[0] %g\n", aaa->length, aaa->size, aaa->data[0]);
    polyPrint(aaa);
    aaa = polyDelete(aaa);
    aaa = polyDelete(aaa);
    //polyPrint(aaa);
    //printf("..%d\n", aaa->length);


    printf("Test\n");
    struct poly *aa = polyCreate();
    polySetCoefficient(aa, 10, -0.1);
    polySetCoefficient(aa, 5, -2.2);
    polySetCoefficient(aa, 3, -1);
    polySetCoefficient(aa, 2, -7.8);
    polySetCoefficient(aa, 0, -22);
    polyPrint(aa);
    struct poly *bb = polyCopy(aa);
    polyPrint(bb);
    struct poly *cc = polyAdd(aa, bb);
    polyPrint(cc);
    printf("degree of aa is %d\n", polyDegree(aa));
    printf("aa at index 5 is %g, aa at index 3 is %g, aa at index1 is %g\n",polyGetCoefficient(aa, 5), polyGetCoefficient(aa, 3), polyGetCoefficient(aa, 1));
    printf("bb at index - 2 is %g, bb at index 10 is %g\n", polyGetCoefficient(bb, -2), polyGetCoefficient(bb, 10));
    printf("degree of cc is %d\n", polyDegree(cc));
    printf("length of cc is %d, size of cc is %d\n", cc->length, cc->size);
    struct poly *dd = polyMultiply(cc, aa);
    polyPrint(dd);
    struct poly *ee = polyPrime(aa);
    printf("prime of aa is ");
    polyPrint(ee);

    struct poly *ff = polyCreate();
    polySetCoefficient(ff, 5, 1.1);
    polySetCoefficient(ff, 4, -2.0004);
    polySetCoefficient(ff, 1, -104.22);
    polySetCoefficient(ff, 8, 9.234);
    polySetCoefficient(ff, 14, 0);
    polyPrint(ff);
    printf("degree of ff is %d\n", polyDegree(ff));
    polyPrint(polyPrime(ff));
    printf("end of my tests\n\n");

    aa = polyDelete(aa);
    bb = polyDelete(bb);
    cc = polyDelete(cc);
    dd = polyDelete(dd);
    ee = polyDelete(ee);
    ff = polyDelete(ff);


    struct poly *p0 = polySetCoefficient (polySetCoefficient (polySetCoefficient (polyCreate(), 0, 4.0), 1, -1.0), 10, 2.0);
   // polyPrint(p0);
    struct poly *p1 = polyCopy (p0);
    struct poly *p2, *p3, *p4;
    printf ("%g\n", polyGetCoefficient (p0, 10));
    printf ("%g\n", polyGetCoefficient (p0, 100));
    printf ("%d\n", polyDegree (p0));
   // printf("printed p0 is ");
    polyPrint (p0);
    polyPrint (p1);
    polySetCoefficient (p1, 2, 1.0/2.0);
   // printf("p0 size is %d, p0 length is %d\n", p0 -> size, p0 -> length);
    polyPrint (p1);
    p2 = polyAdd (p0, p1);
    polyPrint (p2);
    p3 = polyMultiply (p0, p1);
    polyPrint (p3);
    p4 = polyPrime (p0);
    polyPrint (p4);
    printf ("%g\n", polyEval (p0, 0.0));
    printf ("%g\n", polyEval (p0, 1.0));
    printf ("%g\n", polyEval (p0, 2.0));
    p0 = polyDelete (p0);
    p1 = polyDelete (p1);
    p2 = polyDelete (p2);
    p3 = polyDelete (p3);
    p4 = polyDelete (p4);
    return 0;
}
