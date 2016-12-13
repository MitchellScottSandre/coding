#include <stdio.h>
#include <assert.h>

double horner( double f[], int n, double x){

assert (n > 0);
double y = f[n -1];
int i;
for (i = n - 2; i >= 0; i--){
  y = x * y + f[i];  
}

}


int main(void){
double funct1 = {2, 9, 4, 3};
printf("%g\n", horner (funct1, 0, 0 ));
printf("%g\n", horner (funct1, sizeof(f)/sizeof(f[0]), 1));
printf("%g\n", horner (f, sizeof(f)/sizeof(f[0]); -1);

return 0;
}
