// DateItemJPS.java		Author: Jake Sinclair
//
// Object class that 
//

public class DateItemJPS{
	
	private String date; // stores the date
	private int numInmates; // stores the number of inmates for a date
	private Person[] person; // declaring the person array
	// figure out a way to read in the code and set a limit for the array
	// Person[] array that stores an entry for each each inmate
	

	public DateItemJPS(String currentDate, int maxInmates)
	{
		date = currentDate;
		numInmates = 0; // how many people are actually in array
        person = new Person[maxInmates]; 
	}
	
	public void addPerson(String gender, String race, int bookingAge, int currentAge)
	{
        person[numInmates] = new Person(gender, race, bookingAge, currentAge);
        numInmates = numInmates + 1;
    }

    public String getDate()
    {
        return date; // returns the current date stored by the DateItemJPS object
    }

    public int getNumPeople()
	{
		return numInmates; // returns the number of inmates for the current date being stored
	}


	public int getNumPeopleInRange(int min, int max)
	{
		int numPeopleInRange = 0;

		for(int i = 0; i < numInmates; i++){
			int age = person[i].getCurrentAge();

			if (age >= min && age <= max){
				numPeopleInRange = numPeopleInRange + 1;
			}

		}

		return numPeopleInRange; // returns the number of inmates that fall within 
								// the user's specified age range
		
	}

	public int getGenderCount(String choice, int min, int max)
	{
		int genderCount = 0;

		for(int i = 0; i < numInmates; i++){
			String currentGender = person[i].getGender();
			int age = person[i].getCurrentAge();
			
			if ((currentGender.equals(choice)) && (age >= min && age <= max)){
				genderCount = genderCount + 1;
			}

		}

		return genderCount; // returns the number of inmates that are of the user's choice
								// of gender AND in their specified age range
			
	}

	public int getRaceCount(String choice, int min, int max)
	{
		int raceCount = 0;

		for(int i = 0; i < numInmates; i++){
			String currentRace = person[i].getRace();
			int age = person[i].getCurrentAge();
			
			if ((currentRace.equals(choice)) && (age >= min && age <= max)){
				raceCount = raceCount + 1;
			}
		}

		return raceCount; // returns the number of inmates that are of the race specified by the use 
						// AND fall within the users specified age range
	 
	}

	/*public String toString()
	{
		return date + "	" + numInmates;
	}*/

}