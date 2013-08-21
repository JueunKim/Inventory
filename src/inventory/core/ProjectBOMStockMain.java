/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.core;
import inventory.categoryPage.CategoryManage;
import inventory.itemPage.ItemManage;
import inventory.modelPage.ModelManage;
import inventory.nationPage.NationManage;
import inventory.packagePage.PackageManage;
import java.awt.Component;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.*;
import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

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
        this("");
    }
    
    private ProjectBOMStockMain(String ip){
        //DBConnection.setWriteConnection("root", "gcw1234!", "54.214.19.198");
        //DBConnection.setReadConnection("root", "gcw1234!", "54.214.19.198");
//        DBConnection.setWriteConnection("root", "RLAqkdnf1125!", "localhost");
//        DBConnection.setReadConnection("root", "RLAqkdnf1125!", "localhost");
        DBConnection.setWriteConnection("root", "RLAqkdnf1125!", "192.168.60.120");
        DBConnection.setReadConnection("root", "RLAqkdnf1125!", "192.168.60.120");
        
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
        table_type.add("Package");
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
    }
    
    public static javax.swing.JPanel getPage(int i){
        return ((inventory.core.MainFrame)f).getPage(i);
    }
    
    public static javax.swing.JFrame getMainFrame(){
        return f;
    }
    
    public static void main(String[] args){
        new ProjectBOMStockMain().setPage(PageList.indexOf("Login"));
    }
    
    public static void dropAndDisable(Component com, ArrayList<Integer> ids, javax.swing.JList list, int table_type){
        if(list.getSelectedIndex()>=0){
            String name = null;
            if(JOptionPane.showConfirmDialog(com, "This will be Deleted!!!. Are you Sure?!","Confirm",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                name = list.getSelectedValue().toString();
                
                String s = null;
                s = JOptionPane.showInputDialog(com, "Please Type a Reason", "Drop",JOptionPane.OK_CANCEL_OPTION);
                
                if(s != null && !s.trim().equals("")){
                    try {
                        //INSERT INTO `inventory`.`disable` (`description`, `user_id`, `table_id`, `table_type`) VALUES ('desc', 'user_id', 'tabld_id', 'table_type');
                        String sql = "INSERT INTO `inventory`.`disable` (`description`, `user_id`, `table_id`, `table_type`) VALUES ('"+s+"', '"+inventory.core.MainFrame.user_id+"', '"+ids.get(list.getSelectedIndex())+"', '"+table_type+"');";
                        
                        
                        ResultSet rs = inventory.core.DBConnection.updateQueryGetID(sql);
                        
                        if(rs.next()){
                            String table = null;
                            
                            table = inventory.core.ProjectBOMStockMain.table_type.get(table_type).toLowerCase();
                            sql = "UPDATE `inventory`.`"+table+"` SET `disable_id`='"+rs.getLong(1)+"' WHERE `id`='"+ids.get(list.getSelectedIndex())+"';";
                            
                            inventory.core.DBConnection.updateQuery(sql);
                        }
                    } catch (SQLException ex) {
                        //Logger.getLogger(CategoryManage.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }else{
                    JOptionPane.showMessageDialog(com, "Please Type a Reason.","Alert",JOptionPane.OK_OPTION);
                    return;
                }
                
                //inventory.core.DBConnection.updateQuery("DELETE FROM `inventory`.`item` WHERE `id`='"+this.id.get(this.nameList.getSelectedIndex())+"';");
                if(com instanceof CategoryManage){
                    ((CategoryManage)com).LoadData();
                }else if(com instanceof ModelManage){
                    ((ModelManage)com).loadDataByName("");
                }else if(com instanceof PackageManage){
                    ((PackageManage)com).loadDataByName("");
                }else if(com instanceof ItemManage){
                    ((ItemManage)com).loadDataByName("");
                }else if(com instanceof NationManage){
                    ((NationManage)com).LoadData();
                }
                JOptionPane.showMessageDialog(com, name + " was Deleted.","Alert",JOptionPane.OK_OPTION);
            }
        }
    }
    
    public static void showingFrameDisplay(javax.swing.JPanel p){
        if(inventory.core.ProjectBOMStockMain.display != null){
            inventory.core.ProjectBOMStockMain.display.dispose();
        }
        inventory.core.ProjectBOMStockMain.display = new inventory.core.ShowingFrame(p, "CategoryRegister");
        inventory.core.ProjectBOMStockMain.display.setVisible(true);
    }
    
    public static void showingFrameDispose(){
        if(inventory.core.ProjectBOMStockMain.display != null){
            inventory.core.ProjectBOMStockMain.display.dispose();
        }
    }
    
    public static void itemImagefileCopy(String inputSrc, String DestSrc){
        InputStream inStream = null;
	OutputStream outStream = null;
 
    	try{
            //File afile =new File("/home/paul/workspace/Java/netbeans/ProjectBOMStock/src/inventory/Image2.jpg");
            
            //File bfile =new File("src/inventory/core/Image.jpg");
            File afile = new File(inputSrc);
            
            System.out.println(ProjectBOMStockMain.class.getResource("/"));
            File bfile = new File(DestSrc);
            
            System.out.println(inputSrc);
            System.out.println(DestSrc);
            
            //bfile.createNewFile();
            
            //aaa
            
    	    inStream = new FileInputStream(afile);
            outStream = new FileOutputStream(bfile);
 
    	    byte[] buffer = new byte[1024];
 
    	    int length;
    	    //copy the file content in bytes 
    	    while ((length = inStream.read(buffer)) > 0){
 
    	    	outStream.write(buffer, 0, length);
 
    	    }
            
            outStream.flush();
            inStream.close();
    	    outStream.close();
 
    	    System.out.println("File is copied successful!");
 
    	}catch(IOException e){
    		e.printStackTrace();
    	}
    }
    
    private static File openFile(String fileFilters [][]){
        javax.swing.JFileChooser jfc = new JFileChooser();
        
        if(fileFilters != null){
            for(int i = 0; i < fileFilters.length; i++){
                jfc.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter(fileFilters[i][0],fileFilters[i][1]));
            }
        }
        jfc.setFileSelectionMode(javax.swing.JFileChooser.FILES_ONLY);
        jfc.showOpenDialog(jfc);
        return jfc.getSelectedFile();
    }
    
    public static File openImageFile(){
        String fileFilters [][] = {{".png", "png"},
                              {".gif", "gif"},
                              {".jpg", "jpg"}};
        return openFile(fileFilters);
    }
}
