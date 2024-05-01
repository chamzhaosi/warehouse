/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package UtilsTools;

import com.alibaba.druid.pool.DruidDataSourceFactory;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import javax.sql.DataSource;

/**
 *
 * @author user
 */
public class DruidUtils_Root {
    private static DataSource ds = null;
    public final static String PROPERTIES_ROOT = "DRUID_root.properties";

    static {
        try {
            //Create a Properties object
            Properties prop = new Properties();
            
//            //if the user haven't reset thier password, so load the root properties file
//            //otherwise, load to the user properties file
//            File file = new File("..\\WarehouseSystem\\src\\main\\resources\\"+DruidUtils_Root.PROPERTIES_USER);
//            if (file.exists()){
//                prop.load(DruidUtils_Root.class.getClassLoader().getResourceAsStream(PROPERTIES_USER));
//            }else {
//                //Use the Properties function load() to load into the properties file data
//                prop.load(DruidUtils_Root.class.getClassLoader().getResourceAsStream(PROPERTIES_ROOT));
//            }
            prop.load(DruidUtils_Root.class.getClassLoader().getResourceAsStream(PROPERTIES_ROOT));
  
            //Use the function to create a factory and get the data source
            ds = DruidDataSourceFactory.createDataSource(prop);
        } catch (IOException ex) {
            ex.printStackTrace();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    public static Connection getConnection() throws SQLException{
        return ds.getConnection();
    }
    
    public static void close (Statement stat, Connection conn){
        close(null, stat, conn);
    }
    
    public static void close (ResultSet rs, Statement stat, Connection conn){
        //Release the resource
        if (rs!=null){
            try {
                rs.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        if (stat!=null){
            try {
                stat.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        if (conn!=null){
            try {
                conn.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
    
    public static DataSource getDataSource(){
        return ds;
    }
}
