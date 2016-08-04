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
        
        g.drawString("CS 245 Quarter Project", 10, 20);
    }
}