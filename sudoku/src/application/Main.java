package application;
	
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import view.AnswerController;


public class Main extends Application {
	public static Main instance;
	
	private Stage dialog;
	private AnswerController ac;
	@Override
	public void start(Stage primaryStage) {
		instance = this;
		try {
			FXMLLoader loader = new FXMLLoader();
			loader.setLocation(getClass().getResource("/view/MainLayout.fxml"));
			AnchorPane root = loader.load();
			
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
			
			//다이얼로그 만들기
			FXMLLoader dialogLoader = new FXMLLoader();
			dialogLoader.setLocation(getClass().getResource("/view/AnswerLayout.fxml"));
			AnchorPane dialogPane = dialogLoader.load();
			Scene dialogScene = new Scene(dialogPane);
			dialog = new Stage();
			dialog.setScene(dialogScene);
			
			ac = dialogLoader.getController();
			ac.setDialog(dialog);
			
			dialog.initModality(Modality.WINDOW_MODAL);
			dialog.initOwner(primaryStage);
			
			primaryStage.setTitle("스도쿠");
			primaryStage.getIcons().add(new Image("/img/sudokuimg.png"));
			
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public int showDialog() {
		dialog.showAndWait();
		
		return ac.getAnswer();
	}
	public static void main(String[] args) {
		launch(args);
	}
}
