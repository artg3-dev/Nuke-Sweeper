/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI.GridGui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import nukesweeper.Engine.Exceptions.NukeFoundException;
import nukesweeper.Engine.Game;
import nukesweeper.Engine.Grid;
import nukesweeper.Engine.Node;
import nukesweeper.GUI.Node.NodeButton;
import nukesweeper.GUI.NukesweeperGUI;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class GridPanel extends JPanel implements ActionListener {

    private final Game game;
    private final Grid grid;
    private final ArrayList<NodeButton> nodeButtons;

    public GridPanel(Game game) {
        this.game = game;
        this.grid = game.getGrid();
        this.nodeButtons = new ArrayList();
        createComponents();
    }
    
    private void revealCheckedNodes() {
        for (NodeButton i : nodeButtons) {
            Node node = i.getNode();
            if (node.wasChecked()) {
                int neighborCount = grid.getNeighborNukeCount(node);
                i.reveal(neighborCount);
            }
        }
    }
    
    private void revealAllNukes() {
        for (NodeButton i : nodeButtons) {
            Node node = i.getNode();
            if (node.isNuke()) {
                i.reveal(0);
            }
        }
    }

    private void createComponents() {
        // Creates buttons
        setLayout(new GridBagLayout());
        for (int i = 0; i < grid.width; i++) {
            for (int j = 0; j < grid.height; j++) {
                GridBagConstraints c = new GridBagConstraints();
                c.gridx = i;
                c.gridy = j;
                Node node = grid.getNode(i, j);
                NodeButton nodeButton = new NodeButton(node);
                nodeButton.addActionListener(this);
                nodeButtons.add(nodeButton);
                add(nodeButton, c);
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NodeButton button = (NodeButton) e.getSource();
        game.start(button.getNode());
        try {
            game.checkNode(button.getNode());
            revealCheckedNodes();
        } catch (NukeFoundException ex) {
            // DO THE NUKE FOUND METHOD!
            revealAllNukes();
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
