/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Hangman;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author user
 */
public class OptionScreen extends JPanel {
    private BufferedImage pic;
    
    public OptionScreen() {
        setBackground(Color.BLACK);
    }
    
    public Dimension getPreferredSize() {
        return new Dimension(600, 400);
    }
    
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        try {
            pic = ImageIO.read(new 
                  File("C:\\Users\\user\\Documents\\NetBeansProjects\\CS245 "
                          + "Quater Project\\src\\Hangman\\mandelbrot.jpg"));
        } 
        catch (IOException e) {
            e.printStackTrace();
        }
        
        g.drawImage(pic, 120, 80, 384, 256, this);
    }
}
