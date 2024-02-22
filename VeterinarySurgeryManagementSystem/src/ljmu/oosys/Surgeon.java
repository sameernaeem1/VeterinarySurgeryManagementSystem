package ljmu.oosys;

import java.util.ArrayList;
import java.util.List;

/**
 * As the surgeon can also add consulting surgeries, this is used as an extra attribute as an ArrayList.
 */
public class Surgeon extends Staff {
    
	//Attribute specific to surgeon that nurse does not have.
	private List<Surgery> consultingSurgeries = new ArrayList<Surgery>();

	public Surgeon(int ref, String name, Surgery defaultSurgery, List <Booking> bookings, List <Surgery> consultingSurgeries) {

		super(ref, name, defaultSurgery, bookings);
		this.consultingSurgeries = consultingSurgeries;
	}
	
	public List<Surgery> getsonsultingSurgeries() {
		return this.consultingSurgeries;
	}
  
}

