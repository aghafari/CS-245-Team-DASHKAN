/*************************************************************************
 *      File Name: GameScreen3.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Final Version
 *      Date Last Modified: 8/20/2016
 * 
 *      Purpose: Create the sudoku game for the user to play after 
 *      completion of the colored buttons game. The user will be given
 *      a standard sudoku grid to solve. The score will start out at
 *      540 point and will drop 10 points for every wrong space present
 *      in a submitted solution. The user can only lose the points once
 *      for any given square.
 *************************************************************************/
package Hangman;

import java.awt.*;
import javax.swing.*;
import javax.swing.text.*;

/**
 *  Class to create the JPanel for the third game
 *  the user will play. It will use a grid of
 *  JPanels that contain text fields.
 */
public class GameScreen3 extends JPanel {
    
    //2d array to hold the solution
    private final int[] solution = 
        {2, 4, 3, 9, 5, 6, 7, 1, 7, 6, 2, 3,
         6, 9, 8, 7, 4, 8, 2, 1, 5, 9, 7, 3,
         1, 6, 5, 4, 8, 9, 7, 3, 8, 4, 6, 1,
         5, 9, 2, 3, 1, 8, 5, 4, 6, 2, 9, 3,
         2, 8, 7, 1, 4, 6};
    //2d array to keep track of the sudoku grid.
    private final int[][] startingGrid = 
        {{8, 0, 0, 4, 0, 6, 0, 0, 7},
        {0, 0, 0, 0, 0, 0, 4, 0, 0},
        {0, 1, 0, 0, 0, 0, 6, 5, 0},
        {5, 0, 9, 0, 3, 0, 7, 8, 0},
        {0, 0, 0, 0, 7, 0, 0, 0, 0},
        {0, 4, 8, 0, 2, 0, 1, 0, 3},
        {0, 5, 2, 0, 0, 0, 0, 9, 0},
        {0, 0, 1, 0, 0, 0, 0, 0, 0},
        {3, 0, 0, 9, 0, 2, 0, 0, 5}};
    private int[][] grid; //to draw out the sudoku grid
    private SudokuBox[] boxes; //to keep track of the text fields
    private boolean checkedOnce; //to ensure the user keeps points after first check
    private boolean paintedOnce; //to only paint the sudoku grid once
    private int score; //to hold the score 
    
    //no-arg constructor
    public GameScreen3() {
        grid = new int[9][9]; //set the grid to the correct size
        //set the grid to the initial game position
        for (int i = 0; i < startingGrid.length; i++) {
            for (int j = 0; j < startingGrid[i].length; j++) {
                grid[i][j] = startingGrid[i][j];
            }
        }
        boxes = new SudokuBox[54]; //initialize the boxes array
        paintedOnce = false; //set paintedonce to false
        checkedOnce = false; //set checkedonce to false
    }
    
    //getter for the score variable
    public int getScore() {
        return score;
    }
    
    //setter for the score variable
    public void setScore(int s) {
        score = s;
    }
    
    //getter for the checkedonce variable
    public boolean getCheckedOnce() {
        return checkedOnce;
    }
    
    //method: getPreferredSize
    //purpose: tell the frame the size to make 
    //the panel
    public Dimension getPreferredSize()  {
        return new Dimension(600, 400);
    }
    
    //method: checkSolution
    //purpose: to check to see if the submitted
    //solution is correct
    public boolean checkSolution() {
        //create variable to return after checking
        boolean check = true;
        //go through and check each box against the solution
        for (int i = 0; i < solution.length; i++) {
            //get the value of the current box
            int n = boxes[i].getValue();
            //if the number doesn't match
            if (n != solution[i]) {
                //if the box hasn't been guessed wrong before
                if (!boxes[i].getUsed()) {
                    //set the flag to tell the box has been guessed
                    boxes[i].setUsed(true);
                    score -= 10; //deduct 10 points
                    check = false; //return false for the method
                } else {
                    check = false; //return false but don't deduct points
                }
            }
        }
        checkedOnce = true;
        return check;
    }
    
    //method: testString
    //purpose: to ensure the user doesn't enter anything
    //other than the digits 1-9 in the text fields
    public boolean testString(String s) {
        if (s.equals("1") || s.equals("2") ||
            s.equals("3") || s.equals("4") ||
            s.equals("5") || s.equals("6") ||
            s.equals("7") || s.equals("8") ||
            s.equals("9")) {
            return true;
        } else {
            return false;
        }
    }
    
    //method: paintComponent
    //purpose: paint the game screen on the panel
    public void paintComponent(Graphics g) {
        //invoke the super class method
        super.paintComponent(g);                        
        
        //update the score whenever the screen is repainted
        String scoreString = "SCORE: " + Integer.toString(score);
        g.setColor(Color.BLACK);
        g.drawString(scoreString, 20, 20);        
        
        //check if the grid has already been painted
        if (!paintedOnce) {
            //create a big board to hold everything in
            JPanel board = new JPanel();
            board.setBounds(140, 50, 315, 315);
            board.setLayout(null);
            board.setBorder(BorderFactory.createLineBorder(Color.BLACK, 5));
            this.add(board);

            int boxTracker = 0; //to keep track of what text field was added
            for (int i = 0; i < 3; i++) {
                for (int j = 0; j < 3; j++) {
                    //create a smaller box to hold 9 fields or labels
                    JPanel innerBox = new JPanel();
                    innerBox.setBounds(105 * i, 105 * j, 105, 105);
                    innerBox.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                    innerBox.setLayout(null);
                    
                    for (int k = 0; k < 3; k++) {
                        for (int l = 0; l < 3; l++) {
                            //get the current value in the grid
                            String s = Integer.toString(grid[3*j + l][3*i + k]);
                            //for all values of 0, we must add a text field
                            if (s.equals("0")) {
                                //create a text field at this place
                                SudokuBox sb = new SudokuBox(3*j + l, 3*i + k);
                                sb.setHorizontalAlignment(JTextField.CENTER);
                                sb.setBounds(35 * k, 35 * l, 35, 35);
                                sb.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                
                                //add a document filter to mediate the user input
                                AbstractDocument d = (AbstractDocument) sb.getDocument();
                                d.setDocumentFilter(new DocumentFilter() {
                                    public void replace(DocumentFilter.FilterBypass fb, 
                                            int offset, int length, String text, 
                                            AttributeSet attrs) throws BadLocationException {
                                            int boxLength = fb.getDocument().getLength(); 
                                            
                                            //if the user enters a digit 1-9
                                            if (testString(text) && 
                                            boxLength - length + text.length() <= 1) {
                                                //turn it into an integer
                                                int n = Integer.parseInt(text);
                                                sb.setValue(n); //set the field to check later
                                                //change the corresponding number in the grid
                                                grid[sb.getXValue()][sb.getYValue()] = n;
                                                //show the text to the user
                                                super.replace(fb, offset, length, 
                                                              text, attrs);
                                              //if the user enters anything else
                                            } else {
                                                //show the following message
                                                JOptionPane.showMessageDialog(null, 
                                                          "Only Enter Digits 1-9");
                                            }
                                    }
                                });
                                //add the text field to the boxes array
                                boxes[boxTracker] = sb;
                                boxTracker++;
                                
                                //add the text field to the inner panel
                                innerBox.add(sb);
                              //if the number is intended to be a hint
                            } else {
                                //make it a label so it cannot be edited
                                JLabel box = new JLabel(s, SwingConstants.CENTER);
                                box.setBounds(35 * k, 35 * l, 35, 35);
                                box.setBorder(BorderFactory.createLineBorder(Color.BLACK));
                                
                                //add it to the inner panel
                                innerBox.add(box);
                            }
                        }
                    }
                    //add the inner panel to the board panel
                    board.add(innerBox);
                }
            }            
            //set paintedonce to true
            paintedOnce = true;
        }
    }
}
