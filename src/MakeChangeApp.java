import java.text.NumberFormat;
import java.util.Scanner;

public class MakeChangeApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double itemPrice, amtTendered, change;
		boolean loop = true;

		while (loop) {

			registerWelcome();
			itemPrice = getItemPrice(input);
			amtTendered = getAmtTendered(input);
			loop = checkAmtTendered(amtTendered, itemPrice);

			if (loop) {
				continue;
			}

			// formats the change into a money String
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			change = Double.parseDouble(formatter.format(makeChange(amtTendered, itemPrice)).substring(1,
					formatter.format(makeChange(amtTendered, itemPrice)).length()));
			
			System.out.println("*\tItem Price: $" + itemPrice + "\t\t*");
			System.out.println("*\tAmount Tendered: $" + amtTendered + "\t*");
			System.out.println("------------------------------------");
			System.out.println("*\tYour change is: $" + change + "\t\t*");
			buildChangeString(change);
		}

		input.close();
	}

	private static void buildChangeString(double change) {
		// take in change (double)
		// determine the dollars & coins needed
		// print out the results for user as a string
		// Bill types:
		// $100, $50, $20, $10, $5, $1
		int hundreds = 0, fifties = 0, twenties = 0, tens = 0, fives = 0, ones = 0;

		// Coin types:
		// 50 cent, 25 cent, 10 cent, 5 cent, 1 cent
		int fiftyCents = 0, quarters = 0, dimes = 0, nickels = 0, pennies = 0;

		// build String to list out change
		String changeString = "";

		// convert change to cents
		int cents = (int) (change * 100);

		// find bills
		// hundreds
		if (cents / 10000 >= 0) {
			hundreds = cents / 10000;
			cents -= hundreds * 10000;
			if (hundreds > 1) {
				changeString += "" + hundreds + " $100 bills, ";
			} else if (hundreds == 1) {
				changeString += "" + hundreds + " $100 bill, ";
			}
		}
		// fifties
		if (cents / 5000 >= 0) {
			fifties = cents / 5000;
			cents -= fifties * 5000;
			if (fifties > 1) {
				changeString += "" + fifties + " $50 bills, ";
			} else if (fifties == 1) {
				changeString += "" + fifties + " $50 bill, ";
			}
		}
		// twenties
		if (cents / 2000 >= 0) {
			twenties = cents / 2000;
			cents -= twenties * 2000;
			if (twenties > 1) {
				changeString += "" + twenties + " $20 bills, ";
			} else if (twenties == 1) {
				changeString += "" + twenties + " $20 bill, ";
			}
		}
		// tens
		if (cents / 1000 >= 0) {
			tens = cents / 1000;
			cents -= tens * 1000;
			if (tens > 1) {
				changeString += "" + tens + " $10 bills, ";
			} else if (tens == 1) {
				changeString += "" + tens + " $10 bill, ";
			}
		}
		// fives
		if (cents / 500 >= 0) {
			fives = cents / 500;
			cents -= fives * 500;
			if (fives > 1) {
				changeString += "" + fives + " $5 bills, ";
			} else if (fives == 1) {
				changeString += "" + fives + " $5 bill, ";
			}
		}
		// ones
		if (cents / 100 >= 0) {
			ones = cents / 100;
			cents -= ones * 100;
			if (ones > 1) {
				changeString += "" + ones + " $1 bills, ";
			} else if (ones == 1) {
				changeString += "" + ones + " $1 bill, ";
			}
		}

		// find coins
		// 50cents
		if (cents / 50 >= 0) {
			fiftyCents = cents / 50;
			cents -= fiftyCents * 50;
			if (fiftyCents > 1) {
				changeString += "" + fiftyCents + " Fifty Cent Pieces, ";
			} else if (fiftyCents == 1) {
				changeString += "" + fiftyCents + " Fifty Cent Piece, ";

			}
		}
		// quarters
		if (cents / 25 >= 0) {
			quarters = cents / 25;
			cents -= quarters * 25;
			if (quarters > 1) {
				changeString += "" + quarters + " Quarters, ";
			} else if (quarters == 1) {
				changeString += "" + quarters + " Quarter, ";
			}
		}
		// dimes
		if (cents / 10 >= 0) {
			dimes = cents / 10;
			cents -= dimes * 10;
			if (dimes > 1) {
				changeString += "" + dimes + " Dimes, ";
			} else if (dimes == 1) {
				changeString += "" + dimes + " Dime, ";
			}
		}
		// nickels
		if (cents / 5 >= 0) {
			nickels = cents / 5;
			cents -= nickels * 5;
			if (nickels > 1) {
				changeString += "" + nickels + " Nickels, ";
			} else if (nickels == 1) {
				changeString += "" + nickels + " Nickel, ";
			}
		}
		// pennies
		if (cents / 1 >= 0) {
			pennies = cents / 1;
			cents -= pennies * 1;
			if (pennies > 1) {
				changeString += "" + pennies + " Pennies ";
			} else if (pennies == 1) {
				changeString += "" + pennies + " Penny ";
			}
		}

		System.out.println(changeString);
		System.out.println("Thank you! Come Again!");

	}

	private static double makeChange(double amt, double price) {
		// take in the amount paid (double)
		// take in the item price (double)
		// return the change due (double)

		return amt - price;
	}

	private static boolean checkAmtTendered(double amt, double price) {

		if (amt < price) {
			System.out.println("!!!!ERROR: The amount tendered is too low! Try again!!!!");
			return true;
		} else {
			return false;
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
		System.out.println("*****Welcome to the Cash Register*****");
	}

}
