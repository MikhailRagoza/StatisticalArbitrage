package ru.statarb.serverside;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.CharBuffer;

import ru.statarb.service.Message;

public class Connection {
	private final String host = "127.0.0.1";
	private int port;
	private ServerSocket servSocket;
	private Socket clientSocket;
	private BufferedReader reader;

	public Connection(int port) {
		this.port = port;
		try {
			servSocket = new ServerSocket(this.port);
			System.out.println("Waiting for Lua");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			clientSocket = servSocket.accept();
			System.out.println("New connection with Lua");
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void sendMessage(Message message) {
		
	}

	public String getMessage() {
		String newMes = null;
		try {
			newMes = reader.readLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newMes;
	}
    public void closeConnection() {
    	try {
			servSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    	try {
			clientSocket.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
    
	public Socket getClientSocket() {
		return clientSocket;
	}

}
