package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Client extends Application {
	public static void main(String args[]){
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Client.class.getResource("login.fxml"));

		Scene scene = new Scene((AnchorPane)loader.load());
		primaryStage.setScene(scene);
		primaryStage.show();

	}
}
