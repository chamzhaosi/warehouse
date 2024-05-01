/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContainerView;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class BackgroundPanel extends JPanel{
    private static BufferedImage read = null;
    
    public BackgroundPanel(InputStream imagePath){
        try {
             read = ImageIO.read(imagePath);
         } catch (Exception ex) {
             ex.printStackTrace();
         }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(read, 0, 0, getSize().width, getSize().height, null);
    }
}
