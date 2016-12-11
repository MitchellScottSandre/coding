#include <stdio.h>
#include "sf.h"
//#include "sf.c"

int main(){
    struct card hand1[] = {{4,'s'}, {9,'s'},{12,'c'},{11,'s'},{8,'s'}, {8,'s'},
                           {6,'d'}, {3,'d'},{7,'s'},{10,'s'},{12,'d'}};
    struct card hand2[] = {{8,'c'}, {2,'h'},{5,'s'},{6,'c'},{1,'s'},
                           {5,'c'}, {4,'d'},{2,'h'},{13,'d'},{1,'d'}};
    struct card hand3[] = {{8,'c'}, {2,'h'},{5,'s'},{6,'c'},{1,'s'},
                           {5,'c'}, {9,'s'},{6,'h'},{13,'s'},{1,'s'}};
    struct card hand4[] = {{6,'c'}, {5,'c'},{4,'c'},{2,'c'},{3,'c'},
                           {5,'c'}, {9,'s'},{6,'h'},{13,'s'},{1,'s'}};
    struct card hand5[] = {{2,'d'}, {2,'d'},{3,'d'},{2,'d'},{5,'d'},
                        {9,'d'}, {2,'d'},{5,'d'},{6,'d'},{4,'d'}};
    struct card hand6[] = {{10,'d'}, {11,'d'},{2,'d'},{2,'d'},{3,'d'},//ace case
                        {4,'d'}, {7,'d'},{1,'d'},{12,'d'},{13,'d'}};
                        struct card hand7[] = {{10,'d'}, {11,'d'},{2,'d'},{2,'d'},{3,'d'},//ace case
                                            {4,'d'}, {7,'d'},{2,'d'},{12,'d'},{13,'d'}};
    printf ("%d\n", straightflush(hand1, 11));
    printf ("%d\n", straightflush(hand2, 10));
    printf ("%d\n", straightflush(hand3, 10));
    printf ("%d\n", straightflush(hand4, 10));
    printf ("%d\n", straightflush(hand5, 10));
    printf ("%d\n", straightflush(hand6, 10));//1
    printf ("%d\n", straightflush(hand7, 10));//0
    return 0;
}
