/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package TexasHoldEmGame;

import static TexasHoldEmGame.PokerGame.SIZE_RATIO;
import static TexasHoldEmGame.PokerGame.gameFontNameString;
import java.awt.Font;
import org.newdawn.slick.Color;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.TrueTypeFont;

/**
 *
 * @author scott
 */
public class BouncingString {

    public String word = "";
    public Graphics g;
    public double currentXLoc, currentYLoc;
    public static final int speed = (int) (7 * SIZE_RATIO);
    public double directionInRads = 0.0;
    public static final Font wordFont = new Font(gameFontNameString, Font.BOLD, (int) (42 * SIZE_RATIO));
    public static TrueTypeFont wordFont_slickFont;
    public int stringWidth, stringHeight;
    public Color c;
    public int startX, startY, topY_boundary, bottomY_boundary, leftX_boundary, rightX_boundary, direction;//direction is 0 to 360 degrees
    public static Color colourOptions[] = {Color.black, Color.yellow, Color.white, Color.blue, Color.red, Color.green, Color.magenta, Color.orange};

    public BouncingString(String w, int topY_boundary, int bottomY_boundary, int leftX_boundary, int rightX_boundary, Graphics g) {
        this.word = w;
        this.topY_boundary = topY_boundary;
        this.bottomY_boundary = bottomY_boundary;
        this.leftX_boundary = leftX_boundary;
        this.rightX_boundary = rightX_boundary;
        this.direction = direction;
        this.g = g;
        this.startX = (int) (Math.random() * (this.rightX_boundary - this.leftX_boundary)) / 2 + this.leftX_boundary;
        this.startY = (int) (Math.random() * (this.topY_boundary - this.bottomY_boundary)) / 2 + this.bottomY_boundary;
        int x = (int) (Math.random() * 4);
        switch (x) {
            case 0: //UP
                this.direction = 90 + (int) (Math.random() * 60) - 30;
                break;
            case 1: //RIGHT
                this.direction = 0 + (int) (Math.random() * 60) - 30;
                break;
            case 2: //DOWN
                this.direction = 270 + (int) (Math.random() * 60) - 30;
                break;
            case 3: //LEFT
                this.direction = 180 + (int) (Math.random() * 60) - 30;
                break;

        }

        this.directionInRads = (double) (this.direction) / 57.2958;
        this.currentXLoc = startX;
        this.currentYLoc = startY;
        this.wordFont_slickFont = new TrueTypeFont(wordFont, true);
        this.stringWidth = wordFont_slickFont.getWidth(word);
        this.stringHeight = wordFont_slickFont.getHeight("T");
        this.c = this.colourOptions[(int) (Math.random() * this.colourOptions.length)];
    }

    public void drawBouncingString() {

        boolean mustChangeDirection = false;
        int beforeDirection = direction;
        if (this.currentXLoc <= this.leftX_boundary && this.currentYLoc <= this.bottomY_boundary) {
            direction = this.direction - 180;
            mustChangeDirection = true;
        } else if (this.currentXLoc >= this.rightX_boundary && this.currentYLoc <= this.bottomY_boundary) {
            direction = this.direction - 180;
            mustChangeDirection = true;
        } else if (this.currentXLoc >= this.rightX_boundary && this.currentYLoc >= this.topY_boundary) {
            direction = this.direction - 180;
            mustChangeDirection = true;
        } else if (this.currentXLoc <= this.leftX_boundary && this.currentYLoc >= this.topY_boundary) {
            direction = this.direction - 180;
            mustChangeDirection = true;
        }else if (this.currentXLoc <= this.leftX_boundary) {//CHECK IF IT HAS TO REFLECT ON LEFT WALL ----> do I want to add just a bit of randomness?
            direction = 540 - this.direction;
            mustChangeDirection = true;
        } else if (this.currentYLoc + stringHeight >= this.topY_boundary) {
            direction = 0 - direction;
            mustChangeDirection = true;
        } else if (this.currentXLoc + stringWidth >= this.rightX_boundary) {
            direction = 540 - this.direction;
            mustChangeDirection = true;
        } else if (this.currentYLoc <= this.bottomY_boundary) {
            this.direction = 0 - this.direction;
            mustChangeDirection = true;
        }
        if (mustChangeDirection == true) {
//            if (this.direction - beforeDirection < 15) {
//                this.direction += 10;
//            }
            c = this.colourOptions[(int) (Math.random() * this.colourOptions.length)];
            if (this.direction > 360) {
                this.direction -= 360;
            } else if (this.direction < 0) {
                this.direction += 360;
            }
            //add some more randomness

            //this.direction += (Math.random() * 50) - 25;
            this.directionInRads = (double) (this.direction) / 57.2958;
        }

        //move
        this.currentXLoc += Math.cos(directionInRads) * speed;
        this.currentYLoc += Math.sin(directionInRads) * speed;
        //draw

        wordFont_slickFont.drawString((int) (currentXLoc), (int) (currentYLoc), word, c);

    }

}
