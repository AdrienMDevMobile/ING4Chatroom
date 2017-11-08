package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import main.ModelMessage;

public class Client_Read extends Thread {
	

	ObjectInputStream in;
	
	public Client_Read(ObjectInputStream in){
		this.in = in;
	}
	

	public void run() {
				
		ModelMessage message;
		
		while(true){
			
			try {
				message = (ModelMessage) in.readObject();
				System.out.println(message.getPseudo() + " , " + message.getDate() + " :\n" + message.getMessage());
			} catch (IOException e) { System.out.println("Error getting a line"); }
			 catch (ClassNotFoundException e) {System.out.println("Could not read object");	}
		
		}
	}

}
