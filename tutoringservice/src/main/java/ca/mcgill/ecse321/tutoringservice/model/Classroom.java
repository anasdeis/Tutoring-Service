package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class Classroom {
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
   
   private boolean isBooked;
   
   public void setIsBooked(boolean value) {
      this.isBooked = value;
   }
   
   public boolean isIsBooked() {
      return this.isBooked;
   }
   
   private boolean isBigRoom;
   
   public void setIsBigRoom(boolean value) {
      this.isBigRoom = value;
   }
   
   public boolean isIsBigRoom() {
      return this.isBigRoom;
   }
   
   /**
    * <pre>
    *           0..13     1..1
    * Classroom ------------------------- Manager
    *           classroom        &lt;       manager
    * </pre>
    */
   private Manager manager;
   
   public void setManager(Manager value) {
      this.manager = value;
   }
   
   public Manager getManager() {
      return this.manager;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Classroom ------------------------- TutoringSystem
    *           classroom        &lt;       tutoringSystem
    * </pre>
    */
   private TutoringSystem tutoringSystem;
   
   public void setTutoringSystem(TutoringSystem value) {
      this.tutoringSystem = value;
   }
   
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   }
