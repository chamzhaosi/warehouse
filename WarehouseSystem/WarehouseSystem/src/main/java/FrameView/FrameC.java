/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrameView;

import Adapter.ButtonKeyAdapter;
import Adapter.MouseAdapter;
import ContainerView.PicturePanel;
import DB_userPwd.UserPwd;
import ContainerView.BackgroundPanel;
import DBConnector.JDBCTemplate;
import LoginUI.UserLogin;
import UtilsTools.DruidUtils_Root;
import UtilsTools.DruidUtils_User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 *
 * @author user
 */
public class FrameC extends JFrame{
   private PicturePanel pictPanel = null;
   private JPanel loginPanel = null;
   private JPanel rootPanel = null;
   private JPanel userLoginPanel = null;
   private GridBagConstraints gbc = null;
   private JPopupMenu popupMenu =null;
   private JMenuItem upload = null;
   private JTextField user_text = null;
   private JPasswordField password_text = null;
   private JButton loginButton = null;
   
   public FrameC(){
       
   }
   
   public FrameC(String title){
       super(title);
       
       try {
            //this.setIconImage(ImageIO.read(new File ("..\\WarehouseSystem\\src\\main\\logo\\logo_custom.png")));
            this.setIconImage(ImageIO.read(FrameC.class.getClassLoader().getResourceAsStream("logo_custom.png")));
        } catch (IOException ex) {
            ex.printStackTrace();
        }
       
       //rootPanel = new BackgroundPanel("..\\WarehouseSystem\\src\\main\\backgroup\\WMSbg.jpg");
       rootPanel = new BackgroundPanel(FrameC.class.getClassLoader().getResourceAsStream("WMSbg.jpg"));
       this.setContentPane(rootPanel);

       rootPanel.setBorder(new EmptyBorder(70,70,70,70));
       rootPanel.setBackground(new Color(255,255,255));
       rootPanel.setLayout(new GridLayout(1,2,60,60));
       
       setPicture();
       setLoginPanel();
   }
   
   private void setPicture(){
       JLabel pictureHint = new JLabel();

       pictPanel = new PicturePanel();
       rootPanel.add(pictPanel);
       popupMenu = new JPopupMenu();
       pictPanel.add(popupMenu);
       
       pictureHint.setText("Please right click to upload your company logo...");
       pictPanel.add(pictureHint);

       upload = new JMenuItem("Upload");
       popupMenu.add(upload);
       
       pictPanel.addMouseListener(new MouseAdapter(){
       
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getButton()== 3){
                    popupMenu.show(pictPanel, e.getX(), e.getY());
                    if (upload.isSelected() || !upload.isSelected()){
                        //after click the right key button, the popup memu will affect the login panel color
                        //so repaint it after right click
                        loginPanel.repaint();
                    }
               }
            }   
       });
       
       upload.addActionListener(new ActionListener(){
                @Override
                public void actionPerformed(ActionEvent e) {
                    pictPanel.getPicturePath();
                    
                    if (pictPanel.hasPicture()){
                        pictPanel.remove(pictureHint);
                    }
                }
            }); 
           
        if (pictPanel.hasPicture()){
            pictPanel.remove(pictureHint);
        }
   }
   
   private void setLoginPanel(){
       loginPanel = new JPanel();
       loginPanel.setBackground(new Color (109, 191, 237,250));
       loginPanel.setLayout(new BorderLayout());
       rootPanel.add(loginPanel);
       setSystemName("WMS Company");
       setLogin();
       setLoginButton();
   }
   
   private void setSystemName(String name){
       JLabel nameLabel = new JLabel(name,SwingConstants.CENTER);
       nameLabel.setFont(new Font("Ink Free", Font.PLAIN, 60));
       nameLabel.setForeground(new Color (0,0,0));
       loginPanel.add(nameLabel, BorderLayout.NORTH);
   }
   
   private void setLogin(){
       userLoginPanel = new JPanel();
       userLoginPanel.setLayout(new GridBagLayout());
       userLoginPanel.setBackground(new Color(81, 60, 120,80));
       gbc = new GridBagConstraints();
       loginPanel.add(userLoginPanel, BorderLayout.CENTER);
       
       JLabel user = new JLabel("User: ");
       user.setForeground(Color.BLACK);
       user_text = new JTextField(20);
       
       JLabel password = new JLabel("Password: ");
       password.setForeground(Color.BLACK);
       password_text = new JPasswordField(20);
       
       gbc.fill = GridBagConstraints.HORIZONTAL;
       gbc.ipady = 10;
       gbc.gridx = 0;
       gbc.gridy = 0;
       userLoginPanel.add(user, gbc);
       gbc.gridx = 1;
       gbc.gridy = 0;
       userLoginPanel.add(user_text, gbc);
       gbc.insets = new Insets(10,0,0,0);
       gbc.gridx = 0;
       gbc.gridy = 1;
       userLoginPanel.add(password, gbc);
       gbc.gridx = 1;
       gbc.gridy = 1;
       userLoginPanel.add(password_text,gbc);

   }
   
   private void setLoginButton(){
       loginButton = new JButton("Login");
       gbc.fill = GridBagConstraints.VERTICAL;
       gbc.insets = new Insets(20,0,0,0);
       gbc.gridx = 1;
       gbc.gridy = 3;
       userLoginPanel.add(loginButton, gbc);
       userLoginPanel.getRootPane().setDefaultButton(loginButton);
       loginButton.addKeyListener(new ButtonKeyAdapter(){
           @Override
           public void keyPressed(KeyEvent e) {
               if (e.getKeyCode()==KeyEvent.VK_ENTER){
                   checkUsernamePassword();}
           }
       });
       
       loginButton.addActionListener((ActionEvent ev)->checkUsernamePassword());
   }
   
   private void checkUsernamePassword(){
       UserPwd defaultUserPwd = new UserPwd();
       
       defaultUserPwd = getUserPwd();
       String username = defaultUserPwd.getUsername();
       String password = defaultUserPwd.getPassword();
              
       UserPwd dbNamePassword = getDBNamePassword(username);
       //System.out.println(dbNamePassword);
       
       String DBUserName = null;
       String DBPassWord = null;
 
        if (dbNamePassword != null ){
            DBUserName = dbNamePassword.getUsername();
            DBPassWord = dbNamePassword.getPassword();
            //convert the plaintext password to ciphertext
            defaultUserPwd.setPassword(new JDBCTemplate().convertToCipher(defaultUserPwd.getPassword()));
            password = defaultUserPwd.getPassword();
        } else {
            username = null;
            password = null;
        }

       if ((username == null || !username.equals(DBUserName)) || 
               (password == null || !password.equals(DBPassWord))){
           JOptionPane.showMessageDialog(null, "Username or Password wrong", "Warning", 0);
       }else {
           //System.out.println("Login secussefully");
           this.setVisible(false);
           if (hadResetPwd()!=true){
                NewRegisterFrame newFrame = new NewRegisterFrame("Password Reset Messages");
                newFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                newFrame.setBounds(400, 200, 700, 400);
                newFrame.setVisible(true);
           }else {
               MainFrame mainFrame = new MainFrame("Warehouse Management System");
               mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
               mainFrame.setBounds(300, 40, 1200, 800);

               mainFrame.setVisible(true);
           }
       }
   }
     
   private UserPwd getDBNamePassword(String username){
       
       UserPwd defaultUserPwd = new UserPwd();
       List<Map<String, Object>> queryForList = new JDBCTemplate().getDBNamePassword(username);
       //System.out.println(queryForList);
       
       if (queryForList != null){
           for (Map<String, Object> m : queryForList){
                //System.out.println(m);
                Set<String> keySet = m.keySet();
                for (String s : keySet){
                    if (s.equals("User")){
                        defaultUserPwd.setUsername((String)m.get(s));
                    }else if (s.equals("Password")){
                        defaultUserPwd.setPassword((String)m.get(s));
                    }   
                }
            }
       }else {
           return null;
       }

       return defaultUserPwd;
   }
  
   private UserPwd getUserPwd(){
       String userName = user_text.getText();
       char[] pwd = password_text.getPassword();
       String password = new String(password_text.getPassword(),0, pwd.length);
       
       UserPwd defaultUserPwd = new UserPwd();
       defaultUserPwd.setUsername(userName);
       defaultUserPwd.setPassword(password);
       
       return defaultUserPwd;
   }
   
   private boolean hadResetPwd(){
       return new File("..\\WarehouseSystem\\src\\main\\resources\\"+DruidUtils_User.PROPERTIES_USER).exists(); 
   }
}
