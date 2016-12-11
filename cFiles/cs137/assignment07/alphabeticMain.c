#include <stdio.h>
#include <stdlib.h>

char *alphabetic (const char *s);

int main (void)
{
  char *a, *b, *c, *d, *e;
  a = alphabetic ("Ready... aim... fire!");
  b = alphabetic ("***");
  c = alphabetic ("*a*b*c*");
  d = alphabetic ("");
  e = alphabetic ((char *) 0);

  printf ("%s\n", a);
  printf ("%s\n", b);
  printf ("%s\n", c);
  printf ("%s\n", d);
  //printf ("%s\n", e);

  free(a);
  free(b);
  free(c);
  free(d);
  free(e);

  return 0;
}
