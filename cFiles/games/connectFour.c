#include <stdio.h>
#include <stdbool.h>
#include <time.h>

//6 * 7
//1 is X is USER
//0 is O is COMPUTER
//-1 is cannot place here
//-2 is can place here next

//const int USER= 1;
//const int COMP = 0;
//const int EMPTY = -1;
//const int AVAILABLE = -2;

int VERSION = 1;
int DATE = 20160910;

void displayBoard(int numRows, int numCols, int b[numRows][numCols], int width, int height, int uScore, int cScore) {
    //system("cls");
    printf("\n\n");
    printf("User: %d  --------- Computer: %d\n", uScore, cScore);
    int r, c, rr, cc, i, ii;
    char shape;
    for (i = 0; i < numCols ; i++) {
        for (ii = 0; ii < width / 2; ii++) {
            printf(" ");
        }
        printf("%d", i + 1);
        for (ii = width/2 + 1; ii < width; ii++) {
            printf(" ");
        }
    }
    printf("\n");
    for (i = 0; i < numCols * width + 1; i++) {
        printf("_");
    }
    printf("\n|");
    for (r = 0; r < numRows; r++) {

        for (rr = 0; rr < height; rr++) {
            for (c = 0; c < numCols; c++) {
                switch (b[r][c]) {
                case 0:
                    shape = '*';
                    break;
                case 1:
                    shape = 'X';
                    break;
                case -2:
                    shape = ' ';
                    break;
                default:
                    shape = ' ';
                }
                if (rr < height - 1 ) {
                    printf(" ");
                    for (cc = 1; cc < width - 2; cc++) {
                        printf("%c", shape);
                    }
                    printf(" |");
                } else {
                    for (cc = 0; cc < width - 1; cc++) {
                        printf("_");
                    }
                    printf("|");
                }
            }

            
                printf("\n|");
            
            
            
        }
    }


}

//get user input and place that object //have it slide down the board

bool isFull( int col, int numRows, int numCols, int b[numRows][numCols]) {
    int i;

    for (i = 0; i < numRows; i++) {
        if (b[i][col] == -2) { //something can be put here
            return false;
        }
    }
    printf("is full\n");
    return true;
}

void getUserInput( int numCols, int numRows, int b[numRows][numCols]) {
    int c, r;
    while (1 == 1) {
        printf("\nEnter col to drop piece: ");
        scanf("%d", &c);
        if (c < 0 || c > numCols || isFull(c - 1, numRows, numCols, b) == true) {
            printf("Improper input. Try again.\n");
        } else {
            break;
        }

    }
    c--;
    //now search for location to put that piece
    for (r = 0; r < numRows; r++) {
        if (b[r][c] == -2) { //can be put there
            b[r][c] = 1;
            if (r > 0) {
                b[r - 1][c] = -2;//one above it is next available spot
            }
        }
    }
    return;
}

void getCompTurn( int numCols, int numRows, int w, int b[numRows][numCols]) {
    //offensive

    //check two in a row horizontal, defensive



    //defensive and offensive//regarldess of what 3 are in a row i should go there (comp)//
    int r, c, useThisStrategy = -1;;

    //block two horizontally


    int x;
    x = findComputerWin(numCols, numRows, w - 1, b);
    //printf("x is %d\n", x);
    if ( x != -99) {
        if (x > 7000) {
            x-= 7000;
            useThisStrategy = 0; //diagonal from top right to bottom elft
        } else if (x > 5000) {
            x -= 5000;
            useThisStrategy = 1; //diagonal from top left to bottom right
        } else if(x > 4000) {
            x-=4000;
            useThisStrategy = 4; //horizontal, place on right
        } else if(x > 3000) {
            x-=3000;
            useThisStrategy = 2; //horizontal, place on left
        } else if (x > 1000) {
            x-=1000;
            useThisStrategy = 3; //vertical
        }
        r = x/10;
        c = x % 10;
        printf("X %d   R %d    C%d\n", x, r + 1, c + 1);
        switch (useThisStrategy) {
        case 0://top right to bottom left, starts at row 1
            b[r - 1][c + 1] = 0;
            if (r > 1) {
                b[r-2][c+1] = -2;
            }
            return;
            break;//redudant
        case 1: //top left to bottom right
            b[r - 1][c -1] = 0;
            if (r > 1) {
                b[r-2][c-1] = -2;
            }
            return;

        case 3:
            b[r -1][c] = 0;
            if ( r > 1) {
                b[r- 2][c] = -2;
            }
            return;
            break;
        case 2:
            b[r][c - 1] = 0;
            if ( r > 1 ) {
                b[r- 1][c-1] = -2;
            }
            return;
            break;
        case 4:
            b[r][c] = 0;
            if ( r > 1 ) {
                b[r- 1][c] = -2;
            }
            return;
            break;
        }

    }
    ////////////////////////block two horizontally
    for (r = 0; r < numRows; r++) { //check to block on right hand side -------------------RIGHT
        for (c = 1; c < numCols - 2; c++) {
            if (  (b[r][c] == 1 || b[r][c] == 0 ) && b[r][c] == b[r][c + 1] && b[r][c + 2] == -2 && b[r][c-1] == -2 ) {
                b[r][c + 2] = 0;
                if ( r > 1) {
                   /////////////////// printf("AAA %d %d\n", r , c);
                    b[r - 1][c + 2] = -2;

                }
                return;
            }
        }
    }
    //////////////////////////////////////////////////
    x = findWinMinusOneValueInARow( numCols, numRows, w - 1, b);
    ////////////////printf("x is %d\n", x);
    if ( x > -97) {
        if (x > 7000) {
            x-= 7000;
            useThisStrategy = 0; //diagonal from top right to bottom elft
        } else if (x > 5000) {
            x -= 5000;
            useThisStrategy = 1; //diagonal from top left to bottom right
        } else if(x > 4000) {
            x-=4000;
            useThisStrategy = 4; //horizontal, place on right
        } else if(x > 3000) {
            x-=3000;
            useThisStrategy = 2; //horizontal, place on left
        } else if (x > 1000) {
            x-=1000;
            useThisStrategy = 3; //vertical
        }
        r = x/10;
        c = x % 10;
        //////////////printf("X %d   R %d    C%d\n", x, r + 1, c + 1);
        switch (useThisStrategy) {
        case 0://top right to bottom left, starts at row 1
            b[r - 1][c + 1] = 0;
            if (r > 1) {
                b[r-2][c+1] = -2;
            }
            return;
            break;//redudant
        case 1: //top left to bottom right
            b[r - 1][c -1] = 0;
            if (r > 1) {
                b[r-2][c-1] = -2;
            }
            return;

        case 3:
            b[r -1][c] = 0;
            if ( r > 1) {
                b[r- 2][c] = -2;
            }
            return;
            break;
        case 2:
            b[r][c - 1] = 0;
            if ( r > 0 ) {
                b[r- 1][c-1] = -2;
            }
            return;
            break;
        case 4:
            b[r][c] = 0;
            if ( r > 0 ) {
                b[r- 1][c] = -2;
            }
            return;
            break;
        }

    } else if (x == -98){
        return;
    }


    //random

    int i;
    time_t t;
    /* Int#include <time.h>ializes random number generator */
    srand((unsigned) time(&t));
    while (1 == 1) {
        c = rand() % numCols;

        if (!isFull( c, numRows, numCols, b)) { //is not full in that column
            for (i = 0; i < numRows; i++) {
                if (b[i][c] == -2) { //that is currently the available spot
                    b[i][c] = 0; //make it COMP piece
                    if (i > 0) {
                        b[i-1][c] = -2;//one above it is now available
                    }
                    return;
                }
            }
        }

    }

}

int findComputerWin( int numCols, int numRows, int winValueMinusOne, int b[numRows][numCols]) {
//check vertically
    int r, c;
    for (r = 1; r <= numRows - winValueMinusOne; r++) {
        for (c = 0; c < numCols; c++) {
            if (  b[r][c] == 0  && b[r][c] == b[r + 1][c] && b[r][c] == b[r + 2][c] && b[r-1][c] == -2 ) {
                return r*10 + c + 1000;//1000 means vertical
            }
        }
    }
//check horizontally // PUT ON LEFT SIDE
    for (r = 0; r < numRows; r++) {
        for (c = 1; c <= numCols - winValueMinusOne; c++) {
            if (  b[r][c] == 0 && b[r][c] == b[r][c + 1] && b[r][c] == b[r][c + 2] && b[r][c -1] == -2 ) {
                return r*10 + c + 3000;//3000 means horizontal
            }
        }
    }

//check horizontally // PUT ON RIGHT SIDE
    for (r = 0; r < numRows; r++) {
        for (c = 0; c <= numCols - winValueMinusOne; c++) {
            if ( b[r][c] == 0 && b[r][c] == b[r][c + 1] && b[r][c] == b[r][c + 2] && b[r][c + 3] == -2 ) {
                return r*10 + c + 3 + 4000;//4000 means horizontal
            }
        }
    }

//check diagonally
    //slanted left (top is left, bottom is right //first row is row 0
    //start at row 3 go to row 5. start at col3, go to col 6
    for (r = 1; r <=numRows - winValueMinusOne; r++) {

        for (c = 1; c <=numCols - winValueMinusOne; c++) {
            if (b[r][c] == 0  && b[r][c] == b[r + 1][c + 1] && b[r + 1][c + 1] == b[r + 2][c + 2] && b[r -1][c -1] == -2  ) {
                return r*10 + c + 5000;//diagonal slanted top left to bottom right
            }
        }
    }

    for (r = 1; r <=numRows - winValueMinusOne; r++) {
        for (c = 2; c < numCols; c++) {
            if (b[r][c] == 0  && b[r][c] == b[r + 1][c - 1] && b[r + 1][c - 1] == b[r + 2][c - 2] && b[r-1][c + 1] == -2  ) {
                return r*10 + c + 7000;//diagonal slanted top right to bottom left
            }
        }
    }
    return -99;
}
int findWinMinusOneValueInARow( int numCols, int numRows, int winValueMinusOne, int b[numRows][numCols]) {
//check vertically
    int r, c;
    for (r = 1; r <= numRows - winValueMinusOne; r++) {
        for (c = 0; c < numCols; c++) {
            if ( (b[r][c] == 1 || b[r][c] == 0 ) && b[r][c] == b[r + 1][c] && b[r][c] == b[r + 2][c] && b[r-1][c] == -2 ) {
                return r*10 + c + 1000;//1000 means vertical
            }
        }
    }
//check horizontally // PUT ON LEFT SIDE
    for (r = 0; r < numRows; r++) {
        for (c = 1; c <= numCols - winValueMinusOne; c++) {
            if ( (b[r][c] == 1 || b[r][c] == 0 )&& b[r][c] == b[r][c + 1] && b[r][c] == b[r][c + 2] && b[r][c -1] == -2 ) {
                return r*10 + c + 3000;//3000 means horizontal
            }
        }
    }

//check horizontally // PUT ON RIGHT SIDE
//A A A B
    for (r = 0; r < numRows; r++) {
        for (c = 0; c < numCols - winValueMinusOne; c++) {
            if ( (b[r][c] == 1 || b[r][c] == 0 )&& b[r][c] == b[r][c + 1] && b[r][c] == b[r][c + 2] && b[r][c + 3] == -2 ) {
                return r*10 + c + 3 + 4000;//4000 means horizontal
            }
        }
    }

// !A! B A A   ! ! means that is where r c is referrenced from
    for (r = 0; r < numRows; r++) {
        for (c = 0; c < numCols - winValueMinusOne; c++) {
            if ( (b[r][c] == 1 || b[r][c] == 0 )&& b[r][c + 1] == -2 && b[r][c] == b[r][c + 2] && b[r][c + 3] == b[r][c] ) {
                   // printf("----->>>%d\n", r*10 + c + 1);
                return r*10 + c + 1 + 4000;//4000 means horizontal
            }
        }
    }

// !A! A B A   ! ! means that is where r c is referrenced from
    for (r = 0; r < numRows; r++) {
        for (c = 0; c < numCols - winValueMinusOne; c++) {
            if ( (b[r][c] == 1 || b[r][c] == 0 )&& b[r][c + 1] == b[r][c] &&b[r][c + 2] == -2 && b[r][c + 3] == b[r][c] ) {
                    //printf("----->>>%d\n", r*10 + c + 2);
                return r*10 + c + 2 + 4000;//4000 means horizontal
            }
        }
    }



//check diagonally
    //slanted left (top is left, bottom is right //first row is row 0
    //start at row 3 go to row 5. start at col3, go to col 6
    for (r = 1; r <=numRows - winValueMinusOne; r++) {

        for (c = 1; c <=numCols - winValueMinusOne; c++) {//B A A A
            if ((b[r][c] == 1 || b[r][c] == 0 ) && b[r][c] == b[r + 1][c + 1] && b[r + 1][c + 1] == b[r + 2][c + 2] && b[r -1][c -1] == -2  ) {
                return r*10 + c + 5000;//diagonal slanted top left to bottom right
            }
            //A B A A
            if (b[r][c] == -2 && ( b[r- 1][c - 1] == 0 || b[r- 1][c - 1] == 1) && b[r + 1][c + 1] == b[r - 1][c-1] && b[r + 2][c + 2] == b[r -1][c-1] ){
                b[r][c] = 0;
                b[r -1][c] = -2;
                return -98;
            }

            //A A B A

            if ( (b[r][c] == 0 || b[r][c] == 1) && b[r + 1][c+1] == -2 && b[r -1][c - 1] == b[r][c] && b[r + 2][c + 2] == b[r][c]  ){
                b[r + 1][c + 1] = 0;
                b [r][c  + 1] = -2;
                return -98;

            }

            //A A A B

            if ( (b[r][c] == 0 || b[r][c] == 1) && b[r + 2][c + 2] == -2 && b[r - 1][c - 1] == b[r][c] && b[r + 1][c+1] == b[r][c]){
                b [r + 2][c + 2] = 0;
                b[r + 1][c + 2] = -2;
                return -98;

            }
        }
    }

    for (r = 1; r <=numRows - winValueMinusOne; r++) {
        for (c = 2; c < numCols; c++) { // A A A B
            if ((b[r][c] == 1 || b[r][c] == 0 ) && b[r][c] == b[r + 1][c - 1] && b[r + 1][c - 1] == b[r + 2][c - 2] && b[r-1][c + 1] == -2  ) {
                return r*10 + c + 7000;//diagonal slanted top right to bottom left
            }
            //A A B A
            if ((b[r][c] == -2 ) && (b[r - 1][c + 1] == 1 || b[r - 1][c + 1] == 0) && b[r + 1][c - 1] == b[r - 1][c + 1] && b[r + 2][c - 2] == b[r - 1][c + 1] ) {
                b[r][c] = 0;
                b[r - 1][c] = -2;
                return -98;//diagonal slanted top right to bottom left
            }
            //A B AA
            if ((b[r + 1][c - 1] == -2 ) && (b[r][c] == 1 || b[r][c] == 0) && b[r][c] == b[r - 1][c + 1] && b[r][c] == b[r + 2][c - 2] ) {
                b[r + 1][c - 1] = 0;
                b[r ][c - 1] = -2;
                return -98;//diagonal slanted top right to bottom left
            }
            //B A A A
            if (b[r + 2][c - 2] == -2 && (b[r][c] == 1 || b[r][c] == 0) && b[r][c] == b[r + 1][c -1 ] && b[r][c] == b[r - 1][c - 1] ){
                b[r + 2][c - 2] = 0;
                b[r + 1][c - 2] = -2;
                return -98;

            }


        }
    }

    return -99;
}

int checkIfGameOver( int numCols, int numRows, int winValue, int b[numRows][numCols]) {
//check vertically
    int r, c;
    for (r = 0; r <= numRows - winValue; r++) {
        for (c = 0; c < numCols; c++) {
            if ( (b[r][c] == 1 || b[r][c] == 0 ) && b[r][c] == b[r + 1][c] && b[r][c] == b[r + 2][c] && b[r][c] == b[r + 3][c] ) {
                return b[r][c];
            }
        }
    }
//check horizontally
    for (r = 0; r < numRows; r++) {
        for (c = 0; c <= numCols - winValue; c++) {
            if ( (b[r][c] == 1 || b[r][c] == 0 )&& b[r][c] == b[r][c + 1] && b[r][c] == b[r][c + 2] && b[r][c] == b[r][c + 3] ) {
                return b[r][c];
            }
        }
    }

//check diagonally
    //slanted left (top is left, bottom is right //first row is row 0
    //start at row 3 go to row 5. start at col3, go to col 6
    for (r = 0; r <=numRows - winValue; r++) {
        for (c = 0; c <=numCols - winValue; c++) {
            if ((b[r][c] == 1 || b[r][c] == 0 ) && b[r][c] == b[r + 1][c + 1] && b[r + 1][c + 1] == b[r + 2][c + 2] && b[r + 1][c + 1] == b[r + 3][c + 3]  ) {
                return b[r][c];
            }
        }
    }

    for (r = 0; r <=numRows - winValue; r++) {
        for (c = 3; c < numCols; c++) {
            if ((b[r][c] == 1 || b[r][c] == 0 ) && b[r][c] == b[r + 1][c - 1] && b[r + 1][c - 1] == b[r + 2][c - 2] && b[r + 1][c - 1] == b[r + 3][c - 3]  ) {
                return b[r][c];
            }
        }
    }
    return -99;
}


int getGoodInput(int min, int max) {
    int n;
    while (1 == 1) {
        scanf("%d", &n);
        if (n >= min && n <= max) return n;
        printf("Invalid input. Try again. Must be between %d and %d\n", min, max);
    }
}

void startScreen(){
    printf("===============================================================================================================================");
    printf("\n\nWelcome to CONNECT FOUR.\n\nRules: Win by connect 4 of our own pieces in a row. Enjoy!\n\n\n\n\n");
    
    printf("Created by Scott Sandre. \nVERSION : %d \nDATE : %d\n\n", VERSION, DATE);
    printf("===============================================================================================================================");
    printf("\n");
}

void main() {
     int numberRows = 6;//rows
     int numberCols = 10;//cols
    const int xWidth = 8;
    const int yHeight = 4;
    const int startValueNormal = -1;
    const int startValueBottomRow = -2;
    const int IN_A_ROW_WIN_VALUE = 4;
    bool gameOver = false;
    int z, scoreLimit, userScore = 0, computerScore = 0, startGame = 0;

    startScreen();
    printf("\n\nGAME OPTIONS:\n\n");
    printf("-----> SCORE:\nEnter score limit up to which you will play the computer: ");
    scoreLimit = getGoodInput(1, 10);
    printf("-----> ROWS:\nEnter number of rows you want to play with (recommended: 8): ");
    numberRows = getGoodInput(5, 15);

    printf("-----> COLS:\nEnter number of cols you want to play with (recommended: 8): ");
    numberCols = getGoodInput(5, 10);


    int board[numberRows][numberCols];

    bool compStarts = false;

    //have score limit, at end set compStarts = !compStarts
    while (userScore < scoreLimit && computerScore < scoreLimit) {

            printf("\n\nEnter 1 to start the game: ");
            startGame = getGoodInput(1, 1);

        int r, c;
        for (r = 0; r < numberRows - 1; r++) {
            for (c = 0; c < numberCols; c++) {
                board[r][c] = startValueNormal;//set all values to empty
            }
        }

        for (c = 0; c < numberCols; c++) {
            board[numberRows - 1][c] = startValueBottomRow;//set bottom to -2, makes them be able to have things put into them
        }

        if (compStarts == false){
            displayBoard(numberRows, numberCols, board, xWidth, yHeight, userScore, computerScore);
        }
        gameOver = false;
        if (compStarts == true) {
            getCompTurn(numberCols, numberRows, IN_A_ROW_WIN_VALUE, board);
            displayBoard(numberRows, numberCols, board, xWidth, yHeight, userScore, computerScore);
            z = checkIfGameOver ( numberCols, numberRows, IN_A_ROW_WIN_VALUE, board);
//            if (z>= 0) {
//                gameOver = true;
//                compStarts = !compStarts;
//                if (z == 0) {
//                    printf("The computer won!\n\n");
//                    computerScore++;
//                } else {
//                    printf("You won!\n\n");
//                    userScore++;
//                }
//            }

        }
        while (gameOver == false) {
            getUserInput(numberCols, numberRows , board);
            displayBoard(numberRows, numberCols, board, xWidth, yHeight, userScore, computerScore);
            z = checkIfGameOver ( numberCols, numberRows, IN_A_ROW_WIN_VALUE, board);
            if (z>= 0) {
                gameOver = true;
                compStarts = !compStarts;
                if (z == 0) {
                    printf("\n\nThe computer won!\n\n");
                    computerScore++;
                } else {
                    printf("\n\nYou won!\n\n");
                    userScore++;
                }
            }
            if (gameOver == false) {

                getCompTurn(numberCols, numberRows, IN_A_ROW_WIN_VALUE, board);
                displayBoard(numberRows, numberCols, board, xWidth, yHeight, userScore, computerScore);
                z = checkIfGameOver ( numberCols, numberRows, IN_A_ROW_WIN_VALUE, board);
                if (z>= 0) {
                    gameOver = true;
                    compStarts = !compStarts;
                    if (z == 0) {
                        printf("The computer won!\n\n");
                        computerScore++;
                    } else {
                        printf("You won!\n\n");
                        userScore++;
                    }
                }

            }

        }

    }

    printf("Final Score is User: %d vs Computer: %d\n", userScore, computerScore);
    if (userScore == scoreLimit){
        printf("Congrats! You won!\n");
    } else { printf("Oh no! You lost!\n");}
}
