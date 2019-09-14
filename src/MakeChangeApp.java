import java.text.NumberFormat;
import java.util.Scanner;

public class MakeChangeApp {

	public static void main(String[] args) {
		Scanner input = new Scanner(System.in);
		double itemPrice, amtTendered, change;
		boolean loop = true;
		
		while(loop) {
			
			registerWelcome();
			itemPrice = getItemPrice(input);
			amtTendered = getAmtTendered(input);
			loop = checkAmtTendered(amtTendered, itemPrice);
			
			if(loop) {
				continue;
			}
			
			change = makeChange(amtTendered, itemPrice);
			
			//formats the change into a money String
			NumberFormat formatter = NumberFormat.getCurrencyInstance();
			System.out.println("Your change is: " + formatter.format(change));
			
			buildChangeString(change);
		}
			
			input.close();
	}

	private static void buildChangeString(double change) {
		//take in change (double)
		//determine the dollars & coins needed
		//print out the results for user as a string
		
		
	}

	private static double makeChange(double amt, double price) {
		//take in the amount paid (double)
		//take in the item price (double)
		//return the change due (double)
		
		
		return amt - price;
	}

	private static boolean checkAmtTendered(double amt, double price) {
		
		if(amt < price) {
			System.err.println("ERROR: The amount tendered is too low! Try again.");
			return true;
		}
		else {
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
