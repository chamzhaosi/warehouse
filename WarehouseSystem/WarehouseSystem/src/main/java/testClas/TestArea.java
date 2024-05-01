/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testClas;

import java.awt.Graphics;
import javax.swing.JTextArea;

/**
 *
 * @author user
 */
public class TestArea extends JTextArea{

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g); 
        
        g.drawLine(WIDTH, WIDTH, WIDTH, WIDTH);
    }
    
}
