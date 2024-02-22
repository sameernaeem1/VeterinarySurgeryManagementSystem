package ljmu.oosys;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


public class Booking implements Serializable {

    private UUID ref;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Pet pet;
    private Surgery surgery;
    private List<Staff> bookingStaff = new ArrayList<Staff>();


      public Booking (UUID ref, LocalDateTime startDate, LocalDateTime endDate, Pet pet, Surgery surgery, List<Staff> bookingStaff) {
      this.surgery=surgery;
      this.ref = ref;
      this.startDate = startDate;
      this.endDate = endDate;
      this.pet = pet;
      this.bookingStaff = bookingStaff;
      } ;
      
      public Pet getPet() {
          return this.pet;
      }

      public List<Staff> getStaff(){
         return this.bookingStaff;
      }
      public Staff getStaffByRef(int ref) {
        for (Staff staff : bookingStaff) {
            if (staff.getRef() == ref) {
                return staff;
            }
        }
        // If no matching staff is found
        return null;
    }
     



       
      
      @Override
      public String toString() {
          StringBuilder staffNames = new StringBuilder();
          for (Staff s : bookingStaff) {
              staffNames.append("{Staff:").append(s.getName()).append("} ");
          }

          return String.format("%s%s%s%s%s%s", ("{Reference ID:" + ref + "}  "), ("{StartDate: " + startDate + "} "),
                  ("{EndDate:" + endDate + "} "), ("{Pet:" + pet.getName()+"}  "), (staffNames.toString()),
                  ("{Surgery:" + surgery.getName())+"}");
      }
      

 
    public UUID getRef() {
    	return this.ref;
    }
    public LocalDateTime getStartDate() {
    	return this.startDate;
    }
    public LocalDateTime getEndDate() {
    	return this.endDate;
    } 
    public Surgery getSurgery() {
    	return this.surgery;
    }
 
}