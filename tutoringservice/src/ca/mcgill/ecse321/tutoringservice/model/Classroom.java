package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public abstract class Classroom {
private boolean isBooked;
   
   public void setIsBooked(boolean value) {
      this.isBooked = value;
   }
   
   public boolean isIsBooked() {
      return this.isBooked;
   }
   
   
   private String roomCode;
   
   public void setRoomCode(String value) {
      this.roomCode = value;
   }
   
   public String getRoomCode() {
      return this.roomCode;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Classroom ------------------------- Offering
    *           classroom        &gt;       offering
    * </pre>
    */
   private Set<Offering> offering;
   
   public Set<Offering> getOffering() {
      if (this.offering == null) {
         this.offering = new HashSet<Offering>();
      }
      return this.offering;
   }
   
   }
