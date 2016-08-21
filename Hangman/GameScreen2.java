/*************************************************************************
 *      File Name: GameScreen2.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Final Version
 *      Date Last Modified: 8/20/2016
 * 
 *      Purpose: Create the colored button game for the user to play. 
 *      The game consists of putting buttons on the screen of 5 different
 *      colors. The user then has to select the button that matches
 *      the color of the text of a provided color. The user has to guess
 *      for 5 rounds then their total score is displayed to them.
 *************************************************************************/
package Hangman;

import java.awt.*;
import javax.swing.*;


/**
 * Class to create the panel which the user will play the second game on
 */
public class GameScreen2 extends JPanel {
    
    private int score; //keep track of the score
    
    //no-arg constructor to set the score to 0
    public GameScreen2() {
        score = 0;
    }
    
    //single arg constructor to intialize the value of score
    public void setScore(int s) {
        score = s;
    }
    
    //method: getPreferredSize
    //purpose: tell the frame the desired size of the panel
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    //method: paintComponent
    //purpose: override the super class paintComponent method
    public void paintComponent(Graphics g) {
        //invoke the super class method
        super.paintComponent(g);                        
        
        //update the score whenever the screen is repainted
        String scoreString = "SCORE: " + Integer.toString(score);
        g.setColor(Color.BLACK);
        g.drawString(scoreString, 20, 20);
    }
}
