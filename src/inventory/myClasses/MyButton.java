/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.event.ActionEvent;
import java.awt.event.MouseEvent;

/**
 *
 * @author paul
 */
public class MyButton extends javax.swing.JButton{
    public MyButton(){
        super();
        addHandler();
    }
    
    private void addHandler(){
        this.addActionListener(new java.awt.event.ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                updateFunction();
            }
        });
        
        this.addMouseListener(new java.awt.event.MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
                updateFunction();
            }

            @Override
            public void mousePressed(MouseEvent e) {
                updateFunction();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                updateFunction();
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                updateFunction();
            }

            @Override
            public void mouseExited(MouseEvent e) {
                updateFunction();
            }
        });
    }
    
    private void updateFunction(){
        MyUtility.findParent(this, javax.swing.JPanel.class).updateUI();
    }
}
