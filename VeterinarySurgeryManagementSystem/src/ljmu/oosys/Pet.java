package ljmu.oosys;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.List;
import java.util.ArrayList;

public class Pet implements Serializable {

	private int ref;
	private String name;
	private String species;
	private LocalDate regDate;
	private String uniqueType;
	private Surgery surgery;
	private List<Booking> bookings = new ArrayList<Booking>();
	
	

	public Pet(int ref, String name , String species, LocalDate regDate, String uniqueType, Surgery surgery , List<Booking> bookings) {
		this.ref=ref;
		this.name=name;
		this.species=species;
		this.regDate=regDate;
		this.uniqueType=uniqueType;
		this.surgery=surgery;
		this.bookings=bookings;
	};
	

  public String getunique(){
	return this.uniqueType;
  }
 public String getSpecies(){
	return this.species;
 }
 
 public LocalDate getregDate(){
	return this.regDate;
 }
 
 public String getName(){
	return name;
 }

 public int getRef() {
	 return ref;
 }

}