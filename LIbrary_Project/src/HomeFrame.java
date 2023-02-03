import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class HomeFrame extends javax.swing.JFrame {

    /**
     * Creates new form Home
     */
    public HomeFrame() {
        initComponents();
        DateInNull();
        DateInNotNull();


    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // Search button clicked
        Search_Books search = new Search_Books();
        this.setVisible(false);
        search.setVisible(true);
        
   
    }     

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // CHECK-IN BUTTON CLICKED
        CheckInBook checkInBook = new CheckInBook();
        this.setVisible(false);
        checkInBook.setVisible(true);

    }
    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // CHECK-IN BUTTON CLICKED
        GetFines getFines = new GetFines();
        this.setVisible(false);
        getFines.setVisible(true);

    }
   
                           
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();


        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBackground(new java.awt.Color(223, 242, 242));

        jButton1.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton1.setText("Start Searching");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton2.setText("Check-In");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton3.setText("Fines");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel1.setBackground(new java.awt.Color(25, 21, 21));
        jLabel1.setFont(new java.awt.Font("Comic Sans MS", 1, 36)); // NOI18N
        jLabel1.setText("Welcome");

        jButton4.setFont(new java.awt.Font("Comic Sans MS", 0, 18)); // NOI18N
        jButton4.setText("Add Borrower");
        jButton4.setPreferredSize(new java.awt.Dimension(142, 32));
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(86, 86, 86)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(30, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>                        

                                      

    public void DateInNull()
    {
        try{
            Connection conn = ConnectionToDB.getConnection();
            Statement statmt = conn.createStatement();
            String query = "select *,DATEDIFF(CURDATE(),DUE_DATE) AS DATDIFF from book_loans where CURDATE() > DUE_DATE and date_in is null;";
            
            ResultSet getSetRS = statmt.executeQuery(query);
            
            while(getSetRS.next())
            {
            
                int loan_id_set = getSetRS.getInt("loan_id");
                int fine_amt_set = getSetRS.getInt("DATDIFF");
                InsertOrUpdate(loan_id_set,fine_amt_set);

            }
          }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    public void DateInNotNull()
    {
        try{
            Connection conn = ConnectionToDB.getConnection();
            Statement statmt = conn.createStatement();
            String query = "select *,DATEDIFF(DATE_IN,DUE_DATE) AS DATDIFF from book_loans where DUE_DATE < DATE_IN and date_in is not null;";
            
            ResultSet getSetRS = statmt.executeQuery(query);
            
            while(getSetRS.next())
            {
            
                int loan_id_set = getSetRS.getInt("loan_id");
                int fine_amt_set = getSetRS.getInt("DATDIFF");
                InsertOrUpdate(loan_id_set,fine_amt_set);

            }
          }
        catch(Exception e)
        {
            e.printStackTrace();
        }    
    }
    
    private void InsertOrUpdate(int loan_id_set, int fine_amt_set) {
        double fine_amt = fine_amt_set*0.25;
        try{

        Connection conn = ConnectionToDB.getConnection();
        Statement statmt = conn.createStatement();
        String query = " INSERT INTO FINE (LOAN_ID, FINE_AMOUNT) VALUES("+loan_id_set+","+fine_amt+") ON DUPLICATE KEY UPDATE FINE_AMOUNT = "+fine_amt+";";
         int r = statmt.executeUpdate(query);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
         

    }
    /*
    public void UpdateFines() {
        try{
        Connection conn = ConnectionToDB.getConnection();
        Statement st = conn.createStatement();
        String query = "select *,DATEDIFF(CURDATE(),DUE_DATE) AS DATDIFF from book_loans where CURDATE() > DUE_DATE   and date_in is null;";
        System.out.println(query);
        ResultSet rs0 = st.executeQuery(query);
        int load_id =0;
        double fine_amt ;
        while(rs0.next())
        {
             load_id = rs0.getInt("loan_id");
             fine_amt  = rs0.getInt("DATDIFF")*(0.25);
        
        String q1 = "select count(*) from FINE where loan_id="+load_id+";";
            
            ResultSet r1 = st.executeQuery(q1);
            int count1=0;
            while(r1.next())
            {
                count1=r1.getInt("count(*)");
            }
            if(count1==1)
            {

                String q2 = "update fine set FINE_AMOUNT="+fine_amt+" where loan_id="+load_id+";";
                int r2 = st.executeUpdate(q2);
            }
            else if(count1==0)
            {

                String q2 = "insert into fine(fine_amount,loan_id) values ("+fine_amt+","+load_id+");";
                int r2 = st.executeUpdate(q2);
            }
            r1.close();
        }
    }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public void UpdateFineDueDateNull() {
        try{
        Connection conn = ConnectionToDB.getConnection();
        Statement st = conn.createStatement();
        String query = "select *,DATEDIFF(DATE_IN,DUE_DATE) AS DATDIFF from book_loans where DUE_DATE < DATE_IN and date_in is not null;";
        System.out.println(query);
        ResultSet rs0 = st.executeQuery(query);
        int load_id;
        double fine_amt ;
        while(rs0.next())
        {
             load_id = rs0.getInt("loan_id");
             fine_amt  = rs0.getInt("DATDIFF")*(0.25);
        }
        rs0.close();
        String q1 = "select count(*) from FINE where loan_id="+load_id+";";
            
            ResultSet r1 = st.executeQuery(q1);
            int count1=0;
            while(r1.next())
            {
                count1=r1.getInt("count(*)");
            }
            if(count1==1)
            {

                String q2 = "update fine set FINE_AMOUNT="+fine_amt+" where loan_id="+load_id+";";
                int r2 = st.executeUpdate(q2);
            }
            else if(count1==0)
            {

                String q2 = "insert into fine(fine_amount,loan_id) values ("+fine_amt+","+load_id+");";
                int r2 = st.executeUpdate(q2);
            }
            r1.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

              */
        





/*
        // date_in is not null
        String query12 = "select *,DATEDIFF(DATE_IN,DUE_DATE) AS DATDIFF from book_loans where DUE_DATE < DATE_IN and date_in is not null;";
        System.out.println(query12);
        ResultSet rs12 = st.executeQuery(query12);
        while(rs12.next())
        {
            int load_id = rs12.getInt("loan_id");
            String q12 = "select count(*) from FINE where loan_id="+load_id+";";
            ResultSet r12 = st.executeQuery(q12);
            int count12=0;
            while(r12.next())
            {
                count12=r12.getInt("count(*)");
            }
            if(count12==1)
            {
                double fine_amt  = rs12.getInt("DATDIFF")*(0.25);

                String q2 = "update fine set FINE_AMOUNT="+fine_amt+" where loan_id="+load_id+";";
                int r2 = st.executeUpdate(q2);
            }
            else if(count12==0)
            {
                double fine_amt  = rs12.getInt("DATDIFF")*(0.25);

                String q2 = "insert into fine(fine_amount,loan_id) values ("+fine_amt+","+load_id+");";
                int r2 = st.executeUpdate(q2);
            }
            r12.close();
        }
        rs12.close();




        }

        
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
     */
    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // add borrower button clicked
        AddBorrower addBorrower = new AddBorrower();
        this.setVisible(false);
        addBorrower.setVisible(true);
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
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(HomeFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new HomeFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;

    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration                   
}

