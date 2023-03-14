import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
	
	static ArrayList<Integer>userMove = new ArrayList<Integer>();
	static ArrayList<Integer>robotMove = new ArrayList<Integer>();
	
	public static void main(String[] args) {
		char [][] gameboard = {{'1','|','2','|','3'},
							   {'-','+','-','+','-'},
							   {'4','|','5','|','6'},
							   {'-','+','-','+','-'},
							   {'7','|','8','|','9'}};
		printGameBoard(gameboard);								//Just for print gameboard
		while (true) {
			
			System.out.println("please enter an number for the location you want to put: ");
			Scanner scan = new Scanner(System.in);
			
			int user = scan.nextInt();
			while (user >9 || user <=0 ) {
				System.out.println("You typed illegal number!");
				user = scan.nextInt();
				printGameBoard(gameboard);
			}
			while (userMove.contains(user) || robotMove.contains(user)) {
				System.out.println("ERROR!!! Enter a right location.");
				user = scan.nextInt();
				printGameBoard(gameboard);
			}
			String winner = Victory();
			if (winner.length()>0) {
				System.out.println(winner);
				break;
			}

			move(user,gameboard, 1);	
			Random rand = new Random();
			int robot = rand.nextInt(9)+1;
			while (userMove.contains(robot) || robotMove.contains(robot)) {
				robot = rand.nextInt(9)+1;
			}
			move(robot,gameboard, 2);
			printGameBoard(gameboard);
			winner = Victory();
			if (winner.length()>0) {
				System.out.println(winner);
				break;
			}
		}
	}
	
	public static void printGameBoard(char[][] gameboard) {
		for(char[] row : gameboard) {
			for(char elements : row) {
				System.out.print(elements);
			}
			System.out.println();
		}

	}
	
	public static void move(int d, char[][] gameboard, int player) {
		char symbol = ' ';
		if(player == 1) {
			symbol = 'X';
			userMove.add(d);
		}
		else {
			symbol = 'O';
			robotMove.add(d);
		}
		
		switch(d) {
			case 1:
				gameboard[0][0] = symbol;
				break;
			case 2:
				gameboard[0][2] = symbol;
				break;
			case 3:
				gameboard[0][4] = symbol;
				break;
			case 4:
				gameboard[2][0] = symbol;
				break;
			case 5:
				gameboard[2][2] = symbol;
				break;
			case 6:
				gameboard[2][4] = symbol;
				break;
			case 7:
				gameboard[4][0] = symbol;
				break;
			case 8:
				gameboard[4][2] = symbol;
				break;
			case 9:
				gameboard[4][4] = symbol;
				break;
		}

	}
	public static String Victory() {
		List upRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List downRow = Arrays.asList(7,8,9);
		List LeftCol= Arrays.asList(1,4,7);
		List MidCol = Arrays.asList(2,5,8);
		List RightCol = Arrays.asList(3,6,9);
		List Lcross = Arrays.asList(1,5,9);
		List Rcross = Arrays.asList(3,5,7);
		
		List<List> winning = new ArrayList<List>();
		winning.add(upRow);
		winning.add(midRow);
		winning.add(downRow);
		winning.add(LeftCol);
		winning.add(MidCol);
		winning.add(RightCol);
		winning.add(Lcross);
		winning.add(Rcross);
		
		for (List l : winning) {

			if (userMove.containsAll(l)) {
				return ("You win!!!!!");
			}else if (robotMove.containsAll(l)) {
				return("You suck!!!!");
			}else if (userMove.size()+robotMove.size() == 9) {
				return("It is a draw, QQ");
			}

		}

		return "";
	}
	

}
