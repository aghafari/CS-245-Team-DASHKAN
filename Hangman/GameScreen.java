/*************************************************************************
 *      File Name: GameScreen.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/12/2016
 * 
 *      Purpose: Draw the game screen and interact with the user.
 *      This class also randomly selects a word from a list of 5.
 *      it repaints the panel depending on the guesses given by the
 *      user.
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *  JPanel subclass to display the hangman game GUI
 */
public class GameScreen extends JPanel {
    //to make the letter buttons
    private final String[] letters = 
        {"A", "B", "C", "D", "E", "F",
        "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X",
        "Y", "Z"}; 
    //list of possible words
    private final String[] words = 
        {"ABSTRACT", "CEMETERY", "NURSE",
        "PHARMACY", "CLIMBING"};
    private final char[] word; //to store the word
    private int wrongGuesses; //keep track of wrong guesses
    private int score; //keep track of the score
    
    //no-arg constructor
    public GameScreen() {
        //intialize random number seed to current time
        Random r = new Random(System.currentTimeMillis());
        //get a random word from the list
        int index = r.nextInt(words.length);
        //convert it to a char array
        word = words[index].toCharArray();
        wrongGuesses = 0; //set wrong guesses to 0
        score = 100; //set score to 100
    }
    
    //method: getLetters
    //purpose: getter method for the char of letters
    public String[] getLetters(){
        return letters;
    }    
    
    //method: getWord
    //purpose: getter method for the word field
    public char[] getWord() {
        return word;
    }
    
    //method: getPreferredSize
    //purpose: give the prefereed size of the panel to the
    //layout manager.
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    //method: checkGuesses
    //purpose: to ensure the user doesn't exceed the maximum
    //amount of guesses for the game.
    public void checkGuesses(int guesses) {
        wrongGuesses = guesses; //update wrong guesses count
        this.repaint(); //repaint the graphic based on the incorrect letters
    }
    
    //method: paintComponent
    //purpose: override the super class method to customize
    //the panel to our specifications
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //set score to 100
        score = 100;
        
        //set graphics color
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        
        g2.fillRect(150, 250, 200, 5); //draw base
        g2.fillRect(200, 100, 5, 150); //draw pole
        g2.fillRect(205, 100, 70, 5); //draw extendsion of the pole
        
        //calculate the score based on how many guesses are wrong
        score -= (10 * wrongGuesses);
        
        //display the current score
        String scoreString = "SCORE: " + Integer.toString(score);
        g2.drawString(scoreString, 50, 150);
        
        //draw the body based on the number of wrong guesses
        if (wrongGuesses == 1) {
          //only draw head
          g2.fillOval(260, 105, 30, 30);                  
        } else if (wrongGuesses == 2) {
          //draw head and body
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
        } else if (wrongGuesses == 3) {
          //draw head, body, and 1 arm
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
          g2.setStroke(new BasicStroke(3));
          g2.drawLine(276, 150, 290, 170); 
          g2.setStroke(new BasicStroke(1));
        } else if (wrongGuesses == 4) {
          //draw head, body, and 2 arms
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
          g2.setStroke(new BasicStroke(3));
          g2.drawLine(276, 150, 290, 170);
          g2.drawLine(273, 150, 256, 170); 
          g2.setStroke(new BasicStroke(1));
        } else if (wrongGuesses == 5) {
          //draw head, body, 2 arms, and 1 leg
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
          g2.setStroke(new BasicStroke(3));
          g2.drawLine(276, 150, 290, 170);
          g2.drawLine(273, 150, 256, 170);
          g2.drawLine(276, 185, 290, 205);
          g2.setStroke(new BasicStroke(1));
        } else if (wrongGuesses == 6) {
          //draw the whole hangman
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
          g2.setStroke(new BasicStroke(3));
          g2.drawLine(276, 150, 290, 170);
          g2.drawLine(273, 150, 256, 170);
          g2.drawLine(276, 185, 290, 205);
          g2.drawLine(273, 185, 256, 205);
          g2.setStroke(new BasicStroke(1));                 
        }
    }    
}
