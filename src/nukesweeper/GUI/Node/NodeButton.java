/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI.Node;

import nukesweeper.GUI.Icons.IconLoader;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;
import java.util.Objects;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import nukesweeper.Engine.Node;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class NodeButton extends JButton implements MouseListener {

    private final Color[] textColors = new Color[]{
        Color.decode("#5544FF"), // 1
        Color.decode("#23B200"), // 2
        Color.decode("#FF6D6D"), // 3
        Color.decode("#AE00FF"), // 4 
        Color.decode("#F41818"), // 5
        Color.decode("#19D5B2"), // 6
        Color.decode("#D1FF00"), // 7
        Color.decode("#FFFFFF") // 8
    };

    private final Color backgroundColor = Color.decode("#000000");
    private final Color checkedBackgroundColor = Color.decode("#2D2D2D");
    private final Color borderDark = Color.decode("#1D1D1D");
    private final Color borderLight = Color.decode("#4D4D4D");
    private final Color borderHoverDark = Color.decode("#4D4D4D");
    private final Color borderHoverLight = Color.decode("#6D6D6D");
    private final Border defaultBorder
            = BorderFactory.createBevelBorder(
                    BevelBorder.RAISED, borderLight, borderDark);
    private final Border hoverBorder
            = BorderFactory.createBevelBorder(
                    BevelBorder.RAISED, borderHoverLight, borderHoverDark);
    private final Border checkedBorder
            = BorderFactory.createBevelBorder(
                    BevelBorder.LOWERED, borderLight, borderDark);

    private final Node node;
    private final double scalePercent = 0.7;
    private boolean flagged, isEnabled;
    private final IconLoader flag, nuke;

    public NodeButton(Node node) {
        super();
        super.setContentAreaFilled(false);
        this.node = node;
        this.flagged = false;
        this.isEnabled = true;
        this.flag = new IconLoader("nuclear.png");
        this.nuke = new IconLoader("missile.png");
        format();
    }

    public Node getNode() {
        return this.node;
    }

    public void reveal(int neighborCount) {
        setBackground(checkedBackgroundColor);
        setBorder(checkedBorder);
        if (node.isNuke()) {
            int scale = (int) (getPreferredSize().width * scalePercent);
            setIcon(nuke.getIcon(scale));
        } else {
            if (neighborCount > 0) {
                int colorIndex = neighborCount - 1;
                setText("" + neighborCount);
                setForeground(textColors[colorIndex]);
            }
        }
    }

    public boolean isFlagged() {
        return flagged;
    }

    private void format() {
        this.setFont(new Font("Technology", Font.PLAIN, 20));
        this.setHorizontalTextPosition(SwingConstants.CENTER);
        this.setVerticalTextPosition(SwingConstants.CENTER);
        try {
            flag.load();
            nuke.load();
        } catch (IOException e) {
        }
        addMouseListener(this);
        setFocusable(false);
        setBackground(backgroundColor);
        setBorder(defaultBorder);
        setPreferredSize(new Dimension(25, 25));
    }

    @Override
    public void setEnabled(boolean isEnabled) {
        this.isEnabled = isEnabled;
    }

    @Override
    protected void paintComponent(Graphics g) {
        if (getModel().isPressed() && isEnabled) {
            g.setColor(getBackground());
            if (!node.wasChecked()) {
                setBorder(defaultBorder);
            }
        } else if (getModel().isRollover() && isEnabled) {
            g.setColor(getBackground());
            if (!node.wasChecked()) {
                setBorder(hoverBorder);
            }
        } else {
            g.setColor(getBackground());
            if (!node.wasChecked()) {
                setBorder(defaultBorder);
            } else {
                setBorder(checkedBorder);
            }
        }
        g.fillRect(0, 0, getWidth(), getHeight());
        super.paintComponent(g);
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
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if (this.getMousePosition() != null && isEnabled) {
            if (SwingUtilities.isRightMouseButton(e)) {
                if (flagged) {
                    flagged = false;
                    setIcon(null);
                } else if (!node.wasChecked()) {
                    flagged = true;
                    int scale = (int) (getPreferredSize().width * scalePercent);
                    setIcon(flag.getIcon(scale));
                }
            }
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    @Override
    public void setContentAreaFilled(boolean b) {
    }
}
