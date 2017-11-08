package serveur;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;

//Classe de type package car elle ne sera utilisée que par la classe Serveur.
class SalleDeDiscussion extends Thread {
	
	private MessageSender messageSender = new  MessageSender();
	private DataOutputStream out;
	private DataInputStream in;

	public SalleDeDiscussion(DataOutputStream out, DataInputStream in) throws IOException {
		this.out = out;
		this.in = in;
	}
	
	@Override
	public void run() {
		try {
			
			
			System.out.println("connection made");
			out.writeUTF("Connection made");
			
			while(true){
				/*
				new SalleDeDiscussion(numerotock++, ss.accept()); */
				
				String message = in.readUTF();
				
				System.out.println("Received");
				out.writeUTF(message);
			
			}
			
			
	//		messageSender.listReader();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
