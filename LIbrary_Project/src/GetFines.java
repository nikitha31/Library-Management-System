import java.sql.*;
import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.*;
import javax.swing.table.TableRowSorter;

import net.proteanit.sql.DbUtils;


/**
 *
 * @author Nikitha
 */
public class GetFines extends javax.swing.JFrame {

    /**
     * Creates new form GetFines
     */
    public GetFines() {
        initComponents();
    }
    private TableRowSorter sorter;
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        SearchTextBox = new javax.swing.JTextField(15);
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        fineTable = new javax.swing.JTable();
        clearFineButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        SearchTextBox.setToolTipText("Search");
        SearchTextBox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchTextBoxActionPerformed(evt);
            }
        });

        jLabel1.setText("Search");

        fineTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        fineTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                fineTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(fineTable);

        clearFineButton.setText("Clear Fine");
        clearFineButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                clearFineButtonActionPerformed(evt);
            }
        });

        backButton.setText("Back");
        backButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                backButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(86, 86, 86)
                        .addComponent(jLabel1)
                        .addGap(35, 35, 35)
                        .addComponent(SearchTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(153, 153, 153)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(clearFineButton)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(backButton)))))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(SearchTextBox, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(clearFineButton)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(backButton)
                .addGap(13, 13, 13))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        try{
            //JDBC Connection
            HomeFrame hm = new HomeFrame();
            hm.DateInNull();
            hm.DateInNotNull();
            Connection conn = ConnectionToDB.getConnection();
            Statement st = conn.createStatement();
            String query = "select forsum.card_no,forsum.Total_fine_amount from (select CARD_NO,SUM(FINE_AMOUNT)as Total_fine_amount from (select * from FINE where fine_paid=0) f left join BOOK_LOANS bl on f.loan_id=bl.loan_id GROUP BY CARD_NO ) as forsum left join borrower b on forsum.card_no=b.card_no;";
            System.out.println(query);
            ResultSet rs = st.executeQuery(query);
           
            System.out.println("Count"+rs);
            fineTable.setModel(DbUtils.resultSetToTableModel(rs));
            rs.close();
    
            }
            catch(Exception e){
                    e.printStackTrace();
            }

            sorter = new TableRowSorter<>(fineTable.getModel());
            fineTable.setRowSorter(sorter);
            SearchTextBox.getDocument().addDocumentListener(new DocumentListener() {
         @Override
         public void insertUpdate(DocumentEvent e) {
            search(SearchTextBox.getText());
         }
         @Override
         public void removeUpdate(DocumentEvent e) {
            search(SearchTextBox.getText());
         }
         @Override
         public void changedUpdate(DocumentEvent e) {
            search(SearchTextBox.getText());
         }
         public void search(String str) {
            if (str.length() == 0) {
               sorter.setRowFilter(null);
            } else {
               sorter.setRowFilter(RowFilter.regexFilter(str));
            }
         }
      });

        pack();
    }// </editor-fold>                        

    private void SearchTextBoxActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:

    }                                             

    private void clearFineButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                
        // TODO add your handling code here:
        try{
            //JDBC Connection
            Connection conn = ConnectionToDB.getConnection();
            Statement st = conn.createStatement();
            String query = "update FINE SET FINE_PAID=1 WHERE LOAN_ID IN (SELECT LOAN_ID FROM BOOK_LOANS WHERE CARD_NO = \""+selectedCardNumber+"\" AND DATE_IN IS NOT NULL );";
             System.out.println(query);
            int isSuccess = st.executeUpdate(query);

            if(isSuccess==1)
            {
                JOptionPane.showMessageDialog(null, "Success! Cleared fine for all returned books");
                HomeFrame hf = new HomeFrame();
                this.setVisible(false);
                hf.setVisible(true);

            }
            else{
                JOptionPane.showMessageDialog(null, "Error! Please Try Again");

            }
    
            }
            catch(Exception e){
                    e.printStackTrace();
            }

    }                                               

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        HomeFrame hf = new HomeFrame();
        this.setVisible(false);
        hf.setVisible(true);
    }                                          
    String selectedCardNumber;
    private void fineTableMouseClicked(java.awt.event.MouseEvent evt) {                                       
        // TODO add your handling code here:
        int row = fineTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)fineTable.getModel();
        selectedCardNumber = model.getValueAt(row, 0).toString();
       
    
        
    }                                      

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
            java.util.logging.Logger.getLogger(GetFines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GetFines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GetFines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GetFines.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GetFines().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JTextField SearchTextBox;
    private javax.swing.JButton backButton;
    private javax.swing.JButton clearFineButton;
    private javax.swing.JTable fineTable;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration                   
}
