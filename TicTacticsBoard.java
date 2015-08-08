/* Author: Luigi Vincent

*/

import javafx.scene.layout.GridPane;

public class TicTacticsBoard extends GridPane {
	private final int BOARD_SIZE = 9;
	private TicTacToeBoard[] board = new TicTacToeBoard[BOARD_SIZE];

	public TicTacticsBoard() {
		for (int i = 0; i < board.length; i++) {
			board[i] = new TicTacToeBoard();
			board[i].setStyle("-fx-border-color: blue; -fx-border-width: 2; -fx-border-insets: 2");
			add(board[i], i / 3, i % 3);
		}
	}
}