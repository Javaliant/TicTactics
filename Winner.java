/* Author: Luigi Vincent */

public enum Winner {
	NONE(""),
	X("-fx-color: darkred;"),
	O("-fx-color: royalblue"),
	TIE("-fx-color: indigo;");

	private final String style;

	Winner(String style) {
		this.style = style;
	}


	public String getStyle() {
		return style;
	}
}