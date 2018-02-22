package client;

import javafx.scene.control.Label;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;

public class Connection {
	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;

	protected Connection(Attributes attributes) throws IOException {
		InetAddress ip = InetAddress.getByName(attributes.getSererIP());
		int port = Integer.parseInt(attributes.getPort());
		socket = new Socket(ip, port);

		out = new ObjectOutputStream(socket.getOutputStream());
		in = new ObjectInputStream(socket.getInputStream());
	}

	public void send(Label message) {
		try {
			out.writeObject(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public Label arrived()  {
		Label message = null;
		try {
			 message = (Label) in.readObject();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return message;
	}

	public boolean isConnected(){
		return socket.isConnected();
	}
}
