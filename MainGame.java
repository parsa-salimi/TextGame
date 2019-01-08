
public class MainGame {
	Player player;
	IO inputPrompt;
	public static void main(String[] Args) {
		MainGame game = new MainGame();
		game.startGame();
	
	}
	
	private void startGame() {
		player = new Player();
		inputPrompt = new IO();
		inputPrompt.greet();
		player.updateEnergy();
		inputPrompt.mainInfo(player.energy, player.physHealth, player.mentalHealth,
								player.money);
	}
	


}
