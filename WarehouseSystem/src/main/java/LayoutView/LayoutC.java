/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package LayoutView;

import java.awt.Component;
import java.awt.Container;
import java.awt.LayoutManager2;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author user
 */
public class LayoutC extends LayoutAdapter{
    List<Component> list = new ArrayList<>();
        
    @Override
    public void addLayoutComponent(Component comp, Object constraints) {
        list.add(comp);
    }

    @Override
    public void removeLayoutComponent(Component comp) {
        list.remove(comp);
    }

    @Override
    public void layoutContainer(Container parent) {
        //Get the frame size
        int width = parent.getSize().width;
        int height = parent.getSize().height;
        
        //Count the location x, y
        int x = (int)Math.sqrt(width);
        int y = height/2/4;
        
        //Count the container size
        int c_width=(width/2-50);
        int c_height=(height/2+(height/2/2));
        
        //System.out.println("Layout" + width +" / "+ height);
        
        for (Component c : list){
            c.setBounds(x, y, c_width, c_height);
            //System.out.println(x);
            
            //the distance between two containers is 30pixs
            x+=(c_width+30);
        }
    }
    
}
