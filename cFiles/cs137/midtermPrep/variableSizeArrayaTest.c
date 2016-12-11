#include <stdio.h>

int main(void){

printf("Enter length of array\n");
int l;
scanf("%d", &l);
int array[l];
array = {0};
for (int i = 0; i < l; i++){
printf("%d\n", array[i]);
}
return 0;
}
