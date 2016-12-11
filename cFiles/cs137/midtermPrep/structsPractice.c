#include <stdio.h>

int main(void){

struct car {

	int currentSpeed;
	int currentXDistance;
	int weight;
};

typedef struct {

	int speed;
	int xLoc;
	int weight;
} car2;

struct car redCar = {0, 0, 0};
printf("%d\n", redCar.currentSpeed);

car2 blueCar;
blueCar.weight = 55;
printf("%d\n", blueCar.weight);
return 0;
}
