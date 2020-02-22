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
package nukesweeper.GUI.MenuBar;

import com.sun.glass.events.KeyEvent;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;
import nukesweeper.GUI.Actions.NewGameAction;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NukesweeperMenu extends JMenuBar{
    private JMenu gameMenu;
    private JMenuItem newGame;
    public NukesweeperMenu() {
        super();
        createComponents();
    }
    
    public void addNewGameAction(NewGameAction action) {
        newGame.setAction(action);
        newGame.setText("New Game");
    }
    
    private void createComponents() {
        gameMenu = new JMenu("Game");
        newGame = new JMenuItem("New Game");
        newGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F2, 0));
        gameMenu.add(newGame);
        add(gameMenu);
    }
}
