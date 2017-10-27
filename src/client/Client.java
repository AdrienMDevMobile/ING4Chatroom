package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

import serveur.Serveur;
import main.Main;

public class Client extends Thread {
	
	
	public static void main(String[] args) {
		
		try {
			
			Client c = new Client();			

			c.start();
			
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	Socket socket;

	public Client() throws UnknownHostException, IOException{
		this.socket = new Socket(Main.ADDRESSE_SERVEUR, Main.PORT_ENTREE);
	}
	
	@Override
	public void run() {
		try {
			
			Client_Read cr = new Client_Read(new DataInputStream(socket.getInputStream()));
			cr.start();
			
			
			DataOutputStream out = new DataOutputStream(socket.getOutputStream());
			String message;
			Scanner sc = new Scanner(System.in);
			
			while(true){
				message = sc.nextLine();
				
				System.out.println("Sent");
				out.writeUTF(message);	
			}
			
			
			
		} catch (IOException e) {
			
			// TODO Auto-generated catch block
			e.printStackTrace();
			
		}
		
	}
}
