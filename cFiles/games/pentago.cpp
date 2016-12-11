#include <stdio.h>
#include <stdlib.h>
#include <stdbool.h>
#include <time.h>



///========================================================== VARIABLES and CONSTANTS ==========================================================
int VERSION = 1.0;
int DATE = 20160913;

int SIZE = 15;//multiples of 3?
int GAME_BOARD_DIMENSION = 6;
int VERTICAL_HEIGHT = SIZE / 3;
int gameBoard[6][6];
int EMPTY = 2;
int COMP_PIECE = 0;
int USER_PIECE = 1;
int PLAYER_TWO = 0;
int HOR_GAP_FROM_BORDER = 3;
int ROTATE_RIGHT = 1;
int ROTATE_LEFT = 0;
int lastCompTurn[2];
int userScore = 0;
int compScore = 0;
int upToScore;
int numberPiecesOnBoard = 0;
int difficultyPercentage;
int EASY_PERCENTAGE = 60;
int MEDIUM_PERCENTAGE = 80;
int HARD_PERCENTAGE = 90;
int availableSpots[36][2];

int OFFSET = 6;

int ADDED_IN_MIDDLE = 4;

bool doOnce = true;
bool isAWinner = false;

char USER_DISPLAY_PIECE = 'X';
char COMP_DISPLAY_PIECE = 'c';
char EMPTY_DISPLAY_PIECE = ' ';

///========================================================== VARIABLES and CONSTANTS ==========================================================

bool mustRoate() {
    //all boards have a piece on them (not in the middle)
    int counter = 0;
    for (int r = 0; r < 3; r++) { //baord 1
        for (int c = 0; c < 3; c++) {
            if ( (r != 1 || c != 1) && gameBoard[r][c] != EMPTY) {
                //printf("section 1 %d %d\n", r, c);
                counter++;
                c = 100;
                r = 100;
                break;
            }
        }
    }

    for (int r = 0; r < 3; r++) { //board 2
        for (int c = 3; c < 6; c++) {
            if ( (r != 1 || c != 1) && gameBoard[r][c] != EMPTY) {
                //printf("section 2 %d %d\n", r, c);
                counter++;
                c = 100;
                r = 100;
                break;
            }
        }
    }

    for (int r = 3; r < 6; r++) { //board 3
        for (int c = 3; c < 6; c++) {
            if ( (r != 1 || c != 1)  && gameBoard[r][c] != EMPTY) {
                //printf("section 3 %d %d\n", r, c);
                counter++;
                c = 100;
                r = 100;
                break;
            }
        }
    }

    for (int r = 3; r < 6; r++) { //board 4
        for (int c = 0; c < 3; c++) {
            if ( (r != 4 || c !=  1) && gameBoard[r][c] != EMPTY) {
                //printf("section 4 %d %d\n", r, c);
                counter++;
                c = 100;
                r = 100;
                break;
            }
        }
    }

    //printf("counter is: %d\n", counter);

    if (counter != 4) {
        //printf("MUST NOT ROTATE\n");
        return false;
    }
    //printf("MUST ROTATE\n");
    return true;

}

void printTopLine(char ch) {
    for (int i = 0; i < OFFSET; i++) {
        printf(" ");
    }
    for (int i = 0; i < SIZE * GAME_BOARD_DIMENSION - 1 + ADDED_IN_MIDDLE; i++) {
        printf("%c", ch);
    }
    printf("\n");
}

int configureSpacing(int height) {
    int x = height - 2;
    if (x < 0) {
        x = -x;
    }//ABSOLUTE VALUE OF X
    return x;
}

void addTurnToBoard(int r, int c, int playerOrComp) {
    numberPiecesOnBoard++;
    gameBoard[r][c] = playerOrComp;
}

void rotateSection(int section, int direction) { //1 2 3 4 top left, top right, bottom right, bottom left//1 RIGHT, 0 LEFT
    int rotationPoint[2];
    int dataCopy[GAME_BOARD_DIMENSION][GAME_BOARD_DIMENSION];
    switch(section) {
    case 1:
        rotationPoint[0] = 1;
        rotationPoint[1] = 1;
        break;
    case 2:
        rotationPoint[0] = 1;
        rotationPoint[1] = 4;
        break;
    case 3:
        rotationPoint[0] = 4;
        rotationPoint[1] = 4;
        break;
    case 4:
        rotationPoint[0] = 4;
        rotationPoint[1] = 1;
        break;
    }
    //make copy of the data
    for (int r = 0 ; r < GAME_BOARD_DIMENSION / 2; r++) {
        for (int c = 0; c < GAME_BOARD_DIMENSION / 2; c++) {
            dataCopy[r][c] = gameBoard[r + rotationPoint[0] - 1][c + rotationPoint[1] - 1];
        }
    }
    int x = rotationPoint[0];//row
    int y = rotationPoint[1];//col
    if (direction == ROTATE_RIGHT) {
        gameBoard[x - 1][y - 1] = dataCopy[2][0];
        gameBoard[x - 1][y] = dataCopy[1][0];
        gameBoard[x - 1][y + 1] = dataCopy[0][0];
        gameBoard[x][y + 1] = dataCopy[0][1];
        gameBoard[x + 1][y + 1] = dataCopy[0][2];
        gameBoard[x + 1][y] = dataCopy[1][2];
        gameBoard[x + 1][y - 1] = dataCopy[2][2];
        gameBoard[x][y - 1] = dataCopy[2][1];
    } else {
        gameBoard[x - 1][y - 1] = dataCopy[0][2];
        gameBoard[x - 1][y] = dataCopy[1][2];
        gameBoard[x - 1][y + 1] = dataCopy[2][2];
        gameBoard[x][y + 1] = dataCopy[2][1];
        gameBoard[x + 1][y + 1] = dataCopy[2][0];
        gameBoard[x + 1][y] = dataCopy[1][0];
        gameBoard[x + 1][y - 1] = dataCopy[0][0];
        gameBoard[x][y - 1] = dataCopy[0][1];
    }
}

void displayBoard() {
    char piece;
    printf("\n\n");
    ///==========================================================display section numbers 1, 2
    for (int i = 0; i < OFFSET + SIZE * 1.5 - 5; i++) {
        printf(" ");
    }
    printf("Section 1");
    for (int i = 0; i < SIZE * 3 - 5; i++) {
        printf(" ");
    }
    printf("Section 2\n");
    ///=========================================================DISPLAY COLUMN NUMBERS==========================================
    for (int i = 0; i < OFFSET - 1 + SIZE / 2; i++) {
        printf(" ");
    }
    for (int i = 0; i < GAME_BOARD_DIMENSION; i++) {
        printf("%d", i + 1);
        for (int c = 0; c < SIZE; c++) {
            printf(" ");
        }
    }
    printf("\n");
    ///=========================================================DISPLAY BOARD===================================================
    char row = 65;
    for (int r = 0; r < GAME_BOARD_DIMENSION; r++) {
        if (r == GAME_BOARD_DIMENSION/2) {
            printTopLine('-');
            printTopLine('-');
            printTopLine('-');
        } else {
            printTopLine('-');
        }
        for (int h = 0; h < VERTICAL_HEIGHT; h++) {
            if (h == VERTICAL_HEIGHT / 2 ) { //middle
                printf("  %c  ", row);
                row++;
            } else {
                printf("     ");
            }
            for (int c = 0; c < GAME_BOARD_DIMENSION; c++) {
                printf("|");//offsett
                if (c == GAME_BOARD_DIMENSION/2) { //added to centre
                    printf(" | |");
                }
                for (int i = 0; i < HOR_GAP_FROM_BORDER + abs(h - 2); i++) { //GAP
                    printf(" ");
                }
                if (gameBoard[r][c] == USER_PIECE) {            //CHOOSE CHAR
                    piece = USER_DISPLAY_PIECE;
                } else if (gameBoard[r][c] == COMP_PIECE) {
                    piece = COMP_DISPLAY_PIECE;
                    //} else if (gameBoard[r][c] == comp){

                } else {
                    piece = EMPTY_DISPLAY_PIECE;//change to empty
                }

                for (int i = 0; i < SIZE - HOR_GAP_FROM_BORDER*2 - 1 - abs(h - 2)*2; i++) {//PRINT PIECE
                    printf("%c", piece);
                }
                for (int i = 0; i < HOR_GAP_FROM_BORDER+ abs(h - 2); i++) { //GAP
                    printf(" ");
                }
                if (c == GAME_BOARD_DIMENSION - 1) {
                    printf("|");
                }
            }
            printf("\n");
        }
    }
    printTopLine('-');

    ///display section numbers 1, 2
    for (int i = 0; i < OFFSET + SIZE * 1.5 - 5; i++) {
        printf(" ");
    }
    printf("Section 4");
    for (int i = 0; i < SIZE * 3 - 5; i++) {
        printf(" ");
    }
    printf("Section 3\n");
}



void userTurn(int user1OrTwo) {//still have to rotate



    int col, counter = 0, boardRow;
    char row;
    bool gotGoodInput = false;
    printf("\n\nUSER TURN:");
    while (gotGoodInput == false) {
        counter = 0;
        printf("\nPlease enter the coordinates to place your piece EX: A 1, B 2, etc: ");
        scanf(" %c", &row);
        scanf(" %d", &col);
        printf("You entered: %c %d\n", row, col);
        if (row > 64 && row < 65 + GAME_BOARD_DIMENSION) {
            counter++;
            //printf("counter up\n");
            boardRow = row - 65;
        }
        if (col > 0 && col < GAME_BOARD_DIMENSION + 1) {
            counter++;
            //printf("counter up\n");
        }
        if (counter == 2) {
            if (gameBoard[boardRow][col - 1] == EMPTY) {
                //printf("You entered: %c %d\n", row, col);
                //printf("Coordinates are: %d %d\n", boardRow, col - 1);
                gotGoodInput = true;
                addTurnToBoard(boardRow, col - 1, user1OrTwo);///two player///=================
                break;
            } else {
                printf("That spot is already taken. Please try again.\n");
            }
        } else {
            printf("Try again. Invalid input.\n");
        }
    }

    gotGoodInput = false;
    bool userWantsToRotate = false;
    if (mustRoate() == false && numberPiecesOnBoard > 7) {
        gotGoodInput = false;
        char wantToRotate;
        while (gotGoodInput == false) {
            printf("Would you like to rotate? y for Yes, n for No: ");
            scanf(" %c", &wantToRotate);
            if (wantToRotate == 'y' || wantToRotate == 'Y') {
                userWantsToRotate = true;
                gotGoodInput = true;
            } else if (wantToRotate == 'n' || wantToRotate == 'N' ) {
                gotGoodInput = true;
            } else {
                printf("Invalid input, try again.\n");
            }


        }
    }
    if (mustRoate() || userWantsToRotate == true ) {
        displayBoard();
        //printf("Must rotate\n");
        int section, direction;
        gotGoodInput = false;
        while (gotGoodInput == false) {
            printf("\n\n================================You MUST Rotate================================ \n\nEnter board section to rotate (1 for top left, 2 for top right, 3 for bottom right, 4 for bottom left): ");
            scanf("%d", &section);
            if (section > 0 && section < 5) {
                gotGoodInput = true;
                break;
            } else {
                printf("Try again...\n");
            }
        }
        gotGoodInput = false;
        while (gotGoodInput == false) {
            printf("Enter direction: 1 for clockwise, 0 for counter-clockwise: ");
            scanf("%d", &direction);

            if (direction == ROTATE_LEFT || direction == ROTATE_RIGHT) {
                gotGoodInput = true;
                break;
            } else {
                printf("Try again...\n");
            }
        }
        rotateSection(section, direction);
    }
    // else {


    // }
}

void playRandomTurn() {
    bool foundGoodSpot = false;
    int r, c, section, direction;
    while (foundGoodSpot == false) {
        r = rand() % GAME_BOARD_DIMENSION;
        c = rand() % GAME_BOARD_DIMENSION;
        if (gameBoard[r][c] == EMPTY) {
            addTurnToBoard(r, c, COMP_PIECE);//random location
            foundGoodSpot = true;
//                if (lastCompTurn[0] != -1){
//                    addTurnToBoard( lastCompTurn[0],  lastCompTurn[1], COMP_PIECE);
//                }
            break;
        }
    }
    section = rand() % 4 + 1;
    direction = rand() % 2;

    if (mustRoate() ) {
        displayBoard();
        printf("rotating....\n");
        rotateSection(section, direction);
    }

}

int checkIfAWinner(bool actualWinner) {
    if (numberPiecesOnBoard < 9) return -1;
    for (int r = 0; r < GAME_BOARD_DIMENSION; r++) {//horizontal
        for (int c = 0; c < 2; c++) {
            if (gameBoard[r][c] != EMPTY && gameBoard[r][c] == gameBoard[r][c + 1] && gameBoard[r][c] == gameBoard[r][c + 2] && gameBoard[r][c] == gameBoard[r][c + 3] && gameBoard[r][c] == gameBoard[r][c + 4] ) {
                //printf("FOUND WINNER GOING HORIZONTAL at %d %d\n", r, c);
                isAWinner = true && actualWinner;
                return gameBoard[r][c];
            }
        }
    }
    for (int r = 0; r < 2; r++) {//vertical
        for (int c = 0; c < GAME_BOARD_DIMENSION; c++) {
            if (gameBoard[r][c] != EMPTY && gameBoard[r][c] == gameBoard[r + 1][c] && gameBoard[r][c] == gameBoard[r + 2][c] && gameBoard[r][c] == gameBoard[r + 3][c] && gameBoard[r][c] == gameBoard[r + 4][c] ) {
                // printf("FOUND WINNER GOING VERTICAL at %d %d\n", r, c);
                isAWinner = true && actualWinner;
                return gameBoard[r][c];
            }
        }
    }

    for (int r = 4; r < GAME_BOARD_DIMENSION; r++) {//diagonal bottom left to top right
        for (int c = 0; c < 2; c++) {
            if (gameBoard[r][c] != EMPTY && gameBoard[r][c] == gameBoard[r - 1][c + 1] && gameBoard[r][c] == gameBoard[r - 2][c + 2] && gameBoard[r][c] == gameBoard[r - 3][c + 3] && gameBoard[r][c] == gameBoard[r - 4][c + 4]   ) {
                //printf("FOUND WINNER DIAGONAL bottom left to top right, at %d %d\n", r, c);
                isAWinner = true && actualWinner;
                return gameBoard[r][c];
            }
        }
    }

    for (int r = 0; r < 2; r++) {//diagonal top left to bottom right
        for (int c = 0; c < 2; c++) {
            if (gameBoard[r][c] != EMPTY && gameBoard[r][c] == gameBoard[r + 1][c + 1] && gameBoard[r][c] == gameBoard[r + 2][c + 2] && gameBoard[r][c] == gameBoard[r + 3][c + 3] && gameBoard[r][c] == gameBoard[r + 4][c + 4]   ) {
                //printf("FOUND WINNER DIAGONAL top left to bottom right, at %d %d\n", r, c);
                isAWinner = true && actualWinner;
                return gameBoard[r][c];
            }
        }
    }

    //printf("no winner found\n\n");
    return -1;
}

void getAllAvailableSpots() {
    for (int i = 0; i < 36; i++) {
        availableSpots[i][0] = -1;
        availableSpots[i][1] = -1;
    }

    int counter = 0, index = 0, a, b;

    for (int r = 0; r < 6; r++) {
        for (int c = 0; c < 6; c++) {
            if (gameBoard[r][c] == EMPTY) {
                availableSpots[counter][0] = r;
                availableSpots[counter][1] = c;

                counter++;
            }
        }
    }
}

bool inSameSection(int r1, int r2, int c1, int c2) {
    if (r1 < 3 && r2 >= 3) return false;
    if (r1 >= 3 && r2 <3) return false;
    if (c1 < 3 && c2 >= 3) return false;
    if (c1 >= 3 && c2 <3) return false;
    // printf("2 points are in same section, %d %d and %d %d\n", r1, c1, r2, c2);
    return true;
}

void computerPlayTurn() {
    int z, directionToRotate, sectionToRotate, backDirection;
    // printf("\nEnter 1 for comp to go for comp turn, 2 for user to play: ");//part of me entering turn for computer
    // scanf("%d", &z);//this too
    bool foundGoodRotate = false;

//    if (z == 2) {
//        int j, k;
//        printf("Enter row col as ints (0 - 5): ");
//        scanf(" %d", &j);
//        scanf(" %d", &k);
//        addTurnToBoard(j, k, COMP_PIECE);
//    } else {
    getAllAvailableSpots();
    int suggestedMove[2];
    suggestedMove[0] = -1;
    suggestedMove[1] = -1;
    int index = 0,a, b;
    bool foundWinningMove = false;
    bool foundDefensiveMove = false;
    bool foundStrategicMove = false;
    bool defenseRotateDontDo = false;

    while (availableSpots[index][0] != -1) {///=======================winning move
        a = availableSpots[index][0];
        b = availableSpots[index][1];
        gameBoard[a][b] = COMP_PIECE;
        if (checkIfAWinner(true) == COMP_PIECE) {
            //printf("offensive. winner. that winning location is: %d %d %d\n", a, b, c);
            //addTurnToBoard(COMPUTER_PIECE, a, b, c);
            suggestedMove[0] = a;
            suggestedMove[1] = b;

            foundWinningMove = true;
            //printf("FOUND WINNING MOVE\n");

            gameBoard[a][b] = EMPTY;// COMMENT OUT BELOW/delete it
            break;
        } else {
            gameBoard[a][b] = EMPTY; //re set that spot
        }
        index++;
    }

    ///=========================================================winning move with rotate
    index = 0;
    bool keepGoing = true;
    while (availableSpots[index][0] != -1 && keepGoing == true && foundWinningMove == false) {
        a = availableSpots[index][0];
        b = availableSpots[index][1];
        gameBoard[a][b] = COMP_PIECE;//place piece in each available spot
        for (int section = 1; section < 5; section++) {
            for (int direction = 0; direction < 2; direction++) {
                //rotate every board in every direction, see if you win
                //printf("rotating...\n");
                rotateSection(section, direction);
                if (checkIfAWinner(true) == COMP_PIECE) {
                    suggestedMove[0] = a;
                    suggestedMove[1] = b;
                    directionToRotate = direction;
                    sectionToRotate = section;
                    foundWinningMove = true;
                    foundGoodRotate = true;
                    keepGoing = false;
                }
                //rotate back always, because you will place and rotate later
                backDirection = 0;
                if (direction == 0) {
                    backDirection = 1;
                }
                rotateSection(section, backDirection);
                //gameBoard[a][b] = EMPTY;

                if (foundGoodRotate == true) {
                    //printf("found good rotate is true. section:%d; direction: %d\n", section, direction);
                    direction = 100;
                    section = 100;
                    break;
                }
            }
        }

        //printf("making gameboard at that location empty...\n");
        gameBoard[a][b] = EMPTY;
        index++;
    }

    if (foundGoodRotate == false) {
        //printf("DID NOT FIND GOOD ROTATION TO WIN\n");
    }
    bool foundMove = false;
    if (foundWinningMove == false) { ///===============================defensive move============================================
        index = 0 ;
        while (availableSpots[index][0] != -1) {
            //tempGameBoard = gameBoard;
            a = availableSpots[index][0];
            b = availableSpots[index][1];
            gameBoard[a][b] = USER_PIECE;
            if (checkIfAWinner(false) == USER_PIECE) {
                //printf("defensive. must block the user.\n");
                //addTurnToBoard(COMPUTER_PIECE, a, b, c);
                suggestedMove[0] = a;
                suggestedMove[1] = b;
                foundDefensiveMove = true;
                //printf("FOUND !!!!BLOCKING!!! MOVE\n");
                //gameBoard[a][b][c] = COMPUTER_PIECE;
                foundMove = true;
                gameBoard[a][b] = EMPTY;
                break;
            } else {
                gameBoard[a][b] = EMPTY; //re set that spot
            }
            index++;
        }

        ///================================================ defensive move with rotate =============================================
        index = 0;
        keepGoing = true;

        while (availableSpots[index][0] != -1 && keepGoing == true && foundMove == false) {
            a = availableSpots[index][0];
            b = availableSpots[index][1];
            gameBoard[a][b] = USER_PIECE;//place piece in each available spot
            for (int section = 1; section < 5; section++) {
                for (int direction = 0; direction < 2; direction++) {
                    //rotate every board in every direction, see if you win
                    //printf("rotating...\n");
                    rotateSection(section, direction);
                    if (checkIfAWinner(false) == USER_PIECE) {
                        suggestedMove[0] = a;
                        suggestedMove[1] = b;
                        //rotate in the OPPOSITE direction to prevent it
                        directionToRotate = 0;
                        if (direction == 0) {
                            directionToRotate = 1;
                        }
                        //printf("rotate in OPPOSITE direction\n");
                        sectionToRotate = section;
                        foundDefensiveMove = true;
                        foundMove = true;
                        foundGoodRotate = true;
                        // defenseRotateDontDo = true;
                        keepGoing = false;
                        // printf("\n found defensive block rotate move\n");
                    }
                    //rotate back always, because you will place and rotate later
                    backDirection = 0;
                    if (direction == 0) {
                        backDirection = 1;
                    }
                    rotateSection(section, backDirection);
                    //gameBoard[a][b] = EMPTY;

                    if (foundGoodRotate == true) {
                        // printf("found good rotate is true. but i dont need to do it since this is defense. section:%d; direction: %d\n", section, direction);
                        direction = 100;
                        section = 100;
                        break;
                    }
                }
            }

            //printf("making gameboard at that location empty...\n");
            gameBoard[a][b] = EMPTY;
            index++;

        }
        ///================================================ end of defensive move with rotate ======================================
        ///=============================================  strategy =================================================================
        if (foundDefensiveMove == false || foundMove == false) {//strategic move
            //printf("do strategic move here....\n");
            bool fills3InASection = false;
            ///===========================================================OFFENSIVE STRATEGY========================================
            ///=================================MAKE 2 IN A ROW 3===================================================================

            for (int r = 0; r < 6; r++) { ///==================================HORIZONTAL===========================================
                for (int c = 0; c < 5; c++) {
                    if (gameBoard[r][c] == COMP_PIECE && gameBoard[r][c] == gameBoard[r][c + 1]) {
                        if (c > 0 && gameBoard[r][c - 1] == EMPTY) { //place on left where it is empty//prioritize filling a block of 3 !!!!!
                            if (fills3InASection == false) {
                                suggestedMove[0] = r;
                                suggestedMove[1] = c - 1;
                            }
                            if (inSameSection(r, r, c - 1, c + 1)) {
                                fills3InASection = true;
                            }
                            // printf("strategy - horizontal - place on left at %d %d\n",r, c - 1 );
                        }
                        if(c < 4 && gameBoard[r][c + 2] == EMPTY) {
                            if (inSameSection(r, r, c, c + 2)) {
                                suggestedMove[0] = r;
                                suggestedMove[1] = c + 2;
                                //printf("This one fills section, so suggest THIS instead\n");
                                fills3InASection = true;
                            }
                            // printf("strategy - horizontal - place on right at %d %d\n",r, c + 2 );
                        }
                    }
                }
            }

            if (suggestedMove[0] == -1) {
                for (int r = 0; r < 5; r++) { ///==================================VERTICAL================================================
                    for (int c = 0; c < 6; c++) {
                        if (gameBoard[r][c] == COMP_PIECE && gameBoard[r][c] == gameBoard[r + 1][c]) {

                            if (r > 0 && gameBoard[r - 1][c] == EMPTY) { //place on left where it is empty//prioritize filling a block of 3 !!!!!
                                if (fills3InASection == false) {
                                    suggestedMove[0] = r - 1;
                                    suggestedMove[1] = c;
                                }
                                if (inSameSection(r - 1, r  +1, c, c)) {
                                    fills3InASection = true;
                                }
                                //printf("strategy - VERTICAL - place above at %d %d\n",r - 1, c );
                            }

                            if(r < 4 && gameBoard[r + 2][c] == EMPTY) {
                                if (inSameSection(r, r + 2, c, c)) {
                                    suggestedMove[0] = r + 2;
                                    suggestedMove[1] = c ;
                                    //printf("This one fills section, so suggest THIS instead\n");
                                    fills3InASection = true;
                                }
                                //printf("strategy - vertical - place BELOW at %d %d\n",r + 2, c  );

                            }

                        }
                    }
                }
            }

            //

            ///=================================MAKE 3 IN A ROW 4=====================================


            // todo


            ///==========================================================DEFENSIVE STRATEGY======================================

            //block if user has 2 in a row
            if (suggestedMove[0] == -1) {
                for (int r = 0; r < 6; r++) { ///==================================HORIZONTAL BLOCK of 2 in a row
                    for (int c = 0; c < 5; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r][c + 1]) {

                            if (c > 0 && gameBoard[r][c - 1] == EMPTY) { //place on left where it is empty//prioritize filling a block of 3 !!!!!
                                if (fills3InASection == false) {
                                    suggestedMove[0] = r;
                                    suggestedMove[1] = c - 1;
                                }
                                if (inSameSection(r, r, c - 1, c + 1)) {
                                    fills3InASection = true;
                                }
                                //printf("strategy - horizontal- BLOCK - place on left at %d %d\n",r, c - 1 );
                            }
                            if(c < 4 && gameBoard[r][c + 2] == EMPTY) {//place on right
                                if (inSameSection(r, r, c, c + 2)) {
                                    suggestedMove[0] = r;
                                    suggestedMove[1] = c + 2;
                                    //printf("This one fills section, so suggest THIS instead\n");
                                    fills3InASection = true;
                                }
                                //printf("strategy - horizontal - BLOCK - place on right at %d %d\n",r, c + 2 );
                            }
                        }
                    }
                }
            }// end of horizontal block of 2

            if (suggestedMove[0] == -1) {//vertical block of 2 in a row
                for (int r = 0; r < 5; r++) { ///==================================VERTICAL
                    for (int c = 0; c < 6; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r + 1][c]) {

                            if (r > 0 && gameBoard[r - 1][c] == EMPTY) { //place on left where it is empty//prioritize filling a block of 3 !!!!!
                                if (fills3InASection == false) {
                                    suggestedMove[0] = r - 1;
                                    suggestedMove[1] = c;
                                }
                                if (inSameSection(r - 1, r  +1, c, c)) {
                                    fills3InASection = true;
                                }
                                //printf("strategy - VERTICAL - BLOCK - place above at %d %d\n",r - 1, c );
                            }
                            if(r < 4 && gameBoard[r + 2][c] == EMPTY) {
                                if (inSameSection(r, r + 2, c, c)) {
                                    suggestedMove[0] = r + 2;
                                    suggestedMove[1] = c ;
                                    //printf("This one fills section, so suggest THIS instead\n");
                                    fills3InASection = true;
                                }
                                // printf("strategy - vertical - BLOCK - place BELOW at %d %d\n",r + 2, c  );
                            }

                        }
                    }
                }
            }

            ///===============horizontal block of x _ x=================================================================================
            if (suggestedMove[0] == -1) {
                for (int r = 0; r < 6; r++) {
                    for (int c = 0; c < 4; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r][c + 2]) {
                            if (gameBoard[r][c + 1] == EMPTY) { //place on left where it is empty//prioritize filling a block of 3 !!!!!
                                suggestedMove[0] = r ;
                                suggestedMove[1] = c + 1;
                                // printf("strategy - horizontal- BLOCK - place in MIDDLE %d %d\n",r, c + 1 );
                                r = 100;
                                c = 100;
                                break;
                            }

                        }
                    }
                }
            }

            /// vertical block of x _ x
            if (suggestedMove[0] == -1) {
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 6; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r + 2][c]) {
                            if (gameBoard[r + 1][c] == EMPTY) { //place on left where it is empty//prioritize filling a block of 3 !!!!!
                                suggestedMove[0] = r + 1;
                                suggestedMove[1] = c;
                                //printf("strategy - horizontal- BLOCK - place in MIDDLE %d %d\n",r + 1, c );
                                r = 100;
                                c = 100;
                                break;
                            }
                        }
                    }
                }
            }

            ///diagonal block top left to bottom right x _ x
            if (suggestedMove[0] == -1) {
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r + 2][c + 2] && gameBoard[r + 1][c + 1] == EMPTY) {
                            suggestedMove[0] = r + 1;
                            suggestedMove[1] = c + 1;
                            //printf("strategy - horizontal- BLOCK - place in MIDDLE %d %d\n",r + 1, c );
                            r = 100;
                            c = 100;
                            break;
                        }
                    }
                }
            }

            if (suggestedMove[0] == -1) {///diagonal block, top right to bottom left x _ x
                for (int r = 0; r < 4; r++) {
                    for (int c = 2; c < 6; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r + 2][c - 2] && gameBoard[r + 1][c - 1] == EMPTY) {
                            suggestedMove[0] = r + 1;
                            suggestedMove[1] = c - 1;
                            //printf("strategy - horizontal- BLOCK - place in MIDDLE %d %d\n",r + 1, c );
                            r = 100;
                            c = 100;
                            break;
                        }
                    }
                }
            }

            ///diagonal block
            if (suggestedMove[0] == -1) {///diagonal block, top right to bottom left _ x x
                for (int r = 0; r < 4; r++) {
                    for (int c = 2; c < 6; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r + 1][c - 1] && gameBoard[r + 2][c - 2] == EMPTY) {
                            suggestedMove[0] = r + 2;
                            suggestedMove[1] = c - 2;
                            //printf("strategy - horizontal- BLOCK - place in MIDDLE %d %d\n",r + 1, c );
                            r = 100;
                            c = 100;
                            break;
                        }
                    }
                }
            }

            /// diagonal block x x _
            if (suggestedMove[0] == -1) {
                for (int r = 0; r < 4; r++) {
                    for (int c = 0; c < 4; c++) {
                        if (gameBoard[r][c] == USER_PIECE && gameBoard[r][c] == gameBoard[r + 1][c + 1] && gameBoard[r + 2][c + 2] == EMPTY) {
                            suggestedMove[0] = r + 2;
                            suggestedMove[1] = c + 2;
                            //printf("strategy - horizontal- BLOCK - place in MIDDLE %d %d\n",r + 1, c );
                            r = 100;
                            c = 100;
                            break;
                        }
                    }
                }
            }

        }
    }

    //else, random();
    if (suggestedMove[0] != -1) { /// right here======================================================add difficulty logic here
        // printf("doing calculation to see if comp will use suggested move\n");

        int x = rand() % 100;
        // printf("x is %d. must be less than %d to do move...\n", x, difficultyPercentage);
        if (x <= difficultyPercentage) {
            //printf("doing suggested move\n");
            addTurnToBoard(suggestedMove[0], suggestedMove[1], COMP_PIECE);
            if (foundGoodRotate == true ) { //can rotate to win
                // printf("can rotate to win.... ");
                printf("Computer First Placement (will rotate): \n");
                displayBoard();
                printf("Computer Rotating: \n");
                rotateSection(sectionToRotate, directionToRotate);
            } else if (mustRoate() == true) {
                //printf("did not find winning rotate....but must rotate....\n");
                int section = rand() % 4 + 1;
                int direction = rand() % 2;
                displayBoard();
                //printf("rotating....\n");
                rotateSection(section, direction);

            }
        } else {
            //printf("NOT doing suggested move\n");
            playRandomTurn();
        }

    } else {///================================RANDOM MOVE LAST RESORT =======================
        //printf("doing random turn.... \n");
        playRandomTurn();
    }
    //playRandomTurn();



    //}//PART OF ME ENTERING THE TURN FOR COMP
    displayBoard();
}

void initialize() {
    numberPiecesOnBoard = 0;

    isAWinner = false;
    srand (time(NULL));

    for (int r = 0; r < 6; r++) {
        for (int c = 0; c < 6; c++) {
            gameBoard[r][c] = EMPTY;

        }
    }
}


void startScreen() {
    for (int i = 0; i < SIZE * 6 + OFFSET + 10; i++) {
        printf("=");
    }
    printf("\n\nWelcome to PENTAGO game.\n\nRules: Win by placing 5 pieces in a row in any direction: vertical, horizontal or diagonal.");
    printf("\nOnce each section (1-4) has a piece in it, not in its centre, you must rotate 1 section 90 degrees\nin any direction each turn. Enjoy! \n\n\n\n\n");
    printf("Created by Scott Sandre. \nVERSION : %d \nDATE : %d\n\n", VERSION, DATE);

    for (int i = 0; i < SIZE * 6 + OFFSET + 10; i++) {
        printf("=");
    }
    printf("\n\n");
}
int main (void) {
    initialize();
    bool gotGoodInput = false;
    int turnToStart, winner, difficulty, singlePlayer;

    startScreen();
    ///==============================================================UP TO SCORE================================================
    printf("GAME OPTIONS:\n\n");
    printf("-----> SCORE TO PLAY TO:\n\n");
    while (gotGoodInput == false) {
        printf("Please Enter the score you want to play up to (less than 10): ");
        scanf("%d", &upToScore);
        if (upToScore > 0 && upToScore < 10 ) {
            gotGoodInput = true;
        } else {
            printf("Try again.\n");
        }
    }

    ///===========================================NUMBER OF PLAYERS ===========================================================
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
    if (singlePlayer == 1) {
        gotGoodInput = false;
        ///==============================================================DIFFICULTY================================================
        printf("\n-----> GAME DIFFICULTY:\n\n");
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
        printf("\n\n");
        while (compScore < upToScore && userScore < upToScore) {///=========================PLAY GAME =========================================
            initialize();

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
                displayBoard();
            }
            while (isAWinner == false) {//
                userTurn(true);
                checkIfAWinner(true);
                if (isAWinner == true) {
                    displayBoard();
                    break;
                }
                computerPlayTurn();
                checkIfAWinner(true);
            }
            winner = checkIfAWinner(true);
            if (winner == COMP_PIECE) {
                compScore++;
                printf("\n \nThe COMPUTER won this round. Oh no!\n");
            } else {
                userScore++;
                printf("\n \nCongrats! YOU won this round!\n");
            }
            printf("\nCOMPUTER SCORE: %d\nUSER SCORE: %d\n", compScore, userScore);
        }

    } else {///=================================================================TWO PLAYERS===========================================

        int round = 1;
        while (compScore < upToScore && userScore < upToScore) {
            initialize();
            printf("\n\n");
            for (int i = 0; i < SIZE * 3 + OFFSET; i++) {
                printf("=");
            }
            printf("ROUND # %d", round);
            for (int i = 0; i < SIZE * 3; i++) {
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
                userTurn(PLAYER_TWO);
                displayBoard();

            } else {
                //usdisplayBoard(gameBoard);er going first right now
                turnToStart = 0;//computer goes first next time
                printf("Player 1: Your move.\n");
                displayBoard();
            }


            while (isAWinner == false) {//
                printf("Player 1: Your move.\n");
                userTurn(USER_PIECE);
                displayBoard();
                checkIfAWinner( true);
                if (isAWinner == true) {
                    displayBoard();
                    break;
                }
                printf("Player 2: Your move.\n");
                userTurn(PLAYER_TWO);
                displayBoard();
                checkIfAWinner(true);
            }
            winner = checkIfAWinner( true);
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



    }
    return 0;
}
