package application;

import java.io.IOException;

import application.controllers.MenuInicialController;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class ScreenManager {

	private static Stage primaryStage;
	
	public static void start(Stage primaryStage) {
		setPrimaryStage(primaryStage);
		createNewWindow("views/MenuInicial.fxml", new MenuInicialController());
	}
	
	public static void createNewWindow(String fxmlPath, Object controller) {
		try {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
		loader.setController(controller);
		Parent root;
		root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static void createNewWindowModal(String fxmlPath, Object controller) {
		try {
		Stage stage = new Stage();
		FXMLLoader loader = new FXMLLoader(Main.class.getResource(fxmlPath));
		loader.setController(controller);
		Parent root;
		root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.initOwner(primaryStage);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.showAndWait();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Parent loadFXML(String path, Object controller) {
		Parent parent = null;
		try {
			FXMLLoader loader = new FXMLLoader(Main.class.getResource(path));
			loader.setController(controller);
			parent =  loader.load();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return parent;
	}
	
	public static Stage getPrimaryStage() {
		return primaryStage;
	}
	
	public static void setPrimaryStage(Stage stage) {
		primaryStage = stage;
	}
}
