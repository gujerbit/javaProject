package net.gondr.tetris;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.KeyEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import net.gondr.domain.Block;
import net.gondr.domain.Player;

public class Game {
	private GraphicsContext gc;
	public Block[][] board;
	private Block[][] map;

	// 게임판의 너비와 높이를 저장
	private double width;
	private double height;

	private AnimationTimer mainLoop; // 게임의 메인 루프
	private long before;

	private Player player; // 지금 움직이는 블록
	private double blockDownTime = 0;

	private int score = 0;

	private Canvas nextBlockCanvas;
	private GraphicsContext nbgc; // 다음블록 캔버스

	private double nbWidth; // 다음블록 캔버스의 너비
	private double nbHeight;// 다음블록 캔버스의 높이

	private Label scoreLabel;

	public boolean gameOver = false;

	public Game(Canvas canvas, Canvas nextBlockCanvas, Label scoreLabel) {
		width = canvas.getWidth();
		height = canvas.getHeight();
		this.nextBlockCanvas = nextBlockCanvas;
		this.scoreLabel = scoreLabel;

		this.nbgc = this.nextBlockCanvas.getGraphicsContext2D();
		this.nbWidth = this.nextBlockCanvas.getWidth();
		this.nbHeight = this.nextBlockCanvas.getHeight();

		double size = (width - 4) / 10;
		board = new Block[20][10]; // 게임판을 만들어주고
		map = new Block[20][10];

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j] = new Block(j * size + 2, i * size + 2, size);
				map[i][j] = board[i][j];
			}
		}
		gc = canvas.getGraphicsContext2D();

		mainLoop = new AnimationTimer() {
			@Override
			public void handle(long now) {
				update((now - before) / 1000000000d);
				before = now;
				render();
			}
		};

		before = System.nanoTime();
		player = new Player(board);
		gameOver = true;
	}

	public void gameStart() {
		App.app.game2.gameOver = false;
		gameOver = false;
		mainLoop.start();
		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j].setData(false, Color.WHITE);
				board[i][j].setPreData(false, Color.WHITE);
			}
		}
	}

	// 매 프레임마다 실행되는 업데이트 매서드
	public void update(double delta) {
		if (App.app.game2.gameOver || gameOver)
			return;

		blockDownTime += delta;

		double limit = 0.5- score / 100d;

		if (limit < 0.1) {
			limit = 0.1;
		}

		if (blockDownTime >= limit) {
			player.down();
			blockDownTime = 0;
		}
	}

	// 라인이 꽉 찼는지를 검사하는 매서드
	public void checkLineStatus() {
		for (int i = 19; i >= 0; i--) {
			boolean clear = true;
			for (int j = 0; j < 10; j++) {
				if (!board[i][j].getFill()) {
					clear = false; // 한칸이라도 비어있다면 clear를 false로 설정
					break;
				}
			}

			if (clear) { // 해당 줄이 꽉 찼다면
				score++;
				for (int j = 0; j < 10; j++) {
					board[i][j].setData(false, Color.WHITE);
					board[i][j].setPreData(false, Color.WHITE);
				}

				for (int k = i - 1; k >= 0; k--) {
					for (int j = 0; j < 10; j++) {
						board[k + 1][j].copyData(board[k][j]);
					}
				}

				for (int j = 0; j < 10; j++) {
					board[0][j].setData(false, Color.WHITE);
					board[i][j].setPreData(false, Color.WHITE);
				}
				i++;
			}
		}

	}

	// 매 프레임마다 화면을 그려주는 매서드
	public void render() {
		gc.clearRect(0, 0, width, height);
		gc.setStroke(Color.rgb(0, 0, 0));
		gc.setLineWidth(2);
		gc.strokeRect(0, 0, width, height);

		for (int i = 0; i < 20; i++) {
			for (int j = 0; j < 10; j++) {
				board[i][j].render(gc);
			}
		}

		player.render(nbgc, nbWidth, nbHeight);

		if (App.app.game2.gameOver) {
			gc.setFont(new Font("Arial", 30));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setStroke(Color.WHITE);
			gc.strokeText("YOU WIN!!", width / 2, height / 2);
		} else if (gameOver) {
			gc.setFont(new Font("Arial", 30));
			gc.setTextAlign(TextAlignment.CENTER);
			gc.setStroke(Color.WHITE);
			gc.strokeText("YOU LOSE!!", width / 2, height / 2);
		}

	}

	public void keyHandler(KeyEvent e) {
		if (App.app.game2.gameOver || gameOver)
			return;
		player.keyHandler(e);
	}

	public void setGameOver() {
		gameOver = true;
		render();
		mainLoop.stop();
	}
}
