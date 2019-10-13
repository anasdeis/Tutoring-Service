package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity
public enum SubjectType{
   private String highSchoolCourse;

public void setHighSchoolCourse(String value) {
    this.highSchoolCourse = value;
}
public String getHighSchoolCourse() {
    return this.highSchoolCourse;
}
private String cegepCourse;

public void setCegepCourse(String value) {
    this.cegepCourse = value;
}
public String getCegepCourse() {
    return this.cegepCourse;
}
private String universityCourse;

public void setUniversityCourse(String value) {
    this.universityCourse = value;
}
public String getUniversityCourse() {
    return this.universityCourse;
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
