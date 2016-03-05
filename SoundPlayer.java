import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;

public class SoundPlayer {
	static MediaPlayer player;

	public static void play(String target) {
		player = new MediaPlayer(new Media(SoundPlayer.class.getResource(target + ".mp3").toString()));
		player.play();
	}
}