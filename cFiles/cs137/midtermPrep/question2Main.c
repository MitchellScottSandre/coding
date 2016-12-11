#include <assert.h>
#include <math.h>
#include <stdio.h>
double my_atan2(double y, double x);
void main() {
 printf("%g\n", my_atan2(1, 1) * 180 / acos(-1));
 printf("%g\n", my_atan2(-1, -1) * 180 / acos(-1));
}
