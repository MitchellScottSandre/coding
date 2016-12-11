#include <stdio.h>

int main(void){

int c[5] = {0}; //fills it in with 0s
int e[5] = {[3] = 10, [2] = 20};

int i;
for (i = 0 ; i < 5; i++){

printf("c --> %d\n", c[i]);
printf("e --> %d\n", e[i]);
}
polyPrint (rray, sizeof(array)/sizeof(array[0]));
return 0;
}
