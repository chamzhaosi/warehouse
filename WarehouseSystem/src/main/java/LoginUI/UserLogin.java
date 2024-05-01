/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LoginUI;

import FrameView.FrameC;
import javax.swing.JFrame;

/**
 * @author Cham Zhao Si
 * @version 
 */
public class UserLogin {
    public void onCreate(){
        FrameC frame = new FrameC("Warehouse Management System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        frame.setSize(1500,800);
        
        frame.setPicture();
        
        frame.setVisible(true);
        
   }
    
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(()-> new UserLogin().onCreate());
    }
}
