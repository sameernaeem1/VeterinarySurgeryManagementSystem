package ljmu.oosys;

import java.io.Serializable;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Surgery implements Serializable {

	private String name;
	private String postcode;
	private LocalTime openingTime;
	private LocalTime closingTime;
	private DayOfWeek unavailableDay;
	private LocalTime unavailableStartTime;
	private LocalTime unavailableEndTime;
	private List<Pet> pets = new ArrayList<Pet>();
	private List<Staff> allStaff = new ArrayList<Staff>();
	private List<Booking> bookings = new ArrayList<Booking>();

	public Surgery(String name, String postcode, LocalTime openingTime, LocalTime closingTime, DayOfWeek unavailableDay,
			LocalTime unavailableStartTime, LocalTime unavailableEndTime, List<Pet> pets, List<Staff> allStaff,
			List<Booking> bookings) {

		this.name = name;
		this.postcode = postcode;
		this.openingTime = openingTime;
		this.closingTime = closingTime;
		this.unavailableDay = unavailableDay;
		this.unavailableStartTime = unavailableStartTime;
		this.unavailableEndTime = unavailableEndTime;
		this.pets = pets;
		this.allStaff = allStaff;
		this.bookings = bookings;

	}

	public String getName() {

		return this.name;

	}

	public void setName(String Name) {
		this.name = Name;
	}

	public void addBooking(Booking booking) {
		bookings.add(booking);
		System.out.println("Booked succssceed:");
	}

	public void registerStaff(Staff staff) {
		allStaff.add(staff);
	}

	public void removeStaff(String staffName, int ref) {
		for (Staff staff : allStaff) {
			if (staff.getName().equals(staffName) && staff.getRef() == ref) {
				// Staff with the specified name and reference number found
				allStaff.remove(staff);
				System.out.println("Staff removed: " + staff.getName() + " Ref:" + staff.getRef());
				return; // Exit the method after removing the staff
			}
		}

		// Staff not found
		System.out.println("Staff not found with name: " + staffName + " and reference number: " + ref);
	}

	public void searchStaff(String staffName) {

		boolean nameFound = false;
		for (Staff staff : allStaff) {
			if (staff.getName().equals(staffName)) {
				// Staff with the specified name found
				System.out.println("Staff found: " + staff.getName() + " Staff ref:" + staff.getRef());
				nameFound = true;
			}

		}
		if (!nameFound) {
			System.out.println("Staff not found with name: " + staffName);
		}

	}

	public void registerPet(Pet pet) {
		pets.add(pet);
	}

	public void removePet(String PetName, int ref) {
		for (Pet pet : pets) {
			if (pet.getName().equals(PetName) && pet.getRef() == ref) {
				// Pet with the specified name and reference number found
				pets.remove(pet);
				System.out.println("Pet removed: " + pet.getName() + " Ref:" + pet.getRef());
				return;
			}
		}

		// Staff not found
		System.out.println("Pet not found with name: " + PetName + " and reference number: " + ref);
	}

	public void searchPet(String PetName) {

		boolean nameFound = false;
		for (Pet pet : pets) {
			if (pet.getName().equals(PetName)) {
				// Pet with the specified name found
				System.out.println("Pet found: " + pet.getName() + " Pet ref:" + pet.getRef()+ "  "+"Pet Registered Date:" + pet.getregDate());
				nameFound = true;
			}

		}
		if (!nameFound) {
			System.out.println("Staff not found with name: " + PetName);
		}

	}

	public List<Staff> getallStaff() {
		return allStaff;
	}

	public List<Pet> getallPets() {
		return pets;
	}

	public List<Booking> getallbookings() {
		return bookings;
	}

	public void f() {
		if (allStaff.isEmpty()) {
			System.out.println("No staff members registered.");
		} else {
			System.out.println("----- All Staff Members -----");
			for (Staff staff : allStaff) {
				System.out.println(staff.getName() + " (Ref: " + staff.getRef() + ")");
			}
		}
	}

	public void o() {
		if (pets.isEmpty()) {
			System.out.println("No pet members registered.");
		} else {
			System.out.println("----- All Pet Members -----");
			for (Pet pet : pets) {
				System.out.println(pet.getName() + " Ref: " + pet.getRef() + "  " + "Species: " + pet.getSpecies()
						+ "  unique Type: " + pet.getunique());
			}
		}
	}

	public String showAllPetBooking(int ref , int staffRef) {
		StringBuilder everyBooking = new StringBuilder();
		boolean areBookings = false;

		for (Booking b : bookings) {
			if (b.getPet().getRef() == ref) {
				
				areBookings = true;
				everyBooking.append(String.format("%s%s%s%s%s%s",

						("Reference ID:" + b.getRef() + "  "), ("StartDate: " + b.getStartDate() + " "),
						("EndDate:" + b.getEndDate() + " "), ("Pet:" + b.getPet().getName()+"  "), ("Staff:" + b.getStaffByRef(staffRef).getName()+"  "),
						("surgery:" + b.getSurgery().getName())));

				everyBooking.append("\n");
			}
		}
		if (!areBookings) {
			return "This pet has no bookings.";
		}
		return everyBooking.toString();
	}
	
	
	public String nextPetBooking(int ref, int staffRef) {
	    LocalDateTime currentDateTime = LocalDateTime.now();

	    for (Booking b : bookings) {
	        if (b.getPet().getRef() == ref && b.getStartDate().isAfter(currentDateTime)) {
	            return String.format("%s%s%s%s%s%s",
	                    ("Reference ID:" + b.getRef() + "  "),
	                    ("StartDate: " + b.getStartDate() + " "),
	                    ("EndDate:" + b.getEndDate() + " "),
	                    ("Pet:" +b.getPet().getName()+"  "),
	                    ("Staff:" + b.getStaffByRef(staffRef).getName()+ "  "),
	                    ("surgery:" + b.getSurgery().getName()));
	        }
	    }

	    return "No future bookings for this pet.";
	}
	
	
	
	public String showAllStaffBooking(int ref) {
	    StringBuilder everyBooking = new StringBuilder();
	    boolean areBookings = false;

	    for (Booking b : bookings) {
	        for (Staff s : b.getStaff()) {
	            if (s.getRef() == ref) {
	                areBookings = true;
	                everyBooking.append(String.format("%s%s%s%s%s%s",
	                        ("Reference ID:" + b.getRef() + "  "),
	                        ("StartDate: " + b.getStartDate() + " "),
	                        ("EndDate:" + b.getEndDate() + " "),
	                        ("Pet:" + b.getPet().getName()+" "),
	                        ("Staff:" + b.getStaffByRef(ref).getName()+" "),
	                        ("surgery:" + b.getSurgery().getName())));
	                everyBooking.append("\n");
	            }
	        }
	    }

	    if (!areBookings) {
	        return "This staff member has no bookings.";
	    }

	    return everyBooking.toString();
	}
	
	
	
	public String nextStaffBooking(int ref) {
		
	    LocalDateTime currentDateTime = LocalDateTime.now();
	    for (Booking b : bookings) {
	    	for (Staff s : b.getStaff()) {
	    		if (s.getRef() == ref && b.getStartDate().isAfter(currentDateTime)) {
					return String.format("%s%s%s%s%s%s",
					("Reference ID:" + b.getRef() + "  "),
					("StartDate: " + b.getStartDate() + " "),
					("EndDate:" + b.getEndDate() + " "),
					("Pet:" +b.getPet().getName()+"  "),
					("Staff:" + b.getStaffByRef(ref).getName()+ "  "),
					("surgery:" + b.getSurgery().getName()));
		        }
		    }
	    	}
	        

	    return "No future bookings for this staff member.";
	}
	
	
	// fix the print statement here 
	public String searchBooking(LocalDateTime startDate, String petName, Surgery chosenSurgery) {
	    for (Booking booking : bookings) {
	        if (booking.getStartDate().equals(startDate) &&
	            booking.getPet().getName().equals(petName) &&
	            booking.getSurgery().equals(chosenSurgery)) {
	            System.out.println(booking.toString());
	        }
	    }
	    return "Booking not found";
	}

	
	public void removebooking(int Petref, LocalDateTime startDate) {
		for (Booking b : bookings) {
			if (b.getPet().getRef()==Petref && b.getStartDate().equals(startDate)) {
				bookings.remove(b);
				System.out.println("Booking removed: " + b.getRef());
			}
			else{System.out.println("Booking not found with PetRef : " + Petref);
	     }
		}
	}

	public DayOfWeek getUnavailableDay() {
		return this.unavailableDay;
	}
	public LocalTime getUnavailableStartTime() {
		return this.unavailableStartTime;
	}
	public LocalTime getUnavailableEndTime() {
		return this.unavailableEndTime;
	}
	
	public String getConsultingSurgeryList(int ref, Surgery surg) {
		List<Surgeon> allSurgeons = new ArrayList<Surgeon>();
		for(Staff s:allStaff) {
			if(s instanceof Surgeon) {
				allSurgeons.add((Surgeon) s);
			}
		}
		
		for(Surgeon s:allSurgeons) {
			   if(s.getRef()== ref) {
				   s.getsonsultingSurgeries().add(surg);
			   }
		   }
		return "Consulting surgery added";
	}

}