/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.Color;
import java.awt.Component;
import java.util.Calendar;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author paul
 */
public class MyExpireDateCellRenderer extends DefaultListCellRenderer{  
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
        
        Color myColor = new java.awt.Color(180, 255, 180, 152);
        
        setBackground(myColor);
        
        if(((java.sql.Date)list.getModel().getElementAt(index)).getTime() < now.getTime().getTime()){
            myColor = new java.awt.Color(Color.GRAY.getRed(),Color.GRAY.getGreen(),Color.GRAY.getBlue(),152);
            setBackground(myColor);
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< oneWeekAfter.getTime().getTime()){
            myColor = new java.awt.Color(Color.RED.getRed(),Color.RED.getGreen(),Color.RED.getBlue(),152);
            setBackground(myColor);  
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< twoWeekAfter.getTime().getTime()){
            myColor = new java.awt.Color(Color.ORANGE.getRed(),Color.ORANGE.getGreen(),Color.ORANGE.getBlue(),152);
            setBackground(myColor);  
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< fourWeekAfter.getTime().getTime()){
            myColor = new java.awt.Color(255, 255, 120, 152);
            setBackground(myColor);  
        }
        if(isSelected){
            Color selectionBackground = list.getSelectionBackground();
            
            double rate = 0.3;
            
            int r = (int)(selectionBackground.getRed()*rate + myColor.getRed()*(1-rate));
            int g = (int)(selectionBackground.getGreen()*rate + myColor.getGreen()*(1-rate));
            int b = (int)(selectionBackground.getBlue()*rate + myColor.getBlue()*(1-rate));
            
            setBackground(new Color(r,g,b, 128));
            this.setBorder(new javax.swing.border.CompoundBorder(null, new javax.swing.border.LineBorder(Color.blue)));
        }else{
            this.setBorder(new javax.swing.border.CompoundBorder(null, new javax.swing.border.LineBorder(myColor)));
        }
                
        setText(list.getModel().getElementAt(index).toString());  
        setOpaque(true);  
        return this;  
    }  
}