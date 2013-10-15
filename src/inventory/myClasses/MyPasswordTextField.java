/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.Color;
import java.awt.event.KeyEvent;

/**
 *
 * @author paul
 */
public class MyPasswordTextField extends javax.swing.JPasswordField{
    public MyPasswordTextField(){
        super();
        this.setBackground(new Color(255,255,255,152));
        keybordHandler();
    }
    
    private void keybordHandler(){
        this.addKeyListener(new java.awt.event.KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                MyPasswordTextField.this.updateFunction();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                MyPasswordTextField.this.updateFunction();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                MyPasswordTextField.this.updateFunction();
            }
        });
    }
    
    private void updateFunction(){
        MyUtility.findParent(this, javax.swing.JPanel.class).updateUI();
    }
}
