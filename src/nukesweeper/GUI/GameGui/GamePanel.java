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
package nukesweeper.GUI.GameGui;

import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JPanel;
import javax.swing.Timer;
import nukesweeper.Engine.Game;
import nukesweeper.Engine.Node;
import nukesweeper.GUI.CustomListeners.GameStartListener;
import nukesweeper.GUI.CustomListeners.NukeFoundListener;
import nukesweeper.GUI.GridGui.GridPanel;
import nukesweeper.GUI.Timer.TimerDisplay;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */

public class GamePanel extends JPanel 
        implements ActionListener, GameStartListener, NukeFoundListener{
    private Game game;
    private GridPanel grid;
    private final TimerDisplay timeDisplay;
    private final Timer timer;

    public GamePanel(Game game) {
        this.game = game;
        this.grid = new GridPanel(this.game);
        this.timeDisplay = new TimerDisplay();
        this.timer = new Timer(1000, this);
        createComponents();
    }

    private void createComponents() {
        setLayout(new GridBagLayout());
        GridBagConstraints c;
        
        // Timer
        c = new GridBagConstraints();
        c.gridx = 2;
        c.gridy = 0;
        c.anchor = GridBagConstraints.LINE_END;
        add (timeDisplay, c);
        
        // Grid
        grid.addGameStartListener(this);
        grid.addNukeFoundListener(this);
        c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 1;
        c.gridwidth = 3;
        add(grid, c);
    }
    
    @Override
    public void startGame(Node startingNode) {
        game.start(startingNode);
        timer.start();
    }
    
    @Override
    public void nukeFound() {
        timer.stop();
        grid.revealAllNukes();
        grid.setEnabled(false);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        timeDisplay.tick();
    }
}