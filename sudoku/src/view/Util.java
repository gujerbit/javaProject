package view;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class Util {

	
	public static void alert(String msg, String header) {
		Alert a = new Alert(AlertType.INFORMATION);
		a.setTitle("알림");
		a.setHeaderText(header);
		a.setContentText(msg);
		a.show();
	}

}
