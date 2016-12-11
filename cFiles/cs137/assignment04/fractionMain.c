#include <stdio.h>
#include "fraction.h"

int main (void)
{
  struct fraction a, b, c, d, r, bad;

  a = fractionCreate (5, 6);//what if you ad 5/-6 + 2/7
  fractionPrint(a);
  b = fractionCreate (8, 9);
  c = fractionCreate (56, 160);
  d = fractionCreate (35, 150);
  bad = fractionCreate (8, 0);

  r = fractionAdd (a, b);
  fractionPrint(r);
  r = fractionSubtract (c, d);
  fractionPrint(r);
  r = fractionMultiply (a, b);
  fractionPrint(r);
  r = fractionDivide (c, d);
 fractionPrint(r);

  r = fractionAdd (a, bad);
  fractionPrint(r);
  r = fractionAdd (b, r);
  fractionPrint(r);

  printf("My tests now...\n");
  a = fractionCreate(-2, -3);
  b = fractionCreate(-10, 11);
  printf("a \n");
  fractionPrint(a);
  printf("b \n");
  fractionPrint(b);

  printf("a + b\n");
  r = fractionAdd (a, b);
  fractionPrint(r);

  printf("a x b\n");
  r = fractionMultiply (a, b);
  fractionPrint(r);

  printf("a / b\n");
  r = fractionDivide (a, b);
  fractionPrint(r);

  printf("Now enter what you want...\n");
  printf("First fraction:\n");
  int x, y;
  scanf("%d %d", &x, &y);
  a = fractionCreate(x, y);
  printf("Second fraction:\n");
  scanf("%d %d", &x, &y);
  b = fractionCreate(x, y);
  printf("a \n");
  fractionPrint(a);
  printf("b \n");
  fractionPrint(b);

  printf("a + b\n");
  r = fractionAdd (a, b);
  fractionPrint(r);

  printf("a x b\n");
  r = fractionMultiply (a, b);
  fractionPrint(r);

  printf("a / b\n");
  r = fractionDivide (a, b);
  fractionPrint(r);



  return 0;
}
