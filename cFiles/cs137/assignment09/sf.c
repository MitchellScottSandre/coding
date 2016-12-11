#include "sf.h"
#include <stdio.h>



int checkForStraightFlushForEachSuit(int a[]){
    int counter = 0;
    for (int i = 0; i < 10; i++){//check for straight flush in clubs  // endOfC - 4 or - 5
        if (a[i] == 1){//there is a value there. if a[i] = 1, and i = x; then value is x + 1
            counter = 0;
            for (int j = 1; j < 5; j++){
                if (a[j + i] == 1){
                    counter++;
                }
                if (counter == 4) return 1;
            }
        }
    }
    return 0;
}

int straightflush(struct card hand[], int n){ //hand is array of cards
    int clubsInfo[14] = {0};
    int diamondsInfo[14] = {0};
    int heartsInfo[14] = {0};
    int spadesInfo[14] = {0};
    int x;
    for (int i = 0; i < n ; i++){
        x = hand[i].value;
        switch (hand[i].suit){
            case 'c':
                clubsInfo[x - 1] = 1;
                if (x == 1) clubsInfo[13] = 1;//ace
                break;
            case 'd':
                diamondsInfo[x - 1] = 1;
                if (x == 1) diamondsInfo[13] = 1;//ace
                break;
            case 'h':
                heartsInfo[hand[i].value - 1] = 1;
                if (hand[i].value == 1) heartsInfo[13] = 1;//ace
                break;
            case 's':
                spadesInfo[hand[i].value - 1] = 1;
                if (hand[i].value == 1) spadesInfo[13] = 1;//ace
                break;
        }
    }

    if (checkForStraightFlushForEachSuit(clubsInfo) == 1) return 1;
    if (checkForStraightFlushForEachSuit(diamondsInfo) == 1) return 1;
    if (checkForStraightFlushForEachSuit(heartsInfo) == 1) return 1;
    if (checkForStraightFlushForEachSuit(spadesInfo) == 1) return 1;

    return 0;
}

//does using the pad really help? probably not
//maybe I should just get used to typing like this
//and then when I want to use the mouse I just lift my arm back and forth..so I use my biceps more...decent
