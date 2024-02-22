package ljmu.oosys;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
/**
 * This class if used to generalise nurses and surgeons into one category as they share certain attributes and operations.
 */
public class Staff implements Serializable {
	
	private int ref;
	protected String name;
	protected Surgery defaultSurgery;
	protected List<Booking> bookings = new ArrayList<Booking>();
	
	public Staff (int ref, String name, Surgery defaultSurgery, List <Booking> bookings) {
		
		this.ref = ref;
		this.name = name;
		this.defaultSurgery = defaultSurgery;
		this.bookings = bookings;
		
	}
		
	
	//This was implemented with the idea of availability check but was used to make sure booking is valid.
	
	/*
	 * Function is used to ensure that booking is not made when staff won't be at the surgery.
	 */
	public boolean isAvailable(LocalDateTime startDate, LocalDateTime endDate) {
	    LocalDateTime currentDateTime = LocalDateTime.now();

	    for (Booking b : bookings) {
	        // Check if the current booking conflicts with the provided date range
	        if ((b.getStartDate().isBefore(startDate)) && (b.getEndDate().isAfter(startDate))) {
	            return false;
	        }
	        if ((b.getStartDate().isBefore(endDate)) && (b.getEndDate().isAfter(endDate))) {
	            return false;
	        }
	        if ((b.getStartDate().isAfter(startDate)) && (b.getEndDate().isBefore(endDate))) {
	            return false;
	        }
	        if ((b.getStartDate().isBefore(startDate)) && (b.getEndDate().isAfter(endDate))) {
	            return false;
	        }
	        if (b.getStartDate().isBefore(currentDateTime)) {
	            return false;
	        }
	        //equal to
	        if (b.getSurgery().getUnavailableDay() == LocalDate.now().getDayOfWeek() &&
		            b.getSurgery().getUnavailableStartTime().equals(startDate.toLocalTime()) &&
		            b.getSurgery().getUnavailableEndTime().equals(endDate.toLocalTime())) {
		            return false;
		        }
	        // In between.
	        if (b.getSurgery().getUnavailableDay() == LocalDate.now().getDayOfWeek() &&
	            b.getSurgery().getUnavailableStartTime().isBefore(startDate.toLocalTime()) &&
	            b.getSurgery().getUnavailableEndTime().isAfter(endDate.toLocalTime())) {
	            return false;
	        }
	        // Starts before end of unavailable and finishes after unavailable
	        if (b.getSurgery().getUnavailableDay() == LocalDate.now().getDayOfWeek() &&
	            b.getSurgery().getUnavailableEndTime().isAfter(startDate.toLocalTime()) &&
	            b.getSurgery().getUnavailableEndTime().isBefore(endDate.toLocalTime())) {
	            return false;
	        }
	        // Starts before start of unavailable and finishes before unavailable end
	        if (b.getSurgery().getUnavailableDay() == LocalDate.now().getDayOfWeek() &&
	            b.getSurgery().getUnavailableStartTime().isAfter(startDate.toLocalTime()) &&
	            b.getSurgery().getUnavailableStartTime().isBefore(endDate.toLocalTime())) {
	            return false;
	        }
	    }

	    // No conflicts found, the resource is available for the given date range
	    return true;
	}

	
	
	
	
	
	
	
	public int getRef() {
		
		return this.ref;
		
	}
	
	public String getName() {
		
		return this.name;
		
	}
	
	public Surgery getDefaultSurgery() {
		
		return this.defaultSurgery;
		
	}

	public List <Booking> getBookings() {
	
	return this.bookings;
	

}

public void staffAvailablityCheck(){
	// could not finishing it at that moment, due to the time limit
}

}
