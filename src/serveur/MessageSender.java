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

	private List<ObjectOutputStream> outputList;
	
	public MessageSender(){
		outputList = new ArrayList<ObjectOutputStream>();
	}
	
	public synchronized void addStream(ObjectOutputStream out){
		outputList.add(out);
	}
	
	
	//Lecture d ela liste de socket
	public synchronized void sendMessageToList(ModelMessage model) throws IOException{
		
		for(int i = 0; i < outputList.size(); i++){
			sendMessage(model,outputList.get(i));
		}
		
	}

	private synchronized void sendMessage(ModelMessage objectMessage, ObjectOutputStream out) throws IOException{
        
	         //Envoi de l'objet message au client
	         out.writeObject(objectMessage);
			
	}
	
}
