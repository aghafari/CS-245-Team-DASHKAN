/*************************************************************************
 *      File Name: HangmanGame.java
 *      Authors: Tristin Hrafnsson, Darryl Occ,
 *               Ashkan Ghafari
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 2
 *      Date Last Modified: 8/15/2016
 * 
 *      Purpose: Create a GUI that involves a user playing a game of 
 *      Hangman. The user is given three options on starting the program: 
 *      play a game, view highscores, and view the credits. Choosing to 
 *      play a game will bring up a GUI where the user can select letters
 *      to guess the word. Each wrong guess loses points and gains a
 *      part of the hangman. The user keeps guessing until they get the 
 *      word correct or they guess wrong too many times. The game then 
 *      switches to a game with 5 different colored buttons. The user
 *      has to select the color button that matches the font color 
 *      of a color name provided. The score is then calculated and if
 *      it is higher than the lowest high score the user enters their
 *      initials and it is recorded in the high scores screen.
 *************************************************************************/
package Hangman;

import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

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
    private static String[] colors = 
        {"RED", "YELLOW", "GREEN", 
         "BLUE", "PURPLE"
        };
    private static Color[] colorValues =
        {Color.RED, Color.YELLOW, 
         Color.GREEN, Color.BLUE, 
         Color.MAGENTA
        };
    private static String[] names; //hold the names of the high scores
    private static String[] scores; //hold the values of the high scores
    private static char[] selectedWord; //random word from the list
    private static JLabel[] wordOutline; //outline of random word
    private static int wrongGuesses; //to keep track of wrong guesses
    private static int score; //to keep track of the score
    private static String date; //to hold the date
    private static JLabel currentDate; //to display the date
    private static Random colorSelector; //to select a random color
    private static Random colorPlacer; //to place a color button randomly
    private static int[] usedValues; //to keep track of values already used
    private static int rounds; //to keep track of the colored button game rounds
    
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
        JButton scoresButton = new JButton("HIGH SCORES");
        JButton credits = new JButton("CREDITS");
        //customize the font of the buttons
        game.setFont(new Font("Broadway", Font.ITALIC, 20));
        scoresButton.setFont(new Font("Broadway", Font.ITALIC, 20));
        credits.setFont(new Font("Broadway", Font.ITALIC, 20));
        
        //add the buttons to the panel
        options.add(game);
        options.add(scoresButton);
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
                
        scoresButton.addMouseListener(new MouseListener() {
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
                          playGame2();
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
                          playGame2();
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
                score = 0;
                playGame2();
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
    }
    
    //method: getAreaToPlace
    //purpose: To give the bounds to place the buttons for the
    //second game. 
    private static Rectangle getAreaToPlace(int n) {
        switch(n) {
            case 0:
                //first button location
                return new Rectangle(50, 70, 100, 100);
            case 1:
                //second button location
                return new Rectangle(100, 250, 100, 100);                
            case 2:
                //third button location
                return new Rectangle(270, 190, 100, 100);
            case 3:
                //fourth button location
                return new Rectangle(370, 50, 100, 100);
            case 4:
                //fifth button location
                return new Rectangle(450, 250, 100, 100);
            default:
                return null;
        }
    }
    
    //method: checkIfUsed
    //purpose: To determine whether a random number was already 
    //used to color a button in the button game. This ensures
    //that no color is chosen twice.
    private static boolean checkIfUsed(int n) {
        //go through all the values in the usedValues array
        for (int i = 0; i < usedValues.length; i++) {
            //if any number is present return true
            if (usedValues[i] == n)
                return true;
        }
        
        //if the number is not found return false
        return false;
    }    
    
    //method: playGame2
    //purpose: To create a panel for the user to play the colored
    //button game with. The date is displayed and updated each 
    //second. It is played directly after the hangman game.
    private static void playGame2() {
        //create a new seccond game screen and add it to the frame
        GameScreen2 gs2 = new GameScreen2();
        f.add(gs2);
        
        //set the panel layout manager to null
        gs2.setLayout(null);        
        
        //get the current date and save it in a string
        date = ZonedDateTime.now().format(
                      DateTimeFormatter.RFC_1123_DATE_TIME);
        //create a lable with the date information
        currentDate = new JLabel(date.substring
                      (0, date.length() - 6));
        //add the date label to the pane
        gs2.add(currentDate);
        currentDate.setBounds(420, 10, 170, 20);
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
        
        //add a new label to hold the word to indicate what
        //color button to press
        JLabel j = new JLabel();
        j.setBounds(300, 20, 70, 30);
        gs2.add(j);
        
        //set the rounds to 0 and carry on the score from the 
        //first game
        rounds = 0;
        gs2.setScore(score);
        
        //initialize the seed for the random number generators
        colorSelector = new Random(System.currentTimeMillis());
        colorPlacer = new Random(System.currentTimeMillis() / 2);
        
        //Fill the array that keeps track of the numbers used.
        //This array ensures that no number is used twice to 
        //select a color for the buttons.
        usedValues = new int[colorValues.length];
        for (int i = 0; i < usedValues.length; i++) 
            usedValues[i] = -1;
        
        //get a random number for the actual color name
        int index = colorSelector.nextInt(colors.length);
        String word = colors[index];
        
        //get a random number for the color of the text
        int index2 = colorSelector.nextInt(colorValues.length);
        Color currentColor = colorValues[index2];                    
                    
        //set the color and word of the label
        j.setForeground(currentColor);
        j.setText(word);

        //this loop places the buttons on the screen
        for (int i = 0; i < colorValues.length; i++) {
            //get a random number for the color of the button 
            //and check if it has been used, if it has keep 
            //getting random number until a new one is used.
            int colorIndex = colorPlacer.nextInt(colorValues.length);
            while(checkIfUsed(colorIndex)) 
                colorIndex = colorPlacer.nextInt(colorValues.length);
            
            //add a number to the array once it has been used
            usedValues[i] = colorIndex;
                
            //create a new colored button with the random color selected
            ColorButton cb = new ColorButton(colorValues[colorIndex]);
            //set the button in the determined area
            cb.setBounds(getAreaToPlace(i));
            //add a mouse listener to the button
            cb.addMouseListener(new MouseListener() {
                //when the mouse enters the button, highlight it
                public void mouseEntered(MouseEvent e) {
                    cb.highlight();
                }
            
                //when the mouse exits, unhighlight it
                public void mouseExited(MouseEvent e) {
                    cb.unhighlight();
                }
            
                //when the mouse is clicked check the color
                public void mouseClicked(MouseEvent e) {
                    //if the text color matches the button's color
                    //add 100 points to the score
                    if (cb.getColor().equals(j.getForeground()))
                        score += 100;
                    //get a new random color word to display
                    int index = colorSelector.nextInt(colors.length);
                    String word = colors[index];
        
                    //get a new random color to set the text to
                    int index2 = colorSelector.nextInt(colorValues.length);
                    Color currentColor = colorValues[index2];                    
                        
                    //set the text and color of the panel
                    j.setForeground(currentColor);
                    j.setText(word);
                    
                    //update the score and repaint the panel
                    gs2.setScore(score);
                    gs2.repaint();
                    
                    //keep track of how many rounds are played
                    rounds++;
                    //once 5 rounds are played, go the game over screen
                    if (rounds == 5) {
                        f.remove(gs2);
                        getGameEnd(score);
                    }
                }
            
                public void mousePressed(MouseEvent e) {}
                public void mouseReleased(MouseEvent e) {}            
            });
            //add the color button
            gs2.add(cb);
        }
                        
        //update the frame with the panel
        f.pack();
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
                //remove the end screen
                f.remove(es);
                //check if the score is a new high score
                int check = checkScores(n);
                if (check == -1) {
                    //if no new high score go back to menu
                    presentOptions();
                } else {
                    //if the score is high enough, get the
                    //player's initials
                    getInitials(check);
                }
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
    }
    
    //method: checkScores
    //purpose: Determine whether the score a player got after playing
    //a game is high enough to be put on the high score screen. It
    //returns the index where the new score will go, so other methods
    //will know where to put the name corresponding to the score.
    private static int checkScores(int n) {
        //check the score against the others
        for (int i = 0; i < scores.length; i++) {
            //if the score is higher than any present score
            if (n > Integer.parseInt(scores[i])) {
                //move all the other scores down
                for (int j = scores.length - 1; j > i; j--) {
                    scores[j] = scores[j - 1];
                    names[j] = names[j - 1];
                }
                //put the current score in its place
                scores[i] = Integer.toString(n);
                return i; //return the index
            }
        }
        return -1; //return -1 if score is not a new high score
    }
    
    //method: getInitials
    //purpose: Get the user's initials if they achieve a new high 
    //score. The value it takes it the index to place the new name
    //in the name list.
    private static void getInitials(int n) {
        //create a new high score screen and add it to the frame
        NewHighScoreScreen nhs = new NewHighScoreScreen();
        f.add(nhs);
        
        //set the layout manager to null for positioning
        nhs.setLayout(null);
        
        //create a text field and add it to the panel
        JTextField initials = new JTextField(3);
        initials.setBounds(280, 170, 30, 30);        
        nhs.add(initials);
        
        //create an end button for after the initials are entered
        JButton end = new JButton("END");
        end.setBounds(500, 300, 60, 40);
        end.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                //remove the end screen
                f.remove(nhs);
                
                //get the name the user typed in and limit it to 3 characters
                String name = initials.getText();
                names[n] = name.length() > 3 ? name.substring(0, 3) : name;
                
                //create a new printwriter object to write the scores to the file
                PrintWriter pw = null;
                try {
                    //intialize the printwriter with the high scores file
                    pw = new PrintWriter(new File("src\\Hangman\\HighScoresRecords"));
                } catch(IOException ex) {
                    ex.printStackTrace();
                }
                //user the first name to overwrite the current file
                String s = names[0] + " " + scores[0];
                pw.write(s);
                pw.println();
                
                //fill the file with the remaining names and high scores
                for (int i = 1; i < names.length; i++) {
                    String input = names[i] + " " + scores[i];
                    pw.println(input);
                }
                
                //close the printer and go to the options screen
                pw.close(); 
                presentOptions();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        //add the end button
        nhs.add(end);        
        
        //update the frame
        f.pack();
    }
    
    //method: getScores
    //purpose: To display the high scores to the user.
    private static void getScores() {
        //create a new high score screen and add it to the frame
        HighScoreScreen hss = new HighScoreScreen(names, scores);
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
    }
    
    //method: main
    //purpose: Use the SwingUtilities to run the GUI for the
    //project. Also, use this method to initialize the 
    //name and high scores array to keep track of high scores
    //after each game is played.
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                //initialize the arrays to hold the names
                //and high scores
                names = new String[5];
                scores = new String[5];
                
                //create a scanner object to read from the file
                Scanner sc = null;

                try {
                    //initialize the scanner with the high scores file
                    sc = new Scanner(new File("src\\Hangman\\HighScoresRecords"));
                } catch (IOException e) {            
                    e.printStackTrace();
                    System.exit(0);
                }

                //keep track of the index to place the current name and score
                int index = 0;
                //read through the file and put all the scores in the arrays
                while(sc.hasNext()) {
                    //read a line from the file
                    String nextLine = sc.nextLine();
                    //create string to concatenate the characters from the lines
                    String name = "", score = "";
                    //keep track of where the name leaves off
                    int numberStart = 0;
                    //until you encounter a space read the characters of the name
                    for(int i = 0; !Character.isSpaceChar(nextLine.charAt(i)); i++) {
                        name += nextLine.charAt(i); //add it to the name string
                        numberStart = i; //set the numberStart to where we are 
                    }

                    //after reading the name start two characters ahead of where
                    //it left off to get the score
                    for(int i = numberStart + 2; i < nextLine.length(); i++) {
                        score += nextLine.charAt(i); //read score until end of line
                    }

                    //put the name and score in the respective array
                    names[index] = name;
                    scores[index] = score;
                    index++; //go on to the next index in the array
                }
                
                sc.close(); //close the scanner
                createAndShowGUI(); //displays the GUI
            }
        });
    }
}
