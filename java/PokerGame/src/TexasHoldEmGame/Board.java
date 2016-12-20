/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import static TexasHoldEmGame.Player.PLAYER_DISPLAY_CIRCLE_RADIUS;
import static TexasHoldEmGame.Player.chipDisplayWidth;
import static TexasHoldEmGame.PokerGame.BLACK_CHIP;
import static TexasHoldEmGame.PokerGame.BOARD_CENTRE_X;
import static TexasHoldEmGame.PokerGame.BOARD_CENTRE_Y;
import static TexasHoldEmGame.PokerGame.BOARD_HEIGHT;
import static TexasHoldEmGame.PokerGame.BOARD_LEFT_X;
import static TexasHoldEmGame.PokerGame.BOARD_TOP_Y;
import static TexasHoldEmGame.PokerGame.BOARD_WIDTH;
import static TexasHoldEmGame.PokerGame.GAP_FROM_EDGE;
import static TexasHoldEmGame.PokerGame.GREEN_CHIP;
import static TexasHoldEmGame.PokerGame.PURPLE_CHIP;
import static TexasHoldEmGame.PokerGame.RED_CHIP;
import static TexasHoldEmGame.PokerGame.SIZE_RATIO;
import static TexasHoldEmGame.PokerGame.WHITE_CHIP;
import static TexasHoldEmGame.PokerGame.YELLOW_CHIP;
import static TexasHoldEmGame.PokerGame.allChipImages;
import static TexasHoldEmGame.PokerGame.allInButton;
import static TexasHoldEmGame.PokerGame.allPlayers;
import static TexasHoldEmGame.PokerGame.allRounds;
import static TexasHoldEmGame.PokerGame.betButton;
import static TexasHoldEmGame.PokerGame.blockHeight2;
import static TexasHoldEmGame.PokerGame.blockWidth;
import static TexasHoldEmGame.PokerGame.boardImagePixelHeight;
import static TexasHoldEmGame.PokerGame.boardImagePixelWidth;
import static TexasHoldEmGame.PokerGame.callButton;
import static TexasHoldEmGame.PokerGame.changeInCoinsButton;
import static TexasHoldEmGame.PokerGame.checkButton;
import static TexasHoldEmGame.PokerGame.chipImagesDisplay;
import static TexasHoldEmGame.PokerGame.displayChipButtons;
import static TexasHoldEmGame.PokerGame.displayPlayerHandInfoImage;
import static TexasHoldEmGame.PokerGame.downButton;
import static TexasHoldEmGame.PokerGame.exitGameButton;
import static TexasHoldEmGame.PokerGame.foldButton;
import static TexasHoldEmGame.PokerGame.gameFontNameString;
import static TexasHoldEmGame.PokerGame.minusButton;
import static TexasHoldEmGame.PokerGame.nextRoundButton;
import static TexasHoldEmGame.PokerGame.numPlayers;
import static TexasHoldEmGame.PokerGame.numberLinesToDisplay;
import static TexasHoldEmGame.PokerGame.playerHandInfoBackGroundWidth;
import static TexasHoldEmGame.PokerGame.playerHandInfo_backgroundX;
import static TexasHoldEmGame.PokerGame.plusButton;
import static TexasHoldEmGame.PokerGame.roundCounter;
import static TexasHoldEmGame.PokerGame.scrollOffset;
import static TexasHoldEmGame.PokerGame.scrollingInfo;
import static TexasHoldEmGame.PokerGame.scrollingInfoDisplayHeight;
import static TexasHoldEmGame.PokerGame.scrollingInfo_slickFont;
import static TexasHoldEmGame.PokerGame.showIfUserMoveImage;
import static TexasHoldEmGame.PokerGame.startIndexOfDisplayingScrollingInfo;
import static TexasHoldEmGame.PokerGame.textGapHeight;
import static TexasHoldEmGame.PokerGame.thisRound;
import static TexasHoldEmGame.PokerGame.upButton;
import static TexasHoldEmGame.PokerGame.userCannotRaiseImage;
import static TexasHoldEmGame.PokerGame.whiteBlock1;
import static TexasHoldEmGame.PokerGame.whiteBlock1X;
import static TexasHoldEmGame.PokerGame.whiteBlock1Y;
import static TexasHoldEmGame.PokerGame.whiteBlock2;
import static TexasHoldEmGame.PokerGame.whiteBlock2X;
import static TexasHoldEmGame.PokerGame.whiteBlock2Y;
import java.awt.Font;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
//ss

/**
 *
 * @author scott
 */
public class Board {

    //displayHandInfo variables
    public static boolean getHandInfo_fromCalculator = false;
    public static String lastRoundHandName = "";
    public static NumberFormat decimalTwoDigitsFormatter = new DecimalFormat("#00.00");
    public static String titleString_userHandInfo = "User's Hand Info:";
    public static int lastNumOfCardsShown_forHandInfo = -99;
    public static Card handInfoCards[];
    public static ComputerMoveCalculator userMoveCalculator;
    public static int a, b, e, h;
    public static double c, d, k;
    public static String f, recommendedMoveName_1;

    //end of DisplayHandInfo Variables
    public static int heightStringInfo;// = chipValues_slickFont2.getHeight("TT");
    public static String handInfoString1 = "See";
    public static String handInfoString2 = "Hand";
    public static String handInfoString3 = "Info";
    public static String changeInCoins[] = {"Change", "In", "Coins"};
    public static int handInfoGap = (int) (5 * SIZE_RATIO);

    public static int blah1 = 0, blah2 = 0;

    public static int scrollingInfoDisplayWidth;
    public static String startNextRoundString = "Start Next Round";
    public static final String showThatItIsUsersTurnString = "Your turn: ";
    public static final String userCannotRaiseString = "You can't raise!";

    public static Image boardImage;
    public static Image scrollingInfoImage;
    //=================up and down arrows!

    public static int FACE_UP_CARD_SPACER = (int) (10 * SIZE_RATIO);
    public static String checkString, betString, foldString, callString;
    public static String userMoney;
    public static boolean doThisOnce = true;
    public static int indexOfSelectedChip = 0;
    public static boolean justDisplayedACard = false;

    //chip buttons
    //fonts
    public static final Font userOptionsFont = new Font(gameFontNameString, Font.BOLD, (int) (39.00 * SIZE_RATIO));
    public static TrueTypeFont userOptions_slickFont = new TrueTypeFont(userOptionsFont, true);

    public static final Font userOptionsFont2 = new Font(gameFontNameString, Font.BOLD, (int) (26.00 * SIZE_RATIO));
    public static TrueTypeFont userOptions_slickFont2 = new TrueTypeFont(userOptionsFont2, true);
    public static final Font chipValuesFont = new Font(gameFontNameString, Font.BOLD, (int) (24.00 * SIZE_RATIO));
    public static TrueTypeFont chipValues_slickFont2 = new TrueTypeFont(chipValuesFont, true);

    public static int lastFullCardAtTopOfScrollingDisplay_rememberThisCountValue = 0;

    int leftChipDisplayX = foldButton.getX() + foldButton.getWidth() + Player.userDisplayerGap;
    int chipTopY = foldButton.getY();
    int chipGap;

    public Board() throws SlickException {
        try {
            boardImage = new Image("TexasHoldEmData/pokerTable1.png");
            scrollingInfoImage = new Image("TexasHoldEmData/scrolling_info_background3.png");
            boardImage = boardImage.getScaledCopy(boardImagePixelWidth, boardImagePixelHeight);//was BOARD_SCALE//get the image of the board
            scrollingInfoImage = scrollingInfoImage.getScaledCopy(scrollingInfoDisplayWidth, scrollingInfoDisplayHeight);
            //scrollingArrowsHeight = scrollingInfoDisplayWidth / 15;

        } catch (Exception e) {
            System.out.println("Couldn't find image...");
        }
        for (int i = 0; i < 6; i++) {
            chipImagesDisplay[i] = allChipImages[i].getScaledCopy(chipDisplayWidth * 2, chipDisplayWidth * 2); //allChipImages[i].getScaledCopy(1 / makeItThisMuchSmallerRatio );
            //gc, image, x, y, width, height
        }

        chipGap = (betButton.getWidth() - 6 * chipImagesDisplay[0].getWidth()) / 5;
        betString = "BET";
        foldString = "FOLD";
        callString = "CALL";
        checkString = "CHECK";
        startIndexOfDisplayingScrollingInfo = 0;
        determinePlayerLocations();
        displayBoardTextInfo();

        //allRounds.add(new Round ());
    }

    //to determine who goes first, everyone gets a card, player with the highest card is DEALER first, everything rotates clockwise
    public void determinePlayerLocations() {
        int verticalOffset = (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.5);
        int verticalOffset2 = (int) ((float) (PLAYER_DISPLAY_CIRCLE_RADIUS) * 0.2);
        switch (numPlayers) {//don't use breaks...make it do the case of it, and all cases below, more efficient

            case 10:
                allPlayers[9].setPlayerXLoc(BOARD_CENTRE_X - (int) ((float) (BOARD_WIDTH) * 0.48));
                allPlayers[9].setPlayerYLoc(BOARD_CENTRE_Y - (int) ((float) (BOARD_HEIGHT) * 0.33));
            case 9:
                allPlayers[8].setPlayerXLoc(BOARD_CENTRE_X + (int) ((float) (BOARD_WIDTH) * 0.40));
                allPlayers[8].setPlayerYLoc(BOARD_CENTRE_Y + (int) ((float) (BOARD_HEIGHT) * 0.5) - verticalOffset2);
            case 8:
                allPlayers[7].setPlayerXLoc(BOARD_CENTRE_X - (int) ((float) (BOARD_WIDTH) * 0.13));
                allPlayers[7].setPlayerYLoc(BOARD_CENTRE_Y + (int) ((float) (BOARD_HEIGHT) * 0.5) + verticalOffset);
            case 7:

                allPlayers[6].setPlayerXLoc(BOARD_CENTRE_X + (int) ((float) (BOARD_WIDTH) * 0.48));
                allPlayers[6].setPlayerYLoc(BOARD_CENTRE_Y - (int) ((float) (BOARD_HEIGHT) * 0.33));
            case 6:
                allPlayers[5].setPlayerXLoc(BOARD_CENTRE_X - (int) ((float) (BOARD_WIDTH) * 0.54));
                allPlayers[5].setPlayerYLoc(BOARD_CENTRE_Y + verticalOffset2);
            case 5:

                allPlayers[4].setPlayerXLoc(BOARD_CENTRE_X + (int) ((float) (BOARD_WIDTH) * 0.54));
                allPlayers[4].setPlayerYLoc(BOARD_CENTRE_Y + verticalOffset2);
            case 4:
                allPlayers[3].setPlayerXLoc(BOARD_CENTRE_X + (int) ((float) (BOARD_WIDTH) * 0.24));
                allPlayers[3].setPlayerYLoc(BOARD_CENTRE_Y - (int) ((float) (BOARD_HEIGHT) * 0.5) - verticalOffset);

            case 3: // players 2
                allPlayers[2].setPlayerXLoc(BOARD_CENTRE_X - (int) ((float) (BOARD_WIDTH) * 0.40));
                allPlayers[2].setPlayerYLoc(BOARD_CENTRE_Y + (int) ((float) (BOARD_HEIGHT) * 0.5) - verticalOffset2);

            case 2: //opposite ends ish, players 0 1
                allPlayers[0].setPlayerXLoc(BOARD_CENTRE_X + (int) ((float) (BOARD_WIDTH) * 0.13));
                allPlayers[0].setPlayerYLoc(BOARD_CENTRE_Y + (int) ((float) (BOARD_HEIGHT) * 0.5) + verticalOffset);

                allPlayers[1].setPlayerXLoc(BOARD_CENTRE_X - (int) ((float) (BOARD_WIDTH) * 0.24));
                allPlayers[1].setPlayerYLoc(BOARD_CENTRE_Y - (int) ((float) (BOARD_HEIGHT) * 0.5) - verticalOffset);

        }
    }

    public void displayBoardTextInfo() {
        System.out.println("board centre x: " + BOARD_CENTRE_X + ", Board centre y: " + BOARD_CENTRE_Y + ", board width: " + BOARD_WIDTH + ", board height: " + BOARD_HEIGHT);
    }

    public void displayScrollingInfo(GameContainer gc, Graphics g) throws SlickException {
        // g.setColor(Color.green);
        g.drawImage(scrollingInfoImage, GAP_FROM_EDGE, GAP_FROM_EDGE);
        upButton.render(gc, g);
        downButton.render(gc, g);
        int scrollingInfoTextHeight = scrollingInfo_slickFont.getHeight("I");

        int startY_infoFontText = 2 * GAP_FROM_EDGE + upButton.getWidth();
        int leftX_infoFontText = 2 * GAP_FROM_EDGE;
        int counter = 0;

        //making scrolliny y info size = scrolling info
        if (scrollingInfo.size() > numberLinesToDisplay) {
            startIndexOfDisplayingScrollingInfo = scrollingInfo.size() - numberLinesToDisplay;
        }

        counter = -1;

        counter = 0;
        for (int i = startIndexOfDisplayingScrollingInfo + scrollOffset; i < startIndexOfDisplayingScrollingInfo + numberLinesToDisplay + scrollOffset; i++) {
            if (scrollingInfo.size() > i) {
                int yy = startY_infoFontText + (counter) * (textGapHeight + scrollingInfoTextHeight);
                //only display if yy is less than maximum

                if (scrollingInfo.get(i).isText == true) {
                    if (i > 0 && scrollingInfo.get(i - 1).isCard == true) {
                        for (int pp = 0; pp < Card.scrollingCardHeight / scrollingInfoTextHeight - 2; pp++) {
                            counter++;
                        }
                    }
                    yy = startY_infoFontText + counter * (textGapHeight + scrollingInfoTextHeight);
                    blah1 = counter;
                    blah2 = yy;

                    if (yy < scrollingInfoDisplayHeight - textGapHeight) { // this causes it to start glitching!!!
                        scrollingInfo_slickFont.drawString(leftX_infoFontText, yy, scrollingInfo.get(i).getText(), Color.white);
                        justDisplayedACard = false;
                    }
                    //first one draw vertically
                    //scrollingInfo_slickFont.drawString(leftX_infoFontText, startY_infoFontText + (0) * (textGapHeight + scrollingInfoTextHeight), "XXXX", Color.yellow);
                } else if (yy < scrollingInfoDisplayHeight - textGapHeight) {//is a card!!!
                    int newHeight;
                    //card about to move off screen
                    if (yy <= startY_infoFontText + 50) {
                        lastFullCardAtTopOfScrollingDisplay_rememberThisCountValue = counter;
                    }
                    if (yy + Card.scrollingCardHeight < scrollingInfoDisplayHeight - textGapHeight) {
                        newHeight = Card.scrollingCardHeight;
                    } else {
                        newHeight = scrollingInfoDisplayHeight - textGapHeight - yy;
                    }
                    scrollingInfo.get(i).getLeftCard().drawScrollingCard(g, leftX_infoFontText, yy, newHeight);
                    scrollingInfo.get(i).getRightCard().drawScrollingCard(g, leftX_infoFontText + Card.scrollingCardWidth + New_TextOrCardDisplayObject.GAP_BETWEEN_CARDS, yy, newHeight);
                    //justDisplayedACard = true;
                }

                counter++;
            } else {
                break;
            }
        }

        //g.fillRoundRect(GAP_FROM_EDGE, GAP_FROM_EDGE,  scrollingInfoDisplayWidth, appHeight - user_card_height - GAP_FROM_EDGE * 3, GAP_FROM_EDGE * 2);
    }

    public void displayBoard(GameContainer gc, Graphics g) throws SlickException {
        g.drawImage(boardImage, BOARD_LEFT_X, BOARD_TOP_Y);//displays the board
        //put this in loop
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].displayPlayer(g);
        }

        //display the round
        allRounds.get(roundCounter).displayRound(g);
        displayScrollingInfo(gc, g);
        int userAndMoneyDisplayYOffset = blockHeight2 / 5;
        //buttons
        if (thisRound.roundOver == true) {
            nextRoundButton.render(gc, g);
            userOptions_slickFont2.drawString(nextRoundButton.getX() + nextRoundButton.getWidth() / 2 - userOptions_slickFont2.getWidth(startNextRoundString) / 2, nextRoundButton.getY() + nextRoundButton.getHeight() / 2 - userOptions_slickFont2.getHeight("I") / 2, startNextRoundString, Color.white);
        } else if ((PokerGame.betButton.isMouseOver() || PokerGame.plusButton.isMouseOver() || PokerGame.minusButton.isMouseOver()) && PokerGame.getUserMove == true == true && PokerGame.allPlayers[0].allowedToRaise == false) {
            g.drawImage(userCannotRaiseImage, nextRoundButton.getX(), nextRoundButton.getY());
            userOptions_slickFont2.drawString(nextRoundButton.getX() + nextRoundButton.getWidth() / 2 - userOptions_slickFont2.getWidth(userCannotRaiseString) / 2, nextRoundButton.getY() + nextRoundButton.getHeight() / 2 - userOptions_slickFont2.getHeight("I") / 2, userCannotRaiseString, Color.black);

        } else if (PokerGame.getUserMove == true) {
            g.drawImage(showIfUserMoveImage, nextRoundButton.getX(), nextRoundButton.getY());
            userOptions_slickFont2.drawString(nextRoundButton.getX() + nextRoundButton.getWidth() / 2 - userOptions_slickFont2.getWidth(showThatItIsUsersTurnString) / 2, nextRoundButton.getY() + nextRoundButton.getHeight() / 2 - userOptions_slickFont2.getHeight("I") / 2, showThatItIsUsersTurnString, Color.black);
        }
        heightStringInfo = chipValues_slickFont2.getHeight("TT");

        displayPlayerHandInfoImage.render(gc, g);
        changeInCoinsButton.render(gc, g);
        chipValues_slickFont2.drawString(displayPlayerHandInfoImage.getX() + displayPlayerHandInfoImage.getWidth() / 2 - chipValues_slickFont2.getWidth(handInfoString1) / 2, displayPlayerHandInfoImage.getY() + displayPlayerHandInfoImage.getHeight() / 2 - heightStringInfo * 3 / 2 - handInfoGap, handInfoString1, Color.black);
        chipValues_slickFont2.drawString(displayPlayerHandInfoImage.getX() + displayPlayerHandInfoImage.getWidth() / 2 - chipValues_slickFont2.getWidth(handInfoString2) / 2, displayPlayerHandInfoImage.getY() + displayPlayerHandInfoImage.getHeight() / 2 - heightStringInfo * 1 / 2, handInfoString2, Color.black);
        chipValues_slickFont2.drawString(displayPlayerHandInfoImage.getX() + displayPlayerHandInfoImage.getWidth() / 2 - chipValues_slickFont2.getWidth(handInfoString3) / 2, displayPlayerHandInfoImage.getY() + displayPlayerHandInfoImage.getHeight() / 2 + heightStringInfo * 1 / 2 + handInfoGap, handInfoString3, Color.black);
        chipValues_slickFont2.drawString(changeInCoinsButton.getX() + displayPlayerHandInfoImage.getWidth() / 2 - chipValues_slickFont2.getWidth(changeInCoins[0]) / 2, changeInCoinsButton.getY() + displayPlayerHandInfoImage.getHeight() / 2 - heightStringInfo * 3 / 2 - handInfoGap, changeInCoins[0], Color.black);
        chipValues_slickFont2.drawString(changeInCoinsButton.getX() + displayPlayerHandInfoImage.getWidth() / 2 - chipValues_slickFont2.getWidth(changeInCoins[1]) / 2, changeInCoinsButton.getY() + displayPlayerHandInfoImage.getHeight() / 2 - heightStringInfo * 1 / 2, changeInCoins[1], Color.black);
        chipValues_slickFont2.drawString(changeInCoinsButton.getX() + displayPlayerHandInfoImage.getWidth() / 2 - chipValues_slickFont2.getWidth(changeInCoins[2]) / 2, changeInCoinsButton.getY() + displayPlayerHandInfoImage.getHeight() / 2 + heightStringInfo * 1 / 2 + handInfoGap, changeInCoins[2], Color.black);

        whiteBlock1.draw(whiteBlock1X, whiteBlock1Y);
        
       exitGameButton.render(gc, g);
        checkButton.render(gc, g);
        foldButton.render(gc, g);
        callButton.render(gc, g);
        allInButton.render(gc, g);
        betButton.render(gc, g);
        whiteBlock2.draw(whiteBlock2X, whiteBlock2Y);
        plusButton.render(gc, g);//, g);
        minusButton.render(gc, g);

        //exit game button
        userOptions_slickFont.drawString(exitGameButton.getX() + exitGameButton.getWidth() / 2 - userOptions_slickFont.getWidth("Exit") / 2, exitGameButton.getY() + exitGameButton.getHeight()/2 - userOptions_slickFont.getHeight("T") / 2, "Exit", Color.black);
        userOptions_slickFont2.drawString(whiteBlock1X + blockWidth / 2 - userOptions_slickFont2.getWidth("You $" + Integer.toString(allPlayers[0].money)) / 2, whiteBlock1Y + blockHeight2 / 2 - userOptions_slickFont2.getHeight("T") / 2 - userAndMoneyDisplayYOffset, "You $" + Integer.toString(allPlayers[0].money), Color.black); //x y string
        userOptions_slickFont2.drawString(whiteBlock1X + blockWidth / 4 - userOptions_slickFont2.getWidth("5x" + Integer.toString(allPlayers[0].chips[0][1])) / 2, whiteBlock1Y + blockHeight2 * 5 / 6 - userAndMoneyDisplayYOffset, "5x" + Integer.toString(allPlayers[0].chips[0][1]), Color.black);
        userOptions_slickFont2.drawString(whiteBlock1X + blockWidth / 4 - userOptions_slickFont2.getWidth("25x" + Integer.toString(allPlayers[0].chips[1][1])) / 2, whiteBlock1Y + blockHeight2 * 5 / 6 + userOptions_slickFont2.getHeight("T") - userAndMoneyDisplayYOffset, "25x" + Integer.toString(allPlayers[0].chips[1][1]), Color.black);
        userOptions_slickFont2.drawString(whiteBlock1X + blockWidth / 4 - userOptions_slickFont2.getWidth("100x" + Integer.toString(allPlayers[0].chips[2][1])) / 2, whiteBlock1Y + blockHeight2 * 5 / 6 + 2 * userOptions_slickFont2.getHeight("T") - userAndMoneyDisplayYOffset, "100x" + Integer.toString(allPlayers[0].chips[2][1]), Color.black);

        userOptions_slickFont2.drawString(whiteBlock1X + 3 * blockWidth / 4 - userOptions_slickFont2.getWidth("500x" + Integer.toString(allPlayers[0].chips[3][1])) / 2, whiteBlock1Y + blockHeight2 * 5 / 6 - userAndMoneyDisplayYOffset, "500x" + Integer.toString(allPlayers[0].chips[3][1]), Color.black);
        userOptions_slickFont2.drawString(whiteBlock1X + 3 * blockWidth / 4 - userOptions_slickFont2.getWidth("1000x" + Integer.toString(allPlayers[0].chips[4][1])) / 2, whiteBlock1Y + blockHeight2 * 5 / 6 + userOptions_slickFont2.getHeight("T") - userAndMoneyDisplayYOffset, "1000x" + Integer.toString(allPlayers[0].chips[4][1]), Color.black);
        userOptions_slickFont2.drawString(whiteBlock1X + 3 * blockWidth / 4 - userOptions_slickFont2.getWidth("5000x" + Integer.toString(allPlayers[0].chips[5][1])) / 2, whiteBlock1Y + blockHeight2 * 5 / 6 + 2 * userOptions_slickFont2.getHeight("T") - userAndMoneyDisplayYOffset, "5000x" + Integer.toString(allPlayers[0].chips[5][1]), Color.black);

        userOptions_slickFont2.drawString(checkButton.getX() + checkButton.getWidth() / 2 - userOptions_slickFont2.getWidth(checkString) / 2, checkButton.getY() + checkButton.getHeight() / 2 - userOptions_slickFont2.getHeight(checkString) / 2, checkString, Color.black);//x y string colour
        userOptions_slickFont2.drawString(foldButton.getX() + foldButton.getWidth() / 2 - userOptions_slickFont2.getWidth(foldString) / 2, foldButton.getY() + foldButton.getHeight() / 2 - userOptions_slickFont2.getHeight(foldString) / 2, foldString, Color.black);//x y string colour

        userOptions_slickFont2.drawString(callButton.getX() + callButton.getWidth() / 2 - userOptions_slickFont2.getWidth(callString) / 2, callButton.getY() + callButton.getHeight() / 2 - userOptions_slickFont2.getHeight(callString) / 2, callString, Color.black);//x y string colour
        userOptions_slickFont2.drawString(allInButton.getX() + allInButton.getWidth() / 2 - userOptions_slickFont2.getWidth("ALL IN") / 2, allInButton.getY() + allInButton.getHeight() / 2 - userOptions_slickFont2.getHeight("I") / 2, "ALL IN", Color.black);//x y string colour
        
        
        userOptions_slickFont.drawString(betButton.getX() + betButton.getWidth() / 2 - userOptions_slickFont.getWidth(betString) / 2, betButton.getY() + betButton.getHeight() / 2 - userOptions_slickFont.getHeight(betString) / 2, betString, Color.black);//x y string colour

        userOptions_slickFont.drawString(whiteBlock2X + blockWidth / 2 - userOptions_slickFont.getWidth("$" + Integer.toString(allPlayers[0].thisBetAmount)) / 2, whiteBlock2Y + blockHeight2 / 2 - userOptions_slickFont.getHeight("T") / 2, "$" + Integer.toString(allPlayers[0].thisBetAmount), Color.black); //x y string

        int chipValuesDisplayY = chipTopY + chipImagesDisplay[0].getHeight();
        int chipDeltaY = chipValues_slickFont2.getHeight("0") - 5;
        String chipValue = "";
        for (int i = 0; i < 6; i++) {//displays chips and their value 
            if (allPlayers[0].chips[i][1] == 0) {
                continue;//think of this
//                if (i >= 5) {
//                    break;
//                }
            }
            if (doThisOnce == true) {
                displayChipButtons[i].setX(leftChipDisplayX + (chipGap + chipImagesDisplay[0].getWidth()) * i);
                displayChipButtons[i].setY(chipTopY);
                if (i == 5) {
                    doThisOnce = false;
                }
            }
            //draw circle around selected chip

            int ovalY = displayChipButtons[0].getY();
            int ovalX = displayChipButtons[indexOfSelectedChip].getX();//leftChipDisplayX + (chipGap + chipImagesDisplay[0].getWidth()) * indexOfSelectedChip + chipImagesDisplay[0].getWidth() / 2  - highlightWidth;
            g.setColor(Color.yellow);
            g.drawOval(ovalX + 1, ovalY + 1, chipImagesDisplay[0].getWidth() - 2, chipImagesDisplay[0].getWidth() - 2);
            g.drawOval(ovalX, ovalY, chipImagesDisplay[0].getWidth(), chipImagesDisplay[0].getWidth());
            g.drawOval(ovalX - 1, ovalY - 1, chipImagesDisplay[0].getWidth() + 2, chipImagesDisplay[0].getWidth() + 2);//x y w h 
            g.drawOval(ovalX - 2, ovalY - 2, chipImagesDisplay[0].getWidth() + 4, chipImagesDisplay[0].getWidth() + 4);
            g.drawOval(ovalX - 3, ovalY - 3, chipImagesDisplay[0].getWidth() + 6, chipImagesDisplay[0].getWidth() + 6);
            displayChipButtons[i].render(gc, g);
            // g.drawImage(chipImagesDisplay[i], leftChipDisplayX + (chipGap + chipImagesDisplay[0].getWidth()) * i, chipTopY);
            switch (i) {
                case 0:
                    chipValue = (Integer.toString(WHITE_CHIP));
                    chipDeltaY = chipValues_slickFont2.getHeight("0") * 4 / 5;
                    break;
                case 1:
                    chipValue = (Integer.toString(GREEN_CHIP));
                    chipDeltaY = 0;
                    break;
                case 2:
                    chipValue = (Integer.toString(BLACK_CHIP));
                    chipDeltaY = chipValues_slickFont2.getHeight("0") * 4 / 5;
                    break;
                case 3:
                    chipValue = (Integer.toString(PURPLE_CHIP));
                    chipDeltaY = 0;
                    break;
                case 4:
                    chipValue = (Integer.toString(YELLOW_CHIP));
                    chipDeltaY = chipValues_slickFont2.getHeight("0") * 4 / 5;
                    break;
                case 5:
                    chipValue = (Integer.toString(RED_CHIP));
                    chipDeltaY = 0;
                    break;

            }
            chipValues_slickFont2.drawString(leftChipDisplayX + (chipGap + chipImagesDisplay[0].getWidth()) * i + chipImagesDisplay[0].getWidth() / 2 - chipValues_slickFont2.getWidth(chipValue) / 2, chipValuesDisplayY + chipDeltaY, chipValue);//x y string

            //displayHandInformation
            if (PokerGame.displayHandInfo == true) {
                displayUserHandInfo(gc, g);
            }
        }

    }

    public static void displayUserHandInfo(GameContainer gc, Graphics g) throws SlickException {

        //draw background
        g.drawImage(PokerGame.playerHandInfo_backgroundOutline, PokerGame.playerHandInfo_outlineX, PokerGame.playerHandInfo_outlineY);
        g.drawImage(PokerGame.playerHandInfo_backgroundImage, PokerGame.playerHandInfo_backgroundX, PokerGame.playerHandInfo_backgroundY);

        //title at top
        userOptions_slickFont.drawString(playerHandInfo_backgroundX + playerHandInfoBackGroundWidth / 2 - userOptions_slickFont.getWidth(titleString_userHandInfo) / 2, PokerGame.playerHandInfo_backgroundY + GAP_FROM_EDGE, titleString_userHandInfo, Color.white);
        //see how many cards to make, depends on number of face up cards
        if (thisRound.showRiver == true) {
            lastNumOfCardsShown_forHandInfo = 7;
            handInfoCards = new Card[7];
        } else if (thisRound.showTurn == true) {
            lastNumOfCardsShown_forHandInfo = 6;
            handInfoCards = new Card[6];
        } else if (thisRound.showFlop == true) {//include "and doesnt equal last num of cards shown) or something similar
            lastNumOfCardsShown_forHandInfo = 5;
            handInfoCards = new Card[5];
        } else {
            lastNumOfCardsShown_forHandInfo = 2;
            handInfoCards = new Card[2];
        }
        //make x new cards
        for (int i = 0; i < 2; i++) {
            handInfoCards[i] = new Card(allPlayers[0].hand[i].value, allPlayers[0].hand[i].suit);
            handInfoCards[i].cardFileName = allPlayers[0].hand[i].cardFileName;
        }
        for (int i = 2; i < lastNumOfCardsShown_forHandInfo; i++) {
            handInfoCards[i] = new Card(thisRound.faceUpCards[i - 2].value, thisRound.faceUpCards[i - 2].suit);
            handInfoCards[i].cardFileName = thisRound.faceUpCards[i - 2].cardFileName;
        }
        int playHandInfoCard_startLeftX = playerHandInfo_backgroundX + playerHandInfoBackGroundWidth / 2 - (int) ((3.5 * PokerGame.faceUpCardWidth)) - 3 * GAP_FROM_EDGE;
        //display x cards
        for (int i = 0; i < lastNumOfCardsShown_forHandInfo; i++) {
            handInfoCards[i].drawPlayerHandInfoCard(g, playHandInfoCard_startLeftX + i * (PokerGame.faceUpCardWidth + GAP_FROM_EDGE), userOptions_slickFont.getHeight("T") + PokerGame.playerHandInfo_backgroundY + 2 * GAP_FROM_EDGE);
        }

        //make user move calculator
        if (getHandInfo_fromCalculator == true) {
            getHandInfo_fromCalculator = false;
            Card playersOwnCards[] = {allPlayers[0].hand[0], allPlayers[0].hand[1]};
            allPlayers[0].PERSONALITY = PokerGame.TIGHT_AGGRESSIVE;
            userMoveCalculator = new ComputerMoveCalculator(allPlayers[0].PERSONALITY, allPlayers[0].name, thisRound.amountToCall, allPlayers[0].money, allPlayers[0].chips, playersOwnCards, thisRound.faceUpCards, PokerGame.round_turnCounter, thisRound.minimumRaise, allPlayers[0].totalBetThisTurn_perShowOfCards, allPlayers[0].allowedToRaise, thisRound.lastRaiseAmount, thisRound.pot);
            a = thisRound.amountToCall;
            b = thisRound.pot;
            c = userMoveCalculator.getPotOdds_Percentage();
            d = userMoveCalculator.getPlayerOdds_Percentage();//remember to format these!
            e = userMoveCalculator.gethowGoodIsPlayerHand();
            f = userMoveCalculator.getNameOfHand_fromOdds();
            k = userMoveCalculator.getchanceOfMakingHand_allInOnFlop();
            if (f.length() > 1) {
                lastRoundHandName = f;
            }
//        lastTurnCounter = -1;
            recommendedMoveName_1 = "";
            //lastTurnCounter = PokerGame.round_turnCounter;
            h = userMoveCalculator.getDecision();
            recommendedMoveName_1 = "";
            if (h == thisRound.amountToCall && thisRound.amountToCall == 0) {
                recommendedMoveName_1 = "Check";
            } else if (h == thisRound.amountToCall) {
                recommendedMoveName_1 = "Call";
            } else if (h > thisRound.amountToCall) {
                recommendedMoveName_1 = "Raise";
            } else if (thisRound.amountToCall == 0) {
                recommendedMoveName_1 = "Check";
            } else {
                recommendedMoveName_1 = "Fold";
            }
        }

        //userOptions_slickFont2
        //display amountToCall
        int rightColumnOneX = playerHandInfo_backgroundX + 2 * (PokerGame.faceUpCardWidth) + 7 * GAP_FROM_EDGE;
        int gapBetweenInfoStrings = GAP_FROM_EDGE / 2 + userOptions_slickFont2.getHeight("T");
        int actual_info_strings_start_y = PokerGame.playerHandInfo_backgroundY + userOptions_slickFont.getHeight("T") + handInfoCards[0].cardImage.getHeight() + 3 * GAP_FROM_EDGE;
        //LEFT 1 COLUMN PRINTS
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 0 * gapBetweenInfoStrings, "Pot:", Color.white);
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 1 * gapBetweenInfoStrings, "Amount to Call:", Color.white);
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 2 * gapBetweenInfoStrings, "Pot Odds:", Color.white);
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 3 * gapBetweenInfoStrings, "User Odds (UO): ", Color.white);
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 4 * gapBetweenInfoStrings, "UO All In on Flop: ", Color.white);
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 5 * gapBetweenInfoStrings, "Hand Score: ", Color.white);
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 6 * gapBetweenInfoStrings, "Current Hand Name: ", Color.white);
        userOptions_slickFont2.drawString(playerHandInfo_backgroundX + GAP_FROM_EDGE, actual_info_strings_start_y + 7 * gapBetweenInfoStrings, "Recommended Move: ", Color.white);

        //RIGHT 1 COLUMN PRINTS
        userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 0 * gapBetweenInfoStrings, "$" + Integer.toString(b), Color.white);
        userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 1 * gapBetweenInfoStrings, "$" + Integer.toString(a), Color.white);
        userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 2 * gapBetweenInfoStrings, "%" + decimalTwoDigitsFormatter.format(c), Color.white);
        userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 3 * gapBetweenInfoStrings, "%" + decimalTwoDigitsFormatter.format(d), Color.white);
        if (thisRound.showFlop == true && thisRound.showTurn == false){
            userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 4 * gapBetweenInfoStrings, "%" + decimalTwoDigitsFormatter.format(k), Color.white);
        }
        userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 5 * gapBetweenInfoStrings, Integer.toString(e), Color.white);
        if (thisRound.isRoundOver() == false) {
            userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 6 * gapBetweenInfoStrings, f, Color.white);
        } else {
            userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 6 * gapBetweenInfoStrings, lastRoundHandName, Color.white);
        }

        userOptions_slickFont2.drawString(rightColumnOneX, actual_info_strings_start_y + 7 * gapBetweenInfoStrings, recommendedMoveName_1, Color.white);

    }

}
