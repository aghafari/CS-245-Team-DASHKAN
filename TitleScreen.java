/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 *
 * @author user
 */
public class TitleScreen extends JPanel {
    
    public TitleScreen() {
        setBackground(Color.BLACK);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Font font = new Font("Times New Roman", Font.PLAIN, 32);
        Graphics2D g2 = (Graphics2D) g;
        g2.setFont(font);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, 
                            RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(Color.WHITE);
        g2.drawString("CS 245 Quarter Project", 140, 50);
        
        font = new Font("Times New Roman", Font.PLAIN, 20);
        g2.setFont(font);
        g2.drawString("By: Team Dashkan", 220, 370);
    }
}
