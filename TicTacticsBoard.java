/* Author: Luigi Vincent
Board class to represent idea of tic tac toe board
*/

import javafx.scene.layout.GridPane;

public class TicTacticsBoard extends GridPane {
	private final int NUMBER_OF_BOARDS = 9;
	public int boardCounter;
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
		for (int i = 0, j = 0; i < 3; i++) {
			// Horizontal
			if(checkSet(j++, j++, j++)) {
				return;
			}
			// Vertical
			if(checkSet(i, i + 3, i + 6)) {
				return;
			}
		}
		// Diagonal
		if(checkSet(0, 4, 8) || checkSet(2, 4, 6)) {
			return;
		}

		if (++boardCounter == NUMBER_OF_BOARDS) {
			game.endPrompt("It's a tie!");
			return;
		}
	}

	private boolean checkSet(int innerBoard1, int innerBoard2, int innerBoard3) {
		if (boardCounter >= 4) {
			if (board[innerBoard1].equivalentTo(board[innerBoard2]) 
			&& board[innerBoard2].equivalentTo(board[innerBoard3])) {
				game.endPrompt(game.checkWinner(board[innerBoard1].winner().toString()) + " wins!");
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

	public void enable(Position position) {
		for (int i = 0; i < Position.values().length; i++) {
			if (position == Position.values()[i]) {
				board[i].enable();
				break;
			}
		}
	}

	public void enableAll() {
		for (TicTacToeBoard b : board) {
			b.disable();
		}
	}

	public void reset() {
		for (TicTacToeBoard b : board) {
			b.reset();
		}
	}
}