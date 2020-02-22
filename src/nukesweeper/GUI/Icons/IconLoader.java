/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package nukesweeper.GUI.Icons;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

/**
 *
 * @author Art Garcia (artg3.dev@gmail.com)
 */
public class IconLoader {
    private final String iconName;
    private ImageIcon icon;
    private Image image;
    
    public IconLoader(String iconName) {
        this.iconName = iconName;
    }
    
    public void load() throws IOException{
        this.image = ImageIO.read(getClass().getResource(iconName));
        this.icon = new ImageIcon(image);
    }
    
    public ImageIcon getIcon(int scale) {
        icon.setImage(scaleImage(scale, scale));
        return icon;
    }
    
    public ImageIcon getIcon(int width, int height) {
        icon.setImage(scaleImage(width, height));
        return icon;
    }
    
    private Image scaleImage(int width, int height) {
        return image.getScaledInstance(width, height, Image.SCALE_SMOOTH);
    }
}
