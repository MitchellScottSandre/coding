#include <stdio.h>

int max (int array[], int n);
int countValue (int array[], int n, int value);
void absolute (int array[], int n);
int isSorted (int array[], int n);
int isPermutation (int array[], int n);

int main (void)
{
  int i;
  int a[5] = {5,3,2,4,1};
  int b[5] = {-1, 0, 0, 0, 1};
  int c[5] = {-10, 9, -8 , 7, -6};

  printf("max(a, 5) = %d\n", max(a, 5));
  printf("countValue (a, 5, 1) = %d\n", countValue (a, 5, 1));
  printf("countValue (a, 5, 0) = %d\n", countValue (a, 5, 0));
  printf("isSorted (a, 5) = %d\n", isSorted (a, 5));
  printf("isPermutation (a, 5) = %d\n", isPermutation (a, 5));

  printf("max(b, 5) = %d\n", max(b, 5));
  printf("countValue (b, 5, 1) = %d\n", countValue (b, 5, 1));
  printf("countValue (b, 5, 0) = %d\n", countValue (b, 5, 0));
  printf("isSorted (b, 5) = %d\n", isSorted (b, 5));
  printf("isPermutation (b, 5) = %d\n", isPermutation (b, 5));

  printf("My tests\n");

  int d[10] = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10};
  int e[10] = {10, 9, 8, 7, 6, 5, 4, 3, 2, 1};
  int f[5] = {1, 2, 3, 4, 1};
  int g[5] = {5, 4, 3, 2, 2};
  int h[5] = {-999, -5, -77, -3, -9999};

  printf("------>>> permutation - Should be 0 1 then 1 then 0 then 0\n\n");
  printf("isPermutation (d, 10) = %d\n", isPermutation (d, 10));
  printf("isPermutation (e, 10) = %d\n", isPermutation (e, 10));
  printf("isPermutation (f, 5) = %d\n", isPermutation (f, 5));
  printf("isPermutation (g, 5) = %d\n", isPermutation (g, 5));

  printf("----->>> sorted - Should be 1 0 1 0\n\n");
  printf("isSorted (d, 10) = %d\n", isSorted (d, 10));
  printf("isSorted (e, 10) = %d\n", isSorted (e, 10));
  printf("isSorted (f, 5) = %d\n", isSorted (f, 5));
  printf("isSorted (g, 5) = %d\n", isSorted (g, 5));

 // int h[10] = {-10, 4, 6, 10002, 44, 88, 99999, 0, 9, 10};
  //printf("max(h, 5) = %d\n", max(h, 10));
  printf("max(h, 5) = %d\n", max(h, 5));


  printf("should be 2: countValue (f, 5, 4) = %d\n", countValue (f, 5, 4));








  absolute (c, 5);
  for (i = 0; i < 5; i++)
    printf ("c[%d] = %d\n", i, c[i]);
  return 0;
}
