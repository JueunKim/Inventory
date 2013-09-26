/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.core;


import java.awt.BorderLayout;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 *
 * @author Kind
 */
public class MainFrame extends javax.swing.JFrame {

    /**
     * Creates new form MainFrame
     */
    public MainFrame() {
        initPage();
    }
    
    public static Integer user_id = 0;
    public static Integer role = 0;
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    private void myInitComponents() {
        //jPanel1 = new javax.swing.JPanel();
        getContentPane().removeAll();
        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
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
        
        //setLayout(new BorderLayout());
        //setContentPane(new JLabel(new ImageIcon("/home/paul/workspace/Java/netbeans/ProjectBOMStock/referencedData/Image.jpg")));
        
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
        
        this.setLocation(0, 0);
        //this.setLocation(-1600, 100);
        
        pack();
    }
    
    public void setPage(int i){
        if(i >= 0 && i < myPanelList.size()){
            if(myPanel != null)
                myPanelPast = inventory.core.ProjectBOMStockMain.PageList.get(myPanelList.indexOf(myPanel));
            myPanel = myPanelList.get(i);
            myInitComponents();
            this.setTitle(inventory.core.ProjectBOMStockMain.PageList.get(i));
            
            this.setVisible(true);
            this.setResizable(false);
        }else{
            /*
             todo
             * Error Handling
             * out of boundary
             */
            
        }
    }
    
    public javax.swing.JPanel getPage(int i){
        return myPanelList.get(i);
    }
    
    private static void initPage(){
        myPanelList = new ArrayList<javax.swing.JPanel>();
    }
    
    public static void addPage(javax.swing.JPanel panel){
        myPanelList.add(panel);
    }
    
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 122, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    private static ArrayList<javax.swing.JPanel> myPanelList = null;
    public static String myPanelPast = null;
    private static javax.swing.JPanel myPanel = null;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
