/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrameView;

import LayoutView.LayoutC;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;
import javax.swing.JPanel;

/**
 *
 * @author user
 */
public class FrameC extends JFrame{
    public FrameC (String title){
        super(title);
    }
    
    public void setPicture(){
        JPanel root = new JPanel();
        this.setContentPane(root);
        root.setBackground(Color.GRAY);
        root.setLayout(new LayoutC());
        
        JPanel pict = new JPanel();
        pict.setBackground(Color.red);
        
        JPanel pict1 = new JPanel();
        pict1.setBackground(Color.YELLOW);
        
        root.add(pict);
        root.add(pict1);
        
        int f_width = this.getSize().width;
        int f_height = this.getSize().height;
        
        System.out.println(f_width +" / "+ f_height);
        
        
    }
}
