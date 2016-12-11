#include <stdio.h>

int main(void){
	double x = 5.0/6.0; // 5/6 - 3/6 == 2/6 == 1/3, but wont because of error
	double y = 1.0 / 2.0;
	double z = 1.0/3.0;
	if (x - y == z) printf("Yes\n");
	else printf("No\n");
	printf("%g\n", (x - y) - z);
	printf("Trying integer divided by a zdoublez so 5 / 6.0 = %g\n", 5/6.0);
return 0;
}
