import java.io.IOException;
import java.util.Scanner;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

public class IO {
	Scanner s = new Scanner(System.in);
	DefaultTerminalFactory defaultTerminalFactory = new DefaultTerminalFactory();
    Terminal terminal = null;
    TextGraphics textGraphics = null;
    //the IO object keeps the state of the current cursor location, incrementing it every time we print a message
    int cursorPosition = 2;
    IO() {
    	try {
			terminal = defaultTerminalFactory.createTerminal();
			textGraphics = terminal.newTextGraphics();
			//change the text color of the terminal
		    textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
		    textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
	public void greet() throws IOException {

	}
	//this method in effect replaces System.out.println
	public void printMsg(String s) {
	    textGraphics.setForegroundColor(TextColor.ANSI.BLUE);
	    textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
		if(cursorPosition > 100) {
			//fill the screen with one large rectangle of empty chars, to clear the screen
			//first arg is coordinates of top left of rectangle, second arg is width/height
			textGraphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(200,100), ' ');
			cursorPosition = 2;
		}
		textGraphics.putString(2, cursorPosition, s);
		try {
			terminal.putCharacter('\n');
			terminal.flush();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//go to next line, if more than 100 characters move an extra line
		cursorPosition += (s.length()/100)+1;
	    textGraphics.setForegroundColor(TextColor.ANSI.GREEN);
	    textGraphics.setBackgroundColor(TextColor.ANSI.BLACK);
		
	}
	void mainInfo(int energy,int phys,int mental,int money){
		//clear screen
		//first arguement is column, second is row
		textGraphics.putString(100,2,"energy is ");
		textGraphics.putString(120,2,Integer.toString(energy));
		//first arguement is topleft position(col,row), second is seize of rectangle(width,height), third is character to fill
		//fill screen with empty chars to override previous health bars
		textGraphics.fillRectangle(new TerminalPosition(125,2), new TerminalSize(200, 1), ' ');
		textGraphics.fillRectangle(new TerminalPosition(125,2), new TerminalSize(energy/2,1), '-');
		
		textGraphics.putString(100,3,"physical health is ");
		textGraphics.putString(120,3,Integer.toString(phys));
		textGraphics.fillRectangle(new TerminalPosition(125,3), new TerminalSize(200, 1), ' ');
		textGraphics.fillRectangle(new TerminalPosition(125,3), new TerminalSize(phys/2,1), '-');
		
		textGraphics.putString(100,4,"mental health is ");
		textGraphics.putString(120,4,Integer.toString(mental));
		textGraphics.fillRectangle(new TerminalPosition(125,4), new TerminalSize(200, 1), ' ');
		textGraphics.fillRectangle(new TerminalPosition(125,4), new TerminalSize(mental/2,1), '-');
		
		textGraphics.putString(100,5, "money is ");
		textGraphics.putString(120,5,Integer.toString(money));
		textGraphics.fillRectangle(new TerminalPosition(125,5), new TerminalSize(200, 1), ' ');
		textGraphics.fillRectangle(new TerminalPosition(125,5), new TerminalSize(money/2,1), '-');
		try {
			terminal.putCharacter('\n');
			terminal.flush();
		}
		catch (IOException ex) {
			
		}
		
	}
	

	
	int workIntensity() {
		printMsg("How intense do you want to work today? choose a number "
							+ "between 0 and 5");
		int retVal = 0;
		try {
			KeyStroke keyStroke = terminal.readInput();
			Character buffer = keyStroke.getCharacter();
			System.out.println(buffer);
			retVal = Character.getNumericValue(buffer);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal;
	}
	
	//TODO
	//tells the player to do a simple calculation, in a given amount of time
	int miniGame() {
		return 0;
	}
	
	void workIsOver(int salary, int energy, boolean extraWork) {
		//if we have worked extra then we have gained double the amount,
		//so we need to specify this to print the correct values
		int multiplier = (extraWork) ? 2 : 1;
		printMsg("you're coming back from a hard day's work. you have gained "
				+ salary * multiplier + " dollars but have lost " + energy*multiplier + " energy");
	}
	//whether or not to work an extra shift
	boolean extraShift() {
		boolean b = true;
		printMsg("Do you want to work an extra shift?, this will reduce "
				+ "your energy levels,\n and will take up your time, but it will get you money, obviouly"
				+ " press y/n");
		String c = s.nextLine();;
		if(c.equalsIgnoreCase("y")) {
			b= true;
		}
		else if (c.equalsIgnoreCase("n")) {
			b = false;
		}
		else {
			printMsg("please type in either 'y' or 'n'");
			extraShift();
		}
		return b;
	}
	
	String dailyOptions(int time) {
		//list of activities, needs expansion...
		printMsg( "The day has just begun!, what do you want to do? you still have " +
				time + " time slots, use them wisely!");
		printMsg("type in the word in astericks to do that actvity\n");
		textGraphics.putString(100,8,"hit the gym and *Exercise*");
		textGraphics.putString(100,9,"go to the library and *study*");
		textGraphics.putString(100,10,"work on *rocket*, your escape from this hell");
		textGraphics.putString(100,11,"go to a restaurant and *eat* some extra meal");
		textGraphics.putString(100,12,"have some *fun*... this opens up a whole list of new options");
		textGraphics.putString(100,13,"try performance enhancing *meds*\n");
		//the daily options are already printed out in the function "greet", so here we only ask for input.
		
		try {
			terminal.flush();
			terminal.putCharacter('\n');
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String activity = s.nextLine();
		return activity;
	}
	
	public int funActivities(int money) {
		printMsg("how much money do you want to spend?, you currently have " +
				money + " dollars");
		int activity = s.nextInt();
		if (activity >= money) {
			printMsg("you don't have that much money, try again");
			return funActivities(money);
		}
		else if (activity < 0) {
			printMsg("money cannot be negative!, try again");
			return funActivities(money);
		}
		else {
			return activity;
		}
	}
	
	//decide if you want to stay up and get an extra time slot
	public boolean sleep() {
		printMsg("I'm tired... it's getting late. But i don't want to sleep, or should i? ("
				+ "press y or n)");
		String query = s.nextLine();
		if(query.equalsIgnoreCase("y")) {
			printMsg("sleep is for the weak... I'm gonna stay up and do what i must");
			return true;
		}
		else if(query.equalsIgnoreCase("n")) {
			printMsg("obviously i can't even focus tommorow if i stay up this late...");
			return false;
		}
		else {
			printMsg("please type in either y or n");
			return sleep();
		}
	}

	
	//result of exercising
	void excersiceRes(boolean success) {
		if (success) {
			printMsg("You come back from the gym, very tired but also proud, plus, you are feeling so much"
					+ "healthier right now. Your current stats are: ");
		}
		else {
			printMsg("you're just so damn tired to go to the gym right now. Well, you can always go home "
					+ "and watch netflix right? for the record, your stats are: ");
		}
	}
	
	//result of studying
	void studyRes(boolean success) {
		if (success) {
			printMsg("whew, that was quite the mental strain. I definately deserve a rest,"
					+ " and a pay raise!");
		}
		else {
			printMsg("ugh, who wants to learn about linear transformations on "
					+ "finite dimensional vector spaces... when you can just lie down on the couch without "
					+ "a care in the world. I mean, I'm pretty tired as you can see from here: ");
		}
	}
	
	//result of building rocket
	void rocketRes(boolean success, int rocketPoints) {
		if (success) {
			printMsg("without the ministry of love noticing... you have progressed towards building "
					+ "the ROCKET. now you only need to do this " + rocketPoints + " more times to escape!" );
		}
		else {
			printMsg("as desperately as you want to get out of here... you just don't have the motivation"
					+ " to do anything today. i mean, look at how tired you are:  ");
		}
	}
	public void eatRes(boolean success) {
		if (success) {
			printMsg("it's good not to starve for a day, and have an actual meal,"
					+ "I'm feeling so much more motivated now" );
		}
		else {
			printMsg("i don't even have enough money to eat a proper meal...");
		}
		
	}
	
	public void drugRes(boolean success) {
		if (success) {
			printMsg("amphetamines make me feel so good and alive and energsed and..."
					+ "I feel like i can do anything now" );
		}
		else {
			printMsg("If i have any more of these pills i'm gonna be sick...");
		}
		
	}
	//TODO different dialogue based on how your day went
	public void endDay() {
		printMsg("the day is over... (update with more meaningful dialogue)");
	}
	public void endGame(boolean won) {
		// TODO improve dialogue
		if(won) {
			try {
				terminal.clearScreen();
			    textGraphics.setForegroundColor(TextColor.ANSI.RED);
			    textGraphics.setBackgroundColor(TextColor.ANSI.WHITE);
			    textGraphics.fillRectangle(new TerminalPosition(0,0), new TerminalSize(200, 200), '*');
			    textGraphics.putString(50, 50, "YOU HAVE WON THE GAME!! THE ROCKET HAS BEEN BUILT!");
			    
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			printMsg("you have won the game. the ROCKET has been built. ");
		}
		else {
			System.out.print("you have lost the game");
		}
		
	}
	
	
}
