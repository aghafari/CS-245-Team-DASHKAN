package quarterproject1;

import javax.swing.JPanel;
import javax.swing.BorderFactory;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Font;
import java.awt.RenderingHints;

public class MyPanel extends JPanel
{
    public MyPanel()
    {
        setBorder(BorderFactory.createLineBorder(Color.black));
        setBackground(Color.BLACK);
        setOpaque(true);
    }
    
    public Dimension getPreferredSize()
    {
        return new Dimension(600, 400);
    }
    
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        
        Font font = new Font("Times New Roman", Font.BOLD, 36);
        Graphics2D g2 = (Graphics2D)g;
        g2.setFont(font);
        g2.setColor(Color.WHITE);
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.drawString("CS 245 Quarter Project", 100, 50);
        
        Font font2 = new Font("Times New Roman", Font.BOLD, 24);
        g2.setFont(font2);
        g2.drawString("By: Team Dashkan", 190, 350);
    }
}