package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;

@Entity
public class Classroom{
   private String roomCode;

public void setRoomCode(String value) {
    this.roomCode = value;
}
@Id
public String getRoomCode() {
    return this.roomCode;
}
private Set<Offering> offering;

@OneToMany(mappedBy="classroom" )
public Set<Offering> getOffering() {
   return this.offering;
}

public void setOffering(Set<Offering> offerings) {
   this.offering = offerings;
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
   private Manager manager;
   
   @ManyToOne(optional=false)
   public Manager getManager() {
      return this.manager;
   }
   
   public void setManager(Manager manager) {
      this.manager = manager;
   }
   
   private TutoringSystem tutoringSystem;
   
   @ManyToOne(optional=false)
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   public void setTutoringSystem(TutoringSystem tutoringSystem) {
      this.tutoringSystem = tutoringSystem;
   }
   
   }
