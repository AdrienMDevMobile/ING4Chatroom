package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Sql {
	
	private final static String JDBC_DRIVER = "com.mysql.jdbc.Driver";
	private final static String DB_URL = "jdbc:mysql://localhost/";
	
	private final static String nomDatabase = "essai42";
	private final static String nomTable = "chat42";
	
	//Création de la dataab
	public static void creationDatabase() throws SQLException{
		
		Statement stmt = null;
		Connection con = null;
		
		String requete = "CREATE DATABASE IF NOT EXISTS " + nomDatabase ;
		
		con = DriverManager.getConnection(DB_URL , "root" , ""); 
		
		stmt = con.createStatement();
	
		stmt.executeUpdate(requete);
		stmt.close();
		con.close();
	}
	
	public static void creationTableChat() throws SQLException{
		
		Statement stmt = null;
		Connection con = null;
		
		String requete = "CREATE TABLE IF NOT EXISTS " + nomTable + " (id  INT(11) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,utilisateur VARCHAR(20), message VARCHAR(1000), date DATETIME)" ;
		
		con = DriverManager.getConnection(DB_URL+nomDatabase , "root" , ""); 
		
		stmt = con.createStatement();
	
		stmt.executeUpdate(requete);
		stmt.close();
		con.close();
		
		
	}
	
public static void insererMessage(String utilisateur, String message) throws SQLException{
		
		Statement stmt = null;
		Connection con = null;
		
		Date date = new Date();
		SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MM-dd hh:mm:ss");
		
		String requete = "INSERT INTO " + nomTable + " (utilisateur,message,date) VALUES('"+utilisateur+"','"+message+"',NOW())" ;
		
		con = DriverManager.getConnection(DB_URL+nomDatabase , "root" , ""); 
		
		stmt = con.createStatement();
	
		stmt.executeUpdate(requete);
		stmt.close();
		con.close();
		
	}

public static void recuperationMessage() throws SQLException{
	
	Statement stmt = null;
	Connection con = null;
	
con = DriverManager.getConnection(DB_URL+nomDatabase , "root" , ""); 
	
	stmt = con.createStatement();
	
	

	JSONArray jarray = new JSONArray();
	

	
	String requete = "SELECT * FROM " + nomTable ;
	ResultSet rs = stmt.executeQuery(requete);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       String utilisateur  = rs.getString("utilisateur");
       String message = rs.getString("message");
       String date = rs.getString("date");

       //Display values
     //  System.out.print("Utilisateur : " + utilisateur);
     //  System.out.print(", Message: " + message);
     //  System.out.print(", Date: " + date);
       
       JSONObject jobject = new JSONObject();
   		jobject.put("utilisateur", utilisateur);
   		jobject.put("message", message);
   		jobject.put("date", date);
   		
   		jarray.add(jobject);

    }
    
   
    System.out.println(jarray.toString());
	
	stmt.close();
	con.close();
	
}
	
	
	public static void main(String[] args) throws Exception  {

		Sql.creationDatabase();
		Sql.creationTableChat();
		Sql.insererMessage("Adrien","Wesh");
		Sql.recuperationMessage();
		
	
	}
	
	

}
