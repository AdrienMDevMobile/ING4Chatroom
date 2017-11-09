package serveur;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.ModelMessage;
import main.Output;

public class MessageSender  {

	private List<Output> outputList;
	
	public MessageSender(){
		outputList = new ArrayList<Output>();
	}
	
	public synchronized void addStream(Output out){
		outputList.add(out);
	}
	
	
	//Lecture d ela liste de socket
	public synchronized void sendMessageToList(ModelMessage message, int sign) throws IOException{
		
		for(int i = 0; i < outputList.size(); i++){
			System.out.println("Test");
			
			if(outputList.get(i).getSign() != sign){
				sendMessage(message, outputList.get(i).getOut());
				System.out.println("sent");
			}	
		}
		
	}

	private synchronized void sendMessage(ModelMessage objectMessage, ObjectOutputStream out) throws IOException{
        
	         //Envoi de l'objet message au client
	         out.writeObject(objectMessage);
			
	}
	
	
	public synchronized void send10LastMessages(int sign) throws IOException, SQLException{
        
		
		for(int i = 0; i < outputList.size(); i++){
			System.out.println("Test");
			
			if(outputList.get(i).getSign() == sign){
				
				List<ModelMessage> listModelMessage= Sql.recuperation10Messages();
				
				System.out.println("Size list " + listModelMessage.size());
				for(int j =listModelMessage.size()-1;j>=0;j--){
					
					
					System.out.println("OK OK OK OK");
					sendMessage(listModelMessage.get(j), outputList.get(i).getOut());
					System.out.println("sent");
					
				}
				
			
				break;
			}	
		}

		
	}
	
}
