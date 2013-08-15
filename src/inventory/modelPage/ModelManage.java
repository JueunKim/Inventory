/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.modelPage;

import inventory.categoryPage.CategoryManage;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kind
 */
public class ModelManage extends javax.swing.JPanel {

    /**
     * Creates new form ModelManage
     */
    public ModelManage() {
        initComponents();
        this.loadDataByName("");
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        modelNameScrollPane = new javax.swing.JScrollPane();
        modelNameList = new javax.swing.JList();
        modelScrollPane = new javax.swing.JScrollPane();
        modelTextPane = new javax.swing.JTextPane();
        registerButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        dropButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();
        nameSearchTextField = new javax.swing.JTextField();
        nameSearchLabel = new javax.swing.JLabel();
        contactSearchLabel = new javax.swing.JLabel();
        contactSearchTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        modelNameList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelNameListMouseClicked(evt);
            }
        });
        modelNameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                modelNameListValueChanged(evt);
            }
        });
        modelNameScrollPane.setViewportView(modelNameList);

        modelTextPane.setDisabledTextColor(new java.awt.Color(0, 0, 0));
        modelTextPane.setEnabled(false);
        modelScrollPane.setViewportView(modelTextPane);

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
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

        backButton.setText("Back");
        backButton.setPreferredSize(new java.awt.Dimension(60, 30));
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        nameSearchTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                nameSearchTextFieldKeyReleased(evt);
            }
        });

        nameSearchLabel.setText("NameSearch");

        contactSearchLabel.setText("ContactSearch");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(modelNameScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(modelScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(editButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(dropButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameSearchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameSearchTextField)
                    .addComponent(contactSearchTextField)
                    .addComponent(contactSearchLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(registerButton, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(jSeparator1)
                    .addComponent(jSeparator2)
                    .addComponent(jSeparator3)
                    .addComponent(jSeparator4))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(nameSearchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(nameSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(contactSearchLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(contactSearchTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 63, Short.MAX_VALUE)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(modelNameScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(modelScrollPane, javax.swing.GroupLayout.Alignment.LEADING))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void modelNameListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelNameListMouseClicked
        // TODO add your handling code here:
        //System.out.println(this.categoryNameList.getSelectedIndex());
        if(this.modelNameList.getSelectedIndex()>=0)
            settingPane();
            //this.modelTextPane.setText(this.pane.get(this.modelNameList.getSelectedIndex()).toString());
    }//GEN-LAST:event_modelNameListMouseClicked

    private void modelNameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_modelNameListValueChanged
        // TODO add your handling code here:
        if(this.modelNameList.getSelectedIndex()>=0 && this.modelNameList.getSelectedIndex() < pane.size())
            settingPane();
            //this.modelTextPane.setText(this.pane.get(this.modelNameList.getSelectedIndex()).toString());
    }//GEN-LAST:event_modelNameListValueChanged

    private void settingPane(){
        if(!this.pane.get(this.modelNameList.getSelectedIndex()).equals(0))
            this.modelTextPane.setText(this.pane.get(this.modelNameList.getSelectedIndex()).toString());
        else{
            this.modelTextPane.setText("none");
        }
    }
    
    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
        inventory.modelPage.ModelChange p = new inventory.modelPage.ModelChange();
        p.setChangeConfig(null,"",null,"Register");
        if(inventory.core.ProjectBOMStockMain.display != null){
            inventory.core.ProjectBOMStockMain.display.dispose();
        }
        inventory.core.ProjectBOMStockMain.display = new inventory.core.ShowingFrame(p, "Register");
        inventory.core.ProjectBOMStockMain.display.setVisible(true);
        
        //inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ModelChange"));
        //((inventory.modelPage.ModelChange)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ModelChange"))).setChangeConfig(null,"",null,"Register");
    }//GEN-LAST:event_registerButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if(this.modelNameList.getSelectedIndex()>=0 && this.modelNameList.getSelectedIndex() < list.size()){
            inventory.modelPage.ModelChange p = new inventory.modelPage.ModelChange();
            p.setChangeConfig(id.get(this.modelNameList.getSelectedIndex()), list.get(this.modelNameList.getSelectedIndex()), pane.get(this.modelNameList.getSelectedIndex()), "Edit");
            if(inventory.core.ProjectBOMStockMain.display != null){
                inventory.core.ProjectBOMStockMain.display.dispose();
            }
            inventory.core.ProjectBOMStockMain.display = new inventory.core.ShowingFrame(p, "Edit");
            inventory.core.ProjectBOMStockMain.display.setVisible(true);
        }
        /*
        if(this.modelNameList.getSelectedIndex()>=0 && this.modelNameList.getSelectedIndex() < list.size()){
            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ModelChange"));
            ((inventory.modelPage.ModelChange)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ModelChange"))).setChangeConfig(id.get(this.modelNameList.getSelectedIndex()), list.get(this.modelNameList.getSelectedIndex()), pane.get(this.modelNameList.getSelectedIndex()), "Edit");
        }*/
    }//GEN-LAST:event_editButtonActionPerformed

    private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropButtonActionPerformed
        // TODO add your handling code here:
        /*
        int dialogResult = JOptionPane.showConfirmDialog (this, "Would You Like to Delete? Are you Sure?!","Warning",JOptionPane.YES_NO_OPTION);

        if(dialogResult == JOptionPane.YES_OPTION){
            inventory.core.DBConnection.updateQuery("DELETE FROM `inventory`.`model` WHERE `id`='"+id.get(this.modelNameList.getSelectedIndex())+"';");
            this.LoadData();
        }
        */
        inventory.core.ProjectBOMStockMain.dropAndDisable(this, this.id, this.modelNameList, inventory.core.ProjectBOMStockMain.table_type.indexOf("Model"), this);
        this.loadDataByName(this.nameSearchTextField.getText());
    }//GEN-LAST:event_dropButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("AdminMain"));
    }//GEN-LAST:event_backButtonActionPerformed

    private void nameSearchTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_nameSearchTextFieldKeyReleased
        // TODO add your handling code here:
        if(evt.getComponent() instanceof javax.swing.JTextField){
            loadDataByName(((javax.swing.JTextField)evt.getComponent()).getText());
        }
    }//GEN-LAST:event_nameSearchTextFieldKeyReleased
    
    public void findAndSetSelectedItem(String name){
        this.modelNameList.setSelectedIndex(this.list.indexOf(name));
    }
    
    private ArrayList<String> list = null;
    private ArrayList<Integer> pane = null;
    private ArrayList<Integer> id = null;
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel contactSearchLabel;
    private javax.swing.JTextField contactSearchTextField;
    private javax.swing.JButton dropButton;
    private javax.swing.JButton editButton;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JList modelNameList;
    private javax.swing.JScrollPane modelNameScrollPane;
    private javax.swing.JScrollPane modelScrollPane;
    private javax.swing.JTextPane modelTextPane;
    private javax.swing.JLabel nameSearchLabel;
    private javax.swing.JTextField nameSearchTextField;
    private javax.swing.JButton registerButton;
    // End of variables declaration//GEN-END:variables

    public void loadDataByName(String name) {
        list = new ArrayList<String>();
        pane = new ArrayList<Integer>();
        id = new ArrayList<Integer>();
        
        this.modelTextPane.setText("");
        
        try {
            ResultSet rs = null;
            
            rs = inventory.core.DBConnection.excuteQuery("SELECT * FROM inventory.model where name like '%"+name+"%' order by name;");
            
            if(rs != null){
                while(rs.next()){
                    if(rs.getInt("disable_id") != 1){
                        continue;
                    }
                    list.add(rs.getString("name"));
                    pane.add(rs.getInt("contact"));
                    id.add(rs.getInt("id"));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CategoryManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.modelNameList.setListData(list.toArray());
    }
}
