/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.event.KeyEvent;

/**
 *
 * @author paul
 */
public class MyTextPane extends javax.swing.JTextPane{
    public MyTextPane(){
        super();
        keybordHandler();
    }
    
    private void keybordHandler(){
        this.addKeyListener(new java.awt.event.KeyListener() {

            @Override
            public void keyTyped(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                MyTextPane.this.updateFunction();
            }

            @Override
            public void keyPressed(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                MyTextPane.this.updateFunction();
            }

            @Override
            public void keyReleased(KeyEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                MyTextPane.this.updateFunction();
            }
        });
    }
    
    private void updateFunction(){
        MyUtility.findParent(this, javax.swing.JPanel.class).updateUI();
    }
}
