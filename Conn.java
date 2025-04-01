package university.management.system.pkg2;

import java.sql.*;

public class Conn {
    
    Connection c;
    Statement s;
    
    Conn() {
        try {
            // Load the MySQL driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Connect to the specific database (replace "123456" with your MySQL password)
            c = DriverManager.getConnection("jdbc:mysql://localhost:3306/universitymanagementsystem", "root", "123456");

            // Create a statement object
            s = c.createStatement();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
