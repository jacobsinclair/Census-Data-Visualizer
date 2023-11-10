// Person.java					Author: AMH
// An object class describing an individual inmate on a specific date based on
// their age on that date as well as their gender, race, and age at the time
// of booking as coded in the Allegheny County Jail census system.

public class Person {
	private String gender;		// gender coded as F/M
	private String race;		// race coded as A/B/H/I/U/W
	private int bookingAge;		// age at the time of booking
	private int currentAge;		// current age on date of census

	// Constructor: intialize Person object based on provided values
	public Person(String g, String r, int b, int c) {
		gender = g;
		race = r;
		bookingAge = b;
		currentAge = c;
	}
	
	// return the stored gender code as a String
	public String getGender() {return gender;}
	
	// return the stored race code as a String
	public String getRace() {return race;}
	
	// return the current age on the census date as an integer
	public int getCurrentAge() {return currentAge;}
	
	// summarize entire state of object as a String
	public String toString() {
		return gender + " " + race + " " + bookingAge + " " + currentAge;
	}
}