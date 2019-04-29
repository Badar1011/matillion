import java.io.FileInputStream;
import java.io.IOException;
import java.sql.*;
import java.util.Properties;

public class Database {
    public Database()
    {

    }

    public void getDataFromDatabase(String department, String payType, String educationLevel)
    {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;




        try {
            // The newInstance() call is a work around for some
            // broken Java implementations

            // register MySQL Connector/J
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception ex) {
            System.out.println("Failed to register MySQL Connector/J");
            return;
        }

        try (FileInputStream fileInputStream = new FileInputStream("dbvalues.properties")) {
            Properties properties = new Properties();
            properties.load(fileInputStream);
            String url = properties.getProperty("url");
            String password = properties.getProperty("password");
            String username = properties.getProperty("username");



        try {
            conn = DriverManager.getConnection(url,username,password);
        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
            return;
        }

        }
        catch (IOException e)
        {
            System.out.println(e.getMessage());
        }

        try {
            stmt = conn.createStatement();
            String sql =  "SELECT * FROM foodmart.employee, foodmart.department, foodmart.position WHERE position.pay_type = '"+payType+"' AND employee.department_id = department.department_id AND employee.position_id = position.position_id AND employee.education_level = '"+educationLevel+"' AND department.department_description = '"+department+"';";
         //   System.out.println(sql);
            if( stmt.execute(sql)) {
                rs = stmt.getResultSet();
                ResultSetMetaData metaData = rs.getMetaData();

                while (rs.next())
                {
                    for (int i = 1; i <= metaData.getColumnCount(); i++)
                    {
                        System.out.println(metaData.getColumnName(i) + ":" + rs.getString(i) + ", ");
                    }
                }
            }
            // Now do something with the ResultSet ....
            while (rs.next ())
            {
                String nameVal = rs.getString ("name");
                int osCode = rs.getInt ("oscode");
                int ownerCode = rs.getInt ("ownercode");
                System.out.println (
                        "name = " + nameVal
                                + ", oscode = " + osCode
                                + ", ownercode = " + ownerCode);
            }
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
            return;
        } finally {
            // it is a good idea to release
            // resources in a finally{} block
            // in reverse-order of their creation
            // if they are no-longer needed

            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException sqlEx) { /* ignore */ }
                rs = null;
            }

            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException sqlEx) { /* ignore */ }
                stmt = null;
            }
        }

    }
}
