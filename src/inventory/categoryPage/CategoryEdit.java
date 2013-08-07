/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.categoryPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kind
 */
public class CategoryEdit extends javax.swing.JPanel {

    /**
     * Creates new form CategoryEdit
     */
    public CategoryEdit() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        categoryNameLabel = new javax.swing.JLabel();
        categoryNameTextField = new javax.swing.JTextField();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextPane = new javax.swing.JTextPane();
        DescriptionLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();

        categoryNameLabel.setText("Category Name");

        descriptionScrollPane.setViewportView(descriptionTextPane);

        DescriptionLabel.setText("Description");

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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(backButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 242, Short.MAX_VALUE)
                        .addComponent(editButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryNameLabel)
                            .addComponent(DescriptionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryNameLabel)
                    .addComponent(categoryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescriptionLabel))
                .addGap(15, 15, 15)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editButton)
                    .addComponent(backButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf(inventory.core.MainFrame.myPanelPast));
    }//GEN-LAST:event_backButtonActionPerformed

    public void showMode(boolean show){
        show = !show;
        this.categoryNameTextField.setEnabled(show);
        this.descriptionTextPane.setEditable(show);
        this.backButton.setVisible(show);
        this.editButton.setVisible(show);
    }
    
    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        try {
            // TODO add your handling code here:
            //System.out.println(this.categoryNameTextField.getText());
            if(!this.categoryNameTextField.getText().trim().equals("")){
                int dialogResult = JOptionPane.showConfirmDialog (this, "Would You Like to Save?","Warning",JOptionPane.YES_NO_OPTION);
                boolean saved = false;
                if(dialogResult == JOptionPane.YES_OPTION){
                    if(this.name.equals(this.categoryNameTextField.getText())){
                        inventory.core.DBConnection.updateQuery("UPDATE `inventory`.`category` SET `description`='"+this.descriptionTextPane.getText()+"' WHERE `id`='"+this.id+"';");
                        saved = true;
                    }else{
                        //SELECT name FROM inventory.category where name = 'eye';
                        ResultSet rs = inventory.core.DBConnection.excuteQuery("SELECT name FROM inventory.category where name = '"+this.categoryNameTextField.getText()+"';");
                        if(!rs.next()){
                            //UPDATE `inventory`.`category` SET `name`='Knife&suture2', `description`='test45' WHERE `id`='4';
                            inventory.core.DBConnection.updateQuery("UPDATE `inventory`.`category` SET `name`='"+this.categoryNameTextField.getText()+"', `description`='"+this.descriptionTextPane.getText()+"' WHERE `id`='"+this.id+"';");
                            saved = true;
                        }else{
                            JOptionPane.showConfirmDialog(this, "Name is duplicated","Warning",JOptionPane.OK_CANCEL_OPTION);
                        }
                    }
                    
                    if(saved){
                        if(JOptionPane.showConfirmDialog(this, "save done! Now, page will go to \"Category Manage\".","Confirm",JOptionPane.OK_CANCEL_OPTION) == 0){
                            this.clear();
                            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("CategoryManage"));
                             ((inventory.categoryPage.CategoryManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("CategoryManage"))).LoadData();
                        }
                    }
                }
            }else{
                /*
                * category name is empty
                */
                JOptionPane.showConfirmDialog(this, "Category Name is Empty","Warning",JOptionPane.OK_CANCEL_OPTION);
                //System.out.println("Category Name is Empty");
            }
            //System.out.println(this.descriptionTextPane.getText());
        } catch (Exception ex) {
            Logger.getLogger(CategoryRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void clear(){
        this.categoryNameTextField.setText("");
        this.descriptionTextPane.setText("");
        this.id = 0;
        this.name = "";
        this.description = "";
    }
    
    public void setEditConfig(Integer id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        this.categoryNameTextField.setText(name);
        this.descriptionTextPane.setText(description);
    }
    
    private Integer id = 0;
    private String name = "";
    private String description = "";
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel categoryNameLabel;
    protected javax.swing.JTextField categoryNameTextField;
    protected javax.swing.JScrollPane descriptionScrollPane;
    protected javax.swing.JTextPane descriptionTextPane;
    protected javax.swing.JButton editButton;
    // End of variables declaration//GEN-END:variables
}
