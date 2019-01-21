import java.io.FileInputStream;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;



/* TODO :  *mini game
 * 		   *music
 * 		  random encounters
 *         *curses -- kinda dine
 *        different dialogues
 *        mental health depends on different factors
 *        SHOULD NOT GO ABOVE 100
 *        CARCH INVALID INPUT
 *
 */
public class MainGame {
	static Player player;
	static MusicPlayer radio;
	public static void main(String[] Args) throws Exception {
		MainGame game = new MainGame();
		player = new Player();
		radio = new MusicPlayer();
		//see if the player is alive
		while(player.checkLife()) {
			game.startDay();
			if(player.isWinning) {
					break;
				}
			}
		game.endGame(player.isWinning);
	}
	


	private void startDay() throws Exception {
		
		//initializes daily energy and time slots
		player.wakeUp();
		//play a song according to how you feel, i.e your mental health
		radio.playMood(player.mentalHealth);
		player.inputPrompt.mainInfo(player.energy, player.physHealth, player.mentalHealth,
								player.money);
		//start daily work
		/*decide how intense you want to work today, more intense means
		 * more money but also more energy loss
		 */
		int wIntensity = player.inputPrompt.workIntensity();
		player.goToWork(wIntensity);
		//this is the main part of the game, and where most of your decisions are made
		while(player.dailyTime > 0) {
			player.dailyLife();
		}
		//decide whether or not to sacrifice a few hours of sleep for an extra time slot.
		player.maybeSleepLate();
		//Decrease money and mental health levels
		player.maintenace();
	}
	
	
	private void endGame(boolean winning) throws Exception {
		// TODO Auto-generated method stub
		radio.stop();
		player.inputPrompt.endGame(winning);
	}

}


