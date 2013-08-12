/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.core;
import java.util.ArrayList;
import javax.swing.*;

/**
 *
 * @author Kind
 */
public class ProjectBOMStockMain {
    public static ArrayList<String> roles;
    public static ArrayList<String> table_type = null;
    
    private static JFrame f = null;
    public static JFrame display = null;
    
    public static ArrayList<String> PageList = null;
    
    private ProjectBOMStockMain(){
        //DBConnection.setWriteConnection("root", "gcw1234!");
        //DBConnection.setReadConnection("root", "gcw1234!");
        DBConnection.setWriteConnection("root", "RLAqkdnf1125!");
        DBConnection.setReadConnection("root", "RLAqkdnf1125!");
        
        f = new MainFrame();
        PageList = new ArrayList<String>();
        
        setMetaData();
        
        initPage();
    }
    
    private void setMetaData(){
        roles = new ArrayList<String>();
        roles.add("Dumy");
        roles.add("Admin");
        roles.add("User");
        roles.add("Test");
        roles.add("Waiting Permision");
        
        table_type = new ArrayList<String>();
        table_type.add("Dumy");
        table_type.add("None");
        table_type.add("Category");
        table_type.add("Model");
        table_type.add("Nation");
        table_type.add("Item");
    }
    
    private void initPage(){
        addPage(new inventory.userManagement.Login(),"Login");
        addPage(new inventory.userManagement.Regieter(),"Register");
        addPage(new inventory.userManagement.UserManage(),"UserManage");
        
        addPage(new inventory.userPage.UserMain(),"UserMain");
        addPage(new inventory.adminPage.AdminMain(),"AdminMain");
        
        
        addPage(new inventory.categoryPage.CategoryManage(),"CategoryManage");
        addPage(new inventory.categoryPage.CategoryRegister(),"CategoryRegister");
        addPage(new inventory.categoryPage.CategoryEdit(),"CategoryEdit");
        
        addPage(new inventory.nationPage.NationRegister(),"NationRegister");
        addPage(new inventory.nationPage.NationManage(),"NationManage");
        addPage(new inventory.nationPage.NationEdit(),"NationEdit");
        
        addPage(new inventory.modelPage.ModelManage(),"ModelManage");
        addPage(new inventory.modelPage.ModelChange(),"ModelChange");
        
        addPage(new inventory.packagePage.PackageManage(),"PackageManage");
        
        addPage(new inventory.itemPage.ItemManage(), "ItemManage");
        addPage(new inventory.itemPage.ItemUpdate(), "ItemUpdate");
        addPage(new inventory.itemPage.ItemChange(), "ItemChange");
    }
    
    private void addPage(javax.swing.JPanel panel, String title){
        MainFrame.addPage(panel);
        PageList.add(title);
    }
    
    public static void setPage(int i){
        ((MainFrame)f).setPage(i);
        f.setVisible(true);
        f.setResizable(false);
    }
    
    public static javax.swing.JPanel getPage(int i){
        return ((inventory.core.MainFrame)f).getPage(i);
    }
    
    public static void main(String[] args){
        new ProjectBOMStockMain().setPage(PageList.indexOf("Login"));
    }
}
