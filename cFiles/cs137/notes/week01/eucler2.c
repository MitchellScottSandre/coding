#include <stdio.h>

int main(void){
int a = 0,b = 0, r = 0;
printf("Eculer Program:\nEnter two numbers:");
scanf("%d%d", &a, &b);

while (b){
	r = a % b;
	a = b;
	b = r;
}

printf("%d\n", a);//displays answer


return 0;
}
