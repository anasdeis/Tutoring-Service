package ca.mcgill.ecse321.tutoringservice.model;

import java.util.Set;
import java.util.HashSet;

public class University {
   private String name;
   
   public void setName(String value) {
      this.name = value;
   }
   
   public String getName() {
      return this.name;
   }
   
   /**
    * <pre>
    *           0..1     0..*
    * University ------------------------- Subject
    *           university        &gt;       subject
    * </pre>
    */
   private Set<Subject> subject;
   
   public Set<Subject> getSubject() {
      if (this.subject == null) {
         this.subject = new HashSet<Subject>();
      }
      return this.subject;
   }
   
   /**
    * <pre>
    *           0..*     1..1
    * University ------------------------- TutoringSystem
    *           university        &lt;       tutoringSystem
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
