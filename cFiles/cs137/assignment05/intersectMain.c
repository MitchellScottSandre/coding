#include <stdio.h>
int intersect( double x1, double y1, double x2, double y2,
  	       double x3, double y3, double x4, double y4 );
void testIntersect ( double x1, double y1, double x2, double y2,
	 	     double x3, double y3, double x4, double y4 ) {
  printf
    ("(%g,%g)-(%g,%g) and (%g,%g)-(%g,%g) ", x1, y1, x2, y2, x3, y3, x4, y4);
  if (intersect (x1,y1,x2,y2,x3,y3,x4,y4))
    printf ("intersect\n");
  else
    printf ("do not intersect\n");
}

int main (void) {

  testIntersect (2.0,8.0,11.0,0.0,1.0,1.0,10.0,10.0);
  testIntersect (2.0,8.0,11.0,0.0,20.0,20.0,10.0,10.0);
  printf("test case: parallel, no intersect, different y intercepts \n");
  testIntersect (0, 0, 10, 10, 1, 0, 11, 10);
  printf("switched lines 1 and 2\n");
  testIntersect ( 1, 0, 11, 10, 0, 0, 10, 10);
  printf("test case: parallel, YES intersect, same y intercept\n");
  testIntersect (0, 0, 10, 10, 5, 5, 11, 11);
  printf("test case: parallel, NO intersect, same y intercept\n");
  testIntersect (0, 0, 10, 10, 20, 20, 11, 11);
  printf("test case: ONE vertical, NO intersect, X is outside domain\n");
  testIntersect (0, 0, 0, 10, 3, 4, 11, 11);
  printf("test case: ONE vertical, NO intersect, X is inside domain, y is outside\n");
  testIntersect (0, 0, 0, 10, -1, 11, 2, 12);
  printf("test case: ONE vertical, YES intersect\n");
  testIntersect (0, 0, 0, 10, -1, 5, 2, 9);
  printf("switched lines 1 and 2\n");
  testIntersect ( -1, 5, 2, 9, 0, 0, 0, 10);
   printf("test case: both vertical, NO intersect\n");
  testIntersect (0, 0, 0, 10, 100, 0, 100, 10);
  printf("test case: both vertical, yes intersect. 1 on top\n");
  testIntersect (0, 5, 0, 10, 0, 2, 0, 8);
  printf("test case: both vertical, yes intersect. 2 on top\n");
  testIntersect ( 0, 2, 0, 8, 0, 5, 0, 10);
  printf("other test cases...\n should all NOT intersect...\n");
 testIntersect ( 2, 1, 2, 8, 0, 2, 4, -2);
 testIntersect (  0, 2, 4, -2, 2, 1, 2, 8);
testIntersect (   2, 0, 2, 8, -1, 1, 4, -1);
  printf("other test cases...\n should all YES intersect...\n");
   testIntersect (  -1, 1, 5, -1, 2, 0, 2, 8);
   testIntersect (   2, 0, 2, 8, -1, 1, 5, -1);
  //testIntersect (0, 0, 0, 10, 0, 3, 0, 7);
  //testIntersect (0, 0, 0, 10, 0, 30, 0, 70);
  //testIntersect(0, 0, 0, 100,-10, 50, 20, 50);

  return 0;
}
