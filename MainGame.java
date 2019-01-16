import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class MainGame {
	static Player player;
	static IO inputPrompt;
	static MusicPlayer radio;
	public static void main(String[] Args) throws Exception {
		MainGame game = new MainGame();
		player = new Player();
		inputPrompt = new IO();
		radio = new MusicPlayer();
		radio.play("bip.wav");
		game.startGame();
		inputPrompt.greet(); 
		
		
	}
	

	
	private void startGame() throws Exception {
		
		//initializes daily energy and time slots
		player.wakeUp();
		inputPrompt.mainInfo(player.energy, player.physHealth, player.mentalHealth,
								player.money);
		//start daily work
		/*decide how intense you want to work today, more intense means
		 * more money but also more energy loss
		 */
		int wIntensity = inputPrompt.workIntensity();
		player.goToWork(wIntensity);
		//this is the main part of the game, and where most of your decisions are made
		while(player.dailyTime > 0) {
			player.dailyLife();
			player.dailyTime--;
		}
		//decide whether or not to sacrifice a few hours of sleep for an extra time slot.
		player.maybeSleepLate();
	}
	


}


