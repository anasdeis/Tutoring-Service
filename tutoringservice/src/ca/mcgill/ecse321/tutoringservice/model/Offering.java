package ca.mcgill.ecse321.tutoringservice.model;
import AvaliableSession;

import java.util.Set;
import java.util.HashSet;

public class Offering {
/**
    * <pre>
    *           1..2     1..1
    * TutorRole ------------------------- Tutor
    *           tutorRole        &lt;       tutor
    * </pre>
    */
   private Tutor tutor;
   
   public void setTutor(Tutor value) {
      this.tutor = value;
   }
   
   public Tutor getTutor() {
      return this.tutor;
   }
   
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
    *           0..*     0..*
    * Offering ------------------------- RegisteredTutor
    *           offeringsTaught        &lt;       offeringTutors
    * </pre>
    */
   private Set<RegisteredTutor> offeringTutors;
   
   public Set<RegisteredTutor> getOfferingTutors() {
      if (this.offeringTutors == null) {
         this.offeringTutors = new HashSet<RegisteredTutor>();
      }
      return this.offeringTutors;
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
   
   private double price;
   
   public void setPrice(double value) {
      this.price = value;
   }
   
   public double getPrice() {
      return this.price;
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
    *           0..*     1..1
    * Offering ------------------------> AvaliableSession
    *           offering        &gt;       classTime
    * </pre>
    */
   private AvaliableSession classTime;
   
   public void setClassTime(AvaliableSession value) {
      this.classTime = value;
   }
   
   public AvaliableSession getClassTime() {
      return this.classTime;
   }
   
   }
