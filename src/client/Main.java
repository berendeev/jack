package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.*;
import java.net.*;

public class Main extends Application {

	public static void main(String args[]) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn_connect;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("login.fxml"));

		Scene scene = new Scene(loader.load());
		primaryStage.setScene(scene);
		primaryStage.show();

		LoginController loginController = loader.getController();
		btn_connect = loginController.getBtn_connect();
		btn_connect.setOnAction(listener -> mainWin(primaryStage, loginController.getAttributes()));
	}

	public void mainWin(Stage stage, Attributes atr) {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("mainWindow.fxml"));
		MainWindowController controller = loader.getController();

		try {
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

		try {
			InetAddress ipAdress = InetAddress.getByName(atr.getSererIP());
			Socket socket = new Socket(ipAdress, Integer.parseInt(atr.getPort()));
			BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			PrintWriter writer = new PrintWriter(socket.getOutputStream(), true);

			controller.setWriter(writer);

			while (true){
				controller.printMessage(reader.readLine());

			}

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}



	}

}

class FromSerev {

}