/*************************************************************************
 *      File Name: GameScreen2.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/13/2016
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
 *
 * @author user
 */
public class GameScreen2 extends JPanel {
    
    private int score;
    
    public GameScreen2() {
        score = 0;
    }
    
    public void setScore(int s) {
        score = s;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);                        
        
        String scoreString = "SCORE: " + Integer.toString(score);
        g.setColor(Color.BLACK);
        g.drawString(scoreString, 20, 20);
    }
}
