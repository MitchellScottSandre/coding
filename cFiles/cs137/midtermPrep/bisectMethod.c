#include <stdio.h>
#include <math.h>
double function(double z){
return -(z * z) + 5*z + 22;
}

double fixedPointIteration (double guess, double epsilon, int max_iterations){
int z;
double gx;
for (z = 0; z < max_iterations; z++){
gx = g(guess);
if (fabs (gx - guess) < epsilon) return gx;
guess = gx;
}
}

double horner( double f[], int n, double x){

assert (n > 0);
double y = f[n -1];
int i;
for (i = n - 2; i >= 0; i--){
  y = x * y + f[i];  
}

}

double bisect(double a, double b, double epsilon, int max_iterations){
double m;
double fAtM;



for (int i = 0 ; i < max_iterations; i++){
	m = (a + b) / 2;
	fAtM = function(m);

	if (fabs (fAtM) < epsilon) return m;
	if (fAtM > 0){
		b = m;
	} else {
		a = m;
	}

}
}

int main(void) {

	printf("%g\n", bisect(-10, 10, 0.0001, 1000));
	return 0;
}
