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

import java.awt.Frame;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import nukesweeper.Engine.Game;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NewGameDialog extends JDialog implements PropertyChangeListener{
    private NewGamePanel newGamePanel;
    private JOptionPane optionPane;
    private Game newGame;

    public NewGameDialog(Frame frame) {
        super(frame, true);
        setTitle("Create a new Game");
        String createButtonTxt = "Start";
        Object[] options = {createButtonTxt};
        
        newGamePanel = new NewGamePanel();
        newGame = null;
        optionPane = new JOptionPane(newGamePanel,
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.OK_OPTION,
                null, //icon
                options,
                options[0]);

        setContentPane(optionPane);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        optionPane.addPropertyChangeListener(this);
        pack();
        setVisible(true);
    }

    @Override
    public void propertyChange(PropertyChangeEvent e) {
        String prop = e.getPropertyName();
        if (isVisible()
                && (e.getSource() == optionPane)
                && (JOptionPane.VALUE_PROPERTY.equals(prop))
                || JOptionPane.INPUT_VALUE_PROPERTY.equals(prop)) {
            Object value = optionPane.getValue();

            if (value == JOptionPane.UNINITIALIZED_VALUE) {
                return;
            }
            optionPane.setValue(JOptionPane.UNINITIALIZED_VALUE);

            /*
            * This is where the error list is displayed
            * or the monster is created
             */
            newGame = newGamePanel.getGame();
            dispose();
        }
    }
    
    public Game getNewGame() {
        return newGame;
    }
}
