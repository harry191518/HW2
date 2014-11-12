/*

RLai(Yi-Hao Lai)
IKDDhw2.java
for testing postgreSQL
input string and output a table sort by user_id

*/
	import java.sql.Connection;
	import java.sql.DriverManager;
	import java.sql.ResultSet;
	import java.sql.Statement;
	import javax.swing.*;
	import java.awt.*;
	import java.util.Scanner;

	public class IKDDhw2 {
	   public static void main( String args[] )
	     {
		   final Object rows[][] = new Object[100][3];
			
	       Connection c = null;
	       Statement stmt = null;	     
	       
	       try {
	       Class.forName("org.postgresql.Driver");
	         c = DriverManager
	            .getConnection("jdbc:postgresql://iServDB.cloudopenlab.org.tw:5432/harry191518_db_9570",
	            "harry191518_user_9570", "d9wXKnil");
	         c.setAutoCommit(false);
	         System.out.println("Opened database successfully");
	         
	         String str;
	         Scanner scanner = new Scanner(System.in);	         
	         str = scanner.nextLine();
	         scanner.close();
	         
	         stmt = c.createStatement();
	         String sql = "SELECT * FROM twitter WHERE q = " + "'" + str + "' ORDER BY user_id ASC";
	         
	         //String sql = "SELECT * FROM twitter WHERE q = '王建民' ORDER BY user_id ASC";

	         int i = 0;
	         ResultSet rs;
	         for(int k=7; k<=10; k++)
	         {	 
	        	 rs = stmt.executeQuery( sql );
	        	 while ( rs.next() ) {     
	        		 String user_id = rs.getString("user_id");
	        		 if(user_id.length() == k)
	        		 {
	        			 String text = rs.getString("text");
	        			 rows[i][0] = text;
	        			 rows[i][1] = rs.getString("user_name");
	        			 rows[i++][2] = user_id;
	        		 }
	        	 }
	        	 rs.close();
	         }	 
	         stmt.close();
	         c.close();
	       } catch ( Exception e ) {
	         System.err.println( e.getClass().getName()+": "+ e.getMessage() );
	         System.exit(0);
	       }
	       
	       final Object headers[] = {"text", "user_name", "user_id"};

		   JFrame frame = new JFrame("Table Printing");
		   frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		   final JTable table = new JTable(rows, headers);
		   JScrollPane scrollPane = new JScrollPane(table);
		   frame.add(scrollPane, BorderLayout.CENTER);

		   frame.setSize(1200, 500);
		   frame.setVisible(true);
		   
	       System.out.println("Operation done successfully");
	     }
	}
