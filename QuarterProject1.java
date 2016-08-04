package quarterproject1;

import javax.swing.SwingUtilities;
import javax.swing.JFrame;

public class QuarterProject1 
{
    public static void main(String[] args) 
    {
        SwingUtilities.invokeLater
        (
            new Runnable()
            {
                public void run()
                {
                    createAndShowGUI();
                }
            }
        );
    }
    
    private static void createAndShowGUI()
    {
        System.out.println("Greate GUI on EDT? " + SwingUtilities.isEventDispatchThread());
        JFrame f = new JFrame("Basic JFrame");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        f.add(new MyPanel());
        f.pack();
        f.setVisible(true);
    }
}