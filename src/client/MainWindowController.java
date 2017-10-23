package client;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.PrintWriter;

public class MainWindowController {
	@FXML
	private Label lbl_text;
	@FXML
	private TextField tf_massage;

	private PrintWriter writer;

	public void send() {
		writer.print(tf_massage.getText());
	}

	public void printMessage(String message){
		lbl_text.setText(lbl_text.getText() + message + "\n");
		int quantityOfString = message.length() / 102;
		if (quantityOfString == 0) quantityOfString++;
		lbl_text.setPrefHeight(lbl_text.getPrefHeight() + 17 * quantityOfString);
	}

	public void setWriter(PrintWriter writer) {
		this.writer = writer;
	}

}
