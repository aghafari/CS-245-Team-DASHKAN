/*************************************************************************
 *      File Name: HighScoreScreen.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/15/2016
 * 
 *      Purpose: Display the high scores to the user. The high scores
 *      will be checked after each round and if the user gets a score
 *      higher than the lowest high score, their score will be recorded.
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.*;

/**
 *  Class to create a panel to show the high scores
 */
public class HighScoreScreen extends JPanel {
    
    private String[] names; //to hold the names
    private String[] scores; //to hold the scores
    
    //no-arg constructor
    public HighScoreScreen(String[] n, String[] s) {
        //set the background to blue
        setBackground(Color.BLUE);
        
        //keep track of the top 5 scores
        names = n;
        scores = s;
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
        
        //customize the font for the display
        Font font = new Font("Broadway", Font.PLAIN, 32);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        //set rendering hints on
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        //draw the title of the panel
        g2.drawString("HIGH SCORES", 190, 50);
        
        //change the font for the individual scores
        font = new Font("Times New Roman", Font.PLAIN, 20);
        g2.setFont(font);
        //give titles to the name and score
        g2.drawString("NAME", 195, 100);
        g2.drawString("SCORE", 345, 100);
        
        //display the scores and names
        for(int i = 130, j = 0; i <= 250; i = i + 30, j++) {
            g2.drawString(names[j], 200, i);
            g2.drawString(scores[j], 370, i);
        }
    }
}
