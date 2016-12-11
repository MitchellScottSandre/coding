#include <stdio.h>
#include "powers.h"

int main(void){
	int num = 3;
	printf("%d ^ 4 = %d\n", num, quartic(num) );//81
	num = 2;
	printf("%d ^ 5 = %d\n", num, quintic(num) );//32

return 0;
}
