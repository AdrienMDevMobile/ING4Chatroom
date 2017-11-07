package client;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Scanner;

public class Client_Read extends Thread {
	

	DataInputStream in;
	
	public Client_Read(DataInputStream in){
		this.in = in;
	}
	

	public void run() {
				
		String message;
		
		while(true){
			
			try {
				message = in.readUTF();
				System.out.println(message);
			} catch (IOException e) { System.out.println("Error getting a line"); }
		
		}
	}

}
