package client;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;

import java.io.IOException;

public class MainWindowController {

	@FXML
	private TextField tf_message;
	@FXML
	private ScrollPane scrollPane;
	@FXML
	private Button btn_send;

	private Connection connection;
	private VBox labels = new VBox();


	public synchronized void send() {
		//todo
	}

	public void printMessage(Label message) {
		labels.getChildren().add(message);
		scrollPane.setContent(labels);
		scrollPane.setVvalue(scrollPane.getVmax());//crookedly working
	}

	public void init(Attributes attributes) {
		try {
			connection = new Connection(attributes);

			Thread thread = new Thread(() -> {
				while (true) {
					if (connection.isConnected()) {
						Label message = connection.arrived();
						Platform.runLater(() -> printMessage(message));
					} else break;
				}
			});
			thread.setDaemon(true);
			thread.start();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
