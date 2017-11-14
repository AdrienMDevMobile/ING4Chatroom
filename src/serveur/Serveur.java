package serveur;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import client.Client;
import main.Main;
import main.Output;

public class Serveur extends Thread {
	
	List<MessageSender> listMessageSender = new ArrayList<MessageSender>();
	int lastConnectionSign = 0;
	
	public static void main(String[] args) {
		
			Serveur s = new Serveur();
			s.start();
		
	}
	
	public Serveur(){
		String[] listTopics = SalleDeDiscussion.getlistDiscussionsPossibles();
		
		 for(int i = 0; i<listTopics.length; ++i){
			 System.out.println(i);
		 		listMessageSender.add(new MessageSender(i, listMessageSender));
		 }
		 
	}

	@Override
	public void run() {
		
		try {
			Sql.creationDatabase();
			Sql.creationTableChat();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		ServerSocket ss;
		try {
			ss = new ServerSocket(Main.PORT_ENTREE);
			System.out.println("Server on");
			
		while(true){

				
				Socket socket = ss.accept();
				
				int sign = lastConnectionSign++;
				
				listMessageSender.get(0).addStream(new Output(
						new ObjectOutputStream(socket.getOutputStream()), sign));
				
				SalleDeDiscussion salle = new SalleDeDiscussion(
						sign, new ObjectInputStream(socket.getInputStream()), listMessageSender.get(0)
						);
				
	
				salle.start();
		
		
		}
		} catch (IOException e1) {System.out.println("Could not start server socket");}
		
		
		

	}}
