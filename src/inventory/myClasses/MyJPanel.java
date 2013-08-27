/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.myClasses;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;

/**
 *
 * @author paul
 */
public abstract class MyJPanel extends javax.swing.JPanel implements MyPaneInterface{
    private BufferedImage img = null;
//    private String imgSource = "/inventory/itemImage/Image.jpg";
    private String imgSource = "Image.jpg";
    
    public MyJPanel(){
        super();
        
        myInitComponents();
        LoadData();
        
        setComponetsColor(new Color(255,255,255,152));
        
//        if(imgSource != null){
//            this.setImgSource(imgSource);
//        }
    }
    
    protected abstract void myInitComponents();
    
    public abstract void LoadData();
    
    public abstract void setComponetsColor(Color transparent);
    
    public void setImgSource(String src){
        try {
//            URL resource = null;
//            resource = this.getClass().getResource(src);
//            this.setMyImage(resource.toURI());
//            
            this.setMyImage(src);
        } catch (Exception ex) {
            System.out.println(this.getClass().getResource(src));
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // paint the background image and scale it to fill the entire space
        if(img != null){
            g.drawImage(img, 0, 0, getWidth(), getHeight(), this);
        }
    }
    
    public void setMyImage(String address){
        try {
            InputStream is = this.getClass().getResourceAsStream("Image.jpg");
            
            img = ImageIO.read(is);
            //img = ImageIO.read(new File(address));
            //makeTransparency(152, img);
        } catch (IOException ex) {
            System.out.println(this.getClass().getResource("Image.jpg"));;
            Logger.getLogger(this.getClass().getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void makeTransparency(int trans, BufferedImage img){
        int width = img.getWidth();
        int height = img.getHeight();
        
        for(int i = 0; i < height; i++){
            for(int j = 0; j < width; j++){
                int rgb = img.getRGB(j, i);
                int b = rgb & 0x000000FF;
                rgb = rgb >> 8;
                int g = rgb & 0x000000FF;
                rgb = rgb >> 8;
                int r = rgb & 0x000000FF;
                img.setRGB(j, i, new Color(r, g, b, trans).getRGB());
            }
        }
    }
}
