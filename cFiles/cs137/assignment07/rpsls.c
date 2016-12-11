#include <string.h>

//"rock", "paper", "scissors", "lizard", "Spock"

int rpsls(const char *player1, const char *player2) {
    char *nullPointer = 0;
    if (player1 == nullPointer || player2 == nullPointer) return 0;
    int goodInputCounter = 0;
    if (strcmp(player1, "rock") == 0 || strcmp(player1, "paper") == 0 || strcmp(player1, "scissors") == 0 || strcmp(player1, "lizard") == 0|| strcmp(player1, "Spock") == 0 ) {
        goodInputCounter++;
    }
    if (strcmp(player2, "rock") == 0 || strcmp(player2, "paper") == 0 || strcmp(player2, "scissors") == 0 || strcmp(player2, "lizard") == 0|| strcmp(player2, "Spock") == 0 ) {
        goodInputCounter++;
    }
    if (goodInputCounter != 2) return 0;
    //now, we have good input, now do switch case to convert the word/move into a number (ROW or COL), and then return that index in the matrixOutput

    int player1Row, player2Col;

    switch (player1[0]){
        case 'r': player1Row = 0;
        break;
        case 'p': player1Row = 1;
        break;
        case 's': player1Row = 2;
        break;
        case 'l': player1Row = 3;
        break;
        case 'S': player1Row = 4;
        break;
    }
    switch (player2[0]){
        case 'r': player2Col = 0;
        break;
        case 'p': player2Col = 1;
        break;
        case 's': player2Col = 2;
        break;
        case 'l': player2Col = 3;
        break;
        case 'S': player2Col = 4;
        break;
    }

    int outcomeMatrix[5][5] = {
        {0, -1, 1, 1, -1},
        {1, 0, -1, -1, 1},
        {-1, 1, 0, 1, -1},
        {-1, 1, -1, 0, 1},
        {1, -1, 1, -1, 0}
    };
    return outcomeMatrix[player1Row][player2Col];
}
