//#include <stdio.h>
//
//void displayArray(int array[]){
//    int i;
//    for (i = 0; i < 9; i++){
//        printf("%d ", array[i]);
//    }
//    printf("\n");
//}

int isPermutation(int array[]) {

    int i;
    int k[9] = {0};
    for (i = 0; i < 9; i++) {
        if (array[i] <= 0) return 0;
        if (array[i] > 9) return 0;
        k[array[i] - 1] = 1;//if a has number 1, k[0] = 1; if array has number 2, k[1] == 1
    }
    for (i = 0; i < 9; i++) {
        if (k[i] == 0) return 0;//did not have all of the numbers from 1 to n
    }
    return 1;
}

int sudoku (int grid[9][9]) {
    int r, c, b, i, z;
    int array[9];
    //check all rows
    for (r = 0 ; r < 9; r++) {
        for (i = 0; i < 9; i++) {
            array[i] = grid[r][i];
        }
        z = isPermutation(array);

        if (z == 0) {
            return 0;
        }
    }
    //check all cols
    for (c = 0 ; c < 9; c++) {
        for (i = 0; i < 9; i++) {
            array[i] = grid[i][c];
        }
        z = isPermutation(array);
        if (z == 0) {
            return 0;
        }
    }
    //check all 3 by 3 boxes
    for ( r = 0; r < 3; r++) { //iterate through the 3 rows of blocks
        for (c = 0; c < 3; c++) { //iterate through the 3 columns of blocks
            for (i = 0; i < 9; i++) {
                array[i] = grid[(r * 3) + (i / 3)][c*3 + (i % 3)];
            }
            //now array is full
            z = isPermutation(array);
           // printf("BLOCK Row: %d, Col: %d, Array is: ", r, c);
          //  displayArray(array);
            if (z == 0) {
                return 0;
            }

        }
    }

    return 1;
}
