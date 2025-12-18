/**
 * Oceane Daumasson - 40275138
 * COMP249
 * Assignment 0, Part II
 * Due September 19
 * 
 * This program serves to help store owners acquire and manage new appliances. The user is asked to input the maximum amount of appliances their store can 
 * hold before being shown a menu of options related to managing the appliances. After entering the correct password, the user is able to 
 * create new appliances and update them by changing the type, brand and price. They can also search for appliances by specific brands or price ranges.
 * Once the user chooses to quit, they are shown a closing message and the program ends.
 */

import java.util.Scanner;

public class ApplianceDriver {
	/**
	 * Runs the whole program. Displays menu and prompts the user for how to manage appliances.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Scanner keyIn = new Scanner(System.in);
		
		// Set password as a constant
		final String PASSWORD = "c249"; 
		
		// Print welcome message and prompt user for max appliances their store can contain
		System.out.print("Welcome to Oceane's Program! Please enter the maximum number of appliances your store can contain: ");
		int maxAppliances = keyIn.nextInt();
		
		// Create an array for appliance objects with size of maxAppliances
		Appliance[] inventory = new Appliance[maxAppliances]; 
		
		int option;
		int attemptCounter = 0;
		
		// Loop main menu until user decides to quit or option is not valid (1-5)
		do
		{
			// Terminate program if user incorrectly attempts the password 12x in case 1
			if (attemptCounter==12) {
				System.out.print("\nProgram detected suspicious activities and will terminate immediately!");
				System.exit(0);
			}
			
			// Display main menu and prompt user for an option
			System.out.print("\nWhat do you want to do?"
					+ "\n   1.  Enter new appliances (password required)"
					+ "\n   2.  Change information of an appliance (password required)"
					+ "\n   3.  Display all appliances by a specific brand"
					+ "\n   4.  Display all appliances under a certain price."
					+ "\n   5.  Quit"
					+ "\nPlease enter your choice > ");
			option = keyIn.nextInt();
			
			// Initialize correctPassword to false at start of each loop
			boolean correctPassword = false;
			String attemptedPassword;
			
			switch (option) {
				case 1:
					// Prompt user for the password 3 times before going back to main menu
					for (int i=1; i<=3; i++) {
						System.out.print("\nPlease enter your password: ");
						attemptedPassword = keyIn.next();
						attemptCounter ++; // Add to counter until 12 is reached
						
						if (attemptedPassword.equals(PASSWORD)) {
							correctPassword = true;
							attemptCounter = 0; // Reset counter to 0 if correct password is entered
							break;	
						}
					}
			
					// Go back to main menu if password is false, else go on with case 1
					if (!correctPassword)
						break;
					else {
						// Prompt user for number of appliances
						System.out.print("How many appliances would you like to enter? ");
						int appliancesToAdd = keyIn.nextInt();
						int availableSpace = 0;
						
						// Loop through appliance array and count available spaces for new objects
						for (int j=0; j<inventory.length; j++)
							if (inventory[j]==null)
								availableSpace++;
						
						// If there is enough room for the new objects, add them to array
						if (appliancesToAdd<=availableSpace) {
							int added = 0;
							for (int j=0; j<inventory.length && added<appliancesToAdd; j++) 
								if (inventory[j]==null) {
									inventory[j] = new Appliance();
									added++;
								}
							// Print success message for adding appliances
							System.out.println("Succesfully added " + appliancesToAdd + " appliance(s).");
						}
						
						else 
							// If there isn't enough space in the array, print error message
							System.out.println("There is only enough space in the store for " + availableSpace + " more appliance(s).");
						
						break;	
					}
					
				case 2:
					// Prompt user for the password 3 times
					for (int i=1; i<=3; i++) {
						System.out.print("\nPlease enter your password: ");
						attemptedPassword = keyIn.next();
						
						if (attemptedPassword.equals(PASSWORD)) {
							correctPassword = true;
							break;	
						}
					}
					
					// Go back to main menu if password is wrong all 3 times
					if (!correctPassword)
						break;
					
					// Set specific appliance to update to null at start
					Appliance applianceToUpdate = null;
					boolean backToMenu = false;
					
					// Loop serial number prompt until user inputs a valid number
					while (applianceToUpdate==null) {
						System.out.print("Please enter the serial number of the appliance you wish to update: ");
						long serialNumEntered =keyIn.nextLong();
					
						// Loop through appliances to find appliance object with the correct serial number
						for (int j=0; j<inventory.length; j++) {
							if (inventory[j] != null && inventory[j].getSerialNum()==serialNumEntered) {
								applianceToUpdate = inventory[j];
								break;
							}
						}
							
						// If no appliance is found, prompt user to enter another or go back to main menu
						if (applianceToUpdate == null) {
							System.out.print("\nNo appliance with that serial number was found. "
									+ "\n   1.  Enter another serial number"
									+ "\n   2.  Go back to the main menu"
									+ "\nEnter your choice > ");
							int choice = keyIn.nextInt();
								
							// Break loop once user enters 2
							if (choice==2) {
								backToMenu = true;
								break;
							}
						}
					}
				
					// Go back to menu
					if (backToMenu)
						break;
				
					
					// If appliance exists, let the user update it
					if (applianceToUpdate!=null) {
						System.out.print(applianceToUpdate); // Print appliance details
						
						// Initialize user choice to 0 before prompting for what to update (choice 1-5)
						int choice = 0;		
						do {
							System.out.print("\nWhat information would you like to change?\n"
									+ "   1.  brand\n"
									+ "   2.  type\n"
									+ "   3.  price\n"
									+ "   4.  quit\n"
									+ "Enter your choice > ");
							
							// Set user input as choice if integer
							if (keyIn.hasNextInt())
								choice = keyIn.nextInt();
							// Else, store junk answer and print error message
							else {
								System.out.print("\nPlease enter your choice as an integer.");
								keyIn.next();
							}
							
							switch (choice) {
								case 1:
									// Prompt for new brand and use setBrand method to update it
									System.out.print("What is the new brand? ");
									String brand = keyIn.next();										
									applianceToUpdate.setBrand(brand);
									// Print appliance details with new info
									System.out.print(applianceToUpdate);
									break;
										
								case 2:
									// Prompt for new type and use setType method to update it
									do { 
										System.out.print("What is the new type? ");
										String type = keyIn.next();
										applianceToUpdate.setType(type);
										
										// Print error message if type is not valid
										if (applianceToUpdate.getType().equals(""))
											System.out.print("Please enter a valid type. ");
										
									} while (applianceToUpdate.getType().equals("")); // Loop until valid type is entered
										
									// Print appliance details with new info
									System.out.print(applianceToUpdate);
									break;
										
								case 3:
									// Prompt for new price and use setPrice method to update it
									System.out.print("What is the new price? ");
									double price = keyIn.nextDouble();
									applianceToUpdate.setPrice(price);
									// Print appliance details with new info
									System.out.print(applianceToUpdate);
									break;
							}
								
						} while (choice != 4); // End loop once user enters option 4
						break;
					}
	
						
				case 3:
					// Prompt user for a brand
					System.out.print("Please enter the brand name: ");
					String brandName = keyIn.next();
					// Call method to find all appliances with that brand name
					Appliance.findAppliancesBy(inventory, brandName);
					break;
					
				case 4:
					// Prompt user for a maximum price
					System.out.print("Please enter the maximum price: ");
					double maxPrice = keyIn.nextDouble();
					// Call method to find all appliances under that price
					Appliance.findCheaperThan(inventory, maxPrice);
					break;
					
				case 5:
					// Print closing message
					System.out.print("\nThank you for using this program, goodbye!");
					break;
				}
				
		} while (option!=5); // End loop after user inputs option as 5
				
		// Close scanner
		keyIn.close();
	}
}
