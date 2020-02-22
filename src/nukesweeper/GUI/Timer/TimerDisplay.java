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
package nukesweeper.GUI.Timer;

import java.awt.Color;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class TimerDisplay extends JPanel{
    private final Color borderDark = Color.decode("#1D1D1D");
    private final Color borderLight = Color.decode("#4D4D4D");
    private final TimerLogic timer;
    private JLabel display;

    public TimerDisplay() {
        this.timer = new TimerLogic();
        createComponents();
    }
    
    public void tick() {
        timer.increase();
        display.setText(timer.getTime());
    }
    
    private void createComponents() {
        display = new JLabel(timer.getTime());
        display.setOpaque(true);
        display.setBackground(Color.black);
        display.setForeground(Color.red);
        display.setBorder(BorderFactory.createBevelBorder(
                BevelBorder.LOWERED, borderLight, borderDark));
        add(display);
    }
}
