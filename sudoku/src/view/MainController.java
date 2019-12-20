package view;

import game.MainGame;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class MainController {
	
	@FXML
	private Canvas canvas;
	
	private MainGame game;
	
	@FXML
	private void initialize() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
	}
	
	public void GameStart() {
		GraphicsContext gc = canvas.getGraphicsContext2D();
		game = new MainGame(gc);
	}
	
	@FXML
	private void GameCheck() {
		game.gameClear();
	}
	
	@FXML
	private void GameHelp() {
		game.help();
	}
	
	@FXML
	private void kibitz() {
		game.kibitz();
	}
	
	public void MouseHandle(MouseEvent e) {
		game.clickHandle(e);
	}
}
