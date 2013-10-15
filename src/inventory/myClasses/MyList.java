/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

/**
 *
 * @author paul
 */
public class MyList extends javax.swing.JList{
    public MyList(){
        super();
        this.propertyChangeHandler();
    }
    private void propertyChangeHandler(){
        this.addPropertyChangeListener(new PropertyChangeListener() {

            @Override
            public void propertyChange(PropertyChangeEvent evt) {
                MyList.this.updateFunction();
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
            }
        });
    }
    
    private void updateFunction(){
        if(MyUtility.findParent(this, javax.swing.JPanel.class) != null){
            MyUtility.findParent(this, javax.swing.JPanel.class).updateUI();
        }
    }
}
