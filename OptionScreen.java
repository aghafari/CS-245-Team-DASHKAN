/*************************************************************************
 *      File Name: OptionScreen.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 1
 *      Date Last Modified: 8/8/2016
 * 
 *      Purpose: Display the options to the user after the title screen
 *      has been displayed. The user has the choice to play a game, view
 *      the high scores, or have the credits displayed. 
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *  This class extends JPanel to take advantage of its
 *  properties to display the buttons to the user and
 *  show an image representing the project.
 */
public class OptionScreen extends JPanel {
    private BufferedImage pic; //to hold the image
    
    //no-arg constructor
    public OptionScreen() {
        //set the background to black
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
        
        //try-catch block to display the image for the project
        try {
            pic = ImageIO.read(new 
                  File("C:\\Users\\user\\Documents\\NetBeansProjects\\CS245 "
                          + "Quater Project\\src\\Hangman\\mandelbrot.jpg"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        //draw the given picture on the panel
        g.drawImage(pic, 120, 80, 384, 256, this);
    }
}
