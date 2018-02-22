package server;

import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Server {
	private final List<Connection> connections = Collections.synchronizedList(new ArrayList<Connection>());

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
		System.out.println("Server start");
		new Server();
	}

//#############################################################################


	class Connection extends Thread {
		Socket socket;
		ObjectOutputStream out;
		ObjectInputStream in;
		Label name;

		Connection(Socket socket) {
			this.socket = socket;
			try {
				out = new ObjectOutputStream(socket.getOutputStream());
				in = new ObjectInputStream(socket.getInputStream());
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		@Override
		public void run() {
			try {
				name = (Label) in.readObject();
				System.out.println(name);
				synchronized (connections) {
					for (Connection connection : connections) {
						connection.out.writeObject(new Label(name.getText() + "cames now"));
					}
				}

				Label message;
				while (true) {
					message = (Label) in.readObject();
					System.out.println(name + ": " + message);

					synchronized (connections) {
						for (Connection connection : connections) {
							connection.out.writeObject(name);
						}
					}
				}
			} catch (IOException e) {
//				e.printStackTrace();
				try {
					socket.close();
				} catch (IOException e1) {
					e1.printStackTrace();
				}
				System.out.println(name + " exit");
				synchronized (connections) {
					for (Connection connection : connections) {
						try {
							connection.out.writeObject(new Label(name.getText() + " is offline"));
						} catch (IOException e1) {
							e1.printStackTrace();
						}
					}
				}
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}