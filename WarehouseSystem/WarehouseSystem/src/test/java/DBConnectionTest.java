
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
import java.io.InputStream;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JTabbedPane;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author user
 */
public class DBConnectionTest {
    
//    @Test
//    public void testDB(){
//        String convertToCipher = FrameC.convertToCipher("1234");
//        
//        System.out.println(convertToCipher);
//    }
//    
//    
//    @Test
//    public void testDB1(){
//        List<UserPwd> dbNamePassword = FrameC.getDBNamePassword();
//        
//        System.out.println(dbNamePassword);
//    }
    
//    @Test
//    public void test1(){
//        new NewRegisterFrame().updatePropertiesFile("cham", "1234");
//    }


    public static void main(String[] args){
        //NewPersonFrame frame = new NewPersonDialog("test supplier panel");
//        javax.swing.SwingUtilities.invokeAndWait(new Runnable(){
//            @Override
//            public void run() {
                JFrame frame = new JFrame("Supplier List");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setBounds(300, 40, 1200, 800);
                
                //EmployeePanel ep = new EmployeePanel();
                //SupplierPanel sp = new SupplierPanel();
                //CustomerPanel cp = new CustomerPanel();
                InventoryPanel invp = new InventoryPanel();
                //PurchasePanel pp = new PurchasePanel();
                //ReceivedPanel rp = new ReceivedPanel();
                //SalesPanel sp = new SalesPanel();
                //InitialInventoryPanel ip = new InitialInventoryPanel();
                //HomePanel hp = new HomePanel();
                //ExportPanel ep = new ExportPanel();
                //DeletePanel dp = new DeletePanel();

                JTabbedPane tabbedPane = new JTabbedPane();
                tabbedPane.addTab("Inventory",null, invp);
                //tabbedPane.addTab("Purchase", null, pp);
                //tabbedPane.addTab("Receiving", null, rp);
                //tabbedPane.addTab("Employee",null, ep);
                //tabbedPane.addTab("Supplier", null, sp);
                //tabbedPane.addTab("Customer", null, cp);
                //tabbedPane.addTab("Sales", null, sp);
                //tabbedPane.addTab("initialization", ip);
                //tabbedPane.addTab("Home Page", hp);
                //tabbedPane.addTab("Export Page", ep);
                //tabbedPane.addTab("Delete", dp);
                
                tabbedPane.addChangeListener(new ChangeListener(){
                    @Override
                    public void stateChanged(ChangeEvent e) {
                        JTabbedPane name = (JTabbedPane)e.getSource();
                        switch(name.getSelectedIndex()){
                            //case 0:invp.updateInventory(); break;
                            //case 0:pp.updateNewOrderInfo();break;
                            //case 1:rp.setPOComboxItem();break;
                            //case 3:ep.updateNewOrderInfo(); break;
                            //case 2:sp.updateNewOrderInfo();break;
                            //case 5:cp.updateNewOrderInfo();break;
                            //case 3:sp.updateNewOrderInfo();sp.updateNewInvoiceInfo(); sp.updateNewReturnInfo(); break;
                            //case 4: ip.updateInitialInventory(); break;
                            //case 3:hp.updateInventory(); hp.updateAllDBInfo();break;
                            //case 0:ep.updateAllDBInfo(); ep.setAllComBoxItem(); break;
                        }
                    }
                
                });

                frame.add(tabbedPane);
                frame.setVisible(true);
//            }
//        });

    }
    
//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Supplier List");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//
//        JMenuBar menuBar = new JMenuBar();
//        
//        JMenu inventoryMenu = new JMenu("Inventory(I)");
//        inventoryMenu.setMnemonic(KeyEvent.VK_I);
//        inventoryMenu.addMenuListener(new MenuListener(){
//            @Override
//            public void menuSelected(MenuEvent e) {
//                inventoryMenu.add
//            }
//
//            @Override
//            public void menuDeselected(MenuEvent e) {
//                
//            }
//
//            @Override
//            public void menuCanceled(MenuEvent e) {
//                
//            }
//        
//        });
//        menuBar.add(inventoryMenu);
//
//        JMenu customerMenu = new JMenu("Customer(C)");
//        customerMenu.setMnemonic(KeyEvent.VK_C);
//        customerMenu.addMenuListener(new MenuListener(){
//            @Override
//            public void menuSelected(MenuEvent e) {
//                customerMenu.add(new CustomerPanel());
//            }
//
//            @Override
//            public void menuDeselected(MenuEvent e) {
//                
//            }
//
//            @Override
//            public void menuCanceled(MenuEvent e) {
//                
//            }
//        
//        });
//        menuBar.add(customerMenu);
//
//        frame.setJMenuBar(menuBar);
//        frame.setBounds(300, 40, 1200, 800);
//        frame.setVisible(true);
//    }
//    
}
