
// JailCensusDisplayJPS		Author: AMH + Jake Sinclair
// 
// A graphics application that visualizes daily census data from the Allegheny
// County Jail in bar chart format with options for updating the display based
// on subsets of the available data.
//
// Data Source: https://data.wprdc.org/dataset/allegheny-county-jail-daily-census

// javac --module-path %PATH_TO_FX% --add-modules javafx.controls JailCensusDisplayJPS.java
// java --module-path %PATH_TO_FX% --add-modules javafx.controls JailCensusDisplayJPS

// for my MAC file path
// javac --module-path /Users/jacobsinclair/Downloads/javafx-sdk-17.0.7/lib --add-modules javafx.controls JailCensusDisplayJPS.java
// java --module-path /Users/jacobsinclair/Downloads/javafx-sdk-17.0.7/lib --add-modules javafx.controls JailCensusDisplayJPS

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.scene.shape.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.Group;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.util.Scanner;
import java.io.*;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;
import javafx.event.EventHandler;


public class JailCensusDisplayJPS extends Application {

	// filename in current directory to use as data source
 	// private final String filename = "smallData.csv";
	private final String filename = "syntheticData.csv";
	// private final String filename = "mediumData.csv";
	// private final String filename = "Sept2015Data.csv";
	// private final String filename = "Aug2016Data.csv";
	// private final String filename = "Full2017Data.csv";

	
	private final int PLOTSIZE = 400;	// width/height of square plot area
	private final int BORDER = 50;	// border width on all sides of plot area

	private DateItemJPS[] days;	// collection of data items, one per day in datafile
	
	private int maxPerDay;	// most inmates recorded for one day within current datafile
	
	private Rectangle[] bars;	// collection of bars, one per day being displayed
	
	private TextField minAgeField;	// field to display and read minimum age to display
	private TextField maxAgeField;	// field to display and read maximum age to display
	private	int minAge;			// youngest age recorded across data file
	private	int maxAge;			// oldest age recorded across data file
	
	private Label showing;		// label for what subset of data is being displayed
	

	// set up Graphics application with all needed initialization for program
    public void start(Stage primaryStage) {		

		// TODO: process the filename set at the top of this class to determine
		// the number of days covered and maximum number of inmates held on
		// any single day
		int numDays = 0; // stores the number of days that are found in a file
						// is incremented by one when the current date(date) differs from
						// the last date that was scanned in (previousDate)
						
		maxPerDay = 0; // stores the max number of people on a single day
		
		int totalInmates = 1; // keeps track of the number of inmates on the current date
		
		// initializing previousDate and date as different open strings
		// so that in the first iteration of the while loop in try-catch block
		// the condition for incrementing numDays is fulfilled 
		String date = " "; 
		String previousDate;

		try {
			Scanner filescan = new Scanner(new File(filename));
			filescan.useDelimiter(",|\\n");
			
			// read past the header
			filescan.nextLine();

			// ADD CODE TO PROCESS FILE
            while(filescan.hasNext()){

                // previousDate gets date
				previousDate = date;

				// read in the next token as a String, saving it as the date
				// set date to previousDate before first iteration, if the date changes
				// then change the previousDate and add one to the running total
				// of numDays
				date = filescan.next();
				
				if(date.equals(previousDate)){
					// have a check for the total number of inmates for a date.
					// if it is larger then the current maxPerDay then set that value to 
					// be the new maxPerDay
					totalInmates = totalInmates + 1;
					maxPerDay = Math.max(maxPerDay, totalInmates);
				} else {
					// resets the total count of totalInmates
					// starts at one to show that we have moved
					// to the first inmate on a new date
					numDays = numDays + 1;
					totalInmates = 1;
				}
				
                // read in the next token as a String, saving it as the gender
                String gender = filescan.next();
				
                // read in the next token as a String, saving it as the race
                String race = filescan.next();
				
				// read in the next token as a String, saving it as the booking age
                String bookingAge = filescan.next();

                // read in the next token as a String, saving it as the current age
                String currentAge = filescan.next();
				
            }
			
			filescan.close();
		} catch (IOException e) {System.out.println(e);};
		
		System.out.println("Total days covered: " + numDays);
		System.out.println("Max inmates held: " + maxPerDay);

		// instantiating the DateItemJPS array
		days = new DateItemJPS[numDays];

		// daysIndex is intitialized to -1, so that the first time the
		// days array is populated it is at index 0 rather than index 1
		int daysIndex = -1; 
		
		// initializing previousDate and date as different open strings
		// so that in the first iteration of the while loop in try-catch block
		// the condition for incrementing numDays is fulfilled 
		previousDate = " ";
		date = "";
		
		// initializing the minAge to be something that will always be greater than 
		// currentAge, which will set minAge to store currentAge on the first check
		minAge = 1000; 
		
		// maxAge is intitialized to 0 as no ages have been scanned into the array yet
		maxAge = 0; 
		
		try {
			Scanner filescan = new Scanner(new File(filename));
			filescan.useDelimiter(",|\\n");
			
			// read past the header
			filescan.nextLine();

			
			while(filescan.hasNext()){
				
				previousDate = date;
				
				// read in the date
				date = filescan.next();
				
				// if date does not equal current date the index in the DateItemsJPS 
				// array is incremented by 1
				if(!date.equals(previousDate)){ 
					daysIndex = daysIndex + 1;	
					
					// we instantiate a new DateItemJPS object at the next open index of
					// the array when the date has changed 
					// we pass through an argument for the date and the max number of inmates
					// that can be stored for a single date in the array
					days[daysIndex] = new DateItemJPS(date, maxPerDay);
					
				}								
				// read in the next token as a String, saving it as the gender
                String gender = filescan.next();
	
                // read in the next token as a String, saving it as the race
                String race = filescan.next();
				
                // read in the next token as a String, so it can then be parsed as an int
				// then it is set as an int storing the bookingAge
                String bAge = filescan.next();
				int bookingAge = Integer.parseInt(bAge);
				
				// read in the next token as a String, then applying trim
				// to that String, so it can then be parsed as an int
				// finally it is saved as an int storing the currentAge
                String cAge = filescan.next();
				String cAgeTrimmed = (cAge.trim());
				int currentAge = Integer.parseInt(cAgeTrimmed);

				// check to see if the currentAge is less than than minAge
				// if it is then minAge is set to currentAge
				minAge = Math.min(minAge, currentAge); 
													
				// check to see if the currentAge is greater than maxAge
				// if it is then maxAge is set to currentAge
				maxAge = Math.max(maxAge, currentAge); 
			
				// add a new person object at the current index of the DateItemJPS array
				days[daysIndex].addPerson(gender, race, bookingAge, currentAge);
				
			}

			filescan.close();
		} catch (IOException e) {System.out.println(e);};

		Rectangle plot = new Rectangle(BORDER, BORDER, PLOTSIZE, PLOTSIZE);
		plot.setFill(Color.WHITE);
		plot.setStroke(Color.BLACK);

		String firstDate = days[0].getDate();
		Label minDate = new Label(firstDate); 
		minDate.setTranslateX(BORDER-10);
		minDate.setTranslateY(BORDER+PLOTSIZE+10);
		
		// last date that was kept track of stored by the dateString
		Label maxDate = new Label(date); 
		
		maxDate.setTranslateX(BORDER+PLOTSIZE-20);
		maxDate.setTranslateY(BORDER+PLOTSIZE+10);

		// parsing maxPerDay to a string so it can be used in the mostHeld Label
		String mostHeld = Integer.toString(maxPerDay); 
		Label maxPeople = new Label(mostHeld);
		
		// changed BORDER - 30 to BORDER-35 to clean up the scaling of
		// the label. Before this, the label was slightly overlapping with the plot
		maxPeople.setTranslateX(BORDER-35); 
											
		maxPeople.setTranslateY(BORDER-5);
		
		 // Changed the limit of the bars // array to be the number of the days. 
		 // We want one bar for each day in the data set
		bars = new Rectangle[numDays];
		
		Group barGroup = new Group();
		for (int i=0; i<bars.length; i++) {

			// use the number of people for a single day to represent the height of the bar
			// maxPerDay represents the highest number of inmates found for a single day
			double height = (double)PLOTSIZE * (days[i].getNumPeople() / (double)maxPerDay);

			// width of the bar is determined by taking the width of the entire plot, 
			// and dividing it by the number of days, so that each day is the same width
			double width = (double)PLOTSIZE / numDays;

			// the first bar starts by touching the border of the plot, then each sequential 
			// bar in the array is set to touch the right edge of the last bar 
			// created in the plot
			double x = BORDER + (i * width);

			// taking the y coordinate and adding them to represent the full height of the
			// window, then subtracting the height of the bar so we can place the bar
			// inside of the plot and not going outside of the border
			double y = (PLOTSIZE + BORDER) - height;

			// instantiating a new rectangle in the rectangle array, representing a single
			// day in the plot
			bars[i] = new Rectangle(x, y, width, height);

			// adding the bars that have been instantiated to a group of rectangle
			// object so that they can be displayed in the window
			barGroup.getChildren().add(bars[i]);
		}

		MenuButton genderChoice = new MenuButton("Gender");
		genderChoice.setTranslateX(BORDER-20);
		genderChoice.setTranslateY(5);
		String[] genders = {"F", "M"};
		for (int i=0; i<genders.length; i++) {
			MenuItem newItem = new MenuItem(genders[i]);
			genderChoice.getItems().add(newItem);
			newItem.setOnAction(this::genderAction);
		}

		MenuButton raceChoice = new MenuButton("Race");
		raceChoice.setTranslateX(BORDER+60);
		raceChoice.setTranslateY(5);
		String[] races = {"A", "B", "H", "I", "U", "W"};
		for (int i=0; i<races.length; i++) {
			MenuItem newItem = new MenuItem(races[i]);
			raceChoice.getItems().add(newItem);
			newItem.setOnAction(this::raceAction);
		}
		
		Label minAgeLabel = new Label("Min Age:");
		minAgeLabel.setTranslateX(BORDER+140);
		minAgeLabel.setTranslateY(7);		
		minAgeField = new TextField();
		
		// parsing minAge to a string so we can display the 
		// minimum age found in the file
		String minAgeFound = Integer.toString(minAge);
		
		minAgeField.setText(minAgeFound);
		minAgeField.setPrefColumnCount(3);
		minAgeField.setTranslateX(BORDER+190);
		minAgeField.setTranslateY(5);
		minAgeField.setOnAction(this::minAgeAction);

		Label maxAgeLabel = new Label("Max Age:");
		maxAgeLabel.setTranslateX(BORDER+250);
		maxAgeLabel.setTranslateY(7);
		maxAgeField = new TextField();
		
		// parsing maxAge to a string so we can display the 
		// maximum age found in the file
		String maxAgeFound = Integer.toString(maxAge); 
		maxAgeField.setText(maxAgeFound);
		
		maxAgeField.setPrefColumnCount(3);
		maxAgeField.setTranslateX(BORDER+300);
		maxAgeField.setTranslateY(5);
		maxAgeField.setOnAction(this::maxAgeAction);
		
		Button reset = new Button("Reset");
		reset.setTranslateX(BORDER+370);
		reset.setTranslateY(5);
		reset.setOnAction(this::resetAction);
		
		showing = new Label("All data");
		showing.setTranslateX((BORDER+PLOTSIZE)/2);
		showing.setTranslateY(BORDER+PLOTSIZE+20);

 		Group root = new Group(plot, minDate, maxDate, maxPeople, barGroup,
								genderChoice, raceChoice, minAgeLabel,
								minAgeField, maxAgeLabel, maxAgeField, reset,
								showing);
		
        Scene scene = new Scene(root, PLOTSIZE+BORDER*2, PLOTSIZE+BORDER*2,
								Color.WHITE);
		
        primaryStage.setTitle("Daily Jail Census Visualization");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

	private void genderUpdate(String choice) {
		// creating a new height and Y coordinate for the bars in the plot
		// based on the number of inmates that are of the gender chosen by the user

		String minAgeFound = minAgeField.getText();
		int currentMinAge = Integer.parseInt(minAgeFound);

		String maxAgeFound = maxAgeField.getText();
		int currentMaxAge = Integer.parseInt(maxAgeFound);

		for(int i = 0; i < bars.length; i++){
			double newHeight = (double)PLOTSIZE * (days[i].getGenderCount(choice, currentMinAge, currentMaxAge) / (double)maxPerDay);
			double newY = (PLOTSIZE + BORDER) - newHeight;

			bars[i].setHeight(newHeight);
			bars[i].setY(newY);
		}
	}
d
	public void genderAction(ActionEvent event) {
		String choice = ((MenuItem)(event.getSource())).getText();
		genderUpdate(choice);
		showing.setText("Gender: " + choice);
	}

	private void raceUpdate(String choice) {
		// creating a new height and Y coordinate for the bars in the plot
		// based on the number of inmates that are of the race chosen by the user
		
		String minAgeFound = minAgeField.getText();
		int currentMinAge = Integer.parseInt(minAgeFound);

		String maxAgeFound = maxAgeField.getText();
		int currentMaxAge = Integer.parseInt(maxAgeFound);

		for(int i = 0; i < bars.length; i++){
			double newHeight = (double)PLOTSIZE * (days[i].getRaceCount(choice, currentMinAge, currentMaxAge) / (double)maxPerDay);
			double newY = (PLOTSIZE + BORDER) - newHeight;

			bars[i].setHeight(newHeight);
			bars[i].setY(newY);
		}
	}
	
	public void raceAction(ActionEvent event) {
		String choice = ((MenuItem)(event.getSource())).getText();
		raceUpdate(choice);
		showing.setText("Race: " + choice);
	}

	// does the work of determining if we are working with the full set of data,
	// a gender subset, or a race subset
	public void ageUpdate(int currentMinAge, int currentMaxAge){

		// parsing the text in the minAgeField and maxAgeField so we can use them
		// as ints 
		String minAgeFound = minAgeField.getText();
		String maxAgeFound = maxAgeField.getText();
		
		currentMinAge = Integer.parseInt(minAgeFound); 
		currentMaxAge = Integer.parseInt(maxAgeFound);

		// taking the substring of the first character in the label
		String dataset = showing.getText();
		String datasetFirstChar = dataset.substring(0, 1);

		if (datasetFirstChar.equals("A")){ // case that the full dataset is displayed
			for(int i = 0; i < bars.length; i++){
				double newHeight = (double)PLOTSIZE * (days[i].getNumPeopleInRange(currentMinAge, currentMaxAge) / (double)maxPerDay);
				double newY = (PLOTSIZE + BORDER) - newHeight;

				bars[i].setHeight(newHeight);
				bars[i].setY(newY);
			}
		}

		if (datasetFirstChar.equals("G")){ // case that a gender subset is displayed
			for(int i = 0; i < bars.length; i++){
				
				// taking the substring of the dataset string, giving us only 
				// the index containing the gender character
				String genderChoice = dataset.substring(8, 9);
				
				double newHeight = (double)PLOTSIZE * (days[i].getGenderCount(genderChoice, currentMinAge, currentMaxAge) / (double)maxPerDay);
				double newY = (PLOTSIZE + BORDER) - newHeight;

				bars[i].setHeight(newHeight);
				bars[i].setY(newY);
			}
		}

		if (datasetFirstChar.equals("R")){ // case that a race subset is displayed
			for(int i = 0; i < bars.length; i++){
				
				// taking the substring of the dataset string, giving us only 
				// the index containing the race character
				String raceChoice = dataset.substring(6, 7);
				
				double newHeight = (double)PLOTSIZE * (days[i].getRaceCount(raceChoice, currentMinAge, currentMaxAge) / (double)maxPerDay);
				double newY = (PLOTSIZE + BORDER) - newHeight;

				bars[i].setHeight(newHeight);
				bars[i].setY(newY);
			}
		}
		
	}

	public void minAgeAction(ActionEvent event) {
		
		String maxAgeText = maxAgeField.getText();
		int currentMaxAge = Integer.parseInt(maxAgeText);
		
		String minAgeText = minAgeField.getText();
		
		// error checking for if the user enters a parseable input or not
		try {
			int currentMinAge = Integer.parseInt(minAgeText);
			ageUpdate(currentMinAge, currentMaxAge);
			minAgeField.setText(minAgeText);
			ageUpdate(currentMinAge, currentMaxAge);
			
			// if the user enters a minimum age greater than the currentMaxAge
			// then the the text field is reset to to minAge
			// along with the size of the bars scaling to the current age range
			if (currentMinAge > currentMaxAge){
				String minAgeFound = Integer.toString(minAge);
				minAgeField.setText(minAgeFound);
				ageUpdate(minAge, maxAge);
			}
			// if the input is not parseable, a NumberFormatException error
			// is thrown and the text field is reset to minAge
			// along with the size of the bars scaling to the current age range
		} catch (NumberFormatException e) {String minAgeFound = Integer.toString(minAge);
				minAgeField.setText(minAgeFound); ageUpdate(minAge, maxAge);};
	
	}

	public void maxAgeAction(ActionEvent event) {
		
		String minAgeText = minAgeField.getText();
		int currentMinAge = Integer.parseInt(minAgeText);
		
		String maxAgeText = maxAgeField.getText();
		
		// error checking for if the user enters a parseable input or not
		try {
			int currentMaxAge = Integer.parseInt(maxAgeText);
			ageUpdate(currentMinAge, currentMaxAge);
			maxAgeField.setText(maxAgeText);
			ageUpdate(currentMinAge, currentMaxAge);
			
			// if the user enters a maximum age lesser than the currentMaxAge
			// then the the text field is reset to to maxAge
			// along with the size of the bars scaling to the current age range
			if (currentMaxAge < currentMinAge){
				String maxAgeFound = Integer.toString(maxAge);
				maxAgeField.setText(maxAgeFound);
				ageUpdate(minAge, maxAge);
				
			}
			// if the input is not parseable, a NumberFormatException error
			// is thrown and the text fields is reset to to maxAge
			// along with the size of the bars scaling to the current age range
		} catch (NumberFormatException e) {String maxAgeFound = Integer.toString(maxAge);
				maxAgeField.setText(maxAgeFound); ageUpdate(minAge, maxAge);};

	}

	// can be used when the visualization is restricting the data
	// only by the age range
	/*public void allUpdate(){
		// resetting the height and y axis of the bars to reflect the entire data set
		// as well as changing the label back to all data, from the subset that was selected
		for(int i = 0; i< bars.length; i++){
			double resetHeight = (double)PLOTSIZE * (days[i].getNumPeopleInRange(minAge, maxAge) / (double)maxPerDay);
			double resetY = (PLOTSIZE + BORDER) - resetHeight;
			
			bars[i].setHeight(resetHeight);
			bars[i].setY(resetY);
		}
	}*/
		
	public void resetAction(ActionEvent event) {

		// parsing the minAge and maxAge to Strings so we can display them in 
		// their respective text fields
		String minAgeFound = Integer.toString(minAge); 
		String maxAgeFound = Integer.toString(maxAge);


		// resetting the minAgeField and maxAgeField to display the original
		// minAge and maxAge that were found when scanning the file
		minAgeField.setText(minAgeFound); 
		maxAgeField.setText(maxAgeFound);
		
		// resetting the plot to show the initial visualization of the dataset
		ageUpdate(minAge, maxAge);

		showing.setText("All data"); // setting the label to reflect the data set showing
										// all the data
		
	}

    public static void main(String[] args)
    {
        launch(args);
    }
}
