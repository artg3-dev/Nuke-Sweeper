/*
 * Copyright (C) 2020 Art Garcia (artg3.dev@gmail.com)
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package nukesweeper.GUI.Dialogs;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import nukesweeper.Engine.Game;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NewGamePanel extends JPanel implements ActionListener, ChangeListener {
    private JRadioButton beginner, intermediate, expert, custom;
    private JSpinner width, height, nukeCount;
    private SpinnerNumberModel widthModel, heightModel, nukeModel;
    private JLabel wLabel, hLabel, nLabel;
    private int maxNukes;

    public NewGamePanel() {
        maxNukes = 80;
        createComponents();
        layoutComponents();
        customEnabled(false);
    }
    
    public Game getGame() {
        if (beginner.isSelected()) {
            return new Game(Game.BEGINNER);
        } else if (intermediate.isSelected()) {
            return new Game(Game.INTERMEDIATE);
        } else if (expert.isSelected()) {
            return new Game(Game.EXPERT);
        } else { // Custom
            int gridWidth = (Integer) width.getModel().getValue();
            int gridHeight = (Integer) height.getModel().getValue();
            int nukes = (Integer) nukeCount.getModel().getValue();
            return new Game(WIDTH, HEIGHT, nukes);
        }
    }

    private void createComponents() {
        // Buttons
        ButtonGroup group = new ButtonGroup();

        beginner = new JRadioButton("Beginner");
        beginner.addActionListener(this);
        beginner.setSelected(true);
        group.add(beginner);

        intermediate = new JRadioButton("Intermediate");
        intermediate.addActionListener(this);
        group.add(intermediate);

        expert = new JRadioButton("Expert");
        expert.addActionListener(this);
        group.add(expert);

        custom = new JRadioButton("Custom");
        custom.addActionListener(this);
        group.add(custom);
        
        // Labels
        wLabel = new JLabel("Grid Width");
        hLabel = new JLabel("Grid Height");
        nLabel = new JLabel("Nuke Count");

        // Spinners
        widthModel = new SpinnerNumberModel(9, 3, 30, 1);

        width = new JSpinner(widthModel);
        width.addChangeListener(this);

        heightModel = new SpinnerNumberModel(9, 3, 30, 1);
        height = new JSpinner(heightModel);
        height.addChangeListener(this);

        nukeModel = new SpinnerNumberModel(10, 1, maxNukes, 1);
        nukeCount = new JSpinner(nukeModel);
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c;
        
        // Beginner
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(beginner, c);

        // Inter
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(intermediate, c);

        // Advance
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(expert, c);

        // Custom
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 2;
        c.anchor = GridBagConstraints.LINE_START;
        add(custom, c);
        
        // Separator
        JSeparator separator = new JSeparator(SwingConstants.HORIZONTAL);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.gridwidth = 2;
        c.fill = GridBagConstraints.HORIZONTAL;
        c.insets = new Insets(0, 0, 5, 0);
        add(separator, c);

        // Width
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 0);
        add(wLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(width, c);

        // Height
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 0);
        add(hLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(height, c);

        // Nuke Count
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 7;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 0);
        add(nLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 7;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(0, 5, 5, 5);
        c.fill = GridBagConstraints.HORIZONTAL;
        add(nukeCount, c);
    }
    
    private void customEnabled(boolean isVisible) {
        width.setEnabled(isVisible);
        height.setEnabled(isVisible);
        nukeCount.setEnabled(isVisible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(beginner)) {
            width.getModel().setValue(9);
            height.getModel().setValue(9);
            nukeCount.getModel().setValue(10);
            customEnabled(false);
        }
        if (e.getSource().equals(intermediate)) {
            width.getModel().setValue(16);
            height.getModel().setValue(16);
            nukeCount.getModel().setValue(40);
            customEnabled(false);
        }
        if (e.getSource().equals(expert)) {
            width.getModel().setValue(30);
            height.getModel().setValue(16);
            nukeCount.getModel().setValue(99);
            customEnabled(false);
        }
        if (e.getSource().equals(custom)) {
            customEnabled(true);
        }
    }

    @Override
    public void stateChanged(ChangeEvent e) {
        int gridWidth = (Integer) width.getModel().getValue();
        int gridHeight = (Integer) height.getModel().getValue();
        maxNukes = (gridWidth * gridHeight) - 1;
        nukeModel.setMaximum(maxNukes);
        int currentNuke = (Integer) nukeCount.getModel().getValue();
        if (currentNuke > maxNukes) {
            nukeCount.getModel().setValue(maxNukes);
        }
    }
}

class Testing implements Runnable {

    public static void main(String[] args) {
        Testing test = new Testing();
        SwingUtilities.invokeLater(test);
    }

    @Override
    public void run() {
        JFrame frame = new JFrame("TEST");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.getContentPane().add(new NewGamePanel());
        frame.pack();
        frame.setVisible(true);
    }
}
