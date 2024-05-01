/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginUI;

import FrameView.FrameC;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

/**
 * @author Cham Zhao Si
 * @version 
 */
public class UserLogin {
    public void onCreate(){
        FrameC frame = new FrameC("Warehouse Management System");
        
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setBounds(200, 170, 1000, 500);
        
        frame.setVisible(true);
        
   }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()-> new UserLogin().onCreate());
    }
}
