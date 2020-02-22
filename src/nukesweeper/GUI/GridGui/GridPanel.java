/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI.GridGui;

import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
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

    private final Color backgroundColor = Color.decode("#9EA49E");

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
        // Panel stuff
        setBackground(backgroundColor);
        setLayout(new GridBagLayout());

        // Creates buttons
        for (int i = 0; i < grid.width; i++) {
            for (int j = 0; j < grid.height; j++) {
                // Layout stuff
                int insetConstant = 2;
                int top = 0;
                int left = 0;
                int bottom = insetConstant;
                int right = insetConstant;
                if (i == 0) {
                    left = insetConstant;
                }
                if (j == 0) {
                    top = insetConstant;
                }
                GridBagConstraints c = new GridBagConstraints();
                c.insets = new Insets(top, left, bottom, right);
                c.gridx = i;
                c.gridy = j;

                // NodeButton stuff
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
        Node node = button.getNode();
        if (game.hasStarted()) {
            if (!button.isFlagged()) {
                try {
                    game.checkNode(node);
                    revealCheckedNodes();
                } catch (NukeFoundException ex) {
                    // DO THE NUKE FOUND METHOD!
                    revealAllNukes();
                }
            }
        } else {
            game.start(node);
            actionPerformed(e);
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
