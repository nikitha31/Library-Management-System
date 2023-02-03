import java.io.BufferedReader;
import java.io.FileReader;
import java.sql.*;

public class App {
    public  Connection conn = null;
       
        public static void main(String[] args) {
        	
			/* Variables for the database connection */
			String databaseName = "";
        	String url;
        	String userName;
        	String passWord;
            System.out.println("Starting...\n");

            try {
            	App a = new App();
                //Connection conn = a.connection;
    			databaseName = "library";
            	url = "jdbc:mysql://localhost:3306/" + databaseName;
            	userName = "root"; /* Use whatever user account you prefer */
            	passWord = "your_new_password";     /* Include the password for the account of the previous line. */
                a.conn = DriverManager.getConnection(url,userName,passWord);
                Statement stmt = a.conn.createStatement();
                stmt.execute("CREATE DATABASE IF NOT EXISTS Library;"); 
                stmt.execute("USE Library;"); 

                //LoadDataIntoBook(conn); // run only for the first time to load data from csv file into our database.
                //LoadDataIntoBorrower(conn); // run only for the first time to load data from csv file into our database.
               
                a.conn.close();
                 System.out.println("Success! Connecting to Database.");
            }
            catch(Exception ex) {
                System.out.println("ERROR: Cannot connect to database: " + databaseName);
                System.out.println(ex.getMessage());
            }
		}


        private static void LoadDataIntoBook(Connection conn) {
            String booksDataFilePath = "books.csv";
            int batchSize = 20;

            try{
                String insertQuery ="Insert into Book (ISBN,TITLE,AUTHRO,COVER,PUBLISHER,PAGES) values (?,?,?,?,?,?)";
            PreparedStatement statement = conn.prepareStatement(insertQuery);
            BufferedReader bookFileReader = new BufferedReader(new FileReader(booksDataFilePath));
            bookFileReader.readLine(); 
            String eachLine;
            int count = 0;

            while ((eachLine = bookFileReader.readLine()) != null) {
                String[] data = eachLine.split("\t");
                String ISBN = data[0];
                String TITLE = data[2];
                String AUTHRO = data[3];
                String COVER = data[4];
                String PUBLISHER = data[5];
                String pages = data[6];
                int pagesInt = Integer.parseInt(pages); 
              
                statement.setString(1, ISBN);
                statement.setString(2, TITLE);
                statement.setString(3, AUTHRO);
                statement.setString(4, COVER);
                statement.setString(5,PUBLISHER);
                statement.setInt(6, pagesInt);
                statement.addBatch();
 
                if (count % batchSize == 0) {
                    statement.executeBatch();
                }
            }
 
            bookFileReader.close();
            }
            catch(Exception e)
            {
                System.out.println(e.getStackTrace()+" Unable to insert book data");
            }

        }
        private static void LoadDataIntoBorrower(Connection conn2) {
            String borrowerDataFilePath = "borrowers.csv";
            int batchSize = 20;

            try{
            String insertQueryBorrower ="Insert into BORROWER (Card_NO,SSN,FIRST_NAME,LAST_NAME,EMAIL,ADDRESS,CITY,STATE, Phone) values (?,?,?,?,?,?,?,?,?)";
            PreparedStatement statementBor = conn2.prepareStatement(insertQueryBorrower);
            BufferedReader borrowerFileReader = new BufferedReader(new FileReader(borrowerDataFilePath));
            borrowerFileReader.readLine(); 
            String eachBorLine;
            int count1 = 0;

            while ((eachBorLine = borrowerFileReader.readLine()) != null) {
                String[] data = eachBorLine.split(",");
                //System.out.println(data);
                String Card_NO = data[0];

                String SSN = data[1];
                String FIRST_NAME = data[2];
                String LAST_NAME = data[3];
                String EMAIL = data[4];
                String ADDRESS = data[5];
                String CITY = data[6];
                String STATE = data[7];
                String Phone = data[8];

              
                statementBor.setString(1, Card_NO);
                statementBor.setString(2, SSN);
                statementBor.setString(3, FIRST_NAME);
                statementBor.setString(4, LAST_NAME);
                statementBor.setString(5,EMAIL);
                statementBor.setString(6,ADDRESS);
                statementBor.setString(7,CITY);
                statementBor.setString(8,STATE);
                statementBor.setString(9,Phone);

                statementBor.addBatch();
 
                if (count1 % batchSize == 0) {
                    statementBor.executeBatch();
                }
            }
 
            borrowerFileReader.close();
            }
            catch(Exception e)
            {
                System.out.println(e.getStackTrace()+" Unable to insert borrower data");
            }


        }
}