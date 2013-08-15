/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.nationPage;

import inventory.categoryPage.*;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;

/**
 *
 * @author Kind
 */
public class NationManage extends javax.swing.JPanel {

    /**
     * Creates new form NationManage
     */
    public NationManage() {
        initComponents();
        LoadData();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    public void LoadData(){
        list = new ArrayList<String>();
        pane = new ArrayList<String>();
        id = new ArrayList<Integer>();
        
        this.descriptionTextPane.setText("");
        
        try {
            ResultSet rs = null;
            
            rs = inventory.core.DBConnection.excuteQuery("SELECT * FROM inventory.nation order by name;");
            
            if(rs != null){
                while(rs.next()){
                    list.add(rs.getString("name"));
                    pane.add(rs.getString("description"));
                    id.add(rs.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(NationManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.nationNameList.setListData(list.toArray());
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nationNameScrollPane = new javax.swing.JScrollPane();
        nationNameList = new javax.swing.JList();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextPane = new javax.swing.JTextPane();
        backButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        dropButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();

        nationNameList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nationNameListMouseClicked(evt);
            }
        });
        nationNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                nationNameListValueChanged(evt);
            }
        });
        nationNameScrollPane.setViewportView(nationNameList);

        descriptionTextPane.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        descriptionTextPane.setEnabled(false);
        descriptionScrollPane.setViewportView(descriptionTextPane);

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });

        dropButton.setText("Drop");
        dropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropButtonActionPerformed(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(nationNameScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dropButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                        .addComponent(backButton))
                    .addComponent(nationNameScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nationNameListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nationNameListMouseClicked
        // TODO add your handling code here:
        //System.out.println(this.categoryNameList.getSelectedIndex());
        if(this.nationNameList.getSelectedIndex() >= 0){
            this.descriptionTextPane.setText(this.pane.get(this.nationNameList.getSelectedIndex()));
        }
        if(evt.getClickCount() == 2){
            editEvent();
        }
    }//GEN-LAST:event_nationNameListMouseClicked

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("AdminMain"));
    }//GEN-LAST:event_backButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        editEvent();
    }//GEN-LAST:event_editButtonActionPerformed

    private void editEvent(){
        if(this.nationNameList.getSelectedIndex()>=0 && this.nationNameList.getSelectedIndex() < list.size()){
            inventory.nationPage.NationEdit p = new inventory.nationPage.NationEdit();
            p.setEditConfig(id.get(this.nationNameList.getSelectedIndex()), list.get(this.nationNameList.getSelectedIndex()), pane.get(this.nationNameList.getSelectedIndex()));
            inventory.core.ProjectBOMStockMain.showingFrameDisplay(p);
        }
    }
    
    private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropButtonActionPerformed
        // TODO add your handling code here:
        /*int dialogResult = JOptionPane.showConfirmDialog (this, "Would You Like to Delete? Are you Sure?!","Warning",JOptionPane.YES_NO_OPTION);
        
        if(dialogResult == JOptionPane.YES_OPTION){
            inventory.core.DBConnection.updateQuery("DELETE FROM `inventory`.`nation` WHERE `id`='"+id.get(this.nationNameList.getSelectedIndex())+"';");
            this.LoadData();
        }*/
        inventory.core.ProjectBOMStockMain.dropAndDisable(this, this.id, this.nationNameList, inventory.core.ProjectBOMStockMain.table_type.indexOf("Nation"));
    }//GEN-LAST:event_dropButtonActionPerformed

    private void nationNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_nationNameListValueChanged
        // TODO add your handling code here:
        if(this.nationNameList.getSelectedIndex()>=0 && this.nationNameList.getSelectedIndex() < pane.size())
            this.descriptionTextPane.setText(this.pane.get(this.nationNameList.getSelectedIndex()));
    }//GEN-LAST:event_nationNameListValueChanged

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
        
        //inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("NationRegister"));
        inventory.core.ProjectBOMStockMain.showingFrameDisplay(new inventory.nationPage.NationRegister());
    }//GEN-LAST:event_registerButtonActionPerformed

    public void findAndSetSelectedItem(String name){
        this.nationNameList.setSelectedIndex(this.list.indexOf(name));
    }
    
    private ArrayList<String> list = null;
    private ArrayList<String> pane = null;
    private ArrayList<Integer> id = null;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextPane descriptionTextPane;
    private javax.swing.JButton dropButton;
    private javax.swing.JButton editButton;
    private javax.swing.JList nationNameList;
    private javax.swing.JScrollPane nationNameScrollPane;
    private javax.swing.JButton registerButton;
    // End of variables declaration//GEN-END:variables
}
