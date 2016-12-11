#include "sf.h"
#include <stdio.h>
#include <stdbool.h>

void swap (struct card a[], int i, int j){
	struct card temp = a[i];
	a[i] = a[j];
	a[j] = temp;
}

int partitionBySuit (struct card a[], int n){//pivot is a[0]
	int m = 0;
	for (int i = 1; i < n; i++){
        //delete duplicates
        if (a[i].suit == a[0].suit && a[i].value == a[0].value){
            a[i].value = -99;
        }
		else if (a[i].suit < a[0].suit){
			m++;
			swap(a, m, i);
		}
	}
	swap(a, 0, m);
	return m;
}

void displayHand(struct card hand[], int n){
    for (int i = 0; i < n; i++){
        printf("%d%c, ", hand[i].value, hand[i].suit);
    }
    printf("\n");
}
void quicksort(struct card  a[], int n){
	if (n <= 1) return;
	int m = partitionBySuit(a, n);
	quicksort(a, m);
	quicksort(a + m + 1, n - m - 1);
}

void insertionSortByValue(struct card a[], int n, int leftIndex){//n is right index, shiftRight is leftIndex
	for (int i = 1 + leftIndex; i < n; i++){
		struct card x = a[i];
		int j = i - 1;
		while (j >= 0 + leftIndex && a[j].value > x.value){
			a[j + 1] = a[j];
			--j;
		}
		a[j + 1] = x;
	}


}

int checkForStraightFlushForEachSuit(struct card hand[], int startIndex, int endIndex){
    int val;
    bool hasAce = false;
    int aceOffset = 0;
    for (int i = startIndex; i < endIndex + aceOffset; i++){//check for straight flush in clubs  // endOfC - 4 or - 5
        val = hand[i].value;
        if (val == -99) continue; //goes back to top
        if (val == 1 ) {
            hasAce = true;
            aceOffset = 1;
        }
        for (int j = 1; j < 5; j++){
            if (val == 10 && j == 4 && hasAce == true) return 1; //10, 11, 12, 13, ACE
            if (hand[i + j].value != val + j) break;
            if (j == 4) return 1;
        }
    }
    return 0;
}

int straightflush(struct card hand[], int n){ //hand is array of cards
    quicksort(hand, n);//sort deck of cards by suit c d h s
    //displayHand(hand, n);
    //sort by suit
    int endOfC = 0, endOfD = 0, endOfH = 0, endOfS = n;
    char c;
    for (int i = 0; i < n; i++){//end of clubs index //see, i could put these in to functions, put I honestly dont care that much
        if (hand[i].suit == 'c') {
            endOfC++;
        } else {
            endOfD = endOfC;
            break;
        }
    }
    for (int i = endOfC; i < n; i++){//end of diamonds index
        if (hand[i].suit == 'd') {
            endOfD++;
        } else {
            endOfH = endOfD;
            break;
        }
    }

    for (int i = endOfD; i < n; i++){//end of hearts index
        if (hand[i].suit == 'h') {
            endOfH++;
        } else {
            break;
        }
    }

    insertionSortByValue(hand, endOfC, 0);
    insertionSortByValue(hand, endOfD, endOfC);
    insertionSortByValue(hand, endOfH, endOfD);
    insertionSortByValue(hand, n, endOfH);

    if (checkForStraightFlushForEachSuit(hand, 0, endOfC - 4) == 1) return 1;
    if (checkForStraightFlushForEachSuit(hand, endOfC, endOfD - 4) == 1) return 1;
    if (checkForStraightFlushForEachSuit(hand, endOfD, endOfH - 4) == 1) return 1;
    if (checkForStraightFlushForEachSuit(hand, endOfH, n - 4) == 1) return 1;



    return 0;
}
