/* Author: Luigi Vincent

*/

import javafx.scene.layout.GridPane;

public class TicTacToeBoard extends GridPane {
	private final int BOARD_SIZE = 9;
	private TicTacToeSquare[] board = new TicTacToeSquare[BOARD_SIZE];

	public TicTacToeBoard() {
		for (int i = 0; i < board.length; i++) {
			board[i] = new TicTacToeSquare();
			add(board[i].button(), i / 3, i % 3);
		}
	}
}