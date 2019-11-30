package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Set;
import javax.persistence.ManyToMany;

@Entity
public class Student extends Person{
private Set<Offering> coursesTaken;

@ManyToMany(mappedBy="studentsEnrolled" )
public Set<Offering> getCoursesTaken() {
   return this.coursesTaken;
}

public void setCoursesTaken(Set<Offering> coursesTakens) {
   this.coursesTaken = coursesTakens;
}

private Set<SubjectRequest> subjectRequest;

@ManyToMany
public Set<SubjectRequest> getSubjectRequest() {
   return this.subjectRequest;
}

public void setSubjectRequest(Set<SubjectRequest> subjectRequests) {
   this.subjectRequest = subjectRequests;
}

private Integer numCoursesEnrolled;

public void setNumCoursesEnrolled(Integer value) {
    this.numCoursesEnrolled = value;
}
public Integer getNumCoursesEnrolled() {
    return this.numCoursesEnrolled;
}
}
