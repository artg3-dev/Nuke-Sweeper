/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import nukesweeper.Engine.Game;
import nukesweeper.GUI.Actions.NewGameAction;
import nukesweeper.GUI.GameGui.GamePanel;
import nukesweeper.GUI.MenuBar.NukesweeperMenu;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NukesweeperGUI implements Runnable {
    private NukesweeperMenu menuBar;
    private GamePanel gamePanel;
    private JFrame frame;
    public static InputStream compFontIO
            = NukesweeperGUI.class.getResourceAsStream("Fonts/Computerfont.ttf");
    public static InputStream techFontIO
            = NukesweeperGUI.class.getResourceAsStream("Fonts/Technology.ttf");
    
    public void newGame(Game newGame) {
        Container container = frame.getContentPane();
        container.remove(gamePanel);
        gamePanel = new GamePanel(newGame);
        container.add(gamePanel);
        frame.pack();
    }

    @Override
    public void run() {
        createFont(techFontIO);
        createFont(compFontIO);
        frame = new JFrame("Nuke Sweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setResizable(false);
        createComponents();
        frame.setJMenuBar(menuBar);
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents() {
        // Content setup
        Container container = frame.getContentPane();
        container.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 20));
        container.setBackground(Color.DARK_GRAY);
        gamePanel = new GamePanel(new Game(Game.BEGINNER));
        container.add(gamePanel);
        
        // Menu Setup
        NewGameAction newGameAction = new NewGameAction(this, frame);
        menuBar = new NukesweeperMenu();
        menuBar.addNewGameAction(newGameAction);
    }

    private void createFont(InputStream io) {
        try {
            //create the font to use. Specify the size!
            Font customFont = Font.createFont(
                    Font.TRUETYPE_FONT, io).deriveFont(12f);
            GraphicsEnvironment ge = 
                    GraphicsEnvironment.getLocalGraphicsEnvironment();
            //register the font
            ge.registerFont(customFont);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (FontFormatException e) {
            e.printStackTrace();
        }
    }
//    //testing
//    private void printFonts () {
//        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
//        Font[] fonts = ge.getAllFonts();
//        for (int i = 0; i < fonts.length; i ++) {
//            if (fonts[i].getName().equals("Technology Regular") || fonts[i].getName().equals("Computerfont Regular")) {
//                System.out.println(fonts[i]);
//            }
//        }
//    }
}
