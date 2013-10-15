/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.font.TextAttribute;
import java.util.HashMap;
import java.util.Map;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JList;

/**
 *
 * @author paul
 */
public class MyItemManageCellRenderer extends DefaultListCellRenderer{
    public MyItemManageCellRenderer(){
        super();
    }
    
    @Override  
    public Component getListCellRendererComponent(JList list,   
                                                  Object value,  
                                                  int index,   
                                                  boolean isSelected,  
                                                  boolean cellHasFocus) {  
        super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
        
        setText(list.getModel().getElementAt(index).toString());
        if(inventory.itemPage.ItemManage.currentArrayList.get(index) == 0){
            this.setBackground(inventory.itemPage.ItemManage.remainZero);
            final Map<TextAttribute, Object> attr = new HashMap<TextAttribute, Object>(list.getFont().getAttributes());
            attr.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
            this.setFont(list.getFont().deriveFont(attr));
        }else{
            this.setFont(new Font(list.getFont().getFontName(),list.getFont().getStyle(),list.getFont().getSize()));
        }
        
        if(isSelected){
            Color selectionBackground = list.getSelectionBackground();
            
            double rate = 0.2;
            
            int r = (int)(selectionBackground.getRed()*rate + inventory.itemPage.ItemManage.remainZero.getRed()*(1-rate));
            int g = (int)(selectionBackground.getGreen()*rate + inventory.itemPage.ItemManage.remainZero.getGreen()*(1-rate));
            int b = (int)(selectionBackground.getBlue()*rate + inventory.itemPage.ItemManage.remainZero.getBlue()*(1-rate));
            
            setBackground(new Color(r,g,b, 128));
        }
        
        setOpaque(true);  
        return this;  
    }  
}
