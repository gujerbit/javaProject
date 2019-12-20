package view;

import game.MainGame;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AnswerController {
	
	@FXML
	private Label lblQuestion;
	
	@FXML
	private TextField txtAnswer;
	
	private Stage dialog;
	
	private int answer;
	
	public void setDialog(Stage dialog) {
		this.dialog = dialog;
	}
	
	public void sendAnswer() {		
		String value = txtAnswer.getText();
		
		//사용자가 숫자를 입력안했다면 걸러줄 것.
		if(Integer.parseInt(value) < 1 && Integer.parseInt(value) > 9) {
			return;
		}
		
		answer = Integer.parseInt(value);
		
		dialog.close();
	}
	
	public Integer getAnswer() {
		return answer;
	}
}
