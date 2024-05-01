/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrameView;

import PanelView.CustomerPanel;
import PanelView.DeletePanel;
import PanelView.EmployeePanel;
import PanelView.ExportPanel;
import PanelView.HomePanel;
import PanelView.InitialInventoryPanel;
import PanelView.InventoryPanel;
import PanelView.PurchasePanel;
import PanelView.ReceivedPanel;
import PanelView.SalesPanel;
import PanelView.SupplierPanel;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/**
 *
 * @author user
 */
public class MainFrame extends JFrame{
    public MainFrame (String title){
        super(title);
        
        try {
            //this.setIconImage(ImageIO.read(new File ("..\\WarehouseSystem\\src\\main\\logo\\logo_custom.png")));
            this.setIconImage(ImageIO.read(FrameC.class.getClassLoader().getResourceAsStream("logo_custom.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
        
        HomePanel hp = new HomePanel();
        PurchasePanel pp = new PurchasePanel();
        ReceivedPanel rp = new ReceivedPanel();
        SalesPanel sp = new SalesPanel();
        InitialInventoryPanel ip = new InitialInventoryPanel();
        InventoryPanel invp = new InventoryPanel();
        SupplierPanel supp = new SupplierPanel();
        CustomerPanel cp = new CustomerPanel();
        EmployeePanel ep = new EmployeePanel();
        ExportPanel expp = new ExportPanel();
        DeletePanel dp = new DeletePanel();

        JTabbedPane tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Home", hp);
        tabbedPane.addTab("Purchase", pp);
        tabbedPane.addTab("Receive", rp);
        tabbedPane.addTab("Sales", sp);
        tabbedPane.addTab("Initial", ip);
        tabbedPane.addTab("Inventory", invp);
        tabbedPane.addTab("Supplier", supp);
        tabbedPane.addTab("Customer", cp);
        tabbedPane.addTab("Employee", ep);
        tabbedPane.addTab("Export", expp);
        tabbedPane.addTab("Delete", dp);
        
        this.add(tabbedPane);
        
        tabbedPane.addChangeListener(new ChangeListener(){
            @Override
            public void stateChanged(ChangeEvent e) {
                JTabbedPane name = (JTabbedPane)e.getSource();
                switch(name.getSelectedIndex()){
                    case 0:
                        hp.updateAllDBInfo();
                        hp.updateInventory();
                        hp.updateSafety();
                        hp.updateExpired();
                        hp.updateReceive();
                        hp.updateSales();
                        break;
                        
                    case 1: 
                        pp.updateNewOrderInfo();
                        pp.updateNewInvoiceInfo();
                        pp.updateNewReturnInfo();
                        break;
                        
                    case 2:
                        rp.setPOComboxItem();
                        break;
                        
                    case 3:
                        sp.updateNewOrderInfo();
                        sp.updateNewInvoiceInfo(); 
                        sp.updateNewReturnInfo(); 
                        break;
                        
                    case 4:
                        ip.updateInitialInventory();
                        break;
                    
                    case 5:
                        invp.updateInventory();
                        break;
                        
                    case 6:
                        supp.updateNewInfo();
                        break;
                             
                    case 7:
                        cp.updateNewInfo();
                        break;
                        
                    case 8:
                        ep.updateNewInfo();
                        break;
                    
                    case 9:
                        expp.updateAllDBInfo();
                        expp.setAllComBoxItem();
                        break;
                        
                    case 10:
                        dp.updateAllDBInfo();
                        dp.setAllComBoxItem();
                        break;
                }
            }
        });
    }
}
