package client;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

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
	private TextField tf_message;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private Button btn_send;

	private PrintWriter writer;
	private Socket socket;
	private BufferedReader reader;
	private VBox labels = new VBox();


	public synchronized void send() {
		writer.println(tf_message.getText());
		tf_message.clear();
//		System.out.println("pressed");
	}

	public void printMessage(String message) {
		labels.getChildren().add(new Label(message));
		scrollPane.setContent(labels);
	}

	public void start(Attributes attributes) {
		try {
			InetAddress ipAdress = InetAddress.getByName(attributes.getSererIP());
			socket = new Socket(ipAdress, Integer.parseInt(attributes.getPort()));
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new PrintWriter(socket.getOutputStream(), true);
			writer.println(attributes.getName());

			new Thread(() -> {
				while (true) {
					try {
						if (socket.isConnected()) {
							String message = reader.readLine();
							Platform.runLater(() -> printMessage(message));
							Thread.sleep(1000);

						} else break;
					} catch (IOException | InterruptedException e) {
						e.printStackTrace();
					}
				}
			}).start();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
