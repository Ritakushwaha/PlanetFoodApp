/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package PlanetFoodGUI;

import PlanetFoodDAO.CategoriesDao;
import PlanetFoodDAO.ProductsDao;
import PlanetFoodPOJO.Products;
import PlanetFoodPOJO.UserProfile;
import java.awt.Color;
import java.awt.Font;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rita
 */
public class ViewMenuFrame extends javax.swing.JFrame {
    private HashMap<String,String> categories=new HashMap <> ();
    private HashMap<String,Products> productList=new HashMap <> ();
    /**
     * Creates new form ViewMenuFrame
     */
    public ViewMenuFrame() {
        initComponents();
        this.setLocationRelativeTo(null);
        lblUsername1.setText("Hello "+UserProfile.getUserName());
        showDataInTable();
        loadCategory();
    }
    
    public void loadCategory(){
     try
        {
           categories=CategoriesDao.getAllCategoryId();
           for(String catName: categories.keySet())
                   jcCatId.addItem(catName);    
        }
        catch(SQLException ex){
                JOptionPane.showMessageDialog(null, "Exception in DB","Exception!",JOptionPane.ERROR_MESSAGE); 
                ex.printStackTrace();
        }
    }
    
    public void showDataInTable(){
        try
        {
        ArrayList<Products> productList=ProductsDao.getAllData();
        if(productList.isEmpty()==true)
          JOptionPane.showMessageDialog(null, "Sorry! No products present","Error!",JOptionPane.INFORMATION_MESSAGE);  
        else
        {
            Object[] rows=new Object[5];
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            for(Products p: productList)
            {
                rows[0]=p.getProdId();
                rows[1]=p.getCatId();
                rows[2]=p.getProductName();
                rows[3]=p.getProductPrice();
                rows[4]=p.getIsActive();
                model.addRow(rows);
            }
            
        }
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
          ex.printStackTrace();  
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

        jPanel1 = new javax.swing.JPanel();
        jPanel10 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        lblLogout1 = new javax.swing.JLabel();
        lblUsername1 = new javax.swing.JLabel();
        btnBack = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jcCatId = new javax.swing.JComboBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        jPanel10.setBackground(new java.awt.Color(0, 0, 51));

        jLabel11.setFont(new java.awt.Font("Showcard Gothic", 1, 36)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("View Menu Frame");

        lblLogout1.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        lblLogout1.setForeground(new java.awt.Color(255, 255, 255));
        lblLogout1.setText("Logout");
        lblLogout1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lblLogout1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                lblLogout1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                lblLogout1MouseExited(evt);
            }
        });

        lblUsername1.setFont(new java.awt.Font("Showcard Gothic", 0, 14)); // NOI18N
        lblUsername1.setForeground(new java.awt.Color(255, 255, 255));
        lblUsername1.setText("Hello ");

        javax.swing.GroupLayout jPanel10Layout = new javax.swing.GroupLayout(jPanel10);
        jPanel10.setLayout(jPanel10Layout);
        jPanel10Layout.setHorizontalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(lblUsername1)
                .addGap(253, 253, 253)
                .addComponent(jLabel11)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 255, Short.MAX_VALUE)
                .addComponent(lblLogout1)
                .addGap(42, 42, 42))
        );
        jPanel10Layout.setVerticalGroup(
            jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel10Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(jPanel10Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(lblUsername1, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblLogout1))
                .addContainerGap(23, Short.MAX_VALUE))
        );

        btnBack.setBackground(new java.awt.Color(0, 51, 102));
        btnBack.setFont(new java.awt.Font("Tw Cen MT", 1, 18)); // NOI18N
        btnBack.setForeground(new java.awt.Color(255, 255, 255));
        btnBack.setText("Back");
        btnBack.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBackActionPerformed(evt);
            }
        });

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Product Id", "Category Id", "Product Name", "Product Price", "Is Active"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        jcCatId.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Select Category" }));
        jcCatId.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcCatIdActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel10, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(410, 410, 410)
                        .addComponent(btnBack, javax.swing.GroupLayout.PREFERRED_SIZE, 118, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(56, 56, 56)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 877, Short.MAX_VALUE)
                            .addComponent(jcCatId, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jPanel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(77, 77, 77)
                .addComponent(jcCatId, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(71, 71, 71)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 209, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 62, Short.MAX_VALUE)
                .addComponent(btnBack)
                .addGap(29, 29, 29))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void lblLogout1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogout1MouseClicked
        this.dispose();
        LoginFrame login=new LoginFrame();
        login.setVisible(true);        // TODO add your handling code here:
    }//GEN-LAST:event_lblLogout1MouseClicked

    private void lblLogout1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogout1MouseEntered
        lblLogout1.setForeground(Color.red);
        Font f=new Font("Showcard Gothic",Font.BOLD,12);
        lblLogout1.setFont(f);// TODO add your handling code here:
    }//GEN-LAST:event_lblLogout1MouseEntered

    private void lblLogout1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lblLogout1MouseExited
        lblLogout1.setForeground(Color.white);
        Font f=new Font("Showcard Gothic",Font.ITALIC,12);
        lblLogout1.setFont(f);// TODO add your handling code here:
    }//GEN-LAST:event_lblLogout1MouseExited

    private void btnBackActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBackActionPerformed
        CashierOptionFrame admin=new CashierOptionFrame();
        admin.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnBackActionPerformed

    private void jcCatIdActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcCatIdActionPerformed
        if(jcCatId.getSelectedItem()==null)
            return;
        try
        {
        String catName=jcCatId.getSelectedItem().toString(); 
        String catId=categories.get(catName);
        ArrayList<Products> prodList=ProductsDao.getAllDataById(catId);
        if(prodList.isEmpty()==true )
          JOptionPane.showMessageDialog(null, "Sorry! Products present","Error!",JOptionPane.INFORMATION_MESSAGE);  
        else
        {
            Object[] rows=new Object[5];
            DefaultTableModel model=(DefaultTableModel)jTable1.getModel();
            int x=model.getRowCount();
            for(int i=x-1; i>=0;i--)
                model.removeRow(i);
            for(Products p: prodList)
            {
                rows[0]=p.getProdId();
                rows[1]=p.getCatId();
                rows[2]=p.getProductName();
                rows[3]=p.getProductPrice();
                rows[4]=p.getIsActive();
                model.addRow(rows);
            }
        }
        }
        catch(SQLException ex)
        {
          JOptionPane.showMessageDialog(null, "Error while connecting to DB!","Exception!",JOptionPane.ERROR_MESSAGE);
          ex.printStackTrace();  
        }
        
    }//GEN-LAST:event_jcCatIdActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ViewMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ViewMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ViewMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ViewMenuFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ViewMenuFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnBack;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JComboBox jcCatId;
    private javax.swing.JLabel lblLogout1;
    private javax.swing.JLabel lblUsername1;
    // End of variables declaration//GEN-END:variables
}