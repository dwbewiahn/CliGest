package application;
	
import javafx.application.Application;
import javafx.stage.Stage;

/**
 * Classe principal que inicia o aplicativo, dando load no screen manager.
 * @author dwbew
 *
 */

public class Main extends Application {
	

	@Override
	public void start(Stage primaryStage) {
		ScreenManager.start(primaryStage);
	}
	
	public static void main(String[] args) {
		launch(args);
	}
}
