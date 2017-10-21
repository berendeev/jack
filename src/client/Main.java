package client;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

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
		btn_connect.setOnAction(listener -> mainWin(primaryStage));

	}

	public void mainWin(Stage stage){
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Main.class.getResource("mainWindow.fxml"));
		try {
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}

