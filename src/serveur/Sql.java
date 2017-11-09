package serveur;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import main.ModelMessage;




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
	
public static void insererMessage(String utilisateur, String message,String date) throws SQLException{
		
		Statement stmt = null;
		Connection con = null;
		
		
		String requete = "INSERT INTO " + nomTable + " (utilisateur,message,date) VALUES('"+utilisateur+"','"+message+"','"+date+"')" ;
		
		con = DriverManager.getConnection(DB_URL+nomDatabase , "root" , ""); 
		
		stmt = con.createStatement();
	
		stmt.executeUpdate(requete);
		stmt.close();
		con.close();
		
	}

public static List<ModelMessage> recuperation10Messages() throws SQLException{ 
	
	List<ModelMessage> listModelMessage = new ArrayList<ModelMessage>();
	
	Statement stmt = null;
	Connection con = null;
	
	con = DriverManager.getConnection(DB_URL+nomDatabase , "root" , ""); 
	stmt = con.createStatement();

	System.out.println("OK OK OK OK");
	
	String requete = "SELECT * FROM " + nomTable + " ORDER BY id DESC LIMIT 5 " ;
	ResultSet rs = stmt.executeQuery(requete);
    //STEP 5: Extract data from result set
    while(rs.next()){
       //Retrieve by column name
       String utilisateur  = rs.getString("utilisateur");
       String message = rs.getString("message");
       String date = rs.getString("date");
       System.out.println(message);
       ModelMessage unModelMessage = new ModelMessage(utilisateur,message,date);
       listModelMessage.add(unModelMessage);
       
    }
    
	stmt.close();
	con.close();
	
	return listModelMessage;
}





//sdsfsdfsf

	
	
	public static void main(String[] args) throws Exception  {

		/*
		
		Sql.creationDatabase();
		Sql.creationTableChat();
	//	Sql.insererMessage("MAMADOU","J ai mal aux dents ");
		Sql.recuperationMessage();
		
		*/
		
	
	}
	
	


}
