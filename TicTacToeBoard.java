/* Author: Luigi Vincent
Board class to represent idea of tic tac toe board
*/

import javafx.scene.layout.GridPane;

public class TicTacToeBoard extends GridPane {
	private final int NUMBER_OF_SQUARES = 9;
	public int boardCounter;
	private TicTacToeSquare[] board = new TicTacToeSquare[NUMBER_OF_SQUARES];
	private TicTacticsGame game;
	private boolean captured = false;
	private Winner winner = Winner.NONE;

	TicTacToeBoard(TicTacticsGame game) {
		this.game = game;

		for (int i = 0; i < board.length; i++) {
			board[i] = new TicTacToeSquare(this.game, this, Position.values()[i]);
			add(board[i].button(), i / 3, i % 3);
		}
		setStyle("-fx-border-color: cadetblue; -fx-border-width: 2; -fx-border-radius: 5");
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

		if (++boardCounter == NUMBER_OF_SQUARES) {
			winner = Winner.TIE;
			captured = true;
			game.evaluateState();
			styleBoard();
			return;
		}
	}

	private boolean checkSet(int square1, int square2, int square3) {
		if (boardCounter >= 2) {
			if (board[square1].equivalentTo(board[square2]) 
			&& board[square2].equivalentTo(board[square3])) {
				if (!captured) {
					winner = board[square1].button().getText().equals("X") ? Winner.X : Winner.O;
					captured = true;
					game.evaluateState();
					styleBoard();
				}
				return true;
			}
		}
		return false;
	}

	public boolean equivalentTo(TicTacToeBoard target) {
		return winner != Winner.NONE && (winner == target.winner() || target.winner() == Winner.TIE);
	}

	public Winner winner() {
		return winner;
	}

	private void styleBoard() {
		for (TicTacToeSquare square : board) {
			square.button().setStyle(winner.getStyle());
		}
	}

	public void disable() {
		for (int i = 0; i < board.length; i++) {
			board[i].button().setDisable(true);
		}
	}

	public void enable() {
		for (int i = 0; i < board.length; i++) {
			board[i].button().setDisable(false);
		}
	}

	public boolean isCaptured() {
		return captured;
	}

	public boolean isFilled() {
		for (TicTacToeSquare square : board) {
			if (square.button().getText().isEmpty()) {
				return false;
			}
		}
		return true;
	}

	public void reset() {
		captured = false;
		winner = Winner.NONE;
		for (int i = 0; i < board.length; i++) {
			board[i].reset();
		}
	}
}