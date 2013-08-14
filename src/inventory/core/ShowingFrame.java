/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.core;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 *
 * @author Kind
 */
public class ShowingFrame extends javax.swing.JFrame {

    /**
     * Creates new form ShowingFrame
     */
    public ShowingFrame(javax.swing.JPanel p, String title) {
        this.title = title;
        this.myPanel = p;
        setPage();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    
    /**
     * Creates new form MainFrame
     */
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void myInitComponents() {
        //jPanel1 = new javax.swing.JPanel();
        getContentPane().removeAll();
        
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(inventory.core.ProjectBOMStockMain.getMainFrame() != null){
                   inventory.core.ProjectBOMStockMain.getMainFrame().setEnabled(true);
                }
                ShowingFrame.this.dispose();
            }
        }
                );
/*        
        mainMenuBar = new javax.swing.JMenuBar();
        file = new javax.swing.JMenu();
        page = new javax.swing.JMenu();

        file.setText("File");
        mainMenuBar.add(file);

        page.setText("Page");
        mainMenuBar.add(page);

        setJMenuBar(mainMenuBar);
*/
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(myPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(myPanel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        
        this.setLocation(50, 50);
        //this.setLocation(-1600, 100);
        
        pack();
    }
    
    @Override
    public void dispose(){
        inventory.core.ProjectBOMStockMain.getMainFrame().setEnabled(true);
        super.dispose();
    }
    
    public void setPage(){
        if(myPanel != null){
            if(myPanel != null)
                myInitComponents();
            this.setTitle(title);
            inventory.core.ProjectBOMStockMain.getMainFrame().setEnabled(false);
        }else{
            /*
             todo
             * Error Handling
             * out of boundary
             */
            
        }
    }
    /**
     * @param args the command line arguments
     */
    public String title = null;
    private javax.swing.JPanel myPanel = null;
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
