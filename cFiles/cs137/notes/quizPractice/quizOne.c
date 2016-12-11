#include <stdio.h>
#include <stdbool.h>

int main(void){
int a = 5, b = 10, c = 2;

int x = ++a;

printf("x should be 6. a should be 6. %d %d\n", x, a);

int z = b++;

printf("z should be 10, b should then be 11, %d %d\n", z, b);

bool one = false;
bool two = true;
if (!one&&two == true){
printf("true!\n");
} else {

printf("false\n");
}

int h = 5;
printf(" This is h : %d\n", ++h * ++h);
return 0;
}
