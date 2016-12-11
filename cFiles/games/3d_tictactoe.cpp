#include <stdio.h>
#include <stdbool.h>
#include <stdlib.h>
#include <time.h>

//============================================================CHANGE THIS VARIABLE FOR SIZE SCALING=============================
int SIZE = 4;// must be >=3 and <= 7
//============================================================CHANGE THIS VARIABLE FOR SIZE SCALING=============================

//==============================================================VARIABLES=======================================================
int VERSION = 1;
int DATE = 20160910;
int difficulty;
int PIECE_HERE_DOES_NOT_MATTER = -1;
int strategicMoveLocation[3];
int difficultyPercentage;
int VERTICAL_SIZE = SIZE / 3 + 1;
int COMPUTER_PIECE = 0;
int USER_PIECE = 1;
int PLAYER_TWO = 0;
int AVAILABLE_SPOT = 2;
int EMPTY_SPOT = 3;
int GO_HERE = 5;
int X_DISPLAY[3][3] = { {1, 0, 1}, {0, 1, 0}, {1, 0, 1} };// makes x shape in a 3 by 3 grid
int IS_FILLED = 1;
int IS_EMPTY = 0;
int SPACER = SIZE + 3;
int gameBoard [3][3][3];//0 = computer, 1 = user; 2 = available; 3 = empty
//int tempGameBoard [3][3][3];
int gameBoardDisplay [3][3][3][3][3];
int availableSpots[15][3];
int HARD_PERCENTAGE = 95;
int MEDIUM_PERCENTAGE = 75;
int EASY_PERCENTAGE = 60;

bool isAWinner;

char USER_FILLED = 'X';
char COMP_FILLED = 'o';
char COMPUTER_DISPLAY = 'C';
char USER_DISPLAY = 'X';
char TOP = '_';
char FILLED = 'X';
char EMPTY = ' ';

//==============================================================VARIABLES=======================================================


void drawSpacer() {
    for (int i = 0; i < SPACER; i++) {
        printf(" ");
    }
}

void drawLineAcrossTop() {
    printf("  ");
    for (int b = 0; b < 3; b++) {

        for (int c = 0; c < 3; c++) {
            printf (" ");
            for (int c2 = 0; c2 < 3; c2++) {
                printf(" ");
                for (int i = 0; i < SIZE - 2; i++) {
                    printf("%c", TOP);//length 7
                }
                printf(" ");

            }
        }
        drawSpacer();//length 7 //spacer
        printf("   ");//compensator
    }
    printf("\n");
}

void displayBoard(int board[3][3][3]) {
    //===============================================WORDS AT TOP===========================================================
    printf("   ");
    for (int i =0; i < SIZE * 4.5 - 3; i++) { //===========BOTTOM
        printf(" ");
    }
    printf("  BOTTOM");//length 6. 6 / 2 == 3.

    for (int i = 0; i < SIZE * 3 * 3 + SPACER - 3; i++) { //===========MIDDLE
        printf(" ");
    }
    printf("  MIDDLE");//length = 6. 6/2 = 3.

    for (int i = 0; i < SIZE * 3 * 3 + SPACER ; i++) { //===========TOP
        printf(" ");
    }
    printf(" TOP");//length = 6. 6/2 = 3.

    printf("\n ");
    //==================================================DISPLAY COLUMN NUMBERS========================================================
    printf("  ");
    for (int i = 0; i < SIZE * 1.5; i++) {
        printf(" ");
    }
    printf("1");

    for (int j = 0; j < 3; j++) {

        for (int i = 0; i < SIZE * 3; i++) {
            printf(" ");
        }
        printf("2");
        for (int i = 0; i < SIZE * 3; i++) {
            printf(" ");
        }
        printf("3");
        for (int i = 0; i < SIZE * 3; i++) {
            printf(" ");
        }
        if (j == 1) {
            printf("  ");
        }
        if (j != 2) {
            drawSpacer();
            printf("  1");
        }


    }
    printf("\n");

    //==================================================DISPLAY BOARD========================================================

    //A = ascii 65
    char rowLetter = 65;
    char fillChar;
    for (int bigRow = 0; bigRow < 3; bigRow++) {//row ****

        //TOP

        drawLineAcrossTop();

        for (int littleRow = 0; littleRow < 3; littleRow++) {

            for (int i = 0 ; i < VERTICAL_SIZE; i++) {

                for (int b = 0; b < 3; b++) { //board ****

                    if (littleRow == 1 && i == VERTICAL_SIZE / 2) {
                        printf("%c ", rowLetter);

                    } else {
                        printf("  ");
                    }

                    for (int bigC = 0; bigC < 3; bigC++) { //col ****
                        printf ("|");
                        for (int c = 0; c < 3; c++) {
                            if (gameBoardDisplay[b][bigRow][bigC][littleRow][c] == IS_FILLED) {
                                printf(" ");
                                if (gameBoard[b][bigRow][bigC] == USER_PIECE) {
                                    fillChar = USER_FILLED;
                                } else {
                                    fillChar = COMP_FILLED;
                                }
                                //display line in tiniest cell
                                for (int j = 0; j < SIZE - 2; j++) {
                                    printf("%c", fillChar);//FILLED
                                }
                                printf(" ");
                            }
                            if (gameBoardDisplay[b][bigRow][bigC][littleRow][c] == IS_EMPTY) {
                                printf(" ");
                                //display line in tiniest cell
                                for (int j = 0; j < SIZE - 2; j++) {
                                    printf("%c", EMPTY);//EMPTY
                                }
                                printf(" ");
                            }
                        }
                    }
                    printf ("|");
                    drawSpacer();//spacer == length of SIZE + 1
                }
                printf("\n");
            }
        }

        rowLetter++;
    }

    //bottom part of board
    drawLineAcrossTop();

}

void addTurnToBoard(int userOrComp, int a, int b, int c) {
    //make spot above it now available

    gameBoard[a][b][c] = userOrComp;

    if (a < 2) {
        gameBoard[a + 1][b][c] = AVAILABLE_SPOT;
    }

    //b, r/ c
    if (userOrComp == COMPUTER_PIECE) { //computer
        gameBoardDisplay[a][b][c][0][0] = IS_FILLED;
        gameBoardDisplay[a][b][c][0][1] = IS_FILLED;
        gameBoardDisplay[a][b][c][0][2] = IS_FILLED;
        gameBoardDisplay[a][b][c][1][0] = IS_FILLED;
        gameBoardDisplay[a][b][c][1][2] = IS_FILLED;
        gameBoardDisplay[a][b][c][2][0] = IS_FILLED;
        gameBoardDisplay[a][b][c][2][1] = IS_FILLED;
        gameBoardDisplay[a][b][c][2][2] = IS_FILLED;
    } else {
        gameBoardDisplay[a][b][c][0][0] = IS_FILLED;
        gameBoardDisplay[a][b][c][0][2] = IS_FILLED;
        gameBoardDisplay[a][b][c][1][1] = IS_FILLED;
        gameBoardDisplay[a][b][c][2][0] = IS_FILLED;
        gameBoardDisplay[a][b][c][2][2] = IS_FILLED;

    }

}

void playUserTurn(int player1or2) {//what about two player?
    bool goodInput = false;
    int goodCounter = 0, loc1, loc2, choice3, loc3;
    while (goodInput == false ) {
        goodCounter = 0;
        char choice1, choice2;
        printf("\nUSER TURN: \nSelections are of format Board Row Column. B for BOTTOM, M for MIDDLE, T for TOP. Ex: B A 1 . \nEnter move selection: ");
        scanf(" %c", &choice1);
        scanf(" %c", &choice2);
        scanf(" %d", &choice3);

        //do checks of valid INPUT
        if (choice1 == 'B' || choice1 == 'M' || choice1 == 'T' || choice1 == 'b' || choice1 == 'm' || choice1 == 't') {
            goodCounter++;
            switch (choice1) {
            case 'B':
                loc1 = 0;
                break;
            case 'M':
                loc1 = 1;
                break;
            case 'T':
                loc1 = 2;
                break;
            case 'b':
                loc1 = 0;
                break;
            case 'm':
                loc1 = 1;
                break;
            case 't':
                loc1 = 2;
                break;

            }
        }
        if (choice2 == 'A' || choice2 == 'B' || choice2 == 'C' ) {
            goodCounter++;
            loc2 = choice2 - 65;
        }
        if (choice3 == 1 || choice3 == 2 || choice3 == 3) {
            goodCounter++;
            loc3 = choice3 - 1;//0, 1, or 2
        }
        if (goodCounter != 3) {
            printf("Invalid input. Try again.\n");
        } else {
            //else, so far so good
            //do checks of if those spots are available

            if (gameBoard[loc1][loc2][loc3] == AVAILABLE_SPOT) {
                goodInput = true;
                printf("You have entered    : %c %c %d\n", choice1, choice2, choice3);
                //printf("Those locations are : %d %d %d\n", loc1, loc2, loc3);
                break;
            } else {
                printf("Invalid input. That spot is not available. Try again.\n");
            }
        }

    }
    //so now it's a good input...place it
    //gameBoard[loc1][loc2][loc3] = USER_PIECE;
    addTurnToBoard(player1or2, loc1, loc2, loc3);//comp move to 0 0 0

}

void initialize() {
    srand (time(NULL));
    isAWinner = false;


    //place available spots
    for (int r = 0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            gameBoard[0][r][c] = AVAILABLE_SPOT;
            for (int a = 0; a < 3; a++) {
                for (int b = 0; b < 3; b++) {
                    for (int d = 0; d < 3; d++) {
                        gameBoardDisplay[r][c][a][b][d] = IS_EMPTY;//fill in visual display grid
                    }
                }
            }
        }
    }

    //place empty spots
    for (int b = 1; b < 3; b++) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                gameBoard[b][r][c] = EMPTY_SPOT;//change this back to empty spot
            }
        }
    }

}

int determineIfAWinner(int passedBoard[3][3][3], bool actualWinner) { //checks all possible combinations for a winner
    //horizontal, up down  //COLUMNS
    for (int b = 0; b < 3; b++) {
        for (int c = 0; c < 3; c++) {
            if (passedBoard[b][0][c] < 2 && passedBoard[b][0][c] == passedBoard[b][1][c] && passedBoard[b][1][c] == passedBoard[b][2][c]) { //gameBoard[b][0][c] == COMPUTER_PIECE || gameBoard[b][0][c] == USER_PIECE
                //  printf("horizontal up down -- columns -- WINNER IS %d", passedBoard[b][0][c]);
                isAWinner = true && actualWinner;
                return passedBoard[b][0][c];
            }
        }
    }

    //horiztonal, left right
    for (int b = 0; b < 3; b++) {
        for (int r = 0; r < 3; r++) {
            if (passedBoard[b][r][0] < 2 && passedBoard[b][r][0]  == passedBoard[b][r][1]  && passedBoard[b][r][1]  == passedBoard[b][r][2] ) { //gameBoard[b][0][c] == COMPUTER_PIECE || gameBoard[b][0][c] == USER_PIECE
                //   printf("horizontal left right -- ROWS -- WINNER IS %d", passedBoard[b][r][0] );
                isAWinner = true && actualWinner;
                return passedBoard[b][r][0] ;
            }
        }
    }

    //horizontal, / and \

    for (int b = 0 ; b < 3; b++) {
        if (passedBoard[b][0][0] < 2 && passedBoard[b][0][0] == passedBoard[b][1][1] && passedBoard[b][0][0] == passedBoard[b][2][2] ) {
            // printf("horizontal diagonal -- WINNER IS %d\n", passedBoard[b][0][0] );
            isAWinner = true && actualWinner;
            return passedBoard[b][0][0] ;
        }
    }

    for (int b = 0 ; b < 3; b++) {
        if (passedBoard[b][0][2] < 2 && passedBoard[b][0][2] == passedBoard[b][1][1] && passedBoard[b][0][2] == passedBoard[b][2][0] ) {
            // printf("horizontal diagonal -- WINNER IS %d\n", passedBoard[b][0][2] );
            isAWinner = true && actualWinner;
            return passedBoard[b][0][2] ;
        }
    }
    //vertical

    for (int r =0; r < 3; r++) {
        for (int c = 0; c < 3; c++) {
            if (passedBoard[0][r][c] < 2 && passedBoard[0][r][c] == passedBoard[1][r][c] && passedBoard[0][r][c] == passedBoard[2][r][c]) {
                //  printf("vertical -- WINNER IS %d\n", passedBoard[0][r][c] );
                isAWinner = true && actualWinner;
                return passedBoard[0][r][c] ;
            }
        }
    }

    //diagonal, up down (column), bottom at TOP
    for (int c = 0; c < 3; c++) {
        if (passedBoard[0][0][c] < 2 && passedBoard[0][0][c] == passedBoard[1][1][c] && passedBoard[0][0][c] == passedBoard[2][2][c]) {
            //printf("diagonal -- column -- start at top -- WINNER IS %d\n", passedBoard[0][0][c] );
            isAWinner = true && actualWinner;
            return passedBoard[0][0][c] ;
        }
    }

    //diagonal, up down, bottom at BOTTOM
    for (int c = 0; c < 3; c++) {
        if (passedBoard[0][2][c] < 2 && passedBoard[0][2][c] == passedBoard[1][1][c] && passedBoard[0][2][c] == passedBoard[2][0][c]) {
            // printf("diagonal -- column -- start at top -- WINNER IS %d\n", passedBoard[0][2][c]  );
            isAWinner = true && actualWinner;
            return passedBoard[0][2][c]  ;
        }
    }

    //diagonal, left right, bottom at LEFT

    for (int r = 0; r < 3; r++) {
        if (passedBoard[0][r][0] < 2 && passedBoard[0][r][0] == passedBoard[1][r][1] && passedBoard[0][r][0] == passedBoard[2][r][2]) {
            // printf("diagonal -- row -- start at left -- WINNER IS %d\n", passedBoard[0][r][0] );
            isAWinner = true && actualWinner;
            return passedBoard[0][r][0] ;
        }
    }
    //diagonal, left right, bottom at RIGHT
    for (int r = 0; r < 3; r++) {
        if (passedBoard[0][r][2] < 2 && passedBoard[0][r][2] == passedBoard[1][r][1] && passedBoard[0][r][2] == passedBoard[2][r][0]) {
            //printf("diagonal -- row -- start at right -- WINNER IS %d\n", passedBoard[0][r][2] );
            isAWinner = true && actualWinner;
            return passedBoard[0][r][2] ;
        }
    }

    //diagonal diagonal
    //top left to bottom right (at top)
    if (passedBoard[0][0][0] < 2 && passedBoard[0][0][0] == passedBoard[1][1][1] && passedBoard[0][0][0] == passedBoard[2][2][2]) {
        //printf("diagonal -- diagonal -- top left to bottom right at TOP WINNER IS %d\n", passedBoard[0][0][0] );
        isAWinner = true && actualWinner;
        return passedBoard[0][0][0] ;
    }

    //top right to bottom left (at top)
    if (passedBoard[0][0][2] < 2 && passedBoard[0][0][2] == passedBoard[1][1][1] && passedBoard[0][0][2] == passedBoard[2][2][0]) {
        // printf("diagonal -- diagonal -- top right to bottom left (at top) WINNER IS %d\n", passedBoard[0][0][2] );
        isAWinner = true && actualWinner;
        return passedBoard[0][0][2] ;
    }

    //bottom left to top right at TOP
    if (passedBoard[0][2][0] < 2 && passedBoard[0][2][0] == passedBoard[1][1][1] && passedBoard[0][2][0] == passedBoard[2][0][2]) {
        //printf("diagonal -- diagonal -- bottom left to top right at TOP  WINNER IS %d\n", passedBoard[0][2][0] );
        isAWinner = true && actualWinner;
        return passedBoard[0][2][0] ;
    }

    //bottom right to top LEFT at TOP
    if (passedBoard[0][2][2] < 2 && passedBoard[0][2][2] == passedBoard[1][1][1] && passedBoard[0][2][2] == passedBoard[2][0][0]) {
        //printf("diagonal -- diagonal -- bottom right to top LEFT at TOP  WINNER IS %d\n", passedBoard[0][2][2] );
        isAWinner = true && actualWinner;
        return passedBoard[0][2][2] ;
    }
}

void randomTurn() {
    //printf("RANDOM TURN\n");
    //pick level, row, col
    //try it out (available)
    bool foundSpot = false;
    int a, b,c;
    while (foundSpot == false) {
        a = rand() % 3;
        b = rand() % 3;
        c = rand() % 3;
        // printf("a b c is %d %d %d\n", a, b, c);
        if (gameBoard[a][b][c] == AVAILABLE_SPOT) {
            foundSpot = true;
            break;
        }
    }
    addTurnToBoard(COMPUTER_PIECE, a, b, c);
    //gameBoard[a][b][c] = COMPUTER_PIECE;
}

void getAllAvailableSpots() {
    //int availableSpots[15][3];
    for (int i = 0; i < 15; i++) {
        availableSpots[i][0] = -1;
        availableSpots[i][1] = -1;
        availableSpots[i][2] = -1;
    }


    int counter = 0;
    for (int b = 0; b < 3; b++) {
        for (int r = 0; r < 3; r++) {
            for (int c = 0; c < 3; c++) {
                if (gameBoard[b][r][c] == AVAILABLE_SPOT) {
                    availableSpots[counter][0] = b;
                    availableSpots[counter][1] = r;
                    availableSpots[counter][2] = c;
                    counter++;
                }
            }
        }
    }

    //display available spots

//    for (int i = 0; i < 15; i++) {
//        printf ("%d %d %d\n", availableSpots[i][0], availableSpots[i][1], availableSpots[i][2] );
//    }


}



//void checkForHorizontalStrategyMoveAnyOrientationOrFlipped(int lookForThisScenario[3][3], int meetThisNumberToMatch, int goHereR, int goHereC ){//returns the LEVEL to play at 0 1 or 2, already know the go here
////searches for that scenario on the board in any orientation or flipped
//    printf("calledStrategy...\n");
//    //strategicMoveLocation[3]
//    int counter = 0;
//    bool foundMove = false;
//    for (int level = 0; level < 3; level++){
//        counter = 0;
//        //compare lookforthisscenario to that level of the gameBoard
//        for (int r = 0; r < 3; r++){
//            for (int c = 0; c < 3; c++){
//                if (lookForThisScenario[r][c] != PIECE_HERE_DOES_NOT_MATTER && gameBoard[level][r][c] == lookForThisScenario[r][c]){
//                    counter++;
//                }
//            }
//        }
//
//        if (counter == meetThisNumberToMatch){//we have found the scenario
//            strategicMoveLocation[0] = level;
//            strategicMoveLocation[1] = goHereR;
//            strategicMoveLocation[2] = goHereC;//
//            printf("strategic move location is %d %d %d\n\n", level, goHereR, goHereC);
//            foundMove = true;
//            break;
//        }
//    }
//
//    if (foundMove == false){
//        //rotate
////        printf("rotating...\n");
////        lookForThisScenario[0][0] = strategicMoveLocation[2][0];
////        lookForThisScenario[0][1] = strategicMoveLocation[1][0];
////        lookForThisScenario[0][2] = strategicMoveLocation[0][0];
////        lookForThisScenario[1][2] = strategicMoveLocation[0][1];
////        lookForThisScenario[2][2] = strategicMoveLocation[0][2];
////        lookForThisScenario[2][1] = strategicMoveLocation[1][2];
////        lookForThisScenario[2][0] = strategicMoveLocation[2][2];
////        lookForThisScenario[1][0] = strategicMoveLocation[2][1];
////        int j, k;
////        checkForHorizontalStrategyMoveAnyOrientationOrFlipped(lookForThisScenario, meetThisNumberToMatch, , );
//    }
//
//
//
//
////}

void computerPlayTurn() {

    getAllAvailableSpots();

//========================================================OFFENSIVE MOVE TO WIN==========================================
    int index = 0 ;
    int a, b, c;
    int suggestedMove[3];
    suggestedMove[0] = -1;
    suggestedMove[1] = -1;
    suggestedMove[2] = -1;


    bool foundWinningMove = false;
    bool foundDefensiveMove = false;
    bool foundStrategicMove = false;
    while (availableSpots[index][0] != -1) {
        //tempGameBoard = gameBoard;
        a = availableSpots[index][0];
        b = availableSpots[index][1];
        c = availableSpots[index][2];
        gameBoard[a][b][c] = COMPUTER_PIECE;
        if (determineIfAWinner(gameBoard, true) == COMPUTER_PIECE) {
            //printf("offensive. winner. that winning location is: %d %d %d\n", a, b, c);
            //addTurnToBoard(COMPUTER_PIECE, a, b, c);
            suggestedMove[0] = a;
            suggestedMove[1] = b;
            suggestedMove[2] = c;
            foundWinningMove = true;
            //printf("FOUND WINNING MOVE\n");

            gameBoard[a][b][c] = AVAILABLE_SPOT;// COMMENT OUT BELOW/delete it
            break;
        } else {
            gameBoard[a][b][c] = AVAILABLE_SPOT; //re set that spot
        }
        index++;
    }

    if (foundWinningMove == false) {
        //printf("DID NOT FIND WINNING MOVE\n");
//=========================================================DEFENSIVE MOVE TO BLOCK===========================================

        index = 0 ;
        while (availableSpots[index][0] != -1) {
            //tempGameBoard = gameBoard;
            a = availableSpots[index][0];
            b = availableSpots[index][1];
            c = availableSpots[index][2];
            gameBoard[a][b][c] = USER_PIECE;
            if (determineIfAWinner(gameBoard, false) == USER_PIECE) {
                printf("defensive. must block the user.\n");
                //addTurnToBoard(COMPUTER_PIECE, a, b, c);
                suggestedMove[0] = a;
                suggestedMove[1] = b;
                suggestedMove[2] = c;
                foundDefensiveMove = true;
                //printf("FOUND !!!!BLOCKING!!! MOVE\n");
                //gameBoard[a][b][c] = COMPUTER_PIECE;

                gameBoard[a][b][c] = AVAILABLE_SPOT;
                break;
            } else {
                gameBoard[a][b][c] = AVAILABLE_SPOT; //re set that spot
            }
            index++;
        }

        if (foundDefensiveMove == false) {//strategic move to set up a win
            //=========================================================NO ONE CAN WIN RIGHT NOW, STRATEGIC MOVE=================================
            //int strategicMoveLocation[3];
//            int compLoc1[2], compLoc2[2], mustBeFreeLoc1[2], mustBeFreeLoc2[2], goHere[2];
//
//            //===========================================================strategy move #1=======================================================
////            compLoc1[0] = 0; //{0, 1}                   // o x o
////            compLoc1[1] = 1;                            // f f
////            compLoc2[0] = 0; //{0, 2}                   // g
////            compLoc2[1] = 2;
////            mustBeFreeLoc1[0] = 1; //{1, 0}
////            mustBeFreeLoc1[1] = 0;
////            mustBeFreeLoc2[0] = 0; //{0, 1}
////            mustBeFreeLoc2[1] = 1;
////            goHere[0] = 2; //{2, 0}
////            goHere[1] = 0;
//
//            int lookForThisScenario[3][3];
//
//            for (int r = 0; r < 3; r++){
//                for (int c = 0 ; c < 3; c++){
//                    lookForThisScenario[r][c] = PIECE_HERE_DOES_NOT_MATTER;//AVAILABLE_SPOT ??
//                }
//            }
//
//            lookForThisScenario[0][0] = COMPUTER_PIECE;     // o x o        //1
//            lookForThisScenario[0][2] = COMPUTER_PIECE;     // f f          //2
//            lookForThisScenario[0][1] = USER_PIECE;         // g            //3
//            lookForThisScenario[1][0] = AVAILABLE_SPOT;                     //4
//            lookForThisScenario[1][1] = AVAILABLE_SPOT;                     //5 pieces must match
//            lookForThisScenario[2][0] = GO_HERE;
//
//
//            checkForHorizontalStrategyMoveAnyOrientationOrFlipped(  lookForThisScenario, 5, 2, 0 );
//
//
//
//            //strategic move to set up defence
        }

    }

    //================================DIFFICULTY SECTION: percent chance it makes that move
    //printf("suggested move is: %d %d %d\n", suggestedMove[0], suggestedMove[1],suggestedMove[2]);
    if (suggestedMove[0] != -1) { // right here======================================================add difficulty logic here
        //printf("doing calculation to see if comp will use suggested move\n");

        int x = rand() % 100;
       // printf("x is %d. must be less than %d to do move...\n", x, difficultyPercentage);
        if (x <= difficultyPercentage) {
            addTurnToBoard(COMPUTER_PIECE, suggestedMove[0], suggestedMove[1], suggestedMove[2]);
        } else {
           // printf("NOT doing suggested move\n");
            randomTurn();
        }

    } else {//================================RANDOM MOVE LAST RESORT =======================
        //printf("doing random turn.... \n");
        randomTurn();
    }

    displayBoard(gameBoard);
}


void startScreen() {
    for (int i = 0; i < SIZE * 27 + SPACER * 4 + 5; i++){
        printf("=");
    }

    printf("\n\nWelcome to 3D TIC TAC TOE.\n\nRules: Win by placing 3 pieces in a row in any direction: vertical, horizontal, diagonal, through any of the 3 levels.");
    printf("\nYou can only place a piece on a higher level if there is a piece below that cell already. Enjoy! \n\n\n\n\n");
    printf("Created by Scott Sandre. \nVERSION : %d \nDATE : %d\n\n", VERSION, DATE);

    for (int i = 0; i < SIZE * 27 + SPACER * 4 + 5; i++){
        printf("=");
    }

    printf("\n\n");

}

int main() {

    startScreen();
    int winner, upToScore, userScore = 0, compScore = 0, round = 1, singlePlayer, turnToStart;

    bool gotGoodInput = false,  doOnce = true;

    printf("GAME OPTIONS:\n\n-----> SCORE LIMIT\n\n");
    //=========================================================GET SCORE LIMIT FROM USER==========================================
    while (gotGoodInput == false) {
        printf("Please Enter the score you want to play up to (less than 10): ");
        scanf("%d", &upToScore);
        if (upToScore > 0 && upToScore < 10 ) {
            gotGoodInput = true;
        } else {
            printf("Try again.\n");
        }
    }

    //========================================================PLAY AGAINST COMPUTER OR ANOTHER PLAYER=============================
    printf("\n\n-----> NUMBER OF PLAYERS:\n\n");
    gotGoodInput = false;
    while (gotGoodInput == false) {
        printf("Enter 1 for single player against computer, or 2 for two player gameplay: ");
        scanf("%d", &singlePlayer);
        if (singlePlayer== 1 || singlePlayer == 2) {
            gotGoodInput = true;
        } else {
            printf("Try again.\n");
        }
    }

    //========================================================DIFFICULTY=======================================
    if (singlePlayer == 1) {
        gotGoodInput = false;
        printf("\n\n-----> SELECT DIFFICULTY:\n\n");

        while (gotGoodInput == false) {
            printf("Percent chance of making the perfect move...\nEasy: %d, Medium: %d, Hard: %d\n", EASY_PERCENTAGE, MEDIUM_PERCENTAGE, HARD_PERCENTAGE);
            printf("Enter 1 for Easy, 2 for Medium, 3 for Hard: ");
            scanf("%d", &difficulty);
            if (difficulty > 0 && difficulty < 4) {
                gotGoodInput = true;
                switch (difficulty) {
                case 1:
                    difficultyPercentage = EASY_PERCENTAGE;
                    break;
                case 2:
                    difficultyPercentage = MEDIUM_PERCENTAGE;
                    break;
                case 3:
                    difficultyPercentage = HARD_PERCENTAGE;
                    break;
                    //printf("difficulty percentage is: %d\n", difficultyPercentage);
                }
            } else {
                printf("Try again.\n");
            }
        }
        initialize();
        displayBoard(gameBoard);
        //============================================================PLAY GAME ONE PLAYER=====================================
        while (compScore < upToScore && userScore < upToScore) {
            initialize();
            printf("\n\n");
            for (int i = 0; i < SIZE * 13.5 + SPACER + 5; i++) {
                printf("=");
            }
            printf("ROUND # %d", round);
            for (int i = 0; i < SIZE * 13.5 + SPACER + 3; i++) {
                printf("=");
            }
            printf("\n\n");

            // displayBoard(gameBoard);
            round++;

            //who goes first, alternate afterwards
            if (doOnce == true) {
                doOnce = false;
                int firstTurn = rand() % 2;
                if (firstTurn == 0) { //computer starts
                    printf("Computer goes first for the game...\n");
                    turnToStart = 0;//THIS GETS CHANGED BELOW TO GO BACK TO 1
                    // computerPlayTurn();
                } else {
                    printf("User goes first for the game...\n");
                }
            }

            if (turnToStart == 0) { //computer goes first now
                turnToStart = 1;//user goes first next time
                printf("Computer's turn to go first for this round...\n");
                computerPlayTurn();
            } else {
                //user going first right now
                turnToStart = 0;//computer goes first next time
                printf("User's turn to go first for this round...\n");
                displayBoard(gameBoard);
            }


            while (isAWinner == false) {//
                playUserTurn(USER_PIECE);
                determineIfAWinner(gameBoard, true);
                if (isAWinner == true) {
                    displayBoard(gameBoard);
                    break;
                }
                computerPlayTurn();
                determineIfAWinner(gameBoard, true);
            }
            winner = determineIfAWinner(gameBoard, true);
            if (winner == 0) {
                compScore++;
                printf("\n \nThe COMPUTER won this round. Oh no!\n");
            } else {
                userScore++;
                printf("\n \nCongrats! YOU won this round!\n");
            }
            printf("\nCOMPUTER SCORE: %d\nUSER SCORE: %d\n", compScore, userScore);
        }

        //final game info
        if (compScore == upToScore) { //comp won
            printf("The computer won the game! It won %d to %d\n\nThanks for playing!!!!\n", compScore, userScore);
        } else {
            printf("You won the game! You won %d to %d\n\nThanks for playing!!!!\n", userScore, compScore);
        }
        //============================================================PLAY GAME ONE PLAYER=====================================


    } else { //============================================================PLAY GAME TWO PLAYER=====================================

        while (compScore < upToScore && userScore < upToScore) {
            initialize();
            printf("\n\n");
            for (int i = 0; i < SIZE * 13.5 + SPACER + 5; i++) {
                printf("=");
            }
            printf("ROUND # %d", round);
            for (int i = 0; i < SIZE * 13.5 + SPACER + 3; i++) {
                printf("=");
            }
            printf("\n\n");

            // displayBoard(gameBoard);
            round++;

            //who goes first, alternate afterwards
            if (doOnce == true) {
                doOnce = false;
                int firstTurn = rand() % 2;
                if (firstTurn == 0) { //computer starts
                    printf("Player 2 goes first\n");
                    turnToStart = 0;//THIS GETS CHANGED BELOW TO GO BACK TO 1
                    // computerPlayTurn();
                } else {
                    printf("Player 1 goes first\n");
                }
            }

            if (turnToStart == 0) { //computer goes first now
                turnToStart = 1;//user goes first next time
                printf("Player 2: Your move.\n");
                //displayBoard(gameBoard);
                playUserTurn(PLAYER_TWO);
                displayBoard(gameBoard);

            } else {
                //usdisplayBoard(gameBoard);er going first right now
                turnToStart = 0;//computer goes first next time
                printf("Player 1: Your move.\n");
                displayBoard(gameBoard);
            }


            while (isAWinner == false) {//
                printf("Player 1: Your move.\n");
                playUserTurn(USER_PIECE);
                displayBoard(gameBoard);
                determineIfAWinner(gameBoard, true);
                if (isAWinner == true) {
                    displayBoard(gameBoard);
                    break;
                }
                printf("Player 2: Your move.\n");
                playUserTurn(PLAYER_TWO);
                displayBoard(gameBoard);
                determineIfAWinner(gameBoard, true);
            }
            winner = determineIfAWinner(gameBoard, true);
            if (winner == 0) {
                compScore++;
                printf("\n \nPlayer 2 won this round!\n");
            } else {
                userScore++;
                printf("\n \nPlayer 1 won this round!\n");
            }
            printf("\nPlayer 2 SCORE: %d\nPlayer 1 SCORE: %d\n", compScore, userScore);
        }

        //final game info
        if (compScore == upToScore) { //comp won
            printf("Player 2 won the game! They won %d to %d\n\nThanks for playing!!!!\n", compScore, userScore);
        } else {
            printf("Player 1 won the game! They won %d to %d\n\nThanks for playing!!!!\n", userScore, compScore);
        }

    }//============================================================PLAY GAME TWO PLAYER=====================================

    return 0;
}
