import java.sql.*;

public class ConnectionToDB {
    static Connection conn = null;
    public static Connection getConnection(){
            String databaseName = "";
        	String url;
        	String userName;
        	String passWord;
            System.out.println("Starting...\n");

            try {
    			databaseName = "library";
            	url = "jdbc:mysql://localhost:3306/" + databaseName;
            	userName = "root"; /* Use whatever user account you prefer */
            	passWord = "your_new_password";     /* Include the password for the account of the previous line. */
                conn = DriverManager.getConnection(url,userName,passWord);
                Statement stmt = conn.createStatement();
                stmt.execute("CREATE DATABASE IF NOT EXISTS Library;"); 
                stmt.execute("USE Library;"); 

                //LoadDataIntoBook(conn); // run only for the first time to load data from csv file into our database.
                //LoadDataIntoBorrower(conn); // run only for the first time to load data from csv file into our database.
               
                 System.out.println("Success! Connecting to Database.");
            }
            catch(Exception ex) {
                System.out.println("ERROR: Cannot connect to database: " + databaseName);
                System.out.println(ex.getMessage());
            }
            return conn;

    }

    
}
