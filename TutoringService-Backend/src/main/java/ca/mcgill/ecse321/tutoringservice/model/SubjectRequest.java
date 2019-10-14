package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

@Entity
public class SubjectRequest{
   private Set<Student> student;
   
   @ManyToMany(mappedBy="subjectRequest" )
   public Set<Student> getStudent() {
      return this.student;
   }
   
   public void setStudent(Set<Student> students) {
      this.student = students;
   }
   
   private Manager manager;
   
   @ManyToOne(optional=false)
   public Manager getManager() {
      return this.manager;
   }
   
   public void setManager(Manager manager) {
      this.manager = manager;
   }
   
   private Integer requestID;

public void setRequestID(Integer value) {
    this.requestID = value;
}
@Id
public Integer getRequestID() {
    return this.requestID;
}
private String name;

public void setName(String value) {
    this.name = value;
}
public String getName() {
    return this.name;
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
   private TutoringSystem tutoringSystem;
   
   @ManyToOne(optional=false)
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   public void setTutoringSystem(TutoringSystem tutoringSystem) {
      this.tutoringSystem = tutoringSystem;
   }
   
   }
