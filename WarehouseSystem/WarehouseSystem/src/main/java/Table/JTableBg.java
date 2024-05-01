/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Table;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;
import javax.swing.table.TableModel;

/**
 *
 * @author user
 */
public class JTableBg extends JTable{

    public JTableBg(TableModel dm) {
        super(dm);
    }
    
    @Override
    public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
        Component comp = super.prepareRenderer(renderer, row, column);
        Color alternateColor = new Color(200, 201, 210);
        Color whiteColor = Color.WHITE;
        if(!comp.getBackground().equals(getSelectionBackground())) {
           Color c = (row % 2 == 0 ? alternateColor : whiteColor);
           comp.setBackground(c);
           c = null;
        } 
        
//        Object price = getModel().getValueAt(row, column);
//        if (price instanceof Double && !getModel().getColumnName(column).equals("Quantity")){
//            Double price_value = (Double)price;
//            if (price_value > 500 ){
//                comp.setBackground(new Color(230, 123, 96));
//            }else {
//                comp.setBackground(new Color(142, 237, 168));
//            }
//        }
        
        return comp;
     }
}
