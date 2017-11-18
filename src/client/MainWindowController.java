package client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;

public class MainWindowController {
	@FXML
	private Label lbl_text;
	@FXML
	private TextField tf_massage;

	private PrintWriter writer;
	private Socket socket;
	private BufferedReader reader;



	public void send() {
		//
//		lbl_text.setText(lbl_text.getText() + tf_massage.getText() + "\n");
//		int quantityOfString = tf_massage.getLength() / 102;
//		if (quantityOfString == 0) quantityOfString++;
//		lbl_text.setPrefHeight(lbl_text.getPrefHeight() + 17 * quantityOfString);
//		//
		writer.print(tf_massage.getText());
		tf_massage.clear();
	}

	public void printMessage(String message) {
		lbl_text.setPrefHeight(100);
		lbl_text.setText(lbl_text.getText() + message + "\n");
		int quantityOfString = message.length() / 102;
		if (quantityOfString == 0) quantityOfString++;
		//lbl_text.setPrefHeight(lbl_text.getPrefHeight() + 17 * quantityOfString);
	}

	public void start(Attributes attributes) {
		try {
			InetAddress ipAdress = InetAddress.getByName(attributes.getSererIP());

			socket = new Socket(ipAdress, Integer.parseInt(attributes.getPort()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);

			writer.println(attributes.getName());

		} catch (IOException e) {
			e.printStackTrace();
		}
		new Thread(() -> {
			while (true) {
				try {
					String message = reader.readLine();
					if (message != null)
						printMessage(message);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
}
