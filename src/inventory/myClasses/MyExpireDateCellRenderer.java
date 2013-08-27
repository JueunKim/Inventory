/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import inventory.itemPage.ItemManage;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.text.AttributedString;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;

/**
 *
 * @author paul
 */
public class MyExpireDateCellRenderer extends DefaultListCellRenderer{ 
    public MyExpireDateCellRenderer(){
        super();
    }
    
    @Override  
    public Component getListCellRendererComponent(JList list,   
                                                  Object value,  
                                                  int index,   
                                                  boolean isSelected,  
                                                  boolean cellHasFocus) {  
        
        int transparent = 160;
        
        Calendar now = Calendar.getInstance();
        Calendar oneWeekAfter = Calendar.getInstance();
        Calendar twoWeekAfter = Calendar.getInstance();
        Calendar fourWeekAfter = Calendar.getInstance();
        oneWeekAfter.add(Calendar.WEEK_OF_YEAR, 1);
        twoWeekAfter.add(Calendar.WEEK_OF_YEAR, 2);
        fourWeekAfter.add(Calendar.WEEK_OF_YEAR, 4);
        
        Color myColor = null;
        
        if(ItemManage.afterFourWeek != null){
            myColor = ItemManage.afterFourWeek;
        }else{
            myColor = new java.awt.Color(180, 255, 200, transparent);
        }
        setBackground(myColor);
        
        if(((java.sql.Date)list.getModel().getElementAt(index)).getTime() < now.getTime().getTime()){
            if(ItemManage.expired != null){
                myColor = ItemManage.expired;
            }else{
                myColor = new java.awt.Color(Color.GRAY.getRed(),Color.GRAY.getGreen(),Color.GRAY.getBlue(),transparent);
            }
            setBackground(myColor);
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< oneWeekAfter.getTime().getTime()){
            if(ItemManage.oneWeek != null){
                myColor = ItemManage.oneWeek;
            }else{
                myColor = new java.awt.Color(Color.RED.getRed(),Color.RED.getGreen(),Color.RED.getBlue(),transparent);
            }
            setBackground(myColor);  
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< twoWeekAfter.getTime().getTime()){
            if(ItemManage.twoWeek != null){
                myColor = ItemManage.twoWeek;
            }else{
                myColor = new java.awt.Color(Color.ORANGE.getRed(),Color.ORANGE.getGreen(),Color.ORANGE.getBlue(),transparent);
            }
            setBackground(myColor);  
        }else if(((java.sql.Date)list.getModel().getElementAt(index)).getTime()< fourWeekAfter.getTime().getTime()){
            if(ItemManage.fourWeek != null){
                myColor = ItemManage.fourWeek;
            }else{
                myColor = new java.awt.Color(255, 255, 120, transparent);
            }
            setBackground(myColor);  
        }
        if(isSelected){
            Color selectionBackground = list.getSelectionBackground();
            
            double rate = 0.3;
            
            int r = (int)(selectionBackground.getRed()*rate + myColor.getRed()*(1-rate));
            int g = (int)(selectionBackground.getGreen()*rate + myColor.getGreen()*(1-rate));
            int b = (int)(selectionBackground.getBlue()*rate + myColor.getBlue()*(1-rate));
            
            setBackground(new Color(r,g,b, 128));
//            this.setBorder(new javax.swing.border.CompoundBorder(null, new javax.swing.border.LineBorder(Color.blue)));
            this.setBorder(new DefaultListCellRenderer().getBorder());
        }else{
            this.setBorder(new DefaultListCellRenderer().getBorder());
//            this.setBorder(new javax.swing.border.CompoundBorder(null, new javax.swing.border.LineBorder(myColor)));
        }
        
        setText(list.getModel().getElementAt(index).toString());
        
        if(inventory.itemPage.ItemManage.currentArrayList.get(index) == 0){
            final Map<TextAttribute, Object> attr = new HashMap<TextAttribute, Object>(list.getFont().getAttributes());
            attr.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            this.setFont(list.getFont().deriveFont(attr));
        }else{
            this.setFont(new Font(list.getFont().getFontName(),list.getFont().getStyle(),list.getFont().getSize()));
        }
        setOpaque(true);  
        return this;  
    }  
}