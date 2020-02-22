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
package nukesweeper.GUI.Actions;

import java.awt.event.ActionEvent;
import javax.swing.AbstractAction;
import javax.swing.JFrame;
import nukesweeper.Engine.Game;
import nukesweeper.GUI.Dialogs.NewGameDialog;
import nukesweeper.GUI.GameGui.GamePanel;
import nukesweeper.GUI.NukesweeperGUI;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NewGameAction extends AbstractAction{
    private final NukesweeperGUI gui;
    private final JFrame frame;

    public NewGameAction(NukesweeperGUI gui, JFrame frame) {
        this.gui = gui;
        this.frame = frame;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        NewGameDialog dialog = new NewGameDialog(frame);
        Game newGame = dialog.getNewGame();
        if (newGame != null) {
            gui.newGame(newGame);
        }
    }
    
}
