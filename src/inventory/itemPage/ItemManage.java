/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inventory.itemPage;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListCellRenderer;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.ListCellRenderer;
import javax.swing.text.Position;

/**
 *
 * @author Kind
 */
public class ItemManage extends javax.swing.JPanel {
    private ArrayList<Integer> id;
    private ArrayList<String> itemNameArrayList;
    private ArrayList<String> categoryNameArrayList;
    private ArrayList<String> modelNameArrayList;
    private ArrayList<Integer> packageArrayList;
    private ArrayList<Float> priceArrayList;
    private ArrayList<Integer> currentArrayList;
    private ArrayList<Float> totalPriceArrayList;
    private ArrayList<Date> expireDateArrayList;
    private ArrayList<String> descriptionArrayList;
    private ArrayList<String> nationArrayList;
    
    private String order_by = "itemname";
    private String order = "DESC";
    /**
     * Creates new form ItemManage
     */
    public ItemManage() {
        initComponents();
        this.loadDataByName(this.searchByNameTextField.getText());
    }

    public void loadDataByName(String name) {
        if(inventory.core.MainFrame.role == 1){
            this.registerButton.setVisible(true);
            this.dropButton.setVisible(true);
            this.editButton.setVisible(true);
            this.jSeparator1.setVisible(true);
            this.jSeparator3.setVisible(true);
            this.jSeparator4.setVisible(true);
            this.addButton.setVisible(true);
        }else{
            this.registerButton.setVisible(false);
            this.dropButton.setVisible(false);
            this.editButton.setVisible(false);
            this.jSeparator1.setVisible(false);
            this.jSeparator3.setVisible(false);
            this.jSeparator4.setVisible(false);
            this.addButton.setVisible(false);
        }
        
        this.id = new ArrayList<Integer>();
        this.itemNameArrayList = new ArrayList<String>();
        this.categoryNameArrayList = new ArrayList<String>();
        this.modelNameArrayList =  new ArrayList<String>();
        this.packageArrayList =  new ArrayList<Integer>();
        this.priceArrayList = new ArrayList<Float>();
        this.currentArrayList =  new ArrayList<Integer>();
        this.totalPriceArrayList = new ArrayList<Float>();
        this.expireDateArrayList = new ArrayList<Date>();
        this.descriptionArrayList = new ArrayList<String>();
        this.nationArrayList = new ArrayList<String>();
        //this.modelTextPane.setText("");
        
        ArrayList<String> priceOnListArrayList = new ArrayList<String>();
        ArrayList<String> totalPriceOnListArrayList = new ArrayList<String>();
        
        //SELECT item.id,item.name as itemname, category.name as categoryname, model.name as modelname, package.count, item.price, nation.name as nationname, item.current, item.price*item.current as total, item.expiredate, item.description 
        //FROM inventory.item as item inner join inventory.nation as nation inner join inventory.package as package inner join inventory.model as model inner join inventory.category as category 
        //ON item.category_id = nation.id and item.model_id = model.id and item.package_id = package.id and item.category_id = category.id where item.name like '%%' ;
        
        //SELECT item.id,item.name as itemname, category.name as categoryname, model.name as modelname, package.count, item.price, nation.name as nationname, item.current, item.price*item.current as total, item.expiredate, item.description FROM inventory.item as item inner join inventory.nation as nation inner join inventory.package as package inner join inventory.model as model inner join inventory.category as category ON item.category_id = nation.id and item.model_id = model.id and item.package_id = package.id and item.category_id = category.id where item.name like '%%' order by item.name;
        try {
            ResultSet rs = null;
            
            String sql = "SELECT item.id,item.name as itemname, category.name as categoryname, model.name as modelname, package.count, item.price, nation.name as nationname, item.current, item.price*item.current as total, item.expiredate, item.description FROM inventory.item as item join inventory.nation as nation join inventory.package as package join inventory.model as model join inventory.category as category ON item.nation_id = nation.id and item.model_id = model.id and item.package_id = package.id and inventory.item.category_id = inventory.category.id where item.name like '%"+name+"%' order by "+order_by+" "+order+";";
            
            rs = inventory.core.DBConnection.excuteQuery(sql);  
            
            if(rs != null){
                while(rs.next()){
                    this.id.add(rs.getInt("id"));
                    this.itemNameArrayList.add(rs.getString("itemname"));
                    this.categoryNameArrayList.add(rs.getString("categoryname"));
                    this.modelNameArrayList.add(rs.getString("modelname"));
                    this.packageArrayList.add(rs.getInt("count"));
                    this.priceArrayList.add(rs.getFloat("price"));
                    this.nationArrayList.add(rs.getString("nationname"));
                    this.totalPriceArrayList.add(rs.getFloat("total"));
                    String priceAndNation = null;
                    String totalPriceAndNation = null;
                    if(this.nationArrayList.get(this.nationArrayList.size()-1).equals("South Korea")){
                        priceAndNation = (this.priceArrayList.get(this.priceArrayList.size()-1))+" W";
                        totalPriceAndNation = (this.totalPriceArrayList.get(this.totalPriceArrayList.size()-1))/10000+" W";
                    }else if(this.nationArrayList.get(this.nationArrayList.size()-1).equals("Malawi")){
                        priceAndNation = (this.priceArrayList.get(this.priceArrayList.size()-1))+" MK";
                        totalPriceAndNation = (this.totalPriceArrayList.get(this.totalPriceArrayList.size()-1))+" MK";
                    }
                    priceOnListArrayList.add(priceAndNation);
                    totalPriceOnListArrayList.add(totalPriceAndNation);
                    
                    this.currentArrayList.add(rs.getInt("current"));
                    this.expireDateArrayList.add(rs.getDate("expiredate"));
                    this.descriptionArrayList.add(rs.getString("description"));
                }
            }
        } catch (SQLException ex) {
            //Logger.getLogger(CategoryManage.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        this.nameList.setListData(itemNameArrayList.toArray());
        this.categoryList.setListData(this.categoryNameArrayList.toArray());
        this.modelList.setListData(this.modelNameArrayList.toArray());
        this.packageList.setListData(this.packageArrayList.toArray());
        this.priceList.setListData(priceOnListArrayList.toArray());
        this.currentList.setListData(this.currentArrayList.toArray());
        this.totalPriceList.setListData(totalPriceOnListArrayList.toArray());
        this.expireDateList.setListData(this.expireDateArrayList.toArray());
        this.descriptionList.setListData(this.descriptionArrayList.toArray());
        
        this.expireDateList.setCellRenderer(new MyCellRenderer());
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        nameScrollPane = new javax.swing.JScrollPane();
        nameList = new javax.swing.JList();
        modelScrollPane = new javax.swing.JScrollPane();
        modelList = new javax.swing.JList();
        nameLabel = new javax.swing.JLabel();
        modelLabel = new javax.swing.JLabel();
        packageScrollPane = new javax.swing.JScrollPane();
        packageList = new javax.swing.JList();
        packageLabel = new javax.swing.JLabel();
        priceScrollPane = new javax.swing.JScrollPane();
        priceList = new javax.swing.JList();
        priceLabel = new javax.swing.JLabel();
        currentScrollPane = new javax.swing.JScrollPane();
        currentList = new javax.swing.JList();
        currentLabel = new javax.swing.JLabel();
        totalPriceScrollPane = new javax.swing.JScrollPane();
        totalPriceList = new javax.swing.JList();
        totalPriceLabel = new javax.swing.JLabel();
        backButton = new javax.swing.JButton();
        dropButton = new javax.swing.JButton();
        editButton = new javax.swing.JButton();
        registerButton = new javax.swing.JButton();
        searchByNameLabel = new javax.swing.JLabel();
        expireDateScrollPane = new javax.swing.JScrollPane();
        expireDateList = new javax.swing.JList();
        descriptionScrollPane = new javax.swing.JScrollPane();
        descriptionList = new javax.swing.JList();
        descriptionLabel = new javax.swing.JLabel();
        expireDateLabel = new javax.swing.JLabel();
        searchByNameTextField = new javax.swing.JTextField();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        categoryScrollPane = new javax.swing.JScrollPane();
        categoryList = new javax.swing.JList();
        categoryLabel = new javax.swing.JLabel();
        addButton = new javax.swing.JButton();
        deductButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        nameScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        nameScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
        nameScrollPane.setAutoscrolls(true);

        nameList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        nameList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                nameListValueChanged(evt);
            }
        });
        nameScrollPane.setViewportView(nameList);

        modelScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        modelScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        modelList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        modelList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelListMouseClicked(evt);
            }
        });
        modelList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                modelListValueChanged(evt);
            }
        });
        modelScrollPane.setViewportView(modelList);

        nameLabel.setText("Name List");
        nameLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nameLabelMouseClicked(evt);
            }
        });

        modelLabel.setText("Model");
        modelLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                modelLabelMouseClicked(evt);
            }
        });

        packageScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        packageScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        packageList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        packageList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                packageListValueChanged(evt);
            }
        });
        packageScrollPane.setViewportView(packageList);

        packageLabel.setText("Package");
        packageLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                packageLabelMouseClicked(evt);
            }
        });

        priceScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        priceScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        priceList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        priceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                priceListValueChanged(evt);
            }
        });
        priceScrollPane.setViewportView(priceList);

        priceLabel.setText("Price");
        priceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                priceLabelMouseClicked(evt);
            }
        });

        currentScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        currentScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        currentList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        currentList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                currentListValueChanged(evt);
            }
        });
        currentScrollPane.setViewportView(currentList);

        currentLabel.setText("Current Remain");
        currentLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                currentLabelMouseClicked(evt);
            }
        });

        totalPriceScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        totalPriceScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        totalPriceList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        totalPriceList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                totalPriceListValueChanged(evt);
            }
        });
        totalPriceScrollPane.setViewportView(totalPriceList);

        totalPriceLabel.setText("Total Price");
        totalPriceLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                totalPriceLabelMouseClicked(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });
        backButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                backButtonKeyTyped(evt);
            }
        });

        dropButton.setText("Drop");
        dropButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropButtonActionPerformed(evt);
            }
        });
        dropButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                dropButtonKeyTyped(evt);
            }
        });

        editButton.setText("Edit");
        editButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                editButtonActionPerformed(evt);
            }
        });
        editButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                editButtonKeyTyped(evt);
            }
        });

        registerButton.setText("Register");
        registerButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerButtonActionPerformed(evt);
            }
        });
        registerButton.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                registerButtonKeyTyped(evt);
            }
        });

        searchByNameLabel.setText("Search By Name");

        expireDateScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        expireDateScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        expireDateList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        expireDateList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                expireDateListValueChanged(evt);
            }
        });
        expireDateScrollPane.setViewportView(expireDateList);

        descriptionScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        descriptionScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        descriptionList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        descriptionList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                descriptionListValueChanged(evt);
            }
        });
        descriptionScrollPane.setViewportView(descriptionList);

        descriptionLabel.setText("Description");

        expireDateLabel.setText("ExpireDate");
        expireDateLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                expireDateLabelMouseClicked(evt);
            }
        });

        searchByNameTextField.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                searchByNameTextFieldKeyReleased(evt);
            }
        });

        categoryScrollPane.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
        categoryScrollPane.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);

        categoryList.setModel(new javax.swing.AbstractListModel() {
            String[] strings = { "Item 1", "Item 2", "Item 3", "Item 4", "Item 5" };
            public int getSize() { return strings.length; }
            public Object getElementAt(int i) { return strings[i]; }
        });
        categoryList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryListMouseClicked(evt);
            }
        });
        categoryList.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                categoryListValueChanged(evt);
            }
        });
        categoryScrollPane.setViewportView(categoryList);

        categoryLabel.setText("Category");
        categoryLabel.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                categoryLabelMouseClicked(evt);
            }
        });

        addButton.setText("Add Current");
        addButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                addButtonActionPerformed(evt);
            }
        });

        deductButton.setText("Deduct Current");
        deductButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deductButtonActionPerformed(evt);
            }
        });

        jLabel1.setText("W = 10000");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(nameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(nameScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(categoryScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modelScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(packageScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(categoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(modelLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(packageLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(5, 5, 5)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(priceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentLabel)
                        .addGap(18, 18, 18)
                        .addComponent(totalPriceLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(priceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(currentScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(totalPriceScrollPane, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(expireDateScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 130, Short.MAX_VALUE)
                    .addComponent(expireDateLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(descriptionScrollPane, javax.swing.GroupLayout.DEFAULT_SIZE, 160, Short.MAX_VALUE)
                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(searchByNameLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(backButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(deductButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(addButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(searchByNameTextField, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(editButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(registerButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(dropButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(searchByNameLabel)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(searchByNameTextField, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(registerButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(editButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(dropButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(addButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(deductButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(190, 190, 190)
                        .addComponent(backButton, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addContainerGap()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                            .addComponent(nameLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(categoryLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(modelLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(packageLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(priceLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                            .addComponent(currentLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                                    .addGroup(layout.createSequentialGroup()
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                            .addGroup(layout.createSequentialGroup()
                                                .addGap(30, 30, 30)
                                                .addComponent(jLabel1))
                                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                    .addComponent(expireDateLabel, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                    .addComponent(descriptionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED))
                            .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(totalPriceLabel, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(totalPriceScrollPane, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 510, Short.MAX_VALUE)
                            .addComponent(currentScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(priceScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(packageScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(modelScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(nameScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(expireDateScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(descriptionScrollPane, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(categoryScrollPane))))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void nameListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_nameListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex()); 
    }//GEN-LAST:event_nameListValueChanged

    private void categoryListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_categoryListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_categoryListValueChanged

    private void modelListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_modelListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_modelListValueChanged

    private void packageListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_packageListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_packageListValueChanged

    private void priceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_priceListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_priceListValueChanged

    private void currentListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_currentListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_currentListValueChanged

    private void totalPriceListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_totalPriceListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_totalPriceListValueChanged

    private void descriptionListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_descriptionListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
            this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_descriptionListValueChanged

    private void modelListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelListMouseClicked
        // TODO add your handling code here:
        //System.out.println(evt.getClickCount());
        if(evt.getClickCount()==2){
            try {
                //this.categoryList.getSelectedValue().toString()
                String sql = "SELECT name, contact FROM inventory.model where name = '"+this.modelList.getSelectedValue().toString()+"';";
                ResultSet rs = inventory.core.DBConnection.excuteQuery(sql);
                
                if(rs.next()){
                    inventory.modelPage.ModelChange p = new inventory.modelPage.ModelChange();
                    p.setChangeConfig(0, rs.getString("name"), rs.getInt("contact"), "");
                    p.showMode(true);
                    
                    javax.swing.JFrame f = new inventory.core.ShowingFrame(p, "Model");
                    f.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ItemManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_modelListMouseClicked

    private void registerButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerButtonActionPerformed
        // TODO add your handling code here:
        ((inventory.itemPage.ItemUpdate)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemUpdate"))).setElements(0,"","","","");
        inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemUpdate"));
        //((inventory.itemPage.ItemUpdate)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemUpdate"))).setChangeConfig(null,"",null,"Register");
    }//GEN-LAST:event_registerButtonActionPerformed

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_backButtonActionPerformed
        // TODO add your handling code here:
        if(inventory.core.MainFrame.role == 1){
            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("AdminMain"));
        }else{
            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("Login"));
        }
    }//GEN-LAST:event_backButtonActionPerformed

    private void searchByNameTextFieldKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchByNameTextFieldKeyReleased
        // TODO add your handling code here:
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_searchByNameTextFieldKeyReleased

    private void editButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_editButtonActionPerformed
        // TODO add your handling code here:
        if(this.nameList.getSelectedIndex() >= 0){
            ((inventory.itemPage.ItemUpdate)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemUpdate"))).setElements(this.id.get(this.nameList.getSelectedIndex()),this.categoryList.getSelectedValue().toString(),this.modelList.getSelectedValue().toString(),this.nationArrayList.get(this.priceList.getSelectedIndex()),this.packageList.getSelectedValue().toString());
            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemUpdate"));
        }
    }//GEN-LAST:event_editButtonActionPerformed

    private void dropButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropButtonActionPerformed
        // TODO add your handling code here:
        //this.id.get(this.nameList.getSelectedIndex())
        if(this.nameList.getSelectedIndex()>=0){
            String name = null;
            if(JOptionPane.showConfirmDialog(this, "This will be Deleted!!!. Are you Sure?!","Confirm",JOptionPane.OK_CANCEL_OPTION)==JOptionPane.OK_OPTION){
                name = this.nameList.getSelectedValue().toString();
                inventory.core.DBConnection.updateQuery("DELETE FROM `inventory`.`item` WHERE `id`='"+this.id.get(this.nameList.getSelectedIndex())+"';");
                this.loadDataByName("");
                JOptionPane.showMessageDialog(this, name + " was Deleted.","Alert",JOptionPane.OK_OPTION);
            }
        }
    }//GEN-LAST:event_dropButtonActionPerformed

    private void deductButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deductButtonActionPerformed
        // TODO add your handling code here:
        if(this.nameList.getSelectedIndex() >= 0){
            ((inventory.itemPage.ItemChange)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemChange"))).setElements(this.id.get(this.nameList.getSelectedIndex()),this.categoryList.getSelectedValue().toString(),this.modelList.getSelectedValue().toString(),this.nationArrayList.get(this.priceList.getSelectedIndex()),this.packageList.getSelectedValue().toString(),"Deduct");
            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemChange"));
        }
    }//GEN-LAST:event_deductButtonActionPerformed

    private void expireDateListValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_expireDateListValueChanged
        // TODO add your handling code here:
        if(evt.getSource() instanceof javax.swing.JList)
        this.listChanged(((javax.swing.JList)evt.getSource()).getSelectedIndex());
    }//GEN-LAST:event_expireDateListValueChanged

    private void registerButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_registerButtonKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n')
            this.registerButtonActionPerformed(null);
    }//GEN-LAST:event_registerButtonKeyTyped

    private void editButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_editButtonKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n'){
            this.editButtonActionPerformed(null);
        }
    }//GEN-LAST:event_editButtonKeyTyped

    private void dropButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_dropButtonKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n'){
            this.dropButtonActionPerformed(null);
        }
    }//GEN-LAST:event_dropButtonKeyTyped

    private void backButtonKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_backButtonKeyTyped
        // TODO add your handling code here:
        if(evt.getKeyChar()=='\n'){
            this.backButtonActionPerformed(null);
        }
    }//GEN-LAST:event_backButtonKeyTyped

    private void orderByLabelClick(java.awt.event.MouseEvent evt){
        if(evt.getClickCount()>1){
            this.order = "DESC";
        }else{
            this.order = "ASC";
        }
    }
    
    private void nameLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nameLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "itemname";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_nameLabelMouseClicked

    private void categoryLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "categoryname";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_categoryLabelMouseClicked

    private void modelLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_modelLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "modelname";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_modelLabelMouseClicked

    private void packageLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_packageLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "count";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_packageLabelMouseClicked

    private void priceLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_priceLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "price";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_priceLabelMouseClicked

    private void currentLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_currentLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "current";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_currentLabelMouseClicked

    private void totalPriceLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_totalPriceLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "total";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_totalPriceLabelMouseClicked

    private void expireDateLabelMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_expireDateLabelMouseClicked
        // TODO add your handling code here:
        orderByLabelClick(evt);
        this.order_by = "expiredate";
        this.loadDataByName(this.searchByNameTextField.getText());
    }//GEN-LAST:event_expireDateLabelMouseClicked

    private void addButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_addButtonActionPerformed
        // TODO add your handling code here:
        if(this.nameList.getSelectedIndex() >= 0){
            ((inventory.itemPage.ItemChange)inventory.core.ProjectBOMStockMain.getPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemChange"))).setElements(this.id.get(this.nameList.getSelectedIndex()),this.categoryList.getSelectedValue().toString(),this.modelList.getSelectedValue().toString(),this.nationArrayList.get(this.priceList.getSelectedIndex()),this.packageList.getSelectedValue().toString(),"Add");
            inventory.core.ProjectBOMStockMain.setPage(inventory.core.ProjectBOMStockMain.PageList.indexOf("ItemChange"));
        }
    }//GEN-LAST:event_addButtonActionPerformed

    private void categoryListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_categoryListMouseClicked
        // TODO add your handling code here:
        if(evt.getClickCount()==2){
            try {
                //this.categoryList.getSelectedValue().toString()
                String sql = "SELECT name, description FROM inventory.category where name = '"+this.categoryList.getSelectedValue().toString()+"';";
                ResultSet rs = inventory.core.DBConnection.excuteQuery(sql);
                
                if(rs.next()){
                    inventory.categoryPage.CategoryEdit p = new inventory.categoryPage.CategoryEdit();
                    p.setEditConfig(0, rs.getString("name"), rs.getString("description"));
                    p.showMode(true);

                    javax.swing.JFrame f = new inventory.core.ShowingFrame(p, "Category");
                    f.setVisible(true);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ItemManage.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_categoryListMouseClicked
    
    public void setSelectedListItem(String name){
        //System.out.println(this.nameList.getNextMatch(name, 0, Position.Bias.Forward));
        listChanged(this.nameList.getNextMatch(name, 0, Position.Bias.Forward));
    }
    
    private void listChanged(Integer index){
        if(index < 0)
            return;
        this.nameList.setSelectedIndex(index);
        this.categoryList.setSelectedIndex(index);
        this.modelList.setSelectedIndex(index);
        this.packageList.setSelectedIndex(index);
        this.priceList.setSelectedIndex(index);
        this.currentList.setSelectedIndex(index);
        this.totalPriceList.setSelectedIndex(index);
        this.expireDateList.setSelectedIndex(index);
        this.descriptionList.setSelectedIndex(index);
        
        this.nameList.ensureIndexIsVisible(this.nameList.getSelectedIndex());
        this.categoryList.ensureIndexIsVisible(this.categoryList.getSelectedIndex());
        this.modelList.ensureIndexIsVisible(this.modelList.getSelectedIndex());
        this.packageList.ensureIndexIsVisible(this.packageList.getSelectedIndex());
        this.priceList.ensureIndexIsVisible(this.priceList.getSelectedIndex());
        this.currentList.ensureIndexIsVisible(this.currentList.getSelectedIndex());
        this.totalPriceList.ensureIndexIsVisible(this.totalPriceList.getSelectedIndex());
        this.expireDateList.ensureIndexIsVisible(this.expireDateList.getSelectedIndex());
        this.descriptionList.ensureIndexIsVisible(this.descriptionList.getSelectedIndex());
    }
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton addButton;
    private javax.swing.JButton backButton;
    private javax.swing.JLabel categoryLabel;
    private javax.swing.JList categoryList;
    private javax.swing.JScrollPane categoryScrollPane;
    private javax.swing.JLabel currentLabel;
    private javax.swing.JList currentList;
    private javax.swing.JScrollPane currentScrollPane;
    private javax.swing.JButton deductButton;
    private javax.swing.JLabel descriptionLabel;
    private javax.swing.JList descriptionList;
    private javax.swing.JScrollPane descriptionScrollPane;
    private javax.swing.JButton dropButton;
    private javax.swing.JButton editButton;
    private javax.swing.JLabel expireDateLabel;
    private javax.swing.JList expireDateList;
    private javax.swing.JScrollPane expireDateScrollPane;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JLabel modelLabel;
    private javax.swing.JList modelList;
    private javax.swing.JScrollPane modelScrollPane;
    private javax.swing.JLabel nameLabel;
    private javax.swing.JList nameList;
    private javax.swing.JScrollPane nameScrollPane;
    private javax.swing.JLabel packageLabel;
    private javax.swing.JList packageList;
    private javax.swing.JScrollPane packageScrollPane;
    private javax.swing.JLabel priceLabel;
    private javax.swing.JList priceList;
    private javax.swing.JScrollPane priceScrollPane;
    private javax.swing.JButton registerButton;
    private javax.swing.JLabel searchByNameLabel;
    private javax.swing.JTextField searchByNameTextField;
    private javax.swing.JLabel totalPriceLabel;
    private javax.swing.JList totalPriceList;
    private javax.swing.JScrollPane totalPriceScrollPane;
    // End of variables declaration//GEN-END:variables
}