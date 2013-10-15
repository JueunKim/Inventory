/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;

/**
 *
 * @author paul
 */
public class MyColorChooserButton extends MyButton{
    public MyColorChooserButton(){
        super();
        this.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(inventory.core.MainFrame.role != inventory.core.ProjectBOMStockMain.roles.indexOf("Admin")){
                    return;
                }
                
                Color select = null;
                select = javax.swing.JColorChooser.showDialog(MyColorChooserButton.this, "test", MyColorChooserButton.this.getBackground());
                if(select != null){
                    MyColorChooserButton.this.setBackground(select);
                    MyColorChooserButton.this.setForeground(select);
                    
                    String sql = null;
                    String weekID = null;
                    
                    String button = MyColorChooserButton.this.getToolTipText();
                    
                    if(button.equals("expired")){
                        inventory.itemPage.ItemManage.expired = select;
                        weekID = "1";
                    }else if(button.equals("oneWeek")){
                        inventory.itemPage.ItemManage.oneWeek = select;
                        weekID = "2";
                    }else if(button.equals("twoWeek")){
                        inventory.itemPage.ItemManage.twoWeek = select;
                        weekID = "3";
                    }else if(button.equals("fourWeek")){
                        inventory.itemPage.ItemManage.fourWeek = select;
                        weekID = "4";
                    }else if(button.equals("afterFourWeek")){
                        inventory.itemPage.ItemManage.afterFourWeek = select;
                        weekID = "5";    
                    }else if(button.equals("remainZero")){
                        inventory.itemPage.ItemManage.remainZero = select;
                        weekID = "6";
                    }
                    sql = "UPDATE `inventory`.`weekAndColor` SET `colorRed`='"+select.getRed()+"', `colorGreen`='"+select.getGreen()+"', `colorBlue`='"+select.getBlue()+"' WHERE `id`="+weekID+";";
                    
                    if(weekID != null){
                        inventory.core.DBConnection.updateQuery(sql);
                    }
//                    System.out.println(MyColorChooserButton.this.getToolTipText());
                }
            }
        });
    }
}
