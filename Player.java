import java.util.Random;

public class Player {
	
	final static int WRKENERGY = 10;
	
	final static int EXENERGY = 30;
	final static int HPFROMEX = 10;
	
	final static int STUDYENERGY = 30;
	
	final static int RKTENERGY = 30;
	final static int RKTPTSTOWIN = 1;
	
	final static int FOODMONEY = 10;
	final static int FOODENG = 10;
	
	final static int DRUGHEALTH = 10;
	final static int DRUGENG 	= 20;
	final static int DRUGMENTAL = 5;
	
	final static int SLEEPDEPRIVED = 10;
	
	final static int DAILYSORROW = 5;
	final static int RENTNSTUFF  = 10;
	
	final static int FUNHEALTH = 10;
	
	
	
	
	int physHealth = 80;
	int mentalHealth = 80;
	int money = 80;
	
	int rocketPoints = 0;
	int dailyTime;
	
	boolean isAlive = true;
	boolean deathMental = false;
	boolean deathPhysical = false;
	boolean isBroke 	= false;
	boolean isWinning = false;
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
		//initiate a mini game, might get you extra bonus points
		int extraSal = inputPrompt.miniGame();
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
			dailyTime--;
		}
		if(physHealth > 100) {
			physHealth = 100;
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
			dailyTime--;
		}
		
		
		//output a message depending on weather you have successfully completed the exercise 
		inputPrompt.studyRes(success);
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
	}
	
	void buildRocket() {
		boolean success = false;
		if (this.energy >= RKTENERGY) {
			this.rocketPoints++;
			if(this.rocketPoints == RKTPTSTOWIN) {
				//this then ends the game in he mainGame loop
				this.isWinning = true;
			}
			this.energy -= RKTENERGY;
			success = true;
			dailyTime--;
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
			dailyTime--;
		}
		if(energy > 100) {
			energy = 100;
		}
		inputPrompt.eatRes(success);
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
		boolean success = false;
		if (this.physHealth >= DRUGHEALTH) {
			this.physHealth -= DRUGHEALTH;
			this.energy += DRUGENG;
			this.mentalHealth -= DRUGMENTAL;
			success = true;
			dailyTime--;
		}
		
		if(energy > 100) {
			energy = 100;
		}
		
		inputPrompt.drugRes(success);
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
		
	}

	private void haveFun() {
		// TODO Auto-generated method stub
		int action = inputPrompt.funActivities(money);
		this.money -= action;
		//has better effect depending on how much money you have spent
		this.mentalHealth += FUNHEALTH + action;
		dailyTime--;
		inputPrompt.mainInfo(energy, physHealth, mentalHealth, money);
		
	}

	public boolean checkLife() {
		if(this.physHealth <= 0) {
			deathPhysical = true;
		}
		if(this.mentalHealth <= 0) {
			deathMental = true;
		}
		if(this.money <= 0) {
			isBroke = true;
		}
		
		//if whichever of these is false, we have lost the game:
		this.isAlive = !(deathPhysical || deathMental || isBroke);
		return this.isAlive;
	}

}
