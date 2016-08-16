/*************************************************************************
 *      File Name: TitleScreen.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/12/2016
 * 
 *      Purpose: Create a title screen to show basic team and project
 *      information. This screen is displayed for 3 seconds and leads
 *      into the option screen.
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.*;

/**
 * This class extends JPanel and uses inherited properties to
 * display team information.
 */
public class TitleScreen extends JPanel {
    
    //no-arg constructor
    public TitleScreen() {
        //set the background to be black
        setBackground(Color.BLACK);
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
        Font font = new Font("Times New Roman", Font.PLAIN, 32);
        Graphics2D g2 = (Graphics2D) g; //cast to 2D graphics object
        //set the font and turn on rendering hints
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        //draw the project title to the panel
        g2.drawString("CS 245 Quarter Project", 140, 50);
        
        //customize the font again
        font = new Font("Times New Roman", Font.PLAIN, 20);
        g2.setFont(font);
        //display team information
        g2.drawString("By: Team Dashkan", 220, 370);
    }
}
