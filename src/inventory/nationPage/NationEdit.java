/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.nationPage;

import inventory.categoryPage.*;
import java.awt.Color;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kind
 */
public class NationEdit extends inventory.myClasses.MyJPanel {

    /**
     * Creates new form NationEdit
     */
    public NationEdit() {
        super();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nationNameLabel = new javax.swing.JLabel();
        nationNameTextField = new inventory.myClasses.MyTextField();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextPane = new inventory.myClasses.MyTextPane();
        DescriptionLabel = new javax.swing.JLabel();
        backButton = new inventory.myClasses.MyButton();
        editButton = new inventory.myClasses.MyButton();

        nationNameLabel.setText("Nation Name");

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
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 212, Short.MAX_VALUE)
                        .addComponent(editButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nationNameLabel)
                            .addComponent(DescriptionLabel))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionScrollPane)
                            .addComponent(nationNameTextField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(nationNameLabel)
                    .addComponent(nationNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(DescriptionLabel))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(editButton)
                    .addComponent(backButton))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        inventory.core.ProjectBOMStockMain.showingFrameDispose();
    }//GEN-LAST:event_backButtonActionPerformed

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        try {
            // TODO add your handling code here:
            //System.out.println(this.categoryNameTextField.getText());
            if(!this.nationNameTextField.getText().trim().equals("")){
                int dialogResult = JOptionPane.showConfirmDialog (this, "Would You Like to Save?","Warning",JOptionPane.YES_NO_OPTION);
                boolean saved = false;
                if(dialogResult == JOptionPane.YES_OPTION){
                    if(this.name.equals(this.nationNameTextField.getText())){
                        inventory.core.DBConnection.updateQuery("UPDATE `inventory`.`nation` SET `description`='"+this.descriptionTextPane.getText()+"' WHERE `id`='"+this.id+"';");
                        saved = true;
                    }else{
                        //SELECT name FROM inventory.category where name = 'eye';
                        ResultSet rs = inventory.core.DBConnection.executeQuery("SELECT * FROM inventory.nation where name = '"+this.nationNameTextField.getText()+"' and disable_id = 1;");
                        if(!rs.next() || rs.getInt("disable_id")!=1){
                            //UPDATE `inventory`.`category` SET `name`='Knife&suture2', `description`='test45' WHERE `id`='4';
                            inventory.core.DBConnection.updateQuery("UPDATE `inventory`.`nation` SET `name`='"+this.nationNameTextField.getText()+"', `description`='"+this.descriptionTextPane.getText()+"' WHERE `id`='"+this.id+"';");
                            saved = true;
                        }else{
                            JOptionPane.showConfirmDialog(this, "Name is duplicated","Warning",JOptionPane.OK_CANCEL_OPTION);
                        }
                    }
                    
                    if(saved){
                        ((inventory.nationPage.NationManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("NationManage"))).LoadData();
                        ((inventory.nationPage.NationManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("NationManage"))).findAndSetSelectedItem(this.nationNameTextField.getText());
                        this.clear();
                            
                        if(JOptionPane.showConfirmDialog(this, "save done! Now, page will go to \"Nation Manage\".","Confirm",JOptionPane.OK_CANCEL_OPTION) == 0){
                            inventory.core.ProjectBOMStockMain.showingFrameDispose();   
                        }
                    }
                }
            }else{
                /*
                * category name is empty
                */
                JOptionPane.showConfirmDialog(this, "Nation Name is Empty","Warning",JOptionPane.OK_CANCEL_OPTION);
                //System.out.println("Category Name is Empty");
            }
            //System.out.println(this.descriptionTextPane.getText());
        } catch (Exception ex) {
            Logger.getLogger(NationRegister.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void clear(){
        this.nationNameTextField.setText("");
        this.descriptionTextPane.setText("");
        this.id = 0;
        this.name = "";
        this.description = "";
    }
    
    public void setEditConfig(Integer id, String name, String description){
        this.id = id;
        this.name = name;
        this.description = description;
        this.nationNameTextField.setText(name);
        this.descriptionTextPane.setText(description);
    }
    
    private Integer id = 0;
    private String name = "";
    private String description = "";
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.JButton backButton;
    protected javax.swing.JScrollPane descriptionScrollPane;
    protected javax.swing.JTextPane descriptionTextPane;
    protected javax.swing.JButton editButton;
    private javax.swing.JLabel nationNameLabel;
    protected javax.swing.JTextField nationNameTextField;
    // End of variables declaration//GEN-END:variables

    @Override
    protected void myInitComponents() {
        this.initComponents();
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void LoadData() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setComponetsColor(Color transparent) {
        this.backButton.setBackground(transparent);
        this.nationNameTextField.setBackground(transparent);
        this.descriptionScrollPane.setBackground(transparent);
        this.descriptionTextPane.setBackground(transparent);
        this.editButton.setBackground(transparent);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
