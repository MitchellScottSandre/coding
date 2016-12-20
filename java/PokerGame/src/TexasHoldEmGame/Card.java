/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import static TexasHoldEmGame.PokerGame.CLUBS;
import static TexasHoldEmGame.PokerGame.COMP_CARD_SCALE;
import static TexasHoldEmGame.PokerGame.DIAMONDS;
import static TexasHoldEmGame.PokerGame.FACE_UP;
import static TexasHoldEmGame.PokerGame.FACE_UP_CARD_HEIGHT;
import static TexasHoldEmGame.PokerGame.FACE_UP_CARD_SCALE;
import static TexasHoldEmGame.PokerGame.FACE_UP_CARD_WIDTH;
import static TexasHoldEmGame.PokerGame.HEARTS;
import static TexasHoldEmGame.PokerGame.SIZE_RATIO;
import static TexasHoldEmGame.PokerGame.SPADES;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import static TexasHoldEmGame.PokerGame.DASH;

/**
 *
 * @author scott
 */
public class Card {

    //draw centered
    public Image cardImage;
    public boolean isFaceUp;
    public int value;//2, 3, 4, 5.....10, J, Q, K, A,.....R (joker)
    public char suit;//C, S, H, D
    public int xLoc, yLoc;
    public float cardScale;
    public String cardFileName;
    int cardWidth, cardHeight;
    int smallCompCardDisplayWidth;
    int smallCompCardDisplayHeight;
    public static int smallCompX = (int) (27 * SIZE_RATIO);
    public static int smallCompY = (int) (41 * SIZE_RATIO);
    public static int scrollingCardWidth = (int)(smallCompX * 3.5);
    public static int scrollingCardHeight = (int)(smallCompY * 3.5);
    
    public Card (int val, char suit){
        this.value = val;
        this.suit = suit;
    }

    public Card (int x, boolean justForValAndSuit){
        this.value = (x % 13) + 2;
        switch (x / 13) {
            case 0:
                suit = CLUBS;
    
                break;
            case 1:
                suit = DIAMONDS;
             
                break;
            case 2:
                suit = HEARTS;
            
                break;
            case 3:
                suit = SPADES;
         
                break;
        }
    }
    public Card(int x) throws SlickException {
        this.xLoc = 0;
        this.yLoc = 0;
        this.isFaceUp = false;
        this.value = ((x % 13) + 2); //from 2 to 14.. ....
        String s = "";
        if (value <= 10) {//2 to 10
            s += value;
        } else {
            switch (value) {
                case 11:
                    s += "jack";
                    break;
                case 12:
                    s += "queen";
                    break;
                case 13:
                    s += "king";
                    break;
                case 14:
                    s += "ace";
                    break;
            }
        }
        s += "_of_";
        switch (x / 13) {
            case 0:
                suit = CLUBS;
                s += "clubs";
                break;
            case 1:
                suit = DIAMONDS;
                s += "diamonds";
                break;
            case 2:
                suit = HEARTS;
                s += "hearts";
                break;
            case 3:
                suit = SPADES;
                s += "spades";
                break;
        }
        s += ".png";
        cardFileName = s;
        try {
            this.cardImage = new Image("TexasHoldEmData/" + s);
            System.out.println("image assigned --> " + s);
        } catch (Exception e) {
            System.out.println("something went wrong...");
        }
       // cardScale = FACE_UP_CARD_SCALE;
     
        cardImage = cardImage.getScaledCopy(FACE_UP_CARD_WIDTH, FACE_UP_CARD_HEIGHT);
        this.cardHeight = cardImage.getHeight();
        this.cardWidth = cardImage.getWidth();
        //System.out.println("Face up card height: " + cardImage.getHeight() +  ", and width is : " + cardImage.getWidth());
        smallCompCardDisplayWidth = (int) (cardWidth / (FACE_UP_CARD_SCALE / COMP_CARD_SCALE));
        smallCompCardDisplayHeight = (int) (cardHeight / (FACE_UP_CARD_SCALE / COMP_CARD_SCALE));
    }

    public int getxLoc() {
        return xLoc;
    }

    public void setxLoc(int xLoc) {
        this.xLoc = xLoc;
    }

    public int getyLoc() {
        return yLoc;
    }

    public void setyLoc(int yLoc) {
        this.yLoc = yLoc;
    }

    public boolean isFaceUp() {
        return isFaceUp;
    }

    public void setIsFaceUp(boolean faceUpOrNot) {
        this.isFaceUp = faceUpOrNot;
    }

    public float getCardScale() {
        return cardScale;
    }

    public void setCardScale(float cardScale) {
        this.cardScale = cardScale;
    }
    
    public void drawScrollingCard_fromBottomUpToCertainHeight(Graphics g, int xx, int yy, int height) throws SlickException{//yy is bottom of card
        this.cardImage = new Image("TexasHoldEmData/" + cardFileName);
        this.setIsFaceUp(true);
        cardImage = cardImage.getScaledCopy(scrollingCardWidth, scrollingCardHeight);
        cardImage = cardImage.getSubImage(0, Card.scrollingCardHeight - height, scrollingCardWidth, height);
        g.drawImage(cardImage, xx, yy - height);
        
    }
    
    public void drawScrollingCard(Graphics g, int xx, int yy, int height) throws SlickException{
        this.cardImage = new Image("TexasHoldEmData/" + cardFileName);
        this.setIsFaceUp(true);
        cardImage = cardImage.getScaledCopy(scrollingCardWidth, scrollingCardHeight);
        cardImage = cardImage.getSubImage(0, 0, scrollingCardWidth, height);
        g.drawImage(cardImage, xx, yy);
        
    }
    
    public void drawPlayerHandInfoCard(Graphics g, int xx, int yy) throws SlickException{
        this.cardImage = new Image("TexasHoldEmData/" + cardFileName);
        this.setIsFaceUp(true);
        cardImage = cardImage.getScaledCopy(PokerGame.FACE_UP_CARD_SCALE);
        g.drawImage(cardImage, xx, yy);
    }

    public void drawCard(Graphics g, float scale, float angle, boolean faceUpSmall) throws SlickException {
        if (faceUpSmall == true) {
            this.cardImage = new Image("TexasHoldEmData/" + cardFileName);
            cardImage = cardImage.getScaledCopy(smallCompX, smallCompY);
        } else if (isFaceUp() == FACE_UP) {
            this.cardImage = new Image("TexasHoldEmData/" + cardFileName);
            // cardScale = FACE_UP_CARD_SCALE;

            cardImage = cardImage.getScaledCopy(scale);

            //System.out.println("xxx " + smallCompX + "..... " + smallCompY);
        } else {
            this.cardImage = new Image("TexasHoldEmData/" + "card_back.png");
            cardImage = cardImage.getScaledCopy(smallCompX, smallCompY);
            //cardImage = cardImage.getScaledCopy(cardWidth, cardHeight);
        }
        cardImage.rotate(angle);
        g.drawImage(cardImage, xLoc, yLoc);

    }

    public void cardToString() {
        
        char c = ' ';
        if (value <= 10) {
            System.out.print(value);
        } else {
            switch (value) {
                case 11:
                    c = 'J';
                    break;
                case 12:
                    c = 'Q';
                    break;
                case 13:
                    c = 'K';
                    break;
                case 14:
                    c = 'A';
                    break;
            }
            System.out.print(c);
        }
        System.out.print(DASH + "" + suit + "");
    }

}
