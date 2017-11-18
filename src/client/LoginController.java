package client;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class LoginController {
//	String name;
//	String serverIP;
//	String port;

	@FXML
	private TextField tf_name;
	@FXML
	private TextField tf_serverIP;
	@FXML
	private TextField tf_port;
	@FXML
	private Button btn_connect;


	String name;
	String serverIP;
	String port;

	public void forSimpleLife(){
		tf_name.setText("User");
		tf_serverIP.setText("localhost");
		tf_port.setText("6666");
	}

	public void takeAttributes() {
		name = tf_name.getText();
		serverIP = tf_serverIP.getText();
		port = tf_port.getText();

	}

	public Button getBtn_connect() {
		return btn_connect;
	}

	public Attributes getAttributes() {
		return new Attributes(name, serverIP, port);
	}
}

class Attributes {
	private String name;
	private String sererIP;
	private String port;

	Attributes(String name, String sererIP, String port) {
		this.name = name;
		this.sererIP = sererIP;
		this.port = port;
	}

	public String getName() {
		return name;
	}

	public String getSererIP() {
		return sererIP;
	}

	public String getPort() {
		return port;
	}
}