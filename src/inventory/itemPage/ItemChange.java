/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.itemPage;

import java.awt.Color;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Kind
 */
public class ItemChange extends inventory.myClasses.MyJPanel {
    private Integer id = 0;
    private String originalName = null;
    
    int imageWidth = 0;
    int imageHeight = 0;
    
    /**
     * Creates new form ItemChange
     */
    public ItemChange() {
        super();
    }
    
    public void setElements(Integer id, String categoryName, String modelName, String nationName, String packageNumber, String buttonType){
        //set this values
        this.id = id;
        
        if(id > 0){
            try {
                ResultSet rs = inventory.core.DBConnection.executeQuery("SELECT * FROM inventory.item WHERE id = "+id+";");
                if(rs.next()){
                    originalName = rs.getString("name");
                    this.nameTextField.setText(originalName);
                    this.priceTextField.setText(rs.getFloat("price")+"");
                    this.descriptionTextArea.setText(rs.getString("description"));
                    this.currentTextField.setText(rs.getInt("current")+"");
                    this.expiredateTextField.setText(rs.getString("expiredate").toString());
                    
                    this.categoryTextField.setText(categoryName);
                    this.modelTextField.setText(modelName);
                    this.nationTextField.setText(nationName);
                    this.packageTextField.setText(packageNumber);
                    
                    this.changeButton.setText(buttonType);
                }else{
                    this.clearElements();
                    ((inventory.itemPage.ItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"))).loadDataByName("");
                    inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"));
                    return;
                }
            } catch (SQLException ex) {
                Logger.getLogger(ItemUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        
        this.imageLabel.setIcon(getItemImage(this.nameTextField.getText()));
        
        this.imageLabel.updateUI();
        this.imageLabel.repaint();
        this.repaint();
        this.updateUI();
    }
    
    protected ImageIcon getItemImage(String name){
        try {
            String src = null;
            if(name.length()>1){
                src = name.substring(0, name.length()-1);
            }
            /*
             * 1.       first, read local folder
             * 1.1      if the file is on local
             * 1.1.1    if modified date < databased's modifieddate
             * 1.1.1.1  get file from database and write image on the local
             * 1.1.2    else use local file
             * 1.2      if the file is not on local
             * 1.2.1    get file from database
             */
            
            /*
             if(local folder has file){
                 if(modified date of local < modified date of database){
                     get file from database
                     write image by database file
                 }else{
                     use local file
                 }
             }else{
                 get file from database
             }
             
             
             
             
             */
            
            //this is a proceess that retrieve from database
            this.imageWidth = 368;
            this.imageHeight = 490;
            
            String sql = "SELECT * FROM inventory.item_image WHERE name = '"+src+"';";
            
            ResultSet rs = inventory.core.DBConnection.executeQuery(sql);
            
            InputStream is = null;
            
            if(rs.next()){
                this.imageLabel.setIcon(null);
                is = rs.getBinaryStream("image");
                ImageIcon ii = new ImageIcon(ImageIO.read(is).getScaledInstance(this.imageWidth, this.imageHeight, Image.SCALE_DEFAULT));
                is.close();
                return ii;
            }else{
                this.imageLabel.setIcon(null);
                is = this.getClass().getResourceAsStream("itemImange/No_Image.jpg");
                ImageIcon ii = new ImageIcon(ImageIO.read(is).getScaledInstance(this.imageWidth, this.imageHeight, Image.SCALE_DEFAULT));
                is.close();
                return ii;
            }
            /*
            try {
                this.imageLabel.setIcon(null);
                is = this.getClass().getResourceAsStream(src);
                ImageIcon ii = new ImageIcon(ImageIO.read(is).getScaledInstance(this.imageWidth, this.imageHeight, Image.SCALE_DEFAULT));
                is.close();
                return ii;
            } catch (Exception ex) {
                try {
                    this.imageLabel.setIcon(null);
                    is = this.getClass().getResourceAsStream("itemImange/No_Image.jpg");
                    ImageIcon ii = new ImageIcon(ImageIO.read(is).getScaledInstance(this.imageWidth, this.imageHeight, Image.SCALE_DEFAULT));
                    is.close();
                    return ii;
                } catch (IOException ex1) {
                    Logger.getLogger(ItemUpdate.class.getName()).log(Level.SEVERE, null, ex1);
                }
            }
            return null;
            */
        } catch (SQLException ex) {
            Logger.getLogger(ItemUpdate.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ItemUpdate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public void clearElements(){
        id = 0;
        originalName = null;
        
        this.changeTextField.setText("");
        
        this.categoryTextField.setText("");
        this.currentTextField.setText("");
        this.descriptionTextArea.setText("");
        this.expiredateTextField.setText("");
        this.modelTextField.setText("");
        this.nameTextField.setText("");
        this.nationTextField.setText("");
        this.packageTextField.setText("");
        this.priceTextField.setText("");
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        backButton = new inventory.myClasses.MyButton();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionTextArea = new inventory.myClasses.MyTextArea();
        expiredateTextField = new inventory.myClasses.MyTextField();
        currentTextField = new inventory.myClasses.MyTextField();
        nationTextField = new inventory.myClasses.MyTextField();
        priceTextField = new inventory.myClasses.MyTextField();
        categoryLabel = new javax.swing.JLabel();
        nameLabel = new javax.swing.JLabel();
        expiredateLabel = new javax.swing.JLabel();
        currentLabel = new javax.swing.JLabel();
        packageLabel = new javax.swing.JLabel();
        modelLabel = new javax.swing.JLabel();
        nationLabel = new javax.swing.JLabel();
        priceLabel = new javax.swing.JLabel();
        nameTextField = new inventory.myClasses.MyTextField();
        categoryTextField = new inventory.myClasses.MyTextField();
        modelTextField = new inventory.myClasses.MyTextField();
        packageTextField = new inventory.myClasses.MyTextField();
        descriptionLabel = new javax.swing.JLabel();
        changeButton = new inventory.myClasses.MyButton();
        changeTextField = new inventory.myClasses.MyTextField();
        imageLabel = new javax.swing.JLabel();

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        descriptionScrollPane.setEnabled(false);

        descriptionTextArea.setColumns(20);
        descriptionTextArea.setRows(5);
        descriptionTextArea.setEnabled(false);
        descriptionScrollPane.setViewportView(descriptionTextArea);

        expiredateTextField.setEnabled(false);

        currentTextField.setEnabled(false);
        currentTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                currentTextFieldKeyTyped(evt);
            }
        });

        nationTextField.setEnabled(false);

        priceTextField.setEnabled(false);
        priceTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                priceTextFieldKeyTyped(evt);
            }
        });

        categoryLabel.setText("Category");

        nameLabel.setText("Name");

        expiredateLabel.setText("ExpireDate");

        currentLabel.setText("Current Remain");

        packageLabel.setText("Package");

        modelLabel.setText("Model");

        nationLabel.setText("Nation for Price");

        priceLabel.setText("Price");

        nameTextField.setEnabled(false);

        categoryTextField.setEnabled(false);

        modelTextField.setEnabled(false);

        packageTextField.setEnabled(false);

        descriptionLabel.setText("Description");

        changeButton.setText("Change");
        changeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                changeButtonActionPerformed(evt);
            }
        });

        changeTextField.setFont(new java.awt.Font("Ubuntu", 0, 18)); // NOI18N
        changeTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                changeTextFieldKeyTyped(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(currentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nationLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(priceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(packageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(modelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(categoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(expiredateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 88, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(packageTextField)
                    .addComponent(priceTextField)
                    .addComponent(nationTextField)
                    .addComponent(currentTextField)
                    .addComponent(expiredateTextField)
                    .addComponent(nameTextField)
                    .addComponent(categoryTextField)
                    .addComponent(modelTextField)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 256, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(changeButton, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(changeTextField))
                .addGap(12, 12, 12)
                .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 368, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nameLabel)
                            .addComponent(nameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(categoryLabel)
                            .addComponent(categoryTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelLabel)
                            .addComponent(modelTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(8, 8, 8)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(packageLabel)
                            .addComponent(packageTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(priceLabel)
                            .addComponent(priceTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(nationLabel)
                            .addComponent(nationTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(currentLabel)
                                    .addComponent(currentTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(expiredateLabel)
                                    .addComponent(expiredateTextField, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(9, 9, 9)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(descriptionLabel)
                                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                .addComponent(changeTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(3, 3, 3)
                                .addComponent(changeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(imageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 490, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void currentTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_currentTextFieldKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_currentTextFieldKeyTyped

    private void priceTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_priceTextFieldKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())&& evt.getKeyChar() != '.'){
            evt.consume();
        }
    }//GEN-LAST:event_priceTextFieldKeyTyped

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        ((inventory.itemPage.ItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"))).loadDataByName("");
        //inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"));
        if(this.originalName != null && !this.originalName.trim().equals("")){
            ((inventory.itemPage.ItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"))).setSelectedListItem(originalName);
        }
        
        if(javax.swing.SwingUtilities.getWindowAncestor(this) !=null && javax.swing.SwingUtilities.getWindowAncestor(this) instanceof javax.swing.JFrame){
           ((inventory.core.ShowingFrame)javax.swing.SwingUtilities.getWindowAncestor(this)).dispose();
        }
        
        //inventory.core.ProjectBOMStockMain.display.dispose();
        //this.clearElements();    
    }//GEN-LAST:event_backButtonActionPerformed

    private void changeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_changeButtonActionPerformed
        // TODO add your handling code here:
        //Integer type = 0;
        if(JOptionPane.showConfirmDialog(this, "This will be saved. \""+((javax.swing.JButton)evt.getSource()).getLabel()+"\" Number is \n\n\"\""+this.changeTextField.getText()+"\"\"\n\n Are you Sure?!","Confirm",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.CANCEL_OPTION){
            return;
        }
        
        Integer type = 0;
        if(((javax.swing.JButton)evt.getSource()).getText().equals("Deduct")){
            try {
                String sql = "SELECT current-"+this.changeTextField.getText()+" FROM inventory.item where id = "+this.id+";";
                
                ResultSet rs = inventory.core.DBConnection.executeQuery(sql);
                
                if(rs.next()){
                    int remainder = rs.getInt(1);
                    if(remainder < 0){
                        JOptionPane.showMessageDialog(this, "Remainder will be under 0, please retype the value","Alert",JOptionPane.OK_OPTION);
                        return;
                    }
                }
                
                type = 2;
            } catch (SQLException ex) {
                Logger.getLogger(ItemChange.class.getName()).log(Level.SEVERE, null, ex);
            }
        }else if(((javax.swing.JButton)evt.getSource()).getText().equals("Add")){
            type = 1;
        }
        //INSERT INTO `inventory`.`change` (`item_id`, `amount`, `changetype_id`, `editor_id`) VALUES ('1', '1', '1', '1');

        String sql = "INSERT INTO `inventory`.`change` (`item_id`, `amount`, `changetype_id`, `editor_id`, `date`) VALUES ('"+this.id+"', '"+this.changeTextField.getText()+"', '"+type+"', '"+inventory.core.MainFrame.user_id+"', now());";
        inventory.core.DBConnection.updateQuery(sql);
        
        switch(type){
            case 1 : 
                sql = "UPDATE `inventory`.`item` SET `current`=`current`+"+this.changeTextField.getText()+" WHERE `id`='"+this.id+"';";
                inventory.core.DBConnection.updateQuery(sql);
                System.out.println("ADD");
                break;
            case 2 : 
                sql = "UPDATE `inventory`.`item` SET `current`=`current`-"+this.changeTextField.getText()+" WHERE `id`='"+this.id+"';";
                inventory.core.DBConnection.updateQuery(sql);
                System.out.println("DEDUCT");
                break;
        }
        clearElements();
        
        ((inventory.itemPage.ItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf(inventory.core.ProjectBOMStockMain.getMainFrame().getTitle()))).loadDataByName("");
        ((inventory.itemPage.ItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf(inventory.core.ProjectBOMStockMain.getMainFrame().getTitle()))).setSelectedListItem(this.nameTextField.getText());
        
        if(javax.swing.SwingUtilities.getWindowAncestor(this) !=null && javax.swing.SwingUtilities.getWindowAncestor(this) instanceof javax.swing.JFrame){
           ((inventory.core.ShowingFrame)javax.swing.SwingUtilities.getWindowAncestor(this)).dispose();
        }
        /*
        if(!(JOptionPane.showConfirmDialog(this, "Save was Done. Do you want to Continue?!","Alert",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)){
            ((inventory.itemPage.ItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"))).loadDataByName("");
            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"));
            if(this.originalName != null && !this.originalName.trim().equals("")){
                ((inventory.itemPage.ItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemManage"))).setSelectedListItem(originalName);
            }
        }*/
        
    }//GEN-LAST:event_changeButtonActionPerformed

    private void changeTextFieldKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_changeTextFieldKeyTyped
        // TODO add your handling code here:
        if(!Character.isDigit(evt.getKeyChar())){
            evt.consume();
        }
    }//GEN-LAST:event_changeTextFieldKeyTyped

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton backButton;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JTextField categoryTextField;
    private javax.swing.JButton changeButton;
    private javax.swing.JTextField changeTextField;
    private javax.swing.JLabel currentLabel;
    private javax.swing.JTextField currentTextField;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JTextArea descriptionTextArea;
    private javax.swing.JLabel expiredateLabel;
    private javax.swing.JTextField expiredateTextField;
    private javax.swing.JLabel imageLabel;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JTextField modelTextField;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JTextField nameTextField;
    private javax.swing.JLabel nationLabel;
    private javax.swing.JTextField nationTextField;
    private javax.swing.JLabel packageLabel;
    private javax.swing.JTextField packageTextField;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JTextField priceTextField;
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
        this.categoryTextField.setBackground(transparent);
        this.changeButton.setBackground(transparent);
        this.changeTextField.setBackground(transparent);
        this.currentTextField.setBackground(transparent);
        this.descriptionScrollPane.setBackground(transparent);
        this.descriptionTextArea.setBackground(transparent);
        this.expiredateTextField.setBackground(transparent);
        this.modelTextField.setBackground(transparent);
        this.nameTextField.setBackground(transparent);
        this.nationTextField.setBackground(transparent);
        this.packageTextField.setBackground(transparent);
        this.priceTextField.setBackground(transparent);
        //throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
