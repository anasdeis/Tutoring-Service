package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class TutorApplication{
private Integer applicationId;

public void setApplicationId(Integer value) {
this.applicationId = value;
}
@Id
public Integer getApplicationId() {
return this.applicationId;
}
private Set<Subject> subject;

@ManyToMany(mappedBy="tutorRole" )
public Set<Subject> getSubject() {
   return this.subject;
}

public void setSubject(Set<Subject> subjects) {
   this.subject = subjects;
}

private Tutor tutor;

@ManyToOne(optional=false)
public Tutor getTutor() {
   return this.tutor;
}

public void setTutor(Tutor tutor) {
   this.tutor = tutor;
}

private Boolean isAccepted;

public void setIsAccepted(Boolean value) {
    this.isAccepted = value;
}
public Boolean getIsAccepted() {
    return this.isAccepted;
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
