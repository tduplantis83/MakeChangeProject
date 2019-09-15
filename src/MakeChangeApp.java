import java.text.NumberFormat;
import java.util.Scanner;

public class MakeChangeApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double itemPrice, amtTendered, change;
		// formats the change into a money String
		NumberFormat formatter = NumberFormat.getCurrencyInstance();

			registerWelcome();
			itemPrice = getItemPrice(input);
			amtTendered = getAmtTendered(input);
			amtTendered = checkAmtTendered(amtTendered, itemPrice, formatter, input);
			
			if(amtTendered > 0) {
				makeChange(amtTendered, itemPrice, formatter);
			}
			else {
				System.out.println("Take your money back and get out of my store. NO SOUP FOR YOU!");
			}

		input.close();
	}

	private static void buildChangeString(int hundreds, int fifties, int twenties, int tens, int fives, int ones, int fiftyCents, int quarters, int dimes, int nickels, int pennies, String c, String price, String amtPaid) {
		// take in change (double)
		// determine the dollars & coins needed
		// print out the results for user as a string
		// Bill types:
		
		// build String to list out change
		String changeString = "";
		
		//build string of denominations for payout
		//hundreds
		if (hundreds > 1) {
			changeString += "\t" + hundreds + " $100 bills\n";
		} else if (hundreds == 1) {
			changeString += "\t" + hundreds + " $100 bill\n";
		}

		if (fifties > 1) {
			changeString += "\t" + fifties + " $50 bills\n";
		} else if (fifties == 1) {
			changeString += "\t" + fifties + " $50 bill\n";
		}
		
		// twenties
		if (twenties > 1) {
			changeString += "\t" + twenties + " $20 bills\n";
		} else if (twenties == 1) {
			changeString += "\t" + twenties + " $20 bill\n";
		}
		
		// tens
		if (tens > 1) {
			changeString += "\t" + tens + " $10 bills\n";
		} else if (tens == 1) {
			changeString += "\t" + tens + " $10 bill\n";
		}

		// fives
		if (fives > 1) {
			changeString += "\t" + fives + " $5 bills\n";
		} else if (fives == 1) {
			changeString += "\t" + fives + " $5 bill\n";
		}
		
		// ones
		if (ones > 1) {
			changeString += "\t" + ones + " $1 bills\n";
		} else if (ones == 1) {
			changeString += "\t" + ones + " $1 bill\n";
		}
		

		// find coins
		// 50cents
		if (fiftyCents > 1) {
			changeString += "\t" + fiftyCents + " Fifty Cent Pieces\n";
		} else if (fiftyCents == 1) {
			changeString += "\t" + fiftyCents + " Fifty Cent Piece\n";

		}
		
		// quarters
		if (quarters > 1) {
			changeString += "\t" + quarters + " Quarters\n";
		} else if (quarters == 1) {
			changeString += "\t" + quarters + " Quarter\n";
		}
		
		// dimes
		if (dimes > 1) {
			changeString += "\t" + dimes + " Dimes\n";
		} else if (dimes == 1) {
			changeString += "\t" + dimes + " Dime\n";
		}
		
		// nickels
		if (nickels > 1) {
			changeString += "\t" + nickels + " Nickels\n";
		} else if (nickels == 1) {
			changeString += "\t" + nickels + " Nickel\n";
		}
		
		// pennies
		if (pennies > 1) {
			changeString += "\t" + pennies + " Pennies";
		} else if (pennies == 1) {
			changeString += "\t" + pennies + " Penny";
		}

		System.out.println("*******************************");
		System.out.println("Item Price: \t\t" + price);
		System.out.println("Amount Tendered: \t" + amtPaid);
		System.out.println("Your change is: \t" + c);
		System.out.println(changeString);
		System.out.println("*******************************");
		System.out.println("Thank you! Come Again!");

	}

	private static int findLargestDenominations(int cents, int denom) {
		int countOfDenom = 0;
		
		// find denominations
		if (cents / denom >= 0) {
			countOfDenom = cents / denom;
		}
		
		return countOfDenom;
	}
	
	private static void makeChange(double amt, double price, NumberFormat nf) {
		// take in the amount paid (double)
		// take in the item price (double)
		// determine the change due (double)
		// convert change due to cents
		// call findLargestDenominations method to find all denominations 
		// call buildChangeString method to build a string of the denominations needed for payout
		
		double change = amt - price;
		
		// $100, $50, $20, $10, $5, $1
		int hundreds = 0, fifties = 0, twenties = 0, tens = 0, fives = 0, ones = 0;

		// Coin types:
		// 50 cent, 25 cent, 10 cent, 5 cent, 1 cent
		int fiftyCents = 0, quarters = 0, dimes = 0, nickels = 0, pennies = 0;
		
		// convert change to currency format to account for double rounding errors
		// then convert change to a double with rounding error fixed
		// then convert change into cents
		change = Double.parseDouble(nf.format(change).substring(1, (nf.format(change).length())));
		int cents = (int) (change * 100);

		//hundreds
		hundreds = findLargestDenominations(cents, 10000);
		//update cents
		cents -= hundreds * 10000;
				
		// fifties
		fifties = findLargestDenominations(cents, 5000);
		//update cents
		cents -= fifties * 5000;
		
		// twenties
		twenties = findLargestDenominations(cents, 2000);
		//update cents
		cents -= twenties * 2000;
		
		// tens
		tens = findLargestDenominations(cents, 1000);
		//update cents
		cents -= tens * 1000;
		
		// fives
		fives = findLargestDenominations(cents, 500);
		//update cents
		cents -= fives * 500;
		
		// ones
		ones = findLargestDenominations(cents, 100);
		//update cents
		cents -= ones * 100;
		
		// .50
		fiftyCents = findLargestDenominations(cents, 50);
		//update cents
		cents -= fiftyCents * 50;
		
		// .25
		quarters = findLargestDenominations(cents, 25);
		//update cents
		cents -= quarters * 25;
		
		// .10
		dimes = findLargestDenominations(cents, 10);
		//update cents
		cents -= dimes * 10;
		
		// .05
		nickels = findLargestDenominations(cents, 5);
		//update cents
		cents -= nickels * 5;
		
		// .01
		pennies = findLargestDenominations(cents, 1);
		//update cents
		cents -= pennies * 1;
		
		buildChangeString(hundreds, fifties, twenties, tens, fives, ones, fiftyCents, quarters, dimes, nickels, pennies, nf.format(change), nf.format(price), nf.format(amt));
		}

	private static double checkAmtTendered(double amt, double price, NumberFormat nf, Scanner i) {

		double newAmt = 0;
			
		if (amt < price) {
			System.out.println("\nThe amount tendered is too low! You still owe " + (nf.format(price - amt)));
			
			System.out.print("Do you want to pay the rest now? (Y/N): ");
			if (i.next().equalsIgnoreCase("y")) {

				do {
					 newAmt = getRemainingDue(Double.parseDouble(nf.format(price - amt).substring(1,(nf.format(price - amt)).length())), price, i, nf); 
				} while(newAmt < price);
				
				return newAmt;
			}
			else {
				return 0.0;
			}
		} 
		else {
			return amt;
		}
		
	}

	private static double getRemainingDue(double amtRemaining, double price, Scanner i, NumberFormat nf) {
		
		while(true) {
			System.out.print("Please enter " + nf.format(amtRemaining) + " to pay what you still owe: ");
			double nextAmt = i.nextDouble();
			
			if(nextAmt == amtRemaining) {
				return price;
			}
			else if (nextAmt > amtRemaining) {
				System.out.println("\nOK. You gave me a little more than what you still owed.\nI can work with that...");
				return Double.parseDouble(nf.format((nextAmt - amtRemaining) + price).substring(1,(nf.format((nextAmt - amtRemaining) + price).length())));
			}
			else {
				System.out.println("\nI'm sorry, but you still haven't paid enough....\nWhy are you being so difficult?!?!??");
				amtRemaining -= nextAmt;
				amtRemaining = Double.parseDouble(nf.format(amtRemaining).substring(1,(nf.format(amtRemaining)).length()));
				System.out.println("Now you still owe me $" + amtRemaining);
				continue;
			}
		}
	}

	private static double getAmtTendered(Scanner input) {
		System.out.print("Please enter the amount tendered: ");
		return input.nextDouble();
	}

	private static double getItemPrice(Scanner input) {
		System.out.print("Please enter the item price: ");
		return input.nextDouble();
	}

	private static void registerWelcome() {
		System.out.println("******Welcome to the Cash Register!******");
	}

}

