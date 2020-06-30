import java.util.*;

public class MazeRunner {
	public static Maze myMap = new Maze();
	public static int numOfMoves = 0;
	public static String stop = "false";
	
	// main method
	public static void main(String[] args) {
		// print text intro
		intro();
		
		// plot maze
		myMap.printMap();
		
		while(!myMap.didIWin()) {
			stop = userMover();
			if (stop.equals("true")) {
				break;
			}
		}
		if(myMap.didIWin()) {
			System.out.print("Congratulations, you made it out alive!");
			System.out.println("and you did it in " + numOfMoves + " moves");
		}
		
	}
	
	public static void intro() {
		System.out.println("Wellcome to Maze Runner! \nHere is your current position");
		
		
	}
	
	public static void movesMessage(int numOfMoves){
        switch (numOfMoves) {
            case 50:
                System.out.println("Warning: You have made 50 moves, you have 50 remaining before the maze exit closes");
                break;
            case 75:
                System.out.println("Alert! You have made 75 moves, you only have 25 moves left to escape.");
                break;
            case 90:
                System.out.println("DANGER! You have made 90 moves, you only have 10 moves left to escape!!");
                break;
            case 100:
                System.out.println("Oh no! You took too long to escape, and now the maze exit is closed FOREVER >:[");
                break;
            default: break;
        }
    }
	public static String userMover() {
		System.out.println("Where would you like to move? (R, L, U, D)");
		
		//System.out.println("can I move Right: " + myMap.canIMoveRight());
		//System.out.println("can I move Left: " + myMap.canIMoveLeft());
		//System.out.println("can I move Up: " + myMap.canIMoveUp());
		//System.out.println("can I move Down: " + myMap.canIMoveDown());
		
		Scanner input = new Scanner(System.in);
		String answer = input.next();
		
		// get user input, only accept RLUD
		while(!answer.equals("R") && !answer.equals("L") && !answer.equals("U") && !answer.equals("D")) {
			System.out.println("Enter ONLY R, L, U, D, please: ");
			answer = input.next();
			System.out.println("Your input " + answer);
		}
		
		
		if (!myMap.isThereAPit(answer)) {
			if (answer.equals("R") && myMap.canIMoveRight() == true) {
				myMap.moveRight();
			}
			else if (answer.equals("L") && myMap.canIMoveLeft() == true) {
				myMap.moveLeft();
			}
			else if (answer.equals("U") && myMap.canIMoveUp() == true) {
				myMap.moveUp();
			}
			else if (answer.equals("D") && myMap.canIMoveDown() == true) {
				myMap.moveDown();
			}
			else {
				System.out.println("Sorry, you’ve hit a wall.");
			}
			numOfMoves = numOfMoves + 1;
		}
		else {
			System.out.println("Watch out! There's a pit ahead, jump it? (Y/N)");
			Scanner isJump = new Scanner(System.in);
			String jumpAnswer = isJump.next();
			while(!jumpAnswer.equals("Y") && !jumpAnswer.equals("N")) {
				System.out.println("Please enter ONLY (Y/N)");
				jumpAnswer = isJump.next();
			}
			if (jumpAnswer.equals("Y")){
				myMap.jumpOverPit(answer);
				numOfMoves = numOfMoves + 2;
			}
			else {
				System.out.println("Sorry, but you didn't jump- you lose!");
                System.exit(0);
			}
			
		}
		myMap.printMap();
		System.out.println("Number of Moves: " + numOfMoves);
		movesMessage(numOfMoves);
		if (numOfMoves>100) {
			System.out.println("Sorry, but you didn't escape in time- you lose!");
	        System.exit(0);
	        stop = "true"; 
		}
        
		return stop;
	}
	
}
