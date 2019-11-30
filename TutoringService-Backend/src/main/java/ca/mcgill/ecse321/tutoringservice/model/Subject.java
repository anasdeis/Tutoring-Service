package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.ManyToOne;
import javax.persistence.ManyToMany;

@Entity
public class Subject{
   private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
}
private String courseID;

public void setCourseID(String value) {
    this.courseID = value;
}
@Id
public String getCourseID() {
    return this.courseID;
}
private Set<Offering> offering;

@OneToMany(mappedBy="subject" , cascade={CascadeType.ALL})
public Set<Offering> getOffering() {
   return this.offering;
}

public void setOffering(Set<Offering> offerings) {
   this.offering = offerings;
}

private String description;

public void setDescription(String value) {
    this.description = value;
}
public String getDescription() {
    return this.description;
}
@Enumerated(EnumType.STRING)
private SubjectType subjectType;

public void setSubjectType(SubjectType value) {
    this.subjectType = value;
}
public SubjectType getSubjectType() {
    return this.subjectType;
}
   private University university;
   
   @ManyToOne
   public University getUniversity() {
      return this.university;
   }
   
   public void setUniversity(University university) {
      this.university = university;
   }
   
   private Set<TutorApplication> tutorRole;
   
   @ManyToMany
   public Set<TutorApplication> getTutorRole() {
      return this.tutorRole;
   }
   
   public void setTutorRole(Set<TutorApplication> tutorRoles) {
      this.tutorRole = tutorRoles;
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
