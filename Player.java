
public class Player {
	
	final static int WRKENERGY = 10;
	
	final static int EXENERGY = 30;
	final static int HPFROMEX = 10;
	
	final static int STUDYENERGY = 30;
	
	final static int RKTENERGY = 30;
	final static int RKTPTSTOWIN = 20;
	
	final static int FOODMONEY = 10;
	final static int FOODENG = 10;
	
	final static int SLEEPDEPRIVED = 10;
	
	final static int DAILYSORROW = 5;
	final static int RENTNSTUFF  = 10;
	
	
	
	int physHealth = 80;
	int mentalHealth = 80;
	int money = 80;
	
	int rocketPoints = 0;
	int dailyTime;
	boolean isAlive = true;
	
	IO inputPrompt = new IO();
	
	int energy;
	int salary = 5;
	
	void wakeUp() {
		this.energy =(int)((this.physHealth * 0.7) + (this.mentalHealth *0.3));
		dailyTime = 4;
	}
	
	void goToWork(int intensity) {
		this.money += salary + intensity;
		this.energy -= (WRKENERGY + intensity * 5);
		//decide whether or not to work an extra shift
		boolean extraWork = inputPrompt.extraShift();
		if (extraWork) {
			this.money += salary + intensity;
			this.energy -= (WRKENERGY + intensity * 5);
			dailyTime--;
		}
		inputPrompt.workIsOver(salary + intensity, WRKENERGY + intensity * 5, extraWork);
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
		case "meds":		this.getEnergised();
							break;
		case "fun":			this.haveFun();
							break;
		default:			dailyLife();
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
		if (this.energy >= STUDYENERGY) {
			this.salary++;
			this.energy -= STUDYENERGY;
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
		boolean success = false;
		if (this.money >= FOODMONEY) {
			this.money -= FOODMONEY;
			this.energy += FOODENG;
			success = true;
		}
		inputPrompt.eatRes(success, RKTPTSTOWIN - rocketPoints);
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
	}
	

	public void maybeSleepLate() {
		boolean stayingUp = inputPrompt.sleep();
		if (stayingUp) {
			this.physHealth -= SLEEPDEPRIVED;
			this.dailyLife();
		}
	}

	public void maintenace() {
		this.mentalHealth -= DAILYSORROW;
		this.money -= RENTNSTUFF;
		inputPrompt.endDay();
	}
	
	private void getEnergised() {
		// TODO Auto-generated method stub
		
	}

	private void haveFun() {
		// TODO Auto-generated method stub
		String action = inputPrompt.funActivities(dailyTime).toLowerCase();
		
	}

	public boolean checkLife() {
		if(this.physHealth <= 0 || this.mentalHealth <= 0 
				|| this.money <= 0) {
			this.isAlive = false;
		}
		return this.isAlive;
	}

	public void die() {
		
	}
}
