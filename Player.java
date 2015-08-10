/* Author: Luigi Vincent
Player Enum to be used in Tic Tac Toe
facilitates tracking the current player, provides symbol & style to be used
*/

public enum Player {
	X("-fx-text-fill: darkred;"),
	O("-fx-text-fill: gold;");

	private final String style;

	Player(String style) {
		this.style = style;
	}

	public String getStyle() {
		return style;
	}
}