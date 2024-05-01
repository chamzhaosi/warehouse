/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ContainerView;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class PicturePanel extends JPanel{
    private static String filePath = null;
    private static BufferedImage image = null;
    
    String copyFilePath = "..\\WarehouseSystem\\src\\main\\logo\\logo.jpg";
    private File file = new File(copyFilePath);
    
    public boolean hasPicture(){
        return file.exists();
    }
    
    public void getPicturePath(){
        JFileChooser fc = new JFileChooser();
        int val = fc.showOpenDialog(null);
        if(val==fc.APPROVE_OPTION){
            filePath = fc.getSelectedFile().toString();
        }

        if (filePath==null || !filePath.endsWith(".jpg") || !new File(filePath).exists()){
            JOptionPane.showMessageDialog(null, "Wrong selected file,Please select jpg format image!!!", "Error",0);
            filePath = null;
        }else{
            getPicture();
        }
    }
    
    private void getPicture(){ 
        try {
            copyImage();
            image = ImageIO.read(file);
            this.repaint();
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    
    private void copyImage(){
        FileInputStream fis = null;
        FileOutputStream fod = null;
        
        try {
            fis = new FileInputStream(filePath);
            
            byte[] readAllBytes = fis.readAllBytes();
            
            fod = new FileOutputStream(file);
            
            fod.write(readAllBytes);
            
        } catch (FileNotFoundException ex) {
            ex.printStackTrace();
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally {
            try {
                fis.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
            try {
                fod.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }       
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        int width = this.getWidth();
        int height = this.getHeight();
        g.setColor(new Color(52, 73, 94,80));
        g.fillRect(0, 0, width, height);
        g.drawRect(0, 0, width-1, height-1);
        if (hasPicture()){
            try {
                image = ImageIO.read(file);
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
        g.drawImage(image, 0, 0, width, height, null);
    }
    
}
