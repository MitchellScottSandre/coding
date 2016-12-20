/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

//import static TexasHoldEm2.Board.chipImagesDisplay;
//import static TexasHoldEm2.Player.PLAYER_DISPLAY_CIRCLE_RADIUS;
import static TexasHoldEmGame.Board.FACE_UP_CARD_SPACER;
import static TexasHoldEmGame.Player.chipDisplayWidth;
import static TexasHoldEmGame.Round.faceUpCardsY;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.time.Duration;
import java.util.ArrayList;
import java.util.Calendar;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.BasicGame;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.TrueTypeFont;
import org.newdawn.slick.gui.MouseOverArea;
import org.newdawn.slick.util.Log;

/**
 *
 * @author scott
 */
public class PokerGame extends BasicGame {

    public static final String nameVersionInfo =  "Created by Scott Sandre, Dec 2016.         Version 1.0";
    public static final double DISPLAY_SCREEN_SIZE_PERCENT_FOR_TESTING = 1.00;//1.00 is normal

    public static final int ROUNDS_PER_BLINDS_LEVEL = 8;//7, just put 2 for testing
    public static final String DASH = "-";//\u2022";
    public static final String BULLET = "\u2022";
    public static String gameFontNameString = "Calabri";

    public static int faceUpCardWidth = 0;

    //personality constants
    public static final String namesOfPlayerTypes[] = {"Loose Passive", "Tight Passive", "Loose Aggressive", "Tight Aggressive"};
    public static final int LOOSE_PASSIVE = 0;
    public static final int TIGHT_PASSIVE = 1;
    public static final int LOOSE_AGGRESSIVE = 2;
    public static final int TIGHT_AGGRESSIVE = 3;

    //end of fonts
    public static double moreThanFivePlayers_ScrollingInfoPercentage = 0.167;

    public static final int[] playerNumberToPlayerIndexOnTable = {4, 9, 6, 0, 2, 7, 1, 5, 3, 8};//un does the below array
    public static final int[] playersTableOrder = {3, 6, 4, 8, 0, 7, 2, 5, 9, 1};

    public static ArrayList<Round> allRounds = new ArrayList<Round>();
    public static int roundCounter = -1;
    public static int scrollOffset = 0;

    public static final char CLUBS = 'C';
    public static final char DIAMONDS = 'D';
    public static final char HEARTS = 'H';
    public static final char SPADES = 'S';

    public static int user_card_height, user_card_width;

    public static Image background;

    public static final int T1000 = 0;
    public static final int T1500 = 1;
    public static final int T500 = 2;

    public static final int WHITE_CHIP = 5;
    public static final int GREEN_CHIP = 25;
    public static final int BLACK_CHIP = 100;
    public static final int PURPLE_CHIP = 500;
    public static final int YELLOW_CHIP = 1000;
    public static final int RED_CHIP = 5000;

    public static Image WHITE_CHIP_IMAGE;
    public static Image GREEN_CHIP_IMAGE;
    public static Image BLACK_CHIP_IMAGE;
    public static Image PURPLE_CHIP_IMAGE;
    public static Image YELLOW_CHIP_IMAGE;
    public static Image RED_CHIP_IMAGE;

    public static Image allChipImages[] = new Image[6];

    public static int button_dimension = 20;
    public static Image upArrow, downArrow;
    public static MouseOverArea upButton, downButton;
    public static final int ARROW_GAP_FROM_EDGE = 7;
    public static Image userPlayOptionsBackground;
    public static int userPlayOptionsBackground_width;
    public static int userPlayOptionsBackground_height;
    public static int playerHandInfo_backgroundX, playerHandInfo_backgroundY, playerHandInfo_outlineX, playerHandInfo_outlineY;
    //buttons
    public static Image showRulesImage;
    public static MouseOverArea showRulesButton;
    public static Image showIfUserMoveImage, userCannotRaiseImage;
    public static Image playerHandInfo_backgroundImage, playerHandInfo_backgroundOutline;
    public static Image getPlayerHandInfoImage;
    public static Image exitGameImage;
    public static MouseOverArea exitGameButton;
    public static MouseOverArea displayPlayerHandInfoImage;//display hand info
    public static Image changeInCoinsImage;
    public static MouseOverArea changeInCoinsButton;//changeInCoins
    public static Image nextRoundImage;
    public static MouseOverArea nextRoundButton;
    public static Image whiteBlock1, whiteBlock2, redBlock, blueBlock, greenBlock, orangeBlock, plusSign, minusSign, yellowBlock;
    public static MouseOverArea checkButton, foldButton, callButton, betButton, negativeBetButton, plusButton, minusButton, allInButton;
    public static int blockWidth, blockHeight, blockHeight2, plusSignWidth;
    public static int whiteBlock2X, whiteBlock2Y, whiteBlock1X, whiteBlock1Y;
    public static int plusX, plusY, minusX, minusY;
// end of buttons

    public static final Color colours[] = new Color[6];

    public static final int ENTER_KEY = 28;

    public static final double WHITE_STARTING_RATIO = 0.4;
    public static final double GREEN_STARTING_RATIO = 0.3;
    public static final double BLACK_STARTING_RATIO = 0.2;
    public static final double PURPLE_STARTING_RATIO = 0.1;

    public static boolean FACE_UP = true;
    public static boolean FACE_DOWN = false;

    public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    public static double widthDouble = screenSize.getWidth();
    public static double heightDouble = screenSize.getHeight();

    public static final double width_to_height_ratio = 1.45;
    public static int appHeight = (int) (heightDouble * 0.88 * DISPLAY_SCREEN_SIZE_PERCENT_FOR_TESTING);//950
    public static int appWidth = (int) (appHeight * width_to_height_ratio);//(1377)
    public static int boardImagePixelWidth = (int) (appWidth * 0.65);//600;
    public static int boardImagePixelHeight = (int) (appHeight * 0.5452);//348;

    public static final double SIZE_RATIO = (double) (appHeight) / (double) (950);

    public static final int GAP_FROM_EDGE = (int) (15 * SIZE_RATIO);

    public static final float FACE_UP_CARD_SCALE_1 = (float) SIZE_RATIO * 1 / 6.5f;
    public static final float FACE_UP_CARD_SCALE = (float) SIZE_RATIO * 1 / 5f;
    public static final int FACE_UP_CARD_HEIGHT = (int) (145 * SIZE_RATIO), FACE_UP_CARD_WIDTH = (int) (100 * SIZE_RATIO);
    public static final float USER_CARD_SCALE = FACE_UP_CARD_SCALE_1 * 2.2f;
    public static final float COMP_CARD_SCALE = 1f / 18f;

    //fonts
    public static final Font playerNameFont = new Font(gameFontNameString, Font.BOLD, (int) (14 * SIZE_RATIO));
    public static final Font playerInfoFont = new Font(gameFontNameString, Font.BOLD, (int) (SIZE_RATIO * 12));
    public static TrueTypeFont playerName_slickFont, playerInfo_slickFont;

    public static final Font scrollingInfoFont = new Font(gameFontNameString, Font.BOLD, (int) (SIZE_RATIO * 15));
    public static TrueTypeFont scrollingInfo_slickFont;

    public static int numPlayers;
    public static int typeOfGameChoice;//number of chips
    public static int totalChips;

    public int startingChipAmount[][][];//value of chip, number of chips of that value

    public static Deck deck;
    public static Board board;
    public static Player allPlayers[];

    public static int mouseXLoc, mouseYLoc;
    public static String mouseXLocString, mouseYLocString;

    public static final int BOARD_Y_RAISE_OFFSET = (int) (-90 * SIZE_RATIO);
    ;
    public static final int BOARD_X_RIGHT_OFFSET = appWidth / 12;
    public static float BOARD_SCALE = (float) appWidth / (float) boardImagePixelWidth * 0.65f;//relate this to board image pixel width and app width
    public static int BOARD_LEFT_X = (appWidth - boardImagePixelWidth) / 2 + BOARD_X_RIGHT_OFFSET;//((appWidth - (int) ((float) boardImagePixelWidth * BOARD_SCALE)) / 2) + BOARD_X_RIGHT_OFFSET;
    public static int BOARD_TOP_Y = (int) (appHeight * 0.1326);//((appHeight - (int) ((float) boardImagePixelHeight * BOARD_SCALE)) / 2 + BOARD_Y_RAISE_OFFSET);
    public static int BOARD_WIDTH = boardImagePixelWidth;//(int) ((float) (boardImagePixelWidth) * BOARD_SCALE);
    public static int BOARD_HEIGHT = boardImagePixelHeight;//(int) ((float) (boardImagePixelHeight) * BOARD_SCALE);

    public static int approx_max_chips;

    public static int BOARD_CENTRE_X = appWidth / 2 + BOARD_X_RIGHT_OFFSET;
    public static int BOARD_CENTRE_Y = BOARD_TOP_Y + (int) (((double) (boardImagePixelHeight) * BOARD_SCALE) / 2);
    public static boolean screen1_StartScreen = true;
    public static boolean screen2_OptionsScreen_1_numberPlayers = false;
    public static boolean screen2_OptionsScreen_2_gameChips = false;
    public static boolean finishedGettingNumPlayers = false;
    public static boolean displayBoard = false;
    public static boolean displayStartMenu = true;

    public static int faceupCard1X = BOARD_CENTRE_X - (int) ((float) (BOARD_WIDTH) * 0.33);

    public static boolean moveOnToNextTaskWithInput = false;

    public static String numberPlayersString = "";
    public static int scrollingArrowsHeight = (BOARD_LEFT_X - 2 * GAP_FROM_EDGE) / 9;
    private static AppGameContainer app;
    public static Image chipImagesDisplay[];
    public static MouseOverArea displayChipButtons[] = new MouseOverArea[6];

    //starting stuff
    public static int startindexOfDealer;
    public static int startindexOfSmallBlind;
    public static int startindexOfBigBlind;

    //gameplay variables
    public static boolean deltDeck = false;
    public static boolean gameplayMoveOn = false;
    public static boolean gotFirstRoundUpToUserMove = false;
    public static boolean getUserMove = false;
    public static boolean gotUserMove = false;
    public static boolean gotFirstRoundAfterUserMove = false;
    public static boolean getRestOfPlayers_untilEveryoneHasCalledOrFolded = false;
    public static boolean doOnce = false;
    public static boolean finishedTurn = false;
    public static int round_turnCounter = 0;
    public static Calendar c1;
    public static long beforeTime, afterTime;//tenths of seconds
    public static boolean getBeforeTime;
    public static boolean someoneAllInOnFlop = false;

    public static final double WAIT_TIME_BETWEEN_TURNS_tenthsOfSeconds = 0;////==============================WAIT TIME BETWEEN TURNS
    public static final int WAIT_TIME_BETWEEN_ROUNDS = 3;//this doesn't matter any more...we have a button that starts the next round
    public static boolean userIsDealerSoGoToEnd_completedThisAlreaday = false;
    public static int printedOutLast_userItIsYourTurn = 0;

    public static int turnCounter = 0;
    // public static boolean 

    public static final int levelSmallBlinds[] = {10, 15, 20, 25, 50, 75, 100, 150, 200, 300, 400, 500, 600, 800, 1000};
    //public static int mouseReleasedX, mouseReleasedY;
    public static boolean mouseJustReleased = false;

    //=======================================================//Start Menu Stuff================================
    public static Font mainTitleFont;// = new Font(gameFontNameString, Font.BOLD, 50);
    public static TrueTypeFont mainTitle_slickFont;// = new TrueTypeFont(mainTitleFont, true);
    public static final int optionsMenuVerticalShiftUp = -appHeight / 15;
    public static Font mainOptionsFont;
    public static TrueTypeFont mainOptions_slickFont;
    public static String mainTitleString = "Texas Hold 'Em Poker";
    public static final int TITLE_Y = BOARD_HEIGHT / 10;
    public static int TITLE_X;
    public static final int TITLE_FONT_SIZE = (int) (53.00 * SIZE_RATIO);
    public static final int OPTIONS_FONT_SIZE = (int) (45.00 * SIZE_RATIO);
    public static Font rulesFont = new Font(gameFontNameString, Font.BOLD, (int) (SIZE_RATIO * 28));
    public static TrueTypeFont rules_slickFont;
    public static Image money_image;
    public static String pressAnyButtonString = "Press any button to continue";
    public static boolean displayPressAnyButton = true;
    public static boolean displayGetNumberOfPlayers = false;
    public static Image numberOfPlayerImages[] = new Image[9];
    public static MouseOverArea numberOfPlayerButtons[] = new MouseOverArea[9];
    public static double ratioOfButtonsToAppHeight = 0.1;
    public static int numberButtonsLeftX, numberButtonsGap;
    public static final String[] NUMBERS_STRINGS = {"3", "4", "5", "6", "7", "8", "9", "10"};
    public static final int numberButtonsY = appHeight / 3 + optionsMenuVerticalShiftUp;
    public static Image mouseOverNumberImage, mouseSelectedNumberImage;
    public static int highlightCircleDifference_X;
    public static int indexOfSelectedHighlight = -1, indexOfSelectedHighlightGameType = -1;
    public static String numberPlayersDisplayString = "Select the number of players:";
    public static Image gameTypeImages[] = new Image[3];
    public static MouseOverArea gameTypeButtons[] = new MouseOverArea[9];
    public static int gameTypeButtonsLeftX, gameTypeButtonsGap;
    public static Image mouseOverGameTypeImage, mouseSelectedGameTypeImage;
    public static final String GAME_TYPES_STRING[] = {"T500", "T1000", "T1500"};
    public static final String gameTypeDispayString = "Select the game type you wish to play:";
    public static final int gap_between_text_and_shape = (int) (10 * SIZE_RATIO);
    public static boolean showRules = false;
    public static ArrayList<String> gameRules = new ArrayList<String>();
    //========================END OF START MENU STUFF ==================================
    public static Image startTheGameImage;
    public static MouseOverArea startTheGameButton;
    public static final int gapFromLeftEdge = (int) (60 * SIZE_RATIO);
    ;
    public static final String startGameString = "Start Game";
    public static int startGameButtonBorderWidth = (int) (17 * SIZE_RATIO);

    public static ArrayList<New_TextOrCardDisplayObject> scrollingInfo = new ArrayList<New_TextOrCardDisplayObject>();

    //scrolling information stuff
    public static int scrollingInfoDisplayHeight;// = appHeight - user_card_height - GAP_FROM_EDGE * 3;
    public static int textGapHeight;
    public static int numberLinesToDisplay;//= (scrollingInfoDisplayHeight - 2 * GAP_FROM_EDGE - upButton.getWidth()) / (scrollingInfo_slickFont.getHeight("I") + textGapHeight);
    public static int startIndexOfDisplayingScrollingInfo;

    public static int game_difficulty = 2;//easy 0, med 1, hard 2
    public static boolean mouseDown = false;
    public static boolean startedMouseDownTimer = false;
    public static long mouseDownBeforeTime = 0;
    public static long timeMouseHasBeenPressedDown = 0;
    public static int speedUpIndex = 0, speedDownIndex = 0;
    public static int indexWhenToActuallyIncriment = 2;
    public static int playerHandInfoBackGroundWidth;

    //ROUND VARIABLES
    public static Round thisRound;
    public static boolean makeAndStartNewRound = false;
    public static boolean someoneIsAllIn = false;
    public static boolean getBeforeTime_forRound = false;
    public static long beforeTime_forRound = 0;
    public static long afterTime_forRound = 0;
    public static int nextRoundImage_heightRatio = 6; // boardHeight  / heightRatio
    public static int nextRoundImage_widthRatio = 4;//boardWidth / widthRatio
    public static boolean getNextRoundButtonClicked = false;
    public static boolean alreadyDidThisInRound = false;
    public static boolean allowedToMoveOnToNextTurnCounterIndex = false;
    public static int allPlayersHaveFoldedBesidesThisPlayer = -99;
    public static Color WINNING_COLOUR = Color.orange;//new Color(25, 130, 185); // blue
    public static Color NORMAL_PLAYER_COLOR = new Color(0, 150, 21); //green
    public static Color PLAYER_FOLD_COLOR = Color.black;
    public static boolean someoneAutomaticallyWins = false;
    public static boolean entireGameOver = false, userWon = false;
    public static BouncingString bouncingUserWonGameString, bouncingUserLostGameString, bouncingGameOverString;
    public static boolean printedUserWonOrLost = false;
    public static boolean displayedLastFaceUpCard = false;
    public static boolean allOtherPlayersHaveFolded_ThisPersonWins_CompletedThisAlready = false;
    public static int numberPlayersLeftInGame = numPlayers;
    public static boolean playerIsAllIn_moneyIsLessThanAmountToCall = false;

    //DISPLAY HAND INFO
    public static boolean displayHandInfo = false;

    //==========================================================END of Start Menu Stuff=========================
    public static void main(String[] arguments) {
        try {
            app = new AppGameContainer(new PokerGame());
            app.setDisplayMode(appWidth, appHeight, false);
            app.start();
        } catch (SlickException e) {
            e.printStackTrace();
        }
    }

    public PokerGame() {
        super("Texas Hold 'Em");
    }

    public void init(GameContainer gc) throws SlickException {
        gc.setShowFPS(false);
        String ss = "";
        try {
            startTheGameImage = new Image("TexasHoldEmData/start_the_game.png");
            startTheGameImage = startTheGameImage.getScaledCopy(appHeight / 3, appHeight / 6);
            startTheGameButton = new MouseOverArea(gc, startTheGameImage, gapFromLeftEdge, appHeight * 4 / 5 - gapFromLeftEdge / 2);
            gameTypeImages[0] = new Image("TexasHoldEmData/circle_blue.png");
            gameTypeImages[1] = new Image("TexasHoldEmData/circle_green.png");
            gameTypeImages[2] = new Image("TexasHoldEmData/circle_yellow.png");
            for (int i = 0; i < 3; i++) {
                gameTypeImages[i] = gameTypeImages[i].getScaledCopy((int) (appHeight * ratioOfButtonsToAppHeight * 2), (int) (appHeight * ratioOfButtonsToAppHeight * 2));
                gameTypeButtonsGap = gameTypeImages[0].getWidth() / 2;
                gameTypeButtonsLeftX = (int) (appWidth / 2 - 1.5 * gameTypeImages[i].getWidth() - gameTypeButtonsGap * 2);
                gameTypeButtons[i] = new MouseOverArea(gc, gameTypeImages[i], gameTypeButtonsLeftX + i * (gameTypeImages[0].getWidth() + gameTypeButtonsGap * 2), appHeight / 12 * 7 + optionsMenuVerticalShiftUp);
            }

            mouseOverNumberImage = new Image("TexasHoldEmData/numbers_mouse_over.png");//, mouseSelectedNumberImage, displayMouseNumberImage;
            mouseSelectedNumberImage = new Image("TexasHoldEmData/numbers_selected.png");

            //mouseOverGameTypeImage, mouseSelectedGameTypeImage;
            mouseOverGameTypeImage = new Image("TexasHoldEmData/numbers_mouse_over.png");
            mouseSelectedGameTypeImage = new Image("TexasHoldEmData/numbers_selected.png");
            for (int i = 0; i < 8; i++) {//make the button images for the numbers to be displayed
                switch (i % 4) {
                    case 0:
                        ss = "circle_blue";
                        break;
                    case 1:
                        ss = "circle_green";
                        break;
                    case 2:
                        ss = "circle_red";
                        break;
                    case 3:
                        ss = "circle_yellow";
                        break;

                }
                numberOfPlayerImages[i] = new Image("TexasHoldEmData/" + ss + ".png");
                numberOfPlayerImages[i] = numberOfPlayerImages[i].getScaledCopy((int) (appHeight * ratioOfButtonsToAppHeight), (int) (appHeight * ratioOfButtonsToAppHeight));
            }
            mouseOverNumberImage = mouseOverNumberImage.getScaledCopy((int) (numberOfPlayerImages[0].getWidth() * 1.068), (int) (numberOfPlayerImages[0].getWidth() * 1.068));
            mouseSelectedNumberImage = mouseSelectedNumberImage.getScaledCopy(mouseOverNumberImage.getWidth(), mouseOverNumberImage.getWidth());
            //displayMouseNumberImage = mouseOverNumberImage;
            highlightCircleDifference_X = mouseOverNumberImage.getWidth() - numberOfPlayerImages[0].getWidth();

            //mouseOverGmeTypeButton stuff
            mouseOverGameTypeImage = mouseOverGameTypeImage.getScaledCopy(mouseOverNumberImage.getWidth() * 2, mouseOverNumberImage.getWidth() * 2);
            mouseSelectedGameTypeImage = mouseSelectedGameTypeImage.getScaledCopy(mouseOverNumberImage.getWidth() * 2, mouseOverNumberImage.getWidth() * 2);
            numberButtonsGap = (int) ((double) ((appWidth - 8 * numberOfPlayerImages[0].getWidth())) / 9.0);//numberOfPlayerImages[0].getWidth() / 2;
            numberButtonsLeftX = (int) (appWidth / 2 - 4 * numberOfPlayerImages[0].getWidth() - 3.5 * numberButtonsGap); // centre of app - 4.5*buttonWidth - 4 buttonGap
            int w = numberOfPlayerImages[0].getWidth();

            for (int i = 0; i < 8; i++) {//make the BUTTONS (using the images you just made) and set their x and y locations
                numberOfPlayerButtons[i] = new MouseOverArea(app, numberOfPlayerImages[i], numberButtonsLeftX + i * (w + numberButtonsGap), numberButtonsY);//app, image, x, y
            }
        } catch (Exception e) {
            System.out.println("could not scan in circles for number buttons");
        }
        scrollingInfo_slickFont = new TrueTypeFont(scrollingInfoFont, true);
        textGapHeight = scrollingInfo_slickFont.getHeight("I") / 3;
        //numberLinesToDisplay = (scrollingInfoDisplayHeight - 2 * GAP_FROM_EDGE - upButton.getWidth()) / (scrollingInfo_slickFont.getHeight("I") + textGapHeight);
        playerName_slickFont = new TrueTypeFont(playerNameFont, true);
        playerInfo_slickFont = new TrueTypeFont(playerInfoFont, true);
        mainTitleFont = new Font("Calibri", Font.BOLD, TITLE_FONT_SIZE);
        mainTitle_slickFont = new TrueTypeFont(mainTitleFont, true);

        mainOptionsFont = new Font(gameFontNameString, Font.BOLD, OPTIONS_FONT_SIZE);
        mainOptions_slickFont = new TrueTypeFont(mainOptionsFont, true);
        rules_slickFont = new TrueTypeFont(rulesFont, true);

        TITLE_X = appWidth / 2 - mainTitle_slickFont.getWidth(mainTitleString) / 2;
        gc.setTargetFrameRate(60);
        gc.setMaximumLogicUpdateInterval(10);
        colours[0] = Color.white;
        colours[1] = Color.green;
        colours[2] = Color.black;
        colours[3] = Color.magenta;
        colours[4] = Color.yellow;
        colours[5] = Color.lightGray;

        //get user card width and height
        try {
            playerHandInfo_backgroundImage = new Image("TexasHoldEmData/scrolling_info_background3.png");
            playerHandInfo_backgroundOutline = new Image("TexasHoldEmData/green_block_2.png");
            money_image = new Image("TexasHoldEmData/money_pic_2.png");
            getPlayerHandInfoImage = new Image("TexasHoldEmData/white_block_2.png");
            changeInCoinsImage = new Image("TexasHoldEmData/white_block_2.png");
            upArrow = new Image("TexasHoldEmData/up_arrow.png"); // arrows@
            downArrow = new Image("TexasHoldEmData/down_arrow.png");
            upArrow = upArrow.getScaledCopy(scrollingArrowsHeight, scrollingArrowsHeight);
            downArrow = downArrow.getScaledCopy(scrollingArrowsHeight, scrollingArrowsHeight);
            upButton = new MouseOverArea(gc, upArrow, 0, 0, upArrow.getWidth(), upArrow.getWidth());
            downButton = new MouseOverArea(gc, downArrow, 0, 0, upArrow.getWidth(), upArrow.getWidth());
            nextRoundImage = new Image("TexasHoldEmData/user_play_options_background.png");
            showIfUserMoveImage = new Image("TexasHoldEmData/orange_block_1.png");
            showRulesImage = new Image("TexasHoldEmData/green_block_1.png");
            showRulesImage = showRulesImage.getScaledCopy(startTheGameImage.getWidth(), startTheGameImage.getHeight());
            exitGameImage = new Image("TexasHoldEmData/red_block_1.png");
            userCannotRaiseImage = new Image("TexasHoldEmData/red_block_1.png");
            userPlayOptionsBackground = new Image("TexasHoldEmData/user_play_options_background.png");
            whiteBlock1 = new Image("TexasHoldEmData/white_block_1.png");
            whiteBlock2 = new Image("TexasHoldEmData/white_block_2.png");
            redBlock = new Image("TexasHoldEmData/red_block_1.png");
            blueBlock = new Image("TexasHoldEmData/blue_block_1.png");
            greenBlock = new Image("TexasHoldEmData/green_block_1.png");
            orangeBlock = new Image("TexasHoldEmData/orange_block_1.png");
            yellowBlock = new Image("TexasHoldEmData/orange_block_1.png");
            plusSign = new Image("TexasHoldEmData/plus_sign_1.png");
            minusSign = new Image("TexasHoldEmData/minus_sign_1.png");
            Image temp = new Image("TexasHoldEmData/9_of_clubs.png");
            temp = temp.getScaledCopy(USER_CARD_SCALE);
            user_card_height = temp.getHeight();
            user_card_width = temp.getWidth();
            temp = null;
            userPlayOptionsBackground_width = appWidth - (user_card_width * 2 + 4 * GAP_FROM_EDGE);
            userPlayOptionsBackground_height = (int) (appHeight * 0.18);
            userPlayOptionsBackground = userPlayOptionsBackground.getScaledCopy(userPlayOptionsBackground_width, userPlayOptionsBackground_height);

            blockWidth = (userPlayOptionsBackground_width - 5 * Player.userDisplayerGap) / 4;
            blockHeight = userPlayOptionsBackground_height - 2 * Player.userDisplayerGap;
            blockHeight2 = (userPlayOptionsBackground_height - 3 * Player.userDisplayerGap) / 2;
            plusSignWidth = blockHeight2 - Player.userDisplayerGap / 2;

            //next round image
            getPlayerHandInfoImage = getPlayerHandInfoImage.getScaledCopy(blockHeight2 * 3 / 2, blockHeight2 * 3 / 2);
            exitGameImage = exitGameImage.getScaledCopy(blockHeight2 * 3 / 2, blockHeight2 * 3 / 2);
            changeInCoinsImage = changeInCoinsImage.getScaledCopy(blockHeight2 * 3 / 2, blockHeight2 * 3 / 2);
            playerHandInfoBackGroundWidth = userPlayOptionsBackground_width - getPlayerHandInfoImage.getWidth() - 3 * GAP_FROM_EDGE;
            playerHandInfo_backgroundImage = playerHandInfo_backgroundImage.getScaledCopy(playerHandInfoBackGroundWidth, appHeight - userPlayOptionsBackground_height - 5 * GAP_FROM_EDGE);
            playerHandInfo_backgroundOutline = playerHandInfo_backgroundOutline.getScaledCopy(userPlayOptionsBackground_width - getPlayerHandInfoImage.getWidth() - 1 * GAP_FROM_EDGE, appHeight - userPlayOptionsBackground_height - 3 * GAP_FROM_EDGE);
            nextRoundImage = nextRoundImage.getScaledCopy(BOARD_WIDTH / nextRoundImage_widthRatio, BOARD_TOP_Y - 2 * GAP_FROM_EDGE);
            showIfUserMoveImage = showIfUserMoveImage.getScaledCopy(nextRoundImage.getWidth(), nextRoundImage.getHeight());
            userCannotRaiseImage = userCannotRaiseImage.getScaledCopy(nextRoundImage.getWidth(), nextRoundImage.getHeight());
            whiteBlock1 = whiteBlock1.getScaledCopy(blockWidth, blockHeight);
            redBlock = redBlock.getScaledCopy(blockWidth / 2 - Player.userDisplayerGap / 2, blockHeight2);
            yellowBlock = yellowBlock.getScaledCopy(blockWidth / 2 - Player.userDisplayerGap / 2, blockHeight2);
            orangeBlock = orangeBlock.getScaledCopy(blockWidth, blockHeight2);
            blueBlock = blueBlock.getScaledCopy(redBlock.getWidth(), blockHeight2);
            greenBlock = greenBlock.getScaledCopy(blockWidth / 2 - Player.userDisplayerGap / 2, blockHeight2); //getScaledCopy(blockWidth, blockHeight2);
            whiteBlock2 = whiteBlock2.getScaledCopy(blockWidth, blockHeight2);
            plusSign = plusSign.getScaledCopy(plusSignWidth, plusSignWidth);
            minusSign = minusSign.getScaledCopy(plusSignWidth, (int) (plusSignWidth / 3.9));
            nextRoundButton = new MouseOverArea(gc, nextRoundImage, 0, 0, nextRoundImage.getWidth(), nextRoundImage.getHeight());

            //buttons
            displayPlayerHandInfoImage = new MouseOverArea(gc, getPlayerHandInfoImage, 0, 0, getPlayerHandInfoImage.getWidth(), getPlayerHandInfoImage.getHeight());
            showRulesButton = new MouseOverArea(gc, showRulesImage, 0, 0, showRulesImage.getWidth(), showRulesImage.getHeight());
            changeInCoinsButton = new MouseOverArea(gc, getPlayerHandInfoImage, 0, 0, getPlayerHandInfoImage.getWidth(), getPlayerHandInfoImage.getHeight());
            exitGameButton = new MouseOverArea(gc, exitGameImage, 0, 0, exitGameImage.getWidth(), exitGameImage.getHeight());
            checkButton = new MouseOverArea(gc, redBlock, 0, 0, redBlock.getWidth(), redBlock.getHeight());//x y w h
            allInButton = new MouseOverArea(gc, yellowBlock, 0, 0, yellowBlock.getWidth(), yellowBlock.getHeight());
            foldButton = new MouseOverArea(gc, blueBlock, 0, 0, blueBlock.getWidth(), blueBlock.getHeight());
            callButton = new MouseOverArea(gc, greenBlock, 0, 0, greenBlock.getWidth(), greenBlock.getHeight());
            betButton = new MouseOverArea(gc, orangeBlock, 0, 0, orangeBlock.getWidth(), orangeBlock.getHeight());
            plusButton = new MouseOverArea(gc, plusSign, 0, 0, plusSignWidth, plusSignWidth);
            minusButton = new MouseOverArea(gc, minusSign, 0, 0, plusSignWidth, minusSign.getHeight());
            scrollingInfoDisplayHeight = appHeight - user_card_height - GAP_FROM_EDGE * 3;

            try {

                WHITE_CHIP_IMAGE = new Image("TexasHoldEmData/white_chip.png");
                GREEN_CHIP_IMAGE = new Image("TexasHoldEmData/green_chip.png");
                BLACK_CHIP_IMAGE = new Image("TexasHoldEmData/black_chip.png");
                PURPLE_CHIP_IMAGE = new Image("TexasHoldEmData/purple_chip.png");
                YELLOW_CHIP_IMAGE = new Image("TexasHoldEmData/yellow_chip.png");
                RED_CHIP_IMAGE = new Image("TexasHoldEmData/red_chip.png");
                allChipImages[0] = WHITE_CHIP_IMAGE;
                allChipImages[1] = GREEN_CHIP_IMAGE;
                allChipImages[2] = BLACK_CHIP_IMAGE;
                allChipImages[3] = PURPLE_CHIP_IMAGE;
                allChipImages[4] = YELLOW_CHIP_IMAGE;
                allChipImages[5] = RED_CHIP_IMAGE;
                System.out.println("all chip images loaded...");
            } catch (Exception e) {
                System.out.println("Could not load one of the chip images..");
            }

            chipImagesDisplay = allChipImages.clone();
            //chip display button images
//            for (int i = 0; i < 6; i++) {
//                allChipImages[i] = allChipImages[i].getScaledCopy(chipDisplayWidth, chipDisplayWidth); //allChipImages[i].getScaledCopy(1 / makeItThisMuchSmallerRatio );
//            }
            for (int i = 0; i < 6; i++) {
                chipImagesDisplay[i] = allChipImages[i].getScaledCopy(chipDisplayWidth * 2, chipDisplayWidth * 2); //allChipImages[i].getScaledCopy(1 / makeItThisMuchSmallerRatio );
                //gc, image, x, y, width, height
            }
            //chip display buttons

            for (int i = 0; i < 6; i++) {
                displayChipButtons[i] = new MouseOverArea(gc, chipImagesDisplay[i], 0, 0, chipImagesDisplay[i].getWidth(), chipImagesDisplay[i].getHeight());//gc, image, x, y, width, height

            }
        } catch (Exception e) {

        }

        try {
            background = new Image("TexasHoldEmData/background1.jpg");
            background.getScaledCopy(appWidth, appHeight);
        } catch (Exception e) {
            System.out.println("Coudln't load background image");
        }

        //add lines to game rules
        gameRules.add("- In Texas Hold 'Em, players have the ability to make three moves: Check (or call), Bet");
        gameRules.add("  (or raise), and Fold.");
        gameRules.add("- To call is simply to match the previous bet made. To fold is to throw away your hand");
        gameRules.add("  and wait until the next deal.");
        gameRules.add("- If there has been no bet made, you have the option to bet (raise). Once you have bet");
        gameRules.add("  during a turn, you cannot raise again until the next turn.");
        gameRules.add("- The Small Blind (SB) and Big Blind (BB), always located to the left of the dealer, must");
        gameRules.add("  ante the pot at the beginning of each round, regardless of their hand or money.");
        gameRules.add("- The minimum starting raise for each round is equal to the value of the BB. Over time,");
        gameRules.add("  the SB, BB, and thus minimum raise will increase. Players can change-in coins to larger");
        gameRules.add("  valued coins as needed throughout the game.");
        gameRules.add("- The best hand wins each turn, though the pot may be split if there is a tie.");
        gameRules.add("- Click \"See Hand Info\" to see your chances of winning");
        gameRules.add("  and extra hand information.");

    }

    public static boolean checkIfGameOver() {
        //what if user WON the game??
        if (numberPlayersLeftInGame == 1 && allPlayers[0].playerStillInGame == true) {
            if (printedUserWonOrLost == false) {
                scrollingInfo.add(new New_TextOrCardDisplayObject("Congrats! You won!"));
                printedUserWonOrLost = true;
            }
            entireGameOver = true;
            userWon = true;
        } else if (roundCounter > -1 && thisRound.roundOver == true && allPlayers[0].money <= 0) {
            if (printedUserWonOrLost == false) {
                scrollingInfo.add(new New_TextOrCardDisplayObject("Game over! You lost!"));
                printedUserWonOrLost = true;
            }
            entireGameOver = true;
            userWon = false;
            return true;
        }
        return false;
    }

    public int getNextPlayerOnTableThatHasNotFolded_andIsStillInGame(int z) {//passed it 1..4 players
        boolean foundNextPlayerWhoHasNotFolded = false;
        while (foundNextPlayerWhoHasNotFolded == false) {
            z++;//1 becomes 2 //4 becomes 5
            //z = z % numPlayers; //2 % 4 == 2 // 5 % 4 == 1//THIS WAS THE ERROR
            z = Round.getNextPlayerOnTable(z, -1, -1);//z now becomes 4 (player 0) // 1 becomes 4 (player 0) INFINITE LOOP
            if (allPlayers[playersTableOrder[z]].isFolded() == false && allPlayers[playersTableOrder[z]].playerStillInGame == true) {//well, if USER has folded...then go back to top
                foundNextPlayerWhoHasNotFolded = true;
                return z;
            }
        }
        System.out.println("had to return 99..");
        return 99;
    }

    public boolean checkIfSomeoneHasJustWonAutomatically() { //ERROR IN THIS FUNCTION
        for (int i = 0; i < numPlayers; i++) {
            int x = playerNumberToPlayerIndexOnTable[i];
            if (allPlayers[i].playerStillInGame == true && allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(x) == true) {
                thisPlayerWins_allOthersHaveFolded(x);
                return true;
            }
        }
        return false;
    }

    public void doRound_PartA(Round thisRound) {
        gotFirstRoundUpToUserMove = true;
        System.out.println("ROUND PART A");
        //==============================this needs to be in roundTurn A, B, C for any of it to work...testing=======================
        if (checkIfSomeoneHasJustWonAutomatically() == true) {
            return; //exit?? leave
        }
        //==============================this needs to be in roundTurn A, B, C for any of it to work...testing=======================

        int startIndex;
        if (round_turnCounter == 0) {
            startIndex = getNextPlayerOnTableThatHasNotFolded_andIsStillInGame(thisRound.indexOfBigBlind);
        } else {
            startIndex = (thisRound.indexOfSmallBlind);
        }
        int z = startIndex;
        startIndex = getNextPlayerOnTableThatHasNotFolded_andIsStillInGame(z - 1);
        scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[playersTableOrder[startIndex]].name + " is first to bet"));
        for (int p = startIndex; p != 4; p++) {
            p = Round.getNextPlayerOnTable(p, -1, -1);
            if (p == 4) {
                break;
            }
            if (allPlayers[playersTableOrder[p]].playerStillInGame == true) {
                if (allPlayers[playersTableOrder[p]].isFolded() == true) {
                    continue;//else, continue
                } else if (allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(p) == true) {
                    thisPlayerWins_allOthersHaveFolded(p);
                } else {
                    allPlayers[playersTableOrder[p]].playerToString();
                    int c = allPlayers[playersTableOrder[p]].getMove(thisRound.amountToCall);
                    if (c == allPlayers[playersTableOrder[p]].money) {
                        allPlayers[playersTableOrder[p]].player_goAllIn();
                    } else {
                        int x = thisRound.pot;
                        thisRound.increasePot(c);
                        System.out.print("so " + x + " + " + c + " == " + thisRound.pot);
                        if (thisRound.determineIfLargestBet(allPlayers[playersTableOrder[p]].totalBetThisTurn_perShowOfCards) == true) {
                            thisRound.indexOfLastRaiseOnTable = playersTableOrder[p];
                        }
                    }
                }
            }
        }
        getUserMove = true;
    }

    public void doRound_PartB(Round thisRound) {
        System.out.println("ROUND B");
        int p = Round.getNextPlayerOnTable(5, -1, -1);
        //==============================this needs to be in roundTurn A, B, C for any of it to work...testing=======================
        if (checkIfSomeoneHasJustWonAutomatically() == true) {
            return; //exit?? leave
        }
        //==============================this needs to be in roundTurn A, B, C for any of it to work...testing=======================

        getUserMove = false;
        while (thisRound.continueGettingPlayerTurns == true && p != 4) {
            if (allPlayers[playersTableOrder[p]].isFolded() == true) {
                p++;
                p = Round.getNextPlayerOnTable(p, -1, -1);
                if (p == 4) {
                    thisRound.continueGettingPlayerTurns = false;
                }
                continue;
            }
            if (allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(p) == true) {
                thisPlayerWins_allOthersHaveFolded(p);
                p = 4;//exit
            } else {
                if (allPlayers[playersTableOrder[p]].playerStillInGame == true) { //only get their turn if they are still in the game
                    int c = allPlayers[playersTableOrder[p]].getMove(thisRound.amountToCall);
                    if (c == allPlayers[playersTableOrder[p]].money) {//new code for all in
                        allPlayers[playersTableOrder[p]].player_goAllIn();
                    } else {
                        int x = thisRound.pot;
                        thisRound.increasePot(c);

                        if (thisRound.determineIfLargestBet(allPlayers[playersTableOrder[p]].totalBetThisTurn_perShowOfCards) == true) {
                            thisRound.indexOfLastRaiseOnTable = playersTableOrder[p];
                        }
                    }
                }

                p++;
                p = Round.getNextPlayerOnTable(p, -1, -1);
                if (p == 4) {
                    if (allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(4) == true) {
                        thisPlayerWins_allOthersHaveFolded(4);
                    } else {
                        thisRound.continueGettingPlayerTurns = false;
                        getUserMove = true; // get user's turn again
                        gotUserMove = false;
                        doOnce = true;
                        if (allPlayers[0].isFolded() == false && (round_turnCounter != 3 || printedOutLast_userItIsYourTurn < 2)) {
                            if (allPlayers[0].totalBetThisTurn_perShowOfCards != thisRound.amountToCall) {
                                scrollingInfo.add(new New_TextOrCardDisplayObject("User, your turn."));
                                scrollingInfo.add(new New_TextOrCardDisplayObject("Amount to call is $" + thisRound.amountToCall));
                            } else {
                                gotUserMove = true;
                                getUserMove = false;
                            }
                            printedOutLast_userItIsYourTurn++;
                        } else if (allPlayers[0].isFolded() == true) {
                            gotUserMove = true;
                            getUserMove = false;
                        }
                    }
                }
            }//=============================END OF ELSE BLOCK -- playerAtP is NOT the last person on table who has not folded==============
        }
    }

    public void thisPlayerWins_allOthersHaveFolded(int indexOnTable) {//what if someone just went all in?
        if (allOtherPlayersHaveFolded_ThisPersonWins_CompletedThisAlready == false) {
            int indexOfPlayer = playersTableOrder[indexOnTable];//index of player in allPLAYERS
            System.out.println(allPlayers[indexOfPlayer].name + "WINS");
            scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[indexOfPlayer].name + " wins!"));
            scrollingInfo.add(new New_TextOrCardDisplayObject("Everyone else has folded."));
            scrollingInfo.add(new New_TextOrCardDisplayObject("They receive $" + thisRound.pot));
            thisRound.roundChipsPotToInt();
            allPlayers[indexOfPlayer].determineMoney();
            for (int i = 0; i < 6; i++) {//give that player the money by increasing their chips
                allPlayers[indexOfPlayer].chips[i][1] += thisRound.potCopy_ofChips[i];

            }
            allPlayers[indexOfPlayer].determineMoney();
            if (indexOfPlayer == 0) {
                thisRound.nameOfWinner = "User";
            } else {
                thisRound.nameOfWinner = allPlayers[indexOfPlayer].name;
            }
            thisRound.roundOver = true;
            checkIfGameOver();
        }
        allOtherPlayersHaveFolded_ThisPersonWins_CompletedThisAlready = true;
    }

    public static int countNumberPlayersLeftInGame() {
        int x = 0;
        for (int i = 0; i < numPlayers; i++) {
            if (allPlayers[i].playerStillInGame == true) {
                x++;
            }
        }
        numberPlayersLeftInGame = x;
        return numberPlayersLeftInGame;
    }

    public boolean allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(int indexOnTable) {
        int playerIndex = playersTableOrder[indexOnTable];
        if (allPlayers[playerIndex].isFolded() == true) {
            return false;//this player HAS folded
        }        //else, this player has not folded
        int counter = 0;//number of people still in game who have folded
        for (int i = 0; i < numPlayers; i++) {
            if (i == playerIndex) {
                continue;
            }
            if (allPlayers[i].playerStillInGame == true && allPlayers[i].isFolded() == true) {
                counter++;
            }
        }
//        int numberPlayersStillInGame = 0;
//        for (int i = 0; i < numPlayers; i++){
//            if (allPlayers[i].playerStillInGame == true){
//                numberPlayersStillInGame++;
//            }
//        }
        if (counter == countNumberPlayersLeftInGame() - 1) {
            return true;
        }
        return false;
    }

    public void doRound_PartC(Round thisRound) {
        System.out.println("TESTING round c aaaaa");
        //==============================this needs to be in roundTurn A, B, C for any of it to work...testing=======================
        if (checkIfSomeoneHasJustWonAutomatically() == true) {
            return; //exit?? leave
        }
        //==============================this needs to be in roundTurn A, B, C for any of it to work...testing=======================

        int p = Round.getNextPlayerOnTable(5, -1, -1);
        while (p != 4) {
            if (allPlayers[playersTableOrder[p]].isFolded() == true) {//playersTableOrder = {3, 6, 4, 8, 0, 7, 2, 5, 9, 1};
                p++;
                p = p % numPlayers;
                p = Round.getNextPlayerOnTable(p, -1, -1);
                if (p == 4) {
                    thisRound.continueGettingPlayerTurns = false;//new code Dec 5
                }
                continue;
            } else if (allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(p) == true) {
                thisPlayerWins_allOthersHaveFolded(p);
                p = 4;//should exit the while loop...
            } else {//=============================ELSE BLOCK -- playerAtP is NOT the last person on table who has not folded==============
                if (allPlayers[playersTableOrder[p]].compCannotRaise_and_compCalledOrFolded(thisRound.amountToCall) == false) {
                    //get their turn //if they have NOT called or folded and are STILL IN THE GAME
                    if (allPlayers[playersTableOrder[p]].playerStillInGame == true) { //only get their turn if they are still in the game
                        int c = allPlayers[playersTableOrder[p]].getMove(thisRound.amountToCall);
                        if (c == allPlayers[playersTableOrder[p]].money) {//new code for all in
                            allPlayers[playersTableOrder[p]].player_goAllIn();
                        } else {
                            thisRound.increasePot(c);
                        }

                    }
                }
                p++;
                p = p % numPlayers;
                p = thisRound.getNextPlayerOnTable(p, -1, -1);//new code???????????????????????????????
            }//=============================END OF ELSE BLOCK -- playerAtP is NOT the last person on table who has not folded==============
        }
        getRestOfPlayers_untilEveryoneHasCalledOrFolded = false;
    }

    public void doRound_smallAndBigBlinds(Round thisRound) {
        int temp[] = allPlayers[playersTableOrder[thisRound.indexOfSmallBlind]].moneyToChips(thisRound.smallBlindValue); // SMALL BLIND BET
        Player.addArrayOfChipsToPotOfChipsCopy(temp);
        int a = allPlayers[playersTableOrder[thisRound.indexOfSmallBlind]].bet(temp);
        thisRound.increasePot(a);
        temp = allPlayers[playersTableOrder[thisRound.indexOfBigBlind]].moneyToChips(thisRound.bigBlindValue); // BIG BLIND BET
        Player.addArrayOfChipsToPotOfChipsCopy(temp);
        int b = allPlayers[playersTableOrder[thisRound.indexOfBigBlind]].bet(temp);
        thisRound.increasePot(b);
        thisRound.determineIfLargestBet(b);
    }

    public void checkAndWaitToResetRound_withTimeDelay() {
        c1 = Calendar.getInstance();
        if (getBeforeTime_forRound == true) {
            beforeTime_forRound = c1.getTimeInMillis() / 1000;
        }
        afterTime_forRound = c1.getTimeInMillis() / 1000;
        if (afterTime_forRound >= beforeTime_forRound + WAIT_TIME_BETWEEN_ROUNDS) {

        }
    }

    public void checkAndWaitToResetTurn(Round thisRound) {
        c1 = Calendar.getInstance();
        if (getBeforeTime == true) {
            switch (round_turnCounter) {
                case 1:
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Dealing Flop Cards"));
                    break;
                case 2:
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Dealing Turn Card"));
                    break;
                case 3:
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Dealing River Card"));
                    break;
            }
            beforeTime = c1.getTimeInMillis() / 100;
            getBeforeTime = false;
        }

        afterTime = c1.getTimeInMillis() / 100;

        if (afterTime >= beforeTime + WAIT_TIME_BETWEEN_TURNS_tenthsOfSeconds) {
            System.out.println("resetting turn...");
            thisRound.resetTurn();
            thisRound.continueGettingPlayerTurns = true;
            allowedToMoveOnToNextTurnCounterIndex = true;
            //doRound_smallAndBigBlinds(thisRound);
        }
    }

    @Override
    public void update(GameContainer gc, int i) throws SlickException {
        //mouse down timer
        if (mouseDown == true && startedMouseDownTimer == false) {
            startedMouseDownTimer = true;
            c1 = Calendar.getInstance();
            mouseDownBeforeTime = c1.getTimeInMillis() / 10;
        }
        if (mouseDown == true) {
            c1 = Calendar.getInstance();
            timeMouseHasBeenPressedDown = c1.getTimeInMillis() / 10 - mouseDownBeforeTime;
        } else if (mouseDown == false) {
            startedMouseDownTimer = false;
        }

        if (exitGameButton.isMouseOver() == true && mouseJustReleased == true) {
            entireGameOver = true;
            userWon = false;
            app.exit();
        }

        //put game logic here
        if (displayStartMenu == true) {

            for (int z = 0; z < 8; z++) {
                if (numberOfPlayerButtons[z].isMouseOver() == true && mouseJustReleased == true) {
                    indexOfSelectedHighlight = z;
                    numPlayers = indexOfSelectedHighlight + 3;
                }
            }
            for (int z = 0; z < 3; z++) {
                if (gameTypeButtons[z].isMouseOver() == true && mouseJustReleased == true) {
                    indexOfSelectedHighlightGameType = z;
                }
            }
            if (startTheGameButton.isMouseOver() == true && mouseJustReleased == true && numPlayers >= 2 && indexOfSelectedHighlightGameType >= 0) {
                try {
                    typeOfGameChoice = indexOfSelectedHighlightGameType;
                    if (typeOfGameChoice >= 0 && typeOfGameChoice <= 2) {
                        moveOnToNextTaskWithInput = false;
                        switch (typeOfGameChoice) {
                            case 0:
                                totalChips = 500;
                                break;
                            case 1:
                                totalChips = 1000;
                                break;
                            case 2:
                                totalChips = 1500;
                                break;
                        }
                        screen2_OptionsScreen_2_gameChips = false;
                        createStartingChipSet();
                        initializeGame();
                    }
                } catch (Exception e) {
                    System.out.println("Try again yy");
                }
            }

            //show rules button
            if (showRulesButton.isMouseOver() == true && mouseJustReleased == true) {
                System.out.println("SHOW RULES");
                showRules = !showRules;
            }

        }

        if (checkIfGameOver() == false && displayBoard == true) {
            //make new round
            if (makeAndStartNewRound == true) {
                roundCounter++;
                makeAndStartNewRound = false;
                //if (allRounds.size() < roundCounter) {//roundCounter is 0, size is 0, so no rounds is made yet
                allRounds.add(new Round());
                thisRound = null;
                thisRound = allRounds.get(roundCounter);
                System.out.println("Round counter is now " + roundCounter);
                scrollingInfo.add(new New_TextOrCardDisplayObject("Starting Round #" + (roundCounter + 1)));
                thisRound.resetRound();

            }

            if (changeInCoinsButton.isMouseOver() == true && mouseJustReleased == true) {
                for (int w = 0; w < 10; w++) {
                    allPlayers[0].checkCoinAmounts();
                }
            }

            if (displayPlayerHandInfoImage.isMouseOver() == true && mouseJustReleased == true) {
                displayHandInfo = !displayHandInfo;
                if (displayHandInfo == false) {
                    Board.handInfoString1 = "SEE";
                    Board.handInfoString2 = "HAND";
                    Board.handInfoString3 = "INFO";
                } else {
                    Board.handInfoString1 = "";
                    Board.handInfoString2 = "CLOSE";
                    Board.handInfoString3 = "";
                }
            }

           
            if (nextRoundButton.isMouseOver() == true && mouseJustReleased == true) {
                getNextRoundButtonClicked = true;
            }

            if (upButton.isMouseOver() == true && mouseJustReleased == true) {//SINGLE CLICK PRESSING
                if (startIndexOfDisplayingScrollingInfo + scrollOffset > 0) {
                    scrollOffset--;
                }
            } else if (downButton.isMouseOver() == true && mouseJustReleased == true) {
                if (scrollingInfo.size() >= numberLinesToDisplay && startIndexOfDisplayingScrollingInfo + scrollOffset < scrollingInfo.size() - 1) {
                    scrollOffset++;
                }

            }
            if (upButton.isMouseOver() == true && mouseDown == true && timeMouseHasBeenPressedDown > 21) {//SCROLL QUICKLY
                if (startIndexOfDisplayingScrollingInfo + scrollOffset > 0) {
                    speedUpIndex++;
                    if (speedUpIndex == indexWhenToActuallyIncriment) {
                        speedUpIndex = 0;
                        scrollOffset--;
                    }
                }
            } else if (downButton.isMouseOver() == true && mouseDown == true && timeMouseHasBeenPressedDown > 21) {
                if (scrollingInfo.size() >= numberLinesToDisplay && startIndexOfDisplayingScrollingInfo + scrollOffset < scrollingInfo.size() - 1) {
                    speedDownIndex++;
                    if (speedDownIndex == indexWhenToActuallyIncriment) {
                        speedDownIndex = 0;
                        scrollOffset++;
                    }
                }

            }

            //==================================================================GET THE USER'S TURN================================================================================
            if (allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(4) == true) {
                System.out.println("user cannot make a move because user wins!");
                thisPlayerWins_allOthersHaveFolded(4);
            } else if (thisRound.isRoundOver() == false && allPlayers[0].isFolded() == false && callButton.isMouseOver() == true && mouseJustReleased == true) {//user move: CALL
                allPlayers[0].addArrayOfChipsToPotOfChipsCopy(allPlayers[0].moneyToChips(thisRound.amountToCall - allPlayers[0].totalBetThisTurn_perShowOfCards));
                int z = allPlayers[0].bet(allPlayers[0].moneyToChips(thisRound.amountToCall - allPlayers[0].totalBetThisTurn_perShowOfCards));
                thisRound.setPot(z + thisRound.getPot());
                allPlayers[0].move = "CALL";
                scrollingInfo.add(new New_TextOrCardDisplayObject("User called; bet $" + z));
                thisRound.userEndTurn();
            } else if (thisRound.isRoundOver() == false && allPlayers[0].isFolded() == false && checkButton.isMouseOver() == true && mouseJustReleased == true && thisRound.amountToCall == 0) {//user move: CHECK
                scrollingInfo.add(new New_TextOrCardDisplayObject("User checked"));
                allPlayers[0].move = "CHECK";
                thisRound.userEndTurn();
            } else if (thisRound.isRoundOver() == false && allPlayers[0].isFolded() == false && betButton.isMouseOver() == true && mouseJustReleased == true && allPlayers[0].allowedToRaise == true) {//user move: BET
                if (allPlayers[0].thisBetAmount >= thisRound.amountToCall + thisRound.minimumRaise || allPlayers[0].thisBetAmount == thisRound.amountToCall) {//raises by minimum raise, or calls
                    thisRound.setPot(allPlayers[0].thisBetAmount + thisRound.getPot());
                    allPlayers[0].allowedToRaise = false;
                    allPlayers[0].totalBetThisTurn_perShowOfCards += allPlayers[0].thisBetAmount;//what if user is big blind or small blind??
                    allPlayers[0].move = "BET-" + Integer.toString(allPlayers[0].thisBetAmount) + "";
                    thisRound.determineIfLargestBet(allPlayers[0].totalBetThisTurn_perShowOfCards);
                    scrollingInfo.add(new New_TextOrCardDisplayObject("User raised; bet $" + allPlayers[0].thisBetAmount));
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Call Amount: $" + thisRound.amountToCall));
                    thisRound.userEndTurn();

                }

            } else if (thisRound.isRoundOver() == false && allInButton.isMouseOver() == true && mouseJustReleased == true && allPlayers[0].isFolded() == false) {//GOING ALL IN
                int z = allPlayers[0].player_goAllIn();
                thisRound.determineIfLargestBet(allPlayers[0].totalBetThisTurn_perShowOfCards);
                thisRound.setPot(z + thisRound.getPot());
                thisRound.userEndTurn();
            } else if (thisRound.isRoundOver() == false && foldButton.isMouseOver() == true && mouseJustReleased == true) {// FOLD
                allPlayers[0].move = "FOLD";
                allPlayers[0].setFolded(true);
                scrollingInfo.add(new New_TextOrCardDisplayObject("User folded"));
                allPlayers[0].hand[0].setIsFaceUp(false);
                allPlayers[0].hand[1].setIsFaceUp(false);
                thisRound.userEndTurn();
                allPlayers[0].playerColour = PLAYER_FOLD_COLOR;
            }

            if (plusButton.isMouseOver() == true && mouseJustReleased == true) {//==========PLUS BET
                if (allPlayers[0].chips[board.indexOfSelectedChip][0] + allPlayers[0].thisBetAmount <= allPlayers[0].money && allPlayers[0].chips[board.indexOfSelectedChip][1] > 0) {
                    allPlayers[0].thisBetAmount += allPlayers[0].chips[board.indexOfSelectedChip][0];
                    allPlayers[0].chips[board.indexOfSelectedChip][1]--; //need to re set this if they change
                    thisRound.potCopy_ofChips[board.indexOfSelectedChip]++;//changed this _ potcopy
                }

            } else if (minusButton.isMouseOver() == true && mouseJustReleased == true && allPlayers[0].thisBetAmount >= allPlayers[0].chips[board.indexOfSelectedChip][0]) {//========MINUS RAISE
                allPlayers[0].thisBetAmount -= allPlayers[0].chips[board.indexOfSelectedChip][0];
                allPlayers[0].chips[board.indexOfSelectedChip][1]++;
                thisRound.potCopy_ofChips[board.indexOfSelectedChip]--;//changed this _ potcopy

            }

            for (int z = 0; z < 6; z++) {
                if (displayChipButtons[z].isMouseOver() == true && mouseJustReleased == true) {
                    board.indexOfSelectedChip = z;
                }
            }

            //============================================================== gameplay logic ==============================================
            //GAMEPLAY LOGIC
            if (gameplayMoveOn == true && deltDeck == false && turnCounter == 0) { //DEAL, show BIG and Small BLINDS
                System.out.println("dealing");
                deltDeck = true;
                scrollingInfo.add(new New_TextOrCardDisplayObject("Dealing..."));
                thisRound.deal();
                allPlayers[0].hand[0].setIsFaceUp(true);
                allPlayers[0].hand[1].setIsFaceUp(true);
                Board.getHandInfo_fromCalculator = true;
                scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[playersTableOrder[thisRound.indexOfDealer]].name + " is Dealer"));
                scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[playersTableOrder[thisRound.indexOfSmallBlind]].name + " is Small Blind"));
                scrollingInfo.add(new New_TextOrCardDisplayObject(allPlayers[playersTableOrder[thisRound.indexOfBigBlind]].name + " is Big Blind"));
                doRound_smallAndBigBlinds(thisRound);

            }

            if (gameplayMoveOn == true && gotFirstRoundUpToUserMove == false) {
                doRound_PartA(thisRound);
                if (allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(4) == true && someoneAutomaticallyWins == false) {
                    someoneAutomaticallyWins = true;
                    thisPlayerWins_allOthersHaveFolded(4);//index on table
                } else if (allPlayers[0].isFolded() == false) {
                    scrollingInfo.add(new New_TextOrCardDisplayObject("User, your turn."));
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Amount to call is $" + thisRound.amountToCall));
                } else {
                    System.out.println("user folded sooo setting their booleans for them.");
                    gotUserMove = true;
                    getUserMove = false;
                }
            }
            //user move

            if (doOnce == true && gotUserMove == true) {
                getRestOfPlayers_untilEveryoneHasCalledOrFolded = true;
                doOnce = false;
            }
            //ERROR: it's not going on to getRoundC
            //in this current ERROR, we JUST GOT USER MOVE. user called the 280. then everyone else went and called or folded (form get round B)
            if (gotUserMove == true && getUserMove == false) {
                System.out.println(DASH + "-------------------------getting round b");
                doRound_PartB(thisRound);
                System.out.println(DASH + "-------------------------done round b");
                for (int s = 0; s < numPlayers; s++) {
                    System.out.println(allPlayers[s].name + ": is folded?" + allPlayers[s].isFolded() + "Is all in? " + allPlayers[s].isAllIn() + ",  Bet amount this turn" + allPlayers[s].totalBetThisTurn_perShowOfCards);
                }
                //then get's user move again
                if (allPlayers[0].totalBetThisTurn_perShowOfCards != thisRound.amountToCall && allPlayers[0].isFolded() == false && allPlayers[0].isAllIn() == false) {//changed this   
                    System.out.println("Need to get user move bbb");
                    getUserMove = true;

                } else if (thisRound.roundOver == false) {

                    getRestOfPlayers_untilEveryoneHasCalledOrFolded = true;
                    doOnce = false;

                    getUserMove = true;//what change does this do?
                }
            }

            if (getRestOfPlayers_untilEveryoneHasCalledOrFolded == true) {
                System.out.println(DASH + "-------------------------getting round c");
                doRound_PartC(thisRound);
                System.out.println(DASH + "-------------------------got round c");
                if (someoneIsAllIn == true) {
                    System.out.println("someone is all in...");
                    someoneIsAllIn_goToFinalTurn();
                } else {
                    round_turnCounter++;
                    finishedTurn = true;
                    getBeforeTime = true;//testing this out------????????just check someone else card that is in front of
                }
            } else if (allPlayers[0].isBigBlind == true && allPlayers[1].move.equalsIgnoreCase(" ") && allPlayers[0].totalBetThisTurn_perShowOfCards == thisRound.amountToCall && userIsDealerSoGoToEnd_completedThisAlreaday == false && round_turnCounter != 5) {
                System.out.println("USER IS BIGB BLIND, everyone has called, go to showing cards!!!");
                userIsDealerSoGoToEnd_completedThisAlreaday = true;
                round_turnCounter++; //this is being called again and incrimenting it to 4
                System.out.println("User is big. Round counter increased to " + round_turnCounter);
                finishedTurn = true;
                getBeforeTime = true;//ERROR ERROR
                //alreadyDidThisInRound = true;//testing this out------????????
                //if user is big bling and user has called...meaning everyone else has called already, bet200 already
            }

            if (someoneIsAllIn == false && round_turnCounter == 1 && finishedTurn == true) {//SHOW FLOP CARDs (# 1, 2, 3) //someoneIsAllIn == false &&
                thisRound.setShowFlop(true);
                for (int m = 0; m < 3; m++) {
                    if (thisRound.faceUpCards[m] == null) {
                        thisRound.faceUpCards[m] = deck.getNextCard();
                        if (m == 0) {//does this ONLY ONCE
                            Board.getHandInfo_fromCalculator = true;
                        }
                    }
                }
                checkAndWaitToResetTurn(thisRound);
                //scrollingInfo.add(new New_TextOrCardDisplayObject("aaa"));
            }

            if (someoneIsAllIn == false && round_turnCounter == 2 && finishedTurn == true) { // SHOW TURN CARD (# 4) //someoneIsAllIn == false && 
                thisRound.setShowTurn(true);
                System.out.println("round counter must be 2");
                if (thisRound.faceUpCards[3] == null) {
                    Board.getHandInfo_fromCalculator = true;
                    System.out.println("added card # 4 to faceUpCards (TURN)");
                    thisRound.faceUpCards[3] = deck.getNextCard();
                    faceupCard1X = (int) (BOARD_CENTRE_X - thisRound.faceUpCards[0].cardWidth * 2.5 - FACE_UP_CARD_SPACER * 2);
                    thisRound.faceUpCards[3].setxLoc(faceupCard1X + 3 * (thisRound.faceUpCards[0].cardWidth + FACE_UP_CARD_SPACER));
                    thisRound.faceUpCards[3].setyLoc(thisRound.faceUpCardsY);
                    thisRound.faceUpCards[3].setIsFaceUp(true);
                }
                //scrollingInfo.add(new TextOrCardDisplayObject("Showing turn card");
                allowedToMoveOnToNextTurnCounterIndex = false;
                checkAndWaitToResetTurn(thisRound);
                //scrollingInfo.add(new New_TextOrCardDisplayObject("bbb"));

            }

            if (someoneIsAllIn == false && round_turnCounter == 3 && finishedTurn == true) { // SHOW RIVER CARD (# 5) //
                //scrollingInfo.add(new New_TextOrCardDisplayObject("ccc"));
                thisRound.setShowRiver(true);
                System.out.println("round counter must be 3");
                if (thisRound.faceUpCards[4] == null) {
                    Board.getHandInfo_fromCalculator = true;
                    System.out.println("added card # 5 to faceUpCards (RIVER)");//card number 5, index 4
                    thisRound.faceUpCards[4] = deck.getNextCard();
                    thisRound.faceUpCards[4].setxLoc(faceupCard1X + 4 * (thisRound.faceUpCards[0].cardWidth + FACE_UP_CARD_SPACER));
                    thisRound.faceUpCards[4].setyLoc(thisRound.faceUpCardsY);
                    thisRound.faceUpCards[4].setIsFaceUp(true);
                }
                //scrollingInfo.add(new TextOrCardDisplayObject("Showing river card");
                allowedToMoveOnToNextTurnCounterIndex = false;
                checkAndWaitToResetTurn(thisRound);
            }

            //question: don't add someoneIsAllIn == false right here??/
            if (round_turnCounter == 4 && finishedTurn == true && displayedLastFaceUpCard == true) {//determine winner and end of round stuff..
                for (int q = 0; q < numPlayers; q++) {
                    if (allOtherPlayersHaveFolded_and_ThisPlayerHasNotFolded(playerNumberToPlayerIndexOnTable[q]) == true && someoneAutomaticallyWins == false) {
                        someoneAutomaticallyWins = true;
                        thisPlayerWins_allOthersHaveFolded(playerNumberToPlayerIndexOnTable[q]);
                        scrollingInfo.add(new New_TextOrCardDisplayObject("jjjjjj"));

                    }
                }
                round_turnCounter = 5;
                if (someoneAutomaticallyWins == false) {//=============END OF NEW CODE DEC 10th

                    System.out.println("NOW SEE WHO WINS!");
                    scrollingInfo.add(new New_TextOrCardDisplayObject("No more betting!"));
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Total bets this round:"));
                    for (int k = 0; k < numPlayers; k++) {
                        if (allPlayers[k].playerStillInGame == true) {
                            if (allPlayers[k].isFolded() == false) {
                                scrollingInfo.add(new New_TextOrCardDisplayObject(DASH + " " + allPlayers[k].name + ": $" + allPlayers[k].totalBetThisRound));
                                allPlayers[k].hand[0].setIsFaceUp(true);
                                allPlayers[k].hand[1].setIsFaceUp(true);
                            } else {
                                scrollingInfo.add(new New_TextOrCardDisplayObject(DASH + " " + allPlayers[k].name + ": $" + allPlayers[k].totalBetThisRound + " (folded)"));
                            }
                        }
                    }
                    scrollingInfo.add(new New_TextOrCardDisplayObject("This round pot: $" + thisRound.pot));
                    scrollingInfo.add(new New_TextOrCardDisplayObject("Time to show your hands..."));

                    thisRound.determineWinnerOfRound();
                    thisRound.roundOver = true;
                    getBeforeTime_forRound = true;

                }
            }

            if (thisRound.roundOver == true) {

                if (getNextRoundButtonClicked == true) {    /// TO DO FIGURE THIS OUT;
                    makeAndStartNewRound = true;
                    System.out.println("Round counter is currently " + roundCounter);
                    System.out.println("end of old round, starting new round..");
                }
            }
        }
        mouseJustReleased = false;
    }

    public void someoneIsAllIn_goToFinalTurn() {
        thisRound.setShowFlop(true);
        thisRound.setShowRiver(true);
        thisRound.setShowTurn(true);
        finishedTurn = true;
        scrollingInfo.add(new New_TextOrCardDisplayObject("Going to final turn...."));
        faceupCard1X = (int) (BOARD_CENTRE_X - FACE_UP_CARD_WIDTH * 2.5 - FACE_UP_CARD_SPACER * 2);
        for (int m = 0; m < 5; m++) {
            if (thisRound.faceUpCards[m] == null) {//only does this if it is null. so even if 3 cards have already been shown and then it is supposed to go to final turn...it is okay
                thisRound.faceUpCards[m] = deck.getNextCard();
                thisRound.faceUpCards[m].setxLoc(faceupCard1X + m * (thisRound.faceUpCards[0].cardWidth + FACE_UP_CARD_SPACER));
                thisRound.faceUpCards[m].setyLoc(thisRound.faceUpCardsY);
                thisRound.faceUpCards[m].setIsFaceUp(true);
                if (m == 0) {//does this ONLY ONCE
                    Board.getHandInfo_fromCalculator = true;
                }
            }
        }
        round_turnCounter = 4;
    }

    @Override
    public void render(GameContainer gc, Graphics g) throws SlickException {
        //g.setFont(playerName_slickFont);
        //g.setBackground(Color.white);
        g.drawImage(background, 0, 0);
        //display board
        if (displayStartMenu == true) {
            displayStartMenu(gc, g);
        }
        if (displayBoard == true) {
            board.displayBoard(gc, g);

        }

        mouseXLoc = gc.getInput().getMouseX();
        mouseYLoc = gc.getInput().getMouseY();
        mouseXLocString = Integer.toString(mouseXLoc);
        mouseYLocString = Integer.toString(mouseYLoc);
        //game over, user LOST

    }

    public void displayStartMenu(GameContainer gc, Graphics g) {
        showRulesButton.setY(startTheGameButton.getY());
        showRulesButton.setX(appWidth - gapFromLeftEdge - showRulesButton.getWidth());

        //display title       
        mainTitle_slickFont.drawString(TITLE_X, TITLE_Y, mainTitleString);//x, y, string
        
        //display version and name
         rules_slickFont.drawString(appWidth / 2 - rules_slickFont.getWidth(nameVersionInfo) / 2, appHeight - TITLE_Y, nameVersionInfo);//x, y, string
        

        //press any button to continue 
        if (displayPressAnyButton == true) {
            mainOptions_slickFont.drawString(appWidth / 2 - mainOptions_slickFont.getWidth(pressAnyButtonString) / 2, appHeight / 2, pressAnyButtonString);
        }

        if (displayGetNumberOfPlayers == true) {

            if (showRules == false) {
                mainOptions_slickFont.drawString(gapFromLeftEdge, numberOfPlayerButtons[0].getY() - gap_between_text_and_shape + optionsMenuVerticalShiftUp, numberPlayersDisplayString);
                for (int i = 0; i < 8; i++) {
                    numberOfPlayerButtons[i].render(gc, g);
                    mainTitle_slickFont.drawString(numberOfPlayerButtons[i].getX() + numberOfPlayerButtons[i].getWidth() / 2 - mainTitle_slickFont.getWidth(NUMBERS_STRINGS[i]) / 2, numberOfPlayerButtons[i].getY() + numberOfPlayerButtons[0].getHeight() / 2 - mainTitle_slickFont.getHeight(NUMBERS_STRINGS[i]) / 2, NUMBERS_STRINGS[i]);

                    if (numberOfPlayerButtons[i].isMouseOver() == true) {

                        g.drawImage(mouseOverNumberImage, numberOfPlayerButtons[i].getX() - highlightCircleDifference_X / 2, numberOfPlayerButtons[i].getY() - highlightCircleDifference_X / 2);
                    }
                }
                if (indexOfSelectedHighlight >= 0) {
                    g.drawImage(mouseSelectedNumberImage, numberOfPlayerButtons[indexOfSelectedHighlight].getX() - highlightCircleDifference_X / 2, numberOfPlayerButtons[indexOfSelectedHighlight].getY() - highlightCircleDifference_X / 2);
                }

                //display mouse over button image
                //game tpye question stirng //gap_between_text_and_shape //gameTypeDispayString
                mainOptions_slickFont.drawString(gapFromLeftEdge, gameTypeButtons[0].getY() - gap_between_text_and_shape + optionsMenuVerticalShiftUp, gameTypeDispayString);
                for (int i = 0; i < 3; i++) {
                    gameTypeButtons[i].render(gc, g);//GAME_TYPES_STRING
                    mainTitle_slickFont.drawString(gameTypeButtons[i].getX() + gameTypeButtons[0].getHeight() / 2 - mainTitle_slickFont.getWidth(GAME_TYPES_STRING[i]) / 2, gameTypeButtons[0].getY() + gameTypeButtons[0].getHeight() / 2 - mainTitle_slickFont.getHeight(GAME_TYPES_STRING[0]) / 2, GAME_TYPES_STRING[i]);
                    if (gameTypeButtons[i].isMouseOver() == true) {
                        g.drawImage(mouseOverGameTypeImage, gameTypeButtons[i].getX() - highlightCircleDifference_X, gameTypeButtons[i].getY() - highlightCircleDifference_X);
                    }
                    //mouseOverGameTypeImage
                }
                if (indexOfSelectedHighlightGameType >= 0) {
                    g.drawImage(mouseSelectedGameTypeImage, gameTypeButtons[indexOfSelectedHighlightGameType].getX() - highlightCircleDifference_X, gameTypeButtons[indexOfSelectedHighlightGameType].getY() - highlightCircleDifference_X);
                }

                startTheGameButton.render(gc, g);
                //start the game button
                mainOptions_slickFont.drawString(gapFromLeftEdge + startTheGameButton.getWidth() / 2 - mainOptions_slickFont.getWidth(startGameString) / 2, startTheGameButton.getY() + startTheGameButton.getHeight() / 2 - mainOptions_slickFont.getHeight(startGameString) / 2, startGameString);
            }

            showRulesButton.render(gc, g);
            if (showRules == false) {
                mainOptions_slickFont.drawString(showRulesButton.getX() + showRulesButton.getWidth() / 2 - mainOptions_slickFont.getWidth("Show Rules") / 2, showRulesButton.getY() + showRulesButton.getHeight() / 2 - mainOptions_slickFont.getHeight("T") / 2, "Show Rules");
            } else {
                mainOptions_slickFont.drawString(showRulesButton.getX() + showRulesButton.getWidth() / 2 - mainOptions_slickFont.getWidth("Show Menu") / 2, showRulesButton.getY() + showRulesButton.getHeight() / 2 - mainOptions_slickFont.getHeight("T") / 2, "Show Menu");
                displayRules(g);
            }
            if (showRulesButton.isMouseOver() == true) {
                g.setColor(Color.white);
                g.fillRect(showRulesButton.getX(), showRulesButton.getY(), showRulesButton.getWidth(), startGameButtonBorderWidth);
                g.fillRect(showRulesButton.getX(), showRulesButton.getY() + showRulesButton.getHeight() - startGameButtonBorderWidth, showRulesButton.getWidth(), startGameButtonBorderWidth);
                g.fillRect(showRulesButton.getX(), showRulesButton.getY(), startGameButtonBorderWidth, showRulesButton.getHeight());
                g.fillRect(showRulesButton.getX() + showRulesButton.getWidth() - startGameButtonBorderWidth, showRulesButton.getY(), startGameButtonBorderWidth, showRulesButton.getHeight());

            }

            if (showRules == false && startTheGameButton.isMouseOver() == true) {
                g.setColor(Color.white);
                g.fillRect(startTheGameButton.getX(), startTheGameButton.getY(), startTheGameButton.getWidth(), startGameButtonBorderWidth);
                g.fillRect(startTheGameButton.getX(), startTheGameButton.getY() + startTheGameButton.getHeight() - startGameButtonBorderWidth, startTheGameButton.getWidth(), startGameButtonBorderWidth);
                g.fillRect(startTheGameButton.getX(), startTheGameButton.getY(), startGameButtonBorderWidth, startTheGameButton.getHeight());
                g.fillRect(startTheGameButton.getX() + startTheGameButton.getWidth() - startGameButtonBorderWidth, startTheGameButton.getY(), startGameButtonBorderWidth, startTheGameButton.getHeight());

            }

        }
        //cash image
        // g.drawImage(money_image, 500, 500);
    }

    public void displayRules(Graphics g) {
        int startY = numberOfPlayerButtons[0].getY() - gap_between_text_and_shape + optionsMenuVerticalShiftUp - rules_slickFont.getHeight();
        for (int i = 0; i < gameRules.size(); i++) {
            rules_slickFont.drawString(gapFromLeftEdge, startY + i * (int) (rules_slickFont.getHeight() * 1.23), gameRules.get(i), Color.white);
        }
    }

    public void keyPressed(int key, char c) {
        if (key == Input.KEY_ESCAPE) {
            System.exit(0);
        }
        if (key == Input.KEY_F1) {
            if (app != null) {
                try {
                    app.setDisplayMode(600, 600, false);
                    app.reinit();
                } catch (Exception e) {
                    Log.error(e);
                }
            }
        }
    }

    public void createStartingChipSet() {
        //numPlayers, totalChips
        int numWhiteChips = (int) ((double) totalChips * WHITE_STARTING_RATIO);
        int numWhiteChipsPerPlayer = numWhiteChips / numPlayers;

        int numGreenChips = (int) ((double) totalChips * GREEN_STARTING_RATIO);
        int numGreenChipsPerPlayer = numGreenChips / numPlayers;

        int numBlackChips = (int) ((double) totalChips * BLACK_STARTING_RATIO);
        int numBlackChipsPerPlayer = numBlackChips / numPlayers;

        int numPurpleChips = (int) ((double) totalChips * PURPLE_STARTING_RATIO);
        int numPurpleChipsPerPlayer = numPurpleChips / numPlayers;

        System.out.println("Before approximate total chips = " + totalChips);
        totalChips = numPlayers * (numWhiteChipsPerPlayer + numGreenChipsPerPlayer + numBlackChipsPerPlayer + numPurpleChipsPerPlayer);
        System.out.println("afterd total chips = " + totalChips);

        startingChipAmount = new int[numPlayers][6][2];
        for (int i = 0; i < numPlayers; i++) {
            startingChipAmount[i][0][0] = WHITE_CHIP;
            startingChipAmount[i][0][1] = numWhiteChipsPerPlayer;
            startingChipAmount[i][1][0] = GREEN_CHIP;
            startingChipAmount[i][1][1] = numGreenChipsPerPlayer;
            startingChipAmount[i][2][0] = BLACK_CHIP;
            startingChipAmount[i][2][1] = numBlackChipsPerPlayer;
            startingChipAmount[i][3][0] = PURPLE_CHIP;
            startingChipAmount[i][3][1] = numPurpleChipsPerPlayer;
            startingChipAmount[i][4][0] = YELLOW_CHIP;
            startingChipAmount[i][4][1] = 0;//change
            startingChipAmount[i][5][0] = RED_CHIP;
            startingChipAmount[i][5][1] = 0;//change     
        }

        approx_max_chips = (int) (numWhiteChipsPerPlayer * 1.5);
    }

    public void initializeGame() throws SlickException {
        Card tempzzz = new Card(10, 's');
        tempzzz.cardFileName = "7_of_clubs.png";
        tempzzz.cardImage = new Image("TexasHoldEmData/" + tempzzz.cardFileName);
        tempzzz.cardImage = tempzzz.cardImage.getScaledCopy(FACE_UP_CARD_SCALE);
        PokerGame.faceUpCardWidth = tempzzz.cardImage.getWidth();
        tempzzz = null;

//         
        allPlayers = new Player[numPlayers];
        //int [][] temp2 = startingChipAmount.clone();
        Player user = new Player("User", startingChipAmount[0]);
        allPlayers[0] = user;
        for (int i = 1; i < numPlayers; i++) {
            // int [][] temp3 = startingChipAmount.clone();
            Player temp = new Player("Comp " + i + "", startingChipAmount[i]);
            allPlayers[i] = temp;
        }
        for (int i = 0; i < numPlayers; i++) {
            allPlayers[i].playerToString();
        }
        //   
        if (numPlayers >= 5) {
            Board.scrollingInfoDisplayWidth = (int) (appWidth * moreThanFivePlayers_ScrollingInfoPercentage);
        } else {
            Board.scrollingInfoDisplayWidth = BOARD_LEFT_X - 2 * GAP_FROM_EDGE;
        }

        upButton.setX(Board.scrollingInfoDisplayWidth + GAP_FROM_EDGE - upButton.getWidth() - ARROW_GAP_FROM_EDGE);
        upButton.setY(GAP_FROM_EDGE + ARROW_GAP_FROM_EDGE);
        numberLinesToDisplay = (scrollingInfoDisplayHeight - 2 * GAP_FROM_EDGE - upButton.getWidth()) / (scrollingInfo_slickFont.getHeight("I") + textGapHeight);

        downButton.setX(Board.scrollingInfoDisplayWidth + GAP_FROM_EDGE - upButton.getWidth() - ARROW_GAP_FROM_EDGE);
        downButton.setY(GAP_FROM_EDGE + scrollingInfoDisplayHeight - ARROW_GAP_FROM_EDGE - downButton.getHeight());
        //
        Player.playerUserDisplayLeftX = user_card_width * 2 + 3 * GAP_FROM_EDGE;
        Player.playerUserDisplayTopY = appHeight - GAP_FROM_EDGE - userPlayOptionsBackground_height;

        //BUTTONS
        displayPlayerHandInfoImage.setX(appWidth - GAP_FROM_EDGE - displayPlayerHandInfoImage.getWidth());
        displayPlayerHandInfoImage.setY(Player.playerUserDisplayTopY - Player.userDisplayerGap - displayPlayerHandInfoImage.getHeight());
        changeInCoinsButton.setX(appWidth - GAP_FROM_EDGE - displayPlayerHandInfoImage.getWidth());
        exitGameButton.setX(appWidth - GAP_FROM_EDGE - displayPlayerHandInfoImage.getWidth());
        exitGameButton.setY(GAP_FROM_EDGE);
        nextRoundButton.setX(BOARD_CENTRE_X - nextRoundButton.getWidth() / 2);
        changeInCoinsButton.setY(Player.playerUserDisplayTopY - 2 * Player.userDisplayerGap - 2 * displayPlayerHandInfoImage.getHeight());
        nextRoundButton.setY(GAP_FROM_EDGE);
        whiteBlock1X = Player.playerUserDisplayLeftX + Player.userDisplayerGap;
        whiteBlock1Y = Player.playerUserDisplayTopY + Player.userDisplayerGap;
        checkButton.setX(Player.playerUserDisplayLeftX + betButton.getWidth() + 2 * Player.userDisplayerGap);
        checkButton.setY(Player.playerUserDisplayTopY + Player.userDisplayerGap);
        foldButton.setX(Player.playerUserDisplayLeftX + betButton.getWidth() + 2 * Player.userDisplayerGap + checkButton.getWidth() + Player.userDisplayerGap);
        foldButton.setY(Player.playerUserDisplayTopY + Player.userDisplayerGap);//shift this over to the right
        callButton.setX(foldButton.getX()); //(Player.playerUserDisplayLeftX + betButton.getWidth() + 2 * Player.userDisplayerGap);
        callButton.setY(Player.playerUserDisplayTopY + Player.userDisplayerGap * 2 + callButton.getHeight());
        allInButton.setX(checkButton.getX());
        allInButton.setY(callButton.getY());
        betButton.setX(Player.playerUserDisplayLeftX + betButton.getWidth() * 3 + 4 * Player.userDisplayerGap);
        betButton.setY(Player.playerUserDisplayTopY + Player.userDisplayerGap);
        whiteBlock2X = Player.playerUserDisplayLeftX + betButton.getWidth() * 3 + 4 * Player.userDisplayerGap;
        whiteBlock2Y = Player.playerUserDisplayTopY + Player.userDisplayerGap * 2 + foldButton.getHeight();
        plusButton.setX(Player.playerUserDisplayLeftX + (int) (betButton.getWidth() * 2.25) + 3 * Player.userDisplayerGap - plusSign.getWidth() / 2);
        plusButton.setY(Player.playerUserDisplayTopY + Player.userDisplayerGap * 2 + foldButton.getHeight());
        minusButton.setX(Player.playerUserDisplayLeftX + (int) (betButton.getWidth() * 2.75) + 3 * Player.userDisplayerGap - minusSign.getWidth() / 2);
        minusButton.setY(Player.playerUserDisplayTopY + Player.userDisplayerGap * 2 + (int) (foldButton.getHeight() * 1.5) - minusButton.getHeight() / 2);

        playerHandInfo_backgroundX = Player.playerUserDisplayLeftX + GAP_FROM_EDGE;
        playerHandInfo_backgroundY = 2 * GAP_FROM_EDGE;
        playerHandInfo_outlineX = Player.playerUserDisplayLeftX;
        playerHandInfo_outlineY = GAP_FROM_EDGE;

//END OF BUTTONS
        //end of chip display buttons 
        startindexOfDealer = (int) (Math.random() * numPlayers);

        int aaa = Round.getNextPlayerOnTable(startindexOfDealer, -1, -1); // 3
        int bbb = Round.getNextPlayerOnTable(aaa + 1, aaa, -1); // 4
        int ccc = Round.getNextPlayerOnTable(bbb + 1, aaa, bbb);
        startindexOfDealer = aaa; //index on table
        startindexOfSmallBlind = bbb;//index on table
        startindexOfBigBlind = ccc;//index on table
        deck = new Deck();
        deck.displayDeckText();
        board = new Board();

        //make up and down arrow buttons
        displayStartMenu = false;
        displayBoard = true;
        gameplayMoveOn = true;
        makeAndStartNewRound = true;
    }

    public void keyReleased(int key, char c) {
        moveOnToNextTaskWithInput = true;
        if (screen1_StartScreen == true) {
            displayPressAnyButton = false;
            displayGetNumberOfPlayers = true;
            //any key can be pressed
            screen2_OptionsScreen_1_numberPlayers = true;
            screen1_StartScreen = false;

            moveOnToNextTaskWithInput = false;
        }

        if (displayBoard == true && thisRound.roundOver == true && key == 57) {//space bar key
            getNextRoundButtonClicked = true;
        }

    }

    public void mousePressed(int i, int i1, int i2) {
        mouseDown = true;
    }

    public void mouseReleased(int button, int x, int y) {
        mouseJustReleased = true;
        mouseDown = false;
    }

}
