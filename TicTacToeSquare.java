/* Author: Luigi Vincent

*/

import javafx.scene.control.Button;

public class TicTacToeSquare {
	private Button button = new Button();
	private final int SQUARE_SIZE = 70;

	public TicTacToeSquare() {
		button.setMinSize(SQUARE_SIZE, SQUARE_SIZE);
		button.setOnAction(e -> {
			if (button.getText().isEmpty()) {
				button.setText(TicTacTest.checkCurrentPlayer().getSymbol());
				button.setStyle(TicTacTest.checkCurrentPlayer().getStyle());
				//TicTacToeBoard.evaluateBoard();
			}
		});
	}

	public Button button() {
		return button;
	}

	public void clear() {
		button.setText("");
	}

	public boolean equivalentTo(TicTacToeSquare target) {
		return !button.getText().isEmpty() && button.getText().equals(target.button().getText());
	}
}



/*import javafx.scene.control.Button;

public class TicTacToeSquare {
	private boolean filled;
	static boolean turnTracker;
	private Button square = new Button();
	private final int SQUARE_SIZE = 100;

	TicTacToeSquare() {
		square.setMinHeight(SQUARE_SIZE);
		square.setMinWidth(SQUARE_SIZE);
		square.setOnAction(e -> {
			if (square.getText().isEmpty()) {
				square.setText(turnTracker ? "O" : "X");
				square.setStyle(
					turnTracker ? "-fx-text-fill: gold;" : "-fx-text-fill: darkred;");
				filled = true;
				turnTracker = !turnTracker;
				TicTacToe.evaluateBoard();
			}
		});
	}

	public Button button() {
		return square;
	}

	public boolean equals(TicTacToeSquare target) {
		return filled && square.getText().equals(target.button().getText());
	}

	public void clear() {
		filled = false;
		square.setText("");
		square.setDisable(false);
	}
}*/