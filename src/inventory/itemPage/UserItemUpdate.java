/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.itemPage;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
 
/**
 *
 * @author Liz
 */
public class UserItemUpdate extends javax.swing.JPanel {
    private static ArrayList<Integer> QtyArrayList = new ArrayList<>();
    private ArrayList<Integer> IdArrayList = null;
    String recieptNum = null;
   
    /**
     * Creates new form UserItemUpdate
     */
    public UserItemUpdate() {
        initComponents();
//        UserItemUpdate.QtyArrayList = new ArrayList<>();
        this.IdArrayList = new ArrayList<>();
        ArrayList<Integer> QtyArrayList = new ArrayList<>();
    }
    
    public void setElements(Integer id){
        this.IdArrayList.add(id);
        this.reloadDataProcess();
        
    }
    
    private void reloadDataProcess(){
        this.deductTable = new JTable();
        
        javax.swing.table.DefaultTableModel dtm = new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item List", "Code", "Remain", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        };
        
        dtm.addTableModelListener(new TableModelListener(){

            @Override
            public void tableChanged(TableModelEvent e) {
//                throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
                if(e.getType() == TableModelEvent.UPDATE){
//                    System.out.println(UserItemUpdate.QtyArrayList.size());
//                    System.out.println(UserItemUpdate.this.deductTable.getSelectedRow());
//                    System.out.println(Integer.parseInt((String)UserItemUpdate.this.deductTable.getModel().getValueAt(UserItemUpdate.this.deductTable.getSelectedRow(), 2)));                    
                    try{
                        Integer k = (Integer)UserItemUpdate.this.deductTable.getModel().getValueAt(UserItemUpdate.this.deductTable.getSelectedRow(), 3);
                        UserItemUpdate.QtyArrayList.set(UserItemUpdate.this.deductTable.getSelectedRow(), k);
                        UserItemUpdate.this.reloadDataProcess();
                    }catch(Exception exc){
                        UserItemUpdate.this.reloadDataProcess();
                    }
                }
            }
        });
        
        deductTable.setModel(dtm);
        jScrollPane1.setViewportView(deductTable);
        deductTable.getColumnModel().getColumn(0).setPreferredWidth(300);
        deductTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        deductTable.getColumnModel().getColumn(2).setPreferredWidth(70);
        deductTable.getColumnModel().getColumn(3).setPreferredWidth(70);      

        for(int i = 0; i < this.IdArrayList.size(); i++){
            try {
                 String sql = "SELECT item.current as remain, variety.name as vname, CONCAT(category.code, LPAD(variety.varietyNumber,2,'0'), LPAD(item.itemNumber,3,'0')) as wcode "
                    + "FROM inventory.category as category "
                    + "JOIN inventory.item as item "
                    + "JOIN inventory.variety as variety "
                    + "ON category.id = item.category_id AND item.variety_id = variety.id where item.id = '"+this.IdArrayList.get(i)+"';";

                 ResultSet rs = inventory.core.DBConnection.executeQuery(sql);

                 if(rs != null){
                     while(rs.next()){
                        if(UserItemUpdate.QtyArrayList.size()<=i){
                            dtm.addRow(new Object [] { rs.getString("vname"), rs.getString("wcode"), rs.getInt("remain"), 0});
                            UserItemUpdate.QtyArrayList.add(0);
                        }else{
                            dtm.addRow(new Object [] { rs.getString("vname"), rs.getString("wcode"), rs.getInt("remain"), UserItemUpdate.QtyArrayList.get(i)});
                        }
                    }    
                 }
            }catch(SQLException ex){
              Logger.getLogger(UserItemUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        DeductButton = new javax.swing.JButton();
        CancelButton = new javax.swing.JButton();
        deleteButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        deductTable = new javax.swing.JTable();
        clearButton1 = new javax.swing.JButton();

        DeductButton.setText("Deduct");
        DeductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeductButtonActionPerformed(evt);
            }
        });

        CancelButton.setText("Cancel");
        CancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelButtonActionPerformed(evt);
            }
        });

        deleteButton.setText("Delete");
        deleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deleteButtonActionPerformed(evt);
            }
        });

        deductTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item List", "Code", "Remain", "Qty"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Object.class, java.lang.Object.class, java.lang.Integer.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, true
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        deductTable.setRequestFocusEnabled(false);
        jScrollPane1.setViewportView(deductTable);
        deductTable.getColumnModel().getColumn(0).setPreferredWidth(200);
        deductTable.getColumnModel().getColumn(1).setPreferredWidth(150);
        deductTable.getColumnModel().getColumn(3).setPreferredWidth(70);

        clearButton1.setText("Clear");
        clearButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearButton1ActionPerformed(evt);
            }
        });

        org.jdesktop.layout.GroupLayout layout = new org.jdesktop.layout.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 420, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(DeductButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, 101, Short.MAX_VALUE)
                    .add(CancelButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(deleteButton, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .add(clearButton1, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING)
            .add(layout.createSequentialGroup()
                .add(10, 10, 10)
                .add(layout.createParallelGroup(org.jdesktop.layout.GroupLayout.LEADING, false)
                    .add(layout.createSequentialGroup()
                        .add(3, 3, 3)
                        .add(DeductButton, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 52, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(deleteButton)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED)
                        .add(clearButton1)
                        .addPreferredGap(org.jdesktop.layout.LayoutStyle.RELATED, org.jdesktop.layout.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .add(CancelButton))
                    .add(jScrollPane1, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE, 214, org.jdesktop.layout.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(10, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void CancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelButtonActionPerformed
        // TODO add your handling code here:
            if(inventory.core.ProjectBOMStockMain.display != null){
            inventory.core.ProjectBOMStockMain.display.dispose();
        }
        inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("UserItemManage"));
        clearElement();
    }//GEN-LAST:event_CancelButtonActionPerformed

    private void deleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deleteButtonActionPerformed
        // TODO add your handling code here:
     if(this.deductTable != null && this.deductTable.getSelectedRow() >= 0 && this.deductTable.getSelectedRow() < this.IdArrayList.size()){
            this.IdArrayList.remove(this.deductTable.getSelectedRow());
            UserItemUpdate.QtyArrayList.remove(this.deductTable.getSelectedRow());
            this.reloadDataProcess();
        }
    }//GEN-LAST:event_deleteButtonActionPerformed

    private void DeductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeductButtonActionPerformed
        // TODO add your handling code here:
     String sql = null;

        for(int i=0; i< this.IdArrayList.size(); i++){
            try {
                sql = "SELECT current-"+this.deductTable.getValueAt(i,3)+" FROM inventory.item where id = "+this.IdArrayList.get(i)+";";
                            
                ResultSet rs = inventory.core.DBConnection.executeQuery(sql);
                            
                if(rs.next()){
                    int remainder = rs.getInt(1);
                    if(remainder < 0){
                        JOptionPane.showMessageDialog(this, "\""+this.deductTable.getValueAt(i,0)+"\"\nRemainder will be under 0, please retype the value","Alert",JOptionPane.OK_OPTION);
                        return;
                    }
                }
            } catch (SQLException ex) {
                Logger.getLogger(UserItemUpdate.class.getName()).log(Level.SEVERE, null, ex);
            }
        }            
        
        
        if(!(JOptionPane.showConfirmDialog(this, "This table will be affected, Are you \"Sure\"","Alert",JOptionPane.OK_CANCEL_OPTION) == JOptionPane.OK_OPTION)){
            return;
        }
        else {
            recieptNum = JOptionPane.showInputDialog(this,"Please Type a Reciept # or Register Key", "Reciept",JOptionPane.OK_CANCEL_OPTION);           
            if(recieptNum == null || recieptNum.trim().equals("")){
               return ;
            }
        }
        
        for(int i=0; i< this.IdArrayList.size(); i++){
            Integer k = (Integer)UserItemUpdate.this.deductTable.getModel().getValueAt(i, 3);
            
            if(k > 0){
                sql = "INSERT INTO `inventory`.`change` (`item_id`, `amount`, `changetype_id`, `editor_id`, `date`,`register_key`) VALUES ('"+this.IdArrayList.get(i)+"', '"+this.deductTable.getValueAt(i,3) +"', '"+2+"', '"+inventory.core.MainFrame.user_id+"', now(),'"+this.recieptNum+"');"; 
                inventory.core.DBConnection.updateQuery(sql);

                sql = "UPDATE `inventory`.`item` SET `current`=`current`-"+this.deductTable.getValueAt(i,3)+" WHERE `id`='"+this.IdArrayList.get(i)+"';";
                inventory.core.DBConnection.updateQuery(sql);

            }
        }
        
        ((inventory.itemPage.UserItemManage)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("UserItemManage"))).loadData();
        this.reloadDataProcess();
        JOptionPane.showMessageDialog(this, "Success","Alarm",JOptionPane.OK_OPTION);
               
    }//GEN-LAST:event_DeductButtonActionPerformed

    private void clearButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_clearButton1ActionPerformed
        // TODO add your handling code here:
        clearElement();    
    }//GEN-LAST:event_clearButton1ActionPerformed

    private void clearElement(){
        this.IdArrayList = new ArrayList();
        UserItemUpdate.QtyArrayList = new ArrayList();
        this.reloadDataProcess(); 
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelButton;
    private javax.swing.JButton DeductButton;
    private javax.swing.JButton clearButton1;
    private javax.swing.JTable deductTable;
    private javax.swing.JButton deleteButton;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables

}

