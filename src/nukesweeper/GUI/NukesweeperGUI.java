/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI;

import nukesweeper.GUI.Node.NodeButton;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.WindowConstants;
import nukesweeper.Engine.Node;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NukesweeperGUI implements Runnable {

    @Override
    public void run() {
        JFrame frame = new JFrame("Nuke Sweeper");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setPreferredSize(new Dimension(500, 500));
        createComponents(frame.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }

    private void createComponents(Container container) {
        container.setBackground(Color.DARK_GRAY);
        container.setLayout(new GridLayout(5, 5, 1, 1));
        for (int i = 0; i < 25; i++) {
            Node node = new Node(i, 0);
            container.add(new NodeButton(node));
        }
    }

}

// TESTING
class testing {

    public static void main(String[] args) {
        NukesweeperGUI gui = new NukesweeperGUI();
        SwingUtilities.invokeLater(gui);
    }
}
