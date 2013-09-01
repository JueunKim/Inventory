/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.categoryPage;

import java.awt.Color;
import java.net.URISyntaxException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Kind
 */
public class CategoryEdit extends inventory.myClasses.MyJPanel {

    /**
     * Creates new form CategoryEdit
     */
    public CategoryEdit() {
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

        categoryNameLabel = new javax.swing.JLabel();
        categoryNameTextField = new inventory.myClasses.MyTextField();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextPane = new inventory.myClasses.MyTextPane();
        DescriptionLabel = new javax.swing.JLabel();
        backButton = new inventory.myClasses.MyButton();
        editButton = new inventory.myClasses.MyButton();
        codeTextField = new javax.swing.JTextField();
        codeLabel = new javax.swing.JLabel();

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

        codeLabel.setText("Code");

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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(categoryNameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(DescriptionLabel)
                            .addComponent(codeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryNameTextField, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 255, Short.MAX_VALUE)
                            .addComponent(codeTextField))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(categoryNameLabel)
                    .addComponent(categoryNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(codeTextField)
                    .addComponent(codeLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(6, 6, 6)
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
        //inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf(inventory.core.MainFrame.myPanelPast));
        if(javax.swing.SwingUtilities.getWindowAncestor(this) !=null && javax.swing.SwingUtilities.getWindowAncestor(this) instanceof javax.swing.JFrame){
           ((inventory.core.ShowingFrame)javax.swing.SwingUtilities.getWindowAncestor(this)).dispose();
        }
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
            if(this.codeTextField.getText().trim().equals("")){
                JOptionPane.showMessageDialog(this, "Please Type Code.", "Alert", JOptionPane.OK_OPTION);
                return;
            }
            // TODO add your handling code here:
            //System.out.println(this.categoryNameTextField.getText());
            if(!this.categoryNameTextField.getText().trim().equals("")){
                int dialogResult = JOptionPane.showConfirmDialog (this, "Would You Like to Save?","Warning",JOptionPane.YES_NO_OPTION);
                boolean saved = false;
                if(dialogResult == JOptionPane.YES_OPTION){
                    if(this.name.equals(this.categoryNameTextField.getText())){
                        inventory.core.DBConnection.updateQuery("UPDATE `inventory`.`category` SET `description`='"+this.descriptionTextPane.getText()+"', `code`='"+this.codeTextField.getText()+"' WHERE `id`='"+this.id+"';");
                        saved = true;
                    }else{
                        //SELECT name FROM inventory.category where name = 'eye';
                        ResultSet rs = inventory.core.DBConnection.executeQuery("SELECT * FROM inventory.category where name = '"+this.categoryNameTextField.getText()+"' and disable_id = 1;");
                        if(!rs.next() || rs.getInt("disable_id") != 1){
                            //UPDATE `inventory`.`category` SET `name`='Knife&suture2', `description`='test45' WHERE `id`='4';
                            String sql = "UPDATE `inventory`.`category` SET `name`='"+this.categoryNameTextField.getText()+"', `description`='"+this.descriptionTextPane.getText()+"', `code` = '"+this.codeTextField.getText()+"' WHERE `id`='"+this.id+"';";
                            System.out.println(sql);
                            inventory.core.DBConnection.updateQuery(sql);
                            saved = true;
                        }else{
                            JOptionPane.showConfirmDialog(this, "Name is duplicated","Warning",JOptionPane.OK_CANCEL_OPTION);
                        }
                    }
                    
                    if(saved){
                        ((inventory.categoryPage.CategoryManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("CategoryManage"))).LoadData();
                        ((inventory.categoryPage.CategoryManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("CategoryManage"))).findAndSetSelectedItem(this.categoryNameTextField.getText());
                        if(JOptionPane.showConfirmDialog(this, "save done! Now, page will go to \"Category Manage\".","Confirm",JOptionPane.OK_CANCEL_OPTION) == 0){
                            //this.clear();
                            //inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("CategoryManage"));
                            if(javax.swing.SwingUtilities.getWindowAncestor(this) !=null && javax.swing.SwingUtilities.getWindowAncestor(this) instanceof javax.swing.JFrame){
                                ((inventory.core.ShowingFrame)javax.swing.SwingUtilities.getWindowAncestor(this)).dispose();
                             }
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
    
    public void setEditConfig(Integer id, String name, String description, String code){
        this.id = id;
        this.name = name;
        this.code = code;
        this.description = description;
        this.codeTextField.setText(code);
        this.categoryNameTextField.setText(name);
        this.descriptionTextPane.setText(description);
    }
    
    private Integer id = 0;
    private String name = "";
    private String description = "";
    private String code = "";
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel DescriptionLabel;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel categoryNameLabel;
    protected javax.swing.JTextField categoryNameTextField;
    private javax.swing.JLabel codeLabel;
    private javax.swing.JTextField codeTextField;
    protected javax.swing.JScrollPane descriptionScrollPane;
    protected javax.swing.JTextPane descriptionTextPane;
    protected javax.swing.JButton editButton;
    // End of variables declaration//GEN-END:variables

    public void setComponetsColor(Color transparent) {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.categoryNameTextField.setBackground(transparent);
        this.descriptionScrollPane.setBackground(transparent);
        this.descriptionTextPane.setBackground(transparent);
    }

    @Override
    public void LoadData() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    protected void myInitComponents() {
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        this.initComponents();
    }
}
