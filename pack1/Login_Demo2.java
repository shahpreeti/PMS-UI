package pack1;


import java.sql.*;
import java.util.Scanner;
import org.mindrot.jbcrypt.BCrypt;


public class Login_Demo2 {

	static String stored_pass=null;
	static String id,password;
	
	public static void connectDB()
	{
		String connectionUrl = "jdbc:sqlserver://thirdi-55\\MSSQLNEW:1433;" +  
		         "databaseName=HRMS;user=sa;password=Admin123";  

		      // Declare the JDBC objects.  
		      Connection con = null;  
		      PreparedStatement stmt = null;  
		      ResultSet rs = null;  
		      
		      try {  
		         // Establish the connection.  
		         Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");  
		         con = DriverManager.getConnection(connectionUrl);  

		         // Create and execute an SQL statement that returns some data.  
		         String SQL = "select * from ohrm_user where user_name=?";  

		         stmt=con.prepareStatement(SQL); 
		         stmt.setString(1,id);  
		         rs=stmt.executeQuery();
		         while (rs.next()) {   
			            stored_pass=rs.getString(5);
			            System.out.println(stored_pass);
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
		
		Scanner sc=new Scanner(System.in);
		System.out.println("enter id");
		id=sc.next();
		System.out.println("enter pwd");
		password=sc.next();
		
		connectDB();
		if(stored_pass==null)
			System.out.println("invalid id");
		else
		{
			boolean match=checkPassword(password,stored_pass);
			if(match)
				System.out.println("Welcome "+id);
			else
				System.out.println("invalid credentials");
		}
	}

}
