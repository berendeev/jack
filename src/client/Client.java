package client;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;

public class Client extends Application {

	public static void main(String args[]) {
		launch(args);
	}


	@Override
	public void start(Stage primaryStage) throws Exception {
		Button btn_connect;

		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Client.class.getResource("login.fxml"));

		Scene scene = new Scene(loader.load());
		primaryStage.setScene(scene);
		primaryStage.show();

		LoginController loginController = loader.getController();
		loginController.forSimpleLife();///////////////////////////////////////////////// bad shortcut
		btn_connect = loginController.getBtn_connect();
		btn_connect.setOnAction(event -> {
			loginController.takeAttributes();
			primaryStage.close();
			mainWin(loginController.getAttributes());

		});
	}

	public void mainWin(Attributes attributes) {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(Client.class.getResource("mainWindow.fxml"));

		try {
			Scene scene = new Scene(loader.load());
			stage.setScene(scene);
			stage.show();

			MainWindowController controller = loader.getController();

			controller.init(attributes);

		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

