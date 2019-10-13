package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class Offering {
   private String offeringID;
   
   public void setOfferingID(String value) {
      this.offeringID = value;
   }
   
   public String getOfferingID() {
      return this.offeringID;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Offering ------------------------- Student
    *           coursesTaken        &lt;       studentsEnrolled
    * </pre>
    */
   private Set<Student> studentsEnrolled;
   
   public Set<Student> getStudentsEnrolled() {
      if (this.studentsEnrolled == null) {
         this.studentsEnrolled = new HashSet<Student>();
      }
      return this.studentsEnrolled;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Offering ------------------------- Subject
    *           offering        &lt;       subject
    * </pre>
    */
   private Subject subject;
   
   public void setSubject(Subject value) {
      this.subject = value;
   }
   
   public Subject getSubject() {
      return this.subject;
   }
   
   private String term;
   
   public void setTerm(String value) {
      this.term = value;
   }
   
   public String getTerm() {
      return this.term;
   }
   
   private double pricePerHour;
   
   public void setPricePerHour(double value) {
      this.pricePerHour = value;
   }
   
   public double getPricePerHour() {
      return this.pricePerHour;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Offering ------------------------- Classroom
    *           offering        &lt;       classroom
    * </pre>
    */
   private Classroom classroom;
   
   public void setClassroom(Classroom value) {
      this.classroom = value;
   }
   
   public Classroom getClassroom() {
      return this.classroom;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Offering ------------------------> AvaliableSession
    *           offering        &gt;       classTime
    * </pre>
    */
   private Set<AvaliableSession> classTime;
   
   public Set<AvaliableSession> getClassTime() {
      if (this.classTime == null) {
         this.classTime = new HashSet<AvaliableSession>();
      }
      return this.classTime;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Offering ------------------------- Review
    *           offering        &lt;       review
    * </pre>
    */
   private Set<Review> review;
   
   public Set<Review> getReview() {
      if (this.review == null) {
         this.review = new HashSet<Review>();
      }
      return this.review;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Offering ------------------------- Commission
    *           offering        &lt;       commission
    * </pre>
    */
   private Commission commission;
   
   public void setCommission(Commission value) {
      this.commission = value;
   }
   
   public Commission getCommission() {
      return this.commission;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Offering ------------------------- Tutor
    *           offering        &lt;       tutor
    * </pre>
    */
   private Tutor tutor;
   
   public void setTutor(Tutor value) {
      this.tutor = value;
   }
   
   public Tutor getTutor() {
      return this.tutor;
   }
   
   private TutoringSystem tutoringSystem;
   
   public void setTutoringSystem(TutoringSystem value) {
      this.tutoringSystem = value;
   }
   
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   }
