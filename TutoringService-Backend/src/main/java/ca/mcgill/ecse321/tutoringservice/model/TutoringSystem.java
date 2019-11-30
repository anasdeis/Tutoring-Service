package ca.mcgill.ecse321.tutoringservice.model;

import javax.persistence.Entity;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.OneToMany;
import javax.persistence.Id;

@Entity
public class TutoringSystem{
   private Set<SubjectRequest> subjectRequest;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<SubjectRequest> getSubjectRequest() {
      return this.subjectRequest;
   }
   
   public void setSubjectRequest(Set<SubjectRequest> subjectRequests) {
      this.subjectRequest = subjectRequests;
   }
   
   private Set<Subject> subject;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<Subject> getSubject() {
      return this.subject;
   }
   
   public void setSubject(Set<Subject> subjects) {
      this.subject = subjects;
   }
   
   private Set<Person> person;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<Person> getPerson() {
      return this.person;
   }
   
   public void setPerson(Set<Person> persons) {
      this.person = persons;
   }
   
   private Set<University> university;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<University> getUniversity() {
      return this.university;
   }
   
   public void setUniversity(Set<University> universitys) {
      this.university = universitys;
   }
   
   private Set<TutorApplication> tutorApplication;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<TutorApplication> getTutorApplication() {
      return this.tutorApplication;
   }
   
   public void setTutorApplication(Set<TutorApplication> tutorApplications) {
      this.tutorApplication = tutorApplications;
   }
   
   private Set<Review> review;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<Review> getReview() {
      return this.review;
   }
   
   public void setReview(Set<Review> reviews) {
      this.review = reviews;
   }
   
   private Set<AvailableSession> AvailableSession;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<AvailableSession> getAvailableSession() {
      return this.AvailableSession;
   }
   
   public void setAvailableSession(Set<AvailableSession> AvailableSessions) {
      this.AvailableSession = AvailableSessions;
   }
   
   private Set<Classroom> classroom;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<Classroom> getClassroom() {
      return this.classroom;
   }
   
   public void setClassroom(Set<Classroom> classrooms) {
      this.classroom = classrooms;
   }
   
   private Set<Commission> commission;
   
   @OneToMany(mappedBy="tutoringSystem" , cascade={CascadeType.ALL})
   public Set<Commission> getCommission() {
      return this.commission;
   }
   
   public void setCommission(Set<Commission> commissions) {
      this.commission = commissions;
   }
   
   private Integer tutoringSystemID;

public void setTutoringSystemID(Integer value) {
    this.tutoringSystemID = value;
}
@Id
public Integer getTutoringSystemID() {
    return this.tutoringSystemID;
}
}
