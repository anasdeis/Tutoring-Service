package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Tutor extends Person{
   private Set<TutorApplication> tutorApplication;
   
   @OneToMany(mappedBy="tutor" )
   public Set<TutorApplication> getTutorApplication() {
      return this.tutorApplication;
   }
   
   public void setTutorApplication(Set<TutorApplication> tutorApplications) {
      this.tutorApplication = tutorApplications;
   }
   
   private Boolean isRegistered;

public void setIsRegistered(Boolean value) {
    this.isRegistered = value;
}
public Boolean getIsRegistered() {
    return this.isRegistered;
}
   private Set<Offering> offering;
   
   @OneToMany(mappedBy="tutor" )
   public Set<Offering> getOffering() {
      return this.offering;
   }
   
   public void setOffering(Set<Offering> offerings) {
      this.offering = offerings;
   }
   
   private Set<AvailableSession> AvailableSession;
   
   @ManyToMany(mappedBy="tutor" )
   public Set<AvailableSession> getAvailableSession() {
      return this.AvailableSession;
   }
   
   public void setAvailableSession(Set<AvailableSession> AvailableSessions) {
      this.AvailableSession = AvailableSessions;
   }
   
   }
