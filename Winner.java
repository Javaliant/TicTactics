




public enum Winner {
	NONE(""),
	X("-fx-color: darkred;"),
	O("-fx-color: gold"),
	TIE("-fx-color: orangered;");

	private final String style;

	Winner(String style) {
		this.style = style;
	}


	public String getStyle() {
		return style;
	}
}