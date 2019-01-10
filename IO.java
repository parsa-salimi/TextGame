import java.util.Scanner;

public class IO {
	Scanner s = new Scanner(System.in);
	public void greet() {
		System.out.println("...");
	}
	void mainInfo(int energy,int phys,int mental,int money) {
		System.out.println("**********************");
		System.out.println("energy is " + energy);
		System.out.println("physical health is " + phys);
		System.out.println("mental health is " + mental);
		System.out.println("money is " + money);
		System.out.println("**********************");
	}
	
	void intensity() {
		System.out.printf("How intense do you want to work?\n");
	}
	
	int workIntensity() {
		System.out.println("How intense do you want to work today? choose a number "
							+ "between 0 and 5");
		return s.nextInt();
	}
	
	void workIsOver(int salary, int energy, boolean extraWork) {
		//if we have worked extra then we have gained double the amount,
		//so we need to specify this to print the correct values
		int multiplier = (extraWork) ? 2 : 1;
		System.out.println("you're coming back from a hard day's work. you have gained "
				+ salary * multiplier + " dollars but have lost " + energy*multiplier + " energy");
	}
	boolean extraShift() {
		boolean b = false;
		System.out.println("Do you want to work an extra shift?, this will reduce "
				+ "your energy levels,\n3 and will take up your time, but i will get you money, obviouly"
				+ " press y/n");
		String c = s.nextLine();;
		if(c.equalsIgnoreCase("y")) {
			b= true;
		}
		else if (c.equalsIgnoreCase("n")) {
			b = false;
		}
		else {
			System.out.println("please type in either 'y' or 'n'");
			extraShift();
		}
		return b;
	}
	
	void dailyOptions(int time) {
		System.out.println("The day has just begun!, what do you want to do? you still have " +
							time + " time slots, use them wisely!");
		System.out.println("type in the number besides any list item do do that activity");
		
	}
}
