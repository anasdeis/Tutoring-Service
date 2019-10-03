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
   
   /**
    * <pre>
    *           1..1     1..1
    * SubjectRequest ------------------------> Subject
    *           subjectRequest        &gt;       subject
    * </pre>
    */
   private Subject subject;
   
   public void setSubject(Subject value) {
      this.subject = value;
   }
   
   public Subject getSubject() {
      return this.subject;
   }
   
   }
