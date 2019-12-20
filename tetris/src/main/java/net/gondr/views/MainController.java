package net.gondr.views;

import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Label;
import net.gondr.tetris.App;
import net.gondr.tetris.Game;
import net.gondr.tetris.Game2;

public class MainController {
	@FXML
	private Canvas gameCanvas;
	
	@FXML
	private Canvas gameCanvas2;
	
	@FXML
	private Canvas nextBlockCanvas;
	
	@FXML
	private Canvas nextBlockCanvas2;
	
	@FXML
	private Label scoreLabel;
	
	@FXML
	private Label scoreLabel2;
	
	@FXML
	public void initialize() {
		System.out.println("메인 레이아웃 생성 완료");
		App.app.game = new Game(gameCanvas, nextBlockCanvas, scoreLabel);
		App.app.game2 = new Game2(gameCanvas2, nextBlockCanvas2, scoreLabel2);
	}
	
	public void gameStart() {
		App.app.game.gameStart();
		App.app.game2.gameStart();
	}
}
