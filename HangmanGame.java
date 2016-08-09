/*************************************************************************
 *      File Name: HangmanGame.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 1
 *      Date Last Modified: 8/8/2016
 * 
 *      Purpose: Create a GUI that involves a user playing a game of 
 *      Hangman. The user is given three options on starting the program: 
 *      play a game, view highscores, and view the credits. Choosing to 
 *      play a game will bring up a GUI where the user can select letters
 *      to guess the word. Each wrong guess loses points and gains a
 *      part of the hangman. The user keeps guessing until they get the 
 *      word correct or they guess wrong too many times. 
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

/**
 * The main class of the project that handles all the direction
 * of the button clicked by the user.
 */
public class HangmanGame {
    
    private static JFrame f; //holds all the panels
    private static final String[] letters = 
        {"A", "B", "C", "D", "E", "F",
        "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X",
        "Y", "Z"}; //to make the letter buttons
    private static char[] selectedWord; //random word from the list
    private static JLabel[] wordOutline; //outline of random word
    private static int wrongGuesses; //to keep track of wrong guesses
    private static int score; //to keep track of the score
    private static String date; //to hold the date
    private static JLabel currentDate; //to display the date
    
    //method: createAndShowGUI
    //purpose: To intialize the JFrame and display the title screen
    //for 3 seconds. Then switch over to the options screen.
    private static void createAndShowGUI() {
        //intialize the JFrame and set it to close on exit
        f = new JFrame("Hangman Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        //create a new title screen and add to the JFrame
        TitleScreen screen = new TitleScreen();
        f.add(screen);
        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null); //set to center of the screen
        
        //create an ActionListener for the 3 second title screen timer.
        ActionListener closeScreen;
        closeScreen = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                //after 3 seconds clear the frame and go to
                //options screen
                f.remove(screen); 
                presentOptions();
            }
        };
        
        //create and start the 3 second timer and set it
        //to only go off once
        Timer timer = new Timer(3000, closeScreen);
        timer.setRepeats(false);
        timer.start();
    }
    
    //method: presentOptions
    //purpose: To put a panel on the JFrame to allow the user to
    //navigate to the various option of the GUI.
    private static void presentOptions() {
        //create and add a new option screen panel
        OptionScreen options = new OptionScreen();
        f.add(options);
        
        //create the buttons for the screen to direct the 
        //user to the chosen option
        JButton game = new JButton("GAME");
        JButton scores = new JButton("HIGH SCORES");
        JButton credits = new JButton("CREDITS");
        //customize the font of the buttons
        game.setFont(new Font("Broadway", Font.ITALIC, 20));
        scores.setFont(new Font("Broadway", Font.ITALIC, 20));
        credits.setFont(new Font("Broadway", Font.ITALIC, 20));
        
        //add the buttons to the panel
        options.add(game);
        options.add(scores);
        options.add(credits);
        
        //add mouse listeners to the buttons to know if the user
        //clicked on a given option.
        game.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //remove the options panel and go
                //to the game screen
                f.remove(options);
                playGame();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {} 
        });
                
        scores.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //clear the frame and display the scores
                f.remove(options);
                getScores();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {} 
            });
                
        credits.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //clear the screen and display the credits
                f.remove(options);
                getCredits();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {} 
            });
        
        //update the frame with the new panel
        f.pack();
        f.setVisible(true);
    }
    
    //method: playGame
    //purpose: To create a panel for the user to play the hangman
    //game with. It also keeps track of the date and updates every
    //second. 
    private static void playGame() {
        //create a new game screen panel
        GameScreen gs = new GameScreen();
        
        //get the random word
        selectedWord = gs.getWord();
        //create the outline for the word
        wordOutline = new JLabel[selectedWord.length];
        wrongGuesses = 0; //set wrong guesses to 0 initially
        score = 100; //set the beginning score to 100
        
        //loop to create the letter buttons
        for (int i = 0; i < letters.length; i++) {
            //initialize the button with the current letter
            JButton l = new JButton(letters[i]);
            //customize the font
            l.setFont(new Font("Times New Roman", Font.PLAIN, 10));
            //add and actionlistener to each button
            l.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    //take the text of the button clicked
                    //and disable the button
                    char guess = l.getText().charAt(0);
                    l.setEnabled(false);
                    
                    boolean letterPresent = false; 
                    for(int i = 0; i < selectedWord.length; i++) {
                        if (selectedWord[i] == guess) {
                            //if the letter is present change the 
                            //outline labels
                            letterPresent = true;
                            wordOutline[i].setText(String.valueOf(guess));
                        }
                    }
                    
                    //check if the letter was pressent
                    if (!letterPresent) {
                      //if not, increase wrong guesses by one and
                      //drop the score by 10 points
                      wrongGuesses++;
                      score -= 10;
                      //draw the correct body part
                      gs.checkGuesses(wrongGuesses);
                      //if max wrong guesses is reached
                      if(wrongGuesses == 6) {
                          //remove the panel and go to end screen
                          f.remove(gs);
                          getGameEnd(score);
                      }
                    } else {
                      //if the letter is present, check if the word has
                      //been guessed
                      boolean wordGuessed = true;
                      for (int i = 0; i < selectedWord.length; i++) {
                          //if any letter in the outline does not match
                          //the word, the word has not been guessed yet
                          if (selectedWord[i] != wordOutline[i].getText().charAt(0)) {
                              wordGuessed = false;
                          }
                      }
                      //if the word has been guessed
                      if (wordGuessed) {
                          //remove the panel and display the score
                          f.remove(gs);
                          getGameEnd(score);
                      }
                    }
                }
            });
            //add the button to the panel
            gs.add(l);            
        }        
        
        //loop to display the outline for the chosen word
        for (int i = 0; i < selectedWord.length; i++) {
            //each label is initially an underscore
            JLabel j = new JLabel("_");
            //add the label to the wordOutline array
            wordOutline[i] = j;
            //customize the font
            j.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            //add the label
            gs.add(j);
        }
        
        //Button to execute the skip function
        JButton skip = new JButton("SKIP");
        skip.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //if clicked, remove the panel and
                //display a score of 0
                f.remove(gs);
                getGameEnd(0);
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        //add skip button
        gs.add(skip);
        
        //get the current date and save it in a string
        date = ZonedDateTime.now().format(
                      DateTimeFormatter.RFC_1123_DATE_TIME);
        //create a lable with the date information
        currentDate = new JLabel(date.substring
                      (0, date.length() - 6));
        //add the date label to the panel
        gs.add(currentDate);
        //every second update the date label
        Timer dateTimer = new Timer(1000, new ActionListener(){
            public void actionPerformed(ActionEvent e){
                //do the same process as the first time
                date = ZonedDateTime.now().format(
                       DateTimeFormatter.RFC_1123_DATE_TIME);
                currentDate.setText((date.substring(0, date.length() - 6)));
            }
        });
        //start the timer
        dateTimer.start();
        
        //add the gamescreen and update the frame
        f.add(gs);
        f.pack();
        f.setVisible(true);
    }
    
    //method: getGameEnd
    //purpose: To display the game over screen to the user and give
    //the final score. It takes an integer value which is the score
    //calculated in the playGame method.
    private static void getGameEnd(int n) {
        //create a new endscreen panel and add it to the frame
        EndScreen es = new EndScreen(n);
        f.add(es);        
        
        //button for the end function in the panel
        JButton end = new JButton("END");
        //set the location of the button
        es.setLayout(null);
        end.setBounds(500, 300, 60, 40);
        //add a mouse listener to the button
        end.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //remove the end screen and go to options screen
                f.remove(es);
                presentOptions();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        //add the end button to the panel
        es.add(end);
        
        //update the frame with the panel
        f.pack();
        f.setVisible(true);
    }
    
    //method: getScores
    //purpose: To display the high scores to the user.
    private static void getScores() {
        //create a new high score screen and add it to the frame
        HighScoreScreen hss = new HighScoreScreen();
        f.add(hss);
        
        //create a back button to go back to the options screen
        JButton back = new JButton("BACK");
        //set the location of the button
        hss.setLayout(null);
        back.setBounds(30, 350, 100, 30);
        //add a mouselistener to the back button
        back.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //remove the screen and go back to options
                hss.removeAll();
                presentOptions();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        //add the back button to the panel
        hss.add(back);
        
        //update the frame with the panel
        f.pack();
        f.setVisible(true);
    }
    
    //mehtod: getCredits
    //purpose: To display the credits to the user.
    private static void getCredits() {
        //create and add a new credits screen panel
        CreditsScreen cs = new CreditsScreen();
        f.add(cs);
        
        //create a back button to go to options
        JButton back = new JButton("BACK");
        //set the location of the back button
        cs.setLayout(null);
        back.setBounds(30, 350, 100, 30);
        //add a mouse listener to the button
        back.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //remove the panel and go back to options
                cs.removeAll();
                presentOptions();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        //add the back button to the panel
        cs.add(back);
        
        //update the frame with the credits screen
        f.pack();
        f.setVisible(true);
    }
    
    //method: main
    //purpose: Use the SwingUtilities to run the GUI for the
    //project.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI(); //displays the GUI
            }
        });
    }
}
