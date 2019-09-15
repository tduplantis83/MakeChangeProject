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
				change = Double.parseDouble(formatter.format(makeChange(amtTendered, itemPrice)).substring(1,
						formatter.format(makeChange(amtTendered, itemPrice)).length()));
				
				System.out.println("*****************************************");
				System.out.println("Item Price: " + formatter.format(itemPrice));
				System.out.println("Amount Tendered: " + formatter.format(amtTendered));
				System.out.println("Your change is: " + formatter.format(change));
				buildChangeString(change);
			}
			else {
				System.out.println("Take your money back and get out of my store. NO SOUP FOR YOU!");
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
				changeString += "\t" + hundreds + " $100 bills\n";
			} else if (hundreds == 1) {
				changeString += "\t" + hundreds + " $100 bill\n";
			}
		}
		// fifties
		if (cents / 5000 >= 0) {
			fifties = cents / 5000;
			cents -= fifties * 5000;
			if (fifties > 1) {
				changeString += "\t" + fifties + " $50 bills\n";
			} else if (fifties == 1) {
				changeString += "\t" + fifties + " $50 bill\n";
			}
		}
		// twenties
		if (cents / 2000 >= 0) {
			twenties = cents / 2000;
			cents -= twenties * 2000;
			if (twenties > 1) {
				changeString += "\t" + twenties + " $20 bills\n";
			} else if (twenties == 1) {
				changeString += "\t" + twenties + " $20 bill\n";
			}
		}
		// tens
		if (cents / 1000 >= 0) {
			tens = cents / 1000;
			cents -= tens * 1000;
			if (tens > 1) {
				changeString += "\t" + tens + " $10 bills\n";
			} else if (tens == 1) {
				changeString += "\t" + tens + " $10 bill\n";
			}
		}
		// fives
		if (cents / 500 >= 0) {
			fives = cents / 500;
			cents -= fives * 500;
			if (fives > 1) {
				changeString += "\t" + fives + " $5 bills\n";
			} else if (fives == 1) {
				changeString += "\t" + fives + " $5 bill\n";
			}
		}
		// ones
		if (cents / 100 >= 0) {
			ones = cents / 100;
			cents -= ones * 100;
			if (ones > 1) {
				changeString += "\t" + ones + " $1 bills\n";
			} else if (ones == 1) {
				changeString += "\t" + ones + " $1 bill\n";
			}
		}

		// find coins
		// 50cents
		if (cents / 50 >= 0) {
			fiftyCents = cents / 50;
			cents -= fiftyCents * 50;
			if (fiftyCents > 1) {
				changeString += "\t" + fiftyCents + " Fifty Cent Pieces\n";
			} else if (fiftyCents == 1) {
				changeString += "\t" + fiftyCents + " Fifty Cent Piece\n";

			}
		}
		// quarters
		if (cents / 25 >= 0) {
			quarters = cents / 25;
			cents -= quarters * 25;
			if (quarters > 1) {
				changeString += "\t" + quarters + " Quarters\n";
			} else if (quarters == 1) {
				changeString += "\t" + quarters + " Quarter\n";
			}
		}
		// dimes
		if (cents / 10 >= 0) {
			dimes = cents / 10;
			cents -= dimes * 10;
			if (dimes > 1) {
				changeString += "\t" + dimes + " Dimes\n";
			} else if (dimes == 1) {
				changeString += "\t" + dimes + " Dime\n";
			}
		}
		// nickels
		if (cents / 5 >= 0) {
			nickels = cents / 5;
			cents -= nickels * 5;
			if (nickels > 1) {
				changeString += "\t" + nickels + " Nickels\n";
			} else if (nickels == 1) {
				changeString += "\t" + nickels + " Nickel\n";
			}
		}
		// pennies
		if (cents / 1 >= 0) {
			pennies = cents / 1;
			cents -= pennies * 1;
			if (pennies > 1) {
				changeString += "\t" + pennies + " Pennies";
			} else if (pennies == 1) {
				changeString += "\t" + pennies + " Penny";
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

