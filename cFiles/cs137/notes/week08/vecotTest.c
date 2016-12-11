#include <stdio.h>
#include "vector.h"

int main(void){
	struct vector *v  vectorCreate();
	vectorSet(v, 10, 2);
	printf("%d\n", vectorLength(v) );//output 11
	printf("%d\n", vectorGet(v, 10);
	v = vectorDelete(v);//storage will grow by powers of 2, length is index of last set number + 1

}
