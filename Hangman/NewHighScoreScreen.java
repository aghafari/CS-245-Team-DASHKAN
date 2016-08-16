/*************************************************************************
 *      File Name: NewHighScoreScreen.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/15/2016
 * 
 *      Purpose: Show a screen to the user indicating that they 
 *      achieved a new high score. The user is prompted to enter
 *      their initials which are saved to the high scores screen.
 *************************************************************************/
package Hangman;

import java.awt.*;
import javax.swing.*;

/**
 *  Class to create a panel to alert the player of a 
 *  new high score
 */
public class NewHighScoreScreen extends JPanel {

    //no-arg constructor
    public NewHighScoreScreen() {
        //set the background to be red
        setBackground(Color.RED); 
    }
    
    //method: getPreferredSize
    //purpose: Tell the desired size of the panel
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    //method: paintComponent
    //purpose: Override the super class method to 
    //paint a custom panel
    public void paintComponent(Graphics g) {
        //invoke the super class's paint component method
        super.paintComponent(g);
        
        //customize the font
        g.setFont(new Font("Time New Roman", Font.PLAIN, 20));
        
        //display the following message to the user
        g.drawString("CONGRATULATIONS", 200, 30);
        g.drawString("YOU GOT A NEW HIGH SCORE", 150, 50);
        g.drawString("ENTER YOUR INITIALS", 190, 120);
    } 
}
