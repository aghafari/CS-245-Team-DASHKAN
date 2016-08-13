/*************************************************************************
 *      File Name: ColorButton.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/13/2016
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
 *
 * @author user
 */
public class ColorButton extends JLabel {
    
    private Color color;
    private final Color colorTracker;
    
    public ColorButton(Color c) {
        color = c.darker();
        colorTracker = c;
    }
    
    public Color getColor() {
        return colorTracker;
    }
    
    public void highlight() {
        color = colorTracker;
        this.repaint();
    }
    
    public void unhighlight() {
        color = colorTracker;
        color = color.darker();
        this.repaint();
    }
        
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.setColor(color);
        g.fillOval(0, 0, 100, 100);
    } 
}
