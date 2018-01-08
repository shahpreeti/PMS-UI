package pack1;


import java.sql.*;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;


public class Login_Demo_Local {

	static String stored_pass;
	public static void connectDB()
	{
		String connectionUrl = "jdbc:sqlserver://localhost\\SQLEXPRESS:1433;"+
		         "databaseName=HRMS;user=sa;password=Admin123;integratedSecurity=true";  

		      // Declare the JDBC objects.  
		      Connection con = null;  
		      Statement stmt = null;  
		      ResultSet rs = null;  
		      
		      try {  
		         // Establish the connection.  
		         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		         con = DriverManager.getConnection(connectionUrl);  

		         // Create and execute an SQL statement that returns some data.  
		         String SQL = "select password from login_demo where id='preeti.shah'";  
		         stmt = con.createStatement();  
		         rs = stmt.executeQuery(SQL);  
		         while (rs.next()) {  
			            System.out.println(rs.getString(1));  
			            stored_pass=rs.getString(1);
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
	public static boolean checkPassword(String password_plaintext, String stored_hash) {
		boolean password_verified = false;

		if(null == stored_hash || !stored_hash.startsWith("$2a$"))
			throw new java.lang.IllegalArgumentException("Invalid hash provided for comparison");

		password_verified = BCrypt.checkpw(password_plaintext, stored_hash);

		return(password_verified);
	}
	public static void main(String[] args) {
		
		String id,password;
		Scanner sc=new Scanner(System.in);
		System.out.println("enter id");
		id=sc.next();
		System.out.println("enter pwd");
		password=sc.next();
		
		connectDB();
		boolean match=checkPassword(password,stored_pass);
		System.out.println(match);
	}

}
