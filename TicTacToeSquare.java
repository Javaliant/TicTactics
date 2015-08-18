/* Author: Luigi Vincent
Object to represent idea of tic tac toe squares
*/

import javafx.scene.control.Button;

public class TicTacToeSquare {
	private Button button = new Button();
	private final int SQUARE_LENGTH = 70;

	TicTacToeSquare(TicTacticsGame game, TicTacToeBoard board, Position position) {
		button.setMinSize(SQUARE_LENGTH, SQUARE_LENGTH);
		button.setOnAction(e -> {
			if (button.getText().isEmpty()) {
				button.setText(game.getCurrentPlayer().toString());
				button.setStyle(game.getCurrentPlayer().getStyle());
				board.evaluateState();
				game.endTurn();
				game.board().disable();
				game.board().enable(position);
			}
		});
	}

	public Button button() {
		return button;
	}

	public boolean equivalentTo(TicTacToeSquare target) {
		return !button.getText().isEmpty() && button.getText().equals(target.button().getText());
	}

	public void reset() {
		button.setText("");
		button.setStyle("");
		button.setDisable(false);
	}
}