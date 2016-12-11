#include <stdio.h>
void polyPrint (double a[], int n);
int main (void) {

//    7
//-32
//-5
//-22
//-23
//-95
//-2345
//-123

    double a[] = {2.0, 3.0, 4.0};
    double b[] = {0.0, 3.0, 0.0};
    double c[] = {2.0, -2.0, 9.0, -1.0, 8.0, -7.0};
    double d[] = {2.0, 0.0, 0.0, 0.0, 0.0, 1.0};
    double e[] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
    double f[] = {2.0/3.0, 1.0/7.0, 9.0/13.0};
    double g[] = {};

    polyPrint (a, sizeof(a)/sizeof(a[0]));
    polyPrint (b, sizeof(b)/sizeof(b[0]));
    polyPrint (c, sizeof(c)/sizeof(c[0]));
    polyPrint (d, sizeof(d)/sizeof(d[0]));
    polyPrint (e, sizeof(e)/sizeof(e[0]));
    polyPrint (f, sizeof(f)/sizeof(f[0]));
    polyPrint (g, sizeof(g)/sizeof(g[0]));

//    double a[] = {2.0, 3.0, 4.0};
//    double b[] = {0.0, 3.0, 0.0};
//    double c[] = {2.0, -2.0, 9.0, -1.0, 8.0, -7.0};
//    double d[] = {2.0, 0.0, 0.0, 0.0, 0.0, 1.0};
//    double e[] = {0.0, 0.0, 0.0, 0.0, 0.0, 0.0};
//    double f[] = {2.0/3.0, 1.0/7.0, 9.0/13.0};
//
//
//    double g[] = {0, 2.0, -2.8876001, -1.00, 555, 1, 0, -0.333, -0.98762, 1.0000001, 0, 0, 0,  8, -7.522, 0};//0x^6 - 7x^5 + 8x^4 + 0x^3 - 1x^2 - 2x + 2
//    double h[] = {0};
//    double aa[] = {-1, -2, -3, -4 -5, 0, 0, 0, 44, -22, 0.123, -0.0006, 0, 0, 0.1, 65};
//    double bb[] = {-1, 1, 1, 1, 1, 1, -1, -1, -1};
//    double cc[] = {7, -32, -5, -22, -23, -95, -2345, -123};
////    polyPrint (a, sizeof(a)/sizeof(a[0]));
////    polyPrint (b, sizeof(b)/sizeof(b[0]));
////    polyPrint (c, sizeof(c)/sizeof(c[0]));
////    polyPrint (d, sizeof(d)/sizeof(d[0]));
////    polyPrint (e, sizeof(e)/sizeof(e[0]));
////    polyPrint (f, sizeof(f)/sizeof(f[0]));
//    polyPrint (g, sizeof(g)/sizeof(g[0]));
//    polyPrint (h, sizeof(h)/sizeof(h[0]));
//    polyPrint (aa, sizeof(aa)/sizeof(aa[0]));
//    polyPrint (bb, sizeof(bb)/sizeof(bb[0]));
//    polyPrint (cc, sizeof(cc)/sizeof(cc[0]));
//
//
//    printf("-------\n\n");
//    double z = 1.000000001;
//    printf("%.15g\n", z);
    return 0;
}
