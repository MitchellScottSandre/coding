/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

//import  TexasHoldEm2.PokerGame.BIG_BLIND_BUTTON_IMAGE;
import static TexasHoldEmGame.PokerGame.BOARD_WIDTH;
import static TexasHoldEmGame.PokerGame.COMP_CARD_SCALE;
import static TexasHoldEmGame.PokerGame.GAP_FROM_EDGE;
import static TexasHoldEmGame.PokerGame.SIZE_RATIO;
import static TexasHoldEmGame.PokerGame.allChipImages;
import static TexasHoldEmGame.PokerGame.allPlayers;
import static TexasHoldEmGame.PokerGame.allRounds;
import static TexasHoldEmGame.PokerGame.appHeight;
import static TexasHoldEmGame.PokerGame.approx_max_chips;
import static TexasHoldEmGame.PokerGame.button_dimension;
import static TexasHoldEmGame.PokerGame.playerName_slickFont;
import static TexasHoldEmGame.PokerGame.roundCounter;
import static TexasHoldEmGame.PokerGame.round_turnCounter;
import static TexasHoldEmGame.PokerGame.scrollingInfo;
import static TexasHoldEmGame.PokerGame.someoneAllInOnFlop;
import static TexasHoldEmGame.PokerGame.someoneIsAllIn;
import static TexasHoldEmGame.PokerGame.thisRound;
import static TexasHoldEmGame.PokerGame.userPlayOptionsBackground;
import static TexasHoldEmGame.PokerGame.user_card_height;
import static TexasHoldEmGame.PokerGame.user_card_width;

import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import static TexasHoldEmGame.Round.minimumRaise;
//import org.newdawn.slick.gui.MouseOverArea;

/**
 *
 * @author scott
 */
public class Player {

    //for gameplay
    
    public int PERSONALITY;
    public boolean allowedToRaise = true;

    public static int playerUserDisplayLeftX;
    public static int playerUserDisplayTopY;
    public static int displayChipsHeight = 35;
    public static int userDisplayerGap = GAP_FROM_EDGE / 2;
    // public static int chipDisplayWidth;
    public static boolean doOnce = false;

    public static final int USER_LARGE_CARD_START_X = GAP_FROM_EDGE;
    public static boolean normalDisplay = true;
    public static final int DOWN = 1;
    public static final int UP = -1;
    public static final int RIGHT = 1;
    public static final int LEFT = -1;
    public Image buttonImage = null;
    public static final int HORIZONTAL = 0;
    public static double chipDisplayOverlap = 0.22;
    public static int brownBoarderOnBoardHeight = 22;
    //public static double chipDisplayHeight = (double) ( (displayChipsBarHeight) / (double) (approx_max_chips) );
    public int money;
    //public boolean isPosition = false;
    public static int gapBetweenCards = 6;
    boolean isBigBlind, isSmallBlind, isDealer;
    public int chips[][];//value, number of them
    public Card hand[] = new Card[7];
    public Card handForDeterminingWinner[] = new Card[8];
    public Color playerColour = PokerGame.NORMAL_PLAYER_COLOR;
    public String name;
    public int buttonX = 0, buttonY = 0;
    public static final int PLAYER_DISPLAY_CIRCLE_DIAMETER = (int) ((float) (BOARD_WIDTH) * 0.15);
    public static final int PLAYER_DISPLAY_CIRCLE_RADIUS = PLAYER_DISPLAY_CIRCLE_DIAMETER / 2;
    public static final int chipDisplayWidth = PLAYER_DISPLAY_CIRCLE_RADIUS / 4;
    public int playerXLoc_centre, playerYLoc_centre;
    public static int displayCircleBorderThickness = (int) (8 * SIZE_RATIO);
    public String currentRole, move = " ";//THIS MUST BE ONE SPACE EXACTLY
    //public static final Font userBetFont = new Font(gameFontNameString, Font.BOLD, 30);
    //public static TrueTypeFont userBet_slickFont = new TrueTypeFont(userBetFont, true);
    public int totalBetThisTurn_perShowOfCards = 0;
    public int totalBetThisRound = 0;
    public int thisBetAmount = 0;//Round.bigBlindValue;
    public String endOfRound_HandName;
    public int endOfRound_handValue;
    public static int maxNumberOfChipsOfSameValue = 100;
    public double chipsVsMaxNumberRatio[] = {0.9, 0.6, 0.45, 0.25, 0.18, 1.0};//this dictates how many coins they will have once people start winning
    public boolean allIn = false;
    public boolean called = false, folded = false;
    public static Card userLargeCard1, userLargeCard2;
    public boolean playerStillInGame = true;

    public int bet(int chipsToBet[]) {//need to convert this in to chips? a is chip index, b is number of them
        thisBetAmount = 0;
        for (int i = 0; i < 6; i++) {
            thisBetAmount += this.chips[i][0] * chipsToBet[i];
            this.chips[i][1] -= chipsToBet[i];
            checkCoinAmounts();
        }
        determineMoney();
        this.move = "BET-" + thisBetAmount;//total bet this round?
        totalBetThisTurn_perShowOfCards += thisBetAmount;//do this for call
        totalBetThisRound += thisBetAmount;

        return thisBetAmount;
    }

    public int player_goAllIn() {
        if (this.money < thisRound.amountToCall){
            PokerGame.playerIsAllIn_moneyIsLessThanAmountToCall = true;
        }
        someoneIsAllIn = true;
        scrollingInfo.add(new New_TextOrCardDisplayObject(this.name + " went all in."));
        scrollingInfo.add(new New_TextOrCardDisplayObject("             - they bet $" + this.money));
        this.move = "ALL IN";
        this.setAllIn(true);
        
        this.allowedToRaise = false;
        int temp[] = new int[6];
        for (int i = 0; i < 6; i++){
            temp[i] = this.chips[i][1];
        }
        addArrayOfChipsToPotOfChipsCopy(temp);
        thisRound.someoneAllIn = true;
        totalBetThisTurn_perShowOfCards += this.money;
        totalBetThisRound += this.money;
        int x = this.money;
        this.money = 0;
        for (int i = 0; i < 6; i++) {
            this.chips[i][1] = 0;
        }
        if (round_turnCounter == 1) {//3 cards up, someone is going all in on flop
            someoneAllInOnFlop = true;//THIS IS USED FOR CALCULATING A PLAYERS ODDS, you do third column (index 2) instead of second or first column
        }
        return x;
    }


    public int[] moneyToChips(int x) {

        int temp[] = new int[6];
        while (x > 0) {
            checkCoinAmounts();
            for (int i = 5; i >= 0; i--) {
                checkCoinAmounts();
                if (this.chips[i][1] > 0 && x >= this.chips[i][0]) {//has at least 1 chip, money is larger or equal to that chip value
                    temp[i]++;//so we want to bet that chip
                    x -= chips[i][0];
                    i++;
                }
            }
        }


        return temp;
    }

    public int getMove(int x) {
        int k = 0;
        if (name.equalsIgnoreCase("USER")) {
            System.out.println("get user move");
        } else {

            k = getComputerMoveNEW(x);
        }
        allowedToRaise = false;
        return k;
    }

    public static void addArrayOfChipsToPotOfChipsCopy(int temp[]) {
        for (int i = 0; i < 6; i++) {
            allRounds.get(roundCounter).potCopy_ofChips[i] += temp[i];
        }

    }

    public int computerCall(int x) {
        this.move = "CALL";
        int temp[] = moneyToChips(x - this.totalBetThisTurn_perShowOfCards);//if they've already bet 20, dont need to bet 100 to call 100, only need to bet 80
        int z = bet(temp);
        addArrayOfChipsToPotOfChipsCopy(temp);//changed this _ potcopy
        this.move = "CALL";
        return z;//IS THIS CORRECT??
    }

    public boolean compCannotRaise_and_compCalledOrFolded(int x) {//x is call amount
        if (this.allowedToRaise == false && (this.totalBetThisTurn_perShowOfCards == x || isFolded() == true)) {//player has called, or has folded
            return true;
        }
        return false;
    }

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
        if (this.folded == true) {
            this.playerColour = PokerGame.PLAYER_FOLD_COLOR;
        }
    }

    public int playerFold() {
        setFolded(true);
        allowedToRaise = false;
        this.move = "FOLD";
        scrollingInfo.add(new New_TextOrCardDisplayObject(this.name + " folded"));
        return 0;
    }

    public int compRaise(int x) {
        int k = this.thisBetAmount;
        this.move = "RAISE-" + (minimumRaise + x - this.thisBetAmount);
        int temp[] = moneyToChips(minimumRaise + x - this.thisBetAmount);
        this.move = "RAISE-" + (minimumRaise + x - this.thisBetAmount);
        int z = bet(temp);
        addArrayOfChipsToPotOfChipsCopy(temp);//changed this _ potcopy
        allowedToRaise = false;
        return x + minimumRaise - k;//this is how much they just bet.... 
    }

    public int compRaiseNEW(int raiseByBettingThisAmount) {
        int k = this.thisBetAmount;
        this.move = "RAISE-" + (raiseByBettingThisAmount - this.thisBetAmount);
        int temp[] = moneyToChips(raiseByBettingThisAmount - this.thisBetAmount);
        this.move = "RAISE-" + (raiseByBettingThisAmount - this.thisBetAmount);
        int z = bet(temp);
        addArrayOfChipsToPotOfChipsCopy(temp);//changed this _ potcopy
        allowedToRaise = false;
        return raiseByBettingThisAmount - k;//this is how much they just bet.... 

    }

    public void compGoAllIn() {
        someoneIsAllIn = true;
        this.move = "ALL IN";
        int temp[] = moneyToChips(this.money);
        int z = bet(temp);
        this.move = "ALL IN";
    }


    public int getComputerMoveNEW(int x) {//amount to call
        checkCoinAmounts();
        Card playersOwnCards[] = {this.hand[0], this.hand[1]};
        int beforeMoney = this.money;
        ComputerMoveCalculator decision = new ComputerMoveCalculator(this.PERSONALITY, this.name, x, this.money, this.chips, playersOwnCards, thisRound.faceUpCards, PokerGame.round_turnCounter, thisRound.minimumRaise, this.totalBetThisTurn_perShowOfCards, this.allowedToRaise, thisRound.lastRaiseAmount, thisRound.pot);
        int z = decision.getDecision();//gets decision and acts accordingly
        if (z >= beforeMoney){
            return player_goAllIn();
        } else if (z > x) {//raising
            int s = compRaiseNEW(z);
            scrollingInfo.add(new New_TextOrCardDisplayObject(this.name + " raised by $" + (totalBetThisTurn_perShowOfCards - x)));//what if they are big blind?
            scrollingInfo.add(new New_TextOrCardDisplayObject("             - they bet $" + s));
            return s;   
        }else if (z == x || x == 0) {
            System.out.println(x + " === " + z);
            int s = computerCall(x);//calling
            if (x == 0) {
                scrollingInfo.add(new New_TextOrCardDisplayObject(this.name + " checked"));
            } else {
                scrollingInfo.add(new New_TextOrCardDisplayObject(this.name + " called, bet $" + s));
            }
            return s;
        } else if (z == 0 || z < thisRound.amountToCall) {
            return playerFold();

        }

        return 1;
    }

    public void checkCoinAmounts() {//increase and decrease as necessary
        boolean doOnce_tooFew = false;
        boolean doOnce_tooMany = false;
        //too few coins --> turn higher value coins into lower value coins
        for (int i = 0; i < 5; i++) {
            //if chipsI_number   * chipsI_value     <  largerChipValue - thisChipValue...so we have 3 x 5 = 15 < 25 (one large) - 5 == 25. so 15 < 25..we want at least 4
            if (this.chips[i][1] * this.chips[i][0] < this.chips[i + 1][0] - this.chips[i][0] && this.chips[i + 1][1] > 0) {
                doOnce_tooFew = true;
                chips[i + 1][1] -= 1; //minus one
                chips[i][1] += 1 * chips[i + 1][0] / chips[i][0];
            }
        }

        for (int i = 0; i < 5; i++) {
            if (this.chips[i][1] > (double) (maxNumberOfChipsOfSameValue * chipsVsMaxNumberRatio[i])) {
                doOnce_tooMany = true;
                chips[i + 1][1] += 1;
                chips[i][1] -= chips[i + 1][0] / chips[i][0];
            }
        }

    }

    public Player() {

    }

    public Player(String name, int chips[][]) {
        int z = (int) (Math.random() * 4);
        this.PERSONALITY = z;

        if (doOnce == false) {
            doOnce = true;
            for (int i = 0; i < 6; i++) {
                allChipImages[i] = allChipImages[i].getScaledCopy(chipDisplayWidth, chipDisplayWidth); //allChipImages[i].getScaledCopy(1 / makeItThisMuchSmallerRatio );
            }
        }
        this.name = name;
        this.chips = chips.clone();
        determineMoney();
        this.playerColour = PokerGame.NORMAL_PLAYER_COLOR;
        this.currentRole = "BB";

    }

    public int determineUserBetIncrease() {
        //return smallest chip value
        for (int i = 0; i < 6; i++) {
            if (chips[i][1] > 0) {
                return chips[i][0];
            }
        }
        return -1;
    }

    public void determineCurrentRoleString() {
        if (isIsBigBlind() == true) {
            this.currentRole = "BB";
        } else if (isIsSmallBlind() == true) {
            this.currentRole = "SB";
        } else if (isIsDealer() == true) {
            this.currentRole = "DD";
        } else {
            this.currentRole = "";
        }
    }

    public boolean isIsBigBlind() {
        return isBigBlind;
    }

    public void setIsBigBlind(boolean isBigBlind) {
        this.isBigBlind = isBigBlind;
    }

    public boolean isIsSmallBlind() {
        return isSmallBlind;
    }

    public void setIsSmallBlind(boolean isSmallBlind) {
        this.isSmallBlind = isSmallBlind;
    }

    public boolean isIsDealer() {
        return isDealer;
    }

    public void setIsDealer(boolean isDealer) {
        this.isDealer = isDealer;
    }

    public int getPlayerXLoc() {
        return playerXLoc_centre;
    }

    public void setPlayerXLoc(int playerXLoc) {
        this.playerXLoc_centre = playerXLoc;
    }

    public int getPlayerYLoc() {
        return playerYLoc_centre;
    }

    // to do, bet, call, fold, check
    public void setPlayerYLoc(int playerYLoc) {
        this.playerYLoc_centre = playerYLoc;
    }

    public void playerDisplayHand_thatIsUsedToDetermineWinner() {
        System.out.print("\n" + this.name + " : ");
        for (int i = 0; i < 7; i++) {
            this.handForDeterminingWinner[i].cardToString();
            System.out.print(",  ");
        }
        System.out.println();
    }

    public void playerToString() {
        System.out.print("name: " + name + "; money: " + money + ".....   ");
        for (int i = 0; i < chips.length; i++) {
            System.out.print("$" + chips[i][0] + "x" + chips[i][1] + "; ");
        }
        System.out.print("\n");
        for (int i = 0; i < hand.length; i++) {
            if (hand[i] != null) {
                System.out.print(",  ");
                hand[i].cardToString();
            } else {
                System.out.print(", xx");
            }
        }
        System.out.print("\n");
    }

    public void determineMoney() {
        int sum = 0;
        for (int i = 0; i < chips.length; i++) {
            sum += chips[i][0] * chips[i][1];
        }
        setMoney(sum);
    }

    public void displayUserSpecificThings(Graphics g) {

        g.drawImage(userPlayOptionsBackground, playerUserDisplayLeftX, playerUserDisplayTopY);

    }

    public void displayPlayer(Graphics g) throws SlickException {
        if (this.playerStillInGame == false) return;
        if (this.isFolded() == true){
            this.playerColour = PokerGame.PLAYER_FOLD_COLOR;
        } else if (Round.playerNameToIndex(this.name) == thisRound.winnerIndex){
            playerColour = PokerGame.WINNING_COLOUR;
        } else {
            playerColour = PokerGame.NORMAL_PLAYER_COLOR;
        }
        g.setFont(playerName_slickFont);
        determineCurrentRoleString();

        int yOffset = (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.67);
        int xOffset;
        int xOffset2 = (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.38);
        int yOffset2 = (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.25);
        //display players --> name, large circle that contains all their information, total amount
        g.setColor(this.playerColour);
        g.fillOval(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS, playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS, PLAYER_DISPLAY_CIRCLE_DIAMETER, PLAYER_DISPLAY_CIRCLE_DIAMETER);
        g.setColor(Color.white);
        g.fillOval(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS + displayCircleBorderThickness, playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS + displayCircleBorderThickness, PLAYER_DISPLAY_CIRCLE_DIAMETER - 2 * displayCircleBorderThickness, PLAYER_DISPLAY_CIRCLE_DIAMETER - 2 * displayCircleBorderThickness);

        //name ==============================================================================================================
        g.setColor(Color.black);
        if (name.equals("User")) {
            xOffset = (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.27);
        } else {
            xOffset = (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.39);

        }


        int h1 = playerName_slickFont.getHeight("T");
        int h2 = PokerGame.playerInfo_slickFont.getHeight("T");
        int verticalGap = (PLAYER_DISPLAY_CIRCLE_DIAMETER - 6 * h2) / 8;
        int playerStringsStartY = playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS + verticalGap + 3;
        //name
        PokerGame.playerInfo_slickFont.drawString(playerXLoc_centre - (int) (PokerGame.playerInfo_slickFont.getWidth(name) / 2), playerStringsStartY + 1 * verticalGap, name, Color.black);//

        //money 
        PokerGame.playerInfo_slickFont.drawString(playerXLoc_centre - (int) (PokerGame.playerInfo_slickFont.getWidth("$" + money + "")) / 2, playerStringsStartY + 2 * verticalGap + h2, "$" + money + "", Color.black);

        //TURN BET
        PokerGame.playerInfo_slickFont.drawString(playerXLoc_centre - (int) (PokerGame.playerInfo_slickFont.getWidth("Turn: $" + this.totalBetThisTurn_perShowOfCards + "")) / 2, playerStringsStartY + 3 * verticalGap + 2 * h2, "Turn: $" + this.totalBetThisTurn_perShowOfCards + "", Color.black);

        //ROUND BET
        PokerGame.playerInfo_slickFont.drawString(playerXLoc_centre - (int) (PokerGame.playerInfo_slickFont.getWidth("Round: $" + this.totalBetThisRound + "")) / 2, playerStringsStartY + 4 * verticalGap + 3 * h2, "Round: $" + this.totalBetThisRound + "", Color.black);

        //most recent move 
        int xOffset3 = playerXLoc_centre - (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.065 * move.length());
        PokerGame.playerInfo_slickFont.drawString(playerXLoc_centre - (int) (PokerGame.playerInfo_slickFont.getWidth(move)) / 2, playerStringsStartY + 5 * verticalGap + 4 * h2, move, Color.black);

        //role 
        PokerGame.playerInfo_slickFont.drawString(playerXLoc_centre - (int) (PokerGame.playerInfo_slickFont.getWidth(currentRole)) / 2, playerStringsStartY + 6 * verticalGap + 5 * h2, currentRole, Color.black);

        //buttons ==========================================================================================================
 
        int chipStartX = playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS;
        int chipStartY = 0;//for testing
        int height;

        if ("User".equals(this.name)) {
            userLargeCard1 = new Card(hand[0].value, hand[0].suit);
            userLargeCard1.xLoc = USER_LARGE_CARD_START_X;
            userLargeCard1.yLoc = appHeight - GAP_FROM_EDGE - user_card_height;
            userLargeCard1.cardImage = hand[0].cardImage.getScaledCopy(user_card_width, user_card_height);
            userLargeCard1.cardImage.draw(userLargeCard1.getxLoc(), userLargeCard1.getyLoc());

            userLargeCard2 = new Card(hand[1].value, hand[1].suit);
            userLargeCard2.xLoc = USER_LARGE_CARD_START_X + user_card_width + GAP_FROM_EDGE;
            userLargeCard2.yLoc = appHeight - GAP_FROM_EDGE - user_card_height;
            userLargeCard2.cardImage = hand[1].cardImage.getScaledCopy(user_card_width, user_card_height);
            userLargeCard2.cardImage.draw(userLargeCard2.getxLoc(), userLargeCard2.getyLoc());
            displayUserSpecificThings(g);
        }

        int cardX = 0, cardY = 0;
        float rotate1 = 0;
        float rotate2 = 0;
        switch (name) {
            case "User":
                hand[0].setxLoc(playerXLoc_centre + chipDisplayWidth);
                hand[0].setyLoc(playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS * 2);
                hand[1].setxLoc(playerXLoc_centre + chipDisplayWidth + Card.smallCompX + gapBetweenCards);
                hand[1].setyLoc(playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS * 2);
                this.buttonX = playerXLoc_centre - button_dimension / 2;
                this.buttonY = playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - button_dimension;
                break;
            case "Comp 1":
                hand[0].setxLoc(playerXLoc_centre + chipDisplayWidth * 4);
                hand[0].setyLoc(playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS * 2 - hand[0].smallCompY);
                hand[1].setxLoc(playerXLoc_centre + chipDisplayWidth * 4 + gapBetweenCards);
                hand[1].setyLoc(playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS * 2 - Card.smallCompY + gapBetweenCards);
                rotate2 = -24;
                this.buttonX = playerXLoc_centre - button_dimension / 2;
                this.buttonY = playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS;
                break;
            case "Comp 2":
                hand[0].setxLoc(playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS + chipDisplayWidth / 2);
                hand[0].setyLoc(playerYLoc_centre - (int) ((double) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 1.75) - chipDisplayWidth);
                hand[1].setxLoc(playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS + gapBetweenCards + chipDisplayWidth / 2);
                hand[1].setyLoc(playerYLoc_centre - (int) ((double) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 1.75) - chipDisplayWidth + gapBetweenCards);
                rotate1 = 45;
                rotate2 = 65;
                this.buttonX = playerXLoc_centre + (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707) - button_dimension / 2;
                this.buttonY = playerYLoc_centre - (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707) - button_dimension;
                break;
            case "Comp 3":
                hand[0].setxLoc(playerXLoc_centre + chipDisplayWidth * 2);
                hand[0].setyLoc(playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS * 2 - hand[0].smallCompY);
                hand[1].setxLoc(playerXLoc_centre + chipDisplayWidth * 2 + gapBetweenCards);
                hand[1].setyLoc(playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS * 2 - hand[0].smallCompY + gapBetweenCards);
                rotate2 = -24;
                this.buttonX = playerXLoc_centre - button_dimension / 2;
                this.buttonY = playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS;
                break;
            case "Comp 4":

                hand[0].setxLoc(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - Card.smallCompY - Card.smallCompX - chipDisplayWidth);
                hand[0].setyLoc(playerYLoc_centre + chipDisplayWidth);
                hand[1].setxLoc(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - Card.smallCompY - Card.smallCompX - chipDisplayWidth);
                hand[1].setyLoc(playerYLoc_centre + chipDisplayWidth - gapBetweenCards);
                rotate1 = -90;
                rotate2 = -70;
                this.buttonY = playerYLoc_centre - button_dimension / 2;
                this.buttonX = playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - button_dimension;
                break;
            case "Comp 5":
                hand[0].setxLoc(playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS + Card.smallCompY + chipDisplayWidth);
                hand[0].setyLoc(playerYLoc_centre + chipDisplayWidth);
                hand[1].setxLoc(playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS + Card.smallCompY + chipDisplayWidth);
                hand[1].setyLoc(playerYLoc_centre + chipDisplayWidth + gapBetweenCards);
                rotate1 = 90;
                rotate2 = 110;
                this.buttonY = playerYLoc_centre - button_dimension / 2;
                this.buttonX = playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS;
                break;
            case "Comp 6":
                hand[0].setxLoc(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - chipDisplayWidth - Card.smallCompY - Card.smallCompX);
                hand[0].setyLoc(playerYLoc_centre + 2 * chipDisplayWidth);
                hand[1].setxLoc(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - gapBetweenCards - chipDisplayWidth - Card.smallCompX - Card.smallCompY);
                hand[1].setyLoc(playerYLoc_centre + 2 * chipDisplayWidth - gapBetweenCards);
                rotate1 = -(45 + 90);
                rotate2 = -(65 + 50);
                this.buttonX = playerXLoc_centre - (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707) - button_dimension / 2;
                this.buttonY = playerYLoc_centre + (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707) - button_dimension / 2;
                break;
            case "Comp 7":
                hand[0].setxLoc(playerXLoc_centre + chipDisplayWidth);
                hand[0].setyLoc(playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS * 2);
                hand[1].setxLoc(playerXLoc_centre + chipDisplayWidth + gapBetweenCards);
                hand[1].setyLoc(playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS * 2);
                rotate2 = 20;
                this.buttonX = playerXLoc_centre - button_dimension / 2;
                this.buttonY = playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - button_dimension;
                break;
            case "Comp 8":
                hand[0].setxLoc(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - chipDisplayWidth / 2 - Card.smallCompX - chipDisplayWidth);
                hand[0].setyLoc(playerYLoc_centre - (int) ((double) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 1.75));
                hand[1].setxLoc(playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - gapBetweenCards - chipDisplayWidth / 2 - Card.smallCompX - chipDisplayWidth);
                hand[1].setyLoc(playerYLoc_centre - (int) ((double) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 1.75) + gapBetweenCards);
                rotate1 = -45;
                rotate2 = -65;
                this.buttonX = playerXLoc_centre - (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707) - button_dimension / 2;
                this.buttonY = playerYLoc_centre - (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707) - button_dimension;
                break;
            case "Comp 9":
                hand[0].setxLoc(playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS + chipDisplayWidth + Card.smallCompY + gapBetweenCards);
                hand[0].setyLoc(playerYLoc_centre + chipDisplayWidth + gapBetweenCards);
                hand[1].setxLoc(playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS + gapBetweenCards + chipDisplayWidth + Card.smallCompY + gapBetweenCards);
                hand[1].setyLoc(playerYLoc_centre + chipDisplayWidth);
                rotate1 = 45 + 90;
                rotate2 = 65 + 50;
                this.buttonX = playerXLoc_centre + (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707);
                this.buttonY = playerYLoc_centre + (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 0.707) - button_dimension / 2;
                break;
        }

        hand[0].drawCard(g, COMP_CARD_SCALE, rotate1, hand[0].isFaceUp());
        hand[1].drawCard(g, COMP_CARD_SCALE, rotate2, hand[1].isFaceUp());

        if (this.isIsBigBlind() == true) {
            buttonImage = new Image("TexasHoldEmData/dealer_button.png");
            buttonImage = buttonImage.getScaledCopy(button_dimension, button_dimension);
        } else if (this.isIsDealer() == true) {
            buttonImage = new Image("TexasHoldEmData/small_blind_button.png");
            buttonImage = buttonImage.getScaledCopy(button_dimension, button_dimension);
        } else if (this.isIsSmallBlind() == true) {
            buttonImage = new Image("TexasHoldEmData/big_blind_button.png");
            buttonImage = buttonImage.getScaledCopy(button_dimension, button_dimension);
        } else {
            buttonImage = null;
        }
        if (buttonImage != null) {

            g.drawImage(buttonImage, buttonX, buttonY);

        }

        int maxNumChipsDrawn = (int) ((double) displayChipsHeight / (double) (chipDisplayWidth) / chipDisplayOverlap);
        int numChipsToDraw;
        int ydirection = 0;
        int xdirection = 0;
        int horizontalMeansNewChipColour = 1;
        int verticalMeansNewChipColour = 0;
        normalDisplay = true;
        int xTiltDirection = 0;
        int yTiltDirection = 0;
        int xComponent = 0;
        int yComponent = 0;
        boolean flipAround = false;
        int angle = 41;
        double theta = angle * 3.14159 / 180;
        for (int i = 0; i < 6; i++) {
            switch (name) {
                case "Comp 7":
                case "User":
                    chipStartX = playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - chipDisplayWidth;
                    chipStartY = playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - chipDisplayWidth - brownBoarderOnBoardHeight;//-7
                    ydirection = UP;
                    break;
                case "Comp 1":
                    chipStartX = playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS / 2;
                    chipStartY = playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS - chipDisplayWidth + (int) (1.7 * brownBoarderOnBoardHeight);
                    ydirection = DOWN;
                    flipAround = true;
                    break;
                case "Comp 3":
                    chipStartX = playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS;
                    chipStartY = playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS - chipDisplayWidth + (int) (1.7 * (double) brownBoarderOnBoardHeight);
                    ydirection = DOWN;
                    flipAround = true;
                    break;
                case "Comp 5":
                    chipStartX = playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS + brownBoarderOnBoardHeight + 3;
                    chipStartY = playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS / 2 - chipDisplayWidth;
                    ydirection = HORIZONTAL;
                    xdirection = RIGHT;
                    horizontalMeansNewChipColour = 0;
                    verticalMeansNewChipColour = 1;
                    break;
                case "Comp 4":
                    chipStartX = playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS - brownBoarderOnBoardHeight - 3 - chipDisplayWidth;
                    chipStartY = playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS / 2 - chipDisplayWidth;
                    ydirection = HORIZONTAL;
                    xdirection = LEFT;
                    horizontalMeansNewChipColour = 0;
                    verticalMeansNewChipColour = 1;
                    flipAround = true;
                    break;
                case "Comp 2":
                    normalDisplay = false;
                    chipStartX = playerXLoc_centre;
                    chipStartY = playerYLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS * 2;
                    xTiltDirection = RIGHT;
                    yTiltDirection = DOWN;
                    angle = 41;
                    break;
                case "Comp 8":
                    normalDisplay = false;
                    chipStartX = playerXLoc_centre - chipDisplayWidth - brownBoarderOnBoardHeight;
                    chipStartY = playerYLoc_centre - (int) (PLAYER_DISPLAY_CIRCLE_RADIUS * 1.45) - chipDisplayWidth;
                    xTiltDirection = LEFT;
                    yTiltDirection = DOWN;
                    angle = 34;
                    flipAround = true;
                    break;
                case "Comp 9":
                    normalDisplay = false;
                    chipStartX = playerXLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS / 2 + brownBoarderOnBoardHeight;
                    chipStartY = playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS;
                    xTiltDirection = RIGHT;
                    yTiltDirection = UP;
                    angle = 52;
                    flipAround = true;
                    break;
                case "Comp 6":
                    normalDisplay = false;
                    chipStartX = playerXLoc_centre - PLAYER_DISPLAY_CIRCLE_RADIUS / 2 - chipDisplayWidth - brownBoarderOnBoardHeight;
                    chipStartY = playerYLoc_centre + PLAYER_DISPLAY_CIRCLE_RADIUS;
                    xTiltDirection = LEFT;
                    yTiltDirection = UP;
                    angle = 52;
                    break;

            }
            theta = angle * 3.14159 / 180;
            //now draw the chips, stacked vertically depending on how many there
            numChipsToDraw = (int) ((double) (this.chips[i][1]) / (double) (approx_max_chips) * maxNumChipsDrawn);
            //but if there is at least chip, draw at least one
            if (chips[i][1] > 0 && numChipsToDraw == 0) {
                numChipsToDraw = 1;
            }

            if (normalDisplay == true) {
                //if (flipAround == false) {
                for (int j = 0; j < numChipsToDraw; j++) {
                    g.drawImage(allChipImages[i], chipStartX + i * chipDisplayWidth * horizontalMeansNewChipColour + (int) (xdirection * j * chipDisplayWidth * chipDisplayOverlap), verticalMeansNewChipColour * i * chipDisplayWidth + (int) (chipStartY + j * chipDisplayWidth * chipDisplayOverlap * ydirection));
                }

            } else {//display chips on an angle

                for (int j = 0; j < numChipsToDraw; j++) {

                    xComponent = chipStartX + (int) (i * xTiltDirection * chipDisplayWidth * Math.cos(theta));
                    yComponent = chipStartY + (int) (i * yTiltDirection * chipDisplayWidth * Math.sin(theta));
                    //now you have to draw multiple chips...
                    xComponent += (int) (j * xTiltDirection * chipDisplayWidth * Math.cos(theta) * chipDisplayOverlap);
                    yComponent += (int) (-j * yTiltDirection * chipDisplayWidth * Math.cos(theta) * chipDisplayOverlap);
                    g.drawImage(allChipImages[i], xComponent, yComponent);
                }

            }

        }

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int[][] getChips() {
        return chips;
    }

    public void setChips(int[][] chips) {
        this.chips = chips;
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand(Card[] hand) {
        this.hand = hand;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isAllIn() {
        return allIn;
    }

    public void setAllIn(boolean allIn) {
        this.allIn = allIn;
    }

}
