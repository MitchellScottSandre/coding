#include <stdio.h>
#include <stdbool.h>

//is prime
bool isPrime(int num){
	int divisor = 2;
	if (num <= 1) return false;
	while (num / divisor >= divisor){
		if (num % divisor == 0) return false;
		divisor++;
	}
	return true;
}


int GCDFunct(int a, int b){
	int r;
	while (b){
		r = a % b;
		a = b;
		b = r;
	}
	return a;
}

int LCM (int a, int b){
	//6, 5, --> 1, --> 30
	//8, 4, --> 4 ---> 8
	//10, 12 --> 2 --> 60
	int gg = GCDFunct(a, b);
	if (a > b){
		return a / gg * b;
	}
	return b / gg * a;
}


struct book {
	int pages;
	int weight;
	bool awesomeOrNot;
};

typedef struct {
	int weight;
	int maxSpeed;
	int klms;
	double acceleration;
} car;

car forSomeReasonLetsAddSomeCars (car car1, car car2){
	car newCar = {car1.weight + car2.weight, car1.maxSpeed + car2.maxSpeed, car1.klms + car2.klms, car1.acceleration + car2.acceleration};;
	/*newCar.weight = car1.weight + car2.weight;
	newCar.maxSpeed = car1.maxSpeed + car2.maxSpeed;
	newCar.klms = car1.klms + car2.klms;
	newCar.acceleration = car1.acceleration + car2.acceleration;*/
	
	return newCar;
}

int multiplyTwoNums(int x, int y){
	return x * y;
}

int addTwoNums(int x, int y){
	return x + y;
}

int doStuffWithInts(int x, int y, int (*functionPointer)(int, int)){//ouble (*h)(double))
	return functionPointer(x, y);
}

double multiplyDouble(double x){
	return x * 2.2;
}

double divideDouble(double x){
	return x / 1.3;
}

double doStuffWithDoubles(int x, double (*f)(double)){
	return f(x);
}


int main(void){
	printf("%d\n", isPrime(122));
	printf("%d\n", GCDFunct (120, 540));
	//get 2 variables from input, ROWS and COLS, then fill the appropriate 2D array
	int rows, cols, temp;
	printf("LCM OF 10 and 12 is %d\n", LCM(10, 12));

	scanf("%d %d", &rows, &cols);
	int array[rows][cols];



	for (int i = 0; i < rows; i++){
		for (int j = 0; j < cols; j++){
			scanf("%d", &temp);
			array[i][j] = temp;
		}
	}

	//display array
	for (int i = 0; i < rows; i++){
		for (int j = 0; j < cols; j++){
			printf("%d ", array[i][j]);
		}
		printf("\n");
	}

	struct book book1 = {10, 20, false};
	struct book book2 = {.pages = 7, .weight = 2};

	car redCar;
	redCar.weight = 1000;
	redCar.acceleration = 3.21;
	redCar.maxSpeed = 210;
	redCar.klms = 320000;

	car blueCar = {111, 222, 333, 44.21};

	printf("Book 1 Information...\n");
	printf("%d %d %d\n", book1.pages, book1.weight, book1.awesomeOrNot);

	car newCar = forSomeReasonLetsAddSomeCars(redCar, blueCar);

	//print newCar information
	printf("New car information\n");
	printf("%d %d %d %g\n", newCar.weight, newCar.klms, newCar.maxSpeed, newCar.acceleration);

	double z = 4.325;
	printf("%g %f %e\n", z, z, z);

	double p;
	scanf("%lf", &p);
	switch ( (int) p){
		case 0: printf("ha");
		case 1: printf("ra");
		case 2: printf("be\n");
		default: printf("it was an inside job\n");
	}

	int g;
	bool gotGoodInput = false;
	while (gotGoodInput == false){
		printf("Enter number between (inclusive) 0 and 3\n");
		scanf("%d", &g);
		if (g >= 0 && g <= 3) {
			gotGoodInput = true;
			break;
		}
		printf("You done fucked up\n");
	}
	printf("You entered: ");
	switch (g){
		case 0: printf("zero\n");
		break;
		case 1: printf("one\n");
		break;
		case 2: printf("two\n");
		break;
		case 3: printf("three\n");
		break;
	}

	int num1 = 10;
	int num2 = 4;
	printf("mult two ints: %d\n", doStuffWithInts(num1, num2, multiplyTwoNums));
	printf("add two ints: %d\n", doStuffWithInts(num1, num2, addTwoNums));
	double double1 = 4.5;
	printf("mult two doubles: %g\n", doStuffWithDoubles(double1, multiplyDouble) );
	printf("divide two doubles: %g\n", doStuffWithDoubles(double1, divideDouble) );
	return 0;
}
