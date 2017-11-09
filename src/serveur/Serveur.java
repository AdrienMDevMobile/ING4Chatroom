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

import client.Client;
import main.Main;
import main.Output;

public class Serveur extends Thread {
	
	MessageSender messageSender = new MessageSender();
	int lastConnectionSign = 0;
	
	public static void main(String[] args) {
		
			Serveur s = new Serveur();
			s.start();
		
	}

	@Override
	public void run() {
		
		ServerSocket ss;
		try {
			ss = new ServerSocket(Main.PORT_ENTREE);
			System.out.println("Server on");
			
		while(true){

				
				Socket socket = ss.accept();
				
				int sign = lastConnectionSign++;
				
				messageSender.addStream(new Output(
						new ObjectOutputStream(socket.getOutputStream()), sign));
				
				SalleDeDiscussion salle = new SalleDeDiscussion(
						sign, new ObjectInputStream(socket.getInputStream()), messageSender
						);
				
	
				salle.start();
		
		
		}
		} catch (IOException e1) {System.out.println("Could not start server socket");}
		
		
		

	}}
