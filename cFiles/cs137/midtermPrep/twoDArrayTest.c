#include <stdio.h>

int main(void){

printf("Enter number of ROWS and then COLS of 2d array\n");
int rows, cols;
scanf("%d %d", &rows, &cols);
int arrayInfo[rows][cols];

for (int i = 0; i < rows; i++){
	for (int j = 0; j < cols; j++){
		arrayInfo[i][j] = 0;
		printf("%d", arrayInfo[i][j]);
	}
printf("\n");
}


return 0;
}
