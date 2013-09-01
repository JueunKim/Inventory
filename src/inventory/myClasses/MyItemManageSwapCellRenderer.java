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
public class MyItemManageSwapCellRenderer extends DefaultListCellRenderer{
    public MyItemManageSwapCellRenderer(){
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
        
        if(inventory.itemPage.ItemManageSwap.currentArrayList != null){
            if(inventory.itemPage.ItemManageSwap.currentArrayList.size() == 0){
                
            }else{
                if(inventory.itemPage.ItemManageSwap.currentArrayList.get(index) == 0){
                    this.setBackground(inventory.itemPage.ItemManageSwap.remainZero);
                    final Map<TextAttribute, Object> attr = new HashMap<TextAttribute, Object>(list.getFont().getAttributes());
                    attr.put(TextAttribute.STRIKETHROUGH, TextAttribute.STRIKETHROUGH_ON);
                    this.setFont(list.getFont().deriveFont(attr));
                }else{
                    this.setFont(new Font(list.getFont().getFontName(),list.getFont().getStyle(),list.getFont().getSize()));
                }
            }
        }
        
        if(isSelected){
            Color selectionBackground = list.getSelectionBackground();
            
            double rate = 0.2;
            
            int r = (int)(selectionBackground.getRed()*rate + inventory.itemPage.ItemManageSwap.remainZero.getRed()*(1-rate));
            int g = (int)(selectionBackground.getGreen()*rate + inventory.itemPage.ItemManageSwap.remainZero.getGreen()*(1-rate));
            int b = (int)(selectionBackground.getBlue()*rate + inventory.itemPage.ItemManageSwap.remainZero.getBlue()*(1-rate));
            
            setBackground(new Color(r,g,b, 128));
        }
        
        setOpaque(true);  
        return this;  
    }
}


