#include <stdbool.h>
#include <math.h>
#include <stdio.h>
void swap(double *p, double *q) {
    double temp = *p;
    *p = *q;
    *q = temp;
}

double verticalFunction( double startY, double iteration, double deltaY){// goes down
    return startY - deltaY * iteration;
}

double function(double m, double x, double startY) {
    return startY + m*x;
}
int intersect( double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4 ) {

    //increment from point 1 to point 2 in line 1....
    double epsilon = 0.001;
    double deltaX = epsilon / 10;
    double verticalX, verticalY1, verticalY2;
    int verticalLine = 0;

    int verticalCounter = 0;
    //vertical line case
    if ( fabs(x2 - x1) < epsilon) { //line 1 is vertical
        //printf("Line 1 is vertical\n");
        verticalX = x2;
//        verticalY1 = y1;
//        verticalY2 = y2;
        verticalCounter++;
        verticalLine = 1;
    }
    if (fabs(x3 - x4) < epsilon) {
        //line 1 is vertical
       // printf("Line 2 is vertical\n");
        if (verticalCounter == 0) {
            verticalX = x3;
//            verticalY1 = y3;
//            verticalY2 = y4;
            verticalCounter++;
            verticalLine = 2;
        } else {//line 1 is vertical , AND line 2 is vertical
            if ( fabs(x1 - x3) > epsilon){
                //different x values
                return 0;
            } else {//same x values
                //put y1 on top of y2, put y3 on top of y4
                if (y2 > y1) swap (&y1, &y2);
                if (y4 > y3) swap (&y3, &y4);
                if (y3 <= y1 && y3 >= y2) return 1;
                if (y4 <= y1 && y4 >= y2) return 1;
            }
        }

    }

    if (verticalCounter == 1){//only ONE line is vertica
            //number to equal is vertical X

//
//        if (verticalLine == 1){//line 1 is vertical, line 2 is not vertical
//            //make x3 on left of x4
//            if (x3 > x4) swap (&x3, &x4);
//            if (verticalX > x4 || verticalX < x3) return 0;//vertical line is to the right of the other line, or to left of x3
//            //so x is within correct domain, is y?
//            if (y4 > y3) swap (&y3, &y4); //put y3 on top of y4
//            if (y3 <= verticalY1 && y3 >= verticalY2) return 1;
//            if (y4 <= verticalY1 && y4 >= verticalY2) return 1;
//        } else if (verticalLine == 2){//line 1 is vertical, line 2 is not vertical
//            //make x1 on left of x2
//            if (x1 > x2) swap (&x1, &x2);
//            if (verticalX > x2 || verticalX < x1) return 0;//vertical line is to the right of the other line, or to left of x3
//            //so x is within correct domain, is y?
//            if (y2 > y1) swap (&y2, &y1); //put y1 on top of y2
//            if (y1 <= verticalY1 && y1 >= verticalY2) return 1;
//            if (y2 <= verticalY1 && y2 >= verticalY2) return 1;
//        }
    }


    double m1 = (y2 - y1)/(x2 - x1);
    double m2 = (y4 - y3)/(x4 - x3); // what if it is a vertical line??
    double startXLine1 = x1;
    double startXLine2 = x3;
    double directionLine1 = 1, directionLine2 = 1;
    if (x1 > x2) directionLine1 = -1;
    if (x3 > x4) directionLine2 = -1;
    double xNot1 = startXLine1, xNot2 = startXLine2;

    while ( fabs (xNot1 - x2) > epsilon) { //so iterate for all of x of line 1
        if ( fabs( function(m1, xNot1, y1) - function(m2, xNot2, y3)  ) < epsilon ) return 1;
        xNot1 += deltaX * directionLine1;
        xNot2 += deltaX * directionLine2;
    }



    return 0;
}
