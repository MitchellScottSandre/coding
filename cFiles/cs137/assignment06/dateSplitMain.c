#include <stdio.h>

int dateSplit(int dayOfYear, int year, int *day, int *month);

void testDateSplit(int dayOfYear, int year) {
  int day, month;

  if (dateSplit (dayOfYear, year, &day, &month))
   // printf ("%d,%d => %d,%d,  ", dayOfYear, year, day, month);
   printf ("%d,%d,  ", day, month);
  else
   // printf ("%d,%d => invalid,  ", dayOfYear, year);
   printf ("invalid, ");
}

int main (void) {

  testDateSplit (100, 2007);
  testDateSplit (100, 2008);
  testDateSplit (1, 2007);
  testDateSplit (1, 2008);
  testDateSplit (365, 2007);
  testDateSplit (366, 2007);
  testDateSplit (366, 2008);
  testDateSplit (-1, -1);
  printf("my tests\n");
  testDateSplit (1, 0);
  testDateSplit (1, 2008);
  testDateSplit (30, 2008);
  testDateSplit (31, 2008);
  printf("loop\n");
  for (int i = -1; i < 366; i++){
        testDateSplit (i, 2008);
  }

  return 0;
}
