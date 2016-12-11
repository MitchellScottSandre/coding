#include <stdio.h>
#include <assert.h>
#include <math.h>
void numToBase8(int num, int a[], int n){
int counter = 0;	
for (int i = n-1; i >= 0; i--){
		//printf("Num is %d\n", num);
		int x = (int) pow(8.0, i*1.0);
		//printf("x is %d\n", x);
		int r = num / x;
		printf("%d", r);
                num -= r * x ;
		a[counter] = r;
counter++;
	}

}

int main(void){

	int a[4];
	numToBase8(68, a, 4);
	for (int i = 0; i < 4; i++) printf("%d\n", a[i]);


return 0;
}
