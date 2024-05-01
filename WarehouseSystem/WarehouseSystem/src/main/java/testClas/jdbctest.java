/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package testClas;

import UtilsTools.DruidUtils_Root;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.springframework.jdbc.core.JdbcTemplate;


/**
 *
 * @author user
 */
public class jdbctest {
    public static void main(String[] args) {
        JdbcTemplate template = new JdbcTemplate(DruidUtils_Root.getDataSource());
        
        String sql = "select * from testing";

        List<Map<String, Object>> queryForList = template.queryForList(sql);
        
        for (Map<String, Object> m : queryForList){
            Set<String> keySet = m.keySet();
            
        }
    }
}
