package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class SubjectRequest {
   /**
    * <pre>
    *           0..*     0..*
    * SubjectRequest ------------------------- Student
    *           subjectRequest        &gt;       student
    * </pre>
    */
   private Set<Student> student;
   
   public Set<Student> getStudent() {
      if (this.student == null) {
         this.student = new HashSet<Student>();
      }
      return this.student;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * SubjectRequest ------------------------- Manager
    *           subjectRequest        &gt;       manager
    * </pre>
    */
   private Manager manager;
   
   public void setManager(Manager value) {
      this.manager = value;
   }
   
   public Manager getManager() {
      return this.manager;
   }
   
   private Integer requestID;
   
   public void setRequestID(Integer value) {
      this.requestID = value;
   }
   
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
   
   private SubjectType subjectType;
   
   public void setSubjectType(SubjectType value) {
      this.subjectType = value;
   }
   
   public SubjectType getSubjectType() {
      return this.subjectType;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * SubjectRequest ------------------------- TutoringSystem
    *           subjectRequest        &lt;       tutoringSystem
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
