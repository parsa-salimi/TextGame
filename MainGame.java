
public class MainGame {
	static Player player;
	 static IO inputPrompt;
	public static void main(String[] Args) {
		MainGame game = new MainGame();
		player = new Player();
		inputPrompt = new IO();
		game.startGame();
	
	}
	
	private void startGame() {
		
		inputPrompt.greet();
		
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
		//player.dailyOptions();
	}
	


}
