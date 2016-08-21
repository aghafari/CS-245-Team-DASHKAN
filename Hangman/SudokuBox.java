/*************************************************************************
 *      File Name: SudokuBox.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Final Version
 *      Date Last Modified: 8/20/2016
 * 
 *      Purpose: To create a text field to be able to guess a number in
 *      sudoku. This class will keep track of where the box is in the 
 *      sudoku grid and if it has been guessed already.
 *************************************************************************/
package Hangman;

import javax.swing.*;

/**
 *  Class to easily keep track of all the information to 
 *  complete the sudoku game.
 */
public class SudokuBox extends JTextField {
    
    private int value; //to hold the value of the number entered
    private int x; //to give its x position in the grid
    private int y; //to give its y position in the grid
    private boolean used; //to tell if the box has been guessed before
    
    //two-arg constructor to set the x and y posistion of the field
    public SudokuBox(int x, int y) {
        this.x = x;
        this.y = y;
        used = false;
    }
    
    //getter for the value variable
    public int getValue() {
        return value;
    }

    //getter for the x variable
    public int getXValue() {
        return x;
    }

    //getter for the y variable
    public int getYValue() {
        return y;
    }

    //getter for the used variable
    public boolean getUsed() {
        return used;
    }
    
    //setter for the value variable
    public void setValue(int v) {
        value = v;
    }
    
    //setter for the used variable
    public void setUsed(Boolean b) {
        used = b;
    }
}
