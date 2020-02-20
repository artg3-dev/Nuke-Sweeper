/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI.Node;

import nukesweeper.GUI.Icons.IconLoader;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;
import nukesweeper.Engine.Exceptions.NukeFoundException;
import nukesweeper.Engine.Node;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NodeButton extends JButton implements MouseListener, ActionListener {

    private final Color backgroundColor = Color.decode("#000000");
    private final Color checkedBackgroundColor = Color.decode("#2D2D2D");
    private final Color borderDark = Color.decode("#1D1D1D");
    private final Color borderLight = Color.decode("#4D4D4D");
    private final int iconScale = 61;

    private final Node node;
    private boolean flagged;
    private final IconLoader flag, nuke;

    public NodeButton(Node node) {
        super();
        super.setContentAreaFilled(false);
        this.node = node;
        this.flagged = false;
        this.flag = new IconLoader("nuclear.png");
        this.nuke = new IconLoader("missile.png");
        format();
    }

    public boolean isFlagged() {
        return flagged;
    }

    private void format() {
        try {
            flag.load();
            nuke.load();
        } catch (IOException e) {
        }
        addMouseListener(this);
        addActionListener(this);
        setFocusable(false);
        setBackground(backgroundColor);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.RAISED, borderLight, borderDark));
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed()) {
//            g.setColor(Color.pink);
            g.setColor(getBackground());
        } else if (getModel().isRollover()) {
            g.setColor(getBackground());
        } else {
            g.setColor(getBackground());
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }

    @Override
    public boolean equals(Object inQuestion) {
        if (inQuestion == null) {
            return false;
        }

        if (getClass() != inQuestion.getClass()) {
            return false;
        }

        NodeButton compared = (NodeButton) inQuestion;
        return this.node.equals(compared.node);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 13 * hash + Objects.hashCode(this.node);
        return hash;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (e.getButton() == MouseEvent.BUTTON3 && e.getClickCount() == 1) {
            if (flagged) {
                flagged = false;
                setIcon(null);
            } else if (!node.wasChecked()) {
                flagged = true;
                setIcon(flag.getIcon(iconScale));
            }
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            node.check();
        } catch (NukeFoundException ex) {
        }
        setBackground(checkedBackgroundColor);
        setBorder(BorderFactory.createBevelBorder(BevelBorder.LOWERED, borderLight, borderDark));
        setIcon(nuke.getIcon(iconScale));
    }
}
