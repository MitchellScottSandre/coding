/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import static TexasHoldEmGame.PokerGame.scrollingInfo;

/**
 *
 * @author scott
 */
public class New_TextOrCardDisplayObject {
    
    public String text;
    public Card leftCard, rightCard;
    public boolean isText = false;
    public boolean isCard = false;
    public static final int GAP_BETWEEN_CARDS= 5;
    public New_TextOrCardDisplayObject(String s){
        this.text = s;   
        this.isText = true;
    }
    
    public New_TextOrCardDisplayObject(Card c1, Card c2){
        this.leftCard = c1;
        this.rightCard = c2;
        this.isCard = true;
       
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public boolean isIsText() {
        return isText;
    }

    public void setIsText(boolean isText) {
        this.isText = isText;
    }

    public boolean isIsCard() {
        return isCard;
    }

    public void setIsCard(boolean isCard) {
        this.isCard = isCard;
    }

    public Card getLeftCard() {
        return leftCard;
    }

    public void setLeftCard(Card leftCard) {
        this.leftCard = leftCard;
    }

    public Card getRightCard() {
        return rightCard;
    }

    public void setRightCard(Card rightCard) {
        this.rightCard = rightCard;
    }
    
    

   
    
    
}
