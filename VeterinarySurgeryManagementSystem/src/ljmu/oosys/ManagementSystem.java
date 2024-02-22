package ljmu.oosys;
import java.util.UUID;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Random;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * The ManagementSystem class contains all menus and is used for the majority of user input and output.
 */

public class ManagementSystem {
	
	
	//Path variable should not be needed anymore as SER file is in project folder.
    //private static final String PATH = ""; /*Change this to where the file is located on your computer*/
	private static Scanner console = new Scanner(System.in);
	private static List <Booking> bookings = new ArrayList<Booking>();
	private static List <Surgery> surgeries = new ArrayList<Surgery>();
	private static List <Staff> staff = new ArrayList<Staff>();
    private static List <Pet> pets = new ArrayList<Pet>();

/**
 * The main method starts the program by initialising the system and reading all surgeries created from a file and saving any changes back to it on exit. 
 * The menus hierarchy is displayed, allowing the user to navigate through different operations.   
 * 
 * @param args main method parameters.
 * @throws Exception Throws an exception if there is an error.
 */
    
	public static void main(String[] args) throws Exception {
		System.out.println("----- Veterinary Surgery Management System -----");
		 deSerialize();
	
//------------------------------------------------------------------------------------------------------------------------------------------------
				//These lines were used to create surgery objects that are displayed in select surgery.
				//If first menu does not show them create these objects and serialize.
		
	   	/*LocalTime Start_time = LocalTime.of(9, 0);
	  		LocalTime End_time = LocalTime.of(21, 0);
				DayOfWeek unavailableday =DayOfWeek.MONDAY;
				LocalTime unavailable_starTime=LocalTime.of(12,0);
				LocalTime unavailable_endTime=LocalTime.of(17,0); 
				

				surgeries.add(new Surgery("Liverpool", "LLL123", Start_time, End_time,unavailableday,unavailable_starTime,unavailable_endTime,pets,staff,bookings));
				surgeries.add(new Surgery("Manchester", "LLL555", Start_time, End_time,unavailableday,unavailable_starTime,unavailable_endTime,pets,staff,bookings));
				surgeries.add(new Surgery("Chester", "L1111", Start_time, End_time,unavailableday,unavailable_starTime,unavailable_endTime,pets,staff,bookings));
				surgeries.add(new Surgery("London", "LLL455", Start_time, End_time,unavailableday,unavailable_starTime,unavailable_endTime,pets,staff,bookings));
						*/	
			
				
				 
//-------------------------------------------------------------------------------------------------------------------------------------------------
		
	  selectSurgery();
		 
	
		 
	  serialize();
		System.out.println("SYSTEM CLOSED");
		console.close();
	}
	
/**
 * Prompts user to select one of the saved surgeries to manage it.
 * Is the first menu so allows exiting of the program.
 * 
 * @throws Exception Throws exception if there's an error.
 */
	public static void selectSurgery() throws Exception { 
        
		String selection="";

		do{
			System.out.println("Please select a surgery or quit the application:");
			int index =1 ;
			for (Surgery s:surgeries){
			System.out.println(index+" : "+s.getName());
			index++;
			}
			System.out.println(index+" : Quit");
			
			selection=console.next().toUpperCase();
			Surgery chosenSurgery = null;
		     switch (selection) {
			   case "1":
			   case "LIVERPOOL":
				   for(Surgery s:surgeries) {
					   if(s.getName().equals("Liverpool")) {
						   chosenSurgery = s;
					   }
				   }
			     mainMenu(chosenSurgery);
				 break;
			   case "2": 
			   case "MANCHESTER":
				   for(Surgery s:surgeries) {
					   if(s.getName().equals("Manchester")) {
						   chosenSurgery = s;
					   }
				   }
				 mainMenu(chosenSurgery);
				   break;
			   case "3": 
			   case "CHESTER":
				   for(Surgery s:surgeries) {
					   if(s.getName().equals("Chester")) {
						   chosenSurgery = s;
					   }
				   }
				 mainMenu(chosenSurgery);
				   break;
			   case "4": 
			   case "LONDON":
				   for(Surgery s:surgeries) {
					   if(s.getName().equals("London")) {
						   chosenSurgery = s;
					   }
				   }
				 mainMenu(chosenSurgery);
				   break;
			   case "5":
			   case "QUIT": {
				 break;
			   }
			   
			}
		}
		while (!selection.equals("1") && !selection.equals("LIVERPOOL") &&
        !selection.equals("2") && !selection.equals("MANCHESTER") &&
        !selection.equals("3") && !selection.equals("CHESTER") &&
        !selection.equals("4") && !selection.equals("LONDON") &&
        !selection.equals("5") && !selection.equals("QUIT"));
	}
/**
 * Lets the user choose which aspect of the surgery they chose they would like to manage.
 * 
 * @param chosenSurgery Passes in the surgery the user chose so they can specifically manage it.
 * @throws Exception In case an error occurs.
 */
	public static void  mainMenu(Surgery chosenSurgery) throws Exception { 
		String selection="";
		do{
			System.out.println("-----" + chosenSurgery.getName() + " Surgery System -----");
			System.out.println("Please select an option:");
			System.out.println("1: Manage Surgery");
			System.out.println("2: Manage Staff");
			System.out.println("3: Manage Pets");
			System.out.println("4: Go Back");
		
			//Only using console.next() instead of console.nextLine() as there was an issue where the menu prints twice for some reason when using it.
			//Same goes with the rest of the menus.
			selection=console.next().toUpperCase();
			switch (selection) {

				case "1":
				case "MANAGESURGERY":
					surgeryMenu(chosenSurgery);
					break;
				case "2": 
				case "MANAGESTAFF":
					staffMenu(chosenSurgery);
					break;
				case "3": 
				case "MANAGEPETS":
					petMenu(chosenSurgery);
					break;
				case "4": 
				case "GOBACK":
					selectSurgery();
					break;
		   
			}
	}
	while (!selection.equals("1") && !selection.equals("MANAGESURGERY") &&
    !selection.equals("2") && !selection.equals("MANAGESTAFF") &&
    !selection.equals("3") && !selection.equals("MANAGEPETS") &&
    !selection.equals("4") && !selection.equals("GOBACK"));
	}

	/**
	 * Displays all options specific to the surgery.
	 * 
	 * @param chosenSurgery Passes in the surgery the user chose so they can specifically manage it.
	 * @throws Exception exception if error occurs.
	 */
	
	public static void surgeryMenu(Surgery chosenSurgery) throws Exception { 

		String selection="";
	do {
		
		System.out.println("----- Surgery Menu -----");
		System.out.println("Please select an option:");
		System.out.println("1: Register Staff");
		System.out.println("2: Remove Staff");
		System.out.println("3: Search Staff");
		System.out.println("4: Register Pet");
		System.out.println("5: Remove Pet");
		System.out.println("6: Search Pets");
		System.out.println("7: Make Booking");
		System.out.println("8: Cancel Booking");
		System.out.println("9: Search Bookings");
		System.out.println("K: show all the staffs");
		System.out.println("O: show all the Pets");
		System.out.println("10: Go Back");
		
		selection=console.next().toUpperCase();
		switch (selection) {

			   case "1":
			   case "REGISTERSTAFF" :  
				   registerStaff(chosenSurgery);
				 break;
			   
			   case "2" : 
			   case "REMOVESTAFF" : 
				   removeStaff(chosenSurgery);
				 break;

			   case "3" : 
			   case "SEARCHSTAFF" : 
				   searchStaff(chosenSurgery);
				   break;
			   
			   case "4" : 
			   case "REGISTERPET" : 
				   registerPet(chosenSurgery);
				   break;
			   
			   case "5" : 
			   case "REMOVEPET" : 
				   removePet(chosenSurgery);
				   break;
			   
			   case "6" : 
			   case "SEARCHPETS" : 
				  searchPet(chosenSurgery);
				  break;
			   
			   case "7" : 
			   case "ADDBOOKING":
				   	makeBooking(chosenSurgery);
					break;
				
			   case "8" : 
			   case "CANCELBOOKING" : 
				   removeBooking(chosenSurgery);
				   break;
			   
			   case "9" : 
			   case "SEARCHBOOKINGS" : 
				   searchBooking(chosenSurgery);
				   break;

			   case "K":
			   chosenSurgery.f();
			   break;

			   case "O":
			   chosenSurgery.o();
			   break;

			   case "10" : 
			   case "GOBACK" : 
				mainMenu(chosenSurgery);
				   break;
			}
		
		} while (!selection.equals("1") && !selection.equals("REGISTERSTAFF") &&
        !selection.equals("2") && !selection.equals("REMOVESTAFF") &&
        !selection.equals("3") && !selection.equals("SEARCHSTAFF") &&
        !selection.equals("4") && !selection.equals("REGISTERPET") &&
        !selection.equals("5") && !selection.equals("REMOVEPET") &&
        !selection.equals("6") && !selection.equals("SEARCHPETS") &&
        !selection.equals("7") && !selection.equals("MAKEBOOKING") &&
        !selection.equals("8") && !selection.equals("CANCELBOOKING") &&
        !selection.equals("9") && !selection.equals("SEARCHBOOKINGS") &&
		!selection.equals("k") && !selection.equals("O") &&
        !selection.equals("10") && !selection.equals("GOBACK"));
	}
	
	/**
	 * Allows user to manage the staff of the chosen surgery 
	 * 
	 * @param chosenSurgery Passes in the surgery the user chose so they can specifically manage it.
	 * @throws Exception if error occurs.
	 */
	
	public static void staffMenu(Surgery chosenSurgery) throws Exception {
		
		String selection="";
		do{
			System.out.println("----- Staff Menu -----");
			System.out.println("Please select an option:");
			System.out.println("1: Add Booking");
			System.out.println("2: Remove Booking");
			System.out.println("3: Next Booking");
			System.out.println("4: All Bookings");
			System.out.println("5: Add consulting surgery to surgeon");
			System.out.println("6: Go Back");
		
			selection=console.next().toUpperCase();
			switch (selection) {

				case "1":
				case "ADDBOOKING":
					makeBooking(chosenSurgery);

				case "2": 
				case "REMOVEBOOKING":
					removeBooking(chosenSurgery);
					break;
				case "3": 
				case "NEXTBOOKING":
					nextStaffBooking(chosenSurgery);
					break;
				case "4": 
				case "ALLBOOKINGS":
					allStaffBookings(chosenSurgery);
					break;
				case "5": 
				case "ADDCONSULTING":
					addconsultingSurgery(chosenSurgery);
					break;
				case "6": 
				case "GOBACK":
					mainMenu(chosenSurgery);
					break;
		   
			}
	}
	while (!selection.equals("1") && !selection.equals("ADDBOOKING") &&
    !selection.equals("2") && !selection.equals("REMOVEBOOKING") &&
    !selection.equals("3") && !selection.equals("NEXTBOOKING") &&
    !selection.equals("4") && !selection.equals("ALLBOOKINGS") &&
    /*!selection.equals("5") && !selection.equals("AVAILABILITYCHECK") && */
    !selection.equals("6") && !selection.equals("GOBACK"));
		
	}
	
	/**
	 * Shows operations that are available for pets of a surgery.
	 * 
	 * @param chosenSurgery Passes in the surgery the user chose so they can specifically manage it.
	 * @throws Exception if error occurs.
	 */
	
	public static void petMenu(Surgery chosenSurgery) throws Exception {
		
		String selection="";
		do{
			System.out.println("----- Pet Menu -----");
			System.out.println("Please select an option:");
			System.out.println("1: Add Booking");
			System.out.println("2: Remove Booking");
			System.out.println("3: Next Booking");
			System.out.println("4: All Bookings");
			System.out.println("5: Go Back");
		
			selection=console.next().toUpperCase();
			switch (selection) {

				case "1":
				case "ADDBOOKING":
					makeBooking(chosenSurgery);
					break;

				case "2": 
				case "REMOVEBOOKING":
					removeBooking(chosenSurgery);
					break;

				case "3": 
				case "NEXTBOOKING":
					nextPetBooking(chosenSurgery);
					break;
				case "4": 
				case "ALLBOOKINGS":
					allpetBookings(chosenSurgery);
					break;
				case "5": 
				case "GOBACK":
					mainMenu(chosenSurgery);
					break;
		   
			}
	}
	while (!selection.equals("1") && !selection.equals("ADDBOOKING") &&
    !selection.equals("2") && !selection.equals("REMOVEBOOKING") &&
    !selection.equals("3") && !selection.equals("NEXTBOOKING") &&
    !selection.equals("4") && !selection.equals("ALLBOOKINGS") &&
    !selection.equals("5") && !selection.equals("GOBACK"));	
		
	}
	
	/**
	 * Deserializes the data from the ser file so surgeries can be accessed and previously registered members or creating bookings can be accessed.
	 */
	
   private static void deSerialize() {
		
		ObjectInputStream ois;
		try {
			ois = new ObjectInputStream(new FileInputStream("s.ser"));
			surgeries = (List<Surgery>)ois.readObject();
			//staff = (List<Staff>)ois.readObject();
			//bookings = (List<Booking>)ois.readObject();
			//pets =(List<Pet>)ois.readObject();
			ois.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
/**
 * Serializes the data back to the file before the program closes so data is not lost and can be used next time.
 */
	private static void serialize() {
		
		ObjectOutputStream oos;
		try {
			oos = new ObjectOutputStream(new FileOutputStream("s.ser"));
			oos.writeObject(surgeries);
			oos.writeObject(staff);
			//oos.writeObject(bookings);
			//oos.writeObject(pets);
			oos.close();
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

/**
 * Used specifically by surgery menu to register staff. 
 * The user is prompted to enter a name and a unique 3 digit code is allocated.
 * They are then asked if they want to register a nurse or surgeon.
 * If surgeon, they can add consulting surgeries.
 * 
 * @param chosenSurgery Allocates the staff member to that specific surgery.
 */
	private static void registerStaff(Surgery chosenSurgery) {
	    String name = "";
	    // To ensure reference given is unique and is 3 digits.
	    List<Integer> existingStaffRefs = new ArrayList<>();
	    List<Staff> existingStaff = new ArrayList<>();

	    existingStaff.addAll(chosenSurgery.getallStaff());

	    int refNum;
	    for (Staff s : existingStaff) {
	        refNum = s.getRef();
	        existingStaffRefs.add(refNum);
	    }

	    Random random = new Random();

	    int ref;
	    do {
	        ref = random.nextInt(999) + 100;
	    } while (existingStaffRefs.contains(ref));

	    System.out.println("----- Register Staff -----");
	    //Only able to assign one word to name.
	    //Tried using console.nextLine() but wouldn't allow user input.
	    //Tried using concatenation but searchStaff would not recognise the name when entered.
	    System.out.println("Please enter name:");
	    name = console.next();
	    List<Booking> bookings = new ArrayList<Booking>();
	    String staffType = "";

	    do {
	        System.out.println("Are you registering a nurse or surgeon?");
	        staffType = console.next().toUpperCase();
	        switch (staffType) {
	            case "N":
	            case "NURSE":
	                Nurse nurse = new Nurse(ref, name, chosenSurgery, bookings);
	                chosenSurgery.registerStaff(nurse);
	                System.out.println("The nurse has been registered to the surgery.");
	                System.out.println("The reference number is :" + nurse.getRef());
	                break;

	            case "S":
	            case "SURGEON":
	                String consultingOption = "";
	                List<Surgery> consultingSurgeries = new ArrayList<Surgery>();
	                boolean addConsulting = true;
	                do {
	                    System.out.println("Would you like to add another consulting surgery?");
	                    consultingOption = console.next().toUpperCase();
	                    switch (consultingOption) {
	                        case "Y":
	                        case "YES":
	                            boolean surgeryFound = false;
	                            String selection = "";
	                            System.out.println("Select a surgery from the list below:");
	                            
	                            for (Surgery s : surgeries) {
	                                System.out.println(s.getName());
	                                
	                            }
	                            selection = console.next().toLowerCase();
	                            selection = selection.substring(0, 1).toUpperCase()+ selection.substring(1);
	                            for (Surgery s : surgeries) {
	                                if (s.getName().equals(selection)) {
	                                    consultingSurgeries.add(s);
	                                    surgeryFound = true;
	                                } 
	                            }
	                            if (!surgeryFound) {
                                    System.out.println("We could not find the surgery you entered");
                                }
	                            
	                            break;

	                        case "N":
	                        case "NO":
	                            addConsulting = false;
	                            break;
	                    }

	                } while (addConsulting);

	                Surgeon surgeon = new Surgeon(ref, name, chosenSurgery, bookings, consultingSurgeries);
	                chosenSurgery.registerStaff(surgeon);
	                System.out.println("The surgeon has been registered to the surgery.");
	                System.out.println("The reference number is :" + surgeon.getRef());
	                break;
	        }
	    } while (!staffType.equals("N") && !staffType.equals("NURSE") &&
	            !staffType.equals("S") && !staffType.equals("SURGEON"));
	}
	
	
	
	
	/**
	 * Used by the surgery to register a pet.
	 * Unique 5 digit ref is allocated, user enters date and name.
	 * Unique type can be specified e.g. if the species is fish the type is salt water.
	 * 
	 * @param chosenSurgery Allocates the pet member to that specific surgery.
	 */
	 private static void registerPet(Surgery chosenSurgery) {
		final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
	        String name = "";
	     // To ensure reference given is unique and is 5 digits.
	        List<Integer> existingPetRefs = new ArrayList<>();
	        List<Pet> existingPets = new ArrayList<>();

	        existingPets.addAll(chosenSurgery.getallPets());

	        int refNum;
	        for (Pet pet : existingPets) {
	            refNum = pet.getRef();
	            existingPetRefs.add(refNum);
	        }

	        Random random = new Random();

	        int ref;
	        do {
	            ref = random.nextInt(99999) + 10000;
	        } while (existingPetRefs.contains(ref));

	        System.out.println("----- Register Pet -----");
	        System.out.println("Please enter name:");
	        //Only works with one word like in register staff
	        name = console.next();

	        List<Booking> bookings = new ArrayList<Booking>();
	        String species = "";
	        System.out.println("What is the species of the pet?");
	        species = console.next();

	        String userDate = "";
	        System.out.println("Enter registration date (dd-MM-yyyy):");
	        userDate = console.next();
	        
	        String uniqueType = "";
	        System.out.println("What specific type of " + species + " is your pet?");
	        uniqueType=console.next();
	        
	    

	        try {
	            LocalDate regDate = LocalDate.parse(userDate, dateFormatter);
	      
	            Pet pet = new Pet(ref, name, species, regDate , uniqueType ,chosenSurgery , bookings);
	            chosenSurgery.registerPet(pet);
	            System.out.println("The pet has been registered to the surgery.");
                System.out.println("The reference number is :" + pet.getRef());
	            
	            } catch (DateTimeParseException e) {
	            System.out.println("Invalid date format.");
	        }
	
	}
	
	
	 private static void removeStaff(Surgery chosenSurgery) {
		 
		 System.out.println("What is the name of the staff?");
			String staff_name=console.next();
			System.out.println("What is the reference number of the staff?");
			int staff_ref=console.nextInt();
			 
		String refString = Integer.toString(staff_ref);
			
			if (refString.length()==3){
			
			    chosenSurgery.removeStaff(staff_name, staff_ref);
				
			  }
			  else 
			  {
				System.out.println("Staff reference numbers must be 3 digits.");
			  }
		 
	 }
	
	
	 private static void searchStaff(Surgery chosenSurgery) {
		 
		 System.out.println("What is the name of the staff? : ");
			String Staff_name= console.next();
			chosenSurgery.searchStaff(Staff_name);
		 
	 }
	
	
	 private static void removePet(Surgery chosenSurgery) {
		 
		 System.out.println("What is the name of the Pet? : ");
		   String Pet_name=console.next();
		   System.out.println("What is the reference number of the Pet ?:");
		   int pet_ref=console.nextInt();
			
	     String refPetString = Integer.toString(pet_ref);
		   
		   if (refPetString.length()==5){
		   
			   chosenSurgery.removePet(Pet_name, pet_ref);
			   
			  // break;
			 }
			 else 
			 {
			   System.out.println("Please try again!:");
			 }
		 
	 }
	
	
	 private static void searchPet(Surgery chosenSurgery) {
		 
		 System.out.println("What is the name of the pet? : ");
		   String pet_name= console.next();
		   chosenSurgery.searchPet(pet_name);
		 
	 }
	 
	 
	/**
	 * Used by either the surgery, staff or pet to add a booking
	 * Uses isAvailable method to ensure booking is made during opening hours and is not during the half day training slot.
	 * 
	 * @param chosenSurgery Makes the booking for the specific surgery.
	 */
	 private static void makeBooking(Surgery chosenSurgery) {
		    UUID ref = UUID.randomUUID();
		    String enteredStartDate = "";
		    Staff selectedStaff = null;
		    Pet pet = null; 
		    
		    System.out.println("Enter the start date time of the booking (dd-MM-yyyy HH:mm):");
		    console.nextLine();
		    enteredStartDate = console.nextLine();
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		    LocalDateTime bookingstartDateTime = LocalDateTime.parse(enteredStartDate, formatter);
		    //Bookings are 30 minutes long.
		    LocalDateTime endDate = bookingstartDateTime.plusMinutes(30);

		    System.out.println("Enter the pet reference number:");
		    int pet_ref = console.nextInt();
		    String refPetString = String.valueOf(pet_ref);

		    for (Pet p : chosenSurgery.getallPets()) {
		        if (p.getRef() == pet_ref) {
		            pet = p;
		            break;
		        }
		    }

		    if (pet != null) {
		    	boolean moreStaff = true;
		    	String staffAnswer = "";
		    	
		    do {	
		        System.out.println("Enter the staff reference number: ");
		        int staff_ref = console.nextInt();
		        boolean staffFound = false;

		        for (Staff s : chosenSurgery.getallStaff()) {
		            if (s.getRef() == staff_ref) {
		                if (s.isAvailable(bookingstartDateTime, endDate)
		                        && refPetString.length() == 5) {
		                    staffFound = true;
		                    selectedStaff = s;
		                    break; // Exit the loop since we found a suitable staff
		                } else {
		                    System.out.println("Make sure the date lies within a certain range and the pet reference is valid.");
		                }
		            }
		        }

		        if (staffFound) {
		            System.out.println("The Booking reference is: " + ref);
		            List<Staff> staffList = new ArrayList<>();
		            staffList.add(selectedStaff);
		            Booking booking = new Booking(ref, bookingstartDateTime, endDate, pet, chosenSurgery, staffList);
		            chosenSurgery.addBooking(booking);
		            System.out.println("Would you like to add another staff member?");
		            staffAnswer = console.next().toUpperCase();
		            switch(staffAnswer) {
		            case "N":
		            case "NO":
		            	moreStaff = false;
		            	break;
		        }
		        } else {
		            System.out.println("Staff with reference number " + staff_ref + " not found or not available.");
		        }
		    } while (moreStaff);
		        
		        
		        
		    } else {
		        System.out.println("No specified Pet with reference number " + pet_ref + " has been found.");
		        
		    }
		}

	
	
	 
	 /**
	  * Finds a pets next booking by asking for its reference and converting the next booking object to a string.
	  * 
	  * @param chosenSurgery Booking information is stored in the surgery as an arrayList.
	  */
	 private static void nextPetBooking(Surgery chosenSurgery) {
		 
		 String refString;
		 String staffRefString;
		 System.out.println("Enter the pet reference number:");
		 refString = console.next();
		 int ref = Integer.parseInt(refString);
         System.out.println("Enter the Staff reference number:");
		 staffRefString=console.next();
		 int staffRef=Integer.parseInt(staffRefString);

		 String result = chosenSurgery.nextPetBooking(ref,staffRef);
		 System.out.println(result);
	 }
	 
	 
	 private static void nextStaffBooking(Surgery chosenSurgery) {
		 
		 String refString;
		 System.out.println("Enter the staff reference number:");
		 refString = console.next();
		 int ref = Integer.parseInt(refString);
         String result = chosenSurgery.nextStaffBooking(ref);
		 System.out.println(result);

	 }


	 private static void allpetBookings(Surgery chosenSurgery) {
		 String refString;
		 String staffref;
		 System.out.println("Enter the pet reference number:");
		 refString = console.next();
		 int ref = Integer.parseInt(refString);
		 System.out.println("Enter the Staff reference number:");
		 staffref = console.next();
		 int staffintref = Integer.parseInt(staffref);
		 chosenSurgery.showAllPetBooking(ref,staffintref);
		 String result = chosenSurgery.showAllPetBooking(ref,staffintref);
		 System.out.println(result);
	 
}
	 
	 
	 private static void allStaffBookings(Surgery chosenSurgery) {
		 
		 String refString;
		 System.out.println("Enter the staff reference number:");
		 refString = console.next();
		 int ref = Integer.parseInt(refString);
		 chosenSurgery.showAllStaffBooking(ref);
		 String result = chosenSurgery.showAllStaffBooking(ref);
	     System.out.println(result);
}
	 
	private static void searchBooking(Surgery chosenSurgery){
		String enteredDate = "";
		String petName = "";
		System.out.println("Enter the start date time of the booking (dd-MM-yyyy HH:mm):");
		console.nextLine();
		enteredDate = console.nextLine();
		 DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

	        try {
	            LocalDateTime bookingDateTime = LocalDateTime.parse(enteredDate, formatter);
	            System.out.println("Enter the pet's name");
	    		petName = console.next();
	    		chosenSurgery.searchBooking(bookingDateTime, petName, chosenSurgery);
	    		
	        } catch (Exception e) {
	            System.out.println("Invalid date-time format.");
	        }
		 
	 }

	
	private static void removeBooking(Surgery chosenSurgery) {

		System.out.println("What is the reference number of the Pet?");
		int Pet_ref = console.nextInt();
		String refString = Integer.toString(Pet_ref);
		
       System.out.println("What is the start date time of the booking? (dd-MM-yyyy HH:mm)");
       console.nextLine();
       String date = console.nextLine();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	    LocalDateTime bookingstartDateTime = LocalDateTime.parse(date, formatter);
       
       
		if (refString.length() == 5) {

			chosenSurgery.removebooking(Pet_ref, bookingstartDateTime);

		} else {
			System.out.println("Pet reference numbers must be 5 digits.");
		}

	}
	
public static void addconsultingSurgery(Surgery chosenSurgery) {
		
	String refString;
	 System.out.println("Enter the staff reference number:");
	 refString = console.next();
	 int ref = Integer.parseInt(refString);
	 String surgery = "";
	System.out.println("Enter the name of the surgery you want to add:");
	surgery=console.next().toLowerCase();
	surgery = surgery.substring(0, 1).toUpperCase()+ surgery.substring(1);
	for(Surgery s:surgeries) {
		   if(s.getName()== surgery) {
			   Surgery surg = s;
			   String result = chosenSurgery.getConsultingSurgeryList(ref, surg);
				 System.out.println(result);
				 break;
		   }
	   }
		
	}

	
		 
		 
		 
		 
		 
		 
		 
	
	
	
	
	
}