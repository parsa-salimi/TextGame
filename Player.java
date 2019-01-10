
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
	
	void dailyLife() {
		inputPrompt.dailyOptions(dailyTime);
	}
}
