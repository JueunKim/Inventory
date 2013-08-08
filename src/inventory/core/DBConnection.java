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
    private static Connection read = null;
    private static Connection write = null;
    
    private DBConnection(){}
    
    public static Connection getConnection(){
        return read;
    }
    
    public static boolean setReadConnection(String userName, String password) {
        try {
            String readIP = "54.214.19.198";
            read = null;
            read = DriverManager.getConnection("jdbc:mysql://"+readIP,userName, password);
            System.out.println("Read Database Connection Success");
            return true;
        } catch (SQLException sqex) {
            System.out.println("SQLException: " + sqex.getMessage());
            System.out.println("SQLState: " + sqex.getSQLState());
        }
        return false;
    }
    /*
            
     */
    
    public static boolean setWriteConnection(String userName, String password) {
        try {
            String writeIP = "54.214.19.198";
            write = null;
            write = DriverManager.getConnection("jdbc:mysql://"+writeIP,userName, password);
            System.out.println("Write Database Connection Success");
            return true;
        } catch (SQLException sqex) {
            System.out.println("SQLException: " + sqex.getMessage());
            System.out.println("SQLState: " + sqex.getSQLState());
        }
        return false;
    }
    
    public static void updateQuery(String sql){
        if(write != null){
            try {
                Statement s = null;
                s = write.createStatement();
                
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
        if(read != null){
            try {
                Statement s = null;
                s = read.createStatement();
                
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
