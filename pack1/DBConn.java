package pack1;

import java.sql.*; 

public class DBConn {
	 
	 
	public static void main(String args[]){  
		String connectionUrl = "jdbc:sqlserver://thirdi-55\\MSSQLNEW:1433;" +  
		         "databaseName=HRMS;user=sa;password=Admin123";  

		      // Declare the JDBC objects.  
		      Connection con = null;  
		      Statement stmt = null;  
		      ResultSet rs = null;  

		      try {  
		         // Establish the connection.  
		         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		         con = DriverManager.getConnection(connectionUrl);  

		         // Create and execute an SQL statement that returns some data.  
		         String SQL = "SELECT TOP 10 * FROM ohrm_user";  
		         stmt = con.createStatement();  
		         rs = stmt.executeQuery(SQL);  

		         // Iterate through the data in the result set and display it.  
		         while (rs.next()) {  
		            System.out.println(rs.getString(4));  
		         }  
		      }  

		      // Handle any errors that may have occurred.  
		      catch (Exception e) {  
		         e.printStackTrace();  
		      }  
		      finally {  
		         if (rs != null) try { rs.close(); } catch(Exception e) {}  
		         if (stmt != null) try { stmt.close(); } catch(Exception e) {}  
		         if (con != null) try { con.close(); } catch(Exception e) {}  
		      }  	
	}
}