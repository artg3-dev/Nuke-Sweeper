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
package nukesweeper.GUI.Dialogs.Components;

import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class IntegerTextField extends JTextField implements DocumentListener{
    private Color foregroundColor;
    private Color warningColor;
    
    public IntegerTextField() {
        format();
    }
    
    public void setWarningColor(Color color) {
        this.warningColor = color;
    }
    
    private void format() {
        setColumns(3);
        foregroundColor = getForeground();
        warningColor = Color.red;
        getDocument().addDocumentListener(this);
    }
    
    private void checkValidity(String text) {
        try {
            Integer.parseInt(text);
            setForeground(foregroundColor);
        } catch (NumberFormatException e) {
            setForeground(warningColor);
        }
    }

    @Override
    public void setForeground(Color fg) {
        super.setForeground(fg);
        foregroundColor = fg;
    }
    
    @Override
    public void insertUpdate(DocumentEvent e) {
        checkValidity(getText());
    }

    @Override
    public void removeUpdate(DocumentEvent e) {
        checkValidity(getText());
    }

    @Override
    public void changedUpdate(DocumentEvent e) {
    }
}
