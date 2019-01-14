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
	//whether or not to work an extra shift
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
	
	String dailyOptions(int time) {
		System.out.println("The day has just begun!, what do you want to do? you still have " +
							time + " time slots, use them wisely!");
		System.out.println("type in the word in astericks to do that actvity");
		//list of activities, needs expansion...
		System.out.println("hit the gym and *Exercise*");
		System.out.println("go to the library and *study*");
		System.out.println("work on *rocket*, your escape from this hell");
		System.out.println("go to a restaurant and *eat* some extra meal");
		System.out.println("have some *fun*... this opens up a whole list of new options");
		String activity = s.nextLine();
		return activity;
	}
	
	//result of exercising
	void excersiceRes(boolean success) {
		if (success) {
			System.out.println("You come back from the gym, very tired but also proud, plus, you are feeling so much"
					+ "healthier right now. Your current stats are: ");
		}
		else {
			System.out.println("you're just so damn tired to go to the gym right now. Well, you can always go home "
					+ "and watch netflix right? for the record, your stats are: ");
		}
	}
	
	//result of studying
	void studyRes(boolean success) {
		if (success) {
			System.out.println("whew, that was quite the mental strain. I definately deserve a rest,"
					+ " and a pay raise!");
		}
		else {
			System.out.println("ugh, who wants to learn about linear transformations on "
					+ "ginite dimensional vector spaces... when you can just lie down on the couch without "
					+ "a care in the world. I mean, I'm pretty tired as you can see from here: ");
		}
	}
	
	//result of building rocket
	void rocketRes(boolean success, int rocketPoints) {
		if (success) {
			System.out.println("without the ministry of love noticing... you have progressed towards building "
					+ "the ROCKET. now you only need to do this " + rocketPoints + " more times to escape!" );
		}
		else {
			System.out.println("as desperately as you want to get out of here... you just don't have the motivation"
					+ " to do anything today. i mean, look at how tired you are:  ");
		}
	}
	
}
