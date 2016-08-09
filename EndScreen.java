/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman;

import java.awt.*;
import javax.swing.*;

/**
 *
 * @author user
 */
public class EndScreen extends JPanel {
    
    private int score;
    
    public EndScreen() {
        score = 0;
        this.setBackground(Color.RED);
    }
    
    public EndScreen(int s) {
        score = s;
        this.setBackground(Color.RED);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        g.setFont(new Font("Times New Roman", Font.PLAIN, 30));
        g.drawString("GAME OVER", 190, 150);
        String scoreDisplay = "SCORE: " + Integer.toString(score);
        g.drawString(scoreDisplay, 190, 200);
    }
}
