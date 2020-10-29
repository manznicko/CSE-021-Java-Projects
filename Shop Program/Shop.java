import java.util.Scanner;

public class Shop {

	public static String numSuffix(int i) {
		int rem = i % 10;
		switch (rem) {
		case 0:
		case 4:
		case 5:
		case 6:
		case 7:
		case 8:
		case 9:
			return (i + "th");
		case 1:
			if (i % 100 != 11)
				return (i + "st");
			else
				return (i + "th");
		case 2:
			if (i % 100 != 12)
				return (i + "nd");
			else
				return (i + "th");
		case 3:
			if (i % 100 != 13)
				return (i + "rd");
			else
				return (i + "th");
		default:
			break;
		}
		return "";
	}

	public static void setup(Scanner input, String[] names, double[] prices, int[] discpackages, double[] percentdiscount) {
		
		for (int i = 0; i < names.length; i++) {
			System.out.println("Please enter the name of the " + numSuffix((i + 1)) + " item as one word.");
			names[i] = input.next();
			System.out.println("Please enter the price of the " + numSuffix((i + 1)) + " item per each package.");
			prices[i] = input.nextDouble();
			while (prices[i] < 0) {
				System.out.println("Invalid input. Please enter a number greater than zero");
				prices[i] = input.nextDouble();
				
			}
			System.out.println("Enter the number of x packages for the " + numSuffix((i + 1)) + " to qualify for a speacial discount: Buy x get 1 free. Enter 0 for no offer");
			discpackages[i] = input.nextInt();
			while (discpackages[i] < 0) {
				System.out.println("Invalid input. Please enter a number greater than zero");
				discpackages[i] = input.nextInt();
			}
		}

		System.out.println("Enter the dollar amount to qualify for Additional Discount (or 0 if none offered): ");
		percentdiscount[0] = input.nextDouble();
		while(percentdiscount[0] < 0) {
			System.out.println("Invalid Input. Please enter a number greater than zero");
		}
		
		if (percentdiscount[0] != 0) {
			System.out.println("Enter the Additional Discount rate (e.g., 0.1 for 10%): ");
			percentdiscount[1] = input.nextDouble();
		
			while (percentdiscount[1] < 0 || percentdiscount[1] > 0.5) {
				System.out.println("Invalid input. Enter a value > 0 and <= 0.5:");
				percentdiscount[1] = input.nextDouble();

			}
		}

	}

	public static void buy(Scanner input, int[] amounts, String[] names, double[] prices) {

		if (names.length == 0)
			System.out.println("Shop is not setup yet!");
		else {
			for (int i = 0; i < names.length; i++) {
				System.out.println("Enter the amount of packages you wish to purchase for " + names[i]);
				amounts[i] = input.nextInt();
				while (amounts[i] < 0) {
					System.out.println("Invalid input. Amount of packages must be greater than or equal to zero.");
					amounts[i] = input.nextInt();
				}
			}
		}

	}

	public static void list(String[] names, double[] prices, int[] amounts) {

		if (names.length == 0)
			System.out.println("Shop is not setup");
		else if (amounts.length == 0)
			System.out.println("You have not bought anything!");
		else {
			int num = 0;
			for (int i = 0; i < names.length; i++) {
				if (amounts[i] > 0) {
					num++;
					System.out.println(amounts[i] + " lb of " + names[i] + " @ $" + prices[i] * amounts[i]);
				}
			}
			if (num == 0)
				System.out.println("No item were purchased");
		}

	}

	public static int checkout(Scanner input, int[] amounts, String[] names, double[] prices, int[] discpackages,
			double[] percentdiscount, int x) {
		if (names.length == 0)
			System.out.println("Shop is not setup yet!");
		else if(amounts.length == 0)
			System.out.println("You have not bought anything!");
		else {
			double sub = 0;
			double disc = 0;
			for (int i = 0; i < prices.length; i++)
				sub += prices[i] * amounts[i];
			System.out.print("Original Sub Total: \t\t\t$");
			System.out.println(sub);
			System.out.print("Specials Discounts:\t\t\t-$");

			for (int i = 0; i < amounts.length; i++) {

				if (discpackages[i] != 0) {

					for (int j = 1; j <= (amounts[1]); j++) {

						if (j % discpackages[i] == 0)

							disc += prices[i];
					}
				}

			}
			sub -= disc;
			System.out.println(disc);
			System.out.println("New Sub Total: \t\t\t\t$" + sub);
			if (percentdiscount[0] == 0)
				System.out.println("Additional 0% Discount: \t\t-$0.0");
			else {
				if (sub > percentdiscount[0]) {
					System.out.println("Additional " + percentdiscount[1] * 100 + "% Discont: \t\t-$" + sub * percentdiscount[1]);
					sub -= sub * percentdiscount[1];
				}

			}
			System.out.println("Final Sub Total: \t\t\t$" + sub);
			System.out.print("Would you like to rerun the program? (1 for yes 0 for no)");
			x = input.nextInt();
			return x;
		}
		return x;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner input = new Scanner(System.in);

		String[] names = {};
		double[] prices = {};
		int[] amounts = {};
		int[] discpackages = {};
		double[] percentdiscount = new double[2];
		int x = 1, choose = 0, numIt = 0;

		while (x == 1) {
			System.out.println("This Program Supports 4 functions \n 1.Set up shop \n 2. Buy \n 3. List Items \n 4. Checkout");
			System.out.println("Please choose the function you want:");
			choose = input.nextInt();

			if (choose == 1) {
				System.out.println("Greetings user enter amount of items to sell.");
				numIt = input.nextInt();
				names = new String[numIt];
				prices = new double[numIt];
				amounts = new int[numIt];
				discpackages = new int[numIt];
				setup(input, names, prices, discpackages, percentdiscount);
			}

			else if (choose == 2)
				buy(input, amounts, names, prices);
			else if (choose == 3)
				list(names, prices, amounts);
			else if (choose == 4)
				x = checkout(input, amounts, names, prices, discpackages, percentdiscount, x);

		}

	}

}
