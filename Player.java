
public class Player {
	final static int EXENERGY = 30;
	final static int HPFROMEX = 10;
	
	final static int STENERGY = 30;
	
	final static int RKTENERGY = 30;
	final static int RKTPTSTOWIN = 20;
	
	
	
	int physHealth = 80;
	int mentalHealth = 80;
	int workEnergy = 10;
	int rocketPoints = 0;
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
		//get a request from player and return
		String action = inputPrompt.dailyOptions(dailyTime).toLowerCase();
		switch(action) {
		case "exercise":	this.exercise();
							break;
		case "study":		this.study();
							break;
		case "rocket":		this.buildRocket();
							break;
		case "eat":			this.eat();
							break;
		default:	dailyLife();
		}	
	}
	
	void exercise() {
		boolean success = false;
		if (this.energy >= EXENERGY) {
			this.physHealth += HPFROMEX;
			this.energy -= EXENERGY;
			success = true;
		}
		
		//output a message depending on weather you have successfully completed the exercise 
		inputPrompt.excersiceRes(success);
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
	}
	
	void study() {
		boolean success = false;
		if (this.energy >= STENERGY) {
			this.salary++;
			this.energy -= STENERGY;
			success = true;
		}
		
		//output a message depending on weather you have successfully completed the exercise 
		inputPrompt.studyRes(success);
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
	}
	
	void buildRocket() {
		boolean success = false;
		if (this.energy >= RKTENERGY) {
			this.rocketPoints++;
			this.energy -= RKTENERGY;
			success = true;
		}
		inputPrompt.rocketRes(success, RKTPTSTOWIN - rocketPoints);
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
	}
	
	void eat() {
		
	}

	public void maybeSleepLate() {
		// TODO Auto-generated method stub
		
	}
}
