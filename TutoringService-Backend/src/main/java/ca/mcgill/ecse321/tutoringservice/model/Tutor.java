package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.OneToMany;
import javax.persistence.ManyToMany;

@Entity
public class Tutor extends Person{
   private Integer tutorID;

public void setTutorID(Integer value) {
    this.tutorID = value;
}
@Id
public Integer getTutorID() {
    return this.tutorID;
}
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
   
   private Set<AvaliableSession> avaliableSession;
   
   @ManyToMany(mappedBy="tutor" )
   public Set<AvaliableSession> getAvaliableSession() {
      return this.avaliableSession;
   }
   
   public void setAvaliableSession(Set<AvaliableSession> avaliableSessions) {
      this.avaliableSession = avaliableSessions;
   }
   
   }
