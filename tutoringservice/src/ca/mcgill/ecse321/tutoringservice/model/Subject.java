package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public abstract class Subject {
   /**
    * <pre>
    *           1..*     0..*
    * Subject ------------------------- RegisteredTutor
    *           subjectsTaught        &gt;       subjectTutors
    * </pre>
    */
   private Set<RegisteredTutor> subjectTutors;
   
   public Set<RegisteredTutor> getSubjectTutors() {
      if (this.subjectTutors == null) {
         this.subjectTutors = new HashSet<RegisteredTutor>();
      }
      return this.subjectTutors;
   }
   
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
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
   
   private String courseID;
   
   public void setCourseID(String value) {
      this.courseID = value;
   }
   
   public String getCourseID() {
      return this.courseID;
   }
   
   private String description;
   
   public void setDescription(String value) {
      this.description = value;
   }
   
   public String getDescription() {
      return this.description;
   }
   
   private String subjectCategory;
   
   public void setSubjectCategory(String value) {
      this.subjectCategory = value;
   }
   
   public String getSubjectCategory() {
      return this.subjectCategory;
   }
   
   }
