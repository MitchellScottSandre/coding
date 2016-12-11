//#include "vector.c"
#include "vector.h"

#include <stdio.h>
 int main (void){
 	struct vector *v = vectorCreate();
 	vectorSet(v,10,2);
 	printf("%d\n", vectorLength(v));
 	printf("%d\n", vectorGet(v,10));
 	v = vectorDelete(v);

 }