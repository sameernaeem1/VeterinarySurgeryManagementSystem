package ljmu.oosys;
import ljmu.oosys.Surgery;
import java.util.ArrayList;
import java.util.List;

/**
 * Nurse does not have any different methods to surgeon so it simply inherits everything from Staff class.
 */
public class Nurse extends Staff {
	
	public Nurse (int ref, String name, Surgery defaultSurgery, List <Booking> bookings) {
		super(ref, name, defaultSurgery, bookings);
	}
  
	public void setName(String name){
 this.name=name;
}
	
	
}