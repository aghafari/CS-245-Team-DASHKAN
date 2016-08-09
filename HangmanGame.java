/*************************************************************************
 *      File Name: HangmanGame.java
 *      Author: Tristin Hrafnsson
 *      Class: CS 245 - Programming Graphical User Interfaces
 * 
 *      Assignment: Quarter Project - Checkpoint 1
 *      Date Last Modified: 8/7/2016
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
 *
 * @author user
 */
public class HangmanGame {
    
    private static JFrame f;
    private static final String[] letters = 
        {"A", "B", "C", "D", "E", "F",
        "G", "H", "I", "J", "K", "L",
        "M", "N", "O", "P", "Q", "R",
        "S", "T", "U", "V", "W", "X",
        "Y", "Z"};
    private static char[] selectedWord;
    private static JLabel[] wordOutline;
    private static int wrongGuesses;
    private static int score;
    
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                createAndShowGUI();
            }
        });
    }
    
    private static void createAndShowGUI() {
        f = new JFrame("Hangman Game");
        f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        TitleScreen screen = new TitleScreen();
        f.add(screen);
        f.pack();
        f.setVisible(true);
        f.setLocationRelativeTo(null);
        
        ActionListener closeScreen;
        closeScreen = new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                screen.removeAll();
                presentOptions();
            }
        };
        
        Timer timer = new Timer(3000, closeScreen);
        timer.setRepeats(false);
        timer.start();
    }
    
    private static void presentOptions() {
        OptionScreen options = new OptionScreen();
        f.add(options);
                
        JButton game = new JButton("GAME");
        JButton scores = new JButton("HIGH SCORES");
        JButton credits = new JButton("CREDITS");
        game.setFont(new Font("Broadway", Font.ITALIC, 20));
        scores.setFont(new Font("Broadway", Font.ITALIC, 20));
        credits.setFont(new Font("Broadway", Font.ITALIC, 20));
                
        options.add(game);
        options.add(scores);
        options.add(credits);
                
        game.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
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
                f.remove(options);
                getCredits();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {} 
            });
        
            f.pack();
            f.setVisible(true);
    }
    
    private static void playGame() {
        GameScreen gs = new GameScreen();        
        selectedWord = gs.getWord();
        wordOutline = new JLabel[selectedWord.length];
        wrongGuesses = 0;
        score = 100;
        
        for (int i = 0; i < letters.length; i++) {
            JButton l = new JButton(letters[i]);
            l.setFont(new Font("Times New Roman", Font.PLAIN, 10));
            l.addActionListener(new ActionListener() {
                public void actionPerformed(ActionEvent e) {
                    
                    char guess = l.getText().charAt(0);
                    l.setEnabled(false);
                    
                    boolean letterPresent = false;
                    for(int i = 0; i < selectedWord.length; i++) {
                        if (selectedWord[i] == guess) {
                            letterPresent = true;
                            wordOutline[i].setText(String.valueOf(guess));
                        }
                    }
                    
                    if (!letterPresent) {
                      wrongGuesses++;
                      score -= 10;
                      gs.checkGuesses(wrongGuesses);
                      if(wrongGuesses == 6) {
                          f.remove(gs);
                          getGameEnd(score);
                      }
                    } else {
                      boolean wordGuessed = true;
                      for (int i = 0; i < selectedWord.length; i++) {
                          if (selectedWord[i] != wordOutline[i].getText().charAt(0)) {
                              wordGuessed = false;
                          }
                      }
                      if (wordGuessed) {
                          f.remove(gs);
                          getGameEnd(score);
                      }
                    }
                }
            });
            gs.add(l);            
        }        
        
        for (int i = 0; i < selectedWord.length; i++) {
            JLabel j = new JLabel("_");
            wordOutline[i] = j;
            j.setFont(new Font("Times New Roman", Font.PLAIN, 20));
            gs.add(j);
        }
        
        JButton skip = new JButton("SKIP");
        skip.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                f.remove(gs);
                getGameEnd(0);
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        gs.add(skip);
        
        f.add(gs);
        f.pack();
        f.setVisible(true);
    }
    
    private static void getGameEnd(int n) {
        EndScreen es = new EndScreen(n);
        f.add(es);
        

        JButton end = new JButton("END");
        es.setLayout(null);
        end.setBounds(500, 300, 60, 40);
        end.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                f.remove(es);
                presentOptions();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        es.add(end);
        
        f.pack();
        f.setVisible(true);
    }
    
    private static void getScores() {
        HighScoreScreen hss = new HighScoreScreen();
        f.add(hss);
        JButton back = new JButton("BACK");
        hss.setLayout(null);
        back.setBounds(30, 350, 100, 30);
        back.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                hss.removeAll();
                presentOptions();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        hss.add(back);
        
        f.pack();
        f.setVisible(true);
    }
    
    private static void getCredits() {
        CreditsScreen cs = new CreditsScreen();
        f.add(cs);
        JButton back = new JButton("BACK");
        cs.setLayout(null);
        back.setBounds(30, 350, 100, 30);
        back.addMouseListener(new MouseListener() {
            public void mouseClicked(MouseEvent e) {
                cs.removeAll();
                presentOptions();
            }
                    
            public void mousePressed(MouseEvent e) {}
            public void mouseReleased(MouseEvent e) {}
            public void mouseEntered(MouseEvent e) {}
            public void mouseExited(MouseEvent e) {}
        });
        cs.add(back);
        
        f.pack();
        f.setVisible(true);
    }
}
