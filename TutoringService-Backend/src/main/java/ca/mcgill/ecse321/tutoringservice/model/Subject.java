package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class Subject {
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
   
   public String getCourseID() {
      return this.courseID;
   }
   
   /**
    * <pre>
    *           1..1     0..*
    * Subject ------------------------- Offering
    *           subject        &gt;       offering
    * </pre>
    */
   private Set<Offering> offering;
   
   public Set<Offering> getOffering() {
      if (this.offering == null) {
         this.offering = new HashSet<Offering>();
      }
      return this.offering;
   }
   
   private String description;
   
   public void setDescription(String value) {
      this.description = value;
   }
   
   public String getDescription() {
      return this.description;
   }
   
   private SubjectType subjectType;
   
   public void setSubjectType(SubjectType value) {
      this.subjectType = value;
   }
   
   public SubjectType getSubjectType() {
      return this.subjectType;
   }
   
   /**
    * <pre>
    *           0..*     0..1
    * Subject ------------------------- University
    *           subject        &lt;       university
    * </pre>
    */
   private University university;
   
   public void setUniversity(University value) {
      this.university = value;
   }
   
   public University getUniversity() {
      return this.university;
   }
   
   /**
    * <pre>
    *           0..*     0..*
    * Subject ------------------------- TutorApplication
    *           subject        &lt;       tutorRole
    * </pre>
    */
   private Set<TutorApplication> tutorRole;
   
   public Set<TutorApplication> getTutorRole() {
      if (this.tutorRole == null) {
         this.tutorRole = new HashSet<TutorApplication>();
      }
      return this.tutorRole;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * Subject ------------------------- TutoringSystem
    *           subject        &lt;       tutoringSystem
    * </pre>
    */
   private TutoringSystem tutoringSystem;
   
   public void setTutoringSystem(TutoringSystem value) {
      this.tutoringSystem = value;
   }
   
   public TutoringSystem getTutoringSystem() {
      return this.tutoringSystem;
   }
   
   }
