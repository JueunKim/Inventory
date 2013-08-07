/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.core;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Kind
 */
public class DBConnection {
    private static Connection con = null;
    
    private DBConnection(){}
    
    public static Connection getConnection(){
        return con;
    }
    
    public static boolean setConnection(String userName, String password) {
        try {
            con = null;
            con = DriverManager.getConnection("jdbc:mysql://localhost",userName, password);
            System.out.println("Database Connection Success");
            return true;
        } catch (SQLException sqex) {
            System.out.println("SQLException: " + sqex.getMessage());
            System.out.println("SQLState: " + sqex.getSQLState());
        }
        return false;
    }
    
    public static void updateQuery(String sql){
        if(con != null){
            try {
                Statement s = null;
                s = con.createStatement();
                
                s.executeUpdate(sql);
            } catch (SQLException ex) {
                /*
                    todo 
                    * Handle Error
             
                */
                ex.printStackTrace();
                System.out.println("SQL Update Exception in Update Query");
            }
        }else{
            /*
             todo Error Message
             
             */
            System.out.println("Connection Error in Update Query");
        }
    }
    
    public static ResultSet excuteQuery(String sql){
        ResultSet rs = null;
        if(con != null){
            try {
                Statement s = null;
                s = con.createStatement();
                
                rs = s.executeQuery(sql);
            } catch (SQLException ex) {
                /*
                    todo 
                    * Handle Error
             
                */
                System.out.println("SQL Excute Exception in Excute Query");
                return rs = null;
            }
        }else{
            /*
             todo Error Message
             
             */
            System.out.println("Connection Error in Excute Query");
        }
        return rs;
    }
}
