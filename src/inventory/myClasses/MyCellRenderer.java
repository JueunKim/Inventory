/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

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
public class MyCellRenderer extends DefaultListCellRenderer{
    public MyCellRenderer(){
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
        setOpaque(true);  
        return this;  
    }  
}
