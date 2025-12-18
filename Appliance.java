/**
 * Oceane Daumasson - 40275138
 * COMP249
 * Assignment 0, Part I
 * Due September 19 
 */

public class Appliance {
	private String type;
	private String brand;
	private long serialNum;
	private double price;

	public static long serialCounter = 1000000;
	public static final double MIN_PRICE = 1.0; 
	
	/**
	 *  Default constructor to initialize type and brand to null, price to 1.0, and serial number to increase 
	 *  by 1 each time an object is created.
	 */
	public Appliance() {
		type = "";
		brand = "";
		price = 1.0;
		serialNum = serialCounter++;
	}
	
	/** Parameterized constructor to create object and set appliance type, brand and price from user input.
	 * @param type the type of appliance 
	 * @param brand the brand of the appliance
	 * @param price the price of the appliance
	 */
	public Appliance(String type, String brand, double price) {
		setType(type);
		this.brand = brand;
		setPrice(price);
		this.serialNum = serialCounter++; 
	}
	
	/** Accessor and mutator methods */
	public String getType() {
		return type;
	}
	
	/**
	 * Checks that user inputed type is allowed, else print error message.
	 * 
	 * @param type the type of appliance
	 */
	public void setType(String type) {
		// Array of valid appliance types
		String[] typesAllowed = {"Fridge", "Air Conditioner", "Washer", "Dryer", "Freezer", "Stove",
								"Dishwasher", "Water Heaters", "Microwave"};
		boolean isAllowed = false;

		// Loop through allowed types until user inputed type matches
		for (int i=0; i<typesAllowed.length; i++) 
			if (typesAllowed[i].equalsIgnoreCase(type)) {
				this.type = type;
				isAllowed = true;
			}
		
		// Set type to null if none found
		if (!isAllowed) {
			this.type = "";
		}
	}
	
	public String getBrand() {
		return brand;
	}
	
	public void setBrand(String brand) {
		this.brand = brand;
	}
	
	public long getSerialNum() {
		return serialNum;
	}
	
	public double getPrice() {
		return price;
	}
	
	/**
	 * Sets object price to the minimum if less than 1.
	 * 
	 * @param price inputed from user
	 */
	public void setPrice(double price) {
		if (price>MIN_PRICE)
			this.price = price;
		else
			this.price = MIN_PRICE;
	}
	
	/**
	 * Calculates number of appliances created using serial counter.
	 * 
	 * @return number of appliances created
	 */
	public static int findNumberOfCreatedAppliances() {
		return (int)(serialCounter - 1000000);
	}
	
	/**
	 * Compares one appliance to another by looking at type, brand and price.
	 * 
	 * @param anotherAppliance another appliance to compare to this appliance
	 * @return true if the appliances are equal
	 */
	public boolean equals(Appliance anotherAppliance) {
		if (anotherAppliance == null)
			return false;
		else
			return this.type.equals(anotherAppliance.type) && 
					this.brand.equals(anotherAppliance.brand) &&
					this.price==anotherAppliance.price;
	}
	
	/**
	 * Looks through all appliance objects for ones with specified brand. Prints those appliances' information if found.
	 * 
	 * @param a the array of appliance objects
	 * @param b the brand name to search for
	 */
	public static void findAppliancesBy(Appliance[] a, String b) {
		boolean applianceExists = false;
		for (int i=0; i<a.length; i++)
			if (a[i] != null && a[i].getBrand().equalsIgnoreCase(b)) {
				System.out.print(a[i]);
				applianceExists = true; 
			}

		if (!applianceExists)
			System.out.println("No appliances with the brand name " + b + " were found.");
	}
	
	/**
	 * Looks through all appliance objects for ones with price under the maximum specified. Prints those appliances' 
	 * information if found.
	 * 
	 * @param a the array of appliance objects
	 * @param p the maximum price
	 */
	public static void findCheaperThan(Appliance[] a, double p) { 
		boolean cheaperFound = false;
		for (int i=0; i<a.length; i++)
			if (a[i] != null && a[i].getPrice()<=p) {
				System.out.print(a[i]); 
				cheaperFound = true;
			}
	
		if (!cheaperFound)
			System.out.println("No appliances cheaper than $" + p + " were found.");
	}
	
	/**
	 * Prints object details
	 * 
	 * @return a string displaying the appliance's information
	 */
	public String toString() {
		return "\nAppliance Serial #" + serialNum + 
				"\nBrand: " + brand + 
				"\nType: " + type + 
				"\nPrice: $" + price + "\n";
	}
}
