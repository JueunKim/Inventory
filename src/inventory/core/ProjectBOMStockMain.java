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
    private static JFrame f = null;
    
    public static ArrayList<String> PageList = null;
    
    private ProjectBOMStockMain(){
        DBConnection.setWriteConnection("root", "gcw1234!");
        DBConnection.setReadConnection("root", "gcw1234!");
        
        f = new MainFrame();
        PageList = new ArrayList<String>();
        
        initPage();
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
