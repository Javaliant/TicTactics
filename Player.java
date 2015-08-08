enum Player {
	O("O", "-fx-text-fill: gold;"),
	X("X", "-fx-text-fill: darkred");

	private final String symbol;
	private final String style;

	Player(String symbol, String style) {
		this.symbol = symbol;
		this.style = style;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getStyle() {
		return style;
	}
}