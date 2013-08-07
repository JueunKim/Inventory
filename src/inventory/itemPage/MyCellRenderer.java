/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.itemPage;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author paul
 */
public class MyCellRenderer extends DefaultListCellRenderer{  
    @Override  
    public Component getListCellRendererComponent(JList list,   
                                                  Object value,  
                                                  int index,   
                                                  boolean isSelected,  
                                                  boolean cellHasFocus) {  
        
        Calendar now = Calendar.getInstance();
        Calendar oneWeekAfter = Calendar.getInstance();
        Calendar twoWeekAfter = Calendar.getInstance();
        Calendar fourWeekAfter = Calendar.getInstance();
        oneWeekAfter.add(Calendar.WEEK_OF_YEAR, 1);
        twoWeekAfter.add(Calendar.WEEK_OF_YEAR, 2);
        fourWeekAfter.add(Calendar.WEEK_OF_YEAR, 4);
        
        Color myColor = new java.awt.Color(180, 255, 180);
        
        setBackground(myColor);
        
        if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< oneWeekAfter.getTime().getTime()){
            //in 1 month
            setBackground(Color.red);  
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< twoWeekAfter.getTime().getTime()){
            setBackground(Color.ORANGE);  
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< fourWeekAfter.getTime().getTime()){
            setBackground(new java.awt.Color(255, 255, 120));  
        }
        if(isSelected){
            int red = this.getBackground().getRed();
            int green = this.getBackground().getGreen();
            int blue = this.getBackground().getBlue();
            
            double back = 0.25;
            double select = 0.75;
            
            red = (int)(red*back+list.getSelectionBackground().getRed()*select);
            green = (int)(green*back+list.getSelectionBackground().getGreen()*select);
            blue = (int)(blue*back+list.getSelectionBackground().getBlue()*select);
                    
            setBackground(new Color(red,green,blue));
        }    
        setText(list.getModel().getElementAt(index).toString());  
        setOpaque(true);  
        return this;  
    }  
}  

