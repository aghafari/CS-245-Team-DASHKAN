/*************************************************************************
 *      File Name: ColorButton.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Final Version
 *      Date Last Modified: 8/20/2016
 * 
 *      Purpose: Create the colored buttons for the second game. The
 *      class actually uses JLabels as that makes it easier to implement
 *      the highlighting feature. Each button keeps track of its
 *      color to be able to check the user selection during the game.
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.*;

/**
 * Class to use JLabels to simulate colored buttons
 */
public class ColorButton extends JLabel {
    
    private Color color; //keeps track of the color of the button
    private final Color colorTracker; //keeps track of the original color selected
    
    //constructor with a single argument
    public ColorButton(Color c) {
        //set the color to the darker version of the given color
        color = c.darker();
        //stor the original color for later methods.
        colorTracker = c;
    }
    
    //method: getColor
    //purpose: return the color of the button
    public Color getColor() {
        return colorTracker;
    }
    
    //method: highlight
    //purpose: simulate highlighting by making the color
    //lighter when the mouse goes over the button
    public void highlight() {
        color = colorTracker;
        this.repaint();
    }
    
    //method: unhighlight
    //purpose: go back to the regular color of the 
    //button when the mouse is not over it
    public void unhighlight() {
        color = colorTracker;
        color = color.darker();
        this.repaint();
    }
        
    //method: paintComponent
    //purpose: override the super class paintcomponent method.
    public void paintComponent(Graphics g) {
        //invoke the super class paint component method
        super.paintComponent(g);
        
        //set the color and size of the button
        g.setColor(color);
        g.fillOval(0, 0, 100, 100);
    } 
}
