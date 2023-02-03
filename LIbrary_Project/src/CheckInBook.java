import java.sql.*;
import javax.swing.*;

import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

import net.proteanit.sql.DbUtils;


public class CheckInBook extends javax.swing.JFrame {

    /**
     * Creates new form CheckInBook
     */
    public CheckInBook() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        searchForCheckIn = new javax.swing.JTextField();
        SearchForCheckInButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        bookDetailsForCheckInTable = new javax.swing.JTable();
        checkInButton = new javax.swing.JButton();
        backButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(208, 248, 241));

        SearchForCheckInButton.setBackground(new java.awt.Color(204, 204, 204));
        SearchForCheckInButton.setText("Search");
        SearchForCheckInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SearchForCheckInButtonActionPerformed(evt);
            }
        });

        bookDetailsForCheckInTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ISBN", "BORROWER ID", "BORROWER LAST NAME"
            }
        ));
        bookDetailsForCheckInTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bookDetailsForCheckInTableMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(bookDetailsForCheckInTable);

        checkInButton.setBackground(new java.awt.Color(204, 204, 204));
        checkInButton.setText("Check In Book");
        checkInButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                checkInButtonActionPerformed(evt);
            }
        });

        backButton.setBackground(new java.awt.Color(204, 204, 204));
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
                        .addGap(145, 145, 145)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkInButton)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addComponent(backButton)
                                .addGap(20, 20, 20))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(searchForCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(SearchForCheckInButton)))))
                .addContainerGap(38, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(21, 21, 21)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(searchForCheckIn, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SearchForCheckInButton, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(checkInButton)
                .addGap(18, 18, 18)
                .addComponent(backButton)
                .addContainerGap(35, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

    private void backButtonActionPerformed(java.awt.event.ActionEvent evt) {                                           
        // TODO add your handling code here:
        HomeFrame hf = new HomeFrame();
        this.setVisible(false);
        hf.setVisible(true);
    }                                          

    private void SearchForCheckInButtonActionPerformed(java.awt.event.ActionEvent evt) {                                                       
        // TODO add your handling code here:
        bookDetailsForCheckInTable.setVisible(true);
        SearchForLoans();
    }                   
    
    public void SearchForLoans(){
        String givenSearchString = searchForCheckIn.getText();
        try{
        //JDBC Connection
        Connection conn = ConnectionToDB.getConnection();
        Statement st = conn.createStatement();
        String query = "select  bl.LOAN_ID as 'Loan ID',bl.Card_NO as 'Card Number',bl.ISBN as 'ISBN',bo.last_name as 'Borrower Last Name' from book_loans bl left join borrower bo on bl.CARD_NO=bo.Card_NO where (bl.Card_NO like '%"+givenSearchString+"%' or bl.isbn like '%"+givenSearchString+"%' or bo.LAST_NAME like '%"+givenSearchString+"%') and (bl.date_in is null);";
        System.out.println(query);
        ResultSet rs = st.executeQuery(query);
       
        System.out.println("Count"+rs);
        bookDetailsForCheckInTable.setModel(DbUtils.resultSetToTableModel(rs));
        System.out.println("givenSearchString"+givenSearchString);
        rs.close();

        }
        catch(Exception e){
                e.printStackTrace();
        }


    }
    public int selectedLoanID;
    public String selected_card_num;
    public String selected_isbn;
    private void bookDetailsForCheckInTableMouseClicked(java.awt.event.MouseEvent evt) {                                                        
        // TODO add your handling code here:
        int row = bookDetailsForCheckInTable.getSelectedRow();
        DefaultTableModel model = (DefaultTableModel)bookDetailsForCheckInTable.getModel();
        selectedLoanID = (int)model.getValueAt(row, 0);
        selected_card_num = model.getValueAt(row, 1).toString();
        selected_isbn = model.getValueAt(row, 2).toString();

        System.out.println("selected row"+selected_isbn);  
    }                                                       

    private void checkInButtonActionPerformed(java.awt.event.ActionEvent evt) {                                              
        // TODO add your handling code here:
      if(selectedLoanID!=0)
      {
       try
       {

        Connection conn = ConnectionToDB.getConnection();
        Statement st = conn.createStatement();
        String query = "update BOOK set is_available=1 where isbn='"+selected_isbn+"';";
        int r1 = st.executeUpdate(query);
        String q2 = "update book_loans set date_in=CURDATE() WHERE loan_id="+selectedLoanID+";";
        int r2 = st.executeUpdate(q2);
        if(r1==1 && r2==1)
        {
            JOptionPane.showMessageDialog(null, "Book Checked In Successfully");
            HomeFrame hf = new HomeFrame();
            this.setVisible(false);
            hf.setVisible(true);
        }
        else{
            JOptionPane.showMessageDialog(null, "Check In Failed, Please Try Again");

        }
       }
        catch(Exception e)
            {
        e.printStackTrace();
            }
}
    
else{
    JOptionPane.showMessageDialog(null, "Please select a book to check in");

}




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
            java.util.logging.Logger.getLogger(CheckInBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CheckInBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CheckInBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CheckInBook.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CheckInBook().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton SearchForCheckInButton;
    private javax.swing.JButton backButton;
    private javax.swing.JTable bookDetailsForCheckInTable;
    private javax.swing.JButton checkInButton;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField searchForCheckIn;
    // End of variables declaration                   
}
    
    
