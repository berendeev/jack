package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class Server {
	private List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

	Server() {
		int port = 6666;

		try {
			ServerSocket serverSocket = new ServerSocket(port);
			while (true) {
				Socket socket = serverSocket.accept();
				Connection con = new Connection(socket);
				connections.add(con);
				con.start();

			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		System.out.println("Server start1");
		new Server();
		System.out.println("Server start2");
	}

//#############################################################################



	class Connection extends Thread {
		BufferedReader reader;
		PrintWriter writer;
		Socket socket;
		String name;

		Connection(Socket socket) {
			this.socket = socket;

			try {
				reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				writer = new PrintWriter(socket.getOutputStream(), true);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				name = reader.readLine();
				synchronized (connections) {
					Iterator<Connection> iter = connections.iterator();
					while (iter.hasNext()) {
						 iter.next().writer.println(name + " cames now");
					}
				}

				String message;
				while (true) {
					message = reader.readLine();
					synchronized (connections) {
						Iterator<Connection> iter = connections.iterator();
						while (iter.hasNext()) {
							iter.next().writer.println(name + ": " + message);
						}
					}
				}
			} catch (IOException e) {
				e.printStackTrace();

			}
		}
	}
}