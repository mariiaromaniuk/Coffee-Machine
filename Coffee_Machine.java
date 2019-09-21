
package weekfour;

import java.util.Scanner;

//Coffee machine/cash register that dispenses caffeinated drinks and collects money
//owner can also take money out of the register
public class CoffeeMachine1 {

	// static class scope variables so all the methods can use it
	static Scanner scan = new Scanner(System.in); // Scanner class to get user input
	// initial variable instanializing

	static int water = 400, milk = 540, coffeeBeans = 120, numOfCups = 9, money = 550;
	static String choice = ""; // holds user input

	public static void main(String[] args) {
		// Do while loop to keep running unless user enters invalid option or exit
		do {
			// Initial options for the user
			System.out.println("Enter one of the following (buy, fill, take, remanining, exit):");
			choice = scan.next(); // records user input

			if (choice.equals("buy")) // if user chooses buy
				buy(); // invoke buy method

			else if (choice.equals("fill")) // if user chooses fill
				fill(); // invoke fill method

			else if (choice.equals("take")) // if user chooses take
				take(); // invoke take method

			else if (choice.equals("remaining")) { // if user chooses remaining
				System.out.println(); // prints empty line for readability
				remaining(); // invoke remaining method
				System.out.println(); // prints empty line for readability
			}

			else if (!choice.equals("exit")) // if user choosing anything else except exit
				System.out.println("\nThat is not a valid option.\n"); // let user know invalid choice

		} while (!choice.equals("exit")); // exit the loop if user enters exit

		scan.close(); // close scanner
	}

	// buy menu when user wants to buy a drink
	public static void buy() {
		while (true) { //loop to continue if user chooses incorrect option or back
			System.out.println(); //prints line for aesthetics
			//options for user
			System.out.println("What do you want to buy");
			System.out.println("1 - espresso");
			System.out.println("2 - latte");
			System.out.println("3 - cappuccino");
			System.out.println("back - go back to main menu");

			choice = scan.next(); //takes user input
			if (choice.equals("1")) { //if user chooses 1
				espresso(numOfCupsOrdered()); //invoke espresso method
				break; //break to go back to the main menu
			}

			else if (choice.equals("2")) { //if user chooses 2
				latte(numOfCupsOrdered()); //invoke latte method
				break; //break to go back to the main menu
			}

			else if (choice.equals("3")) { //if user chooses 3
				cappuccino(numOfCupsOrdered()); //invoke cappuccino method
				break; //break to go back to the main menu
			}

			else if (choice.equals("back")) { //exit when back is entered
				System.out.println();
				break;
			}
			
			else //if it's not the other choices or back
				System.out.println("Not a valid option."); //it's an invalid choice
			
			

		}
	}

	public static void fill() {
		System.out.println(); // prints empty line for aesthetics
		System.out.print("Enter how many ml of water do you want to add: ");
		checkIfInteger(); // checks if input is an integer
		water += scan.nextInt(); // increments water if amount entered is an integer
		System.out.print("Enter how many ml of milk do you want to add: ");
		checkIfInteger(); // checks if input is an integer
		milk += scan.nextInt(); // increments milk if amount entered is an integer
		System.out.print("Enter how many g of coffee beans do you want to add: ");
		checkIfInteger(); // checks if input is an integer
		coffeeBeans += scan.nextInt(); // increments coffee beans if amount entered is an integer
		System.out.print("Enter how many disposable cups do you want to add: ");
		checkIfInteger(); // checks if input is an integer
		numOfCups += scan.nextInt(); // increments number of cups if amount entered is an integer
		System.out.println(); // prints empty line for aesthetics
		remaining(); // print amount of resources left
		System.out.println(); // prints empty line for aesthetics
	}

	// takes money out of the register
	public static void take() {
		System.out.println("\nGiving you $" + money + " from the register.");
		money -= money; // takes all the money out of the register
		System.out.println("You have $" + money + " left in the register.\n");
	}

	// prints all of the resources that's left in the machine
	public static void remaining() {
		System.out.println("The following resources are available:");
		System.out.println(water + " ml of water.");
		System.out.println(milk + " ml of milk.");
		System.out.println(coffeeBeans + " g of coffee beans.");
		System.out.println(numOfCups + " cups.");
		System.out.println("$" + money + " in the register.");
		// System.out.println(); //prints empty line for aesthetics
	}

	// asks user how many cups they want to order
	public static int numOfCupsOrdered() {
		System.out.println(); // prints line for aesthitics
		System.out.println("How many cups would you like to order?: ");
		checkIfInteger(); // checks if input is an integer
		return scan.nextInt(); // returns the number entered if it's an integer
	}

	// checks if user input is an integer
	public static void checkIfInteger() {
		// loop that repeats unless an integer is entered
		while (!scan.hasNextInt()) {
			System.out.println("That's not a valid input. Please enter an integer (whole number).");
			scan.next(); // clears the buffer
		}
	}

	// if espresso is bought, it involks checkIfEnoughResources to see if it can be
	// made
	public static void espresso(int num) {
		checkIfEnoughResources("espresso", 250, 16, num, 4);
	}

	// if latte is bought, it involks checkIfEnoughResources to see if it can be
	// made
	public static void latte(int num) {
		checkIfEnoughResources("latte", 350, 75, 20, num, 7);
	}

	// if cappuuccino is bought, it involks checkIfEnoughResources to see if it can
	// be made
	public static void cappuccino(int num) {
		checkIfEnoughResources("cappuccino", 200, 100, 12, num, 6);
	}

	// checkIfEnoughResources to see if drink can be made
	public static void checkIfEnoughResources(String drink, int wtr, int cofBean, int num, int cst) {
		System.out.println(); // prints empty line for aesthetics
		if (wtr * num > water) // checks to see if there's enough water
			System.out.println("Not enough water. Need " + (wtr * num - water) + " ml.");
		if (cofBean * num > coffeeBeans) // checks to see if there's enough coffee beans
			System.out.println("Not enough coffee beans. Need " + (cofBean * num - coffeeBeans) + " ml.");
		if (num > numOfCups) // checks to see if there's enough cups
			System.out.println("Not enough cups. Need " + (num - numOfCups) + " cups.");

		// if there's enough resources
		if (wtr * num <= water && cofBean * num <= coffeeBeans && num <= numOfCups) {
			System.out.println("Making the " + drink + ".\n"); // let user know drink is being made
			updateResourcesAndMoney(wtr, cofBean, num, cst); // update amount of resources and money
			remaining(); // prints the remaining resources
		}
		System.out.println(); // prints empty line for aesthetics
	}

	public static void checkIfEnoughResources(String drink, int wtr, int mlk, int cofBean, int num, int cst) {
		System.out.println(); // prints empty line for aesthetics
		if (wtr * num > water) // checks to see if there's enough water
			System.out.println("Not enough water. Need " + (wtr * num - water) + " ml.");
		if (mlk * num > milk) // checks to see if there's enough milk
			System.out.println("Not enough milk. Need " + (mlk * num - milk) + " ml.");
		if (cofBean * num > coffeeBeans) // checks to see if there's enough coffee beans
			System.out.println("Not enough coffee beans. Need " + (cofBean * num - coffeeBeans) + " g.");
		if (num > numOfCups) // checks to see if there's enough cups
			System.out.println("Not enough cups. Need " + (num - numOfCups) + " cups.");

		// if there's enough resources
		if (wtr * num <= water && mlk * num <= milk && cofBean * num <= coffeeBeans && num <= numOfCups) {
			System.out.println("Making the " + drink + ".\n"); // let user know drink is being made
			updateResourcesAndMoney(wtr, mlk, cofBean, num, cst); // update amount of resources and money
			remaining(); // prints the remaining resources
		}
		System.out.println(); // prints empty line for aesthetics
	}

	// update resources and money
	public static void updateResourcesAndMoney(int wtr, int cofBean, int num, int cst) {
		water -= wtr * num; // decrement water based on amount of water used times number of cups
		coffeeBeans -= cofBean * num; // decrement coffee beans based on amount of coffee beans used times number of
										// cups
		numOfCups -= num; // decrement number of cups used
		money += cst * num; // increment money based on cost times number of cups
	}

	// update resources and money
	public static void updateResourcesAndMoney(int wtr, int mlk, int cofBean, int num, int cst) {
		water -= wtr * num; // decrement water based on amount of water used times number of cups
		milk -= mlk * num; // decrement milk based on amount of milk used times number of cups
		coffeeBeans -= cofBean * num; // decrement coffee beans based on amount of coffee beans used times number of
										// cups
		numOfCups -= num; // decrement number of cups used
		money += cst * num; // increment money based on cost times number of cups
	}
}
