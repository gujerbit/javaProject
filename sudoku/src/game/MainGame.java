package game;

import java.util.Random;

import application.Main;
import javafx.geometry.VPos;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Label;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.TextAlignment;
import view.Util;

public class MainGame {
	private GraphicsContext gc;
	private int gap = 3;
	private int size = 60;
	private Random rnd = new Random();

	private Integer board[][];
	private Integer map[][];
	private Integer dMap[][];
	private Integer cMap[][];

	private int count = 81;

	private long start;
	private long end;
	private long time;

	public MainGame(GraphicsContext gc) {
		this.gc = gc;
		board = new Integer[9][9];
		map = new Integer[9][9];
		dMap = new Integer[9][9];
		cMap = new Integer[9][9];
		initgame();
	}

	public void initgame() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				board[i][j] = 0;
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				map[i][j] = 0;
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				dMap[i][j] = 0;
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				cMap[i][j] = 0;
			}
		}

		int randomNumber = rnd.nextInt(9);

		if (randomNumber == 0) {
			board[0][3] = 1;
			board[3][1] = 2;
			board[7][0] = 3;
			board[2][2] = 4;
			board[5][5] = 5;
			board[6][3] = 6;
			board[1][7] = 7;
			board[4][6] = 8;
			board[8][8] = 9;
		} else if (randomNumber == 1) {
			board[0][0] = 5;
			board[2][4] = 3;
			board[1][6] = 4;
			board[4][1] = 6;
			board[3][5] = 1;
			board[5][8] = 9;
			board[7][2] = 8;
			board[8][3] = 7;
			board[6][7] = 2;
		} else if (randomNumber == 2) {
			board[1][0] = 3;
			board[0][3] = 1;
			board[2][7] = 7;
			board[4][2] = 8;
			board[5][4] = 9;
			board[3][8] = 6;
			board[6][1] = 4;
			board[8][5] = 5;
			board[7][6] = 2;
		} else if (randomNumber == 3) {
			board[1][1] = 6;
			board[0][4] = 5;
			board[2][8] = 8;
			board[3][2] = 1;
			board[5][3] = 7;
			board[4][7] = 3;
			board[8][0] = 9;
			board[7][5] = 4;
			board[6][6] = 2;
		} else if (randomNumber == 4) {
			board[2][1] = 4;
			board[4][2] = 6;
			board[8][3] = 1;
			board[1][4] = 9;
			board[3][7] = 5;
			board[5][8] = 2;
			board[6][5] = 7;
			board[7][4] = 3;
			board[8][7] = 8;
		} else if (randomNumber == 5) {
			board[0][4] = 5;
			board[1][2] = 4;
			board[3][5] = 1;
			board[6][7] = 8;
			board[8][5] = 3;
			board[5][5] = 2;
			board[7][4] = 6;
			board[3][7] = 9;
			board[8][2] = 7;
		} else if (randomNumber == 6) {
			board[8][1] = 3;
			board[2][3] = 2;
			board[1][1] = 9;
			board[2][6] = 1;
			board[5][7] = 4;
			board[6][4] = 5;
			board[7][7] = 7;
			board[1][3] = 6;
			board[0][7] = 8;
		} else if (randomNumber == 7) {
			board[5][7] = 3;
			board[1][7] = 1;
			board[6][8] = 7;
			board[8][3] = 2;
			board[3][5] = 8;
			board[5][1] = 9;
			board[1][4] = 5;
			board[4][7] = 4;
			board[7][5] = 6;
		} else if (randomNumber == 8) {
			board[0][8] = 1;
			board[1][7] = 3;
			board[2][6] = 5;
			board[3][5] = 7;
			board[4][4] = 9;
			board[5][5] = 8;
			board[6][3] = 6;
			board[3][6] = 4;
			board[1][1] = 2;
		}
		// 생성은 직접 배열에 넣어주기
		// 랜덤 생성 확인
		// 랜덤 생성 실패 - 터진다
		// 랜덤 배열 확인
		// 확인 완료
		sudoku();
		render();
	}

	private boolean sudoku() {
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (board[i][j] == 0) {
					for (int value = 1; value <= 9; value++) {
						if (checkPossible(i, j, value)) {
							board[i][j] = value;
							if (sudoku())
								return true;
							board[i][j] = 0;
						}
					}
					return false;
				}
			}
		}
		return true;
	}

	private boolean checkPossible(int y, int x, int value) {
		// 열에서 검사
		for (int i = 0; i < 9; i++) {
			if (board[i][x] == value) {
				return false;
			}
		}

		// 행에서 검사
		for (int j = 0; j < 9; j++) {
			if (board[y][j] == value) {
				return false;
			}
		}

		int originX = (x / 3) * 3;
		int originY = (y / 3) * 3;

		// 3X3 검사
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[originY + i][originX + j] == value) {
					return false;
				}
			}
		}
		return true;
	}

	private void render() {
		gc.setTextAlign(TextAlignment.CENTER);
		gc.setTextBaseline(VPos.CENTER);
		gc.setStroke(Color.BLACK);
		gc.strokeLine(12, 200, 575, 200);
		gc.strokeLine(12, 388, 575, 388);
		gc.strokeLine(200, 12, 200, 575);
		gc.strokeLine(388, 12, 388, 575);
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int x = gap + (size + gap) * j;
				int y = gap + (size + gap) * i;

				gc.setFill(Color.rgb(220, 220, 200));
				gc.fillRect(gap * gap + x, gap * gap + y, size, size);
				// gc.strokeText(board[i][j].toString(), x + size/2 + 9, y + size/2 + 9);
			}
		}

		for (int t = 0; t < 5; t++) {
			for (int i = 0; i < 3; i++) {
				for (int j = 0; j < 3; j++) {
					textRender(i, j);
				}
			}
		}
	}

	private void textRender(int y, int x) {
		int hCnt;
		int vCnt;
		while (true) {
			hCnt = rnd.nextInt(3) + x * 3;
			vCnt = rnd.nextInt(3) + y * 3;
			if (map[vCnt][hCnt] == 0)
				break;
		}

		int a = gap + (gap + size) * vCnt;
		int b = gap + (gap + size) * hCnt;

		gc.setStroke(Color.BLUE);
		gc.strokeText(board[vCnt][hCnt].toString(), b + size / 2 + 9, a + size / 2 + 9);
		map[vCnt][hCnt] = board[vCnt][hCnt];
		dMap[vCnt][hCnt] = board[vCnt][hCnt];
		cMap[vCnt][hCnt] = board[vCnt][hCnt];
		// 배치된 숫자가 map과 dMap에 들어가는지 확인
		// 확인 완료
	}

	public void clickHandle(MouseEvent e) {
		double mouseX = e.getX();
		double mouseY = e.getY();

		int bs = gap + size;

		if (mouseX % bs < gap || mouseY % bs < gap) {
			return;
		}

		int x = (int) (mouseX / bs);
		int y = (int) (mouseY / bs);

		if (x >= 9 || y >= 9) {
			return;
		}

		MouseButton btn = e.getButton();
		if (btn == MouseButton.PRIMARY) {
			leftClick(y, x);
		}
		if (btn == MouseButton.SECONDARY) {
			rightClick(y, x);
		}
	}

	private int cnt = 81;
	private int checkH = -1;
	private int checkV = -1;
	private int checkD = -1;

	private void leftClick(int y, int x) {
		if (dMap[y][x] != 0)
			return;
		if (map[y][x] > 0)
			return;
		// 배치된 숫자를 클릭하지 못하게 하는지 확인
		// 확인 완료
		int answer = Main.instance.showDialog();
		// 판에다가 직접 넣어주기
		int a = gap + (gap + size) * y;
		int b = gap + (gap + size) * x;

		gc.setStroke(Color.BLACK);

		if (answer > 0 && answer < 10) {
			map[y][x] = answer;
			gc.strokeText(map[y][x].toString(), b + size / 2 + 9, a + size / 2 + 9);
		}

		cMap[y][x] = map[y][x];

		for (int i = 0; i < 9; i++) {
			if (dMap[i][x] == map[y][x]) {
				gc.setStroke(Color.RED);
				gc.strokeText(map[y][x].toString(), b + size / 2 + 9, a + size / 2 + 9);
			}
			checkH++;
			break;
		}

		for (int i = 0; i < 9; i++) {
			if (checkH > 0 && cMap[i][x] == map[y][x]) {
				gc.setStroke(Color.RED);
				gc.strokeText(map[y][x].toString(), b + size / 2 + 9, a + size / 2 + 9);
			}
		}

		for (int j = 0; j < 9; j++) {
			if (dMap[y][j] == map[y][x]) {
				gc.setStroke(Color.RED);
				gc.strokeText(map[y][x].toString(), b + size / 2 + 9, a + size / 2 + 9);
			}
			checkV++;
			break;
		}

		for (int j = 0; j < 9; j++) {
			if (checkV > 0 && cMap[y][j] == map[y][x]) {
				gc.setStroke(Color.RED);
				gc.strokeText(map[y][x].toString(), b + size / 2 + 9, a + size / 2 + 9);
			}
		}
		
		int originX = (x / 3) * 3;
		int originY = (y / 3) * 3;
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(dMap[originY + i][originX + j] == map[y][x]) {
					gc.setStroke(Color.RED);
					gc.strokeText(map[y][x].toString(), b + size/2 + 9, a + size/2 + 9);
				}
			}
			checkD++;
			break;
		}
		
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				if(checkD > 0 && cMap[originY + i][originX + j] == map[y][x]) {
					gc.setStroke(Color.RED);
					gc.strokeText(map[y][x].toString(), b + size/2 + 9, a + size/2 + 9);
				}
			}
		}
		// map[y][x]에 값이 들어가는지 확인
		// 확인 완료
	}

	private void rightClick(int y, int x) {
		if (dMap[y][x] != 0)
			return;
		map[y][x] = 0;
		cMap[y][x] = 0;
		checkH = -1;
		checkV = -1;
		checkD = -1;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				int a = gap + (gap + size) * y;
				int b = gap + (gap + size) * x;
				gc.setFill(Color.rgb(220, 220, 200));
				gc.fillRect(gap * gap + b, gap * gap + a, size, size);
			}
		}

		cnt++;
		// map[y][x]에 0이 삽입되는지 확인
		// 확인 완료
	}

	public void gameClear() {

		cnt = 81;

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] != 0) {
					cnt--;
				} else {
					continue;
				}
			}
		}

		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] == board[i][j]) {
					count--;
				} else {
					continue;
				}
			}
		}

		if (count == 0) {
			Util.alert("게임 클리어! ", "d^오^b " + "  ⎛⎝⎛° ͜ʖ°⎞⎠⎞ ");
		} else {
			if (cnt == 0) {
				Util.alert("틀린 곳이 없는지 다시 한 번 확인해 보세요", " '͡•_'͡• ");
			}
			Util.alert("아직 빈 칸이 있습니다. " + " 남은 칸 수 : " + cnt, " '͡•_'͡• ");
		}
		count = 81;
		// count가 정상적으로 실행되는지 확인
		// 확인 완료
	}

	public void help() {
		Util.alert("스도쿠는 열, 행, 3 x 3 구역에 1 부터 9까지의 수를 넣어 9 x 9 칸에 모든 숫자를 중복 되지 않도록 채워 넣는 것이 목표입니다.", "도움말");
	}

	private int uCnt = 3;
	private int mc = 81;

	public void kibitz() {
		if (uCnt == 0) {
			Util.alert("힌트를 전부 사용하셨습니다!", " '͡•_'͡• ");
			return;
		}

		gc.setStroke(Color.GREEN);

		mc = 81;
		for (int i = 0; i < 9; i++) {
			for (int j = 0; j < 9; j++) {
				if (map[i][j] != 0) {
					mc--;
				}
			}
		}

		while (true) {
			int y = rnd.nextInt(8);
			int x = rnd.nextInt(8);
			int a = gap + (gap + size) * y;
			int b = gap + (gap + size) * x;

			if (mc <= 9) {
				break;
			}

			if (map[y][x] > 0) {
				continue;
			}

			if (map[y][x] == 0) {
				gc.strokeText(board[y][x].toString(), b + size / 2 + 9, a + size / 2 + 9);
				map[y][x] = board[y][x];
				dMap[y][x] = board[y][x];
				break;
			}

		}

		if (mc <= 9 && mc > 0) {
			Util.alert("각 행, 각 열, 각 구역 내에 빈 칸이 한 칸씩만 남았습니다. 나머지는 직접 해보세요!", "d^오^b");
			return;
		}

		if (mc == 0) {
			Util.alert("더 이상 힌트를 사용할 빈 칸이 존재하지 않습니다!", " '͡•_'͡• ");
			return;
		}

		uCnt--;
		Util.alert("앞으로 " + uCnt + "개의 힌트를 사용 가능합니다.", " ༼ つ ◕_◕ ༽つ ");
	}

}
