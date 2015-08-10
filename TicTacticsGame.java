/* Author: Luigi Vincent
Tic Tac Toe Game Class
Has menus, score tracking, name adding, reset features, mnemonic parsing, and keyboard shortcuts
*/

import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.beans.binding.Bindings;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.Scene;
import javafx.scene.text.Text;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyCodeCombination;
import javafx.scene.input.KeyCombination;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class TicTacticsGame extends BorderPane {
	private StringProperty xPlayer = new SimpleStringProperty("X player");
	private StringProperty oPlayer = new SimpleStringProperty("O player");
	private IntegerProperty xScore = new SimpleIntegerProperty(0);
	private IntegerProperty oScore = new SimpleIntegerProperty(0);
	private IntegerProperty tieScore = new SimpleIntegerProperty(0);
	private boolean scoreDisplayed;
	private Player currentPlayer = Player.X;
	private TicTacticsBoard board;

	TicTacticsGame(Stage stage) {
		board = new TicTacticsBoard(this);
		setTop(generateMenuBar(stage));
		setCenter(board);
	}

	private MenuBar generateMenuBar(Stage stage) {
		MenuItem newGameItem = new MenuItem("_New Game");
		newGameItem.setAccelerator(new KeyCodeCombination(KeyCode.N, KeyCombination.SHORTCUT_DOWN));
		newGameItem.setOnAction(e -> newGame());

		MenuItem exitItem = new MenuItem("E_xit");
		exitItem.setOnAction(e -> Platform.exit());

		Menu gameMenu = new Menu("_Game");
		gameMenu.getItems().addAll(newGameItem, exitItem);

		MenuItem addItem = new MenuItem("_Add player name(s)");
		addItem.setAccelerator(new KeyCodeCombination(KeyCode.A, KeyCombination.SHORTCUT_DOWN));
		addItem.setOnAction(e -> addName(stage));

		Text xText = new Text();
		xText.textProperty().bind(
			Bindings.concat(xPlayer).concat(" wins: ").concat(xScore.asString())
		);

		Text oText = new Text();
		oText.textProperty().bind(
			Bindings.concat(oPlayer).concat(" wins: ").concat(oScore.asString())
		);

		Text tieText = new Text();
		tieText.textProperty().bind(
			Bindings.concat("Ties: ").concat(tieScore.asString())
		);

		VBox scoreLayout = new VBox(5);
		scoreLayout.getChildren().addAll(xText, oText, tieText);
		scoreLayout.setPadding(new Insets(5));
		scoreLayout.setAlignment(Pos.CENTER);

		MenuItem trackItem = new MenuItem("_Toggle score display");
		trackItem.setAccelerator(new KeyCodeCombination(KeyCode.T, KeyCombination.SHORTCUT_DOWN));
		trackItem.setOnAction(e -> {
			if (!scoreDisplayed) {
				setRight(scoreLayout);
				scoreDisplayed = true;
				stage.sizeToScene();
			} else {
				setRight(null);
				scoreDisplayed = false;
				stage.sizeToScene();
			}
		});

		MenuItem resetItem = new MenuItem("_Reset score");
		resetItem.setAccelerator(new KeyCodeCombination(KeyCode.R, KeyCombination.SHORTCUT_DOWN));
		resetItem.setOnAction( e -> {
			xScore.set(0);
			oScore.set(0);
			tieScore.set(0);
		});

		Menu scoreMenu = new Menu("_Score");
		scoreMenu.getItems().addAll(
			addItem,
			trackItem,
			resetItem
		);

		activateMnemonics(
			gameMenu,
			newGameItem,
			exitItem,
			scoreMenu,
			addItem,
			trackItem,
			resetItem
		);

		MenuBar menuBar = new MenuBar();
		menuBar.getMenus().addAll(gameMenu, scoreMenu);
		return menuBar;
	}

	public void endPrompt(String message) {
		board.disable();

		if (message.equals("It's a tie!")) {
			tieScore.setValue(tieScore.getValue() + 1);
		}
		
		Stage stage = new Stage();
		Label label = new Label(message);
		label.setStyle("-fx-font-weight: bold;");

		final int BUTTON_WIDTH = 80;

		Button reset = new Button("New Round");
		reset.setMinWidth(BUTTON_WIDTH);
		reset.setOnAction(e -> {
			stage.close();
			newRound();
		});
		reset.setDefaultButton(true);

		Button quit = new Button("Quit");
		quit.setMinWidth(BUTTON_WIDTH);
		quit.setOnAction(e -> Platform.exit());

		HBox gameLayout = new HBox(5);
		gameLayout.getChildren().addAll(reset, quit);
		gameLayout.setAlignment(Pos.CENTER);

		VBox layout = new VBox(5);
		layout.getChildren().addAll(label, gameLayout);
		layout.setAlignment(Pos.CENTER);

		stage.setScene(new Scene(layout, 175 + new Text(message).getLayoutBounds().getWidth(), 75));
		stage.sizeToScene();
		stage.setTitle("Game Over");
		stage.show();
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	private void activateMnemonics(MenuItem... items) {
		for (MenuItem item : items) {
			item.setMnemonicParsing(true);
		}
	}

	private void newGame() {
		board.boardCounter = 0;
		currentPlayer = Player.X;
		board.reset();
	}

	private void newRound() {
		board.boardCounter = 0;
		board.reset();
	}

	private void addName(Stage primaryStage) {
		Stage stage = new Stage();
		
		Label xName = new Label("Enter X player: ");
		GridPane.setConstraints(xName, 0, 0);
		TextField xPlayerField = new TextField();
		GridPane.setConstraints(xPlayerField, 1, 0);

		Label oName = new Label("Enter O player: ");
		GridPane.setConstraints(oName, 0, 1);
		TextField oPlayerField = new TextField();
		GridPane.setConstraints(oPlayerField, 1, 1);

		Button submit = new Button("Submit");
		submit.setOnAction(e -> {
			String xString = xPlayerField.getText();
			String oString = oPlayerField.getText();
			if (!xString.replaceAll("[^a-zA-Z]", "").isEmpty()) {
				xPlayer.setValue(xString);
			}
			if (!oString.replaceAll("[^a-zA-Z]", "").isEmpty()) {
				oPlayer.setValue(oString);
			}
			primaryStage.sizeToScene();
			stage.close();
		});
		submit.setDefaultButton(true);
		GridPane.setConstraints(submit, 0, 2);

		GridPane layout = new GridPane();
		layout.getChildren().addAll(
			xName,
			xPlayerField,
			oName,
			oPlayerField,
			submit
		);

		stage.setScene(new Scene(layout));
		stage.setTitle("Set name(s): ");
		stage.sizeToScene();
		stage.show();
	}

	public String checkWinner(String winner) {
		if (winner.equals("X")) {
			xScore.setValue(xScore.getValue() + 1);
			return xPlayer.getValue();
		} else {
			oScore.setValue(oScore.getValue() + 1);
			return oPlayer.getValue();
		}
	}

	public void endTurn() {
		currentPlayer = currentPlayer == Player.X ? Player.O : Player.X;
	}

	public TicTacticsBoard board() {
		return board;
	}
}