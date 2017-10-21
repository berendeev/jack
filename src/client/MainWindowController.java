package client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

public class MainWindowController {
	@FXML
	Label lbl_text;
	@FXML
	TextField tf_massage;


	public void send() {
		lbl_text.setText(lbl_text.getText() + tf_massage.getText() + "\n");
		int quantityOfString = tf_massage.getLength() / 102;
		if (quantityOfString == 0) quantityOfString++;
		lbl_text.setPrefHeight(lbl_text.getPrefHeight() + 17 * quantityOfString);
	}

}
