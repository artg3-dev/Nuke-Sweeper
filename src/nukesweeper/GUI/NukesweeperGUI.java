/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI;

import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.GraphicsEnvironment;
import java.io.IOException;
import java.io.InputStream;
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import nukesweeper.Engine.Game;
import nukesweeper.GUI.GameGui.GamePanel;
import nukesweeper.GUI.MenuBar.NukesweeperMenu;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NukesweeperGUI implements Runnable {

    public static InputStream compFontIO
            = NukesweeperGUI.class.getResourceAsStream("Fonts/Computerfont.ttf");
    public static InputStream techFontIO
            = NukesweeperGUI.class.getResourceAsStream("Fonts/Technology.ttf");

    @Override
    public void run() {
        createFont(techFontIO);
        createFont(compFontIO);
        JFrame frame = new JFrame("Nuke Sweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        createComponents(frame.getContentPane());
        frame.setJMenuBar(new NukesweeperMenu());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setBackground(Color.DARK_GRAY);
        GamePanel gamePanel = new GamePanel();
        container.add(gamePanel);
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
