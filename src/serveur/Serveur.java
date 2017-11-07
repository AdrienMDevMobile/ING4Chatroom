package serveur;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import client.Client;
import main.Main;

public class Serveur extends Thread {
	
	
	public static void main(String[] args) {
		
			Serveur s = new Serveur();
			s.start();
		
	}

	@Override
	public void run() {
		
		/*
		 * while(true){
			Socket s = ss.accept();
			System.out.println("Accepted");
		
			Serveurthread st = new Serveurthread(s);
			(new Thread(st)).start();
		}
	
		 */
		
		int numerotock = 1;
		
		try {
			ServerSocket ss = new ServerSocket(Main.PORT_ENTREE);
			System.out.println("Server on");
			
			Socket socket = ss.accept();
			
			SalleDeDiscussion salle = new SalleDeDiscussion(
					new DataOutputStream(socket.getOutputStream()), new DataInputStream(socket.getInputStream())
					);
			

			salle.start();
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}