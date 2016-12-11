#include <stdio.h>
#include <stdbool.h>


bool leap (int year){
if (year % 400 == 0) return true;
else if (year % 100 == 0) return false;
else if (year % 4 == 0) return true;
else return false;
}


int main(void){
printf("Enter year: ");
int x;
scanf("%d", &x);

if (x < 1753){
	printf("%d is before the eyar 1753 and doesn't work...\n", x);
} else {
	printf("%d is %sa leap year\n", x, !leap(x) ?  "not " : "");
}


return 0;
}
