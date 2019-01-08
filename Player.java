
public class Player {
	int physHealth = 100;
	int mentalHealth = 100;
	
	int money = 100;
	int energy;
	int salary;
	
	void updateEnergy() {
		this.energy =(int)((this.physHealth * 0.7) + (this.mentalHealth *0.3));
	}
}
