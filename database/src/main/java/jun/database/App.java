package jun.database;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import jun.views.MainController;

public class App extends Application {
	public static App app;
	
	public static void main(String[] args) {
		launch(args);
	}
	
	@Override
	public void start(Stage primaryStage) throws Exception {
		app = this;
		FXMLLoader loader = new FXMLLoader();
		loader.setLocation(getClass().getResource("/jun/views/Main.fxml"));
		AnchorPane root = (AnchorPane)loader.load();
		Scene scene = new Scene(root);
		
		primaryStage.setScene(scene);
		primaryStage.show();
	}
}
