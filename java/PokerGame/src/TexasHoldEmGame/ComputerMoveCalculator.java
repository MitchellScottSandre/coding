/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import java.util.ArrayList;

/**
 *
 * @author scott
 */
public class ComputerMoveCalculator {

    public static int reducePlayerBluffLikliness = 11;//10 is regular
    public int numSequentially, lowestValOfNumSequentially, numSameSuit, numSameVal, valOfNumbersWithSameVal;
    public int recommendedAmountToBet = 0;
    public String recommendedDecision = "";
    public String decision = "";
    public int amountToCall;
    public int playerMoney, turnCounter;
    public String name = "";
    public boolean betAtLeastToCall = false;
    public int minRaise, lastRaiseAmount;
    public Card playerCards[];
    public Card allCards[];
    public Card faceUpCards[];
    public int personality;
    public String personalityString, finalHandName = "";
    public String bestHandFromCardsNickNameString = "";
    public boolean canRaise = true;
    public boolean shouldProbablyFold = false;
    public int numOuts = 1, sumPoints = 0;
    public double percentChanceOfMakingHand = 0, potOdds_percentage, chanceOfMakingHand_allInOnFlop;
    public int howEasyToBluff_level = 0;//high, means they fold easily. low, means they are likely to call
    public int bettingRiskTolerance_level = 0; //0 means doesn't raise often, 10 means will raise and go big ---> minimum number must be 1
    public int willBluff_level = 0;//how often they will bluff. 10 is frequently, 0 is rarely
    public int patience_level = 0;//how picky they are for playing only with a good hand; 0 means VERY selective, 0 means will call often regardless of quality of hand
    public static int howGoodIsPlayerHand = 0;
    public static double allPokerOddsInformation[][] = {
        //flop to turn;;;; turn to river;;;turn and river;;;----> TURN TO RIVER occurs when someone goes all in!!!!
        //3 cards dealth/4 cards dealth/5 cards dealt-->
        {2.1, 2.2, 4.3},//1 out
        {4.3, 4.3, 8.4},
        {6.4, 6.5, 12.5},
        {8.5, 8.7, 16.5},
        {10.6, 10.9, 20.3},
        {12.8, 13.0, 24.1},
        {14.9, 15.2, 27.8},
        {17.0, 17.4, 31.5},
        {19.1, 19.6, 35.0},
        {21.3, 21.7, 38.4},
        {23.4, 23.9, 41.7},
        {25.5, 26.1, 45.0},
        {27.7, 28.3, 48.1},
        {29.8, 30.4, 51.2},
        {31.9, 32.6, 54.1},
        {34.0, 34.8, 57.0},
        {36.2, 37.0, 59.8},
        {38.3, 39.1, 62.4},
        {40.4, 41.3, 65.0},
        {42.6, 43.5, 67.5}//20 outs
    };
    
    public double getchanceOfMakingHand_allInOnFlop(){
        return this.chanceOfMakingHand_allInOnFlop;
    }

    public String getNameOfHand_fromOdds() {
        return this.bestHandFromCardsNickNameString;
    }

    public double getPotOdds_Percentage() {
        return this.potOdds_percentage;
    }

    public double getPlayerOdds_Percentage() {
        return this.percentChanceOfMakingHand;
    }

    //need to recognize what type of players OTHER players are...if they are a tight passive and bet big..maybe don't call!!!
    public ComputerMoveCalculator(int personality, String name, int amountToCall, int playerMoney, int playerChips[][], Card playerOwnCards[], Card faceUpCards[], int turnCounter, int minRaise, int amountBetSoFarThisTurn, boolean canRaise, int lastRaiseAmount, int pot) {
        this.playerCards = playerOwnCards;
        this.recommendedAmountToBet = 0;
        this.canRaise = canRaise;
        this.turnCounter = turnCounter;
        this.potOdds_percentage = 100 * (double) ((double) (amountToCall) / (double) ((pot + amountToCall)));
        this.personality = personality;
        this.amountToCall = amountToCall;
        this.name = name;
        this.minRaise = minRaise;
        this.playerMoney = playerMoney;
        this.lastRaiseAmount = lastRaiseAmount;
        this.faceUpCards = faceUpCards;
        //variables that will change based on player's personalities
        //all levels from 1 to 10

        switch (personality) {
            case PokerGame.LOOSE_AGGRESSIVE:
                personalityString = PokerGame.namesOfPlayerTypes[PokerGame.LOOSE_AGGRESSIVE];
                howEasyToBluff_level = randomIntWithin(1, 3);
                bettingRiskTolerance_level = randomIntWithin(5, 9);
                willBluff_level = randomIntWithin(5, 10);
                patience_level = randomIntWithin(2, 6);
                break;
            case PokerGame.LOOSE_PASSIVE:
                personalityString = PokerGame.namesOfPlayerTypes[PokerGame.LOOSE_PASSIVE];
                howEasyToBluff_level = randomIntWithin(1, 4);
                bettingRiskTolerance_level = randomIntWithin(1, 4);
                willBluff_level = randomIntWithin(1, 3);
                patience_level = randomIntWithin(2, 4);
                break;
            case PokerGame.TIGHT_AGGRESSIVE:
                personalityString = PokerGame.namesOfPlayerTypes[PokerGame.TIGHT_AGGRESSIVE];
                howEasyToBluff_level = randomIntWithin(3, 5);
                bettingRiskTolerance_level = randomIntWithin(6, 10);
                willBluff_level = randomIntWithin(1, 3);
                 = randomIntWithin(6, 10);
                break;
            case PokerGame.TIGHT_PASSIVE:
                personalityString = PokerGame.namesOfPlayerTypes[PokerGame.TIGHT_PASSIVE];
                howEasyToBluff_level = randomIntWithin(4, 6);
                bettingRiskTolerance_level = randomIntWithin(1, 3);
                willBluff_level = randomIntWithin(1, 4);
                patience_level = randomIntWithin(7, 10);
                break;
        }

        allParametersToString(amountToCall, playerMoney, playerChips, playerOwnCards, faceUpCards, turnCounter, minRaise, amountBetSoFarThisTurn);

        //what if the previous person raised by a lot??
        if (lastRaiseAmount >= minRaise * howEasyToBluff_level * 3 / 2) {
            int k = randomIntWithin(1, 10);
            System.out.println("How easy to bluff: " + howEasyToBluff_level + ", and k is " + k);
            System.out.println("if k is less than or equal to how easy to bluff level then will fold");
            if (k <= howEasyToBluff_level) {
                System.out.println("setting should probably fold to true");
                this.shouldProbablyFold = true; //however, will NOT fold if we have an amazing hand
            }
        }

         sumPoints = 0;
        int numMultipliedByMinRaise = 0;
        switch (turnCounter) {
            case 0://just the player's TWO CARDS
                allCards = playerOwnCards;
                sumPoints += qualityOfPlayersOwnTwoCards(playerOwnCards);
                findBestTwoCardHandName();
                if (shouldProbablyFold == true && sumPoints > bettingRiskTolerance_level * 3) {
                    System.out.println("Should probably fold was true...but player has a great hand...so not going to fold");
                    shouldProbablyFold = false;
                }
                if (shouldProbablyFold == false) {
                    if (sumPoints >= 20) {//pair of high cards  
                        numMultipliedByMinRaise = bettingRiskTolerance_level;
                        recommendedAmountToBet = amountToCall + betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 6, 8//use bettingRiskTolerance_level here
                    } else if (sumPoints >= 14) {
                        numMultipliedByMinRaise = thisNumberOrAtLeastNumber1((int) (bettingRiskTolerance_level / 1.5));//divide by 1.5
                        recommendedAmountToBet = amountToCall + betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 1, 5
                    } else if (sumPoints >= 8) {
                        numMultipliedByMinRaise = ((int) (bettingRiskTolerance_level / 2.5));//divide by 2.5
                        recommendedAmountToBet = amountToCall + betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 0 , 3
                    } else if (sumPoints >= 0) {
                        numMultipliedByMinRaise = (bettingRiskTolerance_level / 4);
                        recommendedAmountToBet = amountToCall + betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 0, 1
                    }
                } else {
                    fold();
                }
                System.out.println("Turn Counter is 0:");
                System.out.println("sumPoints was " + sumPoints + " and multiplied min raise by " + numMultipliedByMinRaise);

                break;
            //for hand decision calculation, remember to use 1)percentChanceOfMakingHand and 2)potOdds_percentage
            case 1://players 2 cards + faceUpCards 1 2 3
                allCards = new Card[5];
                allCards[0] = playerOwnCards[0];
                allCards[1] = playerOwnCards[1];//
                allCards[2] = faceUpCards[0];
                allCards[3] = faceUpCards[1];
                allCards[4] = faceUpCards[2];
                sumPoints = determineHowGoodHandIs(3, 5);
                System.out.println("chance of making hand: " + percentChanceOfMakingHand + ", pot odds: " + potOdds_percentage);
                setRecommendedBetBasedOnPlayerVariablesAndOdds(sumPoints);
                break;
            case 2: //player 2 cards + faceUpCards 1 2 3 4
                allCards = new Card[6];
                allCards[0] = playerOwnCards[0];
                allCards[1] = playerOwnCards[1];
                allCards[2] = faceUpCards[0];
                allCards[3] = faceUpCards[1];
                allCards[4] = faceUpCards[2];
                allCards[5] = faceUpCards[3];
                sumPoints = determineHowGoodHandIs(4, 6);
                setRecommendedBetBasedOnPlayerVariablesAndOdds(sumPoints);
                System.out.println("chance of making hand: " + percentChanceOfMakingHand + ", pot odds: " + potOdds_percentage);
                break;
            case 3: //player 2 cards + faceUpCards 1 2 3 4 5 
            case 4:
                allCards = new Card[7];
                allCards[0] = playerOwnCards[0];
                allCards[1] = playerOwnCards[1];
                allCards[2] = faceUpCards[0];
                allCards[3] = faceUpCards[1];
                allCards[4] = faceUpCards[2];
                allCards[5] = faceUpCards[3];
                allCards[6] = faceUpCards[4];
                sumPoints = determineHowGoodHandIs(5, 7); //sum points is between 2 and 126
                this.bestHandFromCardsNickNameString = finalHandName;
                //we now know how good our hand is 
                //now, call, bet, or fold accordingly
                break;
        }//soooo need to take make functions that take NUMCARDS ON TABLE _ and _ those 5 main variables and outputs percent chance royal flush, then other
        //function which outputs the chance of a straigt etc etc etc

        howGoodIsPlayerHand = sumPoints;
        if (bestHandFromCardsNickNameString.equals("") || bestHandFromCardsNickNameString.equals(" ")){
           bestHandFromCardsNickNameString = finalHandName;
        }
        if (amountToCall >= playerMoney / 2){//someone just bet a crap ton of money
            //only call it if you have a good hand
            System.out.println("Amount to call is really big... sumPoints is " + sumPoints);
            if (this.turnCounter == 0 && sumPoints < 15 - this.bettingRiskTolerance_level || (this.turnCounter > 0 && sumPoints < 55 - this.bettingRiskTolerance_level)){//change these numbers to dictate how often they fold when someone goes all in
                    fold();
            } 
        }

        System.out.println("...." + recommendedAmountToBet);
    }

    public boolean handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(int val1, char suit, int val2) {//x for ANY SUIT, y for same suit
        int goUpToThisNumberForI = 2;//go up to < 2 // 0 1
        if (allCards.length == 2){//only two cards
            goUpToThisNumberForI = 1;
        }
        
        for (int j = 0; j < goUpToThisNumberForI; j++) {//iterate through just the players own first two cards --
            System.out.println("_______" + j);
            for (int k = j + 1; k < allCards.length; k++) {//NOT SORTED
                System.out.println(".........." + j);
                if ( (allCards[j].value == val1 && allCards[k].value == val2 )|| (allCards[j].value == val2 && allCards[k].value == val1)) {//left card is v1, right card is v2
                    if (suit == 'x') {//suit doesn't matter
                        return true;
                    } else if (allCards[j].suit ==  allCards[k].suit) {
                        return true;
                    }
                } 

            }

        }
        
        
        return false;
    }

    public void findBestTwoCardHandName() {//could be 2, 5, 6, or 7 cards

        //sortPlayerHandByCardValue(allCards.length); --. NOT SORTED ... NOPE. THIS ENDED UP BEING ONLY LENGTH TWO WOW WELL DONE SCOTT NICE NICE
        if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(14, 'x', 14) == true) {//aces, any suit
            bestHandFromCardsNickNameString = "Pocket Rockets";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(13, 'y', 14) == true) {//king, then ace, same suit
            bestHandFromCardsNickNameString = "Big Slick in a Suit";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(13, 'x', 14) == true) {
            bestHandFromCardsNickNameString = "Full Auto";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(8, 'x', 14) == true) {
            bestHandFromCardsNickNameString = "Dead Man's Hand";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(13, 'x', 13) == true) {
            bestHandFromCardsNickNameString = "Cowboys King Kong";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(9, 'x', 14) == true) {
            bestHandFromCardsNickNameString = "The Dog";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(12, 'x', 12) == true) {
            bestHandFromCardsNickNameString = "Ladies";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(3, 'x', 12) == true) {
            bestHandFromCardsNickNameString = "The Waiter";
        } else if (handContainsTheseTwoCards_With_AtLeastOneFromPlayerOriginalHand(9, 'x', 9) == true) {
            bestHandFromCardsNickNameString = "A German Dime";
        } else if (allCards[0].value == allCards[1].value) {
            bestHandFromCardsNickNameString = "Pocket Pair";
        } else if (allCards[0].suit == allCards[1].suit) {
            bestHandFromCardsNickNameString = "Flush Rush";
        } else if (allCards[0].value == allCards[1].value + 1   ||    allCards[0].value + 1 == allCards[1].value   || (allCards[0].value == 14 && allCards[1].value == 2) || (allCards[0].value == 2 && allCards[1].value == 14) ) {
            bestHandFromCardsNickNameString = "Straight Bait";
        } else if (allCards[0].value == 14 || allCards[1].value == 14) {
            bestHandFromCardsNickNameString = "A Spike";
        } else if (allCards[0].value == 13 || allCards[1].value == 13) {
            bestHandFromCardsNickNameString = "A Dame";
        } else if (allCards[0].value == 12 || allCards[1].value == 12) {
            bestHandFromCardsNickNameString = "A Jackal";
        } else if (allCards[0].value == 11 || allCards[1].value == 11) {
            bestHandFromCardsNickNameString = "A Fishhook";
        } else if (allCards[0].value == 10 || allCards[1].value == 10) {
            bestHandFromCardsNickNameString = "A Sawbuck";
        } else if (allCards[0].value == 9 || allCards[1].value == 9) {
            bestHandFromCardsNickNameString = "Nina Ross";
        } else  {
            bestHandFromCardsNickNameString = "Down on Your Luck";
        }
        
        System.out.println("findBestTwoCardHandName---->>> " + bestHandFromCardsNickNameString);

    }

    public void setRecommendedBetBasedOnPlayerVariablesAndOdds(int sumPointsOfHand) {
        boolean atLeastCall = true;
        boolean folded = false;
        //aggressive player: greater than 60 - 20 = 40 --> at least CALL
        //what if someone just did a big raise?
        //someone just did a big raise                              AND     (    i have a bad hand                           and               low odds                                            )
        if (lastRaiseAmount >= minRaise * (15 - howEasyToBluff_level) && (sumPointsOfHand < 75 - bettingRiskTolerance_level && percentChanceOfMakingHand + bettingRiskTolerance_level < potOdds_percentage)) {
            //maybe maybe bluff
            System.out.println("previous player just did a big raise! we have a bad hand...maybe should fold");
            System.out.println("checking if maybe we bluff...");
            boolean bluffed = maybeBluff_makeRandomNumberAndMaybeTheyWill();
            if (bluffed == false) {
                folded = true;
                System.out.println("nope. we are folding.");
                fold();
            }
        }
        System.out.println("blah");
        if (folded == false) {
            //lowest is about 25 avg
            if (sumPointsOfHand >= 32 - 2 * bettingRiskTolerance_level || percentChanceOfMakingHand - howEasyToBluff_level / 3 > potOdds_percentage) {//so, sumPoints is between 2 and 126..so if it is 60 it will beat half of hands
                atLeastCall = true;
                System.out.println("set recommended amount to bet to amount to call");
                recommendedAmountToBet = amountToCall;
                System.out.println("sum points of hand greater than 55 - 2 x betting risk tolerance");
                int numMultipliedByMinRaise;
                //in these two cases, RAISE
                //  System.out.println("zz is " +  65 - 2 * bettingRiskTolerance_level)
                if (sumPointsOfHand >= 65 - 2 * bettingRiskTolerance_level) {//pair of high cards 
                    numMultipliedByMinRaise = thisNumberOrAtLeastNumber1((int) (bettingRiskTolerance_level / 1.5));
                    recommendedAmountToBet += betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 6, 8//use bettingRiskTolerance_level here
                } else if (sumPointsOfHand >= 55 - 3 * bettingRiskTolerance_level) {
                    numMultipliedByMinRaise = thisNumberOrAtLeastNumber1((int) (bettingRiskTolerance_level / 2.3));//divide by 
                    recommendedAmountToBet += betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 1, 5
                }
            } else {
                System.out.println("sumPointsHand was too low so folding");
                folded = true;
                System.out.println("111");
                fold();
            }
        }

        if (folded == false) {
            //recommended bet based on PERCENTAGES
            if (percentChanceOfMakingHand > potOdds_percentage + bettingRiskTolerance_level) {// 40 > 30 + 7
                System.out.println(this.name + " : making hand (" + percentChanceOfMakingHand + ") > " + potOdds_percentage + " + " + bettingRiskTolerance_level);
                //raise by a bit
                int z = (int) (percentChanceOfMakingHand - potOdds_percentage);
                System.out.println("z is " + z);
                int numMultipliedByMinRaise;
                if (z >= 17) {//
                    numMultipliedByMinRaise = (int) (bettingRiskTolerance_level * 1.2);
                    recommendedAmountToBet += betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 6, 8//use bettingRiskTolerance_level here
                } else if (z >= 12) {
                    numMultipliedByMinRaise = thisNumberOrAtLeastNumber1((int) (bettingRiskTolerance_level));//divide by 1.5
                    recommendedAmountToBet += betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 1, 5
                } else if (z >= 5) {
                    numMultipliedByMinRaise = ((int) (bettingRiskTolerance_level / 2));//divide by 2.5
                    recommendedAmountToBet += betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 0 , 3
                } else if (z >= 0) {
                    numMultipliedByMinRaise = ((int) (bettingRiskTolerance_level / 3.5));
                    recommendedAmountToBet += betThisAmountOrNextLargest(minRaise * numMultipliedByMinRaise);//was 0, 1
                }
                System.out.println("raising a bit..amount to bet is : " + recommendedAmountToBet);
            } else if (percentChanceOfMakingHand - 2 + bettingRiskTolerance_level / 2 > potOdds_percentage) {//40  + 6/2 > 45 is false
                System.out.println(this.name + ": making hand (" + percentChanceOfMakingHand + ")  - 2 + " + bettingRiskTolerance_level + " > " + potOdds_percentage);
                //call
                // this.recommendedAmountToBet = this.amountToCall;
            } else if (atLeastCall == false) {
                boolean bluffed = maybeBluff_makeRandomNumberAndMaybeTheyWill();
                if (bluffed == false) {
                    fold();
                }

            }

        }
    }

    public int gethowGoodIsPlayerHand() {
        return this.howGoodIsPlayerHand;
    }

    public boolean maybeBluff_makeRandomNumberAndMaybeTheyWill() {
        int z = (int) (Math.random() * reducePlayerBluffLikliness);
        if (z <= willBluff_level) {//high will bluff level means they will bluff---> their bluff levels are between 0 and 10..this reduces their chances of bluffing by 40%
            System.out.println("bluffing...");
            recommendedAmountToBet = amountToCall + betThisAmountOrNextLargest(minRaise * (bettingRiskTolerance_level / 2)); //THEN BLUFF!!!   
            return true;//did bluff
        }
        return false;
    }

    public int timesSuit_C_AppearsInFaceUpCards(char c) {
        int sum = 0;
        for (int i = 0; i < allCards.length - 2; i++) {
            if (faceUpCards[i].suit == c) {
                sum++;
            }
        }
        return sum;
    }

    public int timesValue_Z_AppearsInFaceUpCards(int z) {
        int sum = 0;
        for (int i = 0; i < allCards.length - 2; i++) {
            if (faceUpCards[i].value == z) {
                sum++;
            }
        }
        return sum;
    }

    public boolean handContainsValueZ(int z) {
        for (int i = 0; i < allCards.length; i++) {
            if (allCards[i].value == z) {
                return true;
            }
            if (z == 1 && allCards[i].value == 14) {
                return true;
            }
        }
        return false;
    }

    public boolean checkIf_two_cardsAwayFromStraight() {
        boolean twoAway = false;
        //case 1: three in a row _ X X X _ OR X X X _ _ OR _ _ X X X 
        if (numSequentially == 3) {
            twoAway = true;
            numOuts += 8; //all cases share sum numOuts
            //case 1 A --->   _ X X X _
            if (lowestValOfNumSequentially >= 2 && lowestValOfNumSequentially <= 11) {
                bestHandFromCardsNickNameString = "Backdoor Open-Ended Straight";
            } else {
                bestHandFromCardsNickNameString = "Backdoor Single-Sided Straight";
            }
            //case 
        } else if (numSequentially == 2) { // _ A _ X X or _ X X _ A //CHECK if it contains A
            if (handContainsValueZ(lowestValOfNumSequentially - 2) || handContainsValueZ(lowestValOfNumSequentially + 3)) {
                numOuts += 8;
                twoAway = true;
                bestHandFromCardsNickNameString = "Backdoor Inside Straight";
            }
        } else {//X _ X _ X 
            for (int i = 1; i < 11; i++) {
                if (handContainsValueZ(i) == true && handContainsValueZ(i + 2) && handContainsValueZ(i + 4)) {
                    numOuts += 8;
                    bestHandFromCardsNickNameString = "Backdoor Double Inside Straight";
                }
            }
        }
        return twoAway;
    }

    public boolean checkIf_one_cardAwayFromStraight() {//check for _ X X X X _;   X _ X X X; X X _ X X; X X X _ X; 
        boolean oneAway = false;
        //CASE 1: Four in a row and missing one _ X X X X _ ---> open ended straight draw  
        if (numSequentially == 4 && lowestValOfNumSequentially >= 2 && lowestValOfNumSequentially <= 10) {// _ 2 3 4 5 _, we can get ANY ace or ANY 6
            oneAway = true;
            numOuts += 8;
            bestHandFromCardsNickNameString = "Open-Ended Straight Draw";
        } else if (numSequentially == 4) {// A 2 3 4// any 5
            oneAway = true;
            numOuts += 4;
            bestHandFromCardsNickNameString = "Single-Sided Straight Draw";
        } else if (numSequentially == 3) {// CASE 2: 3 in a row and missing one between so either X _ X X X  OR OR X X X _ X
            if (handContainsValueZ(lowestValOfNumSequentially - 2) || handContainsValueZ(lowestValOfNumSequentially + 4)) {
                numOuts += 4;
                oneAway = true;
                bestHandFromCardsNickNameString = "Gutshot Straight Draw";
            }
        } else if (numSequentially == 2) {//lowest value of num sequentially will be the higher pair X X _ K X (will be k)
            if (handContainsValueZ(lowestValOfNumSequentially - 2) && handContainsValueZ(lowestValOfNumSequentially - 3)) {
                numOuts += 4;
                oneAway = true;
                bestHandFromCardsNickNameString = "Bulls-Eye Gutshot Straight Draw";
            }

        }

        return oneAway;
    }

    public int determineHowGoodHandIs(int numFaceUpCards, int numberTotalCards) {
        int sum = 0;
        int info_num_sequentially[], info_num_same[];
        for (int i = 0; i < numberTotalCards; i++) {
            if (allCards[i] != null) {
                System.out.println("allCards[" + i + "] not null");
            } else {
                System.out.println("allCards[" + i + "] IS null");
            }
        }
        sortPlayerHandByCardValue(allCards.length);
        info_num_sequentially = hasNSequentially(allCards.length);//get info from functions
        numSameSuit = hasNSameSuit(allCards.length);
        info_num_same = hasNSameValue(allCards.length);

        numSequentially = info_num_sequentially[0];//assign values
        lowestValOfNumSequentially = info_num_sequentially[1];
        numSameVal = info_num_same[0];
        valOfNumbersWithSameVal = info_num_same[1];

        boolean increasedSumAlready = false;
        System.out.println("FULL HOUSE NUM: " + fullHouse());
        if (straightFlush() == 10) {//royal flush -----> LOGIC TO MAKE
            sum = 9 * 14;
            finalHandName = "Royal Flush";
            System.out.println("aaa");
        } else if (straightFlush() > 0) {//straight flush
            sum = 8 * 14 + straightFlush();
            System.out.println("bbb");
            finalHandName = "Straight Flush";
        }
        if (numSameVal == 4) {//four of a kind
            sum = 7 * 14 + valOfNumbersWithSameVal;
            System.out.println("ccc");
            finalHandName = "Four of a Kind";
        }
        if (fullHouse() > 0) {//full house
            sum = 6 * 14 + fullHouse();
            System.out.println("ddd");//84
            finalHandName = "Full House";
        }
        if (numSameSuit == 5) {//FLUSH
            sum = 5 * 14;
            System.out.println("eee");
            finalHandName = "Flush";
        } else if (numSequentially == 5) {//straight
            sum = 4 * 14 + lowestValOfNumSequentially;
            finalHandName = "Straight";        
        }
        if (numSameVal == 3) {//3 of a kind  =======================================1 card away from FOUR OF A KIND   
            finalHandName = "3 of a Kind";
            sum = 3 * 14 + valOfNumbersWithSameVal;
            System.out.println("fff");//===================================also, 1 card away from FULL HOUSE
            numOuts += 1;//one away from 4 of a kind
            numOuts += (numFaceUpCards - 1) * 3;// 5 5 ... 5 10 J .. so 3 face up cards, 2 not incldued in 3 of a kind, for each of those two (10 or J) there are 3 more tens and 3 more jacks for FULL HOUSE
        }
        if (twoPair() > 0) {//======================================================1 card away from full house, 1 away from THREE OF A KIND
            finalHandName = "Two Pair";
            sum += 2 * 14 + determineHighestVal();
            System.out.println("ggg");
            //1 away from 3 of a kind , 2 cards possible, for each different pair 2 * 2 = 4 --> if get 3 of a kind, get full house
            numOuts += 4;
        } else if (numSameVal == 2) {//================================================1 away from 3 of a kind
            numOuts += 2;
            finalHandName = "Pair";
            sum += 1 * 14 + valOfNumbersWithSameVal;
            System.out.println("hhh");
        } else if (sum == 0) {
            finalHandName = "High Card";
            System.out.println("ii");
            sum += determineHighestVal();//2 to 14
            //just a high card
        }
        System.out.println("SUM 1 IS " + sum);
        //now calculate odds if there are 5 or 6 cards on the table
        if (numFaceUpCards != 5) {//5 or 6 cards on the table
            //calculate odds
            if (checkIf_one_cardAwayFromStraight() == false && turnCounter != 3) {//===========================1 card away from STRAIGHT
                checkIf_two_cardsAwayFromStraight();//this adds odds automatically=========2 card away from STRAIGHT
            }
            if (numSameSuit == 4) {//======================================================1 card away from FLUSH
                numOuts += 9;//13 cards of same suit - 4 already in play = 9
            } else if (numSameSuit == 3) {//===============================================2 card away from FLUSH
                numOuts += 10; //13 cards of same suit - 3 already in play == 10
            }
        }

        //
        if (numOuts <= 20 && numOuts > 0) {
            percentChanceOfMakingHand = allPokerOddsInformation[numOuts - 1][numFaceUpCards - 3];//UNLESS PLAYER HAS GONE ALL IN ON FLOP
            chanceOfMakingHand_allInOnFlop = allPokerOddsInformation[numOuts - 1][2];
        } else if (numOuts >= 20) {
            System.out.println("num outs is more than 20..what do i do?");
            percentChanceOfMakingHand = allPokerOddsInformation[19][numFaceUpCards - 3];
            chanceOfMakingHand_allInOnFlop = allPokerOddsInformation[19][2];
        }


        return sum;
    }

//    public int percentChanceOf_royalFlush(){
//        return 0;
//    }
    public int twoPair() {
        int numPairs = 0;
        int higherVal = 0;
        for (int i = 0; i < allCards.length - 1; i++) {
            int val = allCards[i].value;
            if (val == allCards[i + 1].value) {
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

    public int straightFlush() {
        return 0;
    }

    public int getDecision() {
        // return this.recommendedAmountToBet;
        if (recommendedAmountToBet > amountToCall && canRaise == true) {
            return this.recommendedAmountToBet;
        } else if (recommendedAmountToBet > amountToCall && canRaise == false) {//want to raise, but we cant, so just call at least
            return this.amountToCall;
        } else {
            return this.recommendedAmountToBet;
        }
    }

    public void fold() {
        if (this.amountToCall > 0) {
            System.out.println("folding...");
            this.recommendedAmountToBet = 0;
        }
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

    public int determineHighestVal() {
        //this is the original, unsorted hand
        if (playerCards[0].value > playerCards[1].value) {
            return playerCards[0].value;
        }
        return playerCards[1].value;

    }

    public static int thisNumberOrAtLeastNumber1(int x) {
        if (x > 1) {
            return x;
        }
        return 1;
    }

    public int fullHouse() {//return pair of 3 value

        for (int i = 0; i < this.allCards.length - 4; i++) {//was 3
            int thisValue = this.allCards[i].value;
            if (thisValue == this.allCards[i + 1].value && thisValue == this.allCards[i + 2].value && this.allCards[i + 3].value == this.allCards[i + 4].value) {
                return thisValue;
            }
            if (thisValue == this.allCards[i + 1].value && this.allCards[i + 2].value == this.allCards[i + 3].value && this.allCards[i + 3].value == this.allCards[i + 4].value) {
                return this.allCards[i + 2].value;
            }
        }
        return -1;
    }

    public static int qualityOfPlayersOwnTwoCards(Card twoCards[]) {
        int quality = 0;
        for (int i = 0; i < 2; i++) {
            if (twoCards[i].value > 11) {//HIGH CARD like J Q K A
                quality += 5;
            }
        }
        if (twoCards[0].value == twoCards[1].value) {//PAIR
            quality += 10;
        }

        if (twoCards[0].value == twoCards[1].value - 1 || twoCards[0].value == twoCards[1].value + 1) {//CONSECUTIVE
            quality += 7;
        }

        if (twoCards[0].suit == twoCards[1].suit) {//SAME SUIT
            quality += 3;
        }
        System.out.println("quality is " + quality);
        return quality;
    }

    public int betThisAmountOrNextLargest(int y) {//try and bet y dollars. if not, bet the next amount lower...THINK IN CHIPS THOUGH SCOTT!
        if (playerMoney >= y) {
            return y;
        } else {//
            return playerMoney;

        }

    }

    public int[] moneyToChips(int x, int chips[][]) {

        int temp[] = new int[6];
        while (x > 0) {
            //checkCoinAmounts();
            for (int i = 5; i >= 0; i--) {
                // checkCoinAmounts();
                if (chips[i][1] > 0 && x >= chips[i][0]) {//has at least 1 chip, money is larger or equal to that chip value
                    temp[i]++;//so we want to bet that chip
                    x -= chips[i][0];
                    i++;
                }
            }
        }


        return temp;
    }

    public static int randomIntWithin(int x, int y) {//maybe change this to have a 3rd paramater, leanInDirection (so lower, higher) towards the spectrum...
        return (int) (Math.random() * (y - x) + x);
    }

    public void allParametersToString(int amountToCall, int playerMoney, int playerChips[][], Card playerOwnCards[], Card faceUpCards[], int turnCounter, int minRaise, int amountBetSoFarThisTurn) {
        System.out.println("Best Computer Move calculator: ");
        System.out.println("Amount to call: " + amountToCall);
        System.out.println("Min raise is: " + minRaise);
        System.out.println("Personality is " + personalityString);
        System.out.println("Total bet this turn: " + amountBetSoFarThisTurn);
        System.out.println("Turn Counter: " + turnCounter);
        System.out.println("Player money: " + playerMoney);
        System.out.println("last raise amount: " + lastRaiseAmount);
        System.out.print("Player two cards:");
        playerOwnCards[0].cardToString();
        System.out.print(", ");
        playerOwnCards[1].cardToString();
        System.out.print("\nFace up cards: ");
        for (int i = 0; i < 5; i++) {
            if (faceUpCards[i] != null) {
                faceUpCards[i].cardToString();
                System.out.print(",  ");
            }
        }
        System.out.println();
    }

    public void sortPlayerHandByCardValue(int handLength) {
        for (int i = 1; i < handLength; i++) {
            Card x = this.allCards[i];
            int j = i - 1;
            while (j >= 0 && this.allCards[j].value > x.value) {
                this.allCards[j + 1] = this.allCards[j];
                --j;
            }
            this.allCards[j + 1] = x;
        }
    }

    //================================================================================USED TO DETERMINE HOW GOOD THE PLAYER HAND IS====================================
    public int[] hasNSequentially(int lengthOfHand) {//check for Ace case //need to NOT ALLOW DUPLICATES
        ArrayList<Card> handList = new ArrayList<Card>();
        int solution[] = {0, 0};//number in a row, lowest number of that
        boolean foundSomeNInAFow = false;
        int hasAce = 0;

        int usedNumbers[] = new int[13];//2,3,4,5,6,7,8,9,10,j,q,k,a --> THIS HANDLES THE ACE CASE
        for (int i = 0; i < lengthOfHand; i++) {//was
            usedNumbers[allCards[i].value - 2]++;//USED
            if (usedNumbers[allCards[i].value - 2] == 1) {//handles NOT having doubles of numbers like 2 3 4 4 5 
                handList.add(this.allCards[i]);
                if (this.allCards[i].value == 14) {
                    Card c = new Card(1, this.allCards[i].suit);
                    handList.add(0, c);//add to front..shifts elements to right
                }
            }
        }

        for (int i = 0; i < handList.size() - 4; i++) {//check for 5 in a row, //was - 4
            int val = handList.get(i).value;
            int counter = 0;
            for (int j = 0; j < 4; j++) {//was 4
                if (handList.get(j + i + 1).value == val + 1 + j) {
                    counter++;
                } else {
                    break;
                }
                if (counter == 4) {//was 4
                    foundSomeNInAFow = true;
                    solution[0] = 5;
                    solution[1] = val;
                }
            }
        }

        if (foundSomeNInAFow == false) {
            for (int i = 0; i < handList.size() - 3; i++) {//check for 5 in a row, //was - 4
                int val = handList.get(i).value;
                int counter = 0;
                for (int j = 0; j < 3; j++) {
                    if (handList.get(j + i + 1).value == val + 1 + j) {
                        counter++;
                    } else {
                        break;
                    }
                    if (counter == 3) {
                        // System.out.println("yes 4 sequentially");
                        foundSomeNInAFow = true;
                        solution[0] = 4;
                        solution[1] = val;
                    }
                }
            }
        }

        if (foundSomeNInAFow == false) {
            for (int i = 0; i < handList.size() - 2; i++) {//check for 5 in a row, //was - 4
                int val = handList.get(i).value;
                int counter = 0;
                for (int j = 0; j < 2; j++) {
                    if (handList.get(j + i + 1).value == val + 1 + j) {
                        counter++;
                    } else {
                        break;
                    }
                    if (counter == 2) {
                        //   System.out.println("yes 3 sequentially");
                        foundSomeNInAFow = true;
                        solution[0] = 3;
                        solution[1] = val;
                    }
                }
            }
        }

        if (foundSomeNInAFow == false) {
            for (int i = 0; i < handList.size() - 1; i++) {//check for 5 in a row, //was - 4
                int val = handList.get(i).value;
                int counter = 0;
                for (int j = 0; j < 1; j++) {
                    if (handList.get(j + i + 1).value == val + 1 + j) {
                        counter++;
                    } else {
                        break;
                    }
                    if (counter == 1) {
                        //System.out.println("yes 2 sequentially");
                        foundSomeNInAFow = true;
                        solution[0] = 2;
                        solution[1] = val;

                    }
                }
            }
        }

        return solution;//return at end that way if there is 2,3,5,7,8 it returns 7 8 not 2 3

    }

    public int[] hasNSameValue(int lengthOfHand) {
        int solution[] = {0, 0};//num of the same, that value
        boolean foundMostOfSameVal = false;
        //4

        for (int i = 0; i < lengthOfHand - 3; i++) {//4 of the same
            int val = this.allCards[i].value;
            if (this.allCards[i + 1].value == val && this.allCards[i + 2].value == val && this.allCards[i + 3].value == val) {
                solution[0] = 4;
                solution[1] = val;
                foundMostOfSameVal = true;
            }
        }
        if (foundMostOfSameVal == false) {
            for (int i = 0; i < lengthOfHand - 2; i++) {//3 of the same
                int val = this.allCards[i].value;
                if (this.allCards[i + 1].value == val && this.allCards[i + 2].value == val) {
                    solution[0] = 3;
                    solution[1] = val;
                    foundMostOfSameVal = true;
                }
            }
        }
        if (foundMostOfSameVal == false) {
            for (int i = 0; i < lengthOfHand - 1; i++) {//2 of the same
                int val = this.allCards[i].value;
                if (this.allCards[i + 1].value == val) {
                    solution[0] = 2;
                    solution[1] = val;
                    foundMostOfSameVal = true;
                }
            }
        }

        return solution;
    }

    public int hasNSameSuit(int lengthOfHand) {
        int numClubs = 0;
        int numHearts = 0;
        int numSpades = 0;
        int numDiamonds = 0;

        for (int i = 0; i < lengthOfHand; i++) {//was 7
            switch (this.allCards[i].suit) {
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
        for (int i = 5; i >= 2; i--) {
            if (numClubs >= i || numHearts >= i || numSpades >= i || numDiamonds >= i) {
                return i;
            }
        }
        return 0;
    }

}
