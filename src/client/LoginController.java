package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
	String name;
	String serverIP;
	String port;

	@FXML
	TextField tf_name;
	@FXML
	TextField tf_serverIP;
	@FXML
	TextField tf_port;
	@FXML
	Button btn_connect;

	public void connectingToServer() {
	name = tf_name.getText();
	serverIP = tf_serverIP.getText();
	port = tf_port.getText();

	}

	public Button getBtn_connect(){
		return btn_connect;
	}

}
