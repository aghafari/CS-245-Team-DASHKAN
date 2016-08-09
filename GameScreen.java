/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 *
 * @author user
 */
public class GameScreen extends JPanel {
    private final String[] words = 
        {"ABSTRACT", "CEMETERY", "NURSE",
        "PHARMACY", "CLIMBING"};
    private final char[] word;
    private String lettersGuessed;
    private int wrongGuesses;
    private int score;
    
    public GameScreen() {
        Random r = new Random(System.currentTimeMillis());
        int index = r.nextInt(words.length);
        word = words[index].toCharArray();
        lettersGuessed = new String();
        wrongGuesses = 0;
        score = 100;
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    public void checkGuesses(int guesses) {
        wrongGuesses = guesses;
        this.repaint();
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        score = 100;
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.BLACK);
        g2.fillRect(150, 250, 200, 5);
        g2.fillRect(200, 100, 5, 150);
        g2.fillRect(205, 100, 70, 5);
        score -= (10 * wrongGuesses);
        String scoreString = "SCORE: " + Integer.toString(score);
        g2.drawString(scoreString, 100, 100);
        if (wrongGuesses == 1) {
          g2.fillOval(260, 105, 30, 30);                  
        } else if (wrongGuesses == 2) {
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
        } else if (wrongGuesses == 3) {
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
          g2.setStroke(new BasicStroke(3));
          g2.drawLine(276, 150, 290, 170); 
          g2.setStroke(new BasicStroke(1));
        } else if (wrongGuesses == 4) {
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
          g2.setStroke(new BasicStroke(3));
          g2.drawLine(276, 150, 290, 170);
          g2.drawLine(273, 150, 256, 170); 
          g2.setStroke(new BasicStroke(1));
        } else if (wrongGuesses == 5) {
          g2.fillOval(260, 105, 30, 30);
          g2.fillRect(273, 135, 3, 50);
          g2.setStroke(new BasicStroke(3));
          g2.drawLine(276, 150, 290, 170);
          g2.drawLine(273, 150, 256, 170);
          g2.drawLine(276, 185, 290, 205);
          g2.setStroke(new BasicStroke(1));
        } else if (wrongGuesses == 6) {
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
    
    public char[] getWord() {
        return word;
    }
    
    public int getWrongGuesses() {
        return wrongGuesses;
    }
    
    public boolean checkLetter(char letter) {
        for (int i = 0; i < word.length; i++) {
            if (word[i] == letter) {
                return true;
            }
        }
        wrongGuesses++;
        return false;
    }
    
    public boolean alreadyGuessed(char letter) {
        for (int i = 0; i < lettersGuessed.length(); i++) {
            if (lettersGuessed.charAt(i) == letter) {
                return true;
            }
        }
        return false;
    }
    
    public boolean wordGuessed() {
        for (int i = 0; i < word.length; i++) {
            //if (word[i] != outline[i])
              //  return false;
        }
        return true;
    }
    
    public void printWord(char[] c) {
        String s = new String();
        for(int i = 0; i < c.length; i++) {
            s += c[i];
        }
        System.out.println(s);
    }
    
    public void playGame() {
        int score = 100;
        while (wrongGuesses < 6 && !wordGuessed()) {
            System.out.println("Enter a Letter");
            char guess = 'k';
            guess = Character.toUpperCase(guess);
            if (alreadyGuessed(guess)) {
                System.out.println("Already Guessed");
            } else {
                lettersGuessed += Character.toString(guess);
                if (checkLetter(guess)) {
                    for (int i = 0; i < word.length; i++) {
                        if (word[i] == guess) {
                           // outline[i] = guess;
                        }
                    }
                } else {
                    System.out.println("Not Present");
                    score -= 10;
                }
            }
        }        
        if(wordGuessed()) {
            System.out.println("You Win");
            System.out.println("Score: " + score);
        } else {
            System.out.println("You Lose");
            System.out.println("Score: " + score);
        }
    }
}
