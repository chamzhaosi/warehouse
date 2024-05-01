/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testClas;

import javax.swing.DefaultListModel;
import javax.swing.JFrame;
import javax.swing.JList;

/**
 *
 * @author user
 */
public class Test {
    public static void main(String[] args) {
        JFrame frame = new JFrame("frame");
        //JScrollPane panel = null;
        DefaultListModel<Person> list = new DefaultListModel<>();
        list.addElement(new Person("Cham" , 12));
        list.addElement(new Person("Lim" , 13));
        list.addElement(new Person("Khoo" , 25));
        
        JList l = new JList(list);
        frame.add(l);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300,500);
        frame.setVisible(true);
    }
}
