package serveur;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.SQLException;

import main.ModelMessage;

//Classe de type package car elle ne sera utilisée que par la classe Serveur.
class SalleDeDiscussion extends Thread {
	
	private int sign;
	private ObjectInputStream in;
	private MessageSender messageSender;

	public SalleDeDiscussion(int sign, ObjectInputStream in, MessageSender messageSender) throws IOException {
		this.sign = sign;
		this.in = in;
		this.messageSender = messageSender;
	}
	
	@Override
	public void run() {
		try {	
			
			System.out.println("connection made");
			
			//TODO : recuperer les dix derniers messages dans la BDD et les envoyer à l'utilisateur.
			
			messageSender.send10LastMessages(sign);
			
			
			
			while(true){
				
				ModelMessage message;
				try {
					message = (ModelMessage) in.readObject();
					
					System.out.println("Received");
					
					messageSender.sendMessageToList(message, sign);
					
					Sql.insererMessage(message.getPseudo() ,message.getMessage(), message.getDate());
					
				} catch (ClassNotFoundException e) {
					System.out.println("Could not receive message");
				} catch (SQLException e) {
					System.out.println("Probleme");
				}
				
			
			}		
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	}
}
