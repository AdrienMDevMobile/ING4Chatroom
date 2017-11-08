package main;

import java.io.Serializable;

public class ModelMessage implements Serializable {
	
	private String pseudo;
	private String message;
	private String date;
	
	
	public ModelMessage(String pseudo,String message, String date){
		this.pseudo = pseudo;
		this.message = message;
		this.date = date;
		
	}
	
	
	public String getPseudo() {
		return pseudo;
	}
	
	public void setPseudo(String pseudo) {
		this.pseudo = pseudo;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	public String getDate() {
		return date;
	}
	
	public void setDate(String date) {
		this.date = date;
	}
	
	
	
	
	
	
	

}
