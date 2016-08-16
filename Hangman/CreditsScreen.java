/*************************************************************************
 *      File Name: CreditsScreen.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/12/2016
 * 
 *      Purpose: Display the credits to the user. Each groups member's
 *      name and bronco ID is displayed.
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.*;

/**
 *  Class to display the group's names and IDs
 */
public class CreditsScreen extends JPanel{
    
    //no-arg constructor
    public CreditsScreen() {
        //set the background to green
        setBackground(Color.GREEN);
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
        
        //customize the font to be displayed
        Font font = new Font("Broadway", Font.PLAIN, 32);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        //turn on rendering hints
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        //draw the title of the screen
        g2.drawString("CREDITS", 220, 50);
        
        //change the font for the names
        font = new Font("Times New Roman", Font.PLAIN, 20);
        g2.setFont(font);
        //display the information on the screen
        g2.drawString("TRISTIN HRAFNSSON, 009821123", 140, 150);
        g2.drawString("DARYL OCC, 010475647", 140, 190);
        g2.drawString("ASHKAN GHAFARI, 009146475", 140, 230);
    }
}
