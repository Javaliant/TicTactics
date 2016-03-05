/* Author: Luigi Vincent
Board class to represent idea of Tic Tactics board
*/

import javafx.scene.layout.GridPane;

public class TicTacticsBoard extends GridPane {
	private final int NUMBER_OF_BOARDS = 9;
	public int boardCounter = 1;
	private TicTacToeBoard[] board = new TicTacToeBoard[NUMBER_OF_BOARDS];
	private TicTacticsGame game;

	TicTacticsBoard(TicTacticsGame game) {
		this.game = game;

		for (int i = 0; i < board.length; i++) {
			board[i] = new TicTacToeBoard(this.game);
			add(board[i], i / 3, i % 3);
		}
		setHgap(5);
		setVgap(5);
	}

	public void evaluateState() {
		for (int horizontal = 0, vertical = 0; horizontal < NUMBER_OF_BOARDS; horizontal += 3) {
			if (checkSet(vertical, vertical + 3, vertical++ + 6) 
			||  checkSet(horizontal, horizontal + 1, horizontal + 2)) {
				return;
			}
		}
		// Diagonal
		if(checkSet(0, 4, 8) || checkSet(2, 4, 6)) {
			return;
		}

		if (++boardCounter == NUMBER_OF_BOARDS) {
			gameEnd();
			game.endPrompt("It's a tie!");
			return;
		}
	}

	private boolean checkSet(int innerBoard1, int innerBoard2, int innerBoard3) {
		if (boardCounter >= 3) {
			if (board[innerBoard1].equivalentTo(board[innerBoard2]) 
			&& board[innerBoard2].equivalentTo(board[innerBoard3])) {
				gameEnd();
				game.endPrompt(game.checkWinner(board[innerBoard1].winner().toString()) + " wins!");
				SoundPlayer.play("game-won");
				return true;
			}
		}
		return false;
	}

	public void disable() {
		for (TicTacToeBoard b : board) {
			b.disable();
		}
	}

	public void gameEnd() {
		for (TicTacToeBoard b : board) {
			b.toggleGameStatus();
		}
	}

	public void enable(Position position) {
		for (int i = 0; i < Position.values().length; i++) {
			if (position == Position.values()[i]) {
				if (board[i].isCaptured()) {
					enableAll();
				} else {
					board[i].enable();
				}
				break;
			}
		}
	}

	public void enableAll() {
		for (TicTacToeBoard b : board) {
			b.enable();
		}
	}

	public void reset() {
		for (TicTacToeBoard b : board) {
			b.reset();
		}
	}
}