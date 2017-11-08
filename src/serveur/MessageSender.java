package serveur;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import main.ModelMessage;

public class MessageSender  {

	private List<Socket> socketList;
	
	public MessageSender(){
		socketList = new ArrayList<Socket>();
	}
	
	
	
	//Lecture d ela liste de socket
	public synchronized void listReader(ModelMessage model) throws IOException{
		
		
		for(int i = 0; i < socketList.size(); i++){
			
			Socket s = socketList.get(i);
			sendMessage(model,s);
			
		}
		
	}
		
		
	
	
		public synchronized void sendMessage(ModelMessage objectMessage, Socket s) throws IOException{
			

			//Utilisation de la socket dans la liste des sockets
	         OutputStream envoiAuClient = s.getOutputStream();
	         ObjectOutputStream out = new ObjectOutputStream(envoiAuClient);
	         
	         //Envoi de l'objet message au client
	         out.writeObject(objectMessage);
			
		}
	
}
