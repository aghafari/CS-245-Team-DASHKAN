/*************************************************************************
 *      File Name: EndScreen.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/12/2016
 * 
 *      Purpose: Show the game over screen to the user and give them
 *      their score.
 *************************************************************************/
package Hangman;

import java.awt.*;
import javax.swing.*;

/**
 *  JPanel subclass to display the user's score after a completed
 *  game or skipped game.
 */
public class EndScreen extends JPanel {
    
    private int score; //keep track of final score
    
    //no-arg constructor, can be used with skipped games
    public EndScreen() {
        //set score to 0 and background to red
        score = 0;
        this.setBackground(Color.RED);
    }
    
    //one-arg int value constructor
    public EndScreen(int s) {
        //set score to given value and set background to red
        score = s;
        this.setBackground(Color.RED);
    }
    
    //method: getPreferredSize
    //purpose: give the prefereed size of the panel to the
    //layout manager.
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    //method: paintComponent
    //purpose: override the super class method to customize
    //the panel to our specifications
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //customize the font
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        
        //display game over to the user
        g.drawString("GAME OVER", 190, 150);
        //show the final score for the game just played
        String scoreDisplay = "SCORE: " + Integer.toString(score);
        g.drawString(scoreDisplay, 190, 200);
    }
}
