/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package FrameView;

import Adapter.ButtonKeyAdapter;
import DB_userPwd.UserPwd;
import LoginUI.UserLogin;
import UtilsTools.DruidUtils_Root;
import UtilsTools.DruidUtils_User;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import org.springframework.jdbc.core.JdbcTemplate;

/**
 *
 * @author user
 */
public class NewRegisterFrame extends JFrame{
        private JPanel newUserPasswordPanel = null;
        private JPanel oldNewUserPwdPanel = null;
    
        private JTextField oldUsername_text = null; 
        private JTextField oldPassword_text = null;
        private JTextField newUsername_text = null;
        private JTextField newPassword_text = null;
        
        private JButton saveButton = null;
        
        private GridBagConstraints gbc = new GridBagConstraints();
        
    public NewRegisterFrame(){
        
    }
    
    public NewRegisterFrame(String title){
        super(title);
        
        //Create a new panel for user resent the default username and password
        newUserPasswordPanel = new JPanel();
        this.setContentPane(newUserPasswordPanel);
        
        newUserPasswordPanel.setBorder(new EmptyBorder(10,10,10,10));
        newUserPasswordPanel.setLayout(new BorderLayout());
        
        setLableMessage();
        setOldNewUserPwdPanel();
        setSaveButton();
    }
    
    private void setLableMessage(){
        
        //Create a message to notice user reset their password
        JLabel labelMessage = 
                new JLabel("Please only reset and confirm your new password , ...", SwingConstants.CENTER);
        labelMessage.setFont(new Font("Arial", Font.PLAIN, 15));
        labelMessage.setForeground(Color.red);
        
        newUserPasswordPanel.add(labelMessage, BorderLayout.NORTH);
    }
    
    private void setOldNewUserPwdPanel(){
        oldNewUserPwdPanel = new JPanel();
        oldNewUserPwdPanel.setLayout(new GridLayout(1,2));
        newUserPasswordPanel.add(oldNewUserPwdPanel, BorderLayout.CENTER);
        
        setOldUserPwdPanel();
        setNewUserPwdPanel();
    }
    
    private void setOldUserPwdPanel(){
        JPanel oldUserPwdPanel = new JPanel();
        oldUserPwdPanel.setLayout(new GridBagLayout());
        oldNewUserPwdPanel.add(oldUserPwdPanel);
        
        //Defualt username label and textfield
        JLabel oldUsername = new JLabel("Old Username: ");
        oldUsername_text = new JTextField(20);
        
        //Defualt password label and textfield
        JLabel oldPassword = new JLabel("Old Password: ");
        oldPassword_text = new JTextField(20);
        
        //set the location of label and textfield in the panel
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.ipadx = 20;
        gbc.gridx = 0;
        gbc.gridy = 0;
        oldUserPwdPanel.add(oldUsername, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        oldUserPwdPanel.add(oldUsername_text, gbc);
        gbc.insets = new Insets(15,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        oldUserPwdPanel.add(oldPassword, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        oldUserPwdPanel.add(oldPassword_text, gbc);
    }
    
    private void setNewUserPwdPanel(){
        JPanel newUserPwdPanel = new JPanel();
        newUserPwdPanel.setLayout(new GridBagLayout());
        oldNewUserPwdPanel.add(newUserPwdPanel);
        
        //Username label and textfield 
        JLabel newUsername = new JLabel("New Username: ");
        newUsername_text = new JTextField(20);
        
        //Password label and textfield
        JLabel newPassword = new JLabel("New Password: ");
        newPassword_text = new JTextField(20);
        
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 0;
        newUserPwdPanel.add(newUsername, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        newUserPwdPanel.add(newUsername_text, gbc);
        gbc.insets = new Insets(15,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 2;
        newUserPwdPanel.add(newPassword, gbc);
        gbc.insets = new Insets(0,0,0,0);
        gbc.gridx = 0;
        gbc.gridy = 3;
        newUserPwdPanel.add(newPassword_text, gbc);
        
    }
    
    private void setSaveButton(){
        JPanel saveButtonPanel = new JPanel();
        saveButtonPanel.setLayout(new GridLayout(1,1));
        saveButtonPanel.setBorder(new EmptyBorder(0,200,0,200));
        newUserPasswordPanel.add(saveButtonPanel, BorderLayout.SOUTH);
        
        //Save button
        JButton saveButton = new JButton("Save");
        saveButtonPanel.add(saveButton);
        
        //Set saveButton is the default button in the panel
        newUserPasswordPanel.getRootPane().setDefaultButton(saveButton);
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                 updateDB();
            }
        });
        
        saveButton.addKeyListener(new ButtonKeyAdapter(){
            @Override
            public void keyPressed(KeyEvent e) {
                if (e.getKeyCode()==KeyEvent.VK_ENTER){
                    updateDB();
                }
            }
        });
    }
    
    private void updateDB(){
            //after make sure the old/new username/password update the new username/password to DB
            
            List<UserPwd> userPwdList = getNewUserPwd();
            
            if (userPwdList != null){
                String oldUsername = userPwdList.get(0).getUsername();
                String newPaswword = userPwdList.get(1).getPassword();

                //Registration the mysql connector driver and get the connection
                JdbcTemplate template = new JdbcTemplate(DruidUtils_Root.getDataSource());
                //Update the nes password to DB
                String sql = "set password for '" + oldUsername+"'@'localhost'"+ " = password ('" + newPaswword +"')";
//                System.out.println(oldUsername);
//                System.out.println(newPaswword);
//                System.out.println(sql);
                int update_count = template.update(sql);
                //System.out.println(update_count);
                if (update_count == 0){
                    JOptionPane.showMessageDialog(oldNewUserPwdPanel, "New password has been updated and Please login again with the new password...","Notice", 1);
                    updatePropertiesFile(oldUsername,newPaswword);
                    //System.exit(0);
                    //Instead of end the system process, create the user login frame again.
                    this.setVisible(false);
                    try {
                        Thread.sleep(2000);
                    } catch (InterruptedException ex) {
                        ex.printStackTrace();
                    }
                    new UserLogin().onCreate();
                }
            }
           
    }
    
    private List<UserPwd> getNewUserPwd(){
        List<UserPwd> listUserPwd = new ArrayList<>();
        
        //to get the new username and password which set in the above textfield
        UserPwd newUserPwd = new UserPwd();
        newUserPwd.setUsername(newUsername_text.getText());
        newUserPwd.setPassword(newPassword_text.getText());
        
        //to get the old username and password which set in the above textfield
        UserPwd oldUserPwd = new UserPwd();
        oldUserPwd.setUsername(oldUsername_text.getText());
        oldUserPwd.setPassword(oldPassword_text.getText());
        
        //to check whether the old username and password existing in DB or not;
        boolean hasRecord = checkDBUsernamePaswword(oldUserPwd);
        
        // if the old username and password is existing, then compare both old and new, both must diffrent
        if (hasRecord == true){
            if (!oldUserPwd.getUsername().equals(newUserPwd.getUsername())|| 
                    newUserPwd.getPassword().equals("") || 
                        oldUserPwd.getPassword().equals(newUserPwd.getPassword())){
                JOptionPane.showMessageDialog(oldNewUserPwdPanel, 
                    "Username may not be same or password may not different with old one", "Warning", 0);
            } else {
                listUserPwd.add(oldUserPwd);
                listUserPwd.add(newUserPwd);
                return listUserPwd;
            }
        }else {
            JOptionPane.showMessageDialog(oldNewUserPwdPanel, 
                    "The old username or password is not exsiting", "Warning", 0);
        }
        
        return null;
    }
    
    private boolean checkDBUsernamePaswword(UserPwd oldUserPwd){
        JdbcTemplate template = new JdbcTemplate(DruidUtils_Root.getDataSource());
        
        String username = oldUserPwd.getUsername();
        String password = oldUserPwd.getPassword();
        
        //convert the old password to cipher text
        String convertPwdSql = "select password('" + password + "')";
        String pwd = template.queryForObject(convertPwdSql, String.class);
        
        
        String querySql = "select * from user where user = ? and password = ?";
        List<Map<String, Object>> queryForList = template.queryForList(querySql, username, pwd);
        
        //if the list is empty, the return false, that means the username and password is not existing in DB
        if (queryForList.isEmpty()){
            return false;
        }else {
            return true;
        }
    }
    
    public static void updatePropertiesFile(String username, String password){
        String url = "jdbc:mysql://localhost:3306/warehouseMS";
        FileOutputStream out = null;
        
        try {
            Properties prop = new Properties();
            
            prop.load(NewRegisterFrame.class.getClassLoader().getResourceAsStream(DruidUtils_Root.PROPERTIES_ROOT));
            //System.out.println(NewRegisterFrame.class.getClassLoader().getResource(DruidUtils_Root.propertiesName));
            out = new FileOutputStream("..\\WarehouseSystem\\src\\main\\resources\\"+DruidUtils_User.PROPERTIES_USER);
            
            prop.setProperty("url", url);
            prop.setProperty("username", username);
            prop.setProperty("password", password);
            prop.store(out, null);
            
        } catch (IOException ex) {
            ex.printStackTrace();
        }finally{
            try {
                out.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }
}
