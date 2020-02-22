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

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NewGamePanel extends JPanel implements ActionListener, ChangeListener {

    private final String beginnerInfo
            = "A Beginner Game is a 9x9 grid with 10 Nukes.";
    private final String intermediateInfo
            = "An Intermediate Game is a 16x16 grid with 40 Nukes.";
    private final String advancedInfo
            = "An Advanced Game is a 16x30 grid with 99 Nukes.";
    private final String customInfo
            = "A Custom Game can be up to 30 cells wide or tall, "
            + "with up to 200 nukes";
    private JRadioButton beginner, intermediate, advanced, custom;
    private JSpinner width, height, nukeCount;
    private SpinnerNumberModel widthModel, heightModel, nukeModel;
    private JLabel wLabel, hLabel, nLabel;
    private JTextArea info;
    private int maxNukes;

    public NewGamePanel() {
        maxNukes = 8;
        createComponents();
        layoutComponents();
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

        advanced = new JRadioButton("Advanced");
        advanced.addActionListener(this);
        group.add(advanced);

        custom = new JRadioButton("Custom");
        custom.addActionListener(this);
        group.add(custom);
        
        // Labels
        wLabel = new JLabel("Grid Width");
        wLabel.setVisible(false);
        hLabel = new JLabel("Grid Height");
        hLabel.setVisible(false);
        nLabel = new JLabel("Nuke Count");
        nLabel.setVisible(false);

        // Spinners
        widthModel = new SpinnerNumberModel(3, 3, 30, 1);

        width = new JSpinner(widthModel);
        width.addChangeListener(this);
        width.setVisible(false);

        heightModel = new SpinnerNumberModel(3, 3, 30, 1);
        height = new JSpinner(heightModel);
        height.addChangeListener(this);
        height.setVisible(false);

        nukeModel = new SpinnerNumberModel(1, 1, maxNukes, 1);
        nukeCount = new JSpinner(nukeModel);
        nukeCount.setVisible(false);

        // Info
        info = new JTextArea(beginnerInfo);
        info.setColumns(15);
        info.setRows(3);
        info.setLineWrap(true);
        info.setWrapStyleWord(true);
        info.setMargin(new Insets(2, 2, 2, 2));
    }

    private void layoutComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c;
        
        // Beginner
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.LINE_START;
        add(beginner, c);

        // Inter
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.LINE_START;
        add(intermediate, c);

        // Advance
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 2;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.LINE_START;
        add(advanced, c);

        // Custom
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 3;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.LINE_START;
        add(custom, c);

        // Width Spinner
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 30, 0, 5);
        add(wLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 4;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 2;
        c.insets = new Insets(5, 0, 0, 5);
        add(width, c);

        // Height Spinner
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 30, 0, 5);
        add(hLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 5;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 2;
        c.insets = new Insets(5, 0, 0, 5);
        add(height, c);

        // Nuke Count
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(5, 30, 0, 5);
        add(nLabel, c);
        
        c = new GridBagConstraints();
        c.gridx = 1;
        c.gridy = 6;
        c.anchor = GridBagConstraints.LINE_START;
        c.gridwidth = 2;
        c.insets = new Insets(5, 0, 0, 5);
        add(nukeCount, c);
        
        // Info
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 7;
        c.gridwidth = 3;
        c.anchor = GridBagConstraints.LINE_START;
        c.insets = new Insets(10, 0, 5, 0);
        add(info, c);
    }
    
    private void customVisible(boolean isVisible) {
        wLabel.setVisible(isVisible);
        width.setVisible(isVisible);
        hLabel.setVisible(isVisible);
        height.setVisible(isVisible);
        nLabel.setVisible(isVisible);
        nukeCount.setVisible(isVisible);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(beginner)) {
            info.setText(beginnerInfo);
            customVisible(false);
        }
        if (e.getSource().equals(intermediate)) {
            info.setText(intermediateInfo);
            customVisible(false);
        }
        if (e.getSource().equals(advanced)) {
            info.setText(advancedInfo);
            customVisible(false);
        }
        if (e.getSource().equals(custom)) {
            info.setText(customInfo);
            customVisible(true);
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
