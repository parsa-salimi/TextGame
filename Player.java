
public class Player {
	int physHealth = 80;
	int mentalHealth = 80;
	int workEnergy = 10;
	int dailyTime;
	IO inputPrompt = new IO();
	
	int money = 80;
	int energy;
	int salary = 5;
	
	void wakeUp() {
		this.energy =(int)((this.physHealth * 0.7) + (this.mentalHealth *0.3));
		dailyTime = 4;
	}
	
	void goToWork(int intensity) {
		this.money += salary + intensity;
		this.energy -= (workEnergy + intensity * 5);
		//decide whether or not to work an extra shift
		boolean extraWork = inputPrompt.extraShift();
		if (extraWork) {
			this.money += salary + intensity;
			this.energy -= (workEnergy + intensity * 5);
			dailyTime--;
		}
		inputPrompt.workIsOver(salary + intensity, workEnergy + intensity * 5, extraWork);
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
		
	}
	
	/*the integer arguement here is just here to get passed around to dailyOptions,
	 * basically, depending on your situation, it would print out a different dialogue, this would 
	 * make the dialogues less mechanical and a bit more realistic
	 * 0: first time coming back from work
	 * 1 : staying late 
	 * 2 : player typed name incorrectly
	 */
	void dailyLife(int dialogue) {
		String action = inputPrompt.dailyOptions(dailyTime,dialogue).toLowerCase();
		switch(action) {
		case "excersice":	this.excersice();
							break;
		case "study":		this.study();
							break;
		case "rocket":		this.buildRocket();
							break;
		default:	dailyLife(2);
		}	
	}
	void excersice() {
		
	}
	
	void study() {
		
	}
	
	void buildRocket() {
		
	}
}
