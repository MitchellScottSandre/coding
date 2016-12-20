/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import static TexasHoldEmGame.Board.FACE_UP_CARD_SPACER;
import static TexasHoldEmGame.PokerGame.BOARD_CENTRE_X;
import static TexasHoldEmGame.PokerGame.BOARD_CENTRE_Y;
import static TexasHoldEmGame.PokerGame.BOARD_HEIGHT;
import static TexasHoldEmGame.PokerGame.BOARD_WIDTH;
import static TexasHoldEmGame.PokerGame.FACE_UP_CARD_SCALE;
import static TexasHoldEmGame.PokerGame.SIZE_RATIO;
import static TexasHoldEmGame.PokerGame.allPlayers;
import static TexasHoldEmGame.PokerGame.betButton;
import static TexasHoldEmGame.PokerGame.bouncingUserLostGameString;
import static TexasHoldEmGame.PokerGame.deck;
import static TexasHoldEmGame.PokerGame.deltDeck;
import static TexasHoldEmGame.PokerGame.doOnce;
import static TexasHoldEmGame.PokerGame.entireGameOver;
import static TexasHoldEmGame.PokerGame.faceupCard1X;
import static TexasHoldEmGame.PokerGame.finishedTurn;
import static TexasHoldEmGame.PokerGame.gameFontNameString;
import static TexasHoldEmGame.PokerGame.gameplayMoveOn;
import static TexasHoldEmGame.PokerGame.getBeforeTime;
import static TexasHoldEmGame.PokerGame.getBeforeTime_forRound;
import static TexasHoldEmGame.PokerGame.getRestOfPlayers_untilEveryoneHasCalledOrFolded;
import static TexasHoldEmGame.PokerGame.getUserMove;
import static TexasHoldEmGame.PokerGame.gotFirstRoundUpToUserMove;
import static TexasHoldEmGame.PokerGame.gotUserMove;
import static TexasHoldEmGame.PokerGame.levelSmallBlinds;
import static TexasHoldEmGame.PokerGame.numPlayers;
import static TexasHoldEmGame.PokerGame.playersTableOrder;
import static TexasHoldEmGame.PokerGame.printedOutLast_userItIsYourTurn;
import static TexasHoldEmGame.PokerGame.roundCounter;
import static TexasHoldEmGame.PokerGame.round_turnCounter;
import static TexasHoldEmGame.PokerGame.scrollingInfo;
import static TexasHoldEmGame.PokerGame.someoneIsAllIn;
import static TexasHoldEmGame.PokerGame.startindexOfBigBlind;
import static TexasHoldEmGame.PokerGame.startindexOfDealer;
import static TexasHoldEmGame.PokerGame.startindexOfSmallBlind;
import static TexasHoldEmGame.PokerGame.turnCounter;
import static TexasHoldEmGame.PokerGame.userIsDealerSoGoToEnd_completedThisAlreaday;
import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import static TexasHoldEmGame.PokerGame.getNextRoundButtonClicked;
import static TexasHoldEmGame.PokerGame.userWon;
import static TexasHoldEmGame.PokerGame.DASH;
import static TexasHoldEmGame.PokerGame.bouncingUserWonGameString;

/**
 *
 * @author scott
 */
public class Round {

    public int winnerIndex = -1;
    public String nameOfFaceUpCardsOnTable = "";
    public String nameOfWinner = "";
    public boolean continueGettingPlayerTurns = true;
    public char fiveSameSuitChar = ' ';
    public Card faceUpCards[] = new Card[5];
    public boolean showFlop = false;
    public boolean showTurn = false;
    public boolean showRiver = false;
    public int pot;
    public static int faceUpCardsY;// = BOARD_CENTRE_Y - deck.cards[0].cardHeight / 2 - (int) ((double) (BOARD_HEIGHT) * 0.00);//was 0.05
    public int potCopy_ofChips[] = new int[6];//just holds the number of chips, already know the value at each index
    public String potString = "";
    public static int indexOfDealer;// startindexOfDealer; // all indexes are index of person AROUND THE TABLE
    public static int indexOfSmallBlind;//startindexOfSmallBlind;
    public static int indexOfBigBlind;//startindexOfBigBlind;
    public boolean roundOver = false;
    public static boolean doThisOnce_forShowingCards = true;
    //public static int level = 1; // 1 - 20 - these dictate small blinds, big blinds, and minimum bets
    public boolean someoneAllIn = false;
    public static int smallBlindValue;//levelSmallBlinds[level - 1];
    public static int bigBlindValue;//levelSmallBlinds[level - 1] * 2;
    public static int minimumRaise;//= bigBlindValue;
    public int lastRaiseAmount = 0;
    public int amountToCall = 0;
    public int lastBet = 0;
    public int minimumBet = minimumRaise + amountToCall;
    public int indexOfLastRaiseOnTable = -1;
    public BouncingString bouncingWinnerString;
    public BouncingString bouncingWinnerMoneyString;
    public static final Font totalPotFont = new Font(gameFontNameString, Font.BOLD, (int) (28.00 * SIZE_RATIO));
    public static TrueTypeFont totalPot_slickFont = new TrueTypeFont(totalPotFont, true); //comment this out for testing main

    public Round() {
        for (int i = 0; i < numPlayers; i++) {
            if (allPlayers[i].money <= 0) {
                allPlayers[i].playerStillInGame = false;
            }
        }
        PokerGame.countNumberPlayersLeftInGame();                                       //number players left in game, gives PokerGame.numberPlayersLeftInGame 
        int indexOfBlindsLevel = roundCounter / PokerGame.ROUNDS_PER_BLINDS_LEVEL;      //blinds
        if (indexOfBlindsLevel > levelSmallBlinds.length - 1) {
            indexOfBlindsLevel = levelSmallBlinds.length - 1;
        }
        smallBlindValue = levelSmallBlinds[indexOfBlindsLevel];
        bigBlindValue = smallBlindValue * 2;
        minimumRaise = bigBlindValue;
        pot = 0;
        for (int i = 0; i < 6; i++) {
            potCopy_ofChips[i] = 0;

        }
        amountToCall = 0;
        this.potString = "Total Pot: $" + pot + "";
        System.out.println("This is round number " + roundCounter);
        //set all player booleans to false
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].playerColour = PokerGame.NORMAL_PLAYER_COLOR;
            allPlayers[i].setIsDealer(false);
            allPlayers[i].setIsBigBlind(false);
            allPlayers[i].setIsSmallBlind(false);
        }
        //shift index of all dealer small blind big blind players by 1

        if (roundCounter == 0 && numPlayers > 2) {//first round
            System.out.println("Doing this for the first round only");
            indexOfDealer = startindexOfDealer;
            indexOfBigBlind = startindexOfBigBlind;
            indexOfSmallBlind = startindexOfSmallBlind;
            System.out.println("before big blind index is: " + indexOfBigBlind + " that person is person " + playersTableOrder[indexOfBigBlind]);
        } else if (roundCounter == 0 && numPlayers == 2) {
            indexOfDealer = startindexOfDealer;
            indexOfSmallBlind = indexOfDealer;
            indexOfBigBlind = startindexOfSmallBlind;
        } else if (PokerGame.numberPlayersLeftInGame > 2) { //what if this player is no longer in the game?
            System.out.println("rotating blinds and dealer..");
            indexOfDealer = getNextPlayerOnTable(indexOfDealer + 1, -1, -1);
            indexOfSmallBlind = getNextPlayerOnTable(indexOfDealer + 1, indexOfDealer, -1);
            indexOfBigBlind = getNextPlayerOnTable(indexOfSmallBlind + 1, indexOfDealer, indexOfSmallBlind);

            System.out.println("after index of big blind is: " + indexOfBigBlind + " that person is person " + playersTableOrder[indexOfBigBlind]);
        } else {
            System.out.println("Just two players left in game..now what?");
            indexOfDealer = getNextPlayerOnTable(indexOfDealer + 1, -1, -1);
            indexOfSmallBlind = indexOfDealer;
            indexOfBigBlind = getNextPlayerOnTable(indexOfDealer + 1, indexOfDealer, -1);
        }

        allPlayers[playersTableOrder[indexOfDealer]].setIsDealer(true);
        allPlayers[playersTableOrder[indexOfSmallBlind]].setIsSmallBlind(true);
        allPlayers[playersTableOrder[indexOfBigBlind]].setIsBigBlind(true);
        //  Board.lastTurnCounter = -1;
        Board.getHandInfo_fromCalculator = false;
        someoneAllIn = false;
        faceUpCardsY = BOARD_CENTRE_Y - deck.cards[0].cardHeight / 2 - (int) ((double) (BOARD_HEIGHT) * 0.01);//was 0.05
        PokerGame.displayedLastFaceUpCard = false;
        PokerGame.allOtherPlayersHaveFolded_ThisPersonWins_CompletedThisAlready = false;
        PokerGame.playerIsAllIn_moneyIsLessThanAmountToCall = false;
    }

    public static void sortPlayerHandByCardValue(Card hand[]) {
        for (int i = 1; i < 7; i++) {
            Card x = hand[i];
            int j = i - 1;
            while (j >= 0 && hand[j].value > x.value) {
                hand[j + 1] = hand[j];
                --j;
            }
            hand[j + 1] = x;
        }
    }

    public int indexOfValueWithSuit(int val, char c, Card hand[]) {
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].value == val && hand[i].suit == c) {
                return i;
            }
        }
        return -1;
    }

    public int hasStraightFlushOrRoyalFlush(Card hand[]) {//thi function requires: hasFiveSequentially, hasFiveSameSuit, whatIsSuitOfFiveOfSameSuit, indexofValueWithSuit
        int x = hasFiveSequentially(hand);//x is lowest value of the 5 in sequential order
        if (x > 0 && hasFiveSameSuit(hand) == true) {//does have 5 sequentially and does have 5 same suit
            char suit = whatIsSuitOfFiveOfSameSuit(hand);
            int indexOfLowestValSequentially = indexOfValueWithSuit(x, suit, hand);
            if (x != -1) {
                //has the lowest value of sequential order card WHICH HAS SAME suit has 5 cards suit
                for (int i = indexOfLowestValSequentially; i < 5; i++) {
                    if (hand[i].suit != suit) {
                        return 0;
                    }
                }
                return x;
            }
        } else {
            return 0;
        }

        return 0;
    }

    public static int hasFiveSequentially(Card hand[]) {//check for Ace case
        ArrayList<Card> handList = new ArrayList<Card>();
        int usedNumbers[] = new int[13];//2,3,4,5,6,7,8,9,10,j,q,k,a
        for (int i = 0; i < 7; i++) {//was ----- WHAT ABOUT DOUBLES OF NUMBERS LIKE 2 3 3 4 4 5 6
            usedNumbers[hand[i].value - 2]++;//USED, increase to 1
            if (usedNumbers[hand[i].value - 2] == 1) {//handles NOT having doubles of numbers like 2 3 4 4 5 
                handList.add(hand[i]);
                if (hand[i].value == 14) {
                    Card c = new Card(1, hand[i].suit);
                    handList.add(0, c);//add to front..shifts elements to right
                }
            }
        }

        for (int i = 0; i < handList.size() - 4; i++) {
            int val = handList.get(i).value;

            int counter = 0;
            for (int j = 0; j < 4; j++) {
                if (handList.get(j + i + 1).value == val + 1 + j) {
                    counter++;
                } else {
                    break;
                }

                if (counter == 4) {
                    System.out.println("yes  sequentially");
                    return val;
                }
            }
        }
        return -1;

    }

    public static char whatIsSuitOfFiveOfSameSuit(Card hand[]) {
        int numClubs = 0;
        int numHearts = 0;
        int numSpades = 0;
        int numDiamonds = 0;
        for (int i = 0; i < 7; i++) {//was 7
            switch (hand[i].suit) {
                case 'c':
                    numClubs++;
                    break;
                case 'd':
                    numDiamonds++;
                    break;
                case 's':
                    numSpades++;
                    break;
                case 'h':
                    numHearts++;
            }
        }

        if (numClubs >= 5) {
            return 'c';
        }
        if (numDiamonds >= 5) {
            return 'd';
        }
        if (numSpades >= 5) {
            return 's';
        }
        if (numHearts >= 5) {
            return 'h';
        }
        return 'x';
    }

    public static boolean hasFiveSameSuit(Card hand[]) {
        int numClubs = 0;
        int numHearts = 0;
        int numSpades = 0;
        int numDiamonds = 0;
        for (int i = 0; i < 7; i++) {//was 7
            switch (hand[i].suit) {
                case 'c':
                    numClubs++;
                    break;
                case 'd':
                    numDiamonds++;
                    break;
                case 's':
                    numSpades++;
                    break;
                case 'h':
                    numHearts++;
            }
        }

        if (numClubs >= 5 || numHearts >= 5 || numSpades >= 5 || numDiamonds >= 5) {
            return true;
        }
        return false;
    }

    public static int determineHighestVal(Card hand[]) {
        //this is the original, unsorted hand
        if (hand[0].value > hand[1].value) {
            return hand[0].value;
        }
        return hand[1].value;

    }

    public static int determineLowestVal(Card hand[]) {
        //this is the original, unsorted hand
        if (hand[0].value < hand[1].value) {
            return hand[0].value;
        }
        return hand[1].value;

    }

    public static int fourOfAKind(Card hand[]) {
        for (int i = 0; i < 4; i++) {//was i < 4
            int val = hand[i].value;
            if (hand[i + 1].value == val && hand[i + 2].value == val && hand[i + 3].value == val) {
                return val;
            }
        }
        return -1;
    }

    public static int threeOfAKind(Card hand[]) {
        for (int i = 0; i < 5; i++) {//was i < 5
            int val = hand[i].value;
            if (hand[i + 1].value == val && hand[i + 2].value == val) {
                return val;
            }
        }
        return -1;
    }

    public static int fullHouseLowerCard(Card hand[]) {
        for (int i = 0; i < 3; i++) {//was i < 3
            int thisValue = hand[i].value;
            if (thisValue == hand[i + 1].value && thisValue == hand[i + 2].value && hand[i + 3].value == hand[i + 4].value) {
                return hand[i + 3].value;
            }
            if (thisValue == hand[i + 1].value && hand[i + 2].value == hand[i + 3].value && hand[i + 3].value == hand[i + 4].value) {
                return thisValue;
            }
        }
        return -1;
    }

    public static int fullHouse(Card hand[]) {//return pair of 3 value
        for (int i = 0; i < 3; i++) {//was 3
            int thisValue = hand[i].value;
            if (thisValue == hand[i + 1].value && thisValue == hand[i + 2].value && hand[i + 3].value == hand[i + 4].value) {
                return thisValue;
            }
            if (thisValue == hand[i + 1].value && hand[i + 2].value == hand[i + 3].value && hand[i + 3].value == hand[i + 4].value) {
                return hand[i + 2].value;
            }
        }
        return -1;
    }

    public static int twoPair(Card hand[]) {
        int numPairs = 0;
        int higherVal = 0;
        for (int i = 0; i < 6; i++) {
            int val = hand[i].value;
            if (val == hand[i + 1].value) {
                higherVal = val;
                numPairs++;
                i++;
            }

        }
        if (numPairs == 2) {
            return higherVal;//will be higher value
        }
        return -1;
    }

    public static int onePair(Card hand[]) {
        for (int i = 0; i < 6; i++) {
            int val = hand[i].value;
            if (val == hand[i + 1].value) {
                return val;
            }
        }
        return -1;
    }

    public static int highestValOfASuit(Card hand[], char c) {
        int maxVal = -1;
        for (int i = 0; i < hand.length; i++) {
            if (hand[i].suit == c && hand[i].value > maxVal) {
                maxVal = hand[i].value;
            }
        }
        return maxVal;
    }

    public void addPlayerCardToScrollingInfoScreen(int index) {
        Card cardOne = new Card(allPlayers[index].hand[0].value, allPlayers[index].hand[0].suit);
        cardOne.cardFileName = allPlayers[index].hand[0].cardFileName;
        Card cardTwo = new Card(allPlayers[index].hand[1].value, allPlayers[index].hand[1].suit);
        cardTwo.cardFileName = allPlayers[index].hand[1].cardFileName;
        scrollingInfo.add(new New_TextOrCardDisplayObject(cardOne, cardTwo));

    }

    public int determineWinnerOfRound() { //player.endOfRound_goodnessOfHandString
        addCardToAllPlayersHands();

        for (int i = 0; i < numPlayers; i++) {//what if both have straight, both start at 5, 6, 7, 8, 9, 10, but person has HIGHER card like ace
            //add their card images to the board too!!!

            if (allPlayers[i].isFolded() == false && allPlayers[i].playerStillInGame == true) {
                Card playerHand[] = allPlayers[i].handForDeterminingWinner;
                Card originalHand[] = allPlayers[i].hand; //use this for determining highest card
                sortPlayerHandByCardValue(playerHand);
                if (hasStraightFlushOrRoyalFlush(playerHand) == 10) {//not exactly true... FIX THIS ERROR ERROR//hasFiveSameSuit(playerHand) == true && hasFiveSequentially(playerHand) >= 0 && hasFiveSequentially(playerHand) == 10) 
                    allPlayers[i].endOfRound_HandName = DASH + " Royal Flush";
                    allPlayers[i].endOfRound_handValue = 1010;
                } else if (hasStraightFlushOrRoyalFlush(playerHand) > 0) {//hasFiveSameSuit(playerHand) == true && hasFiveSequentially(playerHand)
                    allPlayers[i].endOfRound_handValue = 900 + hasStraightFlushOrRoyalFlush(playerHand);
                    allPlayers[i].endOfRound_HandName = DASH + " Straight Flush - " + valueToName(determineHighestVal(originalHand)) + " high";
                } else if (fourOfAKind(playerHand) >= 0) {
                    allPlayers[i].endOfRound_handValue = 800 + fourOfAKind(playerHand);
                    allPlayers[i].endOfRound_HandName = DASH + " Four of a Kind of " + valueToName(fourOfAKind(playerHand)) + "'s";
                } else if (fullHouse(playerHand) >= 0) {
                    allPlayers[i].endOfRound_handValue = 700 + fullHouse(playerHand);
                    allPlayers[i].endOfRound_HandName = DASH + " Full House : " + valueToName(fullHouse(playerHand)) + "'s full of " + valueToName(fullHouseLowerCard(playerHand)) + "'s";
                } else if (hasFiveSameSuit(playerHand) == true) {
                    allPlayers[i].endOfRound_handValue = 600 + determineHighestVal(originalHand);
                    allPlayers[i].endOfRound_HandName = DASH + " Flush - " + valueToName(highestValOfASuit(playerHand, whatIsSuitOfFiveOfSameSuit(playerHand))) + " high";
                } else if (hasFiveSequentially(playerHand) >= 0) {
                    allPlayers[i].endOfRound_handValue = 500 + hasFiveSequentially(playerHand);
                    allPlayers[i].endOfRound_HandName = DASH + " Straight - starts at " + valueToName(hasFiveSequentially(playerHand));
                } else if (threeOfAKind(playerHand) >= 0) {
                    allPlayers[i].endOfRound_handValue = 400 + threeOfAKind(playerHand);
                    allPlayers[i].endOfRound_HandName = DASH + " 3 of a Kind of " + valueToName(threeOfAKind(playerHand)) + "'s";
                } else if (twoPair(playerHand) >= 0) {
                    allPlayers[i].endOfRound_handValue = 300 + twoPair(playerHand);
                    allPlayers[i].endOfRound_HandName = DASH + " Two pair, pair of " + valueToName(twoPair(playerHand)) + "'s high";
                } else if (onePair(playerHand) >= 0) {
                    allPlayers[i].endOfRound_handValue = 200 + onePair(playerHand);
                    allPlayers[i].endOfRound_HandName = DASH + " Pair of " + valueToName(onePair(playerHand)) + "'s - " + valueToName(determineHighestVal(originalHand)) + " high";
                } else {
                    allPlayers[i].endOfRound_handValue = 100 + determineHighestVal(originalHand);
                    allPlayers[i].endOfRound_HandName = DASH + " High card - " + valueToName(determineHighestVal(originalHand));
                }
                System.out.println(allPlayers[i].name + allPlayers[i].endOfRound_HandName);
                scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[i].name + ":"));
                scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[i].endOfRound_HandName));
                if (i != 0) {
                    addPlayerCardToScrollingInfoScreen(i);//so we add the card image
                }

            }

        }
        //take care of tie of length j
        boolean tie = false;
        ArrayList<Player> listOfTiedPlayers = new ArrayList<Player>();
        boolean splitThePot = false;
        int highestScore = 0;
        int indexOfHighestScore = -1;
        for (int i = 0; i < numPlayers; i++) { // what about ties!!!!
            if (allPlayers[i].isFolded() == false && allPlayers[i].endOfRound_handValue > highestScore) {
                highestScore = allPlayers[i].endOfRound_handValue;
                indexOfHighestScore = i;
            }
        }
        listOfTiedPlayers.add(allPlayers[indexOfHighestScore]);
        //check for ties
        for (int i = 0; i < numPlayers; i++) {
            if (i != indexOfHighestScore && allPlayers[i].isFolded() == false && allPlayers[i].endOfRound_handValue == highestScore) {
                tie = true;
                listOfTiedPlayers.add(allPlayers[i]);
            }
        }

        if (tie == true) {
            scrollingInfo.add(new New_TextOrCardDisplayObject("There was a tie between"));
            for (int u = 0; u < listOfTiedPlayers.size(); u++) {
                scrollingInfo.add(new New_TextOrCardDisplayObject(listOfTiedPlayers.get(u).name));
            }
            scrollingInfo.add(new New_TextOrCardDisplayObject("They each had"));
            scrollingInfo.add(new New_TextOrCardDisplayObject(listOfTiedPlayers.get(0).endOfRound_HandName));

            if (highestScore >= 900) {//straight flush or royal flush //-------------- THIS NUMBER SHOULD BE 900 but made it 0 just for testing...
                splitThePot = true;
                splitPot_inTwo(listOfTiedPlayers);
            } else {

                scrollingInfo.add(new New_TextOrCardDisplayObject("Checking next highest cards..."));

                //go through list of tied players..check their highest cards..then check their next highest cards..then split pot
                int valOfHighestCard = 0, valOfNextHighestCard = 1;
                int indexOfWinner = 0;
                boolean mustCheckSecondHighestCard = false;
                Player winnerBasedOffFirstHighestCard = new Player();
                ArrayList<Player> listOfMoreTiedHighestCardPlayers = new ArrayList<Player>();
                for (int i = 0; i < listOfTiedPlayers.size(); i++) {
                    int z = determineHighestVal(listOfTiedPlayers.get(i).hand);
                    if (z > valOfHighestCard) {//checks which of all of the players which tied has the highest card
                        valOfHighestCard = z;
                        //indexOfWinner = i;
                        winnerBasedOffFirstHighestCard = listOfTiedPlayers.get(i);
                    } else if (z == valOfHighestCard) {//two or more players have the same highest card, so we must check second highest card
                        //previous index of winner 
                        mustCheckSecondHighestCard = true;

                    }
                }

                if (mustCheckSecondHighestCard == false) {
                    //we know the winner!
                    indexOfHighestScore = playerNameToIndex(winnerBasedOffFirstHighestCard.name); //no one had the same highest card, so the person we found up there is the winner ^^
                } else {
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Both players have the same"));
                    scrollingInfo.add(new New_TextOrCardDisplayObject("higher cards!"));
                    for (int i = 0; i < listOfTiedPlayers.size(); i++) {
                        int z = determineHighestVal(listOfTiedPlayers.get(i).hand);
                        if (z == valOfHighestCard) {
                            listOfMoreTiedHighestCardPlayers.add(listOfTiedPlayers.get(i));//add Players to the listOfMoreTiedHighestCardPlayers list 
                        }
                    }
                    int highest_secondHard = 0;
                    splitThePot = false;
                    Player winnerBasedOffSecondCard = new Player();
                    ArrayList<Player> tiedPlayersByEnd = new ArrayList<Player>();

                    for (int i = 0; i < listOfMoreTiedHighestCardPlayers.size(); i++) {
                        int h = determineLowestVal(listOfTiedPlayers.get(i).hand);
                        if (h > highest_secondHard) {
                            highest_secondHard = h;
                            winnerBasedOffSecondCard = listOfMoreTiedHighestCardPlayers.get(i);//see who has the highest other (lowest) card!
                            tiedPlayersByEnd.add(winnerBasedOffSecondCard);
                        } else if (h == highest_secondHard) {
                            splitThePot = true; // two or more people have the same higher and lower cards! so we must split the pot
                            tiedPlayersByEnd.add(listOfMoreTiedHighestCardPlayers.get(i));
                        }
                    }
                    if (splitThePot == false) {//go from the person's name to their index (from 0 to 10)..(it's what is used below)....
                        indexOfHighestScore = playerNameToIndex(winnerBasedOffSecondCard.name);

                        //indexOfHighestScore = indexOfWinnerBasedOffOfSecondCard;
                    } else {
                        splitThePot = true;
                        splitPot_inTwo(tiedPlayersByEnd);
                        //split the pot

                    }
                    //determineLowestVal
                    //now have list of people who had same highest cards
                }
            }
        }
        if (splitThePot == false) {
            winnerIndex = indexOfHighestScore;
            allPlayers[indexOfHighestScore].playerColour = PokerGame.WINNING_COLOUR;
            if (indexOfHighestScore == 0) {
                nameOfWinner = "User";
            } else {
                nameOfWinner = allPlayers[indexOfHighestScore].name;
            }
            scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[indexOfHighestScore].name + " wins! Their hand:"));
            scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[indexOfHighestScore].endOfRound_HandName));
            scrollingInfo.add(new New_TextOrCardDisplayObject("They receive $" + this.pot));
            roundChipsPotToInt();
            allPlayers[indexOfHighestScore].determineMoney();
            for (int i = 0; i < 6; i++) {//give that player the money by increasing their chips
                allPlayers[indexOfHighestScore].chips[i][1] += this.potCopy_ofChips[i];

            }
            allPlayers[indexOfHighestScore].determineMoney();
            PokerGame.checkIfGameOver();
        } else {

        }

        return 0;//index of player in allPlayers 0 - 10
    }

    public void splitPot_inTwo(ArrayList<Player> playersToGetMoney) {//0 to 10, NOT from around table
        int numTiedPlayers = playersToGetMoney.size();
        scrollingInfo.add(new New_TextOrCardDisplayObject("Spliting the pot..."));
        scrollingInfo.add(new New_TextOrCardDisplayObject("Winners are: "));
        for (int k = 0; k < numTiedPlayers; k++) {
            scrollingInfo.add(new New_TextOrCardDisplayObject(DASH + " " + playersToGetMoney.get(k).name));
        }
        scrollingInfo.add(new New_TextOrCardDisplayObject("They each get: $" + this.pot / numTiedPlayers));//not quite accurate but yolo
        for (int j = 0; j < numTiedPlayers; j++) {
            for (int i = 0; i < 6; i++) {
                playersToGetMoney.get(j).chips[i][1] += this.potCopy_ofChips[i] / numTiedPlayers;
            }
        }
    }

    public static int playerNameToIndex(String sss) {
        if (sss.equalsIgnoreCase("User")) {
            return 0;
        } else if (sss.equalsIgnoreCase("Comp 1")) {
            return 1;
        } else if (sss.equalsIgnoreCase("Comp 2")) {
            return 2;
        } else if (sss.equalsIgnoreCase("Comp 3")) {
            return 3;
        } else if (sss.equalsIgnoreCase("Comp 4")) {
            return 4;
        } else if (sss.equalsIgnoreCase("Comp 5")) {
            return 5;
        } else if (sss.equalsIgnoreCase("Comp 6")) {
            return 6;
        } else if (sss.equalsIgnoreCase("Comp 7")) {
            return 7;
        } else if (sss.equalsIgnoreCase("Comp 8")) {
            return 8;
        } else if (sss.equalsIgnoreCase("Comp 9")) {
            return 9;
        }
        return 99;
    }

    public void roundChipsPotToInt() {
        int x = 0;
        for (int i = 0; i < 6; i++) {
            System.out.println("$" + allPlayers[0].chips[i][0] + " x " + this.potCopy_ofChips[i]);
            x += this.potCopy_ofChips[i] * allPlayers[0].chips[i][0];
        }
        System.out.println("round chips to pot int is " + x);
    }

    public String valueToName(int x) {

        switch (x) {
            case 1:
                return "A";
            case 2:
                return Integer.toString(x);
            case 3:
                return Integer.toString(x);
            case 4:
                return Integer.toString(x);
            case 5:
                return Integer.toString(x);
            case 6:
                return Integer.toString(x);
            case 7:
                return Integer.toString(x);
            case 8:
                return Integer.toString(x);
            case 9:
                return Integer.toString(x);
            case 10:
                return Integer.toString(x);
            case 11:
                return "J";
            case 12:
                return "Q";
            case 13:
                return "K";
            case 14:
                return "A";

        }
        return "";
    }

    public void resetRound() throws SlickException {
        //check if any players have no more money. if so...remove them from the game
        for (int i = 0; i < numPlayers; i++) {
            if (allPlayers[i].money <= 0) {
                allPlayers[i].playerStillInGame = false;
            }
        }
        someoneIsAllIn = false;
        Board.getHandInfo_fromCalculator = false;
        PokerGame.someoneAutomaticallyWins = false;
        PokerGame.allowedToMoveOnToNextTurnCounterIndex = false;
        PokerGame.alreadyDidThisInRound = false;
        lastRaiseAmount = 0;
        deck.resetDeck();
        lastBet = 0;
        this.setShowFlop(false);
        this.setShowRiver(false);
        this.setShowTurn(false);
        deltDeck = false;
        someoneIsAllIn = false;
        getNextRoundButtonClicked = false;
        getBeforeTime_forRound = false;
        this.amountToCall = 0;
        turnCounter = 0;
        round_turnCounter = 0;
        userIsDealerSoGoToEnd_completedThisAlreaday = false;
        printedOutLast_userItIsYourTurn = 0;
        for (int i = 0; i < faceUpCards.length; i++) {
            faceUpCards[i] = null;
        }
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].playerColour = PokerGame.NORMAL_PLAYER_COLOR;
            allPlayers[i].totalBetThisRound = 0;
            allPlayers[i].setFolded(false);
            for (int j = 0; j < 7; j++) {
                allPlayers[i].hand[j] = null;//RESET EVERY PLAYERS HAND
            }
        }
        resetTurn();
    }

    public void resetTurn() {
        lastBet = 0;
        PokerGame.allowedToMoveOnToNextTurnCounterIndex = false;
        PokerGame.alreadyDidThisInRound = false;
        lastRaiseAmount = 0;
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].thisBetAmount = 0;
            allPlayers[i].totalBetThisTurn_perShowOfCards = 0;
            allPlayers[i].allowedToRaise = true;
            if (allPlayers[i].isFolded() == false) {
                allPlayers[i].move = "";
            }

        }
        this.amountToCall = 0;
        printedOutLast_userItIsYourTurn = 0;
        minimumBet = minimumRaise + amountToCall;
        gotFirstRoundUpToUserMove = false;
        gameplayMoveOn = true;
        gotUserMove = false;
        getRestOfPlayers_untilEveryoneHasCalledOrFolded = false;
        doOnce = false;
        finishedTurn = false;
        getBeforeTime = true;
        userIsDealerSoGoToEnd_completedThisAlreaday = false;//trying this out
    }// this resets the variables for the roudn stored in PokerGame.. obviousy its a new round so constructor will be called

    public boolean determineIfLargestBet(int x) {

        if (x > amountToCall) {
            setAmountToCall(x);
            return true;      //reset every other player's "move string" to null
        }
        return false;
    }

    public static void userEndTurn() {
        System.out.println("USER END TURN");
        allPlayers[0].thisBetAmount = 0;
        allPlayers[0].determineMoney();
        gotUserMove = true;
        getUserMove = false;
    }

    public int getAmountToCall() {
        return amountToCall;
    }

    public void setAmountToCall(int amountToCall) {
        this.amountToCall = amountToCall;
        minimumBet = minimumRaise + amountToCall;
    }

    public static int getNextPlayerOnTable(int indexOnTable, int otherIndex1, int otherIndex2) {//returns index of next player, index ON TABLE so player3 is index 0, user is index 4
        indexOnTable = indexOnTable % 10;
        boolean foundNextPlayer = false;
        while (foundNextPlayer == false) {
            if (playersTableOrder[indexOnTable] < numPlayers && allPlayers[playersTableOrder[indexOnTable]].playerStillInGame == true) {//allowable player number
                //but that player can't already have a position
                if (indexOnTable != otherIndex1 && indexOnTable != otherIndex2) {
                    return indexOnTable;
                }
            }
            indexOnTable++;
            if (indexOnTable == 10) {
                indexOnTable = 0; //loop to beginning
            }
        }
        return -1;
    }

    public void deal() {
        //shuffle then deal
        deck.shuffle();
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].hand[0] = deck.getNextCard();
        }
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].hand[1] = deck.getNextCard();
        }
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].playerToString();
        }
    }

    public void addCardToAllPlayersHands() {
        System.out.println(faceUpCards.length);
        for (int i = 0; i < 5; i++) {
            if (faceUpCards[i] == null) {
                System.out.println("face up card at " + i + " is null.");
            }
        }
        for (int i = 0; i < numPlayers; i++) {
            for (int j = 2; j < 7; j++) {
                allPlayers[i].hand[j] = faceUpCards[j - 2];
            }
        }

        //so need to make copy card hand
        for (int i = 0; i < numPlayers; i++) {//what about the ace
            for (int j = 0; j < 7; j++) {
                Card c = new Card(allPlayers[i].hand[j].value, allPlayers[i].hand[j].suit);
                allPlayers[i].handForDeterminingWinner[j] = c;
            }
        }
    }

    public void displayRound(Graphics g) throws SlickException {//faceUpCardsY
        if (winnerIndex != -1) {
            allPlayers[winnerIndex].playerColour = PokerGame.WINNING_COLOUR;
        }
        String ss = "Min. Bet: $" + PokerGame.allRounds.get(roundCounter).minimumBet;
        totalPot_slickFont.drawString(betButton.getX() - totalPot_slickFont.getWidth(ss) * 2 / 3, Player.playerUserDisplayTopY - Player.userDisplayerGap - totalPot_slickFont.getHeight(ss), ss, Color.white);
        totalPot_slickFont.drawString(BOARD_CENTRE_X - BOARD_WIDTH / 7 - totalPot_slickFont.getWidth(potString) / 2, BOARD_CENTRE_Y + BOARD_HEIGHT / 6, potString);
        totalPot_slickFont.drawString(BOARD_CENTRE_X + BOARD_WIDTH / 7 - totalPot_slickFont.getWidth("Call: $" + Integer.toString(amountToCall)) / 2, BOARD_CENTRE_Y + BOARD_HEIGHT / 6, "Call: $" + Integer.toString(amountToCall));
        //amountToCall
        if (showFlop == false && showRiver == false && showTurn == false) {
            nameOfFaceUpCardsOnTable = "Pre-Flop";
        }
        if (showFlop == true) {
            nameOfFaceUpCardsOnTable = "The Flop";
            for (int i = 0; i < 3; i++) {
                if (faceUpCards[i] == null) {
                    faceUpCards[i] = deck.getNextCard();
                }
                if (doThisOnce_forShowingCards = true) {
                    faceupCard1X = (int) (BOARD_CENTRE_X - faceUpCards[0].cardWidth * 2.5 - FACE_UP_CARD_SPACER * 2);
                    doThisOnce_forShowingCards = false;
                }

                faceUpCards[i].setxLoc(faceupCard1X + i * (faceUpCards[0].cardWidth + FACE_UP_CARD_SPACER));
                faceUpCards[i].setyLoc(faceUpCardsY);
                faceUpCards[i].setIsFaceUp(true);

                faceUpCards[i].drawCard(g, FACE_UP_CARD_SCALE, 0, false);
            }

        }
        if (showTurn == true) {
            nameOfFaceUpCardsOnTable = "The Turn";
            for (int i = 3; i < 4; i++) {
                if (faceUpCards[i] == null) {
                    faceUpCards[i] = deck.getNextCard();
                    faceUpCards[i].setxLoc(faceupCard1X + i * (faceUpCards[0].cardWidth + FACE_UP_CARD_SPACER));
                    faceUpCards[i].setyLoc(faceUpCardsY);
                    faceUpCards[i].setIsFaceUp(true);
                }
                faceUpCards[i].drawCard(g, FACE_UP_CARD_SCALE, 0, false);
            }
        }
        if (showRiver == true) {
            PokerGame.displayedLastFaceUpCard = true;
            nameOfFaceUpCardsOnTable = "The River";
            for (int i = 4; i < 5; i++) {
                if (faceUpCards[i] == null) {
                    faceUpCards[i] = deck.getNextCard();
                    if (faceUpCards[i] == null) {
                        System.out.println("SOMEHOW IT IS STILL NULL");
                    }
                    faceUpCards[i].setxLoc(faceupCard1X + i * (faceUpCards[0].cardWidth + FACE_UP_CARD_SPACER));
                    faceUpCards[i].setyLoc(faceUpCardsY);
                    faceUpCards[i].setIsFaceUp(true);
                }
                faceUpCards[i].drawCard(g, FACE_UP_CARD_SCALE, 0, false);
            }
        }
        totalPot_slickFont.drawString(BOARD_CENTRE_X - BOARD_WIDTH / 5 - totalPot_slickFont.getWidth("BB: $" + this.bigBlindValue) / 2, BOARD_CENTRE_Y - BOARD_HEIGHT / 6 - (int) (totalPot_slickFont.getHeight("T") * 1.2), "BB: $" + this.bigBlindValue, Color.white);
        totalPot_slickFont.drawString(BOARD_CENTRE_X - totalPot_slickFont.getWidth(nameOfFaceUpCardsOnTable) / 2, BOARD_CENTRE_Y - BOARD_HEIGHT / 6 - (int) (totalPot_slickFont.getHeight("T") * 1.2), nameOfFaceUpCardsOnTable, Color.white);
        totalPot_slickFont.drawString(BOARD_CENTRE_X + BOARD_WIDTH / 5 - totalPot_slickFont.getWidth("Round # " + (PokerGame.roundCounter + 1)) / 2, BOARD_CENTRE_Y - BOARD_HEIGHT / 6 - (int) (totalPot_slickFont.getHeight("T") * 1.2), "Round # " + (PokerGame.roundCounter + 1), Color.white);

        if (this.isRoundOver() == true) {
            displayBouncingWinnerName(g);
        }

    }

    public void displayBouncingWinnerName(Graphics g) {
        //for some reason, BOTTOM Y is at the top!
        //public BouncingString(String w, int topY_boundary, int bottomY_boundary, int leftX_boundary, int rightX_boundary, Graphics g) {
        if (bouncingWinnerString == null) {
            bouncingWinnerString = new BouncingString(this.nameOfWinner + " Wins!", PokerGame.BOARD_TOP_Y + PokerGame.BOARD_HEIGHT, PokerGame.BOARD_TOP_Y, PokerGame.BOARD_LEFT_X, PokerGame.BOARD_LEFT_X + PokerGame.BOARD_WIDTH, g);
            bouncingWinnerMoneyString = new BouncingString("$" + this.pot, PokerGame.BOARD_TOP_Y + PokerGame.BOARD_HEIGHT, PokerGame.BOARD_TOP_Y, PokerGame.BOARD_LEFT_X, PokerGame.BOARD_LEFT_X + PokerGame.BOARD_WIDTH, g);
        }
        bouncingWinnerString.drawBouncingString();
        bouncingWinnerMoneyString.drawBouncingString();

        if (entireGameOver == true){
             if (PokerGame.bouncingGameOverString == null) {
               PokerGame.bouncingGameOverString = new BouncingString("Game Over", PokerGame.BOARD_TOP_Y + PokerGame.BOARD_HEIGHT, PokerGame.BOARD_TOP_Y, PokerGame.BOARD_LEFT_X, PokerGame.BOARD_LEFT_X + PokerGame.BOARD_WIDTH, g);
            } else {
                PokerGame.bouncingGameOverString.drawBouncingString();
            } 
        }
        if (entireGameOver == true && userWon == false) {
            if (bouncingUserLostGameString == null) {
                bouncingUserLostGameString = new BouncingString("You Lost!", PokerGame.BOARD_TOP_Y + PokerGame.BOARD_HEIGHT, PokerGame.BOARD_TOP_Y, PokerGame.BOARD_LEFT_X, PokerGame.BOARD_LEFT_X + PokerGame.BOARD_WIDTH, g);
            } else {
                bouncingUserLostGameString.drawBouncingString();
            }
        } else if (entireGameOver == true && userWon == true) {
            if (bouncingUserWonGameString == null) {
               bouncingUserWonGameString = new BouncingString("You Won!", PokerGame.BOARD_TOP_Y + PokerGame.BOARD_HEIGHT, PokerGame.BOARD_TOP_Y, PokerGame.BOARD_LEFT_X, PokerGame.BOARD_LEFT_X + PokerGame.BOARD_WIDTH, g);
            } else {
                bouncingUserLostGameString.drawBouncingString();
            }
        }
    }

    public int getPot() {
        return pot;
    }

    public void increasePot(int x) {
        if (x > lastBet) {
            lastRaiseAmount = x - lastBet;
        }
        lastBet = x;
        this.pot += x;
        this.potString = "Total Pot: $" + pot + "";
    }

    public void setPot(int pot) {
        this.pot = pot;
        this.potString = "Total Pot: $" + pot + "";
    }

    public boolean isShowFlop() {
        return showFlop;
    }

    public void setShowFlop(boolean showFlop) {
        this.showFlop = showFlop;
    }

    public boolean isShowTurn() {
        return showTurn;
    }

    public void setShowTurn(boolean showTurn) {
        this.showTurn = showTurn;
    }

    public boolean isShowRiver() {
        return showRiver;
    }

    public void setShowRiver(boolean showRiver) {
        this.showRiver = showRiver;
    }

    public boolean isRoundOver() {
        return roundOver;
    }

    public void setRoundOver(boolean roundOver) {
        this.roundOver = roundOver;
    }

}
