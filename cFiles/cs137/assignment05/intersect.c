#include <stdbool.h>
#include <math.h>
#include <stdio.h>
void swap(double *p, double *q) {
    double temp = *p;
    *p = *q;
    *q = temp;
}

double function(double m, double x, double startY) {
    return startY + m*x;
}

int intersect( double x1, double y1, double x2, double y2, double x3, double y3, double x4, double y4 ) {
    double epsilon = 0.00001;
    //create equations of lines, see if they intersect. if they do intersect, see if it is with each of their domains and ranges
    //put x1 on left of x2
    bool line1Vertical = false;
    bool line2Vertical = false;
    double m1, m2;
    double line_1_y, line_1_x, line_1_b;
    double line_2_y, line_2_x, line_2_b;
    int verticalCounter = 0;
    //now see if there are any vertical lines
    if ( fabs(x1 - x2) < epsilon) {
        line1Vertical = true;
        verticalCounter++;
    }
    if ( fabs(x3 - x4) < epsilon) {
        line2Vertical = true;
        verticalCounter += 2;
    }
    if (line1Vertical == false ) { //calculate line 1 slope
        m1 = (y2 - y1) / (x2 - x1);
        //printf("\nline 1 slope is %g\n", m1);
    }
    if (line2Vertical == false ) { //calculate line 1 slope
        m2 = (y4 - y3) / (x4 - x3);
        //printf("line 2 slope is %g\n", m2);
    }

    if (verticalCounter == 0) {//===========TEST CASE = none are vertical lines
        line_1_b = y1 - x1*m1;
        line_2_b = y3 - x3*m2;
        //printf("LINE ONE: y = %gx + %g....... LINE TWO: y = %gx + %g\n", m1, line_1_b, m2, line_2_b);
        //now solve y1 = m1 + b1 ==== b2 + m2 = y2
        //y = 3x - 3   and y = 2.3x + 4
        double leftSide = m1 - m2;//3x - 2.3x = 0.7x ====== 7
        double rightSide = line_2_b - line_1_b; //4 -(-30 /
        //======================creates leftX, rightX, topY, bottom Y==========
        double leftx1 = x1, rightx1 = x2, topy1 = y1, bottomy1 = y2;
        double lefty_line1 = y1;
        if (x1 > x2) {
            leftx1 = x2;
            rightx1 = x1;
            lefty_line1 = y2;
        }
        if (y2 > y1) {
            topy1 = y2, bottomy1 = y1;
        }

        double leftx2 = x3, rightx2 = x4, topy2 = y3, bottomy2 = y4;
        if (x3 > x4) {
            leftx2 = x4;
            rightx2 = x3;
        }
        if (y4 > y3) {
            topy2 = y4, bottomy2 = y3;
        }
        //======================end of creates leftX, rightX, topY, bottom Y==========

        if (leftSide != 0) {//==========================================TEST CASE = not parallel (different slopes)
            double xIntercept = rightSide / leftSide;
            double yIntercept = m1 * xIntercept + line_1_b;
            //printf("\nintercept is at (%g, %g)\n", xIntercept, yIntercept);
            //now, is this intercept part of the two domains and ranges??

            if (xIntercept >= leftx1 && xIntercept <= rightx1 && xIntercept >= leftx2 && xIntercept <= rightx2) {//within both lines' domains
                if (yIntercept >= bottomy1 && yIntercept <= topy1 && yIntercept >= bottomy2 && yIntercept <= topy2) {//within both lines/ ranges
                    return 1; // within both
                }
                return 0;//not within both ranges
            }
            return 0;//not within both domains

        } else {//======================================================TEST CASE = are parallel and not vertical
            //printf("are parallel.\n"); //must have the same y intercept
            if (fabs (line_1_b - line_2_b) > epsilon) return 0;//don't have same y intercept
            //some point on 1 is either the bottom point on 2 or the top point on 2
            double startXnot = leftx1;
            double xNot = startXnot;
            double deltaX = epsilon / 5;
            double f_at_xnot;
            while (xNot < rightx1) {
                f_at_xnot = function(m1, xNot, lefty_line1 );
                if ( (fabs (xNot - x3) < epsilon && fabs(f_at_xnot - y3) < epsilon) ||   (fabs (xNot - x4) < epsilon && fabs(f_at_xnot - y4) < epsilon) ) {
                    return 1;//true, intersect
                }
                xNot += deltaX;
            }

            return 0;
        }
    } else {//1 or 2 are vertical
        double otherLineLeftx, otherLineRightx, otherLineTopY, otherLineBottomY, verticalLineX, verticalLineTopY, verticalLineBottomY, otherLineLeftY, slope;
        double b_value;
        if (verticalCounter == 1) { //only line 1 is vertical
            otherLineLeftx = x3;
            otherLineRightx = x4;
            otherLineTopY = y3;
            otherLineBottomY = y4;
            otherLineLeftY = y3;
            verticalLineX = x1;
            verticalLineTopY = y1;
            verticalLineBottomY = y2;
            b_value = y3 - x3*m2;
            slope = m2;
            if (y2 > y1) {//vertical line
                verticalLineTopY = y2;
                verticalLineBottomY = y1;
            }
            if (x3 > x4) {//other line
                otherLineLeftx = x4;
                otherLineRightx = x3;
                otherLineLeftY = y4;
            }
            if (y4 > y3) {
                otherLineTopY = y4, otherLineBottomY = y3;
            }

            //printf("only line 1 is vertical\n");
            //is the second line, at x = x of first line, in range of first line??

        } else if (verticalCounter == 2) { //only line 2 is vertical, line one is normal
            //printf("only line 2 is vertical\n");
            otherLineLeftx = x1;
            otherLineRightx = x2;
            otherLineTopY = y1;
            otherLineBottomY = y2;
            otherLineLeftY = y1;
            verticalLineX = x3;
            verticalLineTopY = y3;
            verticalLineBottomY = y4;
            b_value = y1 - x1*m1;
            slope = m1;
            if (y4 > y3) {//vertical line
                verticalLineTopY = y4;
                verticalLineBottomY = y3;
            }
            if (x1 > x2) {//other line
                otherLineLeftx = x2;
                otherLineRightx = x1;
                otherLineLeftY = y2;
            }
            if (y2 > y1) {
                otherLineTopY = y2, otherLineBottomY = y1;
            }
        }
        if (verticalCounter < 3) { //so line 1 or line 2 are vertical, not BOTH
            //case == other line is outside domain of vertical line
            if (otherLineLeftx > verticalLineX || otherLineRightx < verticalLineX) {
                //printf("result --> x outside domain\n");
                return 0;
            }

            if (otherLineTopY < verticalLineBottomY || otherLineBottomY > verticalLineTopY) {
                //printf("result --> y is outside range");
                return 0;
            }
            //so, does the other line have a y value in the range of the vertical line, at that x location
            double interceptY = slope*verticalLineX + b_value;
            if (interceptY >= verticalLineBottomY && interceptY <= verticalLineTopY) return 1;
//            double startXnot = otherLineLeftx;
//            double xNot = startXnot;
//            double deltaX = epsilon / 5;
//            double f_at_xnot;
//
//            while (xNot < verticalLineX + 0.5) {//go up to the vertical line at that x value
//                f_at_xnot = function(slope, xNot, otherLineLeftY );
//                if ( fabs (xNot - verticalLineX) < epsilon && f_at_xnot >= verticalLineBottomY && f_at_xnot <= verticalLineTopY ) {
//                    //printf("result ---> intersect of vertical line and non vertical line\n");
//                    return 1;//true, intersect
//                }
//                xNot += deltaX;
//            }

        }
        //BOTH ARE VERTICAL
        //printf("both are vertical\n");
        double line1X = x1;
        double line2x = x3;
        if (fabs(x1 - x3) > epsilon){
            //printf("vertical. not same x values. do not intersect.\n");
            return 0;
        }
        double line1TopY = y1, line1BottomY = y2, line2TopY = y3, line2BottomY = y4;
        if (y2 > y1){
            line1TopY = y2;
            line1BottomY = y1;
        }
        if (y4 > y3){
            line2TopY = y4;
            line2BottomY = y3;
        }
        //case 1. line 1 is on top and they intersect
        if (line1BottomY <= line2TopY) return 1;
        //case 2. line 2 is on top and they intersect
        if (line2BottomY <= line1TopY) return 1;

    }

    return 0;
}




//            double deltaX = epsilon / 5;
//            double startXLine1 = x1;
//            double startXLine2 = x3;
//            double directionLine1 = 1, directionLine2 = 1;
//            if (x1 > x2) directionLine1 = -1;
//            if (x3 > x4) directionLine2 = -1;
//           // double xNot1 = startXLine1, xNot2 = startXLine2;
//           double xNot1 = 0, xNot2 = 0;
//           double f_at_xnot1, f_at_xnot2;
//           double domain1 = fabs(x1 - x2);
//           double domain2 = fabs(x3 - x4);
//            printf("Start xnot1 is %g and xnot2 is %g\n", xNot1, xNot2);
//            printf("Start f ox xnot1 is %g....f of xnot2 is %g\n", function(m1, xNot1, y1), function(m2, xNot2, y3));
//            while ( fabs(xNot1) < domain1 ) { //so iterate for all of x of line 1
//                //printf("%g.....%g\n",function(m1, xNot1, y1), function(m2, xNot2, y3))  ;
//                f_at_xnot1 = function(m1, xNot1, y1);
//                while ( fabs(xNot2) < domain2){//iterate through all a of line 2
//                    f_at_xnot2 = function(m2, xNot2, y3);
//                    if ( fabs (f_at_xnot1 - f_at_xnot2) < epsilon && fabs(startXLine1 - startXLine2) < epsilon) return 1;
//                    xNot2 += deltaX * directionLine2;
//                }
//
//                xNot1 += deltaX * directionLine1;
//                xNot2 = 0;
//                startXLine1 += xNot1;
//                startXLine2 += xNot2;
//                //printf("xnot1 and xnot2 are %g and %g\n", xNot1, xNot2);
//            }
//            printf("after xnot1 is %g and xnot2 is %g\n", xNot1, xNot2);
//            printf("after f ox xnot1 is %g....f of xnot2 is %g\n", function(m1, xNot1, y1), function(m2, xNot2, y3));
//


//line 1 is slightly below line 2, line                     TEST CASE = parallel, line 1 below line 2, intersect
//1 is on left, line 2 is on right

//if (rightx1 >= leftx2 && topy1 >= bottomy2) return 1;

//or, line 2 is slightly below line 1, line 2 is on
//left, line 1 is on right
//if (rightx2 >= leftx1 && topy2 >= bottomy1) return 1;     //TEST CASE = parallel, line 2 below line 1, intersect

//lines are parallel, but don't intersect!
//printf("parallel, no intersect");


//    if (x1 > x2){
//            swap(&x1, &x2);
//    }
//    //put x3 on left of x4
//    if (x3 > x4){
//            swap(&x3, &x4);
//    }
//    //put y1 on top of y2
//    if (y2 > y1){
//            swap(&y2, &y1);
//    }
//    //put y3 on top of y4
//    if (y4 > y3){
//            swap(&y3, &y4);
//    }

