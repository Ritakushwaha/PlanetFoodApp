/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFoodDbUtil;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author Rita
 */
public class DbConnection {
  private static Connection conn;
    static{
        try {
            Class.forName("oracle.jdbc.OracleDriver");
            conn = DriverManager.getConnection("jdbc:oracle:thin:@//RITA:1521/XE", "myproject", "rita");
            JOptionPane.showMessageDialog(null, "Connection Established to the DB", "SUCCESS", JOptionPane.INFORMATION_MESSAGE);
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "Error in Loading Driver Class", "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "SQL error :" + ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
    public static Connection getConnection(){
        return conn;
    }
    public static void closeConnection()
    {
      try{
        if(conn!=null)
            conn.close();
        }
	catch(SQLException ex)
        {
            JOptionPane.showMessageDialog(null, "SQL error :" + ex, "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }
} 
