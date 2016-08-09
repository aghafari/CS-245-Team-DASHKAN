/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman;

import javax.swing.*;
import java.awt.*;

/**
 *
 * @author user
 */
public class CreditsScreen extends JPanel{
    
    public CreditsScreen() {
        setBackground(Color.GREEN);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Broadway", Font.PLAIN, 32);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.drawString("CREDITS", 220, 50);
        
        font = new Font("Times New Roman", Font.PLAIN, 20);
        g2.setFont(font);
        g2.drawString("TRISTIN HRAFNSSON, 009821123", 140, 150);
        g2.drawString("DARYL OCC, 010475647", 140, 190);
        g2.drawString("ASHKAN GHAFARI, 009146475", 140, 230);
    }
}
